public class LaneEvent {

	int ball;
	int index;
	int frameNum;
	laneEventBallInfo laneEventBall;
	Bowler bowler;
	boolean mechProb;
	public LaneEvent(int theIndex, int theFrameNum, int theBall,laneEventBallInfo information, Bowler theBowler, boolean mechProblem) {
		index = theIndex;
		frameNum = theFrameNum;
		ball = theBall;
		laneEventBall = information;
		bowler = theBowler;
		mechProb = mechProblem;
	}

	public int getFrameNum() {
		return frameNum;
	}

	public int getIndex() {
		return index;
	}

	public int getBall( ) {
		return ball;
	}

	public laneEventBallInfo getLaneEventBall() {
		return laneEventBall;
	}

	public Bowler getBowler() {
		return bowler;
	}
		public boolean isMechanicalProblem() {
		return mechProb;
	}
};

