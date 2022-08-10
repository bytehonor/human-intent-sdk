package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.chat.ChatClient;
import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class UnsupportIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public UnsupportIntentResolver() {
        this.matcher = IntentMatcher.builder("收到不支持的消息类型，暂无法显示").include("收到", "不支持", "消息类型").build();
    }

    @Override
    public String answer(IntentPayload payload, IntentSession session, IntentContext context) {
        return ChatClient.ok();
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
