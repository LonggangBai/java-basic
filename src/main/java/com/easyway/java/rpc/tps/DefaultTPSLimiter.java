package com.easyway.java.rpc.tps;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultTPSLimiter implements TPSLimiter {

    private String  TPS_LIMIT_RATE_KEY="tps_limit_rate_key";
    private String  TPS_LIMIT_INTERVAL_KEY="tps_limit_interval_key";
    private String  TPS_LIMIT_SERVICE_KEY="tps_limit_service_key";
    private String  DEFAULT_TPS_LIMIT_INTERVAL="60";
    private final ConcurrentMap<String, StatItem> stats
            = new ConcurrentHashMap<String, StatItem>();
    @Override
    public boolean isAllowable(Map<String,String> param) {
        int rate =Integer.valueOf(StringUtils.defaultIfEmpty(param.get(TPS_LIMIT_RATE_KEY),"-1"));
        long interval = Integer.valueOf(StringUtils.defaultIfEmpty(param.get(TPS_LIMIT_INTERVAL_KEY),
                DEFAULT_TPS_LIMIT_INTERVAL));
        String serviceKey = param.get(TPS_LIMIT_SERVICE_KEY);
        if (rate > 0) {
            StatItem statItem = stats.get(serviceKey);
            if (statItem == null) {
                stats.putIfAbsent(serviceKey,
                        new StatItem(serviceKey, rate, interval));
                statItem = stats.get(serviceKey);
            }
            return statItem.isAllowable();
        } else {
            StatItem statItem = stats.get(serviceKey);
            if (statItem != null) {
                stats.remove(serviceKey);
            }
        }

        return true;
    }
}
