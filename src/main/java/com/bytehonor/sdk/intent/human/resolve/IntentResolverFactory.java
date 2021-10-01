package com.bytehonor.sdk.intent.human.resolve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class IntentResolverFactory {

    private static Map<String, IntentResolver> MAP = new HashMap<String, IntentResolver>();

    public static void put(IntentResolver handler) {
        Objects.requireNonNull(handler, "handler");
        Objects.requireNonNull(handler.intent(), "intent");

        if (MAP.get(handler.intent()) != null) {
            throw new RuntimeException("IntentEventHandler exists! " + handler.intent());
        }
        MAP.put(handler.intent(), handler);
    }

    public static IntentResolver optional(String intent) {
        Objects.requireNonNull(intent, "intent");
        return MAP.get(intent);
    }

    public static List<IntentResolver> list() {
        List<IntentResolver> list = new ArrayList<IntentResolver>();
        for (Entry<String, IntentResolver> item : MAP.entrySet()) {
            list.add(item.getValue());
        }
        return list;
    }
}
