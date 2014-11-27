package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import Controleur.ControleurPrincipal;

public class FenetrePrincipal extends JFrame {
	private JMenuBar barreDeMenu=new JMenuBar();
	private JMenu aide=new JMenu("Aide");
	private JMenuItem boutonAPropos=new JMenuItem("A Propos");
	private JFrame cetteFenetre;
	private JLabel texte;
	private JTextField champDeSaisie;
	private String aTrouver;
	private JButton BoutonDeValidation=new JButton("Valider");
	private JButton BoutonDeReponse=new JButton("Reponse");
	/**
	 * @param args
	 */
	public FenetrePrincipal(){
		super("RegExp");
		cetteFenetre=this;
		this.setIconImage(new ImageIcon("logo.png").getImage());
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
	
		// ON FOUT LE TEXTE A TROUVER DANS LE STRING
				// ON CHERCHE HTML
				
				aTrouver=ControleurPrincipal.getTexte();
				String caca = "";
				String caca2 = "";
				String caca3 = "";
				//String texteATester ="<html>Ceci est <font color = red>Squalala</font> le déstructeur ténébreux des 7 chevaliers  de l'apocalypse</html>";
			
				String texteATrouver ="est <font color = red>[^.]+<.font> le";
				Pattern p = Pattern.compile(texteATrouver);
				Matcher m = p.matcher(aTrouver);
				StringBuffer sb = new StringBuffer();
				while (m.find()) {
					 System.out.println("gg");
				     m.appendReplacement(sb, "lol"+m.group()+"lol");
				     caca=m.group();
				     caca2=caca.replaceAll("<font color = red>", ""); 
				     caca3=caca2.replaceAll("</font>", ""); 
				     
				 }
				 String texteATrouverNathan =texteATrouver.replaceAll("<font color = red>", "");
				 String texteATrouverNathanFinal =texteATrouverNathan.replaceAll("<.font>", ""); 
				 m.appendTail(sb);
				/* System.out.println("bwaa");
				 System.out.println(texteATrouverNathanFinal);
				 System.out.println("bwaa");
				 System.out.println(sb.toString());
				 System.out.println(caca);
				 System.out.println(caca3);*/
				
				 // FAUT TROUVER NATHAN FINAL COUNTDOWN
				 //est [^.]+ le
		

		////////////////////////////////////////
		
		JPanel panMilieu=new JPanel();
		this.add(panMilieu,BorderLayout.CENTER);
		panMilieu.setLayout(new FlowLayout());
		panMilieu.add(texte);
		
		JPanel panSud=new JPanel();
		champDeSaisie=new JTextField();
		champDeSaisie.setPreferredSize(new Dimension(200,30));
		/*
		panSud.setLayout(new GridLayout(2,1));
		
		panSud.add(champDeSaisie);
		
		champDeSaisie.setPreferredSize(new Dimension(200,30));
		JPanel panBouton=new JPanel();
		panBouton.setLayout(new FlowLayout());
		panBouton.add(BoutonDeValidation);
		panBouton.add(BoutonDeReponse);
		panSud.add(panBouton);
		*/
		panSud.add(champDeSaisie);
		panSud.add(BoutonDeValidation);
		panSud.add(BoutonDeReponse);
		
		BoutonDeValidation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//texte.setText(AjoutCouleur.ajouterCouleur(texte.getText(), champDeSaisie.getText()));
				if(champDeSaisie.getText().equals("est [^.]+ le")){
					
					JOptionPane.showMessageDialog(null, "Bonne réponse !");
				}else{
					JOptionPane.showMessageDialog(null, "Mauvaise réponse !");
				}
			}
		});
		BoutonDeReponse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "La réponse est : est [^.]+ le");
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
