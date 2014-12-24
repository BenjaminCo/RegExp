package Controleur;
/**
 * Les plages sont faites pour repésenter le debut et la fin(numéros du caractère) des chaines 
 * qui corresspondent au résultats d'une expression régulière.
 * @author Benjamin
 *
 */
public class Plage implements Cloneable{
private int debut;
private int fin;
	/**
	 * Crée une nouvelle Plage.
	 * @param debutPlage l'index du caractère de début de la Plage
	 * @param finPlage l'index du caractère de fin de la Plage
	 */
	public Plage(int debutPlage,int finPlage) {
		// TODO Auto-generated constructor stub
		setDebut(debutPlage);
		setFin(finPlage);
	}
	/**
	 * Obtenir le debut de cette Plage
	 * @return l'index du début de la plage
	 */
	public int getDebut() {
		return debut;
	}
	/**
	 * Modifier le début de la Plage
	 * @param debut le nouvel index de début de la Plage
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
	protected Plage clone()  {
		// TODO Auto-generated method stub
		
		return new Plage(debut, fin);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plage other = (Plage) obj;
		if (debut != other.debut)
			return false;
		if (fin != other.fin)
			return false;
		return true;
	}
	
	
}
