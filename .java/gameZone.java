
import javax.swing.*;
import java.awt.*;

public class gameZone extends JPanel {
    int width;
    int height;
    public gameZone(){

    }
    public gameZone(int w, int h){
        width=w;
        height=h;
    }
    public void paint(Graphics g){
        int [] xPoints={10,30,30,50};
        int [] yPoints={height,height,height-60,height-60};
        g.fillPolygon(xPoints,yPoints,xPoints.length);
        
        g.fillOval(200, 200, 50, 50);;
        
    }
}
