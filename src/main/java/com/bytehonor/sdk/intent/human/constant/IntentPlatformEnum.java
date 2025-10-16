package com.bytehonor.sdk.intent.human.constant;

import com.bytehonor.sdk.framework.lang.string.StringKit;

/**
 * 
 * @author lijianqiang
 *
 */
public enum IntentPlatformEnum {

    WEIXIN_MP("weixin_mp", "微信公众号"),

    XIAOMI_AIBOX("xiaomi_aibox", "小米智能音箱"),

    XIAOMI_MOBILE("xiaomi_mobile", "小米手机小爱"),

    UNDEFINED("undefined", "未定义"),

    ;

    private String key;

    private String value;

    private IntentPlatformEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static IntentPlatformEnum keyOf(String key) {
        if (StringKit.isEmpty(key)) {
            return UNDEFINED;
        }
        key = key.toLowerCase();
        for (IntentPlatformEnum item : IntentPlatformEnum.values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return UNDEFINED;
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
