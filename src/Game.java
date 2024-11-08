//class Game will encapsulate the code for looping through a set of games and recording the results.
public abstract class Game {
    /**
     * a method that plays a set of games and records and returns
     * an AllGamesRecord object for the set.
     *
     * @return
     */
    public AllGamesRecord playAll(){
        AllGamesRecord aGR= new AllGamesRecord();
        while(playNext()){
            System.out.println("New Game Starts!");
            GameRecord record=play();
            aGR.add(record);
        }
        return aGR;
    }

    /**
     * plays a game and returns a GameRecord
     * @return
     */
    abstract GameRecord play();

    /**
     * asks if the next game should be played (this will be called even to check if the first game should be played). The function should return a boolean.
     * @return
     */
    abstract boolean playNext();

}