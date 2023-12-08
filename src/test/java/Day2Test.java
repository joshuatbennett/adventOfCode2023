import org.example.Day2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    Day2 day = new Day2();

    @Test
    public void part1() {
        assertEquals(8, day.solvePart1("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day2example.csv"));
    }

    @Test
    public void part2() {
        assertEquals(2286, day.solvePart2("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day2example.csv"));
    }
}
