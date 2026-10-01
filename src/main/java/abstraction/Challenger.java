package abstraction;

// INTERFACE = a contract / capability. No instance state allowed.
// Any unrelated class can implement it, and a class can implement many.
@FunctionalInterface // exactly one abstract method -> usable as a lambda
public interface Challenger {

    int MAX_SCORE = 100; // implicitly public static final: a constant, NOT state

    String doChallenge(); // implicitly public abstract

    // Java 8: default method -> behavior without breaking existing implementers
    default String describe() {
        return prefix() + doChallenge();
    }

    // Java 8: static method -> utility tied to the interface
    static Challenger of(String answer) {
        return () -> answer;
    }

    // Java 9: private method -> share code between defaults, hidden from implementers
    private String prefix() {
        return "[challenge] ";
    }
}
