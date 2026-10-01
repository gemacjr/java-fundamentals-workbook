package abstraction;

// Puzzle modeled on the article's "Java Challenger" code challenge.
// Predict the output BEFORE running!
public class ZombieChallenge {
    static int nemesisRaids = 0;

    public static void main(String[] args) {
        Zombie zombie = () -> System.out.println("Graw!!! " + nemesisRaids++);
        System.out.println("Nemesis raids: " + nemesisRaids);

        Nemesis nemesis = new Nemesis() {
            public void shoot() { shoots = 23; }
        };

        Zombie.zombie.shoot();
        zombie.shoot();
        nemesis.shoot();
        System.out.println("Nemesis shoots: " + nemesis.shoots + " and raids: " + nemesisRaids);
    }
}

interface Zombie {
    Zombie zombie = () -> System.out.println("Stars!!!"); // hint: what modifiers are implied?
    void shoot();
}

abstract class Nemesis implements Zombie {
    public int shoots = 5; // an abstract class CAN have mutable instance state
}
