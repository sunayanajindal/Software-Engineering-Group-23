import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

public class ControlDeskPartyQueuePanel implements ControlDeskObserver{
    private JPanel partyPanel;
    private JList partyList;
    public ControlDeskPartyQueuePanel(ControlDesk controlDesk){
        partyPanel = new JPanel();
        partyPanel.setLayout(new FlowLayout());
        partyPanel.setBorder(new TitledBorder("Party Queue"));

        Vector empty = new Vector();
        empty.add("(Empty)");

        partyList = new JList(empty);
        partyList.setFixedCellWidth(120);
        partyList.setVisibleRowCount(10);
        JScrollPane partyPane = new JScrollPane(partyList);
        partyPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        partyPanel.add(partyPane);
        controlDesk.getSubscriber().subscribe(this);
    }

    public JPanel getPartyPanel() {
        return partyPanel;
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

