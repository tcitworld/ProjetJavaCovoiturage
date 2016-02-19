import java.util.Arrays;

public class Covoiturage{
	Personne tp[];
	Voiture tv[];

	Covoiturage(Voiture tv [],Personne tp []) {
		this.tp = tp;
		this.tv = tv;
	}

	public boolean villeDesservie(String ville) {
		int i = 0;
		boolean found = false;
		while (i<this.tv.length && !found) {
			found = tv[i].ville.equals(ville);
			i++;
		}
		return found;
	}

	public int nbPersonnes(String ville) {
		int cpt = 0;
		for (int i = 0 ; i < this.tp.length ; i++) {
			cpt = (ville.equals(this.tp[i].ville)) ? cpt+1 : cpt;
		}
		return cpt;
	}

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

	public boolean villeEstDans(String[] villes, String ville) {
		boolean estDans = false;
		int i = 0;
		while (i < villes.length && !estDans) {
			estDans = (ville.equals(villes[i]));
			i++;
		}
		return estDans;
	}

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

	public boolean estPossible() {
		this.trieVoitures();
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

	private Voiture[] getVoitures(String ville) {
		Arrays.sort(this.tv);
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
			Arrays.sort(voitures);
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

	private int idPersonneMax(Personne[] personnes) {
		int max = personnes[0].id;
		for (int i = 1; i < personnes.length ; i++) {
			max = (personnes[i].id > max) ? personnes[i].id : max;
		}
		return max;
	}

	private boolean personneInArray(boolean[] array, int monId) {
		boolean res = false;
		int i = 0;
		while (i < array.length && res) {
			res = array[monId];
		}
		return res;
	}

	public int getIdentifiant(String nomPersonne) {
		int i = 0;
		boolean trouve = false;
		while (i < this.tp.length && !trouve) {
			trouve = nomPersonne.equals(this.tp[i].nom);
			i++;
		}
		return trouve ? this.tp[i-1].id : -1;
	}

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

	private void triePersonnes() {
		Personne [] tab = this.tp;
		int cpt;
	    Personne element;
	 
	    for (int i = 1; i < tab.length ; i++)
	    {    
	        element = tab[i];
	        cpt = i - 1;
	        while (cpt >= 0 && tab[cpt].compareTo(element) < 0)
	        {
	           tab[cpt + 1] = tab[cpt];
	           cpt--;
	        }
	        tab[cpt + 1] = element;
	    }
	}

	private void trieVoitures() {
		Voiture [] tab = this.tv;
		int cpt;
	    Voiture element;
	 
	    for (int i = 1; i < tab.length ; i++)
	    {    
	        element = tab[i];
	        cpt = i - 1;
	        while (cpt >= 0 && tab[cpt].compareTo(element) < 0)
	        {
	           tab[cpt + 1] = tab[cpt];
	           cpt--;
	        }
	        tab[cpt + 1] = element;
	    }
	}

}