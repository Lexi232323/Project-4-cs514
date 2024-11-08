public interface WheelOfFortunePlayer {
    /**
     * get the next guess from the player
     * @param previousGuesses
     * @return char
     */
    char nextGuess(String previousGuesses);

    /**
     * an id for the player
     * @return
     */
    String playerId();

    /**
     * reset the player to start a new game
     */
    void reset();
}
