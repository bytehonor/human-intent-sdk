package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class WhoIamIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public WhoIamIntentResolver() {
        this.matcher = IntentMatcher.builder("你是谁").include("你是谁").include("你", "名字").include("你叫", "什么").build();
    }

    @Override
    public IntentResult answer(IntentPayload payload, IntentSession session, IntentContext context) {
        boolean contains = payload.getWords().contains("叫") || payload.getWords().contains("名字");
        StringBuilder sb = new StringBuilder();
        sb.append("我").append(contains ? "叫" : "是").append(context.getName());
        return IntentResult.one(sb.toString());
    }

    @Override
    public IntentMatcher matcher() {
        return matcher;
    }

}
