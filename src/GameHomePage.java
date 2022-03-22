import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameHomePage extends Frame implements ActionListener {
    private JLabel lane_size_label, max_patron_count_label;
    private JButton OK, Help;
    private JTextField lane_size_field, max_patron_field;
    public int[] arr = new int[2];
    JPanel panel = new JPanel();
    private JFrame win;
    JPanel lanePanel, max_patron_panel, OK_panel, Help_panel;;

    public GameHomePage(){

        win = new JFrame("Game Configuration");
        win.getContentPane().setLayout(new BorderLayout());
        ((JPanel) win.getContentPane()).setOpaque(false);

        JPanel colPanel = new JPanel();
        colPanel.setLayout(new BorderLayout());

        // Patron Panel
        JPanel patronPanel = new JPanel();
        patronPanel.setLayout(new GridLayout(3, 1));
        patronPanel.setBorder(new TitledBorder("Configuration settings"));

        lanePanel = new JPanel();
        lanePanel.setLayout(new FlowLayout());
        lane_size_label = new JLabel("Lane Size");
        lane_size_field = new JTextField("", 15);
        lanePanel.add(lane_size_label);
        lanePanel.add(lane_size_field);

        max_patron_panel = new JPanel();
        max_patron_panel.setLayout(new FlowLayout());
        max_patron_count_label = new JLabel("Max Patron");
        max_patron_field = new JTextField("", 15);
        max_patron_panel.add(max_patron_count_label);
        max_patron_panel.add(max_patron_field);



        patronPanel.add(lanePanel);
        patronPanel.add(max_patron_panel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        Insets buttonMargin = new Insets(4, 4, 4, 4);

        OK = new JButton("OK");
        OK_panel = new JPanel();
        OK_panel.setLayout(new FlowLayout());
        OK.addActionListener(this);
        OK_panel.add(OK);

        Help = new JButton("Help");
        Help_panel = new JPanel();
        Help_panel.setLayout(new FlowLayout());
        Help.addActionListener(this);
        Help_panel.add(Help);

        buttonPanel.add(OK_panel);
        buttonPanel.add(Help_panel);

        // Clean up main panel
        colPanel.add(patronPanel, "Center");
        colPanel.add(buttonPanel, "East");

        win.getContentPane().add("Center", colPanel);

        win.pack();

        // Center Window on Screen
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.show();

    }

    public void actionPerformed(ActionEvent e){
        int result;
        if(e.getSource().equals(Help)){
            JOptionPane.showMessageDialog(win, "1. Decide the maxpatron and lanes to start the game and then start the game\n" +
                            "2. After adding the members in party we can set the velocity and angle in ViewLane to throw the ball and get score\n" +
                            "3. We can check the balls getting hit in Pinsetter\n" +
                            "4. We can end the game using Finish option in ControlDesk\n",
                    "HELP INFORMATION",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource().equals(OK)){
            String s1=lane_size_field.getText().trim();
            String s2=max_patron_field.getText().trim();
            int maxPatronsPerParty = 0, numLanes = 0;
            boolean isNumeric = false;
            try {
                maxPatronsPerParty = Integer.parseInt(s2);
                numLanes = Integer.parseInt(s1);
                isNumeric = true;
            }
            catch(NumberFormatException err){
                JOptionPane.showMessageDialog(win, "Enter a valid numeric value",
                        "WARNING", JOptionPane.WARNING_MESSAGE);

            }
            if(isNumeric) {
                if (s1.isEmpty() && s2.isEmpty()) {
                    JOptionPane.showMessageDialog(win, "You did not enter any value tp begin with. Please enter!!!");
                } else if (s1.isEmpty()) {
                    JOptionPane.showMessageDialog(win, "Please enter number of lanes!!!");
                } else if (s2.isEmpty()) {
                    JOptionPane.showMessageDialog(win, "Please enter max number of patrons per party!!!");
                } else if (Integer.parseInt(s2) > 6) {
                    JOptionPane.showMessageDialog(win, "Max number of patrons allowed per party should be in range 1-6 !!!");
                } else {

                    result = JOptionPane.showConfirmDialog(win, "Sure? You want to Continue with these settings?", "Confirm your settings",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("yes " + result);
                        win.setVisible(false);
                        startGame(numLanes, maxPatronsPerParty);
                    } else if (result == JOptionPane.NO_OPTION) {
                        System.out.println("no " + result);
                    } else {
                        System.out.println("other " + result);
                    }
//                    win.setVisible(false);
//                    startGame(numLanes, maxPatronsPerParty);

                }
            }
        }
    }

    public void startGame(int numLanes, int maxPatronsPerParty){
        ControlDesk controlDesk;
        controlDesk = new ControlDesk( numLanes );

        controlDesk.start();
        ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
    }


    public JFrame getWin() {
        return win;
    }
}
