/**
 * Project Name:java-basic
 * File Name:LinkedQueue.java
 * Package Name:com.easyway.java.basic.collection
 * Date:2015-3-20下午1:53:45
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection;

import java.util.concurrent.atomic.AtomicReference;


/**
 * ClassName:LinkedQueue <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-20 下午1:53:45 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class LinkedQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;


        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(new Node<E>(null, null));
    private AtomicReference<Node<E>> tail = head;


    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> residue = curTail.next.get();
            if (curTail == tail.get()) {
                if (residue == null) /* A */{
                    if (curTail.next.compareAndSet(null, newNode)) /* C */{
                        tail.compareAndSet(curTail, newNode) /* D */;
                        return true;
                    }
                }
                else {
                    tail.compareAndSet(curTail, residue) /* B */;
                }
            }
        }
    }
}
