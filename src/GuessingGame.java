import java.util.Objects;
import java.util.Scanner;

public abstract class GuessingGame extends Game{
    private Scanner scanner=new Scanner(System.in);
    // two games all generate but with different ways
    public abstract String generateHiddenPhrase(String phrase);

    /**
     * two games need users id
     * @return game record
     */
    protected GameRecord setupGame(){
        System.out.println("Please enter your Player ID: ");
        String playerId=scanner.nextLine();
        GameRecord record= new GameRecord();
        record.setPlayerId(playerId);
        return record;
    }

    /**
     * two games need to ask users to play next
     * @return
     */
    @Override
    public boolean playNext() {

        System.out.print("Play or not(Y/N): ");
        char input = Character.toLowerCase(scanner.nextLine().charAt(0));
        while (input != 'y' && input != 'n') {
            System.out.print("Please enter Y or N: ");
            input = Character.toLowerCase(scanner.nextLine().charAt(0));
        }
        return input == 'y';
    }

    /**
     * different ways to guess
     * @param phrase
     * @return String guess
     */
    abstract String getGuess(String phrase);
    // same way to add record
    protected GameRecord play(){
        GameRecord record= setupGame();
        int score=playingProgress();
        record.setScore(score);
        System.out.println("Game completed with score "+score);
        return record;
    }

    /**
     * different methods to calculate score
     * @return score
     */
    protected int playingProgress() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuessingGame that = (GuessingGame) o;
        return Objects.equals(scanner, that.scanner);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(scanner);
    }

    @Override
    public String toString() {
        return "GuessingGame{" +
                "scanner=" + scanner +
                '}';
    }
}
