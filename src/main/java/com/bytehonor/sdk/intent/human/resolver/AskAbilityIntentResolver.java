package com.bytehonor.sdk.intent.human.resolver;

import java.util.List;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswers;
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
    public IntentAnswers answer(IntentPayload payload, IntentSession session, IntentContext context) {
        List<IntentResolver> resolvers = context.getResolvers();
        IntentAnswers answers = IntentAnswers.make();
        answers.title("你可以对我说：");
        for (IntentResolver resolver : resolvers) {
            if (resolver.privated()) {
                continue;
            }
            answers.p(resolver.matcher().getPattern());
        }
        return answers;
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
