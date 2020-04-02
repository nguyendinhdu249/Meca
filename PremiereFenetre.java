import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList; 

public class PremiereFenetre extends JFrame implements ActionListener {
	private JPanel monConteneur;
	private JLabel Combien;
	private JTextField NombreDeClasses;
	private JButton OK;
	private MAINFenetre FenetreMain;
	
	public PremiereFenetre(){
		setTitle("Bonjour");
		setLocation(650,250);
		OK = new JButton("OK");
		OK.setBounds(210,35,60,40);
		OK.addActionListener(this);
		NombreDeClasses = new JTextField(10);
		NombreDeClasses.setBounds(15,35,150,40);
		Combien = new JLabel("Combien de classes ? (entre 2 et 50)");
		Combien.setBounds(15,0,300,40);
		monConteneur = new JPanel();
		monConteneur.setLayout(null);
		monConteneur.add(OK);
		monConteneur.add(NombreDeClasses);
		monConteneur.add(Combien);
		monConteneur.setBounds(10,10,300,200);
		add(monConteneur);
		setSize(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		FenetreMain = new MAINFenetre();
		FenetreMain.setVisible(false);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == OK){
			int choix = Integer.parseInt(NombreDeClasses.getText());
			if (choix>50 || choix<1){
					JOptionPane.showMessageDialog(this,"Veuillez rentrer un nombre entre 1 et 50 !");
			} else {
				FenetreMain.setVisible(true);
				this.setVisible(false);
                FenetreMain.setNombreDeClasse(choix);
			}
		}
	}
}
