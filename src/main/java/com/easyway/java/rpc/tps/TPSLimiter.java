package com.easyway.java.rpc.tps;

import java.util.Map;

public interface TPSLimiter {

    /**
     * 根据 tps 限流规则判断是否限制此次调用.
     *
     * @return true 则允许调用，否则不允许
     */
    boolean isAllowable(Map<String,String> param);

}
