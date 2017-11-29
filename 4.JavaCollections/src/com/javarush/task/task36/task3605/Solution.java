package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        TreeSet<String> set = new TreeSet<>();

        int symbol = 0;
        while ((symbol = bufferedReader.read()) != -1){
            String letter = ("" + (char)symbol).toLowerCase();
            if (letter.equals("[A-Za-z]") && !set.contains(letter)) set.add(letter);
        }

        String output = "";
        for (int i = 0; i < 5; i++){
            if (set.isEmpty()) break;
            output += set.pollFirst();
        }
        //    /home/li/Downloads/subscription-form(copy).txt
    }
}
