package com.bytehonor.sdk.api.tuling.model.request;

/**
 * <pre>
 * 参数   类型  是否必须    取值范围    说明
 * reqType int N   -   输入类型:0-文本(默认)、1-图片、2-音频
 * perception  -   Y   -   输入信息
 * userInfo    -   Y   -   用户参数
 * 
 * 
 * {
 *   "reqType":0,
 *   "perception": {
 *       "inputText": {
 *           "text": "附近的酒店"
 *       },
 *       "inputImage": {
 *           "url": "imageUrl"
 *       },
 *       "selfInfo": {
 *           "location": {
 *               "city": "北京",
 *               "province": "北京",
 *               "street": "信息路"
 *           }
 *       }
 *   },
 *   "userInfo": {
 *       "apiKey": "",
 *       "userId": ""
 *   }
 * }
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class TulingRequest {

    private Integer reqType;

    private TulingPerception perception;

    private TulingUserInfo userInfo;

    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

    public TulingPerception getPerception() {
        return perception;
    }

    public void setPerception(TulingPerception perception) {
        this.perception = perception;
    }

    public TulingUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TulingUserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
