package Interface;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controleur.ControleurPrincipal;
import Model.GestionnaireDeFichier;

/**
 * Permet l'affichage de l'interface utilisateur
 * 
 * @author Benjamin
 *
 */
public class FenetrePrincipal extends JFrame {

	private JMenuBar barreDeMenu = new JMenuBar();

	private JMenu aide = new JMenu("Aide");
	private JMenu commande = new JMenu("Commande");
	
	private JMenuItem ItemAPropos = new JMenuItem("A Propos");
	private JMenuItem RegExpPrecedent = new JMenuItem("RegExpPrecedent");
	private JMenuItem RegExpSuivant = new JMenuItem("RegExpSuivant");
	private JMenuItem ExoPrecedent = new JMenuItem("ExoPrecedent");
	private JMenuItem ExoSuivant = new JMenuItem("ExoSuivant");
	

	private JFrame cetteFenetre;
	private JLabel texte;
	private JTextField champDeSaisie = new JTextField();;

	private JButton BoutonDeReponse = new JButton("Réponse");
	private JButton BoutonDAide = new JButton("Aide");
	private JButton changerExo = new JButton("switch");

	private ControleurPrincipal monControleur;
	private ControleurPrincipal monControleur1;

	private String affReponse = "";
	private JLabel labReponse = new JLabel();;

	private String exprSolution;
	private int i;

	/**
	 * Affiche la fenetre principale de l'application
	 * 
	 * @param controleurPrincipal
	 *            Le controleurPrincipal de l'application
	 * 
	 */

	/*
	 * Exercice exo=new
	 * Exercice("textes/texteTest.txt","textes/texteTest.regexp");
	 * exo.realiserExercice("ce|te");
	 * 
	 * System.out.println(exo); System.out.println(exo.estResolu());
	 */

	public FenetrePrincipal(final ControleurPrincipal controleurPrincipal) {
		super("RegExp");
		cetteFenetre = this;
		monControleur = controleurPrincipal;
		this.setSize(new Dimension(500, 300));
		this.setLayout(new BorderLayout());

		this.add(barreDeMenu, BorderLayout.NORTH);
		barreDeMenu.add(commande);
		barreDeMenu.add(aide);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../logoPtut.jpg")));

		aide.add(ItemAPropos);
		commande.add(RegExpPrecedent);
		commande.add(RegExpSuivant);
		commande.add(ExoPrecedent);
		commande.add(ExoSuivant);

		ItemAPropos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				A_propos fenetreApropos = new A_propos(cetteFenetre, "A Propos");

			}
		});
		
		RegExpPrecedent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				monControleur.nouvelExercice("RegExpPrecedent");
				texte.setText(monControleur.resoudreExercice(null));

			}
		});
		
		RegExpSuivant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				monControleur.nouvelExercice("RegExpSuivant");
				texte.setText(monControleur.resoudreExercice(null));
			}
		});
		
		ExoPrecedent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				monControleur.nouvelExercice("ExoPrecedent");
				texte.setText(monControleur.resoudreExercice(null));
			}
		});
		
		ExoSuivant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				monControleur.nouvelExercice("ExoSuivant");
				texte.setText(monControleur.resoudreExercice(null));
				

			}
		});
		

		// AFFICHAGE

		JPanel conteneur = new JPanel(new BorderLayout());

		JPanel top = new JPanel();
		JPanel mid = new JPanel();
		JPanel bot = new JPanel();

		conteneur.add(top, BorderLayout.NORTH);
		conteneur.add(mid, BorderLayout.CENTER);
		conteneur.add(bot, BorderLayout.SOUTH);

		
		/*top.setBackground(new Color(169, 56, 56));
		 * 
		 * 	mid.setBackground(new Color(125, 174, 163));
		 * 
		 */
	
		top.setBackground(new Color(233, 0, 0));
		mid.setBackground(new Color(243, 251, 0));
		bot.setBackground(new Color(9, 154, 38));
		
		mid.setPreferredSize(new Dimension(100, 100));

		// ---------------------------------------------------
		// Panel TOP
		// ---------------------------------------------------
		texte = new JLabel(monControleur.resoudreExercice(null));

		JPanel petitN = new JPanel();

		petitN.setBorder(new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED),
				new EmptyBorder(0, 0, 0, 0)));
		petitN.add(texte);
		texte.setFont(new Font("Calibri", Font.PLAIN, 15));
		top.add(petitN);
		
		// ---------------------------------------------------
		// Panel MID
		// ---------------------------------------------------

		JPanel petitCentre = new JPanel();

		petitCentre.setLayout(new FlowLayout());

		TitledBorder title;
		title = BorderFactory
				.createTitledBorder("Veuillez saisir votre regexp :");
		petitCentre.setBorder(title);

		champDeSaisie.setPreferredSize(new Dimension(200, 25));

		petitCentre.add(champDeSaisie);
		petitCentre.add(BoutonDeReponse);
		petitCentre.add(BoutonDAide);

		BoutonDeReponse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				affReponse = "La reponse était : "
						+ monControleur.getExerciceSolution();
				labReponse.setText(affReponse);
				champDeSaisie.setText(exprSolution);
				// labReponse.setCursor(null);

			}
		});
		BoutonDAide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				labReponse
						.setText("http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html");
				labReponse.setCursor(new Cursor(Cursor.HAND_CURSOR));
				addListener(labReponse);

			}
		});

		mid.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED),
				new EmptyBorder(0, 0, 0, 0)));
		mid.add(petitCentre);

		champDeSaisie.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (champDeSaisie.getText().equals("")) {
					texte.setText(monControleur.resoudreExercice(null));

				} else {
					
					texte.setText(monControleur.resoudreExercice(champDeSaisie
							.getText()));
					
					

				}

				// verifier => valider & exprSolution
				// ///////////////////////////////////////////////
				// VALIDATION
				// /////////////////////////////////////////////


				try {
				if (monControleur.estExerciceResolu()) {
					affReponse = "Bonne Réponse !";
					labReponse.setText(affReponse);
					
						wait(2);
						
					} else {
					affReponse = "";
					labReponse.setText(affReponse);
				}}catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				

			} 

				// ////////////////////////////////////////:

				// System.out.println("saisie : "+champDeSaisie.getText());
				// System.out.println("vert rouge : "+vertrouge);
				// texte.setText(AjoutCouleur.ajouterCouleur(monControleur.getTexteAfficher(),
				// champDeSaisie.getText(),"red"));

				// System.out.println(AjoutCouleur.ajouterCouleur(monControleur.getTexteAfficher(),
				// champDeSaisie.getText(),"red"));

				// ccc
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		// ---------------------------------------------------
		// Panel BOT
		// ---------------------------------------------------

		bot.setLayout(new FlowLayout());
		bot.add(labReponse);

		bot.add(changerExo);
		changerExo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				monControleur.nouvelExercice();
				texte.setText(monControleur.resoudreExercice(null));
			}
		});
		
		
		this.add(conteneur);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void addListener(JLabel lb_url) {

		lb_url.addMouseListener(new MouseAdapter() {
			// Click sur le lien

			public void mouseClicked(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				String plainText = label.getText().replaceAll("\\<.*?\\>", "");
				try {
					Desktop.getDesktop().browse(new URI(plainText));
				} catch (URISyntaxException ex) {
					Logger.getLogger(FenetrePrincipal.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(FenetrePrincipal.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}

			// Survol sur le lien
			public void mouseEntered(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				String plainText = label.getText().replaceAll("\\<.*?\\>", "");
				// Sousligner le texte
				String urlText = "<html><u>" + plainText + "</u></html>";
				label.setText(urlText);
			}

			// Quitte la zone du lien
			public void mouseExited(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				String plainText = label.getText().replaceAll("\\<.*?\\>", "");
				// Texte sans souslignage
				String urlText = "<html>" + plainText + "</html>";
				label.setText(urlText);
			}
		});
	}

}
