package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader= Files.newBufferedReader(Paths.get(args[0]), StandardCharsets.UTF_8);
        while (bufferedReader.ready()){
            sb.append(bufferedReader.readLine().toLowerCase().replaceAll("[^A-Za-z]", ""));
        }
        TreeSet<Character> set = new TreeSet<>();
        char[] array = sb.toString().toCharArray();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        set.stream().limit(5).forEach(System.out::print);




        //    /home/li/Downloads/subscription-form(copy).txt
    }
}
