import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList; 

public class MAINFenetre extends JFrame implements ActionListener { 
	//Pour la fenetre
	private JPanel panneauLiaison;
	private JPanel panneauClasse;
	private MyPaint panneauDessin;
	private JButton[] classes;
	
	//Pour les liaison
	private JButton ajouteLiaison;
	private int CountLiaison;
	private JLabel[] liaisons;
	private JTextField Classe1;
	private JTextField Classe2;
	private JTextField Type;
	
	//Pour le mecanique
	private Classe[] ListeDeClasse;
	private Liaison [][] ListeDeLiaison;
	private JButton BoutonBouge;
	
	
	
	
		
	public MAINFenetre(){
		//Pour la fenetre
		setTitle("NGUYEN DINH NGUYEN");
		setLocation(100,50);
		setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * Mon panneau 1
		 */
		panneauDessin = new MyPaint();
		panneauDessin.setBounds(20,20,750,610);
		panneauDessin.setLayout(null);
		
		BoutonBouge = new JButton("B");
		BoutonBouge.setBounds(700,560,50,50);
		BoutonBouge.setBackground(Color.black);
		BoutonBouge.setForeground(Color.white);
		/* branchement de l'écouteur*/
		BoutonBouge.addActionListener(this);
		panneauDessin.add(BoutonBouge);
		
		/**
		 * Mon panneau 2
		 */
		panneauClasse = new JPanel();
		panneauClasse.setBounds(790,20,235,610);
		panneauClasse.setLayout(null);
		panneauClasse.setBackground(Color.white);
		
		JLabel LabelClasse = new JLabel();
		LabelClasse.setText("Classes");
		LabelClasse.setBounds(10,10,215,20);
		panneauClasse.add(LabelClasse);
		
		/**
		 * Mon panneau 3
		 */
		JPanel panneauAjouteLiaison = new JPanel();
		panneauAjouteLiaison.setLayout(null);
		panneauAjouteLiaison.setBounds(1035,20,235,170);
		panneauAjouteLiaison.setBackground(Color.black);
		
		ajouteLiaison = new JButton("Liaisons(+)");
		ajouteLiaison.setBounds(10,10,215,30);
		ajouteLiaison.setBackground(Color.white);
		ajouteLiaison.setForeground(Color.black);
		/* branchement de l'écouteur*/
		ajouteLiaison.addActionListener(this);
		panneauAjouteLiaison.add(ajouteLiaison);
		
		//Les questions
		JLabel LabelClasse1 = new JLabel("Classe 1:");
		LabelClasse1.setForeground(Color.white);
		LabelClasse1.setBounds(20,50,100,30);
		
		Classe1 = new JTextField(10);
		Classe1.setBounds(80,50,20,30);
		
		JLabel LabelClasse2 = new JLabel("Classe 2: ");
		LabelClasse2.setForeground(Color.white);
		LabelClasse2.setBounds(120,50,100,30);
		
		Classe2 = new JTextField(10);
		Classe2.setBounds(180,50,20,30);
		
		JLabel LabelClasse3 = new JLabel("Type de liaison:(Pivot(0), Glissier(1))");
		LabelClasse3.setForeground(Color.white);
		LabelClasse3.setBounds(10,90,300,30);
		
		Type = new JTextField(10);
		Type.setBounds(40,130,150,30);
		
		panneauAjouteLiaison.add(Classe1);
		panneauAjouteLiaison.add(Classe2);
		panneauAjouteLiaison.add(Type);
		panneauAjouteLiaison.add(LabelClasse1);
		panneauAjouteLiaison.add(LabelClasse2);
		panneauAjouteLiaison.add(LabelClasse3);
		add(panneauAjouteLiaison);
		
		/**
		 * Mon panneau 4
		 */
		panneauLiaison = new JPanel();
		panneauLiaison.setBounds(1035,200,235,430);
		panneauLiaison.setLayout(null);
		panneauLiaison.setBackground(Color.white);
		
		CountLiaison = 0;
		liaisons = new JLabel[28]; //nombre maximale de liaison est 43 (limite du panneau)
		for (int i = 0; i<liaisons.length; i++){
			liaisons[i] = new JLabel();
			liaisons[i].setForeground(Color.black);
			liaisons[i].setBounds(5,5+i*15,235,20);
			panneauLiaison.add(liaisons[i]);
		}
	
		/**
		 * Mon panneau Global
		 */
		JPanel panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,1300,700);
		panneauGlobal.setLayout(null);
		panneauGlobal.setBackground(Color.red);
		panneauGlobal.add(panneauClasse);
		panneauGlobal.add(panneauLiaison);
		panneauGlobal.add(panneauAjouteLiaison);
		panneauGlobal.add(panneauDessin);
		this.add(panneauGlobal);
		
	}
	
	
	public void setNombreDeClasse(int NombreDeClasses){
		ListeDeClasse = new Classe[NombreDeClasses];
		classes = new JButton[NombreDeClasses];
		for (int i = 0; i<classes.length; i++){
			//add classe
			ListeDeClasse[i] = new Classe(i, new Color((int)(Math.random() * 0x1000000)));
			
			//add bouton de classe
			classes[i] = new JButton("Classe "+i);
			classes[i].setBounds(10,35+(580/NombreDeClasses)*i,215,580/NombreDeClasses-10);
			classes[i].setBackground(ListeDeClasse[i].getColor());
			classes[i].setForeground(Color.black);
			classes[i].addActionListener(this);
			panneauClasse.add(classes[i]);
			
			//etablir table de liaison
			ListeDeLiaison = new Liaison [NombreDeClasses][NombreDeClasses];
		}
		//Pour dessiner les classes
		panneauDessin.addDessin(ListeDeClasse);
	}
	
	public void actionPerformed (ActionEvent e){
		if (e.getSource()== ajouteLiaison){
			int choix1 = Integer.parseInt(Classe1.getText());
			int choix2 = Integer.parseInt(Classe2.getText());
			int choix3 = Integer.parseInt(Type.getText());
			//Les critieres pour creer un liaison
			if (choix1 == choix2 || choix3>1 || choix1<0 || choix2<0 || choix3<0 || choix1 > ListeDeClasse.length-1 || choix2>ListeDeClasse.length-1 ||ListeDeLiaison[choix1][choix2] != null){
				JOptionPane.showMessageDialog(this,"Impossible de creer la liaison");
			} else {
				
				if(CountLiaison<liaisons.length){
					if(choix3 == 0){
						liaisons[CountLiaison].setText(CountLiaison+")  Liaison PIVOT ("+choix1+"/"+choix2+")");
						panneauLiaison.add(liaisons[CountLiaison]);
					}
					if(choix3 == 1){
						liaisons[CountLiaison].setText(CountLiaison+")  Liaison GLISSIERE ("+choix1+"/"+choix2+")");
						panneauLiaison.add(liaisons[CountLiaison]);
					}
					ListeDeLiaison[choix1][choix2] = new Liaison();
					CountLiaison++;
					if (choix1>choix2){
						panneauDessin.addLiaison(choix2,choix1,choix3);
					} else {
						panneauDessin.addLiaison(choix1,choix2,choix3);
					}
				}
			}
			
			
		}
		for (int i = 0; i<classes.length; i++){
			if (e.getSource()== classes[i]){
				panneauDessin.setChoix(i);
			}
		
		}
		if (e.getSource()== BoutonBouge){
			panneauDessin.vaBouger();
		}
	}
}
