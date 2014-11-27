package Controleur;
import java.io.*;
import java.util.regex.*;
/**
 * Permet d'ajouter des balise de coloration dans un texte
 * @author Benjamin
 *
 */
public class AjoutCouleur {
	
	/**
	 * Colorise un texte donnée à partir d'une expression regulière donnée
	 * @param texteATester le texte à coloriser.
	 * @param texteATrouver l'expression régulière qui définit la colorisation.
	 * @return le texte coloriséau format html comportant les balises correspondantes
	 */
	public static String ajouterCouleur(String texteATester, String texteATrouver){
		
		//PEDALE
		 Pattern p = Pattern.compile(texteATrouver);
		 Matcher m = p.matcher(texteATester);
		 StringBuffer sb = new StringBuffer();
		 
		 
		 
		 while (m.find()) {
		     m.appendReplacement(sb, "<font color='red'>"+m.group()+"</font>");
		 }
		 
		 m.appendTail(sb);
		 System.out.println("cc"+sb);
		return sb.toString();
		
	}
	
	

}
