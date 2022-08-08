package com.bytehonor.sdk.intent.human.model;

public class IntentRequest {

    private String query;

    private String uuid;

    private String app;

    public IntentRequest() {
    }

    public static IntentRequest create(String query, String uuid, String app) {
        IntentRequest request = new IntentRequest();
        request.setQuery(query);
        request.setUuid(uuid);
        request.setApp(app);
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

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

}
