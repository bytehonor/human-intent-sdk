package com.bytehonor.sdk.intent.human.chat;

import java.util.List;

import com.bytehonor.sdk.api.tuling.TulingApiClient;
import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.string.StringRemoveUtils;
import com.bytehonor.sdk.framework.lang.util.RandomKit;
import com.google.common.collect.Lists;

public class ChatClient {

    private static final List<String> LIST = Lists.newArrayList("呵", "嗯", "哦", "咦");

    private static final String DEF = "不知道怎么回答你了";

    public static String ask(String query, String user) {
        String clear = StringRemoveUtils.removeNonChinese(query);
        if (StringKit.isEmpty(clear)) {
            return ok();
        }
        if (clear.length() > 128) {
            return DEF;
        }
        String answer = TulingApiClient.simpleAsk(query, user).trim();
        if (StringKit.isEmpty(answer) == false) {
            return answer;
        }
        return DEF;
    }

    public static String ok() {
        int index = RandomKit.integer(0, 100) % LIST.size();
        return LIST.get(index);
    }
}
