package com.bytehonor.sdk.intent.human.listener;

import com.bytehonor.sdk.intent.human.model.IntentResult;

public interface IntentListener {

    public void chain(IntentResult result);

}
