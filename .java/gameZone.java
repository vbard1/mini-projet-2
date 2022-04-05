
import javax.swing.*;
import java.awt.*;

public class gameZone extends JPanel {
    int width;
    int height;
    Graphics graph;
    int dessin;
    Trajectoire traj;
    public gameZone(){

    }
    public gameZone(int w, int h){
        width=w;
        height=h;
        repaint();
        setVisible(true);
    }
    public void paint(Graphics g){

        int [] xPoints={10,30,30,50};
        int [] yPoints={height,height,height-60,height-60};
        g.fillPolygon(xPoints,yPoints,xPoints.length);
        
        g.fillOval(200, 200, 50, 50);
        if(dessin==1){
            for(int i= 0;i<traj.paramTraj[0].size();i+=3){
                g.fillOval((int)(traj.paramTraj[1].get(i)),(int)(traj.paramTraj[0].get(i)),3,3);      
                System.out.println("i :"+i);
            }

        //System.out.println("graph "+g);
        }
        
        
        
    }
    public void preview(Trajectoire t){
        dessin=1;
        traj=t;
        repaint();
        
    }
}
