/* AddPartyView.java
 *
 *  Version:
 * 		 $Id$
 * 
 *  Revisions:
 * 		$Log: AddPartyView.java,v $
 * 		Revision 1.7  2003/02/20 02:05:53  ???
 * 		Fixed addPatron so that duplicates won't be created.
 * 		
 * 		Revision 1.6  2003/02/09 20:52:46  ???
 * 		Added comments.
 * 		
 * 		Revision 1.5  2003/02/02 17:42:09  ???
 * 		Made updates to migrate to observer model.
 * 		
 * 		Revision 1.4  2003/02/02 16:29:52  ???
 * 		Added ControlDeskEvent and ControlDeskObserver. Updated Queue to allow access to Vector so that contents could be viewed without destroying. Implemented observer model for most of ControlDesk.
 * 		
 * 
 */

/**
 * Class for GUI components need to add a party
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

/**
 * Constructor for GUI used to Add Parties to the waiting party queue.
 *  
 */

public class AddPartyView implements ActionListener, ListSelectionListener {

	private int maxSize;

	private JFrame win;
	private JButton addPatron, newPatron, remPatron, finished;
	private JList partyList, allBowlers;
	private Vector party, bowlerdb;
	private Integer lock;
    private JPanel bowlerPanel;
	private ControlDeskView controlDesk;

	private String selectedNick, selectedMember;

	public AddPartyView(ControlDeskView controlDesk, int max) {

		this.controlDesk = controlDesk;
		maxSize = max;
		AddPartyElements Elements = new AddPartyElements("Add Party");

		win = Elements.getWin();

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new GridLayout(1, 3));


		AddPartyListView PartyListElements = new AddPartyListView();
		// Party Panel
		JPanel partyPanel = PartyListElements.getPartyPanel();
		partyList = PartyListElements.getPartyList();
		partyList.addListSelectionListener(this);
		party = PartyListElements.getParty();


		// Bowler Database
		AddPartyBowlerView BowlerView = new AddPartyBowlerView();
		bowlerPanel = BowlerView.getBowlerPanel();
		allBowlers = BowlerView.getAllBowlers();
		allBowlers.addListSelectionListener(this);


		// Button Panel
		JPanel buttonPanel = Elements.getButtonPanel();

		addPatron = Elements.addButton("Add to Party", buttonPanel);
		remPatron = Elements.addButton("Remove Member", buttonPanel);
		newPatron = Elements.addButton("New Patron", buttonPanel);
		finished  = Elements.addButton("Finished", buttonPanel);

		addPatron.addActionListener(this);
		remPatron.addActionListener(this);
		newPatron.addActionListener(this);
		finished.addActionListener(this);

		// Clean up main panel
		colPanel.add(partyPanel);
		colPanel.add(bowlerPanel);
		colPanel.add(buttonPanel);

		win.getContentPane().add("Center", colPanel);

		win.pack();

		// Center Window on Screen
		Elements.CenterWindowOnScreen(win);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addPatron)) {
			if (selectedNick != null && party.size() < maxSize) {
				if (party.contains(selectedNick)) {
					System.err.println("Member already in Party");
					JOptionPane.showMessageDialog(win,"Member already in Party!!!");
				} else {
					party.add(selectedNick);
					partyList.setListData(party);
				}
			}
			else if (selectedNick != null && party.size() == maxSize){
				JOptionPane.showMessageDialog(win,"Cannot select more members. Max limit reached!!!");
			}
			else if (selectedNick == null ){
				JOptionPane.showMessageDialog(win,"You did not select any player!!!");
			}
		}
		if (e.getSource().equals(remPatron)) {
			if (selectedMember != null) {
				party.removeElement(selectedMember);
				partyList.setListData(party);
			}
			else{
				if(party.size() == 0){
					JOptionPane.showMessageDialog(win,"Party has no member to remove!!!");
				}
				else{
					JOptionPane.showMessageDialog(win,"Please select a member to remove!!!");
				}

			}
		}
		if (e.getSource().equals(newPatron)) {
			NewPatronView newPatron = new NewPatronView( this );
		}
		if (e.getSource().equals(finished)) {
			if ( party != null && party.size() > 0) {
				controlDesk.updateAddParty( this );
			}
			else if ( party != null && party.size() == 0 ){
				JOptionPane.showMessageDialog(win,"You did not add any player to party. Come back again if you want to play!!!!!");
			}
			win.hide();
		}

	}

/**
 * Handler for List actions
 * @param e the ListActionEvent that triggered the handler
 */
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource().equals(allBowlers)) {
			selectedNick =
				((String) ((JList) e.getSource()).getSelectedValue());
		}
		if (e.getSource().equals(partyList)) {
			selectedMember =
				((String) ((JList) e.getSource()).getSelectedValue());
		}
	}

/**
 * Accessor for Party
 */
	public Vector getNames() {
		return party;
	}

/**
 * Called by NewPatronView to notify AddPartyView to update
 * 
 * @param newPatron the NewPatronView that called this method
 */
	public void updateNewPatron(NewPatronView newPatron) {
		try {
			Bowler checkBowler = BowlerFile.getBowlerInfo( newPatron.getNick() );
			if ( checkBowler == null ) {
				BowlerFile.putBowlerInfo(
					newPatron.getNick(),
					newPatron.getFull(),
					newPatron.getEmail());
				bowlerdb = new Vector(BowlerFile.getBowlers());
				allBowlers.setListData(bowlerdb);
				party.add(newPatron.getNick());
				partyList.setListData(party);
			} else {
				System.err.println( "A Bowler with that name already exists." );
			}
		} catch (Exception e2) {
			System.err.println("File I/O Error");
		}
	}

/**
 * Accessor for Party
 */
	public Vector getParty() {
		return party;
	}

}
