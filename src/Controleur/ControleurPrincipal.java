package Controleur;

import Model.Importer;

public class ControleurPrincipal {

	public static String getTexte() {
		// TODO Auto-generated method stub
		return Importer.importerTexte("textes/texteTest.txt");
	}

}
