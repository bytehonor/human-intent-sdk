package com.bytehonor.sdk.intent.human.model;

public class IntentRequest {

    private String query;

    private String uuid;

    public static IntentRequest create(String query, String uuid) {
        IntentRequest request = new IntentRequest();
        request.setQuery(query);
        request.setUuid(uuid);
        return request;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
