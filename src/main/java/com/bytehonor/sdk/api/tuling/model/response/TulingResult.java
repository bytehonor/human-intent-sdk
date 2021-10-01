package com.bytehonor.sdk.api.tuling.model.response;

import java.util.Map;

/**
 * <pre>
 * 参数   类型  是否包含    取值范围    说明
 * resultType  String  Y   文本(text);连接(url);音频(voice);视频(video);图片(image);图文(news) 输出类型
 * values  -   Y   -   输出值
 * groupType   int Y   -   ‘组’编号:0为独立输出，大于0时可能包含同组相关内容 (如：音频与文本为一组时说明内容一致)
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class TulingResult {
    private String resultType;

    private Map<String, String> values;

    private Integer groupType;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

}
