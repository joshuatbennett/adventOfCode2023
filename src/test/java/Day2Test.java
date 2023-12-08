import org.example.Day2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {

    @Test
    public void part1() {
        Day2 day2 = new Day2();
        assertEquals(8, day2.solvePart1("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day2example.csv"));
    }

    @Test
    public void part2() {
        Day2 day2 = new Day2();
        assertEquals(2286, day2.solvePart2("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day2example.csv"));
    }
}
