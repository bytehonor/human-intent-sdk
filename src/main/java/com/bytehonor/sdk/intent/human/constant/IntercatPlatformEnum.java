package com.bytehonor.sdk.intent.human.constant;

import com.bytehonor.sdk.lang.spring.string.SpringString;

/**
 * 
 * @author lijianqiang
 *
 */
public enum IntercatPlatformEnum {

    WEIXIN_MP("weixin_mp", "微信公众号"),

    XIAOMI_AIBOX("xiaomi_aibox", "小米智能音箱"),

    XIAOMI_MOBILE("xiaomi_mobile", "小米手机小爱"),;

    private String key;

    private String value;

    private IntercatPlatformEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static IntercatPlatformEnum keyOf(String key) {
        if (SpringString.isEmpty(key)) {
            return null;
        }
        key = key.toLowerCase();
        for (IntercatPlatformEnum item : IntercatPlatformEnum.values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
