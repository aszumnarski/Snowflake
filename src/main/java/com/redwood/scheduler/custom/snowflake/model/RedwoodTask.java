package com.redwood.scheduler.custom.snowflake.model;

public class RedwoodTask {

    private String owner;
    private String assignee;
    private String description;
    private String deadline;
    private String accountNumber;
    private String reviewNotificationId;
    private String companyCode;
    private String start;
    private String end;
    private String status;
    private String jobId;
    private String catalogPathCurrentPeriod;
    private String delegationHistory;
    private String filename;

    public RedwoodTask() {
    }

    public RedwoodTask(
            String owner,
            String assignee,
            String description,
            String deadline,
            String accountNumber,
            String reviewNotificationId,
            String companyCode,
            String start,
            String end,
            String status,
            String jobId,
            String catalogPathCurrentPeriod,
            String delegationHistory,
            String filename) {

        this.owner = owner;
        this.assignee = assignee;
        this.description = description;
        this.deadline = deadline;
        this.accountNumber = accountNumber;
        this.reviewNotificationId = reviewNotificationId;
        this.companyCode = companyCode;
        this.start = start;
        this.end = end;
        this.status = status;
        this.jobId = jobId;
        this.catalogPathCurrentPeriod = catalogPathCurrentPeriod;
        this.delegationHistory = delegationHistory;
        this.filename = filename;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getReviewNotificationId() {
        return reviewNotificationId;
    }

    public void setReviewNotificationId(String reviewNotificationId) {
        this.reviewNotificationId = reviewNotificationId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCatalogPathCurrentPeriod() {
        return catalogPathCurrentPeriod;
    }

    public void setCatalogPathCurrentPeriod(String catalogPathCurrentPeriod) {
        this.catalogPathCurrentPeriod = catalogPathCurrentPeriod;
    }

    public String getDelegationHistory() {
        return delegationHistory;
    }

    public void setDelegationHistory(String delegationHistory) {
        this.delegationHistory = delegationHistory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "RedwoodTask{" +
                "owner='" + owner + '\'' +
                ", assignee='" + assignee + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", reviewNotificationId='" + reviewNotificationId + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", status='" + status + '\'' +
                ", jobId='" + jobId + '\'' +
                ", catalogPathCurrentPeriod='" + catalogPathCurrentPeriod + '\'' +
                ", delegationHistory='" + delegationHistory + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}