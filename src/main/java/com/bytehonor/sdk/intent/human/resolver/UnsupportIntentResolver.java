package com.bytehonor.sdk.intent.human.resolver;

import java.util.ArrayList;
import java.util.List;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswer;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;
import com.bytehonor.sdk.lang.spring.core.Randomizer;
import com.google.common.collect.Lists;

public class UnsupportIntentResolver implements IntentResolver {

    private static final List<String> LIST = Lists.newArrayList("呵", "嗯", "哦", "咦");
    private final IntentMatcher matcher;

    public UnsupportIntentResolver() {
        this.matcher = IntentMatcher.builder("收到不支持的消息类型，暂无法显示").include("收到", "不支持", "消息类型").build();
    }

    @Override
    public List<IntentAnswer> answer(IntentPayload payload, IntentSession session, IntentContext context) {
        List<IntentAnswer> answers = new ArrayList<IntentAnswer>();
        int index = Randomizer.integer(0, 100) % LIST.size();
        answers.add(IntentAnswer.text(LIST.get(index)));
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
