package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Exporter;
import Model.Importer;

/**
 * Teste les fonctions d'exportation
 * @author Benjamin
 *
 */
public class testExporter {
	
	@Test
	public void testAjouterLigne() {
		String nouvelleExpressionReguliere="je suis le test: "+Math.random();
		String pathFichier="textes/Test.regexp";
		Exporter.ajouterLigne(pathFichier, nouvelleExpressionReguliere);
		int nbligne=Importer.getNombreLigneFichier(pathFichier);
		assertEquals(nouvelleExpressionReguliere,Importer.importerLigne(pathFichier, nbligne));
		
	}

}
