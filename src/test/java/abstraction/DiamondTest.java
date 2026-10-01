package abstraction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Java's three rules for resolving inherited methods, in priority order.
class DiamondTest {

    // Rule 1: CLASSES WIN. A method from a superclass beats any interface default.
    static class Base {
        public String describe() { return "from class"; }
    }
    static class ClassWins extends Base implements Ranked {
        public int rank() { return 7; }
    }

    @Test
    void rule1_classBeatsInterfaceDefault() {
        assertEquals("from class", new ClassWins().describe());
    }

    // Rule 2: MORE SPECIFIC INTERFACE WINS. A sub-interface's default beats its parent's.
    interface EliteRanked extends Ranked {
        default String describe() { return "elite"; }
    }
    static class SpecificWins implements Ranked, EliteRanked {
        public int rank() { return 1; }
    }

    @Test
    void rule2_subInterfaceBeatsParentInterface() {
        assertEquals("elite", new SpecificWins().describe());
    }

    // Rule 3: OTHERWISE, YOU MUST CHOOSE. Unrelated defaults -> compile error
    // unless the class overrides. That's your exercise in DiamondChallenger.
    @Test
    void rule3_youResolveTheConflict() {
        assertEquals("[challenge] 42 | rank #1", new DiamondChallenger().describe());
    }
}
