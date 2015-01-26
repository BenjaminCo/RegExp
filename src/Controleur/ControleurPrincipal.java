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
	public void nouvelExercice() {
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
	
	public void nouvelExercice(String choix) {
		if(monGestionnaireDeFichier==null){
			
			monGestionnaireDeFichier=new GestionnaireDeFichier();
			String texte=monGestionnaireDeFichier.getTexte();
			String regExp=monGestionnaireDeFichier.getRegexp();
			exoActuel=new Exercice(texte,regExp);
			
		}else{
			/////////////////////////////////////
			switch(choix){
			
			case "RegExpPrecedent":
				monGestionnaireDeFichier.RegexpPrecedent();
				
			break;
			case "RegExpSuivant":
				monGestionnaireDeFichier.RegexpSuivant();
				
			break;
			case "ExoPrecedent":
				monGestionnaireDeFichier.exerciceMoinsUn();
				
			break;
			case "ExoSuivant":
				monGestionnaireDeFichier.exercicePlusUn();
				
			break;
			
			
			}

			
			////////////////////////////
			String texte=monGestionnaireDeFichier.getTexte();
			String regExp=monGestionnaireDeFichier.getRegexp();
			exoActuel=new Exercice(texte,regExp);
		}
	}
	
	
	
	
	public GestionnaireDeFichier getGestionnaireDeFichier(){
		return monGestionnaireDeFichier;
	}
	
	/**
	 * Lancement de l'application
	 * @param args rien est attendu
	 */
	public static void main(String[] args){
		new ControleurPrincipal();
	}


		
}

