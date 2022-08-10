package com.bytehonor.sdk.intent.human.resolver;

import java.time.LocalDate;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class AskAgeIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public AskAgeIntentResolver() {
        this.matcher = IntentMatcher.builder("你多大").include("你", "多大").include("你", "年龄").build();
    }

    @Override
    public String answer(IntentPayload payload, IntentSession session, IntentContext context) {
        int year = LocalDate.now().getYear();
        StringBuilder sb = new StringBuilder();
        sb.append("我今年").append(year - 2018).append("岁");
        return sb.toString();
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
