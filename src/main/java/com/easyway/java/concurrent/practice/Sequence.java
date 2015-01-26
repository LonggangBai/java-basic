package com.easyway.java.concurrent.practice;

import com.easyway.java.concurrent.practice.annotations.GuardedBy;
import com.easyway.java.concurrent.practice.annotations.ThreadSafe;

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
