import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class EndGameReportMemberPanel {
    private JList memberList;
    JPanel partyPanel;
    public EndGameReportMemberPanel(Party party){
        partyPanel = new JPanel();
        partyPanel.setLayout(new FlowLayout());
        partyPanel.setBorder(new TitledBorder("Party Members"));

        Vector myVector = new Vector();
        Iterator iter = (party.getMembers()).iterator();
        while (iter.hasNext()){
            myVector.add( ((Bowler)iter.next()).getNick() );
        }
        memberList = new JList(myVector);
        memberList.setFixedCellWidth(120);
        memberList.setVisibleRowCount(5);
        //memberList.addListSelectionListener(this);
        JScrollPane partyPane = new JScrollPane(memberList);
        //partyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        partyPanel.add(partyPane);

        partyPanel.add( memberList );
    }

    public JList getMemberList() {
        return memberList;
    }

    public JPanel getPartyPanel() {
        return partyPanel;
    }
}
