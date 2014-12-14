package Controleur;
/**
 * Les plages sont faites pour rep�senter le debut et la fin(num�ros du caract�re) des chaines 
 * qui corresspondent au r�sultats d'une expression r�guli�re.
 * @author Benjamin
 *
 */
public class Plage implements Cloneable{
private int debut;
private int fin;
	/**
	 * Cr�e une nouvelle Plage.
	 * @param debutPlage l'index du caract�re de d�but de la Plage
	 * @param finPlage l'index du caract�re de fin de la Plage
	 */
	public Plage(int debutPlage,int finPlage) {
		// TODO Auto-generated constructor stub
		setDebut(debutPlage);
		setFin(finPlage);
	}
	/**
	 * Obtenir le debut de cette Plage
	 * @return l'index du d�but de la plage
	 */
	public int getDebut() {
		return debut;
	}
	/**
	 * Modifier le d�but de la Plage
	 * @param debut le nouvel index de d�but de la Plage
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}
	/**
	 * Obtenir la fin de la Plage
	 * @return l'index de fin de Plage
	 */
	public int getFin() {
		return fin;
	}
	/**
	 * Modifier la fin de la Plage
	 * @param fin le nouvel index de fin de la Plage
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}
	@Override
	public String toString() {
		return "Plage [debut=" + debut + ", fin=" + fin + "]";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Object o = null;
		try {
			// On r�cup�re l'instance � renvoyer par l'appel de la 
			// m�thode super.clone()
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous impl�mentons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return o;
	}
	
	
}
