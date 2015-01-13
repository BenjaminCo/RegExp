package Controleur;

import java.util.ArrayList;

import Interface.FenetrePrincipal;
import Model.Importer;

/**
 * Permet le lancement de l'application ainsi que d'effectuer les différents traitements
 * @author Benjamin
 *
 */

public class ControleurPrincipal {
	private Exercice exoActuel;
	private ArrayList<String> nomsFichiersTxt;
	/**
	 * Lancement de l'application
	 */
	public ControleurPrincipal() {
		
		
		
		//création d'un nouveau exercice
		nomsFichiersTxt=Importer.importerTextesRepertoire("textes");
		
		
		
		String texte=Importer.importerTexte("textes/texteTest.txt");
		String regExp=Importer.importerExpression("textes/texteTest.regexp",1);
		
		exoActuel=new Exercice(texte,regExp);
		
		new FenetrePrincipal(this);
		
	}
	
	public static void main(String[] args){
		new ControleurPrincipal();
	}

	public String resoudreExercice(String regexpUtilisateur) {
		
		return exoActuel.realiserExercice(regexpUtilisateur);
	}
	
	public boolean estExerciceResolu(){
		return exoActuel.estResolu();
	}

	public String getExerciceSolution(){
		return exoActuel.getSolution();
	}
}

