package com.easyway.java.basic.concurrent.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.easyway.java.basic.concurrent.practice.annotations.GuardedBy;
import com.easyway.java.basic.concurrent.practice.annotations.ThreadSafe;

/**
 * BetterAttributeStore
 * <p/>
 * Reducing lock duration
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class BetterAttributeStore {
    @GuardedBy("this") private final Map<String, String>
            attributes = new HashMap<String, String>();

    public boolean userLocationMatches(String name, String regexp) {
        String key = "users." + name + ".location";
        String location;
        synchronized (this) {
            location = attributes.get(key);
        }
        if (location == null)
            return false;
        else
            return Pattern.matches(regexp, location);
    }
}
