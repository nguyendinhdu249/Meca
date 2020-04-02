// Decrire une classe par les Droite

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList; 

public class Classe {
	private int num;
	private LinkedList<Droite> D;
	private Color color;
	
	public Classe(int n, Color c) {
		num = n;
		color = c;
		D = new LinkedList<Droite>();
	}
	public Color getColor(){
		return color;
	}
	
	public void dessine(Graphics g){
		 g.setColor(color);
		
		 for (int i = 0; i < D.size(); i++) {
			 D.get(i).dessine(g);
		 }
	}
	
	public void addDroite(Droite a){
		D.add(a);
		
	}
	//~ public void changepara(Point a){
		//~ for (int i = 0; i<D.size(); i++){
			//~ D.get(i).spindroite(a,0.03);
		//~ }
	//~ }
	
	
	public String toString() {
		return num + "";
	}
	
	public void ChangeListeDeDroite(Point a1, Point a2, Point b1, Point b2){
		
		for (int i = 0; i < D.size(); i++) {
			 D.get(i).deplacement(a1, a2, b1, b2);
		 }
	}
	public void ChangeListeDeDroite(Point a1, Point a2){
		for (int i = 0; i < D.size(); i++) {
			 D.get(i).deplacement(a1, a2);
		 }
	}
}
