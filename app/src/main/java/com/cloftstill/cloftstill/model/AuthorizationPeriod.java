package com.cloftstill.cloftstill.model;

/**
 * Created by dcblera on 29/06/16.
 */
public class AuthorizationPeriod {
    private String startDate;
    private String endDate;

    public AuthorizationPeriod() {}

    public void setStartDate(String start) {
        startDate = start;
    }

    public void setEndDate(String end) {
        endDate = end;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
