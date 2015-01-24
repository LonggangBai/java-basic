package com.easyway.java.basic.concurrent.practice;

import java.util.HashSet;
import java.util.Set;

import com.easyway.java.basic.concurrent.practice.annotations.GuardedBy;
import com.easyway.java.basic.concurrent.practice.annotations.ThreadSafe;

/**
 * PersonSet
 * <p/>
 * Using confinement to ensure thread safety
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class PersonSet {
    @GuardedBy("this") private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    interface Person {
    }
}
