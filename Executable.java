public class Executable{
    public static void main(String[] args){
        Voiture v1=new Voiture(1,"Paris",5);
        Voiture v2=new Voiture(2,"Marseille",3);
        Voiture v3=new Voiture(3,"Paris",2);
        Personne p1=new Personne(1,"Toto","Paris",true);
        Personne p2=new Personne(2,"Titi","Paris",true);
        Personne p3=new Personne(3,"Tutu","Marseille",false);
        Covoiturage c1=new Covoiturage(new Voiture[]{v1,v2,v3},new Personne[]{p1,p2,p3} ) ;
        
        System.out.println("Voiture " + v1.id + " peut prendre " + v1.capa + " personnes et stationne à " + v1.ville);

        System.out.println("Voiture " + v2.id + " peut prendre " + v2.capa + " personnes et stationne à " + v2.ville);

        System.out.println("Personne "+ p1.id + " s'appelle " + p1.nom + " et habite à " + p1.ville);
        System.out.println("Marseille est desservie : " + c1.villeDesservie("Marseille"));
        System.out.println("Le nombre d'étudiants habitant à Marseille est " + c1.nbPersonnes("Marseille"));
        
        if (c1.capaciteSuffisante("Marseille")) {
        	System.out.println("Marseille a une capacité suffisante");
        }
        String [] t = {"Marseille","Paris"};
        if (c1.villeEstDans(t,"Paris")) {
        	System.out.println("Paris est bien dans le tableau");
        }
        System.out.println("Liste des villes : ");
        for (String ville : c1.getVilles()) {
        	System.out.println("* " + ville);
        }
        //c1.estPossible();

        for (Voiture voit : c1.getVoitures("Paris")) {
             System.out.println(voit.id);
        }

        for (Personne pers : c1.getPersonnes("Paris")) {
            System.out.println(pers.id);
        }
    }
}
