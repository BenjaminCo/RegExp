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
		
		 Pattern p = Pattern.compile("(\n)|"+"("+solutionATrouver+")");
		 Matcher m = p.matcher(texteATester);
		 StringBuffer sb = new StringBuffer();
		 
		 
		 
			 while (m.find()) {
				// System.out.println("ololol   " +m.group());
				
				 
				if (m.group(1)!=null) {
					m.appendReplacement(sb, "<br/>");
				}
				
				//System.out.println(m.group(2));
				if (m.group(2)!=null) {
					 m.appendReplacement(sb, "<span style='background:green'>"+m.group(2)+"</span>");
				}
				
			    
			 }
		 
		 m.appendTail(sb);
		
		
		 
		 p = Pattern.compile("(<span style='background:green'>)|(</span>)|(<br/>)|"+"("+texteATrouver+")");
		 m = p.matcher(sb.toString());
		 sb = new StringBuffer();
		while (m.find()) {
			// System.out.println("ololol" +m.group());
			//System.out.println(m.group(4));
			if (m.group(4)!=null) {
		    	m.appendReplacement(sb, "<u style='color:red'>"+m.group(4)+"</u>");
			}
		 }
		m.appendTail(sb);
		return "<html>"+sb.toString()+"</html>";
	}
	
	
	
	public static String fusion(String depart, String test1, String test2) 
		// premier string => sanscouleur
		// Deuxième string => vert
		// Troisième string => rouge
	 {
		String salut ="";
		boolean pastrouverg = true;
		boolean pastrouver1=true;
		boolean pastrouver2=true;
		boolean fini = false;
		//String depart="salut";
		//String test1="sa<u>l</u>ut"; //é avant après le l
		//String test2="sal<k>u</k>t"; // t avant après le u
		
		// sa<u>l
		
		String[] tabdepart = depart.split("");
		String[] tabdepartfinal = tabdepart;
		String[] tabtest1 = test1.split("");
		String[] tabtest2 = test2.split("");
		
		int j=0;
		int intdepart =0;
		int intdepartfinal=0;
		int inttabtest1=0;
		int inttabtest2=0;
		
		
		int intdepartmax =tabdepart.length;
		int intdepartfinalmax=tabtest2.length;
		int inttabtest1max=tabtest1.length;
		int inttabtest2max=tabtest2.length;
		
		
		
		//System.out.println(tabdepart.toString());
		//System.out.println(tabdepart.length);
		

		while (intdepart<tabdepart.length){
			pastrouverg=true;

			/////////////////////////////////////////////////////////////////////
			fini = false;
			
	
			
			//System.out.println("Lettre checkée : " + tabdepart[intdepart]);
			if(tabtest1[inttabtest1].equals("<")){
				while(!tabtest1[inttabtest1].equals(">")&&inttabtest1<=inttabtest1max){
					
					tabdepartfinal=fonction(intdepartfinal, tabtest1[inttabtest1], tabdepartfinal);
					intdepartfinal++;
					inttabtest1++;
					pastrouverg=false;
					
				}
				tabdepartfinal=fonction(intdepartfinal, tabtest1[inttabtest1], tabdepartfinal);
				intdepartfinal++;
				inttabtest1++;
				pastrouverg=false;
			}

			
			/////////////////////////////////////////////////////////////////////
			fini=false;
			if(tabtest2[inttabtest2].equals("<")){
				while(!tabtest2[inttabtest2].equals(">")&&inttabtest2<=inttabtest2max){
					
					tabdepartfinal=fonction(intdepartfinal, tabtest2[inttabtest2], tabdepartfinal);
					intdepartfinal++;
					inttabtest2++;
					pastrouverg=false;
					
				}
				tabdepartfinal=fonction(intdepartfinal, tabtest2[inttabtest2], tabdepartfinal);
				intdepartfinal++;
				inttabtest2++;
				pastrouverg=false;
			}

			
			
			/////////////////////////////////////////////////////////////////////
			
			
			
			
			if(pastrouverg){
		
			intdepart++;
			inttabtest1++;
			inttabtest2++;
			intdepartfinal++;
			}
			
			j=0;
			
			//System.out.println("OMAGAD");
			while(j<tabdepartfinal.length){
				//System.out.print(tabdepartfinal[j]);
				
				j++;
			}
			//System.out.println("");
			//System.out.println("OMAGAD");
			//System.out.println(" ");
		}
		/////FIN WHILE ////////////////////////
		j=0;
		while(j<tabdepartfinal.length){
			salut=salut+tabdepartfinal[j];
			
			j++;
		}
	
		//System.out.println("e,voierbv");
		//System.out.println(salut);
		//System.out.println("ébvjhsfdbdui");
		
		return salut;
	}
	
	public static String [] fonction (int n,String chaine,String []tableau){

	    String[] tab2 = new String [tableau.length+1];
	    for(int i=0;i<n;i++){
	        tab2[i]=tableau[i];
	    }
	    tab2[n]=chaine;
	    for(int i=n+1;i< tableau.length+1;i++){
	        tab2[i]=tableau[i-1];
	    }
	    
		return tab2;
	    
	}
	
	

}
