package Tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import Model.Importer;

public class testImporter {

	@Test
	public void testImpoerterExpression() {
		assertEquals("plusieurs",Model.Importer.importerExpression("textes/texteTest.regexp", 2));
	}

	
	@Test
	public void testAssociation_Texte_Avec_TexteRegExp(){
		Map <String,String> map = Importer.Association_Texte_Regxp();
		String mapok ="{texte3.txt=texte3.regExp, texteTest.txt=texteTest.regExp, texte1.txt=texte1.regExp, texte2.txt=texte2.regExp}";
		assertEquals(mapok,map.toString());
		
	}
}
