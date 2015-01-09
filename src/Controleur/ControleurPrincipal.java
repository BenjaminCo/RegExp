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
	/**
	 * Lancement de l'application
	 */
	public ControleurPrincipal() {
		
		new FenetrePrincipal(this);
		
		//création d'un nouveau exercice
		String texte=Importer.importerTexte("textes/texteTest.txt");
		String regExp=Importer.importerExpression("textes/texteTest.regexp");
		exoActuel=new Exercice(texte,regExp);
		
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

}

