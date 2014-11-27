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
	 * Colorise un texte donn�e � partir d'une expression reguli�re donn�e
	 * @param texteATester le texte � coloriser.
	 * @param texteATrouver l'expression r�guli�re qui d�finit la colorisation.
	 * @return le texte coloris�au format html comportant les balises correspondantes
	 */
	public static String ajouterCouleur(String texteATester, String texteATrouver,String couleur){
		
		//System.out.println(texteATester);
		//System.out.println(texteATrouver);
		
		 Pattern p = Pattern.compile(texteATrouver);
		 Matcher m = p.matcher(texteATester);
		 StringBuffer sb = new StringBuffer();
		 
		 
		 if(couleur.equals("green")){
		 while (m.find()) {
			// System.out.println("ololol   " +m.group());
		     m.appendReplacement(sb, "<font color='green'>"+m.group()+"</font>");
		 }
		 }
		 else {
			 while (m.find()) {
				// System.out.println("ololol" +m.group());
			     m.appendReplacement(sb, "<font color='red'>"+m.group()+"</font>");
			 }
		 }
		 
		 m.appendTail(sb);
		//System.out.println("lololol");
		// sb.replaceAll("<font color='red'><font color='green'>", "</font></font>");
		return sb.toString();
		
	}
	/*
	public static void main(String[] args) {
		System.out.println(ajouterCouleur(null, null, null));
		
	}*/
	
	

}
