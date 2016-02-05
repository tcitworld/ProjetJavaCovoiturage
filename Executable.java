public class Executable{
    public static void main(String[] args){
        Voiture v1=new Voiture(1,"Paris",4);
        Voiture v2=new Voiture(2,"Marseille",3);
        Voiture v3=new Voiture(3,"Paris",2);
        Personne p1=new Personne(1,"Toto","Paris",true);
        Personne p2=new Personne(2,"Titi","Paris",true);
        Personne p4=new Personne(4,"Yassine","Paris",true);
        Personne p3=new Personne(3,"Tutu","Marseille",false);
        Personne p5=new Personne(5,"Salomé","Paris",true);
        Personne p6=new Personne(6,"Yassin2","Paris",true);
        Personne p7=new Personne(7,"Arthur","Paris",true);
        Covoiturage c1=new Covoiturage(new Voiture[]{v1,v2,v3},new Personne[]{p1,p2,p3,p4,p5,p6,p7} ) ;
        
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
        System.out.println("Le Covoiturage est possible ?");
        System.out.println((c1.estPossible()) ? "oui !" : "non !");

        int[] tbl = c1.attribution();
        for (int i = 1; i < tbl.length; i++) {
            System.out.println("La personne " + i + " est dans la voiture " + tbl[i]);
        }

        c1.triePersonnes();

        for (int i = 0; i < c1.tp.length; i++) {
            System.out.println("La personne " + c1.tp[i].id + " est nommée " + c1.tp[i].nom + ".");
        }

        System.out.println("Yassine a comme identifiant " + c1.getIdentifiantDichotomique("Yassine"));
    }
}
