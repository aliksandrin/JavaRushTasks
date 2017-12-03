package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry( int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else {
            if (o instanceof Entry) {
                Entry var2 = (Entry)o;
                if (Objects.equals(this.key, var2.getKey()) && Objects.equals(this.value, var2.getValue())) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.key) ^ Objects.hashCode(this.value);
    }

    @Override
    public String toString() {
        return this.key + "=" + this.value;
    }
}
