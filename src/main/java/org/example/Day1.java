package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.Utils.readValuesFromCSV;

public class Day1 implements Day {
    Map<String, String> stringNumberMap = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    @Override
    public Integer solvePart1(String path) {
        List<String> values = readValuesFromCSV(path);
        List<Integer> numbers = extractNumbers(values);
        return numbers.stream().mapToInt(x -> x).sum();
    }

    @Override
    public Integer solvePart2(String path) {
        List<String> values = convertStringNumbersToIntegers(readValuesFromCSV(path));
        List<Integer> numbers = extractNumbers(values);
        return numbers.stream().mapToInt(x -> x).sum();
    }

    List<Integer> extractNumbers(List<String> values) {
        List<Integer> numbers = new ArrayList<>();
        values.forEach(value -> {
            String x = value.replaceAll("[^0-9.]", "");
            if (x.length() == 1) {
                numbers.add(Integer.parseInt(String.format("%s%s", x, x)));
            } else {
                numbers.add(Integer.parseInt(String.format("%s%s", x.charAt(0), x.charAt(x.length() - 1))));
            }
        });
        return numbers;
    }

    public List<String> convertStringNumbersToIntegers(List<String> stringList) {
        List<String> newValues = new ArrayList<>();
        duplicateFirstLetterOfNumberStrings(stringList).forEach(value -> {
            String newValue = value;
            for (Map.Entry<String, String> entry : stringNumberMap.entrySet()) {
                newValue = newValue.replace(entry.getKey(), entry.getValue());
            }
            newValues.add(newValue);
        });
        return newValues;
    }

    private List<String> duplicateFirstLetterOfNumberStrings(List<String> values) {
        List<String> newValues = new ArrayList<>();
        values.forEach(value -> {
            String newValue = value;
            for (Map.Entry<String, String> entry : stringNumberMap.entrySet()) {
                String key = entry.getKey();
                newValue = newValue.replace(key, String.format("%s%s", key.charAt(0), key));
            }
            newValues.add(newValue);
        });
        return newValues;
    }
}
