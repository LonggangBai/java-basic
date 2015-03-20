/**
 * Project Name:java-basic
 * File Name:CollectionUtils.java
 * Package Name:com.easyway.java.basic.collection.exams
 * Date:2015-3-20上午9:22:52
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.collection.exams;
/**
 * ClassName:CollectionUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-20 上午9:22:52 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;

/**
 * Created by xiaozi on 1/6/15.
 */
public class CollectionUtils {

    /**
     * 取出collection中某一列的值
     * @param c
     * @param propName
     * @param result
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T>Collection<T> pluck(Collection<?> c, String propName, Collection<T> result) {
        if (c == null) {
            return result;
        }
        try {
            for (Iterator<?> it = c.iterator(); it.hasNext();) {
                Object element = it.next();
                T value = (T) PropertyUtils.getProperty(element, propName);
                result.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 取出collection中某一列的值，并以另一列的值作为键关联
     * @param c
     * @param propName
     * @param keyName
     * @param result
     * @param <K>
     * @param <V>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <K, V>Map<K, V> pluck(Collection<?> c, String keyName, String propName, Map<K, V> result) throws Exception {
        if (c == null) {
            return result;
        }
        try {
            for (Iterator<?> it = c.iterator(); it.hasNext();) {
                Object element = it.next();
                K key = (K) PropertyUtils.getProperty(element, keyName);
                V value = (V) PropertyUtils.getProperty(element, propName);
                result.put(key, value);
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error while getting the object property.", e);
        }
    }

    /**
     * 使用collection中某一列的值关联collection
     * @param c
     * @param keyName
     * @param <K>
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <K, T>Map <K, T> keyBy(List<T> c, String keyName, Class<K> clazz) throws Exception {
        if (c == null) {
            return new LinkedHashMap<K, T>();
        }
        try {
            Map<K, T> keyed = new LinkedHashMap<K, T>();
            for (Iterator<T> it = c.iterator(); it.hasNext(); ) {
                T element = it.next();
                K key = (K) PropertyUtils.getProperty(element, keyName);
                keyed.put(key, element);
            }
            return keyed;
        } catch (Exception e) {
            throw new Exception("Error while getting the object property.", e);
        }
    }

    /**
     * 按照collection中某一列的值分组
     * @param c
     * @param propName
     * @param <K>
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <K, T>Map <K, List<T>> groupBy(List<T> c, String propName, Class<K> clazz) throws Exception {
        if (c == null) {
            return new LinkedHashMap<K, List<T>>();
        }
        try {
            Map<K, List<T>> grouped = new LinkedHashMap<K, List<T>>();
            for (Iterator<T> it = c.iterator(); it.hasNext(); ) {
                T element = it.next();
                K key = (K) PropertyUtils.getProperty(element, propName);
                List<T> l = grouped.get(key);
                if (l == null) {
                    l = new ArrayList<T>();
                    grouped.put(key, l);
                }
                l.add(element);
            }
            return grouped;
        } catch (Exception e) {
            throw new Exception("Error while getting the object property.", e);
        }
    }

    /**
     * collection去重
     * @param c
     * @param result
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> unique(Collection<T> c, Collection<T> result) {
        if (c == null) {
            return result;
        }
        Set<T> s = new LinkedHashSet<T>(c);
        result.addAll(s);
        return result;
    }
}

