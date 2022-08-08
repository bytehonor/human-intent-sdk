package com.bytehonor.sdk.intent.human.util;

import com.bytehonor.sdk.intent.human.recognize.IntentRecognizer;
import com.bytehonor.sdk.lang.spring.string.SpringString;

public class IntentRecognizeUtils {

    public static boolean isAppMatch(String app, IntentRecognizer recognizer) {
        if (SpringString.isEmpty(app) || recognizer == null) {
            return false;
        }
//        if (CollectionUtils.isEmpty(recognizer.apps())) {
//            return false;
//        }
//        if (recognizer.apps().contains(IntentConstants.ANY) == false && recognizer.apps().contains(app) == false) {
//            return false;
//        }
        return true;
    }
}
