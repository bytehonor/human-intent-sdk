package com.bytehonor.sdk.intent.human.resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.intent.human.util.IntentRecognizeUtils;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public class IntentRecognizerFactory {

    private static final Logger LOG = LoggerFactory.getLogger(IntentRecognizerFactory.class);

    private static Map<String, IntentResolver> INTENTS = new HashMap<String, IntentResolver>();

    private static Map<String, List<IntentResolver>> APPS = new ConcurrentHashMap<String, List<IntentResolver>>();

    public static void put(IntentResolver recognizer) {
        Objects.requireNonNull(recognizer, "recognizer");
        Objects.requireNonNull(recognizer.intent(), "intent");

        if (INTENTS.get(recognizer.intent()) != null) {
            throw new RuntimeException("IntentRecognizer exists! " + recognizer.intent());
        }
        INTENTS.put(recognizer.intent(), recognizer);
    }

    public static IntentResolver optional(String intent) {
        Objects.requireNonNull(intent, "intent");
        return INTENTS.get(intent);
    }

    public static List<IntentResolver> list(String app) {
        if (SpringString.isEmpty(app)) {
            LOG.warn("list recognizer of app is empty!");
            return new ArrayList<IntentResolver>();
        }
        List<IntentResolver> list = APPS.get(app);
        if (CollectionUtils.isEmpty(list) == false) {
            return list;
        }
        list = new ArrayList<IntentResolver>();
        for (Entry<String, IntentResolver> item : INTENTS.entrySet()) {
            if (IntentRecognizeUtils.isAppMatch(app, item.getValue()) == false) {
                continue;
            }
            list.add(item.getValue());
        }
        APPS.put(app, list);
        return list;
    }
}
