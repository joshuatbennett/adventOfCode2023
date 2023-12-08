import org.example.Day4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {
    Day4 day = new Day4();
    @Test
    public void part1() {
        assertEquals(13, day.solvePart1("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day4example.csv"));
    }
    @Test
    public void part2() {
        assertEquals(30, day.solvePart2("C:\\Users\\benne\\IdeaProjects\\adventOfCode\\src\\test\\resources\\day4example.csv"));
    }
}
