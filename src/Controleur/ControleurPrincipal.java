package Controleur;



import Interface.FenetrePrincipal;

import Model.GestionnaireDeFichier;

/**
 * Permet le lancement de l'application ainsi que d'effectuer les diff�rents traitements
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
	 * Tente de r�aliser l'execice actuel.
	 * @param regexpUtilisateur l'expression r�guli�re entr�e par l'utilisateur.
	 * @return Une String comportant le html repr�sentant la coloration solution et utilisateur.
	 */
	public String resoudreExercice(String regexpUtilisateur) {
		
		return exoActuel.realiserExercice(regexpUtilisateur);
	}
	
	/**
	 * V�rifie si dans l'exercice actuel l'expression r�guli�re entr�e par l'utilisateur s�l�ctionne le m�me texte que l'expression r�guli�re solution.
	 * @return Vrai si l'expression r�guli�re entr�e par l'utilisateur s�l�ctionne le m�me texte que l'expression r�guli�re solution.
	 */
	public boolean estExerciceResolu(){
		return exoActuel.estResolu();
	}

	/**
	 * Obtenir l'expression r�guli�re solution de l'exercice actuel.
	 * @return Une String comportant l'expression r�guli�re
	 */
	public String getExerciceSolution(){
		return exoActuel.getSolution();
	}
	
	/**
	 * Permet cr�er ou un nouvel exercice ou de passer � l'exercice suivant si il en existe deja un.
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

