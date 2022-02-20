import java.util.Vector;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Date;

public class Lane extends Thread implements PinsetterObserver {	
	private Pinsetter setter;
	private Vector subscribers;
	private int ball;
	private int bowlIndex;
	private boolean tenthFrameStrike;
	private boolean canThrowAgain;
	private Bowler currentThrower;			// = the thrower who just took a throw
	private GamePlay gamePlay;

	/** Lane()
	 * 
	 * Constructs a new lane and starts its thread
	 * 
	 * @pre none
	 * @post a new lane has been created and its thered is executing
	 */
	public Lane() { 
		setter = new Pinsetter();
		subscribers = new Vector();
		setter.subscribe( this );
		gamePlay = new GamePlay();
		this.start();
	}

	/** run()
	 * 
	 * entry point for execution of this lane 
	 */
	public void run() {

		while (true) {
//				// we have a party on this lane,
//								// so next bower can take a throw

			if(gamePlay.isPartyAssigned() && !gamePlay.isGameFinished()){
				gamePlay.checkGameHaulted();

				if (gamePlay.bowlerIterator.hasNext()) {
					currentThrower = (Bowler)gamePlay.bowlerIterator.next();

					canThrowAgain = true;
					tenthFrameStrike = false;
					ball = 0;
					while (canThrowAgain) {
						setter.ballThrown();		// simulate the thrower's ball hiting
						ball++;
					}
					if (gamePlay.frameNumber == 9){
						gamePlay.lastFramePlay(bowlIndex, currentThrower);
					}

					setter.reset();
					bowlIndex++;

				} else {
					gamePlay.frameNumber++;
					gamePlay.resetBowlerIterator();
					bowlIndex = 0;
					if (gamePlay.frameNumber > 9) {
						gamePlay.gameFinished = true;
						gamePlay.gameNumber++;
					}
				}
			} else if ( gamePlay.isPartyAssigned() && gamePlay.isGameFinished()  /*partyAssigned && gameFinished*/) {
				int result = gamePlay.gameFinished();

				if (result == 0) {// no, dont want to play another game
					Vector printVector;
					EndGameReport egr = new EndGameReport( ((Bowler)gamePlay.getParty().getMembers().get(0)).getNickName() + "'s Party", gamePlay.getParty());
					printVector = egr.getResult();
					Iterator scoreIt = gamePlay.getParty().getMembers().iterator();
					gamePlay.resetParty();
					publish(lanePublish());

					int myIndex = 0;
					while (scoreIt.hasNext()){
						Bowler thisBowler = (Bowler)scoreIt.next();
						//ScoreReport sr = new ScoreReport( thisBowler, finalScores[myIndex++], gameNumber );
						ScoreReport sr = new ScoreReport( thisBowler, gamePlay.getFinalScores()[myIndex++], gamePlay.gameNumber );
						sr.sendEmail(thisBowler.getEmail());
						Iterator printIt = printVector.iterator();
						while (printIt.hasNext()){
							if (thisBowler.getNick() == (String)printIt.next()){
								System.out.println("Printing " + thisBowler.getNick());
								sr.sendPrintout();
							}
						}
					}
				}
			}
			gamePlay.goToSleep();
		}
	}

	/** recievePinsetterEvent()
	 * 
	 * recieves the thrown event from the pinsetter
	 *
	 * @pre none
	 * @post the event has been acted upon if desiered
	 * 
	 * @param pe 		The pinsetter event that has been received.
	 */
	public void receivePinsetterEvent(PinsetterEvent pe) {
		
			if (pe.pinsDownOnThisThrow() >=  0) {			// this is a real throw
				gamePlay.markScore(currentThrower, gamePlay.frameNumber + 1, pe.getThrowNumber(), pe.pinsDownOnThisThrow(), bowlIndex);
				publish( lanePublish() );
				// next logic handles the ?: what conditions dont allow them another throw?
				// handle the case of 10th frame first
				if (gamePlay.frameNumber == 9) {
					if (pe.totalPinsDown() == 10) {
						setter.resetPins();
						if(pe.getThrowNumber() == 1) {
							tenthFrameStrike = true;
						}
					}
				
					if ((pe.totalPinsDown() != 10) && (pe.getThrowNumber() == 2 && tenthFrameStrike == false)) {
						canThrowAgain = false;
						//publish( lanePublish() );
					}
				
					if (pe.getThrowNumber() == 3) {
						canThrowAgain = false;
						//publish( lanePublish() );
					}
				} else { // its not the 10th frame
			
					if (pe.pinsDownOnThisThrow() == 10) {		// threw a strike
						canThrowAgain = false;
						//publish( lanePublish() );
					} else if (pe.getThrowNumber() == 2) {
						canThrowAgain = false;
						//publish( lanePublish() );
					} else if (pe.getThrowNumber() == 3)  
						System.out.println("I'm here...");
				}
			} else {								//  this is not a real throw, probably a reset
			}
	}
	
	public void assignParty( Party theParty ) {
		gamePlay.assignParty(theParty);
	}

	/** lanePublish()
	 *
	 * Method that creates and returns a newly created laneEvent
	 * 
	 * @return		The new lane event
	 */
	private LaneEvent lanePublish(  ) {
		laneEventBallInfo laneEventBall = new laneEventBallInfo(gamePlay.getParty(),gamePlay.getCumulScores(), gamePlay.getScores() );
		LaneEvent laneEvent = new LaneEvent(bowlIndex,gamePlay.frameNumber+1, ball, laneEventBall, currentThrower, gamePlay.gameIsHalted);

		return laneEvent;
	}

	/** isPartyAssigned()
	 * 
	 * checks if a party is assigned to this lane
	 * 
	 * @return true if party assigned, false otherwise
	 */
	public boolean isPartyAssigned() {
		return gamePlay.isPartyAssigned();
	}
	
	/** subscribe
	 * 
	 * Method that will add a subscriber
	 * 
	 * @param subscribe	Observer that is to be added
	 */

	public void subscribe( LaneObserver adding ) {
		subscribers.add( adding );
	}

	/** unsubscribe
	 * 
	 * Method that unsubscribes an observer from this object
	 * 
	 * @param removing	The observer to be removed
	 */
	
	public void unsubscribe( LaneObserver removing ) {
		subscribers.remove( removing );
	}

	/** publish
	 *
	 * Method that publishes an event to subscribers
	 * 
	 * @param event	Event that is to be published
	 */

	public void publish( LaneEvent event ) {
		if( subscribers.size() > 0 ) {
			Iterator eventIterator = subscribers.iterator();

			while ( eventIterator.hasNext() ) {
				( (LaneObserver) eventIterator.next()).receiveLaneEvent( event );
			}
		}
	}



	/**
	 * Accessor to get this Lane's pinsetter
	 * 
	 * @return		A reference to this lane's pinsetter
	 */

	public Pinsetter getPinsetter() {
		return setter;	
	}

	/**
	 * Pause the execution of this game
	 */
	public void pauseGame() {
		gamePlay.gameIsHalted = true;
		publish(lanePublish());
	}
	
	/**
	 * Resume the execution of this game
	 */
	public void unPauseGame() {
		gamePlay.gameIsHalted = false;
		publish(lanePublish());
	}

}
