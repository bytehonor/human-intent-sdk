package com.bytehonor.sdk.intent.human.util;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.intent.human.constant.IntentConstants;
import com.bytehonor.sdk.intent.human.recognize.IntentRecognizer;
import com.bytehonor.sdk.lang.spring.util.StringObject;

public class IntentRecognizeUtils {

    public static boolean isAppMatch(String app, IntentRecognizer recognizer) {
        if (StringObject.isEmpty(app) || recognizer == null) {
            return false;
        }
        if (CollectionUtils.isEmpty(recognizer.apps())) {
            return false;
        }
        if (recognizer.apps().contains(IntentConstants.ANY) == false && recognizer.apps().contains(app) == false) {
            return false;
        }
        return true;
    }
}
