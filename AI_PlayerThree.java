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
    // AI_III has different method to guess
    @Override
    public char nextGuess(String previousGuesses) {
        char guess;

        // Add previous guesses to avoid duplication
        for (char c : previousGuesses.toCharArray()) {
            guessedLetters.add(c);
        }

        // Keep generating random letters until we find one that hasn't been guessed
        do {
            guess = (char) ('a' + random.nextInt(26));  // Generates a random letter from 'a' to 'z'
        } while (guessedLetters.contains(guess));

        // Add the current guess to guessedLetters to avoid repeating it in future guesses
        guessedLetters.add(guess);
        System.out.println("Random guess: " + guess);
        return guess;
    }
    // set AI_III ID
    @Override
    public String playerId() {
        return "AI_PlayerThree";
    }

    @Override
    public void reset() {
        guessedLetters.clear();  // Clear guessed letters to reset for a new game
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
