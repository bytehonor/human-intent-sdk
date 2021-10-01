package com.bytehonor.sdk.intent.human.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntentFilterFactory {

    public static List<IntentRequestFilter> REQUESTS = new ArrayList<IntentRequestFilter>();

    public static List<IntentResultFilter> RESULTS = new ArrayList<IntentResultFilter>();

    public static void putRequest(IntentRequestFilter handler) {
        Objects.requireNonNull(handler, "handler");

        REQUESTS.add(handler);
    }

    public static void putResult(IntentResultFilter handler) {
        Objects.requireNonNull(handler, "handler");

        RESULTS.add(handler);
    }

    public static List<IntentRequestFilter> request() {
        return REQUESTS;
    }

    public static List<IntentResultFilter> result() {
        return RESULTS;
    }
}
