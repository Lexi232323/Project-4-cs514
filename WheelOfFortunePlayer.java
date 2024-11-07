public interface WheelOfFortunePlayer {
    //get the next guess from the player
    char nextGuess(String previousGuesses);
    //an id for the player
    String playerId();
    //reset the player to start a new game
    void reset();
}
