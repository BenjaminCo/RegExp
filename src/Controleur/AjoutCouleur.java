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
		return "<html>"+sb.toString()+"</html>";
		
	}
	
	public static void main(String[] args) {
		
		String caca = "";
		String caca2 = "";
		String caca3 = "";
		String texteATester ="<html>Ceci est <font color = red>Squalala</font> le déstructeur ténébreux des 7 chevaliers  de l'apocalypse</html>";
		String texteATrouver ="red>[^.]+<.font";
		 Pattern p = Pattern.compile(texteATrouver);
		 Matcher m = p.matcher(texteATester);
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
			 System.out.println("gg");
		     m.appendReplacement(sb, "lol"+m.group()+"lol");
		     caca=m.group();
		     caca2=caca.replaceAll("red>", ""); 
		     caca3=caca2.replaceAll("</font", ""); 
		     
		 }
		 m.appendTail(sb);
		 System.out.println(sb.toString());
		 System.out.println(caca);
		 System.out.println(caca3);
		 
        /*
        for(int i=0;i<=matcher.groupCount();i++){
        	System.out.println(matcher.group(i));
        }
        */
       
        
	}

}
