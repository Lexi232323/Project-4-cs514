import java.util.Objects;
//GameRecord keeps track of the score (integer) and player id (String) for a single play of a game. It must implement Comparable and provide a default implementation of compareTo which compares scores.
public class GameRecord implements Comparable<GameRecord>{
    private int score;
    private String playerId;
    // to comparable
    @Override
    public int compareTo(GameRecord other){
        if(this.score> other.score){
            return 1;
        } else if (this.score< other.score) {
            return -1;
        }else{return 0;
        }
    }
    //set and get for private data
    public void setScore(int score){this.score=score;}
    public void setPlayerId(String playerId){
        this.playerId=playerId;
    }
    public int getScore(){
        return this.score;
    }
    public String getPlayerId(){
        return this.playerId;
    }


    @Override
    public String toString() {
        return "GameRecord{" +
                "score=" + score +
                ", playerId='" + playerId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecord that = (GameRecord) o;
        return score == that.score && Objects.equals(playerId, that.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, playerId);
    }
}
