/**
 * Personne représente une personne
 * 
 * @author Thomas Citharel
 * @version 0.1
 */

class Personne {
	/**
	 * L'id de la personne
	 */
	int id;

	/**
	 * Le nom de la personne
	 */
	String nom;

	/**
	 * La ville de la personne
	 */
	String ville;

	/**
	 * Si la personne a le permis
	 * 
	 * @see Personne#peutConduire()
	 */
	boolean permis;

	Personne(int id, String nom, String ville, boolean permis) {
		this.id = id;
		this.nom = nom;
		this.ville = ville;
		this.permis = permis;
	}

	/**
	 * peutConduire
	 *
	 * @return si la personne peut conduire
	 */

	public boolean peutConduire() {
		return this.permis;
	}

	/**
	 * compareTo
	 *
	 * @param pers
	 *			une personne
	 *
	 * @return si le nom de cette personne est avant ou après dans l'ordre lexicographique celui de l'autre personne
	 */

	public int compareTo(Personne pers) {
		return pers.nom.compareTo(this.nom);
	}
}