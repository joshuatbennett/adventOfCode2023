import org.example.Day3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    Day3 day = new Day3();
    @Test
    public void part1() {
        assertEquals(4361, day.solvePart1("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day3example.csv"));
    }
    @Test
    public void part2() {
        assertEquals(467835, day.solvePart2("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day3example.csv"));
    }
}
