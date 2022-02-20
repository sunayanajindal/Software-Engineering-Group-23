import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

public class AddPartyElements {
    JButton button;
    JFrame win;
    public AddPartyElements(){
        win = new JFrame("Add Party");
        win.getContentPane().setLayout(new BorderLayout());
        ((JPanel) win.getContentPane()).setOpaque(false);
    }

    public JFrame getWin() {
        return win;
    }

    public JPanel getButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        Insets buttonMargin = new Insets(4, 4, 4, 4);
        return buttonPanel;
    }

    public JButton addButton(String button_text, JPanel buttonPanel){
        button = new JButton(button_text);
        JPanel thisButtonPanel = new JPanel();
        thisButtonPanel.setLayout(new FlowLayout());
        thisButtonPanel.add(button);
        buttonPanel.add(thisButtonPanel);
        return button;
    }

    public void CenterWindowOnScreen(JFrame win){
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.show();
    }

//    public JPanel getPartyPanel(){
//        partyPanel = new JPanel();
//        partyPanel.setLayout(new FlowLayout());
//        partyPanel.setBorder(new TitledBorder("Your Party"));
//
//        party = new Vector();
//        Vector empty = new Vector();
//        empty.add("(Empty)");
//
//        partyList = new JList(empty);
//        partyList.setFixedCellWidth(120);
//        partyList.setVisibleRowCount(5);
//        //partyList.addListSelectionListener(this);
//        JScrollPane partyPane = new JScrollPane(partyList);
//        //        partyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        partyPanel.add(partyPane);
//        return partyPanel;
//    }
//
//    public JList getPartyList() {
//        return partyList;
//    }
//
//    public Vector getParty() {
//        return party;
//    }
}
