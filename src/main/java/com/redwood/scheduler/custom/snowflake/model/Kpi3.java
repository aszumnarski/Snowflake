package com.redwood.scheduler.custom.snowflake.model;

public class Kpi3 {

    private String period;
    private String companyCode;
    private String type;
    private String totalOpenItems;
    private String proposed;
    private String cleared;
    private String errors;
    private String accountItems;
    private String filename;

    public Kpi3() {
    }

    public Kpi3(
            String period,
            String companyCode,
            String type,
            String totalOpenItems,
            String proposed,
            String cleared,
            String errors,
            String accountItems,
            String filename) {

        this.period = period;
        this.companyCode = companyCode;
        this.type = type;
        this.totalOpenItems = totalOpenItems;
        this.proposed = proposed;
        this.cleared = cleared;
        this.errors = errors;
        this.accountItems = accountItems;
        this.filename = filename;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalOpenItems() {
        return totalOpenItems;
    }

    public void setTotalOpenItems(String totalOpenItems) {
        this.totalOpenItems = totalOpenItems;
    }

    public String getProposed() {
        return proposed;
    }

    public void setProposed(String proposed) {
        this.proposed = proposed;
    }

    public String getCleared() {
        return cleared;
    }

    public void setCleared(String cleared) {
        this.cleared = cleared;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getAccountItems() {
        return accountItems;
    }

    public void setAccountItems(String accountItems) {
        this.accountItems = accountItems;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Kpi3{" +
                "period='" + period + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", type='" + type + '\'' +
                ", totalOpenItems='" + totalOpenItems + '\'' +
                ", proposed='" + proposed + '\'' +
                ", cleared='" + cleared + '\'' +
                ", errors='" + errors + '\'' +
                ", accountItems='" + accountItems + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}