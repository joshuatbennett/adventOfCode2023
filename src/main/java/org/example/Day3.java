package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 {
    public class Symbol {
        private boolean hasAdjacentSymbol;
        private Integer startColumn;
        private final String value;
        private final Integer row;
        private Integer endColumn;
        private List<Character> surroundingCells;

        public Symbol(Integer row, String value) {
            this.startColumn = -1;
            this.endColumn = -1;
            this.row = row;
            this.value = value;
            this.hasAdjacentSymbol = false;
        }

        public void setColumns(int startIndex) {
            this.startColumn = startIndex;
            this.endColumn = startIndex + value.length()-1;
        }

        public void setSurroundingCells(List<String> board) {
            List<Character> surroundingCells = new ArrayList<>();
            List<char[]> boardMatrix = new ArrayList<>();
            board.forEach(row -> boardMatrix.add(row.toCharArray()));
            if(startColumn > 0)
                surroundingCells.add(boardMatrix.get(this.row)[this.startColumn-1]);
            if(endColumn < boardMatrix.get(0).length-1)
                surroundingCells.add(boardMatrix.get(this.row)[this.endColumn+1]);
            if(row > 0) {
                if(startColumn > 0)
                    surroundingCells.add(boardMatrix.get(this.row-1)[this.startColumn-1]);
                if(endColumn < boardMatrix.get(0).length-1)
                    surroundingCells.add(boardMatrix.get(this.row-1)[this.endColumn+1]);
                for (int i = startColumn; i <= endColumn; i++) {
                    surroundingCells.add(boardMatrix.get(this.row - 1)[i]);
                }
            }
            if(row < board.size()-1) {
                if(startColumn > 0)
                    surroundingCells.add(boardMatrix.get(this.row+1)[this.startColumn-1]);
                if(endColumn < boardMatrix.get(0).length-1)
                    surroundingCells.add(boardMatrix.get(this.row+1)[this.endColumn+1]);
                for (int i = startColumn; i <= endColumn; i++) {
                    surroundingCells.add(boardMatrix.get(this.row + 1)[i]);
                }
            }

            this.surroundingCells = surroundingCells;
        }

        public void setHasAdjacentSymbol() {
            this.hasAdjacentSymbol = this.surroundingCells.stream().anyMatch(cell -> !cell.equals('.'));
        }

        public boolean hasAdjacentSymbol() {
            return hasAdjacentSymbol;
        }

        public String getValue() {
            return value;
        }
    }

    public int solvePart1(String path) {
        List<Symbol> symbols = new ArrayList<>();
        List<String> rows = Utils.readValuesFromCSV(path);
        addSymbols(rows, symbols, Pattern.compile("[\\d]*"));
        symbols.forEach(symbol -> findStartColumn(symbol, rows));
        symbols.forEach(symbol -> symbol.setSurroundingCells(rows));
        symbols.forEach(Symbol::setHasAdjacentSymbol);

        symbols.stream().filter(Symbol::hasAdjacentSymbol).toList().forEach(symbol -> System.out.println(symbol.value));
        return symbols.stream().filter(Symbol::hasAdjacentSymbol).mapToInt(s -> Integer.parseInt(s.getValue())).sum();
    }

    private void searchForAdjacentSymbols(Symbol symbol, List<String> rows) {
        int rowCount = rows.size();
        int columnCount = rows.get(0).length();
        char symbolToLeft = '.';
        char symbolToRight = '.';
        List<Character> symbolsAbove = new ArrayList<>();
        List<Character> symbolsBelow = new ArrayList<>();

        if(symbol.startColumn > 0)
            symbolToLeft = rows.get(symbol.row).charAt(symbol.startColumn - 1);

        if(symbol.endColumn < rows.get(0).length() -1)
            symbolToRight = rows.get(symbol.row).charAt(symbol.endColumn + 1);

        if(symbol.row > 0) {
            for (int i = symbol.startColumn - 1; i < symbol.endColumn + 2; i++) {
                if(i >= 0 && i < columnCount) {
                    char character = rows.get(symbol.row - 1).charAt(i);
                    if(character != '.'){
                        symbolsAbove.add(character);
                    }
                }
            }
        }

        if(symbol.row < rowCount - 1) {
            for (int i = symbol.startColumn - 1; i < symbol.endColumn + 2; i++) {
                if(i >= 0 && i < columnCount) {
                    char character = rows.get(symbol.row + 1).charAt(i);
                    if(character != '.'){
                        symbolsBelow.add(character);
                    }
                }
            }
        }

        if(symbolToLeft != '.' || symbolToRight != '.' || !symbolsAbove.isEmpty() || !symbolsBelow.isEmpty()) {
            symbol.setHasAdjacentSymbol();
        }
    }

    private void findStartColumn(Symbol symbol, List<String> x) {
        symbol.setColumns(x.get(symbol.row).indexOf(symbol.value));
    }

    private void addSymbols(List<String> x, List<Symbol> symbols, Pattern pattern) {
        for(int i = 0; i < x.size(); i++) {
            String row = x.get(i);
            Matcher matcher = pattern.matcher(row);
            while(matcher.find()) {
                String value = matcher.group(0);
                if(!value.isEmpty()) {
                    symbols.add(new Symbol(i, value));
                }
            }
        }
    }
}
