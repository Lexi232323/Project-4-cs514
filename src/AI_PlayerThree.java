import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class AI_PlayerThree implements WheelOfFortunePlayer {
    private Set<Character> guessedLetters;
    private Random random;

    public AI_PlayerThree() {
        this.guessedLetters = new HashSet<>();
        this.random = new Random();
    }

    /**
     * AI_III has different method to guess
     * @param previousGuesses
     * @return
     */
    @Override
    public char nextGuess(String previousGuesses) {
        char guess;


        for (char c : previousGuesses.toCharArray()) {
            guessedLetters.add(c);
        }
        do {
            guess = (char) ('a' + random.nextInt(26));
        } while (guessedLetters.contains(guess));
        guessedLetters.add(guess);
        System.out.println("Random guess: " + guess);
        return guess;
    }

    /**
     * set AI_III ID
     * @return
     */
    @Override
    public String playerId() {
        return "AI_PlayerThree";
    }

    /**
     * Clear guessed letters to reset for a new game
     */

    @Override
    public void reset() {
        guessedLetters.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AI_PlayerThree that = (AI_PlayerThree) o;
        return Objects.equals(guessedLetters, that.guessedLetters) && Objects.equals(random, that.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guessedLetters, random);
    }

    @Override
    public String toString() {
        return "AI_PlayerThree{" +
                "guessedLetters=" + guessedLetters +
                ", random=" + random +
                '}';
    }
}
