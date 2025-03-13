package org.example.task;

import java.util.Random;

public class Task1 {
    public static int[] generateRandomArray(int size, int min, int max) {
        Random random = new Random();
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(max - min + 1) + min;
        }
        return numbers;
    }

    public static double calculateAverage(int[] numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }

        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }

    public static void printArray(int[] numbers) {
        System.out.print("Массив: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
