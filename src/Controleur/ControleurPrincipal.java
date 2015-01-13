package Controleur;

import java.util.ArrayList;

import Interface.FenetrePrincipal;
import Model.Importer;
import Model.GestionnaireDeFichier;

/**
 * Permet le lancement de l'application ainsi que d'effectuer les différents traitements
 * @author Benjamin
 *
 */

public class ControleurPrincipal {
	private Exercice exoActuel;
	//private ArrayList<String> nomsFichiersTxt;
	private GestionnaireDeFichier monGestionnaireDeFichier=null;
	/**
	 * Lancement de l'application
	 */
	public ControleurPrincipal() {
		
		
		
		//création d'un nouveau exercice
		//nomsFichiersTxt=Importer.importerTextesRepertoire("textes");
		
		
		
		//String texte=Importer.importerTexte("textes/texteTest.txt");
		//String regExp=Importer.importerExpression("textes/texteTest.regexp",1);
		
		//exoActuel=new Exercice(texte,regExp);
		nouvelExercice();
		
		new FenetrePrincipal(this);
		
	}
	
	/**
	 * Tente de réaliser l'execice actuel.
	 * @param regexpUtilisateur l'expression réguliére entrée par l'utilisateur.
	 * @return Une String comportant le html représentant la coloration solution et utilisateur.
	 */
	public String resoudreExercice(String regexpUtilisateur) {
		
		return exoActuel.realiserExercice(regexpUtilisateur);
	}
	
	/**
	 * Vérifie si dans l'exercice actuel l'expression régulière entrée par l'utilisateur sélèctionne le même texte que l'expression régulière solution.
	 * @return Vrai si l'expression régulière entrée par l'utilisateur sélèctionne le même texte que l'expression régulière solution.
	 */
	public boolean estExerciceResolu(){
		return exoActuel.estResolu();
	}

	/**
	 * Obtenir l'expression réguliére solution de l'exercice actuel.
	 * @return Une String comportant l'expression réguliére
	 */
	public String getExerciceSolution(){
		return exoActuel.getSolution();
	}
	
	/**
	 * Permet créer ou un nouvel exercice ou de passer é l'exercice suivant si il en existe deja un.
	 */
	private void nouvelExercice() {
		if(monGestionnaireDeFichier==null){
			monGestionnaireDeFichier=new GestionnaireDeFichier();
			String texte=monGestionnaireDeFichier.getTexte();
			String regExp=monGestionnaireDeFichier.getRegexp();
			exoActuel=new Exercice(texte,regExp);
		}else{
			monGestionnaireDeFichier.exerciceSuivant();
			String texte=monGestionnaireDeFichier.getTexte();
			String regExp=monGestionnaireDeFichier.getRegexp();
			exoActuel=new Exercice(texte,regExp);
		}
	}
	
	/**
	 * Lancement de l'application
	 * @param args rien est attendu
	 */
	public static void main(String[] args){
		new ControleurPrincipal();
	}


		
}

