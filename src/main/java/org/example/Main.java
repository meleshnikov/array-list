package org.example;

import org.example.list.IntegerArrayList;

import java.util.Comparator;
import java.util.Random;


public class Main {

    private static final int SIZE = 100;
    private static final int MIN = 1;
    private static final int MAX = 100;


    public static void main(String[] args) {

        IntegerArrayList list1 = generateRandomList();
        IntegerArrayList list2 = generateRandomList();
        IntegerArrayList list3 = generateRandomList();

        list1.bubbleSort(Comparator.naturalOrder());
        System.out.println(list1);

        list2.insertionSort(Comparator.naturalOrder());
        System.out.println(list2);

        list3.selectionSort(Comparator.naturalOrder());
        System.out.println(list3);

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