class Voiture implements Comparable<Voiture> {
	int id;
	String ville;
	int capa;

	Voiture(int id, String ville, int capa) {
		this.id = id;
		this.ville = ville;
		this.capa = capa;
	}

	public int compareTo(Voiture voiture) {
		return voiture.capa - this.capa;
	}
}