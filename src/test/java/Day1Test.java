import org.example.Day1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    Day1 day = new Day1();
    @Test
    public void part1() {
        assertEquals(142, day.solvePart1("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day1example.csv"));
    }

    @Test
    public void part2() {
        assertEquals(302, day.solvePart2("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day1part2example.csv"));
    }
}
