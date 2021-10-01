package com.bytehonor.sdk.intent.human.util;

import java.util.HashMap;
import java.util.Map;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;

public class IntentSlotBuilder {

    private Map<String, String> slots;

    private IntentSlotBuilder() {
        this.slots = new HashMap<String, String>();
    }

    public static IntentSlotBuilder create() {
        return new IntentSlotBuilder();
    }

    public IntentSlotBuilder put(String key, String value) {
        if (StringObject.isEmpty(key) || StringObject.isEmpty(value)) {
            return this;
        }
        this.slots.put(key, value);
        return this;
    }

    public Map<String, String> build() {
        return this.slots;
    }
}
