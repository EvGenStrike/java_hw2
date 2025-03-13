package org.example.task;

import java.util.ArrayList;
import java.util.Random;

public class Task2 {
    public static ArrayList<Double> generateRandomList(int size, double min, double max) {
        Random random = new Random();
        ArrayList<Double> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(min + (max - min) * random.nextDouble());
        }
        return list;
    }

    public static void bubbleSort(ArrayList<Double> list) {
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    double temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void printList(ArrayList<Double> list) {
        System.out.println(list);
    }
}
