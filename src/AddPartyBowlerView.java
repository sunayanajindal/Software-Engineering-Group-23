import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;

public class AddPartyBowlerView {
    private Vector bowlerdb;
    private JList allBowlers;
    private JPanel bowlerPanel;
    private String selectedNick;

    public AddPartyBowlerView(){
        bowlerPanel = new JPanel();
        bowlerPanel.setLayout(new FlowLayout());
        bowlerPanel.setBorder(new TitledBorder("Bowler Database"));

        try {
            bowlerdb = new Vector(BowlerFile.getBowlers());
        } catch (Exception e) {
            System.err.println("File Error");
            bowlerdb = new Vector();
        }
        allBowlers = new JList(bowlerdb);
        allBowlers.setVisibleRowCount(8);
        allBowlers.setFixedCellWidth(120);
        JScrollPane bowlerPane = new JScrollPane(allBowlers);
        bowlerPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //allBowlers.addListSelectionListener(this);
        bowlerPanel.add(bowlerPane);
    }

    public JList getAllBowlers() {
        return allBowlers;
    }

    public JPanel getBowlerPanel() {
        return bowlerPanel;
    }
}
