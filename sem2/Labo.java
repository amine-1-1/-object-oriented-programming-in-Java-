class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private int poids; 
    private String couleur;
    private int age;
    private int esperanceVie;
    private boolean clonee;

    public Souris(int unPoids, String uneCouleur, int unAge, int EsperanceVie) {
        this.poids = unPoids ;
        this.couleur = uneCouleur;
        this.age = unAge;
        this.esperanceVie = EsperanceVie;
        this.clonee = false;
        System.out.println("Une nouvelle souris !");
    }
    
    public Souris(int Poids, String Couleur, int unAge) {
        this(Poids, Couleur, Age, ESPERANCE_VIE_DEFAUT);
    }

    public Souris(int Poids, String uneCouleur) {
        this(Poids, Couleur, 0);
    }

    public Souris(Souris autreSouris) {
        poids = autreSouris.poids;
        couleur = autreSouris.couleur;
        age = autreSouris.age;
        esperanceVie = autreSouris.esperanceVie * 4 / 5;
        clonee = true;
        System.out.println("Clonage d'une souris !");
    }

    public String toString() {
        String affichage = "Une souris " + couleur;
        if(clonee) {
            affichage += ", clonee,";
        } 
        affichage += " de " + age +" mois et pesant "+ poids +" grammes";
        return affichage;
    }

    public void vieillir() {
        age++;
        if (clonee && (age > (esperanceVie / 2))) {
            couleur = "verte";                
        }

    }

    public void evolue() {
        for(int i = age; i < esperanceVie ; i++) {
            vieillir();
        }
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}