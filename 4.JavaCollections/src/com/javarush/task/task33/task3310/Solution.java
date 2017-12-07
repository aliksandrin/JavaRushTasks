package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);
        OurHashMapStorageStrategy strategy1 = new OurHashMapStorageStrategy();
        testStrategy(strategy1, 10000);
        FileStorageStrategy strategy2 = new FileStorageStrategy();
        testStrategy(strategy2, 500);
        OurHashBiMapStorageStrategy strategy3 = new OurHashBiMapStorageStrategy();
        testStrategy(strategy3, 10000);

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> set = new HashSet<>();
        for (String string : strings) {
            set.add(shortener.getId(string));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> set = new HashSet<>();
        for (Long key : keys) {
            set.add(shortener.getString(key));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getName());
        Shortener shortener = new Shortener(strategy);
        Set<Long> setKeys;
        Set<String> randomStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            randomStrings.add(Helper.generateRandomString());
        }
        Date start = new Date();
        setKeys = getIds(shortener,randomStrings);
        Date stop = new Date();
        Helper.printMessage("getIds worktime: " + (stop.getTime()- start.getTime()));

        Set<String> newStrings;
        start = new Date();
        newStrings = getStrings(shortener, setKeys);
        stop = new Date();
        Helper.printMessage("getStrings worktime: " + (stop.getTime()- start.getTime()));

        if (randomStrings.equals(newStrings))  Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
