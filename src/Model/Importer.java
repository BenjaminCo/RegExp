package Model;
import java.io.*;

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
	
	public static void importerRepertoire(String path) {
		File repertoire=new File(path);
		String [] listefichiers; 

		listefichiers=repertoire.list(); 
		for (int j = 0; j < listefichiers.length; j++) {
			System.out.println(listefichiers[j]);
		}

	}
	public static void main(String[] args) {
		importerRepertoire("textes");
	}
}
