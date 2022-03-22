import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

public class ControlDeskLaneStatusPanel {
    private JPanel LaneStatusPanel;
    public ControlDeskLaneStatusPanel(ControlDesk controlDesk){
        LaneStatusPanel = new JPanel();
        int numLanes = controlDesk.getNumLanes();
        LaneStatusPanel.setLayout(new GridLayout(numLanes, 1));
        LaneStatusPanel.setBorder(new TitledBorder("LANE STATUS"));

        HashSet lanes=controlDesk.getLanes();
        Iterator it = lanes.iterator();
        int laneCount=0;
        while (it.hasNext()) {
            Lane curLane = (Lane) it.next();
            LaneStatusView laneStat = new LaneStatusView(curLane,(laneCount+1));
            curLane.subscribe(laneStat);
            ((Pinsetter)curLane.getPinsetter()).subscribe(laneStat);
            JPanel lanePanel = laneStat.showLane();
            lanePanel.setBorder(new TitledBorder("Lane" + ++laneCount ));
            LaneStatusPanel.add(lanePanel);
        }
    }
    public JPanel getLaneStatusPanel(){
        return LaneStatusPanel;
    }
}
