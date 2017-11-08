package com.easyway.java.rpc.tps;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RpcStatus {

    private static final ConcurrentMap<String, RpcStatus> SERVICE_STATISTICS = new ConcurrentHashMap<String, RpcStatus>();

    private static final ConcurrentMap<String, ConcurrentMap<String, RpcStatus>> METHOD_STATISTICS = new ConcurrentHashMap<String, ConcurrentMap<String, RpcStatus>>();
    private final ConcurrentMap<String, Object> values = new ConcurrentHashMap<String, Object>();
    private final AtomicInteger active = new AtomicInteger();
    private final AtomicLong total = new AtomicLong();
    private final AtomicInteger failed = new AtomicInteger();
    private final AtomicLong totalElapsed = new AtomicLong();
    private final AtomicLong failedElapsed = new AtomicLong();
    private final AtomicLong maxElapsed = new AtomicLong();
    private final AtomicLong failedMaxElapsed = new AtomicLong();
    private final AtomicLong succeededMaxElapsed = new AtomicLong();

    /**
     * 用来实现executes属性的并发限制（即控制能使用的线程数）
     * 2017-08-21 yizhenqiang
     */
    private volatile Semaphore executesLimit;
    private volatile int executesPermits;

    private RpcStatus() {
    }

    /**
     * @param serviceIdentityKey
     * @return status
     */
    public static RpcStatus getStatus(String serviceIdentityKey ) {
        RpcStatus status = SERVICE_STATISTICS.get(serviceIdentityKey);
        if (status == null) {
            SERVICE_STATISTICS.putIfAbsent(serviceIdentityKey, new RpcStatus());
            status = SERVICE_STATISTICS.get(serviceIdentityKey);
        }
        return status;
    }

    /**
     * @param serviceIdentityKey
     */
    public static void removeStatus(String serviceIdentityKey ) {
        SERVICE_STATISTICS.remove(serviceIdentityKey);
    }

    /**
     * @param serviceIdentityKey
     * @param methodName
     * @return status
     */
    public static RpcStatus getStatus(String serviceIdentityKey , String methodName) {
        ConcurrentMap<String, RpcStatus> map = METHOD_STATISTICS.get(serviceIdentityKey);
        if (map == null) {
            METHOD_STATISTICS.putIfAbsent(serviceIdentityKey, new ConcurrentHashMap<String, RpcStatus>());
            map = METHOD_STATISTICS.get(serviceIdentityKey);
        }
        RpcStatus status = map.get(methodName);
        if (status == null) {
            map.putIfAbsent(methodName, new RpcStatus());
            status = map.get(methodName);
        }
        return status;
    }

    /**
     * @param serviceIdentityKey
     */
    public static void removeStatus(String serviceIdentityKey , String methodName) {
        ConcurrentMap<String, RpcStatus> map = METHOD_STATISTICS.get(serviceIdentityKey);
        if (map != null) {
            map.remove(methodName);
        }
    }

    /**
     * @param serviceIdentityKey
     */
    public static void beginCount(String serviceIdentityKey , String methodName) {
        beginCount(getStatus(serviceIdentityKey));
        beginCount(getStatus(serviceIdentityKey, methodName));
    }

    private static void beginCount(RpcStatus status) {
        status.active.incrementAndGet();
    }

    /**
     * @param serviceIdentityKey
     * @param elapsed
     * @param succeeded
     */
    public static void endCount(String serviceIdentityKey , String methodName, long elapsed, boolean succeeded) {
        endCount(getStatus(serviceIdentityKey), elapsed, succeeded);
        endCount(getStatus(serviceIdentityKey, methodName), elapsed, succeeded);
    }

    private static void endCount(RpcStatus status, long elapsed, boolean succeeded) {
        status.active.decrementAndGet();
        status.total.incrementAndGet();
        status.totalElapsed.addAndGet(elapsed);
        if (status.maxElapsed.get() < elapsed) {
            status.maxElapsed.set(elapsed);
        }
        if (succeeded) {
            if (status.succeededMaxElapsed.get() < elapsed) {
                status.succeededMaxElapsed.set(elapsed);
            }
        } else {
            status.failed.incrementAndGet();
            status.failedElapsed.addAndGet(elapsed);
            if (status.failedMaxElapsed.get() < elapsed) {
                status.failedMaxElapsed.set(elapsed);
            }
        }
    }

    /**
     * set value.
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        values.put(key, value);
    }

    /**
     * get value.
     *
     * @param key
     * @return value
     */
    public Object get(String key) {
        return values.get(key);
    }

    /**
     * get active.
     *
     * @return active
     */
    public int getActive() {
        return active.get();
    }

    /**
     * get total.
     *
     * @return total
     */
    public long getTotal() {
        return total.longValue();
    }

    /**
     * get total elapsed.
     *
     * @return total elapsed
     */
    public long getTotalElapsed() {
        return totalElapsed.get();
    }

    /**
     * get average elapsed.
     *
     * @return average elapsed
     */
    public long getAverageElapsed() {
        long total = getTotal();
        if (total == 0) {
            return 0;
        }
        return getTotalElapsed() / total;
    }

    /**
     * get max elapsed.
     *
     * @return max elapsed
     */
    public long getMaxElapsed() {
        return maxElapsed.get();
    }

    /**
     * get failed.
     *
     * @return failed
     */
    public int getFailed() {
        return failed.get();
    }

    /**
     * get failed elapsed.
     *
     * @return failed elapsed
     */
    public long getFailedElapsed() {
        return failedElapsed.get();
    }

    /**
     * get failed average elapsed.
     *
     * @return failed average elapsed
     */
    public long getFailedAverageElapsed() {
        long failed = getFailed();
        if (failed == 0) {
            return 0;
        }
        return getFailedElapsed() / failed;
    }

    /**
     * get failed max elapsed.
     *
     * @return failed max elapsed
     */
    public long getFailedMaxElapsed() {
        return failedMaxElapsed.get();
    }

    /**
     * get succeeded.
     *
     * @return succeeded
     */
    public long getSucceeded() {
        return getTotal() - getFailed();
    }

    /**
     * get succeeded elapsed.
     *
     * @return succeeded elapsed
     */
    public long getSucceededElapsed() {
        return getTotalElapsed() - getFailedElapsed();
    }

    /**
     * get succeeded average elapsed.
     *
     * @return succeeded average elapsed
     */
    public long getSucceededAverageElapsed() {
        long succeeded = getSucceeded();
        if (succeeded == 0) {
            return 0;
        }
        return getSucceededElapsed() / succeeded;
    }

    /**
     * get succeeded max elapsed.
     *
     * @return succeeded max elapsed.
     */
    public long getSucceededMaxElapsed() {
        return succeededMaxElapsed.get();
    }

    /**
     * Calculate average TPS (Transaction per second).
     *
     * @return tps
     */
    public long getAverageTps() {
        if (getTotalElapsed() >= 1000L) {
            return getTotal() / (getTotalElapsed() / 1000L);
        }
        return getTotal();
    }

    /**
     * 获取限制线程数的信号量，信号量的许可数就是executes设置的值
     * 2017-08-21 yizhenqiang
     * @param maxThreadNum executes设置的值
     * @return
     */
    public Semaphore getSemaphore(int maxThreadNum) {
        if(maxThreadNum <= 0) {
            return null;
        }

        if (executesLimit == null || executesPermits != maxThreadNum) {
            synchronized (this) {
                if (executesLimit == null || executesPermits != maxThreadNum) {
                    executesLimit = new Semaphore(maxThreadNum);
                    executesPermits = maxThreadNum;
                }
            }
        }

        return executesLimit;
    }
}