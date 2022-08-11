package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswers;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class AskNameIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public AskNameIntentResolver() {
        this.matcher = IntentMatcher.builder("你是谁").include("你", "是谁").include("你", "名字").include("你叫", "什么").build();
    }

    @Override
    public IntentAnswers answer(IntentPayload payload, IntentSession session, IntentContext context) {
        boolean contains = payload.getWords().contains("叫") || payload.getWords().contains("名字");
        StringBuilder sb = new StringBuilder();
        sb.append("我").append(contains ? "叫" : "是").append(context.getName());
        return IntentAnswers.make().p(sb.toString());
    }

    @Override
    public IntentMatcher matcher() {
        return matcher;
    }

    @Override
    public boolean privated() {
        return false;
    }

}
