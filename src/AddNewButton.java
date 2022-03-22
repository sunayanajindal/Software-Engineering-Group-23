import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//public class AddNewButton<T> {
//    private JPanel newButtonPanel;
//    public AddNewButton(T controlDesk, String ButtonName){
//        JButton newButton = new JButton(ButtonName);
//        newButtonPanel = new JPanel();
//        newButtonPanel.setLayout(new FlowLayout());
//        newButton.addActionListener((ActionListener) controlDesk);
//        newButtonPanel.add(newButton);
//    }
//    public JPanel returnButton(){return newButtonPanel;}
//}

public class AddNewButton<T> {
    private JPanel newButtonPanel;
    public AddNewButton(T controlDesk, String ButtonName){
        JButton newButton = new JButton(ButtonName);
        newButtonPanel = new JPanel();
        newButtonPanel.setLayout(new FlowLayout());
        newButton.addActionListener((ActionListener) controlDesk);
        newButtonPanel.add(newButton);
    }
    public JPanel returnButton(){return newButtonPanel;}
}