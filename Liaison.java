// Decrire une liaison entre 2 Classe

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList; 

public class Liaison  {
	public Point position; //Position
	public int type;
	public int c1;
	public int c2;
	public Color col1;
	public Color col2;
	
	
	
	public Liaison(Point p, int tt, int ac1, int ac2) {
		position = p;
		type = tt;
		c1 = ac1;
		c2 = ac2;

	}
	public Liaison(Point p, int tt, int ac1, int ac2,Color co1,Color co2) {
		c1 = ac1;
		c2 = ac2;
		col1 = co1;
		col2 = co2;
		position = p;
		type = tt;
	}
	public Liaison() {
		position = new Point (10000,10000);
	}
	
	//~ public void ChangeParametre (LinkedList<Liaison> listeL){
		//~ if (type == 0){
			//~ for (int i = 0; i<c1.L.size(); i++){
				//~ if ( c1.num == 0){
				 //~ addSystemE
				 
				//~ }
				//~ c1.L.get(i)
			//~ }
		//~ }
	//~ }
	
	public void dessine(Graphics g){
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setColor(col1);
		g.fillOval((int)position.x-13,(int)position.y-13,26, 26);
		g2d.setColor(col2);
		g2d.drawOval((int)position.x-15,(int)position.y-15,30,30);
	}
	public void setPosition(Point a){
		position = a;
	}
	public void setType(int t){
		type = t;
	}
	public Point getPosition(){
		return position;
	}
	public int getType(){
		return type;
	}
	
	public String toString() {
		return ("("+ getY()+","+getX()+")");
	}
	public double getY(){
		return position.gety();
	}
	public double getX(){
		return position.getx();
	}
	public double distance(Liaison B) {
		return this.position.DistanceEntrePoints(B.position);
	}
}	

