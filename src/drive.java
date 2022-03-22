import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.io.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class drive extends Frame  {
	private JFrame win;
//	private Label lane_size, max_patron_count;
//	private Button OK, Help;
//	private TextField lane_size_field, max_patron_field;
//	JPanel panel = new JPanel();

	public drive(){
		GameHomePage home = new GameHomePage();
		win = home.getWin();

	}

	public static void main(String[] args) {
		new drive();
		int numLanes = 3;
		int maxPatronsPerParty=5;
		//GameHomePage home = new GameHomePage();

//		System.out.println(home.arr[0]);
//		System.out.println(home.arr[1]);
//		ControlDesk controlDesk;
//		controlDesk = new ControlDesk( numLanes );
//
//		controlDesk.start();
//		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);

	}
}
