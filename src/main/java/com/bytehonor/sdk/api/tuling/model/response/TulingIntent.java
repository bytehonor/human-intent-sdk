package com.bytehonor.sdk.api.tuling.model.response;

/**
 * <pre>
 * 参数   类型  是否包含    取值范围    说明
 * code    int Y   -   输出功能code
 * intentName  String  N   -   意图名称
 * actionName  String  N   -   意图动作名称
 * parameters  Map N   -   功能相关参数
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class TulingIntent {

    private Integer code;

    private String intentName;

    private String actionName;

//    private Map<String, String> parameters;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

}
