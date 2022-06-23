package com.bytehonor.sdk.intent.human.constant;

import com.bytehonor.sdk.lang.spring.util.StringObject;

/**
 * 
 * @author lijianqiang
 *
 */
public enum NewsChannelEnum {

    INSTANT("INSTANT", "实时"),

    TODAY("TODAY", "今天"),

    YESTODAY("YESTODAY", "昨天"),

    ;

    private String key;

    private String name;

    private NewsChannelEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public static NewsChannelEnum keyOf(String key) {
        if (StringObject.isEmpty(key)) {
            return TODAY;
        }
        for (NewsChannelEnum item : NewsChannelEnum.values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return TODAY;
    }

    public static NewsChannelEnum nameOf(String name) {
        if (StringObject.isEmpty(name)) {
            return TODAY;
        }
        for (NewsChannelEnum item : NewsChannelEnum.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return TODAY;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
