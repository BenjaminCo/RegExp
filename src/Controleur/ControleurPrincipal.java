package Controleur;

import java.util.ArrayList;

import Interface.FenetrePrincipal;
import Model.Importer;

/**
 * Permet le lancement de l'application ainsi que d'effectuer les diff�rents traitements
 * @author Benjamin
 *
 */
public class ControleurPrincipal {
	private String texteNonModifie;
	private String texteAffiche;
	private String RegExp;
	private String texteATrouver;
	private String texteModifie;
	
	
	private ArrayList<Plage> solution;//� initialiser
	private ArrayList<Plage> utilisateur;//� initialiser
	
	/**
	 * Lancement de l'application
	 */
	public ControleurPrincipal() {
		// TODO Auto-generated constructor stub
		new FenetrePrincipal(this);
		
		
	}
	
	
	
	
	
	/**
	 * Permet d'obtenir le texte issu d'un fichier texte transpos� au format HTML
	 * @return Une String comportant le texte issu d'un fichier texte transpos� au format HTML
	 */
	public String getTexteAfficher() {
		// TODO Auto-generated method stub
		texteNonModifie=Importer.importerTexte("textes/texteTest.txt");
		//RegExp=Importer.importerTexte("textes/texteTest.regexp");
		//texteModifie=AjoutCouleur.ajouterCouleur(texteNonModifie, RegExp);
		//System.out.println("cc");
		//System.out.println(texteModifie);
		
		//System.out.println(texteAffiche);
		
		return texteNonModifie;
	}
	
	public String getRegExp() {
		// TODO Auto-generated method stub
		texteNonModifie=Importer.importerExpression("textes/texteTest.regexp");
		//RegExp=Importer.importerTexte("textes/texteTest.regexp");
		//texteModifie=AjoutCouleur.ajouterCouleur(texteNonModifie, RegExp);
		//System.out.println("cc");
		//System.out.println(texteModifie);
		
		//System.out.println(texteAffiche);
		
		return texteNonModifie;
	}
	
	public static void main(String[] args){
		new ControleurPrincipal();
	}
	

}
