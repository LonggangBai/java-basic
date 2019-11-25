package com.easyway.cluster.id;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;
/**
 * 10+10+12+7
 * ip+threadId+ms+7seqNo
 */
public class CustomGenerateID {

    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0])
                .intValue();
    }

    public static void main(String[] args)throws UnknownHostException {
        Long ip=IPUtil.getIpAddr2Long();
        //10
        System.out.println(String.valueOf(ip).length());
        System.out.println(getProcessID());
        System.out.println(String.format("%010d",getProcessID()));
        System.out.println(Integer.MAX_VALUE);
    }
}
