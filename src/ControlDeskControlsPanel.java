import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ControlDeskControlsPanel {
    private JPanel controlsPanel;

    public ControlDeskControlsPanel(ControlDeskView controlDesk){
        // Controls Panel
        controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(6, 6));
        controlsPanel.setBorder(new TitledBorder("CONTROLS"));
        this.addButtonToPanel("Add Party", controlDesk );
        this.addButtonToPanel("Finished", controlDesk );

//        controlsPanel = new JPanel();
//        controlsPanel.setLayout(new GridLayout(4, 1));
//        controlsPanel.setBorder(new TitledBorder("Controls"));
//        controlsPanel.add(new AddNewButton<ControlDeskView>(controlDesk,"Add Party").returnButton());
//        controlsPanel.add(new AddNewButton<ControlDeskView>(controlDesk,"Finished").returnButton());

    }

    public void addButtonToPanel(String Button_text, ControlDeskView controlDesk ){
        System.out.println("button : " + Button_text);
        JButton button = new JButton(Button_text);
        JPanel subControlPanel = new JPanel();
        subControlPanel.setLayout(new FlowLayout());
        button.addActionListener(controlDesk);
        subControlPanel.add(button);
        controlsPanel.add(subControlPanel);
    }

    public JPanel getControlsPanel() {
        return controlsPanel;
    }

}

