package Controleur;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Analyse un texte � l'aide d'une expression r�guli�re et retourne une liste de Plages 
 * 
 * @author Etudiant
 *
 */
public class Analyse {
	/**
	 * Analyse un texte � l'aide d'une expression r�guli�re et retourne une liste de Plages
	 * @param texte le texte � analyser
	 * @param exprRegex l'expression r�guli�re � compiler
	 * @return La liste de plages r�sultant de l'analyse
	 * @see Plage
	 */
	public static ArrayList<Plage> analyser(String texte,String exprRegex){
		ArrayList<Plage> listePlage=new ArrayList<Plage>();
		Pattern p = Pattern.compile(exprRegex);
		Matcher m = p.matcher(texte);
		
		while (m.find()) {
				listePlage.add(new Plage(m.start(), m.end()));
		}
		
		return listePlage;
		
	}
	//ceci est un test
	public static void main(String [] args){
		System.out.println("ceci est un test");
		System.out.println(analyser("Ce texte est un test/nSur plusieurs lignes/nComme ceci", "ce|test"));
	}
}
