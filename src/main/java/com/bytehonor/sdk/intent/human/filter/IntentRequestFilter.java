package com.bytehonor.sdk.intent.human.filter;

import com.bytehonor.sdk.intent.human.model.IntentRequest;

public interface IntentRequestFilter {

    public void chain(IntentRequest request);

}
