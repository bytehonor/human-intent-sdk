package com.bytehonor.sdk.api.tuling.model.response;

import java.util.List;

/**
 * <pre>
 * {
 * "intent": {
 *    "code": 10005,
 *    "intentName": "",
 *    "actionName": "",
 *    "parameters": {
 *        "nearby_place": "酒店"
 *    }
 * },
 * "results": [
 *    {
 *        "groupType": 1,
 *        "resultType": "url",
 *        "values": {
 *            "url": "http://m.elong.com/hotel/0101/nlist/#indate=2016-12-10&outdate=2016-12-11&keywords=%E4%BF%A1%E6%81%AF%E8%B7%AF"
 *        }
 *    },
 *    {
 *        "groupType": 1,
 *        "resultType": "text",
 *        "values": {
 *            "text": "亲，已帮你找到相关酒店信息"
 *        }
 *    }
 * ]
 * }
 * </pre>
 * 
 * @author lijianqiang
 *
 */
public class TulingResponse {

    private TulingIntent intent;

    private List<TulingResult> results;

    public TulingIntent getIntent() {
        return intent;
    }

    public void setIntent(TulingIntent intent) {
        this.intent = intent;
    }

    public List<TulingResult> getResults() {
        return results;
    }

    public void setResults(List<TulingResult> results) {
        this.results = results;
    }

}
