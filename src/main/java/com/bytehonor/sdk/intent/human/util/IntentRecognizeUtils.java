package com.bytehonor.sdk.intent.human.util;

import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.intent.human.resolver.IntentResolver;

public class IntentRecognizeUtils {

    public static boolean isAppMatch(String app, IntentResolver recognizer) {
        if (StringKit.isEmpty(app) || recognizer == null) {
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
