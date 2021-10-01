package com.bytehonor.sdk.api.tuling.model.request;

/**
 * <pre>
 * 参数  类型  是否必须    取值范围    说明
 * apiKey  String  Y   32位 机器人标识
 * userId  String  Y   长度小于等于32位   用户唯一标识
 * groupId String  N   长度小于等于64位   群聊唯一标识
 * userIdName  String  N   长度小于等于64位   群内用户昵称
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class TulingUserInfo {

    private String apiKey;

    private String userId;

    private String groupId;

    private String userIdName;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserIdName() {
        return userIdName;
    }

    public void setUserIdName(String userIdName) {
        this.userIdName = userIdName;
    }

}
