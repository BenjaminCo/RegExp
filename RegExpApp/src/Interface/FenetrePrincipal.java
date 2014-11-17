package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controleur.AjoutCouleur;
import Controleur.ControleurPrincipal;

public class FenetrePrincipal extends JFrame {
	private JMenuBar barreDeMenu=new JMenuBar();
	private JMenu aide=new JMenu("Aide");
	private JMenuItem boutonAPropos=new JMenuItem("A Propos");
	private JFrame cetteFenetre;
	private JLabel texte;
	private JTextField champDeSaisie;
	/**
	 * @param args
	 */
	public FenetrePrincipal(){
		super("RegExp");
		cetteFenetre=this;
		this.setSize(new Dimension(500,400));
		this.setLayout(new BorderLayout());
		
		
		this.add(barreDeMenu,BorderLayout.NORTH);
		barreDeMenu.add(aide);
		aide.add(boutonAPropos);
		
		boutonAPropos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				A_propos fenetreApropos = new A_propos(cetteFenetre, "A Propos");
				
			}
		});
		
		texte=new JLabel(ControleurPrincipal.getTexte());
		JPanel panMilieu=new JPanel();
		this.add(panMilieu,BorderLayout.CENTER);
		panMilieu.setLayout(new FlowLayout());
		panMilieu.add(texte);
		
		
		
		JPanel panSud=new JPanel();
		champDeSaisie=new JTextField();
		champDeSaisie.setPreferredSize(new Dimension(200,30));
		panSud.add(champDeSaisie);
		JButton BoutonDeValidation=new JButton("Valider");
		panSud.add(BoutonDeValidation);
		BoutonDeValidation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			texte.setText(AjoutCouleur.ajouterCouleur(texte.getText(), champDeSaisie.getText()));
			
				
			}
		});
		this.add(panSud,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FenetrePrincipal  fen=new FenetrePrincipal();
	}

}
