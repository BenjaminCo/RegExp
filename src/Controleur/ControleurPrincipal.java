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
	Exercice exoActuel;
	/**
	 * Lancement de l'application
	 */
	public ControleurPrincipal() {
		
		new FenetrePrincipal(this);
		
		//création d'un nouveau exercice
		exoActuel=new Exercice("textes/texteTest.txt","textes/texteTest.regexp");
		
	}
	
	
	
	
	
	
	public static void main(String[] args){
		new ControleurPrincipal();
	}





	//A FAIRE
	public String getTexteAfficher() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
