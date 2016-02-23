/**
 * Voiture représente une voiture
 * 
 * @author Thomas Citharel
 * @version 0.1
 */

class Voiture {
	/**
	 * L'id de la voiture
	 */
	int id;

	/**
	 * La ville de la voiture
	 */
	String ville;

	/**
	 * La capacité de la voiture
	 */
	int capa;

	Voiture(int id, String ville, int capa) {
		this.id = id;
		this.ville = ville;
		this.capa = capa;
	}

	/**
	 * compareTo
	 *
	 * @param voiture
	 * 			Une voiture
	 *
	 * @return si la voiture a plus ou moins de capacité qu'une autre
	 */

	public int compareTo(Voiture voiture) {
		return voiture.capa - this.capa;
	}
}