package Tests;

import static org.junit.Assert.*;
import org.junit.Test;


public class testImporter {

	@Test
	public void testImpoerterExpression() {
		assertEquals("plusieurs",Model.Importer.importerLigne("textes/texteTest.regexp", 2));
	}

}
