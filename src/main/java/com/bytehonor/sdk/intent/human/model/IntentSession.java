package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;
import java.util.Objects;

public class IntentSession implements Serializable {

    private static final long serialVersionUID = -1460407043271707300L;

    private int id;

    private boolean paused;

    private String uuid;

    private String platform;

    private String preIntent;

    private String nowIntent;

    private long lastAt;

    public static IntentSession init(String uuid, String platform) {
        Objects.requireNonNull(uuid, "uuid");

        IntentSession session = new IntentSession();
        session.setUuid(uuid);
        session.setPlatform(platform);
        return session;
    }

    public IntentSession() {
        this.id = 0;
        this.paused = false;
        this.lastAt = 0L;
        this.preIntent = "";
        this.nowIntent = "";
        this.platform = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPreIntent() {
        return preIntent;
    }

    public void setPreIntent(String preIntent) {
        this.preIntent = preIntent;
    }

    public String getNowIntent() {
        return nowIntent;
    }

    public void setNowIntent(String nowIntent) {
        this.nowIntent = nowIntent;
    }

    public long getLastAt() {
        return lastAt;
    }

    public void setLastAt(long lastAt) {
        this.lastAt = lastAt;
    }

}
