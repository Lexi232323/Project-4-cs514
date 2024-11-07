import java.util.Scanner;
//concrete class
public class WheelOfFortuneUserGame extends WheelOfFortune implements WheelOfFortunePlayer{
    private Scanner scanner=new Scanner(System.in);
    private String playId;
    private StringBuilder previousGuesses= new StringBuilder();
    @Override
    public char nextGuess(String previousGuesses){
        String guesses=getGuess(previousGuesses);
        return guesses.charAt(0);
    }
    @Override
    public String playerId(){
        return play().getPlayerId();
    }
    @Override
    public void reset(){
        previousGuesses.setLength(0);
    }
    @Override
    public String getGuess(String previousGuesses){
        String input;
        System.out.println("Please enter your guess: ");
        do{
            input=scanner.nextLine();
            if (input.length() != 1||!Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Please enter a SINGLE LETTER");
                continue;
            }
            char guess=input.charAt(0);
            if(previousGuesses.toLowerCase().indexOf(Character.toLowerCase(guess))!=-1){
                System.out.println("Try a new guess: ");
            }
            else {
                break;
            }
        }while(true);
        return input;
    }
    @Override
    protected GameRecord play(){
        System.out.println("Please enter your Game ID: ");
        playId =scanner.nextLine();
        GameRecord record= new GameRecord();
        record.setPlayerId(playId);
        int score=playingProgress();
        record.setScore(score);
        System.out.println("Game completed with score "+score);
        return record;
    }

    public static void main(String [] args) {
        WheelOfFortuneUserGame wofUserGame = new WheelOfFortuneUserGame();
        AllGamesRecord record = wofUserGame.playAll();
        System.out.println(record);
    }
}
