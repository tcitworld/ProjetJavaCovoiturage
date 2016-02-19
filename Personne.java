class Personne {
	int id;
	String nom;
	String ville;
	boolean permis;

	Personne(int id, String nom, String ville, boolean permis) {
		this.id = id;
		this.nom = nom;
		this.ville = ville;
		this.permis = permis;
	}

	public boolean peutConduire() {
		return this.permis;
	}

	public int compareTo(Personne pers) {
		return pers.nom.compareTo(this.nom);
	}
}