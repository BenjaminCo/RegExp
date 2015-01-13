package Model;

import java.util.ArrayList;

public class GestionnaireDeFichier {
	
	public static String pathRepertoire="textes";
	
	private ArrayList<String> nomsFichiers;
	private int indiceFichierActuel;
	private int indiceLigneFichierRegexp;
	private int nombreDeLigneDuFichierRegexp;
	private String nomFichierActuel;
	
	private String texte;
	private String regexp;
	/**
	 *Importe directement les donn�es necessaire � la cr�ation d'un nouvel exercice.
	 *Ainsi on obtient le texte du premier fichier par ordre alphab�tique du repertoire {@code pathRepertoire}.
	 *Et la premi�re ligne du fichier .regexp correspondant correspondant � une expression r�guli�re.
	 */
	public GestionnaireDeFichier() {
		
		nomsFichiers=Importer.importerTextesRepertoire(pathRepertoire);
		
		indiceFichierActuel=0;
		nomFichierActuel=nomsFichiers.get(indiceFichierActuel);
		nombreDeLigneDuFichierRegexp=Importer.getNombreLigneFichier(pathRepertoire+"/"+nomFichierActuel+".regexp");
		
		
		setTexte(Importer.importerTexte(pathRepertoire+"/"+nomFichierActuel+".txt"));
		
		indiceLigneFichierRegexp=1;
		setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
		
	
	}
	/**
	 *Importe la ligne suivante du fichier .regexp correspondant correspondant � une expression r�guli�re.
	 *Sinon si on arrive au bout du fichier .regexp: 
	 *	Importe le texte du fichier suivant par ordre alphab�tique du repertoire {@code pathRepertoire}.
	 *	Et la premi�re ligne du fichier .regexp correspondant correspondant � une expression r�guli�re.
	 */
	public void exerciceSuivant() {
		if(indiceLigneFichierRegexp < nombreDeLigneDuFichierRegexp){
			indiceLigneFichierRegexp++;
			setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
		}else{
			indiceFichierActuel++;
			nomFichierActuel=nomsFichiers.get(indiceFichierActuel);
			setTexte(Importer.importerTexte(pathRepertoire+"/"+nomFichierActuel+".txt"));
			
			indiceLigneFichierRegexp=1;
			setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
		}
		
	}
	/**
	 * Permet d'obtenir le texte du fichier derni�rement import�.
	 * @return une String comportant le texte du fichier derni�rement import�.
	 */
	public String getTexte() {
		return texte;
	}
	
	private void setTexte(String texte) {
		this.texte = texte;
	}
	/**
	 * Permet d'obtenir l'expression r�guli�re du fichier derni�rement import�.
	 * @return une String comportant l'expression du fichier derni�rement import�.
	 */
	public String getRegexp() {
		return regexp;
	}
	
	private void setRegexp(String regexp) {
		this.regexp = regexp;
	}
}