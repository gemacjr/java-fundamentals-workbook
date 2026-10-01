package abstraction;

import java.util.ArrayList;
import java.util.List;

// ABSTRACT CLASS = partial implementation + shared MUTABLE STATE + constructor.
// This is the Template Method pattern: the skeleton is fixed (final),
// subclasses fill in only the varying step.
public abstract class AbstractChallenge implements Challenger {

    private final String title;                       // constructor-initialized state
    private int attempts;                             // mutable per-instance state
    protected final List<String> log = new ArrayList<>(); // visible to subclasses only

    protected AbstractChallenge(String title) {      // interfaces can't have this
        this.title = title;
    }

    // Template method: final so subclasses can't reorder the algorithm.
    public final String doChallenge() {
        attempts++;
        log.add("start " + title);
        String answer = solve();
        log.add("end " + title);
        return title + " -> " + answer;
    }

    protected abstract String solve(); // the one "hole" subclasses must fill

    public int getAttempts() { return attempts; }
}
