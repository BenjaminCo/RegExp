package Tests;


import static org.junit.Assert.*;

import org.junit.Test;

import Model.Importer;


public class Tests1 {
	@Test
	public void impoterUnFichierTexte (){
		assertEquals(Importer.importerTexte("textes/texteTest.txt"),"Ce texte est un test\nSur plusieurs lignes\nComme ceci\n");
	}
	
}
