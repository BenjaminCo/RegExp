package Controleur;

import java.util.ArrayList;

import java.util.List;


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
	
	//Pour coloration variable clonés
	private ArrayList<Plage> solutionBis;
	private ArrayList<Plage> utilisateurBis;
	private ArrayList<Plage> communeBis;
	
	private String texteColore;
	
	//Paramètres de coloration
	public static final String baliseOuvranteCouleurSolution="<span style='background:#7DAEA3'>";
	public static final String baliseFermanteCouleurSolution="</span>";
	public static final String baliseOuvranteCouleurUtilisateur="<u style='color:red'>";
	public static final String baliseFermanteCouleurUtilisateur="</u>";
	public static final String balisesOuvranteCouleurCommune=baliseOuvranteCouleurSolution+baliseOuvranteCouleurUtilisateur;
	public static final String balisesFermanteCouleurCommune=baliseFermanteCouleurUtilisateur+baliseFermanteCouleurSolution;
	/**
	 * Crée un nouvel exercice 
	 * @param texte la String comportant le texte
	 * @param regExp la String comportant l' expression régulière Solution.
	 */
	public Exercice(String texte,String regExp) {
		
		this.texte=texte;
		this.regExp=regExp;
		solution=Analyse.analyser(texte, regExp);
		
	}

	/**
	 * Permet de cloner une liste
	 * @param list La liste à cloner.
	 * @return la liste clonée
	 */
	private static List<Plage> cloneList(List<Plage> list) {
	    List<Plage> clone = new ArrayList<Plage>(list.size());
	    for(Plage item: list) clone.add(item.clone());
	    return clone;
	}
	
	public String getSolution(){
		return regExp;
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
	 * Vérifie si l'exercice est résolu ou non par l'utilisateur. Attention il se peut que le résultat soit mauvais en cas de plages concomittantes.
	 *
	 * @return un booléen vrais si l'exercice est résolu faux sinon.
	 * 
	 */
	public boolean estResolu(){
		
		return solution.equals(utilisateur);
	}



	/**
	 * Permet de réaliser l'exercice à partir de l'expression régulière rentrée par l'utilisateur.
	 * @param exprReg L'expression régulière entrée par l'utilisateur.
	 * @return le texte avec les balises de coloration.
	 */
	public String realiserExercice(String exprReg){
		utilisateur=Analyse.analyser(texte, exprReg);
		comparerPlages();
		
		coloration();
		return texteColore;
	}
	/**
	 * Permet de despecialiser les charactères HTML d'un texte.
	 * @param texteP Le texte à despecialiser.
	 * @return Le texte despecialisé.
	 */
	private String despecialisation(String texteP) {
		while(texteP.indexOf("<")!=-1){
			int index =texteP.indexOf("<");
			texteP=texteP.replaceFirst("<", "&lt");
			//décalage des plages communes
			for(int j=0;j<communeBis.size();j++){
				if (communeBis.get(j).getDebut()>index) {
					communeBis.get(j).setDebut(communeBis.get(j).getDebut()+2);
					//car le début est avant la fin pas besoin de faire de deuxième condition
					communeBis.get(j).setFin(communeBis.get(j).getFin()+2);
				}else if((communeBis.get(j).getDebut()<=index)&&(communeBis.get(j).getFin()>index)){
					communeBis.get(j).setFin(communeBis.get(j).getFin()+2);
				}
				
			}
			//décalage des plages solution
			for (int k = 0; k < solutionBis.size(); k++) {
				if (solutionBis.get(k).getDebut()>index) {
					solutionBis.get(k).setDebut(solutionBis.get(k).getDebut()+2);
					//car le début est avant la fin pas besoin de faire de deuxième condition
					solutionBis.get(k).setFin(solutionBis.get(k).getFin()+2);
				}else if((solutionBis.get(k).getDebut()<=index)&&(solutionBis.get(k).getFin()>index)){
					solutionBis.get(k).setFin(solutionBis.get(k).getFin()+2);
				}
			}
			//décalage des plagesutilisateur
			for (int k = 0; k < utilisateurBis.size(); k++) {
				if (utilisateurBis.get(k).getDebut()>index) {
					utilisateurBis.get(k).setDebut(utilisateurBis.get(k).getDebut()+2);
					//car le début est avant la fin pas besoin de faire de deuxième condition
					utilisateurBis.get(k).setFin(utilisateurBis.get(k).getFin()+2);
				}else if((utilisateurBis.get(k).getDebut()<=index)&&(utilisateurBis.get(k).getFin()>index)){
					utilisateurBis.get(k).setFin(utilisateurBis.get(k).getFin()+2);
				}
			
			}
		}
		while(texteP.indexOf(">")!=-1){
			int index =texteP.indexOf(">");
			texteP=texteP.replaceFirst(">", "&gt");
			//décalage des plages communes
			for(int j=0;j<communeBis.size();j++){
				if (communeBis.get(j).getDebut()>index) {
					communeBis.get(j).setDebut(communeBis.get(j).getDebut()+2);
					//car le début est avant la fin pas besoin de faire de deuxième condition
					communeBis.get(j).setFin(communeBis.get(j).getFin()+2);
				}else if((communeBis.get(j).getDebut()<=index)&&(communeBis.get(j).getFin()>index)){
					communeBis.get(j).setFin(communeBis.get(j).getFin()+2);
				}
				
			}
			//décalage des plages solution
			for (int k = 0; k < solutionBis.size(); k++) {
				if (solutionBis.get(k).getDebut()>index) {
					solutionBis.get(k).setDebut(solutionBis.get(k).getDebut()+2);
					//car le début est avant la fin pas besoin de faire de deuxième condition
					solutionBis.get(k).setFin(solutionBis.get(k).getFin()+2);
				}else if((solutionBis.get(k).getDebut()<=index)&&(solutionBis.get(k).getFin()>index)){
					solutionBis.get(k).setFin(solutionBis.get(k).getFin()+2);
				}
			}
			//décalage des plagesutilisateur
			for (int k = 0; k < utilisateurBis.size(); k++) {
				if (utilisateurBis.get(k).getDebut()>index) {
					utilisateurBis.get(k).setDebut(utilisateurBis.get(k).getDebut()+2);
					//car le début est avant la fin pas besoin de faire de deuxième condition
					utilisateurBis.get(k).setFin(utilisateurBis.get(k).getFin()+2);
				}else if((utilisateurBis.get(k).getDebut()<=index)&&(utilisateurBis.get(k).getFin()>index)){
					utilisateurBis.get(k).setFin(utilisateurBis.get(k).getFin()+2);
				}
			
			}
		}
		//texteP=texteP.replaceAll("<", " &lt ");
		//texte.replaceAll(">", " &gt ");
		return texteP;
		
	}

	/**
	 * Permet de créer un texte coloré en fonction des listes de plages: commune,utilisateur,solution
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
		//despecialisation des characère html
		texteColore=despecialisation(texteColore);
		
		//insertion des couleurs pour commune
		for(int i=0;i<communeBis.size();i++){
			//insertion des balises ouvrante
			int index=communeBis.get(i).getDebut();
			insererChaine( balisesOuvranteCouleurCommune,index);
			//décalage des Plages communes
			
			for(int j=0;j<communeBis.size();j++){
				if (communeBis.get(j).getDebut()>=index) {
					communeBis.get(j).setDebut(communeBis.get(j).getDebut()+balisesOuvranteCouleurCommune.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					communeBis.get(j).setFin(communeBis.get(j).getFin()+balisesOuvranteCouleurCommune.length());
				}
				
			}
			//décalage des plages solution
			for (int k = 0; k < solutionBis.size(); k++) {
				if (solutionBis.get(k).getDebut()>=index) {
					solutionBis.get(k).setDebut(solutionBis.get(k).getDebut()+balisesOuvranteCouleurCommune.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					solutionBis.get(k).setFin(solutionBis.get(k).getFin()+balisesOuvranteCouleurCommune.length());
				}
			}
			//décalage des plagesutilisateur
			for (int k = 0; k < utilisateurBis.size(); k++) {
				if (utilisateurBis.get(k).getDebut()>=index) {
					utilisateurBis.get(k).setDebut(utilisateurBis.get(k).getDebut()+balisesOuvranteCouleurCommune.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					utilisateurBis.get(k).setFin(utilisateurBis.get(k).getFin()+balisesOuvranteCouleurCommune.length());
				}
			}
			//insetion des balises fermantes
			index=communeBis.get(i).getFin();
			insererChaine( balisesFermanteCouleurCommune,index );
			//décalage des Plages communes
			for(int j=0;j<communeBis.size();j++){
				if (communeBis.get(j).getDebut()>=index) {
					communeBis.get(j).setDebut(communeBis.get(j).getDebut()+balisesFermanteCouleurCommune.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					communeBis.get(j).setFin(communeBis.get(j).getFin()+balisesFermanteCouleurCommune.length());
				}
				
			}
			//décalage des plages solution
			for(int j=0;j<solutionBis.size();j++){
				if (solutionBis.get(j).getDebut()>=index) {
					solutionBis.get(j).setDebut(solutionBis.get(j).getDebut()+balisesFermanteCouleurCommune.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					solutionBis.get(j).setFin(solutionBis.get(j).getFin()+balisesFermanteCouleurCommune.length());
				}
				
			}
			//décalage des plages utilisateur
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+balisesFermanteCouleurCommune.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+balisesFermanteCouleurCommune.length());
				}
				
			}
			
		}
		// /!\ on ne se préoccupe plus de communeBis vu que toutes les plages on était placé 
		//=> les plages de commmuneBis ne reflèterons plus la réalité après
		
		//insertion des couleurs pour solution
		for (int i = 0; i < solutionBis.size(); i++) {
			int index=solutionBis.get(i).getDebut();
			//insertion balises ouvrante
			insererChaine(baliseOuvranteCouleurSolution,index);
			//décalage plages solution
			for(int j=0;j<solutionBis.size();j++){
				if (solutionBis.get(j).getDebut()>=index) {
					solutionBis.get(j).setDebut(solutionBis.get(j).getDebut()+baliseOuvranteCouleurSolution.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					solutionBis.get(j).setFin(solutionBis.get(j).getFin()+baliseOuvranteCouleurSolution.length());
				}
				
			}
			//décalage plages utilisateur
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+baliseOuvranteCouleurSolution.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+baliseOuvranteCouleurSolution.length());
				}
				
			}
			//insetion des balises fermantes
			index=solutionBis.get(i).getFin();
			insererChaine(baliseFermanteCouleurSolution,index);
			
			for(int j=0;j<solutionBis.size();j++){
				if (solutionBis.get(j).getDebut()>=index) {
					solutionBis.get(j).setDebut(solutionBis.get(j).getDebut()+baliseFermanteCouleurSolution.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					solutionBis.get(j).setFin(solutionBis.get(j).getFin()+baliseFermanteCouleurSolution.length());
				}
				
			}
			//décalage plages utilisateur
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+baliseFermanteCouleurSolution.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+baliseFermanteCouleurSolution.length());
				}
			}
			
		}
		// /!\ on ne se préoccupe plus de utilisateurBis vu que toutes les plages on était placé 
			//=> les plages de utilisateurBis ne reflèterons plus la réalité après
		
		//insertion des couleurs pour utilisateur
		for (int i = 0; i < utilisateurBis.size(); i++) {
			int index=utilisateurBis.get(i).getDebut();
			//insertion balises ouvrante
			insererChaine(baliseOuvranteCouleurUtilisateur,index);
			//décalage plages utilisateur
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+baliseOuvranteCouleurUtilisateur.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+baliseOuvranteCouleurUtilisateur.length());
				}
				
			}
			//insetion des balises fermantes
			index=utilisateurBis.get(i).getFin();
			insererChaine(baliseFermanteCouleurUtilisateur,index);
			
			for(int j=0;j<utilisateurBis.size();j++){
				if (utilisateurBis.get(j).getDebut()>=index) {
					utilisateurBis.get(j).setDebut(utilisateurBis.get(j).getDebut()+baliseFermanteCouleurUtilisateur.length());
					//car le début est avant la fin pas besoin de faire de deuxième condition
					utilisateurBis.get(j).setFin(utilisateurBis.get(j).getFin()+baliseFermanteCouleurUtilisateur.length());
				}
			}
		}
		//mise en place des balises html de début et de fin
		texteColore="<html>"+texteColore+"</html>";
		//mise en place des saut de lignes
		texteColore=texteColore.replaceAll("\n", "<br/>");
		
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
		Exercice exo=new Exercice("<html> lala </html>","<html");
		exo.realiserExercice("<html");
		
		System.out.println(exo +"\n");
		System.out.println(exo.estResolu());
		
		
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public void setRegexp(String regexp) {
		this.regExp = regexp;
	}
	
}
