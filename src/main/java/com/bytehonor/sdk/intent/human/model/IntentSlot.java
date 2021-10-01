package com.bytehonor.sdk.intent.human.model;

/**
 * <pre>
 * 参数名  描述  类型  是否必须
 * key    槽位名称    String  是
 * value   提取出的槽位值，例如city槽位可能提取的值是北京   String  否
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class IntentSlot {

    private String key;

    private String value;

    public IntentSlot(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public IntentSlot() {
        this(null, null);
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
