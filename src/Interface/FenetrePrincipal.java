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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import Model.Pays;

/**
 * Permet l'affichage de l'interface utilisateur
 * 
 * @author Benjamin
 *
 */
@SuppressWarnings("serial")
public class FenetrePrincipal extends JFrame {

	private JMenuBar barreDeMenu = new JMenuBar();

	private JMenu aide = new JMenu("Aide");
	private JMenu commande = new JMenu("Commande");
	private JMenu OptionPays = new JMenu("Pays");

	private JMenuItem ItemAPropos = new JMenuItem("A Propos");
	private JMenuItem RegExpPrecedent = new JMenuItem("RegExp Precedent");
	private JMenuItem RegExpSuivant = new JMenuItem("RegExp Suivant");
	private JMenuItem TextePrecedent = new JMenuItem("Texte Precedent");
	private JMenuItem TexteSuivant = new JMenuItem("Texte Suivant");

	private JFrame cetteFenetre;
	private JLabel texte;
	private JTextField champDeSaisie = new JTextField();

	private JButton BoutonDeReponse = new JButton("Réponse");
	private JButton BoutonDAide = new JButton("Aide");
	private JButton changerExo = new JButton("Switch");

	private ControleurPrincipal monControleur;

	private String affReponse = "";
	private JLabel labReponse = new JLabel();

	private String exprSolution;
	private static int i;

	static Pays tab[] = Pays.values();
	private JMenuItem tabOption[] = new JMenuItem[tab.length];

	/**
	 * Affiche la fenetre principale de l'application
	 * 
	 * @param controleurPrincipal
	 *            Le controleurPrincipal de l'application
	 * 
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
		barreDeMenu.add(OptionPays);

		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				this.getClass().getResource("../logo.png")));

		aide.add(ItemAPropos);
		commande.add(RegExpPrecedent);
		commande.add(RegExpSuivant);
		commande.add(TextePrecedent);
		commande.add(TexteSuivant);

		ItemAPropos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
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

		TextePrecedent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				monControleur.nouvelExercice("ExoPrecedent");
				texte.setText(monControleur.resoudreExercice(null));
			}
		});

		TexteSuivant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				monControleur.nouvelExercice("ExoSuivant");
				texte.setText(monControleur.resoudreExercice(null));

			}
		});

		// AFFICHAGE

		JPanel conteneur = new JPanel(new BorderLayout());

		final JPanel top = new JPanel();
		final JPanel mid = new JPanel();
		final JPanel bot = new JPanel();

		conteneur.add(top, BorderLayout.NORTH);
		conteneur.add(mid, BorderLayout.CENTER);
		conteneur.add(bot, BorderLayout.SOUTH);

		top.setBackground(new Color(233, 0, 0));
		mid.setBackground(new Color(243, 251, 0));
		bot.setBackground(new Color(9, 154, 38));

		for (i = 0; i < tab.length; i++) {
			tabOption[i] = new JMenuItem(tab[i].getName());
			OptionPays.add(tabOption[i]);
			tabOption[i].setActionCommand(tabOption[i].getName());
		}

		/* Mise en place des actions listenner pour changer les couleurs */

		for (i = 0; i < tabOption.length; i++) {
			final Pays colorPays = tab[i];
			tabOption[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (e.getActionCommand().equals(colorPays.getName())) {

						top.setBackground(colorPays.getColorTop());
						mid.setBackground(colorPays.getColorMid());
						bot.setBackground(colorPays.getColorBot());

					}
				}
			});

		}

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

				labReponse.addMouseListener(new MouseAdapter() {

					public void mouseEntered(MouseEvent e) {
						JLabel label = (JLabel) e.getSource();
						String plainText = label.getText().replaceAll(
								"\\<.*?\\>", "");

						label.setText(plainText);
					}

				});
				reponse();

			}
		});

		BoutonDeReponse.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					reponse();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

		BoutonDAide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				aide();
			}
		});

		BoutonDAide.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					aide();
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

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
				// //////////////////////////////////////////////

				try {
					if (monControleur.estExerciceResolu()) {
						affReponse = "Bonne Réponse !";
						labReponse.setText(affReponse);

						wait(2);

					} else {
						affReponse = "";
						labReponse.setText(affReponse);
					}
				} catch (InterruptedException e1) {

					e1.printStackTrace();

				}
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

		changerExo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					monControleur.nouvelExercice();
					texte.setText(monControleur.resoudreExercice(null));
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

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

	private void reponse() {
		affReponse = "La reponse était : "
				+ monControleur.getExerciceSolution();
		labReponse.setText(affReponse);
		champDeSaisie.setText(exprSolution);
		labReponse.setCursor(Cursor.getDefaultCursor());
	}

	private void aide() {
		labReponse
		.setText("http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html");
		labReponse.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addListener(labReponse);
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
				// Souligner le texte
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
