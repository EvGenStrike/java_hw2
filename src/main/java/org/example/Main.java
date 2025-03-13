package org.example;

import org.example.task.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите номер задания (1-5): ");
        int taskNumber = scanner.nextInt();

        scanner.nextLine();

        switch (taskNumber) {
            case 1:
                runAverageCalculation();
                break;
            case 2:
                runBubbleSort();
                break;
            case 3:
                runSortEmployeesBySalary();
                break;
            case 4:
                runFetchHeaders();
                break;
            case 5:
                runSync(scanner);
                break;
            default:
                return;
        }
    }

    public static void runAverageCalculation() {
        int size = 10;
        int min = 1, max = 100;

        int[] numbers = Task1.generateRandomArray(size, min, max);
        Task1.printArray(numbers);
        double average = Task1.calculateAverage(numbers);

        System.out.println("Среднее значение: " + average);
    }

    public static void runBubbleSort() {
        int size = 10;
        double min = 0.0, max = 100.0;

        ArrayList<Double> list = Task2.generateRandomList(size, min, max);

        System.out.println("Исходный список:");
        Task2.printList(list);

        Task2.bubbleSort(list);

        System.out.println("Отсортированный список:");
        Task2.printList(list);
    }

    public static void runSortEmployeesBySalary() {
        List<Employee> employees = Task3.generateEmployees();

        System.out.println("Исходный список сотрудников:");
        Task3.printEmployees(employees);

        List<Employee> sortedEmployees = Task3.sortBySalary(employees);

        System.out.println("\nСписок сотрудников, отсортированный по зарплате:");
        Task3.printEmployees(sortedEmployees);
    }

    public static void runFetchHeaders() {
        Task4.fetchAndPrintHeaders();
    }

    public static void runSync(Scanner scanner) {
        System.out.print("Введите путь к исходной папке: ");
        String source = scanner.nextLine();

        System.out.print("Введите путь к целевой папке: ");
        String target = scanner.nextLine();

        Task5 syncTask = new Task5(source, target);

        syncTask.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        syncTask.stop();

        scanner.close();
    }
}