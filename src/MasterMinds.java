import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
/**
*
 *
 *
 *MasterMinds game with parent guessing game
 */
public class MasterMinds extends GuessingGame {
    static String colors = "RGBYOP";
    private Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MasterMinds mastermind = new MasterMinds();
        AllGamesRecord allGamesRecord = mastermind.playAll();
        System.out.println(allGamesRecord);
    }

    /**
     *
     *
     * @return score
     */
   @Override
    protected int playingProgress() {
        int guessCount = 0;
        int exactCount;
        int partialCount;
        String secretCode = generateHiddenPhrase(colors);

        System.out.println("Welcome to Mastermind! The secret code is hidden.");
        System.out.println(secretCode);

        while (guessCount < 5) {
            StringBuilder secretCodeForCrossingOut = new StringBuilder(secretCode);
            String guessFromUser = getGuess(colors);
            exactCount = computeExacts(secretCodeForCrossingOut, guessFromUser);
            partialCount = computePartials(secretCodeForCrossingOut, guessFromUser);

            guessCount++;

            // Display result of the guess
            System.out.println(exactCount + " exact, " + partialCount + " partial");
            System.out.println("Guesses left: " + (5 - guessCount));

            if (exactCount == 4) {
                System.out.println("Bingo! You win!");
                return 10 + (5 - guessCount);
            } else if (guessCount == 5) {
                System.out.println("You lost! The correct code was: " + secretCode);
                return exactCount;
            }
        }
        return 0;
    }

    /**
     * make abs class concrete
     *
     * @param phrase
     * @return String
     */
    @Override
    public String generateHiddenPhrase(String phrase) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(phrase.charAt(rand.nextInt(phrase.length())));
        }
        return sb.toString();
    }

    /**
     * make abs class concrete
     * @param colors
     * @return guess
     */
    @Override
    public String getGuess(String colors) {
        System.out.println("Enter your guess (4 letters from " + colors + "): ");
        StringBuilder guess = new StringBuilder();
        while (true) {
            guess.append(scanner.nextLine().toUpperCase());
            if (guess.length() == 4 && guess.toString().matches("[RGBYOP]+")) {
                break;
            } else {
                System.out.println("Please enter 4 valid letters from the set: " + colors);
                guess.setLength(0);
            }
        }
        return guess.toString();
    }

    /**
     * new method in this game own
     * @param secretCodeForCrossingOut
     * @param guess
     * @return number of right color in right space
     */
    public int computeExacts(StringBuilder secretCodeForCrossingOut, String guess) {
        int exactCount = 0;
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == secretCodeForCrossingOut.charAt(i)) {
                exactCount++;
                secretCodeForCrossingOut.setCharAt(i, 'x');
            }
        }
        return exactCount;
    }

    /**
     * new method in this game own
     * @param secretCodeForCrossingOut
     * @param guess
     * @return number of right color
     */
    public int computePartials(StringBuilder secretCodeForCrossingOut, String guess) {
        int partialCount = 0;
        int[] secretCodeFreq = new int[256];
        int[] guessFreq = new int[256];

        for (int i = 0; i < 4; i++) {
            if (secretCodeForCrossingOut.charAt(i) != 'x' && guess.charAt(i) != secretCodeForCrossingOut.charAt(i)) {
                secretCodeFreq[secretCodeForCrossingOut.charAt(i)]++;
                guessFreq[guess.charAt(i)]++;
            }
        }

        for (int i = 0; i < 256; i++) {
            partialCount += Math.min(secretCodeFreq[i], guessFreq[i]);
        }
        return partialCount;
    }

    @Override
    public String toString() {
        return "MasterMinds{" +
                "scanner=" + scanner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasterMinds that = (MasterMinds) o;
        return Objects.equals(scanner, that.scanner);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(scanner);
    }
}
