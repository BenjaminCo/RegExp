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
		
		String texteATester ="one cat two cats in the yard";
		String texteATrouver ="cat|the";
		 Pattern p = Pattern.compile(texteATrouver);
		 Matcher m = p.matcher(texteATester);
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
		     m.appendReplacement(sb, "<html><font color = #012345>"+m.group()+"</font></html>");
		 }
		 m.appendTail(sb);
		 System.out.println(sb.toString());
        /*
        for(int i=0;i<=matcher.groupCount();i++){
        	System.out.println(matcher.group(i));
        }
        */
       
        
	}

}
