package com.bytehonor.sdk.intent.human.filter;

import com.bytehonor.sdk.intent.human.model.IntentResult;

public interface IntentResultFilter {

    public void chain(IntentResult result);

}
