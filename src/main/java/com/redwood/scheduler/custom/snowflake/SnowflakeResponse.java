package com.redwood.scheduler.custom.snowflake;

import com.fasterxml.jackson.databind.JsonNode;

public class SnowflakeResponse {

    private final JsonNode root;

    private final String code;
    private final String message;
    private final String statementHandle;
    private final String sqlState;

    private final int insertedRows;
    private final int updatedRows;
    private final int deletedRows;

    public SnowflakeResponse(
            JsonNode root,
            String code,
            String message,
            String statementHandle,
            String sqlState,
            int insertedRows,
            int updatedRows,
            int deletedRows) {

        this.root = root;
        this.code = code;
        this.message = message;
        this.statementHandle = statementHandle;
        this.sqlState = sqlState;
        this.insertedRows = insertedRows;
        this.updatedRows = updatedRows;
        this.deletedRows = deletedRows;
    }

    public boolean isSuccess() {
        return "090001".equals(code);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getInsertedRows() {
        return insertedRows;
    }

    public int getUpdatedRows() {
        return updatedRows;
    }

    public int getDeletedRows() {
        return deletedRows;
    }

    public JsonNode getData() {
        return root.path("data");
    }

    public JsonNode getRoot() {
        return root;
    }

    public String getStatementHandle() {
        return statementHandle;
    }

    public String getSqlState() {
        return sqlState;
    }
}