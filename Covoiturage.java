import java.util.Arrays;

class Covoiturage{
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
		Arrays.sort(this.tv);
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

	public String [] getVilles() {
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
		Arrays.sort(this.tv);
		boolean possible = true;
		int i = 0;

		while (i < this.getVilles().length && possible) {
			String ville = this.getVilles()[i];
			int nbConducteurs = 0;
			for (int j = 0 ; j < this.tp.length ; j++) {
				if (this.tp[j].permis && this.tp[j].ville == ville){
					nbConducteurs++;
				}
			}
			int nbPersonnes = nbPersonnes(ville);
			int k,capa;
			k = 0;
			capa = 0;
			while (k < this.tv.length && possible) {
				if (this.tv[k].ville == ville) {
					capa = this.tv[k].capa;
					if (capa < nbPersonnes) {
						nbPersonnes -= capa;
						nbConducteurs--;
						k++;
					} else {
						possible = false;
					}
				}
			}
			possible = nbConducteurs > 0;
			i++;
		}

		return possible && this.capaciteSuffisante();
	}

	public Voiture[] getVoitures(String ville) {
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

	public Personne[] getPersonnes(String ville) {
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

	// public int[] Attribution() {
	// 	Arrays.sort(this.tv);
	// 	int[] idVoitures = new int[this.tv.length];
	// 	for (int i = 0; i < this.tv.length ; i++) {
	// 		for (int j = 0; j < this.tp.length; j++ ) {
	// 			if (this.tp[j].id = i)
	// 				idVoitures[i]
	// 		}
	// 	}
	// }

	public int getIdentifiant(String nomPersonne) {
		int i = 0;
		boolean trouve = false;
		while (i < this.tp.length && !trouve) {
			// System.out.println(i);
			// System.out.println(nomPersonne);
			// System.out.println(this.tp[i].nom);
			trouve = nomPersonne.equals(this.tp[i].nom);
			i++;
		}
		return trouve ? this.tp[i-1].id : -1;
	}
}