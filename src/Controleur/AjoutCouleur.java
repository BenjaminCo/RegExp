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
	public static String ajouterCouleur(String texteATester, String texteATrouver,String solutionATrouver){
		
		//System.out.println(texteATester);
		//System.out.println(texteATrouver);
		
		 Pattern p = Pattern.compile("(\n)|"+"("+solutionATrouver+")|"+"("+texteATrouver+")");
		 Matcher m = p.matcher(texteATester);
		 StringBuffer sb = new StringBuffer();
		 
		 
		 
			 while (m.find()) {
				// System.out.println("ololol   " +m.group());
				
				 
				if (m.group(1)!=null) {
					m.appendReplacement(sb, "<br/>");
				}
				
				System.out.println(m.group(2));
				if (m.group(2)!=null) {
					 m.appendReplacement(sb, "<span style='background:green'>"+m.group(2)+"</span>");
				}
				System.out.println(m.group(3));
				if (m.group(3)!=null) {
			    	m.appendReplacement(sb, "<u style='color:red'>"+m.group(3)+"</u>");
				}
			    
			 }
		 
		 
			 while (m.find()) {
				// System.out.println("ololol" +m.group());
			    
			 }
		 
		 
		 m.appendTail(sb);
		//System.out.println("lololol");
		// sb.replaceAll("<font color='red'><font color='green'>", "</font></font>");
		 System.out.println(sb.toString());
		return "<html>"+sb.toString()+"</html>";
		
	}
	/*
	public static void main(String[] args) {
		System.out.println(ajouterCouleur(null, null, null));
		
	}*/
	
	

}
