import java.util.Vector;
import java.io.*;

public class drive {

	public static void main(String[] args) {

		int numLanes = 3;
		int maxPatronsPerParty=5;
		ControlDesk controlDesk;
		controlDesk = new ControlDesk( numLanes );
		controlDesk.start();
		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
		//controlDesk.subscribe( cdv );

	}
}
