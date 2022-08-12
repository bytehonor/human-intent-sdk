package com.bytehonor.sdk.intent.human.resolver;

import java.util.Set;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class AskAbilityIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public AskAbilityIntentResolver() {
        this.matcher = IntentMatcher.builder("你会什么").include("你能", "做什么").include("你会", "什么").include("你有", "什么", "功能")
                .build();
    }

    @Override
    public IntentAnswer answer(IntentPayload payload, IntentSession session, IntentContext context) {
        Set<String> patterns = context.getPublics();
        IntentAnswer answer = IntentAnswer.make();
        answer.p("你可以对我说：");
        answer.p("");
        for (String pattern : patterns) {
            answer.p(pattern);
        }
        return answer;
    }

    @Override
    public IntentMatcher matcher() {
        return matcher;
    }

    @Override
    public boolean privated() {
        return true;
    }

}
