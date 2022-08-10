package com.bytehonor.sdk.intent.human.resolver;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class AskNameIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public AskNameIntentResolver() {
        this.matcher = IntentMatcher.builder("你是谁").include("你", "是谁").include("你", "名字").include("你叫", "什么").build();
    }

    @Override
    public List<IntentAnswer> answer(IntentPayload payload, IntentSession session, IntentContext context) {
        boolean contains = payload.getWords().contains("叫") || payload.getWords().contains("名字");
        StringBuilder sb = new StringBuilder();
        sb.append("我").append(contains ? "叫" : "是").append(context.getName());
        List<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        answers.add(IntentAnswer.text(sb.toString()));
        return answers;
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
