package com.bytehonor.sdk.intent.human.util;

import java.util.Objects;

import com.bytehonor.sdk.intent.human.constant.NewsChannelEnum;
import com.bytehonor.sdk.intent.human.constant.PageConstants;
import com.bytehonor.sdk.intent.human.model.IntentSession;

public class NewsIntentSessionUtils {

    public static IntentSession init(String uuid, String intent) {
        Objects.requireNonNull(uuid, "uuid");
        Objects.requireNonNull(intent, "intent");

        IntentSession session = new IntentSession();
        session.setPage(PageConstants.PAGE_FIRST);
        session.setNewsChannel(NewsChannelEnum.TODAY.getKey());
        session.setUuid(uuid);
        session.setIntent(intent);
        session.setNewsIndex(0);
        return session;
    }
}
