package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controleur.Exercice;

public class testExercice {

	@Test
	public void testColorationSolution() {
		//creation de l'exercice
		Exercice exo = new Exercice("Un texte pour tester:\nLa coloration de la solution", "texte|solution");
		String solution="<html>Un "+Exercice.baliseOuvranteCouleurSolution+"texte"+Exercice.baliseFermanteCouleurSolution+" pour tester:<br/>La coloration de la "+Exercice.baliseOuvranteCouleurSolution+"solution"+Exercice.baliseFermanteCouleurSolution+"</html>";
		assertEquals(solution, exo.realiserExercice(null));
		
	}
	@Test
	public void testColorationUtilisateur() {
		//creation de l'exercice
		Exercice exo = new Exercice("Un texte pour tester:\nLa coloration de la solution", "texte|solution");
		String solution="<html>"+Exercice.baliseOuvranteCouleurUtilisateur+"Un"+Exercice.baliseFermanteCouleurUtilisateur+" "+Exercice.baliseOuvranteCouleurSolution+"texte"+Exercice.baliseFermanteCouleurSolution+" pour tester:<br/>La coloration de la "+Exercice.baliseOuvranteCouleurSolution+"solution"+Exercice.baliseFermanteCouleurSolution+"</html>";
		
		assertEquals(solution, exo.realiserExercice("Un"));
		
	}
	@Test
	public void testEstResolu() {
		//creation de l'exercice
		Exercice exo = new Exercice("Un texte pour tester:\nLa coloration de la solution", "texte|solution");
		exo.realiserExercice("solution|texte");
		assertTrue(exo.estResolu());
		
	}
	@Test
	public void testDespecialisation(){
		Exercice exo=new Exercice("<html> lala </html>","<html");
		
		assertEquals("<html><span style='background:#7DAEA3'><u style='color:red'>&lthtml</u></span>&gt lala &lt/html&gt</html>", exo.realiserExercice("<html"));
	}
}
