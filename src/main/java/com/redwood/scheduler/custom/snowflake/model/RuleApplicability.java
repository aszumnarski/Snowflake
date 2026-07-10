package com.redwood.scheduler.custom.snowflake.model;

public class RuleApplicability {
    private String source;
    private String system;
    private String client;
    private String bukrs;
    private String accountItem;
    private String responsibilityGroup;
    private String konto;
    private String filename;

    public RuleApplicability() {
    }

    public RuleApplicability(
            String source,
            String system,
            String client,
            String bukrs,
            String accountItem,
            String responsibilityGroup,
            String konto,
            String filename) {

        this.source = source;
        this.system = system;
        this.client = client;
        this.bukrs = bukrs;
        this.accountItem = accountItem;
        this.responsibilityGroup = responsibilityGroup;
        this.konto = konto;
        this.filename = filename;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getAccountItem() {
        return accountItem;
    }

    public void setAccountItem(String accountItem) {
        this.accountItem = accountItem;
    }

    public String getResponsibilityGroup() {
        return responsibilityGroup;
    }

    public void setResponsibilityGroup(String responsibilityGroup) {
        this.responsibilityGroup = responsibilityGroup;
    }

    public String getKonto() {
        return konto;
    }

    public void setKonto(String konto) {
        this.konto = konto;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "RuleApplicability{" +
                "source='" + source + '\'' +
                ", system='" + system + '\'' +
                ", client='" + client + '\'' +
                ", bukrs='" + bukrs + '\'' +
                ", accountItem='" + accountItem + '\'' +
                ", responsibilityGroup='" + responsibilityGroup + '\'' +
                ", konto='" + konto + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
