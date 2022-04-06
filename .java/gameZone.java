
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

        
        if(dessin==1){
            g.setColor(new Color(250,255,224));
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(Color.black);
            for(int i= 0;i<traj.paramTraj[0].size();i+=3){
                g.fillOval((int)(traj.paramTraj[0].get(i)),this.height-(int)(traj.paramTraj[1].get(i)),3,3);      
            }
        }
        int [] xPoints={10,30,30,50};
        int [] yPoints={height,height,height-60,height-60};
        g.fillPolygon(xPoints,yPoints,xPoints.length);
        g.setColor(Color.red);
        g.drawRect(0,0,this.width,this.height);
        g.setColor(new Color(250,255,224));
        g.fillOval(200, 200, 50, 50);
        
        
        
    }
    public void preview(Trajectoire t){
        dessin=1;
        traj=t;
        repaint();
        
    }
}
