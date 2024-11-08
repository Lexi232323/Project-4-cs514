import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AI_PlayerTwo implements WheelOfFortunePlayer {
    private String lettersList;
    private int indexLettersList;
    private Set<Character> previousGuessesSet;

    public AI_PlayerTwo() {
        indexLettersList = 0;
        lettersList = "eariotnslcudpmhgbfywkvxzjq";
        previousGuessesSet = new HashSet<>();
    }

    /**
     * different method to guess for AI_II
     * @param previousGuesses
     * @return
     */
    @Override
    public char nextGuess(String previousGuesses) {
        char guess;

        while (indexLettersList < lettersList.length()) {
            guess = lettersList.charAt(indexLettersList);
            indexLettersList++;


            if (!previousGuessesSet.contains(guess) && !previousGuesses.contains("" + guess)) {
                previousGuessesSet.add(guess);
                return guess;
            }
        }
        return '\0';
    }

    /**
     * set AI-II ID
     * @return
     */
    @Override
    public String playerId() {
        return "AI_PlayerTwo";
    }
    /**
     * Clear guessed letters to reset for a new game
     */

    @Override
    public void reset() {
        indexLettersList = 0;
        previousGuessesSet.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AI_PlayerTwo that = (AI_PlayerTwo) o;
        return indexLettersList == that.indexLettersList && Objects.equals(lettersList, that.lettersList) && Objects.equals(previousGuessesSet, that.previousGuessesSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lettersList, indexLettersList, previousGuessesSet);
    }

    @Override
    public String toString() {
        return "AI_PlayerTwo{" +
                "lettersList='" + lettersList + '\'' +
                ", indexLettersList=" + indexLettersList +
                ", previousGuessesSet=" + previousGuessesSet +
                '}';
    }
}
