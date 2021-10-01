package com.bytehonor.sdk.intent.human.recognize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.bytehonor.util.StringObject;
import com.bytehonor.sdk.intent.human.util.IntentRecognizeUtils;

public class IntentRecognizerFactory {

    private static final Logger LOG = LoggerFactory.getLogger(IntentRecognizerFactory.class);

    private static Map<String, IntentRecognizer> MAP = new HashMap<String, IntentRecognizer>();

    public static void put(IntentRecognizer recognizer) {
        Objects.requireNonNull(recognizer, "recognizer");
        Objects.requireNonNull(recognizer.intent(), "intent");

        if (MAP.get(recognizer.intent()) != null) {
            throw new RuntimeException("IntentRecognizer exists! " + recognizer.intent());
        }
        MAP.put(recognizer.intent(), recognizer);
    }

    public static IntentRecognizer optional(String intent) {
        Objects.requireNonNull(intent, "intent");
        return MAP.get(intent);
    }

    public static List<IntentRecognizer> list(String app) {
        List<IntentRecognizer> list = new ArrayList<IntentRecognizer>();
        if (StringObject.isEmpty(app)) {
            LOG.warn("list recognizer of app is empty!");
            return list;
        }
        for (Entry<String, IntentRecognizer> item : MAP.entrySet()) {
            if (IntentRecognizeUtils.isAppMatch(app, item.getValue()) == false) {
                continue;
            }
            list.add(item.getValue());
        }
        return list;
    }
}
