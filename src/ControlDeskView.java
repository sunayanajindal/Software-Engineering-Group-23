/* ControlDeskView.java
 *
 *  Version:
 *			$Id$
 * 
 *  Revisions:
 * 		$Log$
 * 
 */

/**
 * Class for representation of the control desk
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;

public class ControlDeskView implements ActionListener, ControlDeskObserver {

	//private JButton addParty, finished, assign;
	private JFrame win;
	private JList partyList;
	
	/** The maximum  number of members in a party */
	private int maxMembers;
	
	private ControlDesk controlDesk;

	/**
	 * Displays a GUI representation of the ControlDesk
	 *
	 */

	public ControlDeskView(ControlDesk controlDesk, int maxMembers) {

		this.controlDesk = controlDesk;
		this.maxMembers = maxMembers;
		int numLanes = controlDesk.getNumLanes();

		win = new JFrame("Control Desk");
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new BorderLayout());
		// Controls Panel
		JPanel controlsPanel=new ControlDeskControlsPanel(this).getControlsPanel();

		// Lane Status Panel
		JPanel laneStatusPanel=new ControlDeskLaneStatusPanel(controlDesk).getLaneStatusPanel();

		// Party Queue Panel
		JPanel partyList = new ControlDeskPartyQueuePanel(controlDesk).getPartyPanel();

		// Clean up main panel
		colPanel.add(controlsPanel, "East");
		colPanel.add(laneStatusPanel, "Center");
		colPanel.add(partyList, "West");

		win.getContentPane().add("Center", colPanel);

		win.pack();

		/* Close program when this window closes */
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Center Window on Screen
		Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.setLocation(
			((screenSize.width) / 2) - ((win.getSize().width) / 2),
			((screenSize.height) / 2) - ((win.getSize().height) / 2));
		win.show();

	}

	/**
	 * Handler for actionEvents
	 *
	 * @param e	the ActionEvent that triggered the handler
	 *
	 */

//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource().equals(addParty)) {
//			System.out.println("add party clicked");
//			AddPartyView addPartyWin = new AddPartyView(this, maxMembers);
//		}
//		if (e.getSource().equals(assign)) {
//			controlDesk.assignLane();
//		}
//		if (e.getSource().equals(finished)) {
//			System.out.println("finished clicked");
//			win.hide();
//			System.exit(0);
//		}
//	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add Party")) {
			AddPartyView addPartyWin = new AddPartyView(this, maxMembers);
		}
		else if (e.getActionCommand().equals("Finished")) {
			win.hide();
			System.exit(0);
		}
		else if (e.getActionCommand().equals("Query Results")) {
			SearchableView query = new SearchableView();
		}
	}

	/**
	 * Receive a new party from andPartyView.
	 *
	 * @param addPartyView	the AddPartyView that is providing a new party
	 *
	 */

	public void updateAddParty(AddPartyView addPartyView) {
		controlDesk.addPartyQueue(addPartyView.getParty());
	}

	/**
	 * Receive a broadcast from a ControlDesk
	 *
	 * @param ce	the ControlDeskEvent that triggered the handler
	 *
	 */

	public void receiveControlDeskEvent(ControlDeskEvent ce) {
		partyList.setListData(((Vector) ce.getPartyQueue()));
	}
}
