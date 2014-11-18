package Controleur;
import java.io.*;
import java.util.regex.*;
public class AjoutCouleur {
	
	public static String ajouterCouleur(String texteATester, String texteATrouver){
		
		
		 Pattern p = Pattern.compile(texteATrouver);
		 Matcher m = p.matcher(texteATester);
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
		     m.appendReplacement(sb, "<font color='red'>"+m.group()+"</font>");
		 }
		 m.appendTail(sb);
		return sb.toString();
		
	}
	
	

}
