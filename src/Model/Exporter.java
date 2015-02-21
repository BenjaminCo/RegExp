package Model;

import java.io.FileWriter;
import java.io.IOException;

public class Exporter {
	/**
	 * Ajoute une ligne à la fin du fichier cible
	 * @param pathFichier Le chemin du fichier cible
	 * @param ligneAAjouter la chaine caracteres à ajouter
	 */
	public static void ajouterLigne(String pathFichier,String ligneAAjouter){
		FileWriter writer = null;
		ligneAAjouter="\n"+ligneAAjouter;
		try{
			writer = new FileWriter(pathFichier, true);
			writer.write(ligneAAjouter,0,ligneAAjouter.length());
		}catch(IOException ex){
			ex.printStackTrace();
		}
		if(writer != null){
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
