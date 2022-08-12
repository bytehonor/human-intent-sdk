package com.bytehonor.sdk.intent.human.resolver;

import com.bytehonor.sdk.intent.human.matcher.IntentMatcher;
import com.bytehonor.sdk.intent.human.model.IntentAnswers;
import com.bytehonor.sdk.intent.human.model.IntentContext;
import com.bytehonor.sdk.intent.human.model.IntentPayload;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class FinishIntentResolver implements IntentResolver {

    private final IntentMatcher matcher;

    public FinishIntentResolver() {
        this.matcher = IntentMatcher.builder("结束对话").include("滚").include("关了").include("关掉").include("关闭")
                .include("关机").include("暂停").include("停止").include("休眠").include("闭嘴").include("吵死").include("不在")
                .include("安静").include("不想", "听").include("再见").include("退出").include("结束").build();
    }

    @Override
    public IntentAnswers answer(IntentPayload payload, IntentSession session, IntentContext context) {
        return IntentAnswers.make().p("好的，赛油娜拉");
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
