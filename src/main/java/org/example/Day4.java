package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 implements Day {

    @Override
    public Integer solvePart1(String path) {
        List<Card> cards = Utils.readValuesFromCSV(path).stream().map(Card::new).toList();
        return cards.stream().mapToInt(Card::getPoints).sum();
    }

    @Override
    public Integer solvePart2(String path) {
        List<Card> cards = Utils.readValuesFromCSV(path).stream().map(Card::new).toList();
        return evaluateCards(cards);
    }

    private int evaluateCards(List<Card> cards) {
        Map<Integer, List<Integer>> cardRewards = new HashMap<>();
        for (Card card : cards) {
            List<Integer> rewards = new ArrayList<>();
            for(int i = card.index+1; i <= card.getWinningNumberCount()+card.index; i++) {
                rewards.add(i);
            }
            cardRewards.put(card.index, rewards);
        }

        List<Integer> finalCards = new ArrayList<>(cards.stream().map(Card::getIndex).toList());
        int i = 0;
        while(i < finalCards.size()) {
            List<Integer> rewards = cardRewards.get(finalCards.get(i));
            finalCards.addAll(rewards);
            i++;
        }
        return finalCards.size();
    }

    public static class Card {
        private final int index;
        private final Integer points;
        private final List<Integer> winningNumbers;
        private final List<Integer> ownedNumbers;

        public Card(String values) {
            this.index = Integer.parseInt(values.split(": ")[0].replace("Card ", "").trim());
            this.winningNumbers = Stream.of(values.split(": ")[1].split(" \\| ")[0].split(" "))
                    .filter(number -> !number.isBlank())
                    .map(Integer::parseInt)
                    .sorted()
                    .toList();
            this.ownedNumbers = Stream.of(values.split(": ")[1].split(" \\| ")[1].split(" "))
                    .filter(number -> !number.isBlank())
                    .map(Integer::parseInt)
                    .sorted()
                    .collect(Collectors.toList());
            this.points = this.calculatePoints();
        }

        private Integer calculatePoints() {
            return (int) Math.pow(2, getWinningNumberCount()-1);
        }

        private Integer getWinningNumberCount() {
            List<Integer> ownedWinningNumbers = new ArrayList<>(this.winningNumbers);
            ownedWinningNumbers.retainAll(this.ownedNumbers);
            return ownedWinningNumbers.size();
        }

        public int getPoints() {
            return this.points;
        }

        public Integer getIndex() {
            return this.index;
        }
    }
}
