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
	private static boolean montreException=true;
	/**
	 * Analyse un texte � l'aide d'une expression r�guli�re et retourne une liste de Plages
	 * @param texte le texte � analyser
	 * @param exprRegex l'expression r�guli�re � compiler
	 * @return La liste de plages r�sultant de l'analyse
	 * @see Plage
	 */
	public static ArrayList<Plage> analyser(String texte,String exprRegex){
		ArrayList<Plage> listePlage=new ArrayList<Plage>();
		try {
			Pattern p = Pattern.compile(exprRegex);
		
			Matcher m = p.matcher(texte);
			
			while (m.find()) {
					listePlage.add(new Plage(m.start(), m.end()));
			}
		} catch (Exception e) {
			if(e.toString().equals("java.lang.NullPointerException")){
				//ne rien faire
			}else{
				if(montreException){
					System.out.println(e);
					throw e;
				}
			}
			
		}
		return listePlage;
		
	}
	
}
