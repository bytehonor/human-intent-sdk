package com.bytehonor.sdk.intent.human.recognize;

import java.util.Set;

import com.bytehonor.sdk.intent.human.model.IntentRequest;
import com.bytehonor.sdk.intent.human.model.IntentTarget;

public interface IntentRecognizer {

    public Set<String> apps();

    public String intent();

    public String pattern();

    public IntentTarget recoginze(IntentRequest request);
}
