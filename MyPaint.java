import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList; 

public class MyPaint extends JPanel implements MouseListener, MouseMotionListener {
	//Choisir fonctionnement
	private int ActionMouse;
	
	//Dessiner les liaisons
	private Liaison currentLiaison;
	private Liaison [][] ListeDeLiaison;
	private Liaison [][] ListeDeLiaisonAncien;
	private LinkedList<Equation> SystemEquation;
	private int classe1;
	private int classe2;
	private int type;

	
	
	//Dessiner les classes
	private Droite currentDroite;
	private Classe [] ListeDeClasse;
	private int Choix;
	
	//Pour le systeme d'Equation
	public static LinkedList<Equation> equa;
	public static Liaison [][] bbbb;
	public static LinkedList<Liaison> li;
	public static boolean theFist;
	public static boolean max;
	public static boolean dau;
	public static boolean da_tim_thay_nghiem;
	
	public MyPaint(){
		Choix =0;
		currentDroite = null;	
		currentLiaison = null;
		
		dau = true;
		//Action mouse
		ActionMouse = 0;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public void paint(Graphics g) {    		  
		super.paint(g);
		draw(g);
		
 	}
 	
 	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//~ System.out.println(d);
		//~ if (!d.isEmpty())
			//~ for (int i = 0; i < d.size(); i++){
				//~ g.setColor(Color.black);
				//~ d.get(i).dessine(g);
			//~ }
			
		
	
		//Dessiner les classes
		for (int i = 0; i < ListeDeClasse.length; i++) {
			ListeDeClasse[i].dessine(g);
		}
		//En train de dessiner un classe
		if (currentDroite != null) {	
				g.setColor(ListeDeClasse[Choix].getColor());
				currentDroite.dessine(g);
		}
		//Dessiner les liaisons

		for (int i = 0; i < ListeDeLiaison.length; i++) {
			for (int j = i; j < ListeDeLiaison.length; j++) {
					ListeDeLiaison[i][j].dessine(g);
			}
		}
		//En train de dessiner une liaison
		if (currentLiaison != null) {	
			g.setColor(Color.yellow);
			currentLiaison.dessine(g);
		}
		
	}
	public void mousePressed(MouseEvent e) {
		if(ActionMouse == 0){
			Point p1 = new Point(e.getX(), e.getY());
			currentDroite = new Droite(p1, p1);
		} else {
			Point liaisontemp = new Point(e.getX(), e.getY());
			ListeDeLiaison[classe1][classe2] = new Liaison(liaisontemp, type,classe1,classe2, ListeDeClasse[classe1].getColor(),ListeDeClasse[classe2].getColor());
			currentLiaison = null;
			repaint(); 
		}
		
	}
	public void mouseReleased(MouseEvent e) {
		if(ActionMouse == 0){
			Point p2 = new Point(e.getX(), e.getY());
			currentDroite.setB(p2);
			ListeDeClasse[Choix].addDroite(currentDroite); 
			currentDroite = null;
			repaint(); 
		} else {
			ActionMouse = 0;
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(ActionMouse == 1){
			Point liaisontemp = new Point(e.getX(), e.getY());
			currentLiaison = new Liaison(liaisontemp,0,classe1,classe2, ListeDeClasse[classe1].getColor(),ListeDeClasse[classe2].getColor());
			repaint();
		}
	}
	public void mouseDragged(MouseEvent e) {
		//redessiner la droite chaque fois 
		if(ActionMouse == 0){
			Point temp = new Point(e.getX(), e.getY());
			currentDroite.setB(temp);
			repaint();
		} 
			
	}
	public void addDessin(Classe [] Listclasses){
		ListeDeClasse = Listclasses;
		ListeDeLiaison = new Liaison [ListeDeClasse.length][ListeDeClasse.length];
		ListeDeLiaisonAncien = new Liaison [ListeDeClasse.length][ListeDeClasse.length];
		for (int i = 0; i < ListeDeLiaison.length; i++) {
			for (int j = 0; j < ListeDeLiaison.length; j++) {
				ListeDeLiaison[i][j] = new Liaison();
				ListeDeLiaisonAncien[i][j] = new Liaison();
				
			}
		}
		
	}
	public void setChoix(int c){
		Choix = c;
	}
	public void addLiaison(int a, int b, int c){
		if (a>b){
			classe1 = b;
			classe2 = a;
		} else {
			classe1 = a;
			classe2 = b;
		}
		type = c;
		ActionMouse = 1;
		
	}
	
	public void vaBouger(){
		
		bbbb = ListeDeLiaison;
		for (int i = 0; i < ListeDeLiaison.length; i++) {
			for (int j = i+1; j < ListeDeLiaison.length; j++) {
				if (ListeDeLiaison[i][j].getX() < 10000) {
					ListeDeLiaisonAncien[i][j].setPosition(ListeDeLiaison[i][j].getPosition());
				} else {
					ListeDeLiaisonAncien[i][j].setPosition(ListeDeLiaison[0][0].getPosition());
				}
			}
		}
		
		li = new LinkedList<Liaison>();
		equa = new LinkedList<Equation>();
		
		//~ for (int i = 0; i < bbbb.length; i++) {
			//~ for (int j = 0; j < bbbb.length; j++) {
				//~ System.out.print(bbbb[i][j]);
			//~ }
			//~ System.out.println();
		//~ }
				
		for (int i = 0; i < bbbb.length; i++) {
			for (int j = i+1; j < bbbb.length; j++) {
				if (bbbb[i][j].getX() < 10000) {
					li.add(bbbb[i][j]);
					bbbb[i][j].c1=i;
					bbbb[i][j].c2=j;
				}
			}
		}

		int a = 0;
		int b = 0;
	
		for (int i = 0; i < bbbb.length; i++) {
			for (int j = i + 1; j < bbbb.length; j++) {
				if (bbbb[i][j].getX() < 10000) {
					if (i != 0) {
						for (int k = j + 1; k < bbbb.length; k++) {
							if (bbbb[i][k].getX() < 10000) {
								for (int ii = 0; ii < li.size(); ii++) {
									if (li.get(ii) == bbbb[i][j]) a = ii;
									if (li.get(ii) == bbbb[i][k]) b = ii;
								}
								equa.add(new Equation(a, b, bbbb[i][j].distance(bbbb[i][k])));
							}
						} 
					}
					for (int h = i + 1; h < j; h++) {
							if (bbbb[h][j].getX() < 10000) {
								for (int ii = 0; ii < li.size(); ii++) {
									if (li.get(ii) == bbbb[i][j]) a = ii;
									if (li.get(ii) == bbbb[h][j]) b = ii;
								}
								equa.add(new Equation(a, b, bbbb[i][j].distance(bbbb[h][j])));
							}
						}
					for (int h = j + 1; h < bbbb.length; h++) {
						if (bbbb[j][h].getX() < 10000) {
							for (int ii = 0; ii < li.size(); ii++) {
									if (li.get(ii) == bbbb[i][j]) a = ii;
									if (li.get(ii) == bbbb[j][h]) b = ii;
								}
								equa.add(new Equation(a, b, bbbb[i][j].distance(bbbb[j][h])));
							}
					}
				}
			}
		}

		theFist = true;
		max = true;
		
		if (dau == true) reso('+', 0);
		else reso('-', 0);
		//~ System.out.println();
		//~ for (int i = 0; i < li.size(); i++) {
			//~ System.out.println(li.get(i));
		//~ }
		//~ System.out.println("---------------------");
		
		//tinh toan su di chuyen cua classe
		
		Point a1 = new Point(0,0);
		Point b1 = new Point(0,0);
		Point a2 = new Point(0,0);
		Point b2 = new Point(0,0);
		for (int i = 1; i < ListeDeLiaison.length; i++) {
			int gocdichchuyen = 0;
			for (int j = 0; j < ListeDeLiaison.length; j++) {
				if(bbbb[i][j].getX() < 10000){
					if (gocdichchuyen == 0){ 
						
						a1 = ListeDeLiaisonAncien[i][j].getPosition();
						a2 = bbbb[i][j].getPosition();
						System.out.println(a1.x+ " h " +a2.x);
						ListeDeClasse[i].ChangeListeDeDroite(a1,a2);
						gocdichchuyen = 1;
					} else {
						
						b1 = ListeDeLiaisonAncien[i][j].getPosition();
						b2 = bbbb[i][j].getPosition();
						System.out.println(b1.x+ " h " +b2.x);
						ListeDeClasse[i].ChangeListeDeDroite(a1,a2,b1,b2);
						gocdichchuyen = 0;
					}
					
					
				}
				if(bbbb[j][i].getX() < 10000){
					if (gocdichchuyen == 0){ 
						a1 = ListeDeLiaisonAncien[j][i].getPosition();
						a2 = bbbb[j][i].getPosition();
						System.out.println(a1.x+ "   " +a2.x);
						ListeDeClasse[i].ChangeListeDeDroite(a1,a2);
						gocdichchuyen = 1;
					} else {
						
						b1 = ListeDeLiaisonAncien[j][i].getPosition();
						b2 = bbbb[j][i].getPosition();
						System.out.println(b1.x+ "   " +b2.x);
						ListeDeClasse[i].ChangeListeDeDroite(a1,a2,b1,b2);
						gocdichchuyen = 0;
					}
					
					
				}
			}
			
		}
		
		
		
		
		for (int i = 0; i < ListeDeLiaison.length; i++) {
			for (int j = 0; j < ListeDeLiaison.length; j++) {
				System.out.print(ListeDeLiaisonAncien[i][j]);
			}
			System.out.println();
		}
		
		ListeDeLiaison = bbbb;
		for (int i = 0; i < bbbb.length; i++) {
			for (int j = 0; j < bbbb.length; j++) {
				System.out.print(bbbb[i][j]);
			}
			System.out.println();
		}
		if (max == true) dau = !dau;
		
		//System.out.println(bbbb[li.getFirst().c1][li.getFirst().c2]);
		repaint();
	}
	static void reso(char c, int n) {
		
		if (n == li.size()) {
			da_tim_thay_nghiem = true;
			for (int i = 0; i < equa.size(); i++) {
				if(equa.get(i).l2<li.size() && equa.get(i).l1<li.size()){
					if (Math.abs(ResoudreEqua(li.get(equa.get(i).l2).position.x,li.get(equa.get(i).l1).position.x,li.get(equa.get(i).l2).position.y, li.get(equa.get(i).l1).position.y,equa.get(i).L)) > 100) {
						da_tim_thay_nghiem = false;
					} 
				}
			}
			if (da_tim_thay_nghiem) {
				for (int i = 0; i < li.size(); i++) {
					//System.out.println(bbbb[li.get(i).c1][li.get(i).c2]);
					double aaa = li.get(i).position.x;
					double bbb = li.get(i).position.y;
					
					//System.out.println(li.get(i).c1+"]["+li.get(i).c2);
					bbbb[li.get(i).c1][li.get(i).c2] = new Liaison(new Point(aaa,bbb),li.get(i).type,li.get(i).c1,li.get(i).c2, li.get(i).col1,li.get(i).col2);
					//~ System.out.println(li.get(i).position.x);
					//~ System.out.println(bbbb[1][2] + "Ket qua");
					//~ TestL = new Liaison2(new Point(aaa,bbb),bbbb[1][2].type,bbbb[1][2].c1,bbbb[1][2].c2);
				}
				System.out.println();
				max = false;
			}
		
		} else {
			boolean ok = true;
			for (int i = 1; i < bbbb.length; i++) {
				if (li.get(n) == bbbb[0][i]) ok = false;
			}
			if (ok && theFist) {
				
				//~ for (int i = 0; i <100; i++){
					//~ System.out.print((int)(-Math.pow((-1),i)*((i+1)/2))+"||");
				//~ }
				for (int i = 0; i < 10; i++) {
					//System.out.println(li.get(n).position.x + "cong them");
					if (c == '+') li.get(n).position.x += 2;
					else li.get(n).position.x -= 2;
					li.get(n).position.y += ((int)(-Math.pow((-1),i)*((i+1)/2)));
					theFist = false;
					reso(c, n+1);
					if (c == '+') li.get(n).position.x -= 2;
					else li.get(n).position.x += 2;
					li.get(n).position.y -= ((int)(-Math.pow((-1),i)*((i+1)/2)));
					if (da_tim_thay_nghiem) break;
				}
			} else if (ok) {
				for (int i = 0; i < 10; i++) {
					li.get(n).position.x += ((int)(-Math.pow((-1),i)*((i+1)/2)));
					for (int j = 0; j < 10; j++) {
						li.get(n).position.y += ((int)(-Math.pow((-1),j)*((j+1)/2)));
						reso(c, n+1);
						li.get(n).position.y -= ((int)(-Math.pow((-1),j)*((j+1)/2)));
						if (da_tim_thay_nghiem) break;
					}
					li.get(n).position.x -= ((int)(-Math.pow((-1),i)*((i+1)/2)));
					if (da_tim_thay_nghiem) break;
				}
			} else {
				reso(c, n+1);
			}
			
			
			
		//~ for (int i = 0; i < li.size(); i++) {
			//~ System.out.println(li.get(i));
		//~ }
		}
		
		//~ System.out.println(bbbb[1][2] + " ");
		//~ System.out.println(TestL + "Thu nghiem");
	}
	static double ResoudreEqua(double xa,double xb,double ya,double yb,double L){
		return Math.pow(xa - xb, 2) + Math.pow(ya - yb, 2) - Math.pow(L, 2);
	}
}

