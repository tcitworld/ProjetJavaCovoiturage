/**
 * Covoiturage représente un covoiturage
 * 
 * @author Thomas Citharel
 * @version 0.1
 */
public class Covoiturage{

	/**
	 * Tableaux de personnes participant au covoiturage
	 *
	 * @see Personne
	 */
	Personne tp[];

	/**
	 * Tableaux de voitures participant au covoiturage
	 *
	 * @see Voiture
	 */
	Voiture tv[];

	Covoiturage(Voiture tv [],Personne tp []) {
		/**
		 * @see Personne
		 */
		this.tp = tp;

		/**
		 * @see Voiture
		 */
		this.tv = tv;
	}

	/**
	 * villeDesservie
	 *
	 * @param ville 
	 * 			un nom de ville
	 *
	 * @return si la ville est desservie par au moins une voiture
	 *
	 */

	public boolean villeDesservie(String ville) {
		int i = 0;
		boolean found = false;
		while (i<this.tv.length && !found) {
			found = tv[i].ville.equals(ville);
			i++;
		}
		return found;
	}

	/**
	 * nbPersonnes
	 *
	 * @param ville
	 *			un nom de ville
	 *
	 * @return cpt
	 *		le nombre de personnes pour une ville
	 *
	 */

	public int nbPersonnes(String ville) {
		int cpt = 0;
		for (int i = 0 ; i < this.tp.length ; i++) {
			cpt = (ville.equals(this.tp[i].ville)) ? cpt+1 : cpt;
		}
		return cpt;
	}

	/**
	 * capaciteSuffisante
	 *
	 * @param ville
	 *			un nom de ville
	 *
	 * @return capacite suffisante
	 *		s'il y a assez de places dans toutes les voitures pour toutes les personnes de la ville
	 *
	 */

	public boolean capaciteSuffisante(String ville) {
		this.trieVoitures();
		int cptPlaces = 0;
		for (int i=0;i<this.tv.length;i++) {
			if (this.tv[i].ville.equals(ville)) {
				cptPlaces+=this.tv[i].capa;
			}
		}
		return cptPlaces >= this.nbPersonnes(ville);
	}

	/**
	 * villeEstDans
	 *
	 * @param villes
	 *			un tableaux de noms de villes
	 * @param ville
	 *			le nom de ville recherché
	 *
	 * @return si la ville est dans le tableau
	 *
	 */

	public boolean villeEstDans(String[] villes, String ville) {
		boolean estDans = false;
		int i = 0;
		while (i < villes.length && !estDans) {
			estDans = (ville.equals(villes[i]));
			i++;
		}
		return estDans;
	}

	/**
	 * getVilles
	 *
	 * @return villes
	 *			un tableaux de toutes les villes
	 */

	public String[] getVilles() {
		String villes[] = new String[this.tv.length + this.tp.length];
		int k = 0;
		for (int i = 0; i < this.tv.length; i++) {
			if (!this.villeEstDans(villes,this.tv[i].ville)) {
				villes[k] = this.tv[i].ville;
				k++;
			}
		}
		for (int i=0; i < this.tp.length; i++) {
			if (!this.villeEstDans(villes, this.tp[i].ville)) {
				villes[k] = this.tp[i].ville;
				k++;
			}
		}
		String [] villes2 = new String[k];
		for (int l=0;l<k;l++) {
			villes2[l] = villes[l];
		}
		return villes2;
	}

	/**
	 * capaciteSuffisante
	 *
	 * @return si la capacité de toutes les villes est suffisante
	 */
	public boolean capaciteSuffisante() {
		boolean res = true;
		String[] villes = this.getVilles();
		int i = 0;
		while (i<villes.length && res) {
			res = this.capaciteSuffisante(villes[i]);
			i++;
		}
		return res;
	}

	/**
	 * estPossible
	 *
	 * @return si le covoiturage est possible dans toutes les villes
	 */

	public boolean estPossible() {
		boolean possible = true;
		int i = 0;

		while (i < this.getVilles().length && possible) {
			String ville = this.getVilles()[i];
			int nbConducteurs = this.getConducteurs(ville).length;
			int nbPersonnes = this.nbPersonnes(ville);
			Personne[] personnes = this.getPersonnes(ville);
			Voiture[] voitures = this.getVoitures(ville);
			int k,capa;
			k = 0;
			capa = 0;
			while (k < voitures.length && possible) {
				capa = voitures[k].capa;
				if (capa < nbPersonnes) {
					nbPersonnes -= capa;
					nbConducteurs--;
					k++;
				} else {
					possible = false;
				}
			}
			possible = nbConducteurs > 0;
			i++;
		}

		return possible && this.capaciteSuffisante();
	}

	/**
	 * getVoitures
	 *
	 * @param ville
	 * 			un nom de ville
	 *
	 * @return retourne les objets voiture pour une ville donnée
	 */

	private Voiture[] getVoitures(String ville) {
		this.trieVoitures();
		Voiture voitures[] = new Voiture[this.tv.length];
		int j = 0;
		for (int i = 0 ; i < this.tv.length ; i++) {
			if (this.tv[i].ville.equals(ville)) {
				voitures[j] = this.tv[i];
				j++;
			}
		}
		Voiture voitures2[] = new Voiture[j];
		for (int i = 0 ; i < j ; i++) {
			voitures2[i] = voitures[i];
		}
		return voitures2;
	}

	/**
	 * getPersonnes
	 *
	 * @param ville
	 * 			un nom de ville
	 *
	 * @return retourne les objets personnes pour une ville donnée
	 */

	private Personne[] getPersonnes(String ville) {
		Personne personnes[] = new Personne[this.tp.length];
		int j = 0;
		for (int i = 0 ; i < this.tp.length ; i++) {
			if (this.tp[i].ville.equals(ville)) {
				personnes[j] = this.tp[i];
				j++;
			}
		}
		Personne personnes2[] = new Personne[j];
		for (int i = 0 ; i < j ; i++) {
			personnes2[i] = personnes[i];
		}
		return personnes2;
	}

	/**
	 * getPersonnes
	 *
	 * @param ville
	 * 			un nom de ville
	 *
	 * @return retourne les objets personnes qui conduisent pour une ville donnée
	 */

	private Personne[] getConducteurs(String ville) {
		Personne personnes[] = new Personne[this.tp.length];
		int j = 0;
		for (int i = 0 ; i < this.tp.length ; i++) {
			if (this.tp[i].ville.equals(ville) && this.tp[i].peutConduire()) {
				personnes[j] = this.tp[i];
				j++;
			}
		}
		Personne conducteurs[] = new Personne[j];
		for (int i = 0 ; i < j ; i++) {
			conducteurs[i] = personnes[i];
		}
		return conducteurs;
	}

	/**
	 * attribution
	 *
	 * @return retourne un tableau associant les identifiants de personnes aux identifiants de voitures
	 */

	public int[] attribution() {
		int[] idPersonnes = new int[this.idPersonneMax(this.tp)+1];
		for (int i = 0;i < this.idPersonneMax(this.tp)+1 ; i++) {
			idPersonnes[i] = -1;
		}
		int i = 0;
		while (i < this.getVilles().length) {
			String ville = this.getVilles()[i];
			Personne[] personnes = this.getPersonnes(ville);
			Voiture[] voitures = this.getVoitures(ville);
			boolean[] personnesFaites = new boolean[idPersonneMax(personnes)+1];
			int k;
			k = 0;
			int lastidPersonne = 0;
			boolean possible = true;
			while (k < voitures.length) {
				possible = true;
				int j = lastidPersonne;
				while (j < personnes.length && possible) {
					voitures[k].capa--;
					if (!this.personneInArray(personnesFaites,personnes[j].id)) {
						if (voitures[k].capa >= 0) {
							idPersonnes[personnes[j].id] = voitures[k].id;
							personnesFaites[personnes[j].id] = true;
						} else {
							possible = false;
							lastidPersonne = j;
						}
					}
					j++;
				}
				k++;
			}
			i++;
		}

		return idPersonnes;
	}

	/**
	 * idPersonneMax
	 *
	 * @param personnes
	 * 			tableaux d'objets Personne
	 *
	 * @return l'id maximum des personnes
	 */

	private int idPersonneMax(Personne[] personnes) {
		int max = personnes[0].id;
		for (int i = 1; i < personnes.length ; i++) {
			max = (personnes[i].id > max) ? personnes[i].id : max;
		}
		return max;
	}

	/**
	 * personneInArray
	 *
	 * @param array
	 *			un tableau
	 * @param monId
	 * 			l'id d'une personne
	 *
	 * @return si l'id de la personne est dans le tableau
	 */

	private boolean personneInArray(boolean[] array, int monId) {
		boolean res = false;
		int i = 0;
		while (i < array.length && res) {
			res = array[monId];
		}
		return res;
	}

	/**
	 * getIdentifiant
	 *
	 * @param nomPersonne
	 *			le nom de la personne
	 *
	 * @return l'identificant de la personne concernée
	 */

	public int getIdentifiant(String nomPersonne) {
		int i = 0;
		boolean trouve = false;
		while (i < this.tp.length && !trouve) {
			trouve = nomPersonne.equals(this.tp[i].nom);
			i++;
		}
		return trouve ? this.tp[i-1].id : -1;
	}

	/**
	 * getIdentifiantDichotomique
	 *
	 * @param nomPersonne
	 *			le nom de la personne
	 *
	 * @return l'identificant de la personne concernée
	 */

	public int getIdentifiantDichotomique(String nomPersonne) {
		this.triePersonnes();
		boolean trouve = false;
		int debut = 0;
		int i = 0;
		int fin = this.tp.length;
		int milieu = 0;
		while (!trouve && i < this.tp.length) {
			milieu = (debut + fin)/2;
			if (this.tp[milieu].nom.compareTo(nomPersonne) < 0) {
				debut+=1;
			}
			else if (this.tp[milieu].nom.equals(nomPersonne)) {
				trouve = true;
			}
			else {
				fin-=1;
			}
			i+=1;
		}
		return this.tp[milieu].id;
	}

	/**
	 * triePersonnes
	 *
	 * trie les personnes selon l'ordre lexicographique
	 */

	private void triePersonnes() {
		int cpt;
	    Personne element;
	 
	    for (int i = 1; i < this.tp.length ; i++)
	    {    
	        element = this.tp[i];
	        cpt = i - 1;
	        while (cpt >= 0 && this.tp[cpt].compareTo(element) < 0)
	        {
	           this.tp[cpt + 1] = this.tp[cpt];
	           cpt--;
	        }
	        this.tp[cpt + 1] = element;
	    }
	}

	/**
	 * triePersonnes
	 *
	 * trie les voitures selon l'ordre inverse de leur capacité
	 */

	private void trieVoitures() {
		int cpt;
	    Voiture element;
	 
	    for (int i = 1; i < this.tv.length ; i++)
	    {    
	        element = this.tv[i];
	        cpt = i - 1;
	        while (cpt >= 0 && this.tv[cpt].compareTo(element) > 0)
	        {
	           this.tv[cpt + 1] = this.tv[cpt];
	           cpt--;
	        }
	        this.tv[cpt + 1] = element;
	    }
	}

}