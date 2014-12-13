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
				+ ", commune=" + commune + "]";
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
		
		for(int i=0;i<commune.size();i++){
			//insertion
			texteColore=insererChaine(texteColore, balisesOuvranteCouleurCommune, commune.get(i).getDebut());
			
			
		}
		
	}
	



	/**
	 *  Insérer une chaine de caractère dans une String à un index <strong>Et répercute les décalages sur les plages de l'exercice</strong>.
	 * @param chaineAInserer String à insérer.
	 * @param index Entier localisant l'insertion.
	 * @param chaineCible La String devant subir l'insertion.
	 * @return La String avec l'insertion de la chaine à l'index voulu.
	 * 
	 * 
	 */
	private String insererChaine(String chaineCible, String chaineAInserer,int index){
		decaler(index, chaineAInserer.length());
		
		String b=chaineCible.substring(0,index);
		String c=chaineCible.substring(index);
		chaineCible=b+chaineAInserer+c;
		
		return chaineCible;
		
	}
	/**
	 * Permet de décaler toutes les plages de l'exercice
	 * @param index l'index à partir du quel les Plages doivent etre décalés
	 * @param decalage le nombre de décalges voulus
	 */
	private void decaler(int index, int decalage) {
		// TODO Auto-generated method stub
		
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
