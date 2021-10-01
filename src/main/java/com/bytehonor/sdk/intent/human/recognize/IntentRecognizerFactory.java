package com.bytehonor.sdk.intent.human.recognize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class IntentRecognizerFactory {

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

    public static List<IntentRecognizer> list() {
        List<IntentRecognizer> list = new ArrayList<IntentRecognizer>();
        for (Entry<String, IntentRecognizer> item : MAP.entrySet()) {
            list.add(item.getValue());
        }
        return list;
    }
}
