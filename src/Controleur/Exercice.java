package Controleur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	//Listes de plages qui match
	private ArrayList<Plage> solution;
	private ArrayList<Plage> utilisateur;
	private ArrayList<Plage> commune;
	
	//Pour coloration variable clon�s
	private ArrayList<Plage> solutionBis;
	private ArrayList<Plage> utilisateurBis;
	private ArrayList<Plage> communeBis;
	
	private String texteColore;
	
	//Param�tres de coloration
	private String baliseOuvranteCouleurUtilisateur, baliseFermanteCouleurUtilisateur, baliseOuvranteCouleurSolution, baliseFermanteCouleurSolution;
	private String balisesOuvranteCouleurCommune;
	private String balisesFermanteCouleurCommune;
	/**
	 * Cr�e un nouvel exercice 
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

	/**
	 * Permet de cloner une liste
	 * @param list La liste � cloner.
	 * @return la liste clon�e
	 */
	private static List<Plage> cloneList(List<Plage> list) {
	    List<Plage> clone = new ArrayList<Plage>(list.size());
	    for(Plage item: list) clone.add(item.clone());
	    return clone;
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
	 * V�rifie si l'exercice est r�solu ou non par l'utilisateur. Attention il se peut que le r�sultat soit mauvais en cas de plages concomittantes.
	 *
	 * @return un bool�en vrais si l'exercice est r�solu faux sinon.
	 * 
	 */
	public boolean estResolu(){
		
		return solution.equals(utilisateur);
	}



	/**
	 * Permet de r�aliser l'exercice � partir de l'expression r�guli�re rentr�e par l'utilisateur.
	 * @param exprReg L'expression r�guli�re entr�e par l'utilisateur.
	 * @return le texte avec les balises de coloration.
	 */
	public String realiserExercice(String exprReg){
		utilisateur=Analyse.analyser(texte, exprReg);
		comparerPlages();
		coloration();
		return texteColore;
	}
	
	/**
	 * Permet de cr�er un texte color� en fonction des listes de plages: commune,utilisateur,solution
	 */

	private void coloration(){
		
		texteColore=texte;
		
		//clonage des variables
		utilisateurBis=(ArrayList<Plage>)cloneList(utilisateur);
		solutionBis= (ArrayList<Plage>)cloneList(solution);
		communeBis= (ArrayList<Plage>) cloneList(commune);
		
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
		//insertion des couleur pour commune
		for(int i=0;i<communeBis.size();i++){
			//insertion des balises ouvrante
			int index=communeBis.get(i).getDebut();
			insererChaine( balisesOuvranteCouleurCommune,index);
			//d�calage des Plages communes
			
			for(int j=0;j<communeBis.size();j++){
				if (communeBis.get(j).getDebut()>=index) {
					communeBis.get(j).setDebut(communeBis.get(j).getDebut()+balisesOuvranteCouleurCommune.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					communeBis.get(j).setFin(communeBis.get(j).getFin()+balisesOuvranteCouleurCommune.length());
				}
				
			}
			//d�calage des plages solution
			for (int k = 0; k < solutionBis.size(); k++) {
				if (solutionBis.get(k).getDebut()>=index) {
					solutionBis.get(k).setDebut(solutionBis.get(k).getDebut()+balisesOuvranteCouleurCommune.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					solutionBis.get(k).setFin(solutionBis.get(k).getFin()+balisesOuvranteCouleurCommune.length());
				}
			}
			//d�calage des plagesutilisateur
			for (int k = 0; k < utilisateurBis.size(); k++) {
				if (utilisateurBis.get(k).getDebut()>=index) {
					utilisateurBis.get(k).setDebut(utilisateurBis.get(k).getDebut()+balisesOuvranteCouleurCommune.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					utilisateurBis.get(k).setFin(utilisateurBis.get(k).getFin()+balisesOuvranteCouleurCommune.length());
				}
			}
			//insetion des balises fermantes
			index=communeBis.get(i).getFin();
			insererChaine( balisesFermanteCouleurCommune,index );
			//d�calage des Plages communes
			for(int j=0;j<communeBis.size();j++){
				if (communeBis.get(j).getDebut()>=index) {
					communeBis.get(j).setDebut(communeBis.get(j).getDebut()+balisesFermanteCouleurCommune.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					communeBis.get(j).setFin(communeBis.get(j).getFin()+balisesFermanteCouleurCommune.length());
				}
				
			}
			//d�calage des plages solution
			for(int j=0;j<solutionBis.size();j++){
				if (solutionBis.get(j).getDebut()>=index) {
					solutionBis.get(j).setDebut(solutionBis.get(j).getDebut()+balisesFermanteCouleurCommune.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					solutionBis.get(j).setFin(solutionBis.get(j).getFin()+balisesFermanteCouleurCommune.length());
				}
				
			}
			//d�calage des plages utilisateur
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+balisesFermanteCouleurCommune.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+balisesFermanteCouleurCommune.length());
				}
				
			}
			
		}
		// /!\ on ne se pr�occupe plus de communeBis vu que toutes les plages on �tait plac� 
		//=> les plages de commmuneBis ne refl�terons plus la r�alit� apr�s
		
		//insertion des couleurs pour solution
		for (int i = 0; i < solutionBis.size(); i++) {
			int index=solutionBis.get(i).getDebut();
			//insertion balises ouvrante
			insererChaine(baliseOuvranteCouleurSolution,index);
			//d�calage plages solution
			for(int j=0;j<solutionBis.size();j++){
				if (solutionBis.get(j).getDebut()>=index) {
					solutionBis.get(j).setDebut(solutionBis.get(j).getDebut()+baliseOuvranteCouleurSolution.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					solutionBis.get(j).setFin(solutionBis.get(j).getFin()+baliseOuvranteCouleurSolution.length());
				}
				
			}
			//d�calage plages utilisateur
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+baliseOuvranteCouleurSolution.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+baliseOuvranteCouleurSolution.length());
				}
				
			}
			//insetion des balises fermantes
			index=solutionBis.get(i).getFin();
			insererChaine(baliseFermanteCouleurSolution,index);
			
			for(int j=0;j<solutionBis.size();j++){
				if (solutionBis.get(j).getDebut()>=index) {
					solutionBis.get(j).setDebut(solutionBis.get(j).getDebut()+baliseFermanteCouleurSolution.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					solutionBis.get(j).setFin(solutionBis.get(j).getFin()+baliseFermanteCouleurSolution.length());
				}
				
			}
			//d�calage plages utilisateur
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+baliseFermanteCouleurSolution.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+baliseFermanteCouleurSolution.length());
				}
			}
			
		}
		// /!\ on ne se pr�occupe plus de utilisateurBis vu que toutes les plages on �tait plac� 
			//=> les plages de utilisateurBis ne refl�terons plus la r�alit� apr�s
		
		//insertion des couleurs pour utilisateur
		for (int i = 0; i < utilisateurBis.size(); i++) {
			int index=utilisateurBis.get(i).getDebut();
			//insertion balises ouvrante
			insererChaine(baliseOuvranteCouleurUtilisateur,index);
			//d�calage plages utilisateur
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+baliseOuvranteCouleurUtilisateur.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+baliseOuvranteCouleurUtilisateur.length());
				}
				
			}
			//insetion des balises fermantes
			index=utilisateurBis.get(i).getFin();
			insererChaine(baliseFermanteCouleurUtilisateur,index);
			
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+baliseFermanteCouleurUtilisateur.length());
					//car le d�but est avant la fin pas besoin de faire de deuxi�me condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+baliseFermanteCouleurUtilisateur.length());
				}
			}
		}
	}
	



	/**
	 *  Ins�rer une String dans texteColore � un index.
	 * @param chaineAInserer String � ins�rer.
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
	 * Permet de d�caler toutes les plages de l'exercice
	 * @param index l'index � partir du quel les Plages doivent etre d�cal�s
	 * @param decalage le nombre de d�calges voulus
	 */

	
	/**
	 * D�finit les plages commmunes entre les plages solutions et les plages utilisateurs
	 */
	private void comparerPlages(){
		commune=new ArrayList<Plage>();
		
		
				
		for(int i=0;i<solution.size();i++){
			
			for(int j=0;j<utilisateur.size();j++){
				//Plages strictements �gales
				if((utilisateur.get(j).getDebut()==solution.get(i).getDebut())&&(utilisateur.get(j).getFin()==solution.get(i).getFin())){
					//Ajout de la plage � commune
					commune.add(solution.get(i));
				//plage utilisateur compris dans la plage solution	
				}else if((utilisateur.get(j).getDebut()>=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()<=solution.get(i).getFin())){
					//Ajout de la plage utilisateur � commune
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
		System.out.println(exo.estResolu());
		
		
	}
	
}
