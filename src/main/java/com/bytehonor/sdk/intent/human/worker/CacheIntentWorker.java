package com.bytehonor.sdk.intent.human.worker;

import java.util.concurrent.TimeUnit;

import com.bytehonor.sdk.intent.human.model.IntentSession;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheIntentWorker implements IntentWorker {

    private static int CAPACITY = 1024;

    private static Cache<String, IntentSession> CACHE = CacheBuilder.newBuilder().initialCapacity(CAPACITY) // 设置初始容量为100
            .maximumSize(1024 * CAPACITY) // 设置缓存的最大容量
            .expireAfterWrite(4, TimeUnit.HOURS) // 设置缓存在写入一分钟后失效
            .concurrencyLevel(20) // 设置并发级别为10
            .build(); // .recordStats() // 开启缓存统计

    @Override
    public IntentSession get(String uuid) {
        return CACHE.getIfPresent(uuid);
    }

    @Override
    public void put(IntentSession session) {
        CACHE.put(session.getUuid(), session);
    }

}
