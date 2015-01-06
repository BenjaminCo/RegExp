package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.Expression;

import javax.swing.*;

import Controleur.AjoutCouleur;
import Controleur.ControleurPrincipal;
import Controleur.Exercice;
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
	
	private String vert1="";
	private String rouge1="";
	private String affReponse="";
	private JLabel labReponse;
	
	private String exprSolution;
	/**
	 * Affiche la fenetre principale de l'application
	 * @param controleurPrincipal Le controleurPrincipal de l'application
	 * 
	 */
	
	/*
	Exercice exo=new Exercice("textes/texteTest.txt","textes/texteTest.regexp");
	exo.realiserExercice("ce|te");
	
	System.out.println(exo);
	System.out.println(exo.estResolu());
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
		//COLIGNON
		
		final Exercice exo=new Exercice("textes/texteTest.txt","textes/texteTest.regexp");
		
		
		
		
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
		panSud.setLayout(new GridLayout(2, 1));
		champDeSaisie=new JTextField();
		champDeSaisie.setPreferredSize(new Dimension(200,30));
		JButton BoutonDeReponse=new JButton("Réponse");
		
		JPanel pan=new JPanel();
		pan.add(champDeSaisie);
		pan.add(BoutonDeReponse);
		panSud.add(pan);
		labReponse=new JLabel(affReponse);
		panSud.add(labReponse);
		labReponse.setHorizontalAlignment(JLabel.CENTER);
		labReponse.setVerticalAlignment(JLabel.CENTER);
		BoutonDeReponse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				affReponse="La reponse était : "+exprSolution ;
				labReponse.setText(affReponse);
				champDeSaisie.setText(exprSolution);
				
				
			}
		});
		champDeSaisie.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				

				vert1 = AjoutCouleur.ajouterCouleur(sanscouleur,null,exprSolution);
				if (champDeSaisie.getText().equals("")) {
					texte.setText( AjoutCouleur.ajouterCouleur(sanscouleur,null,exprSolution));
					
					
					
				}else{
					
					texte.setText("<html>"+exo.realiserExercice(champDeSaisie.getText())+"</html>");
					System.out.println(exo);
					//texte.setText( AjoutCouleur.ajouterCouleur(sanscouleur,champDeSaisie.getText(),exprSolution));
					rouge1 = AjoutCouleur.ajouterCouleur(sanscouleur,champDeSaisie.getText(),null);
					
					
				}
				
				
				// verifier => valider & exprSolution 
				/////////////////////////////////////////////////
				//VALIDATION
				///////////////////////////////////////////////
				
				
				
				//System.out.println(vert1);
				
				
				rouge1=rouge1.replaceAll("<u style='color:red'>", "<span style='background:green'>");
				
				rouge1=rouge1.replaceAll("</u>","</span>");
				
				//System.out.println(rouge1);
				
				if(rouge1.equals(vert1)){
					affReponse="Bonne Réponse";
					labReponse.setText(affReponse);
					
				}else{
					affReponse="";
					labReponse.setText(affReponse);
				}
				
				
			
				
				
				//////////////////////////////////////////:
				
				
				
					
			
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
