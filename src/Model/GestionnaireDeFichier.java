package Model;

import java.util.ArrayList;

import Controleur.Exercice;

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
	 *Importe directement les données necessaire à la création d'un nouvel exercice.
	 *Ainsi on obtient le texte du premier fichier par ordre alphabétique du repertoire {@code pathRepertoire}.
	 *Et la première ligne du fichier .regexp correspondant correspondant à une expression régulière.
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
	 *Importe la ligne suivante du fichier .regexp correspondant à une expression régulière.
	 *Sinon si on arrive au bout du fichier .regexp: 
	 *	Importe le texte du fichier suivant par ordre alphabétique du repertoire {@code pathRepertoire}.
	 *	Et la première ligne du fichier .regexp correspondant correspondant à une expression régulière.
	 */
	public void exerciceSuivant() {
		if(indiceLigneFichierRegexp < nombreDeLigneDuFichierRegexp){
			indiceLigneFichierRegexp++;
			setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
		}else{
			indiceFichierActuel++;
			nomFichierActuel=nomsFichiers.get(indiceFichierActuel);
			nombreDeLigneDuFichierRegexp=Importer.getNombreLigneFichier(pathRepertoire+"/"+nomFichierActuel+".regexp");
			setTexte(Importer.importerTexte(pathRepertoire+"/"+nomFichierActuel+".txt"));
			
			indiceLigneFichierRegexp=1;
			setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
		}
		
	}
	
	//Mappage pour vérifier si on a fait toutes les lignes de regexp(sans ordre) , une fois que l'on a fait toutes les lignes on retourne un boolean visant 
	// a faire changer le joueur d'exercice
	
	
	//Si l'utilisteur veut vraiment changer de regExp a deviner (fichier .regexp)
	public void RegexpSuivant(){
		
		if(indiceLigneFichierRegexp < nombreDeLigneDuFichierRegexp){
			indiceLigneFichierRegexp++;
			setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
		}
	}
	
	public void RegexpPrecedent(){
		
		if(indiceLigneFichierRegexp >1){
			indiceLigneFichierRegexp--;
			setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
		}
	}
	
	
	//Si l'utilisateur veut vraiment changer d'exercice (fichier texte)
	public void exercicePlusUn(){
		indiceFichierActuel++;
		nomFichierActuel=nomsFichiers.get(indiceFichierActuel);
		setTexte(Importer.importerTexte(pathRepertoire+"/"+nomFichierActuel+".txt"));
		indiceLigneFichierRegexp=1;
		setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
	}
	
	public void exerciceMoinsUn(){
		indiceFichierActuel--;
		nomFichierActuel=nomsFichiers.get(indiceFichierActuel);
		setTexte(Importer.importerTexte(pathRepertoire+"/"+nomFichierActuel+".txt"));
		indiceLigneFichierRegexp=1;
		setRegexp(Importer.importerExpression(pathRepertoire+"/"+nomFichierActuel+".regexp", indiceLigneFichierRegexp));
	}
	
	
	/**
	 * Permet d'obtenir le texte du fichier dernièrement importé.
	 * @return une String comportant le texte du fichier dernièrement importé.
	 */
	public String getTexte() {
		return texte;
	}
	
	private void setTexte(String texte) {
		this.texte = texte;
	}
	/**
	 * Permet d'obtenir l'expression régulière du fichier dernièrement importé.
	 * @return une String comportant l'expression du fichier dernièrement importé.
	 */
	public String getRegexp() {
		return regexp;
	}
	
	private void setRegexp(String regexp) {
		this.regexp = regexp;
	}
}
