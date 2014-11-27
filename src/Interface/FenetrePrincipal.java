package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.Expression;

import javax.swing.*;

import Controleur.AjoutCouleur;
import Controleur.ControleurPrincipal;
/**
 * Permet l'affichage de l'interface utilisateur
 * @author Benjamin
 *
 */
public class FenetrePrincipal extends JFrame {
	private JMenuBar barreDeMenu=new JMenuBar();
	private JMenu aide=new JMenu("Aide");
	private JMenuItem boutonAPropos=new JMenuItem("A Propos");
	private JFrame cetteFenetre;
	private JLabel texte;
	private JTextField champDeSaisie;
	private ControleurPrincipal monControleur;
	private String sanscouleur ="";
	private String vert ="";
	private String vertrouge="";
	/**
	 * Affiche la fenetre principale de l'application
	 * @param controleurPrincipal Le controleurPrincipal de l'application
	 * 
	 */
	
	private String exprSolution;
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
		
		//AFFICHAGE
		exprSolution=monControleur.getRegExp();
		sanscouleur = monControleur.getTexteAfficher();
		vert = AjoutCouleur.ajouterCouleur(sanscouleur, null ,exprSolution);
		//texte=new JLabel(monControleur.getTexteAfficher());
		texte=new JLabel(vert);
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
		
		/*BoutonDeValidation.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				vertrouge = vert;
				vertrouge = AjoutCouleur.ajouterCouleur(vert, champDeSaisie.getText(),"red");
			//texte.setText(AjoutCouleur.ajouterCouleur(monControleur.getTexteAfficher(), champDeSaisie.getText(),"red"));
				texte.setText(vertrouge);
			//System.out.println(AjoutCouleur.ajouterCouleur(monControleur.getTexteAfficher(), champDeSaisie.getText(),"red"));
				
			//ccc	
			}
		});*/
		
		champDeSaisie.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				if (champDeSaisie.getText().equals("")) {
					texte.setText( AjoutCouleur.ajouterCouleur(sanscouleur,null,exprSolution));
				}else{
					texte.setText( AjoutCouleur.ajouterCouleur(sanscouleur,champDeSaisie.getText(),exprSolution));
				}
					
			
				//System.out.println("saisie : "+champDeSaisie.getText());
				//System.out.println("vert rouge : "+vertrouge);
			//texte.setText(AjoutCouleur.ajouterCouleur(monControleur.getTexteAfficher(), champDeSaisie.getText(),"red"));
				
				
			//System.out.println(AjoutCouleur.ajouterCouleur(monControleur.getTexteAfficher(), champDeSaisie.getText(),"red"));
				
			//ccc	
			}

			@Override
			public void keyTyped(KeyEvent e) {}
		});
		this.add(panSud,BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

}
