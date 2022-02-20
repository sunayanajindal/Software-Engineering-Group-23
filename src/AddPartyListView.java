import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

public class AddPartyListView {
    JPanel partyPanel;
    JList partyList;
    Vector party;
    public AddPartyListView(){
        partyPanel = new JPanel();
        partyPanel.setLayout(new FlowLayout());
        partyPanel.setBorder(new TitledBorder("Your Party"));

        party = new Vector();
        Vector empty = new Vector();
        empty.add("(Empty)");

        partyList = new JList(empty);
        partyList.setFixedCellWidth(120);
        partyList.setVisibleRowCount(5);
        //partyList.addListSelectionListener(this);
        JScrollPane partyPane = new JScrollPane(partyList);
        //        partyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        partyPanel.add(partyPane);
    }

    public JList getPartyList() {
        return partyList;
    }

    public Vector getParty() {
        return party;
    }

    public JPanel getPartyPanel() {
        return partyPanel;
    }
}
