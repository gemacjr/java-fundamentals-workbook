package abstraction;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AbstractionTest {

    @Test
    void interfaceCanBeALambda() {
        Challenger c = () -> "42";
        assertEquals("[challenge] 42", c.describe()); // default + private method
        assertEquals("hi", Challenger.of("hi").doChallenge()); // static method
    }

    @Test
    void abstractClassHoldsStateAndFixesTheAlgorithm() {
        AbstractChallenge c = new AbstractChallenge("FizzBuzz") {
            @Override protected String solve() { return "done"; }
        };

        assertEquals("FizzBuzz -> done", c.doChallenge());
        c.doChallenge();

        assertEquals(2, c.getAttempts()); // state survives between calls
        assertEquals("start FizzBuzz", c.log.get(0));
    }

    @Test
    void predictTheZombieOutput() {
        PrintStream realOut = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));
        try {
            ZombieChallenge.nemesisRaids = 0;
            ZombieChallenge.main(new String[0]);
        } finally {
            System.setOut(realOut);
        }

        String expected = String.join(System.lineSeparator(),
                "Nemesis raids: 0",                  // lambda body hasn't run yet
                "Stars!!!",                          // Zombie.zombie: implicit static final constant
                "Graw!!! 0",                         // post-increment prints 0, then raids = 1
                "Nemesis shoots: 23 and raids: 1"    // abstract class holds mutable instance state
        ) + System.lineSeparator();

        assertEquals(expected, captured.toString());
    }
}
