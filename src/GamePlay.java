import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import static java.lang.Thread.sleep;

public class GamePlay {
    private Party party;
    public boolean gameIsHalted;
    private boolean partyAssigned;
    public boolean gameFinished;
    private int[] curScores;
    private int[][] cumulScores;
    private HashMap scores;
    public int frameNumber;
    public Iterator bowlerIterator;
    private int[][] finalScores;
    public int gameNumber;

    public GamePlay(){
        gameIsHalted = false;
        partyAssigned = false;
        gameFinished = false;
        scores = new HashMap();
        gameNumber = 0;
    }

    public Party getParty(){
        return party;
    }

    public int[][] getFinalScores() {
        return finalScores;
    }

    public HashMap getScores() {
        return scores;
    }

    public int[] getCurScores() {
        return curScores;
    }

    public int[][] getCumulScores() {
        return cumulScores;
    }

    public void goToSleep(){
        try {
            sleep(10);
        }
        catch (Exception e) {}
    }

    public boolean isPartyAssigned() {
        return partyAssigned;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void checkGameHaulted(){
        while(gameIsHalted){
            goToSleep();
        }
    }

    public void lastFramePlay(int bowlIndex, Bowler currentThrower){
        finalScores[bowlIndex][gameNumber] = cumulScores[bowlIndex][9];
        try{
            Date date = new Date();
            String dateString = "" + date.getHours() + ":" + date.getMinutes() + " " + date.getMonth() + "/" + date.getDay() + "/" + (date.getYear() + 1900);
            ScoreHistoryFile.addScore(currentThrower.getNick(), dateString, new Integer(cumulScores[bowlIndex][9]).toString());
        } catch (Exception e) {System.err.println("Exception in addScore. "+ e );}

    }

    /** assignParty()
     *
     * assigns a party to this lane
     *
     * @pre none
     * @post the party has been assigned to the lane
     *
     * @param theParty		Party to be assigned
     */
    public void assignParty( Party theParty ) {
        party = theParty;
        resetBowlerIterator();
        partyAssigned = true;

        curScores = new int[party.getMembers().size()];
        cumulScores = new int[party.getMembers().size()][10];
        finalScores = new int[party.getMembers().size()][128]; //Hardcoding a max of 128 games, bite me.
        gameNumber = 0;

        resetScores();
    }

    /** resetScores()
     *
     * resets the scoring mechanism, must be called before scoring starts
     *
     * @pre the party has been assigned
     * @post scoring system is initialized
     */
    public void resetScores() {
        Iterator bowlIt = (party.getMembers()).iterator();

        while ( bowlIt.hasNext() ) {
            int[] toPut = new int[25];
            for ( int i = 0; i != 25; i++){
                toPut[i] = -1;
            }
            scores.put( bowlIt.next(), toPut );
        }
        gameFinished = false;
        frameNumber = 0;
    }

    /** resetBowlerIterator()
     *
     * sets the current bower iterator back to the first bowler
     *
     * @pre the party as been assigned
     * @post the iterator points to the first bowler in the party
     */
    public void resetBowlerIterator() {
        bowlerIterator = (party.getMembers()).iterator();
    }

    public void YES_PlayAgain(){
        resetScores();
        resetBowlerIterator();

    }

    public int gameFinished(){
        EndGamePrompt egp = new EndGamePrompt( ((Bowler) party.getMembers().get(0)).getNickName() + "'s Party" );
        int result = egp.getResult();
        egp.distroy();
        egp = null;
        System.out.println("result was: " + result);
        if (result==1){
            YES_PlayAgain();
            return 1;
        }
        else{
            //NO_PlayAgain();
            return 0;
        }
    }

    public void resetParty(){
        partyAssigned = false;
        party = null;
        partyAssigned = false;
    }

    /** markScore()
     *
     * Method that marks a bowlers score on the board.
     *
     * @param Cur		The current bowler
     * @param frame	The frame that bowler is on
     * @param ball		The ball the bowler is on
     * @param score	The bowler's score
     */
    public void markScore( Bowler Cur, int frame, int ball, int score, int bowlIndex ){
        int[] curScore;
        int index =  ( (frame - 1) * 2 + ball);
        //HashMap scores = gamePlay.getScores();
        curScore = (int[]) scores.get(Cur);


        curScore[ index - 1] = score;
        scores.put(Cur, curScore);
        //getScore( Cur, frame );
        cumulScores[bowlIndex] = new CalculateScore(curScore,index).getCumulScores();

        //publish( lanePublish() );
    }

}
