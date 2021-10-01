package com.bytehonor.sdk.intent.human.model;

import java.util.Objects;

import com.bytehonor.sdk.define.bytehonor.util.MD5Utils;
import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.constant.NewsChannelEnum;
import com.bytehonor.sdk.intent.human.constant.PageConstants;

public class IntentSession {

    private int id;

    private boolean auto;

    private String uuid;

    private String app;

    private long preTime;

    private String preIntent;

    private String nowIntent;

    private int page;

    private String newsChannel;

    private int newsIndex;

    public static IntentSession init(String uuid, String app) {
        Objects.requireNonNull(uuid, "uuid");

        IntentSession session = new IntentSession();
        session.setUuid(uuid);
        session.setApp(app);
        session.setPage(PageConstants.PAGE_FIRST);
        session.setNewsChannel(NewsChannelEnum.TODAY.getKey());
        session.setPreIntent("");
        session.setNowIntent(IntentConstants.PUBLIC_DEFAULT);
        session.setNewsIndex(0);
        return session;
    }

    public IntentSession() {
        this.id = 0;
        this.auto = true;
        this.preTime = 0L;
        this.preIntent = "";
        this.nowIntent = IntentConstants.PUBLIC_DEFAULT;
        this.newsIndex = 0;
        this.page = PageConstants.PAGE_FIRST;
    }

    public String toCacheKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(app).append(":").append(uuid).append(":").append(page).append(":").append(newsChannel);
        return MD5Utils.md5(sb.toString());
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

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getNewsChannel() {
        return newsChannel;
    }

    public void setNewsChannel(String newsChannel) {
        this.newsChannel = newsChannel;
    }

    public int getNewsIndex() {
        return newsIndex;
    }

    public void setNewsIndex(int newsIndex) {
        this.newsIndex = newsIndex;
    }

}
