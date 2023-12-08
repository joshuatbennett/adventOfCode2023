import org.example.Day1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    @Test
    public void part1() {
        Day1 day1 = new Day1();
        assertEquals(142, day1.solvePart1("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day1example.csv"));
    }

    @Test
    public void part2() {
        Day1 day1 = new Day1();
        assertEquals(302, day1.solvePart2("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day1part2example.csv"));
    }
}
