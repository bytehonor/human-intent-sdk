package com.bytehonor.sdk.intent.human.resolver;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentResult;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class WhatCanDoIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public WhatCanDoIntentResolver() {
        this.matcher = IntentMatcher.builder("你会什么").include("你能", "做什么").include("你会", "什么").include("你有", "什么", "功能")
                .build();
    }

    @Override
    public IntentResult answer(IntentPayload payload, IntentSession session, IntentContext context) {
        List<IntentResolver> recognizers = context.getPool().all();
        List<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        answers.add(IntentAnswer.text("你可以对我说："));
        for (IntentResolver recognizer : recognizers) {
            answers.add(IntentAnswer.text(recognizer.matcher().getPattern()));
        }
        return IntentResult.of(answers);
    }

    @Override
    public IntentMatcher matcher() {
        return matcher;
    }

}
