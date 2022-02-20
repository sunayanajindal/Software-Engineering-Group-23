import java.util.HashMap;

public class laneEventBallInfo {
    private Party party;
    int[][] cumulScore;
    HashMap score;

    public laneEventBallInfo( Party party_, int[][] theCumulScore, HashMap theScore) {
        party = party_;
        cumulScore = theCumulScore;
        score = theScore;
    }
    public int[][] getCumulScore() {
        return cumulScore;
    }

    public HashMap getScore() {
        return score;
    }

    public Party getParty() {
        return party;
    }

}
