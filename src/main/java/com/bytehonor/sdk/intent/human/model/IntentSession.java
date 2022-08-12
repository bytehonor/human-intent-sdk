package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;
import java.util.Objects;

public class IntentSession implements Serializable {

    private static final long serialVersionUID = -1460407043271707300L;

    private int id;

    private boolean paused;

    private String uuid;

    private String app;

    private String platform;

    private String preIntent;

    private String nowIntent;

    private long time;

    public static IntentSession init(String uuid, String app, String platform) {
        Objects.requireNonNull(uuid, "uuid");

        IntentSession session = new IntentSession();
        session.setUuid(uuid);
        session.setApp(app);
        session.setPlatform(platform);
        return session;
    }

    public IntentSession() {
        this.id = 0;
        this.paused = false;
        this.time = 0L;
        this.preIntent = "";
        this.nowIntent = "";
        this.app = "";
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

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
