package com.javarush.task.task33.task3310.tests;


import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Test;
import org.junit.Assert;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String s1 = "Guava";
        String s2 = "Apache";
        String s3 = "Guava";

        Long k1 = shortener.getId(s1);
        Long k2 = shortener.getId(s2);
        Long k3 = shortener.getId(s3);
        Assert.assertNotEquals(k2, k1);
        Assert.assertNotEquals(k2, k3);
        Assert.assertEquals(k1, k3);

        Assert.assertEquals(shortener.getString(k1), s1);
        Assert.assertEquals(shortener.getString(k2), s2);
        Assert.assertEquals(shortener.getString(k3), s3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy hashMapStorageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy hashMapStorageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy hashMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy hashMapStorageStrategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy hashMapStorageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

}
