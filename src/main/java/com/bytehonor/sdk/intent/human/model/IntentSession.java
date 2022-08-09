package com.bytehonor.sdk.intent.human.model;

import java.io.Serializable;
import java.util.Objects;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;

public class IntentSession implements Serializable {

    private static final long serialVersionUID = -1460407043271707300L;

    private int id;

    private boolean auto;

    private String uuid;

    private long preTime;

    private String preIntent;

    private String nowIntent;

    public static IntentSession init(String uuid) {
        Objects.requireNonNull(uuid, "uuid");

        IntentSession session = new IntentSession();
        session.setUuid(uuid);
        session.setPreIntent("");
        session.setNowIntent(IntentConstants.PUBLIC_DEFAULT);
        return session;
    }

    public IntentSession() {
        this.id = 0;
        this.auto = true;
        this.preTime = 0L;
        this.preIntent = "";
        this.nowIntent = IntentConstants.PUBLIC_DEFAULT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public long getPreTime() {
        return preTime;
    }

    public void setPreTime(long preTime) {
        this.preTime = preTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
}
