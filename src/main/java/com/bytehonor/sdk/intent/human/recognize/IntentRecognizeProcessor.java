package com.bytehonor.sdk.intent.human.recognize;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.intent.human.util.IntentRecognizeUtils;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public class IntentRecognizeProcessor {

    public static List<String> listPatterns(String app, List<String> intents) {
        List<String> list = new ArrayList<String>();
        for (String intent : intents) {
            IntentRecognizer recognizer = IntentRecognizerFactory.optional(intent);
            if (IntentRecognizeUtils.isAppMatch(app, recognizer) == false) {
                continue;
            }
            if (SpringString.isEmpty(recognizer.pattern()) == false) {
                list.add(recognizer.pattern());
            }
        }
        return list;
    }

    public static List<String> listPatterns(String app) {
        List<String> list = new ArrayList<String>();
        List<IntentRecognizer> recognizers = IntentRecognizerFactory.list(app);
        for (IntentRecognizer recognizer : recognizers) {
            if (SpringString.isEmpty(recognizer.pattern()) == false) {
                list.add(recognizer.pattern());
            }
        }
        return list;
    }
}
