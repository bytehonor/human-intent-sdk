package com.bytehonor.sdk.api.tuling.client;

import java.util.Map;
import java.util.Objects;

import com.bytehonor.sdk.okhttp.bytehonor.client.BytehonorOkHttpClient;

public class TulingOkHttpClient {

    /**
     * 不传递参数的get请求。
     * 
     * @param url
     * @return @
     */
    public static String get(String url) {
        return get(url, null, null);
    }

    /**
     * 传递参数的get请求。
     * 
     * @param url
     * @param paramsMap
     * @return @
     */
    public static String get(String url, Map<String, String> paramsMap) {
        return get(url, paramsMap, null);
    }

    /**
     * 传递参数，且传递请求头的get请求。
     * 
     * @param url
     * @param paramsMap
     * @param headerMap
     * @return @
     */
    public static String get(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        Objects.requireNonNull(url, "url");
        return BytehonorOkHttpClient.get(url, paramsMap, headerMap);
    }

    /**
     * 传递参数的postForm请求。
     * 
     * @param url
     * @param paramsMap
     * @return @
     */
    public static String postForm(String url, Map<String, String> paramsMap) {
        return postForm(url, paramsMap, null);
    }

    /**
     * 传递参数，且传递请求头的postForm请求。
     * 
     * @param url
     * @param paramsMap
     * @param headerMap
     * @return @
     */
    public static String postForm(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
        Objects.requireNonNull(url, "url");
        return BytehonorOkHttpClient.postForm(url, paramsMap, headerMap);
    }

    public static String postJson(String url, String json) {
        return BytehonorOkHttpClient.postJson(url, json);
    }

}
