package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
        TreeSet<Character> set = new TreeSet<>(sb.toString().chars().mapToObj(e->(char)e).collect(Collectors.toList()));
        set.stream().limit(5).forEach(System.out::print);




        //    /home/li/Downloads/subscription-form(copy).txt
    }
}
