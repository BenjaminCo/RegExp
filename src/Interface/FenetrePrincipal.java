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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




import javax.swing.*;

import Controleur.AjoutCouleur;
import Controleur.ControleurPrincipal;
import Controleur.Exercice;

/**
 * Permet l'affichage de l'interface utilisateur
 * 
 * @author Benjamin
 *
 */
public class FenetrePrincipal extends JFrame {
	private JMenuBar barreDeMenu = new JMenuBar();
	private JMenu aide = new JMenu("Aide");
	private JMenuItem ItemAPropos = new JMenuItem("A Propos");
	private JMenuItem Javadoc = new JMenuItem("Javadoc");
	private JFrame cetteFenetre;
	private JLabel texte;
	private JTextField champDeSaisie;
	private ControleurPrincipal monControleur;
	private String sanscouleur = "";
	private String vert = "";
	private String vertrouge = "";

	private String vert1 = "";
	private String rouge1 = "";
	private String affReponse = "";
	private JLabel labReponse;

	private String exprSolution;

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

	public FenetrePrincipal(ControleurPrincipal controleurPrincipal) {
		super("RegExp");
		cetteFenetre = this;
		monControleur = controleurPrincipal;
		this.setSize(new Dimension(500, 400));
		this.setLayout(new BorderLayout());

		this.add(barreDeMenu, BorderLayout.NORTH);
		barreDeMenu.add(aide);
		aide.add(ItemAPropos);
		aide.add(Javadoc);
		ItemAPropos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				A_propos fenetreApropos = new A_propos(cetteFenetre, "A Propos");

			}
		});
		/*
		 * Javadoc.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * 
		 * } });
		 */

		Javadoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
				//Desktop.getDesktop().browse(new URI("C:/Users/nathan/Documents/GitHub/RegExp/doc/index.html"));
					String curDir = System.getProperty("user.dir");
					
					
				
					System.out.println(curDir);
					
					
						curDir=curDir+"\\doc\\index.html";
						
						
					
					System.out.println(curDir);
					
					
					Pattern p = Pattern.compile(curDir);
					
					Matcher m = p.matcher("\\");
					
					StringBuffer sb = new StringBuffer();
					
					while (m.find()) {
					
					m.appendReplacement(sb,"/");
					
					}
					m.appendTail(sb);
					
					System.out.println(sb.toString());
					
					curDir=sb.toString();

					System.out.println(curDir);
					Desktop.getDesktop().browse(new URI("file:///"+curDir));
					
					
					

					
					
					
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
		});
		// COLIGNON

		final Exercice exo = new Exercice("textes/texteTest.txt",
				"textes/texteTest.regexp");

		// AFFICHAGE
		exprSolution = monControleur.getRegExp();
		sanscouleur = monControleur.getTexteAfficher();
		vert = AjoutCouleur.ajouterCouleur(sanscouleur, null, exprSolution);
		// texte=new JLabel(monControleur.getTexteAfficher());

		texte = new JLabel(vert);

		JPanel panMilieu = new JPanel();
		this.add(panMilieu, BorderLayout.CENTER);
		panMilieu.setLayout(new FlowLayout());
		panMilieu.add(texte);

		final JPanel panSud = new JPanel();
		panSud.setLayout(new GridLayout(2, 1));
		champDeSaisie = new JTextField();
		champDeSaisie.setPreferredSize(new Dimension(200, 30));
		JButton BoutonDeReponse = new JButton("Réponse");
		JButton BoutonDAide = new JButton("Aide");

		JPanel pan = new JPanel();
		pan.add(champDeSaisie);
		pan.add(BoutonDeReponse);
		pan.add(BoutonDAide);
		panSud.add(pan);
		labReponse = new JLabel(affReponse);
		panSud.add(labReponse);
		labReponse.setHorizontalAlignment(JLabel.CENTER);
		labReponse.setVerticalAlignment(JLabel.CENTER);
		BoutonDeReponse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				affReponse = "La reponse était : " + exprSolution;
				labReponse.setText(affReponse);
				champDeSaisie.setText(exprSolution);
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

		champDeSaisie.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

				vert1 = AjoutCouleur.ajouterCouleur(sanscouleur, null,
						exprSolution);
				if (champDeSaisie.getText().equals("")) {
					texte.setText(AjoutCouleur.ajouterCouleur(sanscouleur,
							null, exprSolution));

				} else {

					texte.setText(exo.realiserExercice(champDeSaisie.getText()));
					//System.out.println(exo);
					// texte.setText(
					// AjoutCouleur.ajouterCouleur(sanscouleur,champDeSaisie.getText(),exprSolution));
					rouge1 = AjoutCouleur.ajouterCouleur(sanscouleur,
							champDeSaisie.getText(), null);

				}

				// verifier => valider & exprSolution
				// ///////////////////////////////////////////////
				// VALIDATION
				// /////////////////////////////////////////////

				// System.out.println(vert1);

				rouge1 = rouge1.replaceAll("<u style='color:red'>",
						"<span style='background:green'>");

				rouge1 = rouge1.replaceAll("</u>", "</span>");

				// System.out.println(rouge1);

				if (rouge1.equals(vert1)) {
					affReponse = "Bonne Réponse";
					labReponse.setText(affReponse);

				} else {
					affReponse = "";
					labReponse.setText(affReponse);
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
		this.add(panSud, BorderLayout.SOUTH);

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
