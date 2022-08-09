package com.bytehonor.sdk.intent.human.resolver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class HowOldIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public HowOldIntentResolver() {
        this.matcher = IntentMatcher.builder("你多大").include("你", "多大").include("你", "年龄").build();
    }

    @Override
    public List<IntentAnswer> answer(IntentPayload payload, IntentSession session, IntentContext context) {
        int year = LocalDate.now().getYear();
        StringBuilder sb = new StringBuilder();
        sb.append("我今年").append(year - 2018).append("岁");
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
