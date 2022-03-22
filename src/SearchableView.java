import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class SearchableView implements ActionListener, ListSelectionListener {

    private int maxSize;

    private JFrame win;
    private JButton highestScore, lowestScore, indAvg, overallAvg, overallHigh, overallLow, bestPlayer, worstPlayer;
    private JList partyList, allBowlers;
    private Vector party, bowlerdb;
    private Integer lock;
    private JPanel bowlerPanel;
    private ControlDeskView controlDesk;
    private String selectedNickName;

    public SearchableView(){
        // Bowler Database
        AddPartyElements Elements = new AddPartyElements("Search Results");

        win = Elements.getWin();
        JPanel colPanel = new JPanel();
        colPanel.setLayout(new GridLayout(1, 3));

        AddPartyBowlerView BowlerView = new AddPartyBowlerView();
        bowlerPanel = BowlerView.getBowlerPanel();
        allBowlers = BowlerView.getAllBowlers();
        allBowlers.addListSelectionListener(this);

        // Party Panel
        JPanel partyPanel = new JPanel();
        partyPanel.setLayout(new FlowLayout());
        partyPanel.setBorder(new TitledBorder("Query Result"));
        Vector empty = new Vector();
        empty.add("(Empty)");

        partyList = new JList(empty);
        partyList.setFixedCellWidth(200);
        partyList.setVisibleRowCount(6);
        partyList.addListSelectionListener(this);
        JScrollPane partyPane = new JScrollPane(partyList);
        //        partyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        partyPanel.add(partyPane);

        // Button Panel
        JPanel buttonPanel = Elements.getButtonPanel();
        //highestScore, lowestScore, indAvg, overallAvg, overallHigh, overallLow, bestPlayer, worstPlayer
        highestScore = Elements.addButton("Highest score", buttonPanel);
        lowestScore = Elements.addButton("Lowest score", buttonPanel);
        indAvg = Elements.addButton("Average score", buttonPanel);
        bestPlayer = Elements.addButton("Best player", buttonPanel);
        worstPlayer = Elements.addButton("Worst player", buttonPanel);
        overallHigh = Elements.addButton("Highest overall score", buttonPanel);
        overallLow  = Elements.addButton("Lowest overall score", buttonPanel);
        overallAvg  = Elements.addButton("Average overall score", buttonPanel);

        highestScore.addActionListener(this);
        lowestScore.addActionListener(this);
        indAvg.addActionListener(this);
        bestPlayer.addActionListener(this);
        worstPlayer.addActionListener(this);
        overallHigh.addActionListener(this);
        overallLow.addActionListener(this);
        overallAvg.addActionListener(this);

        // Clean up main panel
        colPanel.add(bowlerPanel);
        colPanel.add(buttonPanel);
        colPanel.add(partyPanel);

        win.getContentPane().add("Center", colPanel);

        win.pack();

        // Center Window on Screen
        Elements.CenterWindowOnScreen(win);

    }

    public void actionPerformed(ActionEvent e){
        String result;
        if (e.getActionCommand().equals("Highest score")){
            try {
                if (selectedNickName == null){
                    JOptionPane.showMessageDialog(win,"Select player whose stat you want to see!!!");
                }
                else{
                    result = QueryScores.updateStats(selectedNickName, "Highest score");
                    partyList.setListData(getDisplayStat("Highest Score", result));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("Highest overall score")){
            System.out.println("Highest overall score");
            try {
                result = QueryScores.updateStats("-", "overall highest");
                Vector<String> displayQueryResult = new Vector<>();
                displayQueryResult.add("Highest overall score : " + result);
                partyList.setListData(displayQueryResult);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("Lowest score")){
            try {
                if (selectedNickName == null){
                    JOptionPane.showMessageDialog(win,"Select player whose stat you want to see!!!");
                }
                else {
                    result = QueryScores.updateStats(selectedNickName, "Lowest score");
                    partyList.setListData(getDisplayStat("Lowest score", result));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("Lowest overall score")){
            System.out.println("Lowest overall score");
            try {
                result = QueryScores.updateStats("-", "overall lowest");
                Vector<String> displayQueryResult = new Vector<>();
                displayQueryResult.add("Lowest overall score : " + result);
                partyList.setListData(displayQueryResult);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("Average score")){
            try {
                if (selectedNickName == null){
                    JOptionPane.showMessageDialog(win,"Select player whose stat you want to see!!!");
                }
                else {
                    result = QueryScores.updateStats(selectedNickName, "Average score");
                    partyList.setListData(getDisplayStat("Average score", result));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("Average overall score")){
            System.out.println("Average overall score");
            try {
                result = QueryScores.updateStats("-", "Average overall score");
                Vector<String> displayQueryResult = new Vector<>();
                displayQueryResult.add("Average overall score : " + result);
                partyList.setListData(displayQueryResult);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("Best player")){
            System.out.println("Best player");
            try {
                result = QueryScores.updateStats("-", "Best player");
                Vector<String> displayQueryResult = new Vector<>();
                displayQueryResult.add("Best player : " + result);
                partyList.setListData(displayQueryResult);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("Worst player")){
            System.out.println("Worst player");
            try {
                result = QueryScores.updateStats("-", "Worst player");
                Vector<String> displayQueryResult = new Vector<>();
                displayQueryResult.add("Worst player : " + result);
                partyList.setListData(displayQueryResult);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public Vector getDisplayStat(String category, String result){
        Vector<String> displayQueryResult = new Vector<>();
        if (result.equals("-1") || result.equals("100000") || result.equals("-1.0"))
            displayQueryResult.add(selectedNickName + " has not played till now.");
        else
            displayQueryResult.add(selectedNickName + "'s " + category + " : " + result);
        return displayQueryResult;
    }

    public void valueChanged(ListSelectionEvent e){
        if (e.getSource().equals(allBowlers)) {
            selectedNickName = ((String) ((JList) e.getSource()).getSelectedValue());
        }
    }
}
