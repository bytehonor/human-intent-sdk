package com.bytehonor.sdk.intent.human.model;

import java.util.Objects;

import com.bytehonor.sdk.intent.human.constant.NewsChannelEnum;
import com.bytehonor.sdk.intent.human.constant.PageConstants;
import com.bytehonor.sdk.lang.spring.util.MD5Utils;

public class NewsIntentSession {

    private int id;

    private String uuid;

    private int page;

    private String newsChannel;

    private int newsIndex;

    public static NewsIntentSession init(String uuid, String app) {
        Objects.requireNonNull(uuid, "uuid");

        NewsIntentSession session = new NewsIntentSession();
        session.setUuid(uuid);
        session.setPage(PageConstants.PAGE_FIRST);
        session.setNewsChannel(NewsChannelEnum.TODAY.getKey());
        session.setNewsIndex(0);
        return session;
    }

    public NewsIntentSession() {
        this.id = 0;
        this.newsIndex = 0;
        this.page = PageConstants.PAGE_FIRST;
    }

    public String toCacheKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(uuid).append(":").append(page).append(":").append(newsChannel);
        return MD5Utils.md5(sb.toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
