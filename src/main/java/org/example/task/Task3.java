package org.example.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task3 {
    public static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иванов Иван", 30, "IT", 75000.0));
        employees.add(new Employee("Петров Петр", 40, "Продажи", 50000.0));
        employees.add(new Employee("Сидоров Алексей", 35, "Маркетинг", 62000.0));
        employees.add(new Employee("Кузнецова Анна", 29, "Финансы", 82000.0));
        employees.add(new Employee("Морозов Дмитрий", 45, "IT", 55000.0));

        return employees;
    }

    public static List<Employee> sortBySalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
    }

    public static void printEmployees(List<Employee> employees) {
        employees.forEach(System.out::println);
    }
}
