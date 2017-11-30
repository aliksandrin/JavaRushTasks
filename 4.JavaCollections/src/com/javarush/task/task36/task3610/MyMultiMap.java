package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return values().size();
    }

    @Override
    public V put(K key, V value) {
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            if (list.size() == repeatCount) {
                list.remove(0);
                list.add(value);
                return list.get(list.size() - 2);
            } else {
                list.add(value);
                return list.get(list.size() - 2);
            }
        }
        List<V> list = new ArrayList<>();
        list.add(value);
        map.put(key, list);
        return null;
    }

    @Override
    public V remove(Object key) {
        Iterator<Entry<K, List<V>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Entry<K, List<V>> entry = iterator.next();
            List<V> list = entry.getValue();
            if (entry.getKey().equals(key)) {
                list = entry.getValue();
                V item = list.remove(0);
                if (list.isEmpty()) iterator.remove();
                return item;
            }
        }
        return null;

    }


    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        for (Entry<K, List<V>> pair : map.entrySet()) {
            list.addAll(pair.getValue());
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, List<V>> pair : map.entrySet()) {
            if (pair.getValue().contains(value)) return true;
        }
        return map.containsValue(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}