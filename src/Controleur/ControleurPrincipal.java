/*package Controleur;

import java.util.ArrayList;

import Interface.FenetrePrincipal;
import Model.Importer;

/**
 * Permet le lancement de l'application ainsi que d'effectuer les différents traitements
 * @author Benjamin
 *
 */
/*
public class ControleurPrincipal {
	private Exercice exoActuel;
	/**
	 * Lancement de l'application
	 */
	/*public ControleurPrincipal() {
		
		new FenetrePrincipal(this);
		
		//création d'un nouveau exercice
		exoActuel=new Exercice("textes/texteTest.txt","textes/texteTest.regexp");
		
	}
	
	
	
	
	
	
	public static void main(String[] args){
		new ControleurPrincipal();
	}





	//A FAIRE
	public String getTexteAfficher() {
		// TODO Auto-generated method stub
		return null;
	}
	

}*/

package Controleur;

import Interface.FenetrePrincipal;
import Model.Importer;

/**
 * Permet le lancement de l'application ainsi que d'effectuer les différents traitements
 * @author Benjamin
 *
 */
public class ControleurPrincipal {
	private String texteNonModifie;
	private String texteAffiche;
	private String RegExp;
	private String texteATrouver;
	private String texteModifie;
	/**
	 * Lancement de l'application
	 */
	public ControleurPrincipal() {
		// TODO Auto-generated constructor stub
		new FenetrePrincipal(this);
	}
	/**
	 * Permet d'obtenir le texte issu d'un fichier texte transposé au format HTML
	 * @return Une String comportant le texte issu d'un fichier texte transposé au format HTML
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
