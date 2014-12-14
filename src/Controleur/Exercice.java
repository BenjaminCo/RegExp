package Controleur;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DebugGraphics;

import org.hamcrest.core.IsEqual;

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
	
	//pour coloration
	private ArrayList<Plage> solutionBis;
	private ArrayList<Plage> utilisateurBis;
	private ArrayList<Plage> communeBis;
	
	private String texteColore;
	private String baliseOuvranteCouleurUtilisateur, baliseFermanteCouleurUtilisateur, baliseOuvranteCouleurSolution, baliseFermanteCouleurSolution;
	private String balisesOuvranteCouleurCommune;
	private String balisesFermanteCouleurCommune;
	/**
	 * Crée un nouvel exercice 
	 * @param pathTexte l'emplacement du fichier comportant le texte
	 * @param pathRegexp l'emplacement du fichier comportant le path
	 */
	public Exercice(String pathTexte,String pathRegexp) {
		texte=Importer.importerTexte(pathTexte);
		regExp=Importer.importerExpression(pathRegexp);
		solution=Analyse.analyser(texte, regExp);
		
		//ici on choisi la couleur de la coloration
		baliseOuvranteCouleurSolution="<span style='background:green'>";
		baliseFermanteCouleurSolution="</span>";
		baliseOuvranteCouleurUtilisateur="<u style='color:red'>";
		baliseFermanteCouleurUtilisateur="</u>";
		balisesOuvranteCouleurCommune=baliseOuvranteCouleurSolution+baliseOuvranteCouleurUtilisateur;
		balisesFermanteCouleurCommune=baliseFermanteCouleurUtilisateur+baliseFermanteCouleurSolution;
	}



	@Override
	public String toString() {
		return "Exercice [texte=" + texte + ", regExp=" + regExp
				+ ", solution=" + solution + ", utilisateur=" + utilisateur
				+ ", commune=" + commune + ", solutionBis=" + solutionBis
				+ ", utilisateurBis=" + utilisateurBis + ", communeBis="
				+ communeBis + ", texteColore=" + texteColore
				+ ", baliseOuvranteCouleurUtilisateur="
				+ baliseOuvranteCouleurUtilisateur
				+ ", baliseFermanteCouleurUtilisateur="
				+ baliseFermanteCouleurUtilisateur
				+ ", baliseOuvranteCouleurSolution="
				+ baliseOuvranteCouleurSolution
				+ ", baliseFermanteCouleurSolution="
				+ baliseFermanteCouleurSolution
				+ ", balisesOuvranteCouleurCommune="
				+ balisesOuvranteCouleurCommune
				+ ", balisesFermanteCouleurCommune="
				+ balisesFermanteCouleurCommune + "]";
	}













	/**
	 * Permet de réaliser l'exercice à partir de l'expression régulière rentrée par l'utilisateur.
	 * @param exprReg L'expression régulière rentrée par l'utilisateur.
	 */
	public void realiserExercice(String exprReg){
		utilisateur=Analyse.analyser(texte, exprReg);
		comparerPlages();
		coloration();
	}
	
	/**
	 * Permet de créer un texte coloré en fonction des listes de plages: commune,utilisateur,solution
	 */
	private void coloration(){
		texteColore=texte;
		
		utilisateurBis= (ArrayList<Plage>) utilisateur.clone();
		solutionBis= (ArrayList<Plage>) solution.clone();
		communeBis= (ArrayList<Plage>) commune.clone();
		
		//supression des doublons
		for(int i=0;i<communeBis.size();i++){
			Plage plageCommune=communeBis.get(i);
			for(int j=0;j<solutionBis.size();j++){
				Plage plageSolution=solutionBis.get(j);
					//Plage solution comprise dans commune
				 if(((plageCommune.getDebut()<=plageSolution.getDebut())&&(plageCommune.getFin()>=plageSolution.getFin()))){
					  solutionBis.remove(j);
					  //commune comprise dans solution
				 }else if(((plageCommune.getDebut()>plageSolution.getDebut())&&(plageCommune.getFin()<plageSolution.getFin()))){
					 solutionBis.add(new Plage(plageSolution.getDebut(), plageCommune.getDebut()));
					 solutionBis.add(new Plage(plageCommune.getFin(), plageSolution.getFin()));
					 solutionBis.remove(j);
					  //les plages qui se chevauchent
				 }else if((plageSolution.getDebut()<plageCommune.getDebut())&&(plageSolution.getFin()>plageCommune.getDebut())&&(plageCommune.getFin()>=plageSolution.getFin())){
					 solutionBis.get(j).setFin(plageCommune.getDebut());
				 }else if((plageSolution.getDebut()>=plageCommune.getDebut())&&(plageSolution.getDebut()<plageCommune.getFin())&&(plageCommune.getFin()<plageSolution.getFin())){
					 solutionBis.get(j).setDebut(plageCommune.getFin());
				 }
			}
			for(int k=0;k<utilisateurBis.size();k++){
				Plage plageUtilisateur=utilisateurBis.get(k);
				//Plage utilisateur comprise dans commune
				 if(((plageCommune.getDebut()<=plageUtilisateur.getDebut())&&(plageCommune.getFin()>=plageUtilisateur.getFin()))){
					  utilisateurBis.remove(k);
					  //commune comprise dans utilisateur
				 }else if(((plageCommune.getDebut()>plageUtilisateur.getDebut())&&(plageCommune.getFin()<plageUtilisateur.getFin()))){
					 utilisateurBis.add(new Plage(plageUtilisateur.getDebut(), plageCommune.getDebut()));
					 utilisateurBis.add(new Plage(plageCommune.getFin(), plageUtilisateur.getFin()));
					 utilisateurBis.remove(k);
					  //les plages qui se chevauchent
				 }else if((plageUtilisateur.getDebut()<plageCommune.getDebut())&&(plageUtilisateur.getFin()>plageCommune.getDebut())&&(plageCommune.getFin()>=plageUtilisateur.getFin())){
					 utilisateurBis.get(k).setFin(plageCommune.getDebut());
				 }else if((plageUtilisateur.getDebut()>=plageCommune.getDebut())&&(plageUtilisateur.getDebut()<plageCommune.getFin())&&(plageCommune.getFin()<plageUtilisateur.getFin())){
					 utilisateurBis.get(k).setDebut(plageCommune.getFin());
				 }
			}
		}
		
		for(int i=0;i<commune.size();i++){
			//insertion
			insererChaine( balisesOuvranteCouleurCommune, commune.get(i).getDebut());
			
			decalerDebut(commune.get(i).getDebut(), balisesOuvranteCouleurCommune.length());
		}
		
	}
	



	/**
	 *  Insérer une String dans texteColore à un index.
	 * @param chaineAInserer String à insérer.
	 * @param index Entier localisant l'insertion.
	 * 
	 * 
	 * 
	 * 
	 */
	private void insererChaine(String chaineAInserer,int index){
		
		
		String b=texteColore.substring(0,index);
		String c=texteColore.substring(index);
		texteColore=b+chaineAInserer+c;
		
		
		
		
	}
	/**
	 * Permet de décaler toutes les plages de l'exercice
	 * @param index l'index à partir du quel les Plages doivent etre décalés
	 * @param decalage le nombre de décalges voulus
	 */
	private void decalerDebut(int index, int decalage) {
		// TODO Auto-generated method stub
		
		/*
		for(int i=0;i<commune.size();i++){
			if (commune.get(i).getDebut()>=index) {
				commune.get(i).setDebut(commune.get(i).getDebut()+decalage);
			}
			if (commune.get(i).getFin()>=index) {
				commune.get(i).setFin(commune.get(i).getFin()+decalage);
			}
			
		}*/
		
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
					commune.add(solution.get(i));
				//plage utilisateur compris dans la plage solution	
				}else if((utilisateur.get(j).getDebut()>=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()<=solution.get(i).getFin())){
					//Ajout de la plage utilisateur à commune
					commune.add(utilisateur.get(j));
					//plage solution compris dans la plage utilisateur
				}else if((utilisateur.get(j).getDebut()<=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()>=solution.get(i).getFin())){
					commune.add(solution.get(i));
				}
				// les plages qui se chevauchent
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
