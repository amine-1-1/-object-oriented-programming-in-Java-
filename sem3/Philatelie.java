import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2014;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private String code;
    private int anneeEmission;
    private String paysOrigine;
    private double valeurFaciale;

    public Timbre(String unCode, int uneAnneeEmission, String unPaysOrigine, double uneValeurFaciale) {
        code = unCode;
        anneeEmission = uneAnneeEmission;
        paysOrigine = unPaysOrigine;
        valeurFaciale = uneValeurFaciale;
    }

    public Timbre(String unCode, int uneAnneeEmission, String unPaysOrigine) {
        this(unCode, uneAnneeEmission, unPaysOrigine, VALEUR_TIMBRE_DEFAUT);
    }

    public Timbre(String unCode, int uneAnneeEmission) {
        this(unCode, uneAnneeEmission, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }

    public Timbre(String unCode) {
        this(unCode, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }

    public Timbre() {
        this(CODE_DEFAUT, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }

    public double vente() {
        if (age() <= 5) {
            return valeurFaciale;
        } else {
            return valeurFaciale * age() * 2.5;
        }
    }

    public String toString() {
        return "Timbre de code "+ code +" datant de "+ anneeEmission +" (provenance "+ paysOrigine +") " +
                "ayant pour valeur faciale "+ valeurFaciale +" francs";
    }

    public int age() {
        return ANNEE_COURANTE - anneeEmission;
    }

    public String getCode() { return code; }
    public int getAnnee() { return anneeEmission; }
    public String getPays() { return paysOrigine; }
    public double getValeurFaciale() { return valeurFaciale; }
}

class Rare extends Timbre {
    private int exemplaires;
    
    public Rare(int nombre) {
        super();
        exemplaires = nombre;
    }

    public Rare(String unCode, int uneAnneeEmission, String unPaysOrigine, double uneValeurFaciale, int nbEx) {
        super(unCode, uneAnneeEmission, unPaysOrigine, uneValeurFaciale);
        exemplaires = nbEx;
    }

    public Rare(String unCode, int uneAnneeEmission, String unPaysOrigine, int nbEx) {
        super(unCode, uneAnneeEmission, unPaysOrigine);
        exemplaires = nbEx;
    }

    public Rare(String unCode, int uneAnneeEmission, int nbEx) {
        super(unCode, uneAnneeEmission);
        exemplaires = nbEx;
    }

    public Rare(String unCode, int nbEx) {
        super(unCode);
        exemplaires = nbEx;
    }

    public int getExemplaires() { 
        return exemplaires; 
    }

    public String toString() {
        return super.toString() + "\nNombre d'exemplaires -> " + exemplaires;
    }

    public double vente() {
        double prixBase;
        if (exemplaires < BASE_1_EXEMPLAIRES) {
            prixBase = PRIX_BASE_1;
        } else {
            if (exemplaires < BASE_2_EXEMPLAIRES) {
                prixBase = PRIX_BASE_2;
            } else {
                prixBase = PRIX_BASE_3;
            }
        }
        return prixBase * age() / 10;
    }

}

class Commemoratif extends Timbre {

    public Commemoratif(String unCode, int uneAnneeEmission, String unPaysOrigine, double uneValeurFaciale) {
        super(unCode, uneAnneeEmission, unPaysOrigine, uneValeurFaciale);
    }

    public Commemoratif(String unCode, int uneAnneeEmission, String unPaysOrigine) {
        super(unCode, uneAnneeEmission, unPaysOrigine);
    }

    public Commemoratif(String unCode, int uneAnneeEmission) {
        super(unCode, uneAnneeEmission);
    }

    public Commemoratif(String unCode) {
        super(unCode);
    }

    public Commemoratif() {
        super();
    }

    public String toString() {
        return super.toString() + "\nTimbre celebrant un evenement";
    }

    public double vente() {
        return super.vente() * 2;
    }
}
	
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}