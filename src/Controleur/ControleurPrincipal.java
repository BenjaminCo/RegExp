package Controleur;

import Interface.FenetrePrincipal;
import Model.GestionnaireDeFichier;

/**
 * Permet le lancement de l'application ainsi que d'effectuer les diff�rents
 * traitements
 * 
 * @author Benjamin
 *
 */

public class ControleurPrincipal {
	private Exercice exoActuel;
	private GestionnaireDeFichier monGestionnaireDeFichier = null;

	private boolean modeAdmin;

	/**
	 * Lancement de l'application
	 */
	public ControleurPrincipal() {

		modeAdmin = false;

		nouvelExercice();

		new FenetrePrincipal(this);

	}

	public boolean isModeAdmin() {
		return modeAdmin;
	}

	/**
	 * Permet de passer du mode normal au mode administrateur et vice-versa
	 */
	public void changeMode() {
		if (modeAdmin) {
			modeAdmin = false;
			String texte = monGestionnaireDeFichier.getTexte();
			String regExp = monGestionnaireDeFichier.getRegexp();
			exoActuel = new Exercice(texte, regExp);
		} else {
			modeAdmin = true;
			String texte = monGestionnaireDeFichier.getTexte();
			exoActuel = new Exercice(texte, null);
		}
	}

	/**
	 * Ajouter une expression r�guli�re � l'exercice actuel
	 * 
	 * @param expression
	 *            Une String comportant l'expression r�guli�re � ajouter.
	 */
	public void ajouterExpression(String expression) {
		monGestionnaireDeFichier.ajouterExpression(expression);
	}

	/**
	 * Tente de r�aliser l'execice actuel.
	 * 
	 * @param regexpUtilisateur
	 *            l'expression r�guli�re entr�e par l'utilisateur.
	 * @return Une String comportant le html repr�sentant la coloration solution
	 *         et utilisateur.
	 */
	public String resoudreExercice(String regexpUtilisateur) {

		return exoActuel.realiserExercice(regexpUtilisateur);
	}

	/**
	 * V�rifie si dans l'exercice actuel l'expression r�guli�re entr�e par
	 * l'utilisateur s�l�ctionne le m�me texte que l'expression r�guli�re
	 * solution.
	 * 
	 * @return Vrai si l'expression r�guli�re entr�e par l'utilisateur
	 *         s�l�ctionne le m�me texte que l'expression r�guli�re solution.
	 */
	public boolean estExerciceResolu() {
		return exoActuel.estResolu();
	}

	/**
	 * Obtenir l'expression r�guli�re solution de l'exercice actuel.
	 * 
	 * @return Une String comportant l'expression r�guli�re
	 */
	public String getExerciceSolution() {
		return exoActuel.getSolution();
	}

	/**
	 * Permet de cr�er ou un nouvel exercice ou de passer � l'exercice suivant
	 * si il en existe deja un.
	 */
	public void nouvelExercice() {
		if (modeAdmin) {
			if (monGestionnaireDeFichier == null) {
				monGestionnaireDeFichier = new GestionnaireDeFichier();
			} else {
				monGestionnaireDeFichier.exercicePlusUn();
			}

			String texte = monGestionnaireDeFichier.getTexte();
			exoActuel = new Exercice(texte, null);
		} else {
			if (monGestionnaireDeFichier == null) {

				monGestionnaireDeFichier = new GestionnaireDeFichier();

			} else {
				monGestionnaireDeFichier.exerciceSuivant();

			}
			String texte = monGestionnaireDeFichier.getTexte();
			String regExp = monGestionnaireDeFichier.getRegexp();
			exoActuel = new Exercice(texte, regExp);
		}
	}

	public void nouvelExercice(String choix) {
		if (monGestionnaireDeFichier == null) {

			monGestionnaireDeFichier = new GestionnaireDeFichier();
			String texte = monGestionnaireDeFichier.getTexte();
			String regExp = monGestionnaireDeFichier.getRegexp();
			exoActuel = new Exercice(texte, regExp);

		} else {
			
			switch (choix) {

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
			
			String texte = monGestionnaireDeFichier.getTexte();
			String regExp = monGestionnaireDeFichier.getRegexp();
			if (modeAdmin) {
				exoActuel = new Exercice(texte, null);
			} else {
				exoActuel = new Exercice(texte, regExp);
			}

		}
	}

	public GestionnaireDeFichier getGestionnaireDeFichier() {
		return monGestionnaireDeFichier;
	}

	/**
	 * Lancement de l'application
	 * 
	 * @param args
	 *            rien est attendu
	 */
	public static void main(String[] args) {
		new ControleurPrincipal();
	}

	public void choixExercice(String string, int indice) {

		monGestionnaireDeFichier.choixExercice(string, indice);
		String texte = monGestionnaireDeFichier.getTexte();
		String regExp = monGestionnaireDeFichier.getRegexp();
		if (modeAdmin) {
			exoActuel = new Exercice(texte, null);
		} else {
			exoActuel = new Exercice(texte, regExp);
		}
	}

}
