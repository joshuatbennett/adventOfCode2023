package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Day> days = new HashMap<>();
        days.put(1, new Day1());
        days.put(2, new Day2());
        days.put(3, new Day3());
        days.put(4, new Day4());

        printDayResults(days);
    }

    private static void printDayResults(Map<Integer, Day> days) {
        System.out.println("--------------------------------------------");
        days.forEach((key, value) -> {
            System.out.printf("Day %s Part %s: %s%n",
                    key,
                    1,
                    value.solvePart1(String.format("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\main\\resources\\day%s.csv", key))
            );
            System.out.printf("Day %s Part %s: %s%n",
                    key,
                    2,
                    value.solvePart2(String.format("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\main\\resources\\day%s.csv", key))
            );
            System.out.println("--------------------------------------------");
        });
    }
}