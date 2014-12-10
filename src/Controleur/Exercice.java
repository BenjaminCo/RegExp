package Controleur;

import java.util.ArrayList;

import Model.Importer;
/**
 * Un exercice
 * @author Etudiant
 *
 */
public class Exercice {
	private String texte;
	private String regExp;
	
	private ArrayList<Plage> solution;
	private ArrayList<Plage> utilisateur;//à initialiser
	
	/**
	 * Crée un nouvel exercice 
	 * @param pathTexte l'emplacement du fichier comportant le texte
	 * @param pathRegexp l'emplacement du fichier comportant le path
	 */
	public Exercice(String pathTexte,String pathRegexp) {
		texte=Importer.importerTexte(pathTexte);
		regExp=Importer.importerExpression(pathRegexp);
		solution=Analyse.analyser(texte, regExp);
		
	}

	@Override
	public String toString() {
		return "Exercice [texte=" + texte + ", regExp=" + regExp
				+ ", solution=" + solution + ", utilisateur=" + utilisateur
				+ "]";
	}
	
	//Ceci est un test
	public static void main(String[] args) {
		System.out.println(new Exercice("textes/texteTest.txt","textes/texteTest.regexp"));
	}
	
}
