// Decrire une Droite par 2 Point

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Droite {
	private Point A;
	private Point B;
	
	public Droite(Point a, Point b) {
		A = a;
		B = b;
	}
	
	public void dessine(Graphics g){
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine((int)A.x,(int)A.y, (int)B.x, (int)B.y);
       
	}
	
	public String toString() {
		return A.x + " " + A.y + " " + B.x + " " + B.y + " ";
	}
	
	public void setB(Point p2){
		B=p2;
	}
	//~ public void spindroite(Point center,double Angle){
		
		//~ A.spinpoint(center, Angle);
		//~ B.spinpoint(center, Angle);
		
	//~ }
	
	public void deplacement(Point a1, Point a2, Point b1, Point b2){
		System.out.println((a1.x - a2.x)+" II "+(a1.y - a2.y)+" II ");
		double angle = Math.toDegrees(Math.atan((b2.y-a2.y)/(b2.x-a2.x)) - Math.atan((b1.y-a1.y)/(b1.x-a1.x)));
		if (angle <90 && angle >-90){
		A.rotateAround(a2,angle);
		B.rotateAround(a2,angle);
		} else {
		A.rotateAround(a2,180+angle);
		B.rotateAround(a2,180+angle);
		}
		
		
	}
	public void deplacement(Point a1, Point a2){
		A.x -= (a1.x - a2.x);
		A.y -= (a1.y - a2.y);
		B.x -= (a1.x - a2.x);
		B.y -= (a1.y - a2.y);
	}
}
