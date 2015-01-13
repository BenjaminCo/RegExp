package Controleur;



import Interface.FenetrePrincipal;
import Model.GestionnaireDeFichier;


/**
 * Permet le lancement de l'application ainsi que d'effectuer les différents traitements
 * @author Benjamin
 *
 */

public class ControleurPrincipal {
	private Exercice exoActuel;
	private GestionnaireDeFichier monGestionnaireDeFichier=null;
	
	/**
	 * Lancement de l'application
	 */
	public ControleurPrincipal() {
		
		
		nouvelExercice();
		
		new FenetrePrincipal(this);
		
	}
	/**
	 * Permet créer ou un nouvel exercice ou de passer à l'exercice suivant si il en existe deja un.
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
	 * Tente de réaliser l'execice actuel.
	 * @param regexpUtilisateur l'expression régulière entrée par l'utilisateur.
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
	 * Obtenir l'expression régulière solution de l'exercice actuel.
	 * @return Une String comportant l'expression régulière
	 */
	public String getExerciceSolution(){
		return exoActuel.getSolution();
	}
	
	/**
	 * Lancement de l'application
	 * @param args rien est attendu
	 */
	public static void main(String[] args){
		new ControleurPrincipal();
	}
}

