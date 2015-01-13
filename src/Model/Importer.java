package Model;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Importer {

	/**
	 * Lit un fichier et retourne son contenu
	 * @param path L'adresse du fichier a lire
	 * @return Retourne une String contenant le texte du fichier lu.
	 */
	public static String importerTexte(String path) {
		String texte="";
		double aleat =  Math.random()*10;
		
		
		//System.out.println(aleat);
		// TODO Auto-generated constructor stub
		//CC
			try {
				
				
				
				InputStream inputStream=new FileInputStream(path);
				InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
				BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
				String ligne;
				
				/*
				if(aleat>5){
					while((ligne=bufferedReader.readLine())!=null&& ligne!="$"){
						
					}
				}*/
				
				
				while((ligne=bufferedReader.readLine())!=null&& ligne!="$"){
					texte+=ligne+"\n";
				}
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return texte;
	}
	public static String importerExpression(String path,int numeroLigne){
		String expression="";
		try {
			InputStream inputStream=new FileInputStream(path);
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
			String ligne;
			int numLigneCourante=1;
			while((ligne=bufferedReader.readLine())!=null){
				if(numLigneCourante==numeroLigne){
					expression=ligne;
				}
				numLigneCourante++;
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return expression;
		
	}
	
	public static ArrayList<String> importerTextesRepertoire(String path) {
		File repertoire=new File(path);
		String [] listefichiers; 
		ArrayList<String> nomsFichiers=new ArrayList<String>();
		listefichiers=repertoire.list(); 
		for (int j = 0; j < listefichiers.length; j++) {
			if(listefichiers[j].endsWith(".txt")){
				nomsFichiers.add(listefichiers[j].substring(0,listefichiers[j].length()-4));
			}
		}
		
		return nomsFichiers;
	}
	

		public static int getNombreLigneFichier(String path) {
			String str=null;
			int count=0;
			try {
				FileInputStream fis = new FileInputStream(path);
				LineNumberReader l = new LineNumberReader(new BufferedReader(new InputStreamReader(fis)));
				while ((str=l.readLine())!=null)
				{
					count = l.getLineNumber();
				}
				l.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return count;
			
		}
	
		
	public static void main(String[] args) {
		importerTextesRepertoire("textes");
	}
	
	
	public static Map<String , String > Association_Texte_Regxp(){
		Map <String,String> map = new HashMap<>();
		ArrayList<String> nomsFichiers=importerTextesRepertoire("textes/Test");

		for(int i=0;i<nomsFichiers.size();i++){
			String F=nomsFichiers.get(i)+".txt";
			//System.out.println( F);
			//System.out.println( F.replaceAll(".txt", ".regExp"));
			map.put(F, F.replaceAll(".txt", ".regExp"));
		}
		return map;
	}
	
}
