import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.io.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class drive extends Frame  {
	private JFrame win;

	public drive(){
		GameHomePage home = new GameHomePage();
		win = home.getWin();

	}

	public static void main(String[] args) {
		new drive();
		int numLanes = 3;
		int maxPatronsPerParty=5;


	}
}
