package org.example;

import org.example.list.IntegerArrayList;

import java.util.Comparator;
import java.util.Random;


public class Main {

    private static final int SIZE = 10_000;
    private static final int MIN = 1;
    private static final int MAX = 10_000;


    public static void main(String[] args) {

        IntegerArrayList list1 = generateRandomList();


        list1.bubbleSort(Comparator.naturalOrder());
        System.out.println(list1);

        int index = list1.binarySearch(47, Comparator.naturalOrder());
        System.out.println(index);

        index = list1.linearSearch(0, list1.size(), 47);
        System.out.println(index);

    }

    private static IntegerArrayList generateRandomList() {
        IntegerArrayList ints = new IntegerArrayList();
        for (int i = 0; i < SIZE; i++) {
            ints.add(randomInt(MIN, MAX));
        }
        return ints;
    }

    private static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}