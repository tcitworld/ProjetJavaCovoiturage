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
		for (int i=0;i<this.tp.length; i++) {
			cpt = (tp[i].ville.equals(ville)) ? cpt+1 : cpt;
		}
		return cpt;
	}

	public boolean capaciteSuffisante(String ville) {
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
		boolean res = false;
		String[] villes = this.getVilles();
		for (int i = 0;i < ; ) {
			
		}
	}
}