package com.bytehonor.sdk.intent.human.recognize;

import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentTarget;

public interface IntentRecognizer {
    
    public String app();

    public String intent();
    
    public String pattern();

    public IntentTarget recoginze(IntentRequest request);
}
