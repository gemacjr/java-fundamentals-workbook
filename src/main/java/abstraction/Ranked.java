package abstraction;

// A second capability that ALSO ships a default describe().
// Implementing both Challenger and Ranked creates a "diamond" conflict.
public interface Ranked {
    int rank();

    default String describe() {
        return "rank #" + rank();
    }
}
