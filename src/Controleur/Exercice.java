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
	 * Cr�e un nouvel exercice 
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
	 * Permet de r�aliser l'exercice � partir de l'expression r�guli�re rentr�e par l'utilisateur.
	 * @param exprReg L'expression r�guli�re rentr�e par l'utilisateur.
	 */
	public void realiserExercice(String exprReg){
		utilisateur=Analyse.analyser(texte, exprReg);
		comparerPlages();
		coloration();
	}
	
	/**
	 * Permet de cr�er un texte color� en fonction des listes de plages: commune,utilisateur,solution
	 */
	private void coloration(){
		texteColore=texte;
		
		for(int i=0;i<commune.size();i++){
			//insertion
			texteColore=insererChaine(texteColore, balisesOuvranteCouleurCommune, commune.get(i).getDebut());
			
			
		}
		
	}
	



	/**
	 *  Ins�rer une chaine de caract�re dans une String � un index <strong>Et r�percute les d�calages sur les plages de l'exercice</strong>.
	 * @param chaineAInserer String � ins�rer.
	 * @param index Entier localisant l'insertion.
	 * @param chaineCible La String devant subir l'insertion.
	 * @return La String avec l'insertion de la chaine � l'index voulu.
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
	 * Permet de d�caler toutes les plages de l'exercice
	 * @param index l'index � partir du quel les Plages doivent etre d�cal�s
	 * @param decalage le nombre de d�calges voulus
	 */
	private void decaler(int index, int decalage) {
		// TODO Auto-generated method stub
		
	}
	
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
					commune.add(new Plage(solution.get(i).getDebut(),solution.get(i).getFin()));
				//plage utilisateur compris dans la plage solution	
				}else if((utilisateur.get(j).getDebut()>=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()<=solution.get(i).getFin())){
					//Ajout de la plage utilisateur � commune
					commune.add(utilisateur.get(j));
					//plage solution compris dans la plage utilisateur
				}else if((utilisateur.get(j).getDebut()<=solution.get(i).getDebut())&&(utilisateur.get(j).getFin()>=solution.get(i).getFin())){
					commune.add(solution.get(i));
				}
				// impl�menter les plages qui se chevauchent
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
