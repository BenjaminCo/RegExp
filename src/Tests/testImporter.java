package Tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import Model.Importer;

public class testImporter {

	@Test
	public void testImpoerterExpression() {
		assertEquals("plusieurs",Model.Importer.importerExpression("textes/texteTest.regexp", 2));
	}

}
