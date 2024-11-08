import java.util.ArrayList;
//concrete method
public class WheelOfFortuneAIGame extends WheelOfFortune {
    private ArrayList<WheelOfFortunePlayer> AIPlayers;
    private int playerIndex;

    /**
     * create data member
     */
    public WheelOfFortuneAIGame() {
        this(new ArrayList<>());
        AIPlayers.add(new AI_PlayerTwo());
        AIPlayers.add(new AI_PlayerOne());
        AIPlayers.add(new AI_PlayerThree());
    }

    public WheelOfFortuneAIGame(WheelOfFortunePlayer player) {
        this();
        AIPlayers.add(player);
    }

    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> players) {
        AIPlayers = players;
        this.playerIndex = 0;
    }

    /**
     * make abs method to concrete
     * @param previousGuesses
     * @return
     */
    @Override
    public String getGuess(String previousGuesses) {
        char nextGuess = AIPlayers.get(playerIndex).nextGuess(previousGuesses);
        return String.valueOf(nextGuess);
    }

    /**
     * AIs do not enter ID
     * @return record
     */
    @Override
    public GameRecord play() {

        GameRecord record = new GameRecord();
        record.setPlayerId(AIPlayers.get(playerIndex).playerId());
        record.setScore(playingProgress());
        return record;
    }

    /**
     * different method for AI to keep guess
     * @return
     */
    @Override
    public boolean playNext() {
        if (phraseList.size() == usedPhraseIndex.size()) {
            if (playerIndex >= AIPlayers.size() - 1) {
                return false;
            } else {
                playerIndex++;
                usedPhraseIndex.clear();
            }
        }


        AIPlayers.get(playerIndex).reset();
        return true;
    }

    /**
     * Main for AI
     * @param args
     */
    public static void main(String[] args) {
        // Initialize the players
        ArrayList<WheelOfFortunePlayer> players = new ArrayList<>();
        players.add(new AI_PlayerOne());
        players.add(new AI_PlayerTwo());
        players.add(new AI_PlayerThree());

        WheelOfFortuneAIGame wheelOfFortuneAIGame = new WheelOfFortuneAIGame(players);

        AllGamesRecord record = wheelOfFortuneAIGame.playAll();

        System.out.println(record);
        for (WheelOfFortunePlayer player : players) {
            System.out.println(player.playerId() + " average score: " + record.average(player.playerId()));
            System.out.println(player.playerId() + " Highest score: " + record.highGameList(player.playerId(),1));
        }
    }
}