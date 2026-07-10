package com.redwood.scheduler.custom.snowflake;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redwood.scheduler.infrastructure.logging.api.Logger;

import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SnowflakeClient {

    private static final int TIMEOUT = 600;

    private final SnowflakeConfig config;

    private final HttpClient httpClient;
    private final ObjectMapper mapper;
    private final String jwt;
    private final Logger log;

    public SnowflakeClient(
            SnowflakeConfig config, String jwt, Logger log) {

        this.config = config;
        this.jwt = jwt;
        this.log = log;
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public SnowflakeResponse execute(
            String statement,
            Map<Integer, List<String>> bindings)
            throws Exception {

        String payload =
                buildPayload(
                        statement,
                        bindings);

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(config.getEndpoint()))
                        .header(
                                "Authorization",
                                "Bearer " + jwt)
                        .header(
                                "X-Snowflake-Authorization-Token-Type",
                                "KEYPAIR_JWT")
                        .header(
                                "Content-Type",
                                "application/json; charset=UTF-8")
                        .header(
                                "Accept",
                                "*/*")
                        .POST(
                                HttpRequest.BodyPublishers.ofString(
                                        payload))
                        .build();
        log.info("Endpoint: " + config.getEndpoint());


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return parseResponse(response.body());

    }

    private String buildPayload(
            String statement,
            Map<Integer, List<String>> bindings)
            throws Exception {

        ObjectNode root =
                mapper.createObjectNode();

        root.put("statement", statement);
        root.put("timeout", TIMEOUT);
        root.put("database", config.getDatabase());
        root.put("schema", config.getSchema());
        root.put("warehouse", config.getWarehouse());
        root.put("role", config.getRole());

        if (!bindings.isEmpty()) {

            ObjectNode bindingsNode =
                    root.putObject("bindings");

            for (Map.Entry<Integer, List<String>> entry
                    : bindings.entrySet()) {

                ObjectNode binding =
                        bindingsNode.putObject(
                                String.valueOf(
                                        entry.getKey()));

                binding.put("type", "TEXT");

                ArrayNode values =
                        binding.putArray("value");

                for (String value
                        : entry.getValue()) {

                    values.add(value);
                }
            }
        }

        return mapper.writeValueAsString(root);
    }

    private SnowflakeResponse parseResponse(
            String responseBody)
            throws Exception {

        JsonNode root =
                mapper.readTree(responseBody);

        JsonNode stats =
                root.path("stats");

        return new SnowflakeResponse(
                root,
                root.path("code").asText(),
                root.path("message").asText(),
                root.path("statementHandle").asText(),
                root.path("sqlState").asText(),
                stats.path("numRowsInserted").asInt(0),
                stats.path("numRowsUpdated").asInt(0),
                stats.path("numRowsDeleted").asInt(0)
        );
    }
}