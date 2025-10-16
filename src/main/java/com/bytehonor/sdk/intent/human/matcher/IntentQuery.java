package com.bytehonor.sdk.intent.human.matcher;

import com.bytehonor.sdk.framework.lang.constant.CharConstants;
import com.bytehonor.sdk.framework.lang.regex.PatternKit;
import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.string.StringRemoveUtils;

public class IntentQuery {

    /**
     * 只保留 英文,汉字,数字,空格 4种. 非中文之间插入空格
     * 
     * @param text
     * @return
     */
    public static String prepare(String text) {
        text = StringRemoveUtils.removeHttp(text);
        text = StringRemoveUtils.replaceNonNormalWithBlank(text);
        if (StringKit.isEmpty(text)) {
            return "";
        }
        int length = text.length();
        char[] source = text.toCharArray();
        char[] target = new char[length * 2];
        int at = 0;
        for (int i = 0; i < length; i++) {
            if (PatternKit.isNormalChar(source[i])) {
                if (i > 1 && blank(source[i - 1], source[i])) {
                    // 非中文之间补个空格
                    target[at++] = CharConstants.BLANK;
                }
                target[at++] = source[i];
                continue;
            }
            target[at++] = CharConstants.BLANK;
        }
        return new String(target, 0, at);
    }

    private static boolean blank(char last, char now) {
        if (last == CharConstants.BLANK || now == CharConstants.BLANK) {
            return false;
        }
        if (PatternKit.isChineseChar(last) && PatternKit.isChineseChar(now) == false) {
            return true;
        }
        if (PatternKit.isChineseChar(last) == false && PatternKit.isChineseChar(now)) {
            return true;
        }
        return false;
    }

}
