package Controleur;

import Interface.FenetrePrincipal;
import Model.Importer;


public class ControleurPrincipal {
	private String texteNonModifie;
	private String texteAffiche;
	public ControleurPrincipal() {
		// TODO Auto-generated constructor stub
		new FenetrePrincipal(this);
	}
	public String getTexteAfficher() {
		// TODO Auto-generated method stub
		texteNonModifie=Importer.importerTexte("textes/texteTest.txt");
		texteAffiche=("<html>"+texteNonModifie+"</html>").replaceAll("\n", "<br/>");
		System.out.println(texteAffiche);
		System.out.println(texteNonModifie);
		return texteAffiche;
	}
	
	public static void main(String[] args){
		new ControleurPrincipal();
	}
	

}
