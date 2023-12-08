package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.MIN_VALUE;
import static org.example.Utils.readValuesFromCSV;

public class Day2 {

    Map<String, Integer> blockCounts = Map.of("red", 12, "blue", 14, "green", 13);

    public Integer solvePart1(String path) {
        List<Game> validGames = readValuesFromCSV(path)
                .stream()
                .map(Game::new)
                .filter(Game::isValid)
                .toList();

        return validGames
                .stream()
                .mapToInt(Game::getIndex)
                .sum();
    }

    public Integer solvePart2(String path) {
        List<Game> games = readValuesFromCSV(path)
                .stream()
                .map(Game::new)
                .toList();

        return games
                .stream()
                .mapToInt(Game::getPower)
                .sum();
    }

    public class Game {
        private final int index;
        private final List<Map<String, Integer>> rounds;
        private final boolean valid;
        private final Map<String, Integer> minBlockCounts;
        private final Integer power;

        public Game(String gameData) {
            this.index = Integer.parseInt(gameData.split(":")[0].replace("Game ", ""));
            this.rounds = this.getRoundList(gameData);
            this.valid = this.validateGame();
            this.minBlockCounts = this.initializeMinBlockCounts();
            this.setMinBlockCounts();
            this.power = this.setPower();
        }

        private Map<String, Integer> initializeMinBlockCounts() {
            Map<String, Integer> minBlockCounts = new HashMap<>();
            minBlockCounts.put("red", MIN_VALUE);
            minBlockCounts.put("blue", MIN_VALUE);
            minBlockCounts.put("green", MIN_VALUE);
            return minBlockCounts;
        }

        private boolean validateGame() {
            AtomicBoolean valid = new AtomicBoolean(true);
            this.rounds.forEach(round -> blockCounts.keySet().forEach(color -> {
                if (round.containsKey(color) && round.get(color) > blockCounts.get(color))
                    valid.set(false);
            }));
            return valid.get();
        }

        private List<Map<String, Integer>> getRoundList(String gameData) {
            List<Map<String, Integer>> reveals = new ArrayList<>();
            List<String> rounds = List.of(gameData.replace(String.format("Game %s: ", this.index), "").split("; "));
            rounds.forEach(round -> {
                Map<String, Integer> reveal = new HashMap<>();
                Arrays.stream(round.split(", ")).forEach(block -> {
                    String color = block.split(" ")[1];
                    Integer count = Integer.valueOf(block.split(" ")[0]);
                    reveal.put(color, count);
                });
                reveals.add(reveal);
            });
            return reveals;
        }

        public boolean isValid() {
            return this.valid;
        }

        public int getIndex() {
            return this.index;
        }

        public int getPower() {
            return this.power;
        }

        public void setMinBlockCounts() {
            this.rounds.forEach(round -> this.minBlockCounts.keySet().forEach(color -> {
                if (round.containsKey(color) && round.get(color) > this.minBlockCounts.get(color)) {
                    this.minBlockCounts.put(color, round.get(color));
                }
            }));
        }

        public Integer setPower() {
            AtomicInteger power = new AtomicInteger(1);
            this.minBlockCounts.values().forEach(count -> power.set(power.get() * count));
            return power.get();
        }
    }
}
