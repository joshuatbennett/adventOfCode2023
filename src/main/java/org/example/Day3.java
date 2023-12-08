package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day3 {
    private static void addPaddingRows(List<List<Character>> inputMatrix) {
        int rows = inputMatrix.get(0).size();
        List<Character> paddingRow = Collections.nCopies(rows, '.').stream().toList();
        inputMatrix.add(0, paddingRow);
        inputMatrix.add(paddingRow);
    }

    public int solvePart1(String path) {
        List<List<Character>> inputMatrix = getInputMatrix(path);
        List<Part> parts = findPartNumbers(inputMatrix);
        return parts
                .stream()
                .filter(part -> part.isValid)
                .mapToInt(part -> Integer.parseInt(part.value))
                .sum();
    }

    public int solvePart2(String path) {
        List<List<Character>> inputMatrix = getInputMatrix(path);
        List<Part> parts = findPartNumbers(inputMatrix);
        List<Gear> gears = findGears(inputMatrix);
        gears.forEach(gear -> findSurroundingCells(gear.row, gear.column, 1, inputMatrix));
        setGearPartNumbers(gears, parts);
        List<Gear> validGears = gears
                .stream()
                .filter(gear -> gear.parts.size() == 2)
                .toList();
        return validGears
                .stream()
                .mapToInt(Gear::getGearRatio)
                .sum();
    }

    private void setGearPartNumbers(List<Gear> gears, List<Part> parts) {
        for (Part part : parts
                .stream()
                .filter(part -> part.surroundingCells.contains('*'))
                .toList()) {
            Integer partNumberStartColumn = part.endColumn - part.value.length() + 1;
            Integer partNumberEndColumn = part.endColumn;
            Integer partNumberRow = part.row;
            gears
                    .stream()
                    .filter(gear -> gear.row >= partNumberRow - 1)
                    .filter(gear -> gear.row <= partNumberRow + 1)
                    .filter(gear -> gear.column >= partNumberStartColumn - 1)
                    .filter(gear -> gear.column <= partNumberEndColumn + 1)
                    .findFirst()
                    .ifPresent(nearbyGear -> nearbyGear.addPartNumber(part));
        }
    }

    private List<Character> findSurroundingCells(int rowNumber, int endColumn, int valueLength, List<List<Character>> inputMatrix) {
        List<Character> surroundingCells = new ArrayList<>();
        List<Character> rowBefore = inputMatrix
                .get(rowNumber - 1)
                .subList(endColumn - valueLength, endColumn + 2);
        List<Character> row = inputMatrix
                .get(rowNumber)
                .subList(endColumn - valueLength, endColumn + 2);
        List<Character> rowAfter = inputMatrix
                .get(rowNumber + 1)
                .subList(endColumn - valueLength, endColumn + 2);

        surroundingCells.addAll(rowBefore);
        surroundingCells.addAll(rowAfter);
        surroundingCells.add(row.get(0));
        surroundingCells.add(row.get(row.size() - 1));

        return surroundingCells;
    }

    private List<List<Character>> getInputMatrix(String path) {
        List<String> rows = Utils.readValuesFromCSV(path);
        List<List<Character>> inputMatrix = new ArrayList<>();
        rows.forEach(row -> {
            List<Character> inputMatrixRow = new ArrayList<>();
            for (int i = 0; i < row.toCharArray().length; i++) {
                inputMatrixRow.add(row.charAt(i));
            }
            inputMatrix.add(inputMatrixRow);
        });
        return padMatrix(inputMatrix);
    }

    private List<List<Character>> padMatrix(List<List<Character>> inputMatrix) {
        addPaddingColumns(inputMatrix);
        addPaddingRows(inputMatrix);
        return inputMatrix;
    }

    private void addPaddingColumns(List<List<Character>> inputMatrix) {
        inputMatrix.forEach(row -> {
            row.add(0, '.');
            row.add('.');
        });
    }

    private List<Gear> findGears(List<List<Character>> inputMatrix) {
        List<Gear> gears = new ArrayList<>();
        int rowCount = inputMatrix.size();
        int columnCount = inputMatrix.get(0).size();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (inputMatrix.get(i).get(j) == '*') {
                    gears.add(new Gear(i, j));
                }
            }
        }
        return gears;
    }

    private List<Part> findPartNumbers(List<List<Character>> inputMatrix) {
        List<Part> parts = new ArrayList<>();
        int rowCount = inputMatrix.size();
        int columnCount = inputMatrix.get(0).size();
        for (int i = 0; i < rowCount; i++) {
            String number = "";
            for (int j = 0; j < columnCount; j++) {
                Character cell = inputMatrix.get(i).get(j);
                if (Character.isDigit(cell)) {
                    number = String.format("%s%s", number, cell);
                    Character nextCell = inputMatrix.get(i).get(j + 1);
                    if (!Character.isDigit(nextCell)) {
                        parts.add(new Part(i, number, j));
                        number = "";
                    }
                }
            }
        }

        parts.forEach(part -> part.setSurroudingCells(findSurroundingCells(part.row, part.endColumn, part.value.length(), inputMatrix)));
        parts.forEach(Part::setIsValid);
        return parts;
    }

    public static class Gear {
        private final int row;
        private final int column;
        private final List<Part> parts;

        public Gear(int row, int column) {
            this.row = row;
            this.column = column;
            this.parts = new ArrayList<>();
        }

        public void addPartNumber(Part part) {
            parts.add(part);
        }

        public int getGearRatio() {
            return Integer.parseInt(this.parts.get(0).value) * Integer.parseInt(this.parts.get(1).value);
        }
    }

    public static class Part {
        private final Integer endColumn;
        private final String value;
        private final Integer row;

        private boolean isValid;
        private List<Character> surroundingCells;

        public Part(Integer row, String value, int endColumn) {
            this.endColumn = endColumn;
            this.row = row;
            this.value = value;
            this.isValid = false;
            this.surroundingCells = new ArrayList<>();
        }

        public void setIsValid() {
            this.isValid = this.surroundingCells.stream().filter(cell -> !Character.isDigit(cell)).anyMatch(cell -> cell != '.');
        }

        public void setSurroudingCells(List<Character> surroundingCells) {
            this.surroundingCells = surroundingCells;
        }
    }
}