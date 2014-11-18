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
	private ControleurPrincipal monControleur;
	/**
	 * @param controleurPrincipal 
	 * @param args
	 */
	public FenetrePrincipal(ControleurPrincipal controleurPrincipal){
		super("RegExp");
		cetteFenetre=this;
		monControleur=controleurPrincipal;
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
		
		texte=new JLabel(monControleur.getTexteAfficher());
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
			texte.setText(AjoutCouleur.ajouterCouleur(monControleur.getTexteAfficher(), champDeSaisie.getText()));
			System.out.println(AjoutCouleur.ajouterCouleur(monControleur.getTexteAfficher(), champDeSaisie.getText()));
				
			}
		});
		this.add(panSud,BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

}
