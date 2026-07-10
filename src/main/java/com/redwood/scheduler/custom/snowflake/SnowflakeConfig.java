package com.redwood.scheduler.custom.snowflake;

public class SnowflakeConfig {

    private String endpoint;

    private String database;
    private String schema;
    private String warehouse;
    private String role;

    private String snowflakeCredential;
    private String privateKeyCredential;

    public SnowflakeConfig() {
    }

    public SnowflakeConfig(
            String endpoint,
            String database,
            String schema,
            String warehouse,
            String role,
            String snowflakeCredential,
            String privateKeyCredential) {

        this.endpoint = endpoint;
        this.database = database;
        this.schema = schema;
        this.warehouse = warehouse;
        this.role = role;
        this.snowflakeCredential = snowflakeCredential;
        this.privateKeyCredential = privateKeyCredential;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(
            String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(
            String database) {
        this.database = database;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(
            String schema) {
        this.schema = schema;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(
            String warehouse) {
        this.warehouse = warehouse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(
            String role) {
        this.role = role;
    }

    public String getSnowflakeCredential() {
        return snowflakeCredential;
    }

    public void setSnowflakeCredential(
            String snowflakeCredential) {
        this.snowflakeCredential = snowflakeCredential;
    }

    public String getPrivateKeyCredential() {
        return privateKeyCredential;
    }

    public void setPrivateKeyCredential(
            String privateKeyCredential) {
        this.privateKeyCredential = privateKeyCredential;
    }
}