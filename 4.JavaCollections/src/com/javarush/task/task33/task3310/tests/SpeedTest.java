package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpeedTest {

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        for (String str : strings) {
            ids.add(shortener.getId(str));
        }

        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }

        Date end = new Date();
        return end.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener1 = new Shortener(hashMapStorageStrategy);

        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);

        Set<String> origStrings = IntStream.range(0, 10000).mapToObj(i -> Helper.generateRandomString()).collect(Collectors.toSet());

        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        Long timeForShortener1 = getTimeForGettingIds(shortener1, origStrings, ids1);
        Long timeForShortener2 = getTimeForGettingIds(shortener2, origStrings, ids2);

        Assert.assertTrue(timeForShortener1 > timeForShortener2);

        Set<String> strins1 = new HashSet<>();
        Set<String> strins2 = new HashSet<>();
        Assert.assertEquals(getTimeForGettingStrings(shortener1, ids1, strins1),
                getTimeForGettingStrings(shortener2, ids2, strins2), 30);

    }
}
