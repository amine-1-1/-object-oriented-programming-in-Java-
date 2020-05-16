import java.util.ArrayList;

class Auteur {
    
    
    private String nom;
    private boolean priX; 
    
    public Auteur(String nom, boolean priX) {
        this.nom = nom;
        this.priX = priX;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public boolean getPrix() {
        return this.priX;
    }
    
}

class Oeuvre
{
    
    private String titre;
    private String langue;
    private Auteur auteur;
    
    public Oeuvre(String titre, Auteur auteur, String langue) {
        this.titre = titre;
        this.auteur = auteur;
        this.langue = langue;
    }
    
    public Oeuvre(String titre, Auteur auteur) {
        this(titre, auteur, "francais");
    }
    
    public String getTitre() {
        return this.titre;
    }
    
    public Auteur getAuteur() {
        return this.auteur;
    }
    
    public String getLangue() {
        return this.langue;
    }
    
    public String afficher() {
        return this.titre + ", " + this.auteur.getNom() + ", en " + this.langue;
    }
}

class Exemplaire {
    private Oeuvre oeuvre;
    private String message;
    
    public Exemplaire(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
        this.message = "Nouvel exemplaire -> ";
        this.afficher();
    }
    
    public Exemplaire(Exemplaire exemplaire) {
        this.oeuvre = exemplaire.getOeuvre();
        this.message = "Copie d'un exemplaire de -> ";
        this.afficher();
    }
    
    public Oeuvre getOeuvre() {
        return this.oeuvre;
    }
    
    public void afficher() {
        System.out.print(this.message + this.oeuvre.afficher() + "\n");
        this.message = "Un exemplaire de ";
    }
}

class Bibliotheque {
    private String nom;
    private ArrayList<Exemplaire> exemplaires = new ArrayList();;
    
    public Bibliotheque(String nom) {
        this.nom = nom;
        System.out.print("La bibliotheque " + this.nom + " est ouverte !\n");
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public int getNbExemplaires(){
        return this.exemplaires.size();
    }
    
    public void stocker(Oeuvre o) {
        Exemplaire exemplaire = new Exemplaire(o);
        this.exemplaires.add(exemplaire);
    }
    
    public void stocker(Oeuvre o, int n) {
        for (int i = 0; i < n; i++) {
            Exemplaire exemplaire = new Exemplaire(o);
            this.exemplaires.add(exemplaire);
        }
    }
    
    public ArrayList<Exemplaire> listerExemplaires() {
        return this.exemplaires;
    }
    
    public ArrayList<Exemplaire> listerExemplaires(String langue) {
        ArrayList<Exemplaire> exemplairesParLangue = new ArrayList();
        for(Exemplaire exemplaire : this.exemplaires) {
            if(exemplaire.getOeuvre().getLangue() == langue) {
                exemplairesParLangue.add(exemplaire);
            }
        }
        return exemplairesParLangue;
    }
    
    public int compterExemplaires(Oeuvre oeuvre) {
        int nExemplaires = 0;
        for (Exemplaire exemplaire : this.exemplaires) {
            nExemplaires += exemplaire.getOeuvre() == oeuvre ? 1 : 0;
        }
        return nExemplaires;
    }
    
    public void afficherAuteur(boolean value) {
        for (Exemplaire exemplaire : this.exemplaires) {
            Auteur auteur = exemplaire.getOeuvre().getAuteur();
            if (!value && !auteur.getPrix()) {
                System.out.print(auteur.getNom() + "\n");
            } else if (value && auteur.getPrix()) { 
                System.out.print(auteur.getNom() + "\n");
            }
        }
    }
    
    public void afficherAuteur() {
        this.afficherAuteur(true);
    }
}



public class Biblio {
    
    public static void afficherExemplaires(ArrayList<Exemplaire> exemplaires) {
        for (Exemplaire exemplaire : exemplaires) {
            System.out.print("\t");
            exemplaire.afficher();
        }
    }
    
    public static void main(String[] args) {
        // create and store all the exemplaries
        Auteur a1 = new Auteur("Victor Hugo", false);
        Auteur a2 = new Auteur("Alexandre Dumas", false);
        Auteur a3 = new Auteur("Raymond Queneau", true);
        
        Oeuvre o1 = new Oeuvre("Les Miserables", a1, "francais");
        Oeuvre o2 = new Oeuvre("L\'Homme qui rit", a1, "francais");
        Oeuvre o3 = new Oeuvre("Le Comte de Monte-Cristo", a2, "francais");
        Oeuvre o4 = new Oeuvre("Zazie dans le metro", a3, "francais");
        Oeuvre o5 = new Oeuvre("The count of Monte-Cristo", a2, "anglais");
        
        Bibliotheque biblio = new Bibliotheque("municipale");
        biblio.stocker(o1, 2);
        biblio.stocker(o2);
        biblio.stocker(o3, 3);
        biblio.stocker(o4);
        biblio.stocker(o5);
        
        // ...
        System.out.println("La bibliotheque " + biblio.getNom() + " offre ");
        afficherExemplaires(biblio.listerExemplaires());
        String langue = "anglais";
        System.out.println("Les exemplaires en " + langue + " sont  ");
        afficherExemplaires(biblio.listerExemplaires(langue));
        System.out.println("Les auteurs a succes sont  ");
        biblio.afficherAuteur();
        System.out.print("Il y a " + biblio.compterExemplaires(o3) + " exemplaires");
        System.out.println(" de  " + o3.getTitre());
    }
}
