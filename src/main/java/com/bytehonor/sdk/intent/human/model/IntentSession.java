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

    private String preIntent;

    private String nowIntent;

    private Integer page;

    private String newsChannel;

    private Integer newsIndex;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getNewsChannel() {
        return newsChannel;
    }

    public void setNewsChannel(String newsChannel) {
        this.newsChannel = newsChannel;
    }

    public Integer getNewsIndex() {
        return newsIndex;
    }

    public void setNewsIndex(Integer newsIndex) {
        this.newsIndex = newsIndex;
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

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getNowIntent() {
        return nowIntent;
    }

    public void setNowIntent(String nowIntent) {
        this.nowIntent = nowIntent;
    }

    public String toCacheKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(app).append(":").append(uuid).append(":").append(page).append(":").append(newsChannel);
        return MD5Utils.md5(sb.toString());
    }

}
