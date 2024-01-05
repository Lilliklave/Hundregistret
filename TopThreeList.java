import java.util.*;

public class TopThreeList {

    private ArrayList<Scores> scoreBoard = new ArrayList<>();

    public TopThreeList() {
        this.scoreBoard = new ArrayList<Scores>();
    }

    public int addScore(Score s){
        if(scoreBoard.size() < 3 ){
            scoreBoard.size(s);
            
        }else {
            Score lowest = Collections.min(scoreBoard);
            
            if (s.compareTo(lowest) > 0) {
                
                scoreBoard.remove(lowest);
                scoreBoard.add(s);
            }

        }


    }

    public TopThreeList getScore(){

    }


}
