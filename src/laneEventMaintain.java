public class laneEventMaintain {
    boolean mechProb;
    Bowler bowler;
    public laneEventMaintain(Bowler theBowler,boolean mechProblem) {
        bowler = theBowler;
        mechProb = mechProblem;
    }
    public Bowler getBowler() {
        return bowler;
    }
    public boolean isMechanicalProblem() {
        return mechProb;
    }

}
