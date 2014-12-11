package Controleur;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DebugGraphics;

import Model.Importer;
/**
 * Un exercice
 * @author Etudiant
 *
 */
public class Exercice {
	private String texte;
	private String regExp;
	
	private ArrayList<Plage> solution;
	private ArrayList<Plage> utilisateur;
	private ArrayList<Plage> commune;
	/**
	 * Crée un nouvel exercice 
	 * @param pathTexte l'emplacement du fichier comportant le texte
	 * @param pathRegexp l'emplacement du fichier comportant le path
	 */
	public Exercice(String pathTexte,String pathRegexp) {
		texte=Importer.importerTexte(pathTexte);
		regExp=Importer.importerExpression(pathRegexp);
		solution=Analyse.analyser(texte, regExp);
		
	}

	
	
	@Override
	public String toString() {
		return "Exercice [texte=" + texte + ", regExp=" + regExp
				+ ", solution=" + solution + ", utilisateur=" + utilisateur
				+ ", commune=" + commune + "]";
	}



	public void realiserExercice(String exprReg){
		utilisateur=Analyse.analyser(texte, exprReg);
		comparerPlages();
	}
	/**
	 * Définit les plages commmunes entre les plages solutions et les plages utilisateurs
	 */
	private void comparerPlages(){
		commune=new ArrayList<Plage>();
		for(int i=0;i<solution.size();i++){
			
			for(int j=0;j<utilisateur.size();j++){
				//Plages strictements égales
				if((utilisateur.get(j).getDebut()==solution.get(i).getDebut())&&(utilisateur.get(j).getFin()==solution.get(i).getFin())){
					//Ajout de la plage à commune
					commune.add(new Plage(solution.get(i).getDebut(),solution.get(i).getFin()));
				//plage utilisateur compris dans la plage solution	
				}else if((utilisateur.get(j).getDebut()>=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()<=solution.get(i).getFin())){
					//Ajout de la plage utilisateur à commune
					commune.add(utilisateur.get(j));
					//plage solution compris dans la plage utilisateur
				}else if((utilisateur.get(j).getDebut()<=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()>=solution.get(i).getFin())){
					commune.add(solution.get(i));
				}
				// implémenter les plages qui se chevauchent
				else if((utilisateur.get(j).getDebut()<=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()<=solution.get(i).getFin())&&(utilisateur.get(j).getFin()>=solution.get(i).getDebut())){
					commune.add(new Plage(solution.get(i).getDebut(), utilisateur.get(j).getFin()));
				}else if((utilisateur.get(j).getDebut()>=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()>=solution.get(i).getFin())&&(solution.get(i).getFin()>=utilisateur.get(j).getDebut())){
					commune.add(new Plage(utilisateur.get(j).getDebut(),solution.get(i).getFin()));
				}
				
				
			}
		}
	}
	
	//Ceci est un test
	public static void main(String[] args) {
		Exercice exo=new Exercice("textes/texteTest.txt","textes/texteTest.regexp");
		exo.realiserExercice("ce|te");
		
		System.out.println(exo);
		
		
	}
	
}
