package com.easyway.java.basic.concurrent.practice;

import com.easyway.java.basic.concurrent.practice.annotations.GuardedBy;
import com.easyway.java.basic.concurrent.practice.annotations.ThreadSafe;

/**
 * Sequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class Sequence {
    @GuardedBy("this") private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
