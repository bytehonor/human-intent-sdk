package com.bytehonor.sdk.intent.human.recognize;

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

    private static Map<String, IntentRecognizer> INTENTS = new HashMap<String, IntentRecognizer>();

    private static Map<String, List<IntentRecognizer>> APPS = new ConcurrentHashMap<String, List<IntentRecognizer>>();

    public static void put(IntentRecognizer recognizer) {
        Objects.requireNonNull(recognizer, "recognizer");
        Objects.requireNonNull(recognizer.intent(), "intent");

        if (INTENTS.get(recognizer.intent()) != null) {
            throw new RuntimeException("IntentRecognizer exists! " + recognizer.intent());
        }
        INTENTS.put(recognizer.intent(), recognizer);
    }

    public static IntentRecognizer optional(String intent) {
        Objects.requireNonNull(intent, "intent");
        return INTENTS.get(intent);
    }

    public static List<IntentRecognizer> list(String app) {
        if (SpringString.isEmpty(app)) {
            LOG.warn("list recognizer of app is empty!");
            return new ArrayList<IntentRecognizer>();
        }
        List<IntentRecognizer> list = APPS.get(app);
        if (CollectionUtils.isEmpty(list) == false) {
            return list;
        }
        list = new ArrayList<IntentRecognizer>();
        for (Entry<String, IntentRecognizer> item : INTENTS.entrySet()) {
            if (IntentRecognizeUtils.isAppMatch(app, item.getValue()) == false) {
                continue;
            }
            list.add(item.getValue());
        }
        APPS.put(app, list);
        return list;
    }
}
