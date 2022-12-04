package org.example;

import org.example.list.CustomArrayList;
import org.example.list.StringArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StringArrayList strings = new StringArrayList();

        strings.add("hello");
        strings.add("hello");
        strings.add("hello");

        strings.set(1, "this");

        System.out.println(strings);


    }
}