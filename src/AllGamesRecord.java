import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * collect all score
 */
public class AllGamesRecord {
    private ArrayList<GameRecord>listGameRecord= new ArrayList<>();

    /**
     * adds a GameRecord to the AllGamesRecord
     * @param record
     */
    public void add(GameRecord record){
        this.listGameRecord.add(record);
    }

    /**
     * returns the average score for all games added to the record
     * @return
     */
    public double average(){
        if(listGameRecord.isEmpty()) return 0;
        double sum=0;
        for(GameRecord record: this.listGameRecord){
            sum+= record.getScore();
        }
        return sum/ listGameRecord.size();
    }

    /**
     * returns the average score for all games of a particular player
     * @param playId
     * @return
     */
    public double average(String playId){
        double sum=0;
        int count=0;
        for(GameRecord record: this.listGameRecord){
            if(record.getPlayerId().equals(playId)){
                sum+=record.getScore();
                count++;
            }
        }
        return count>0? sum/count:0;
    }

    /**
     * returns a sorted list of the top n scores including player and score.
     * This method should use the Collections class to sort the game instances.
     * @param n
     * @return
     */
    public ArrayList<GameRecord> highGameList(int n) {
        ArrayList<GameRecord> topList = new ArrayList<>(this.listGameRecord);

        topList.sort(Comparator.comparingDouble(GameRecord::getScore).reversed());


        if (topList.size() < n) {
            n = topList.size();
        }
        return new ArrayList<>(topList.subList(0, n));
    }

    /**
     * returns a sorted list of the top n scores for the specified player.
     * This method should use the Collections class to sort the game instances.
     * @param playId
     * @param n
     * @return
     */
    public ArrayList<GameRecord> highGameList(String playId, int n) {
        ArrayList<GameRecord> playerRecords = new ArrayList<>();
        for (GameRecord record : this.listGameRecord) {
            if (record.getPlayerId().equals(playId)) {
                playerRecords.add(record);
            }
        }
        playerRecords.sort(Comparator.comparingDouble(GameRecord::getScore).reversed());
        if (playerRecords.size() < n) {
            n = playerRecords.size();
        }
        return new ArrayList<>(playerRecords.subList(0, n));
    }
    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null||getClass()!=o.getClass() )return false;
        AllGamesRecord that=(AllGamesRecord) o;
        return Objects.equals(listGameRecord,that.listGameRecord);
    }
    @Override
    public int hashCode(){
        return Objects.hash(listGameRecord);
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("the highest two scores: ").append(highGameList(2)).append("\n");
        sb.append("the average score: ").append(average()).append("\n");
        return sb.toString();
    }
}
