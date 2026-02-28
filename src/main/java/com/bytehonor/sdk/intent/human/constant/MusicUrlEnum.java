package com.bytehonor.sdk.intent.human.constant;

import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.bytehonor.sdk.framework.lang.util.RandomKit;

/**
 * 
 * @author lijianqiang
 *
 */
public enum MusicUrlEnum {

    HOAPROX_MONBET_REMIX("Hoaprox_MonBet_Remix", "https://m.bytehonor.com/res/music/Hoaprox_MonBet_Remix.mp3"),

    FRIENDSHIPS_MIX("FRIENDSHIPS_MIX", "https://m.bytehonor.com/res/music/Friendships_Original_Mix.mp3"),

    PLANET("PLANET", "https://m.bytehonor.com/res/music/PLANET.mp3"),

    HYMN_WEEKEND("HYMN_WEEKEND", "https://m.bytehonor.com/res/music/Hymn_for_the_Weekend_Mix.mp3"),

    JAPAN_GQY("JAPAN_GQY", "https://m.bytehonor.com/res/music/JAPAN_GQY.mp3"),

    JUST_LIKE("JUST_LIKE", "https://m.bytehonor.com/res/music/Just_Like_This_Mix.mp3"),

    MAI_LAJIAO("MAI_LAJIAO", "https://m.bytehonor.com/res/music/MAI_LAJIAO.mp3");

    private String key;

    private String url;

    private MusicUrlEnum(String key, String url) {
        this.key = key;
        this.url = url;
    }

    public static MusicUrlEnum keyOf(String key) {
        if (StringKit.isEmpty(key)) {
            return HOAPROX_MONBET_REMIX;
        }
        for (MusicUrlEnum sc : MusicUrlEnum.values()) {
            if (key.equals(sc.getKey())) {
                return sc;
            }
        }
        return HOAPROX_MONBET_REMIX;
    }

    public static MusicUrlEnum random() {
        return MusicUrlEnum.values()[RandomKit.integer(0, 2)];
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
