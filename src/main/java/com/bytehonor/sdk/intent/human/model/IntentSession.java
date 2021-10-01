package com.bytehonor.sdk.intent.human.model;

import com.bytehonor.sdk.define.bytehonor.util.MD5Utils;

public class IntentSession {

    private int id;

    private boolean auto;

    private String uuid;

    private String app;

    private String intent;

    private Integer page;

    private String newsChannel;

    private Integer newsIndex;

    public IntentSession() {
        this.id = 0;
        this.auto = true;
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

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String toCacheKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(app).append(":").append(uuid).append(":").append(page).append(":").append(newsChannel);
        return MD5Utils.md5(sb.toString());
    }

}
