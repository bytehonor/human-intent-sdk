package com.bytehonor.sdk.intent.human.resolver;

import java.util.List;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
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
    public String answer(IntentPayload payload, IntentSession session, IntentContext context) {
        List<IntentResolver> resolvers = context.getResolvers();
        StringBuilder answers = new StringBuilder();
        answers.append("你可以对我说：");
        for (IntentResolver resolver : resolvers) {
            if (resolver.privated()) {
                continue;
            }
            answers.append(resolver.matcher().getPattern());
        }
        return answers.toString();
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
