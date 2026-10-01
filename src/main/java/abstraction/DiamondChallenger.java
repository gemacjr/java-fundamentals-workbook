package abstraction;

// Inherits describe() from BOTH Challenger and Ranked.
// Delete the override below and javac refuses to compile:
//
//   error: types Challenger and Ranked are incompatible;
//   class DiamondChallenger inherits unrelated defaults for describe() ...
//
// Java won't guess which default you meant -- you must override and decide.
public class DiamondChallenger implements Challenger, Ranked {

    @Override
    public String doChallenge() { return "42"; }

    @Override
    public int rank() { return 1; }

    @Override
    public String describe() {
        // InterfaceName.super.method() picks one specific inherited default
        return Challenger.super.describe() + " | " + Ranked.super.describe();
    }
}
