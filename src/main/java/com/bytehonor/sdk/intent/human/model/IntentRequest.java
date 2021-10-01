package com.bytehonor.sdk.intent.human.model;

import java.util.HashSet;
import java.util.Set;

public class IntentRequest {

    private long ts;

    private String query;

    private String uuid;

    private String app;

    private Set<String> words;

    private IntentSession session;

    public IntentRequest() {
        this.ts = System.currentTimeMillis();
        this.session = new IntentSession();
    }

    public static IntentRequest of(String query, String uuid, String app) {
        IntentRequest request = new IntentRequest();
        request.setQuery(query);
        request.setUuid(uuid);
        request.setApp(app);
        request.setWords(new HashSet<String>());
        request.getSession().setUuid(uuid);
        return request;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Set<String> getWords() {
        return words;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

    public IntentSession getSession() {
        return session;
    }

    public void setSession(IntentSession session) {
        this.session = session;
    }

}
