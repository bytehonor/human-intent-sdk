package com.bytehonor.sdk.intent.human.resolve;

import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentTarget;

public interface IntentResolver {

    public String intent();
    
    public IntentResult resolve(IntentTarget target);
}
