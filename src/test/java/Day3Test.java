import org.example.Day3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    @Test
    public void part1() {
        Day3 day3 = new Day3();
        assertEquals(4361, day3.solvePart1("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day3example.csv"));
    }
    @Test
    public void part2() {
        Day3 day3 = new Day3();
        assertEquals(467835, day3.solvePart2("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day3example.csv"));
    }
}
