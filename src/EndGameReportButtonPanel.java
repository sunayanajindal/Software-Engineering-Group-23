import javax.swing.*;
import java.awt.*;

public class EndGameReportButtonPanel {
    JPanel buttonPanel;
    public EndGameReportButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        Insets buttonMargin = new Insets(4, 4, 4, 4);
    }

    public JButton addButton(String button_text){
        JButton button = new JButton(button_text);
        JPanel thisButtonPanel = new JPanel();
        thisButtonPanel.setLayout(new FlowLayout());
        //button.addActionListener(this);
        thisButtonPanel.add(button);
        buttonPanel.add(thisButtonPanel);
        return button;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }
}
