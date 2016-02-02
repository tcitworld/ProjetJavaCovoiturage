public class Executable{
    public static void main(String[] args){
        Voiture v1=new Voiture(1,"Paris",5);
        Voiture v2=new Voiture(2,"Marseille",3);
        Personne p1=new Personne(1,"Toto","Paris",true);
        Personne p2=new Personne(2,"Titi","Paris",true);
        Covoiturage c1=new Covoiturage(new Voiture[]{v1,v2},new Personne[]{p1,p2} ) ;
        
        System.out.println("Voiture " + v1.id + " peut prendre " + v1.capa + " personnes et stationne à  " + v1.ville);
        System.out.println("Personne "+ p1.id + " s'appelle " + p1.nom + " et habite à " + p1.ville);
        System.out.println("Marseille est desservie : " + c1.villeDesservie("Marseille"));
        System.out.println("Le nombre d'étudiants habitant à Marseille est " + c1.nbPersonnes("Marseille"));
        
        if (c1.capaciteSuffisante("Marseille")) {
        	System.out.println("Marseille a une capacité suffisante");
        }
        String [] t = {"Marseille","Paris"};
        if (c1.villeEstDans(t,"Paris")) {
        	System.out.println("Inside");
        }
        System.out.println("Liste des villes : ");
        for (String ville : c1.getVilles()) {
        	System.out.println(ville);
        }
    }
}
