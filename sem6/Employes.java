/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Employe {
	
	private final String nom;
	private double revenuMensuel;
	private int tauxOccupation;
	private double prime = 0;
	
	public Employe(String nom, double revenuMensuel, int tauxOccupation){
		
		this.nom = nom;
		this.revenuMensuel = revenuMensuel;
		
		
		if (tauxOccupation < 10){
			
			this.tauxOccupation = 10;
			
		} else if (tauxOccupation > 100){
			
			this.tauxOccupation = 100;
			
		} else {
			
			this.tauxOccupation = tauxOccupation;
			
		}
		
		System.out.print(this.displayConstruct1());
		
		
	}
	
	public Employe(String nom, double revenuMensuel){
		
		this.nom = nom;
		this.revenuMensuel = revenuMensuel;
		this.tauxOccupation = 100;
		
		System.out.print(this.displayConstruct1());
		
	}
	
	public String displayConstruct1(){
		
		String str = "Nous avons un nouvel employé : " + this.nom + ",";
		return str;
	}
	
	public String displayPrime(){
		
		String str = "";
		if(this.prime > 0){
			
			str += " francs," + " Prime : " + String.format("%.2f",this.prime) + ".";
			
		} else {
			
			str += " francs.";
			
		}
		
		return str;
		
	}
	
	public double revenuAnnuel(){
		
		return this.salaireBase();
		
	}
	
	public double salaireBase(){
		
		return this.revenuMensuel * 12 * this.tauxOccupation/100  + this.prime;
		
	}
	
	public String toString(){
		
		String str = this.nom + " : ";
		str +=  "\n  Taux d'occupation : " + this.tauxOccupation + "%. ";
		str+= "Salaire annuel : " + String.format("%.2f", this.revenuAnnuel());
		str += this.displayPrime();
		
		return str;
		
	}
	
	public void demandePrime(){
		
		Scanner clavier = new Scanner(System.in);
		double ddePrime = 0;
		int i = 0;
		
		
		
		do{
			
			try{
				System.out.println("Montant de la prime souhaitée par " + this.nom + " ?");
				ddePrime = clavier.nextDouble();
				
			} catch(InputMismatchException e){
				
				System.out.println("Vous devez introduire un nombre!");
				clavier.nextLine();
				i++;
				
			}
			
			
			if (ddePrime > 0.02*salaireBase() && ddePrime != 0){
				i++;
				System.out.println("Trop cher!");
				ddePrime = 0;
				
			}
		
		}while ((ddePrime > 0.02*salaireBase() || ddePrime == 0) && i < 5  ) ;
		
		if(i < 5){
			
			this.prime = ddePrime;
			
		}
		
		
	}

	
}

class Manager extends Employe{
	
	private int joursVoyages;
	private int nouveauxClients;
	public static final int FACTEUR_GAIN_CLIENT = 500;
	public static final int FACTEUR_GAIN_VOYAGE = 100;
	
	public Manager(String nom, double revenuMensuel,
			int joursVoyages,int nouveauxClients,int tauxOccupation){
		
		super(nom, revenuMensuel, tauxOccupation);
		this.joursVoyages = joursVoyages;
		this.nouveauxClients = nouveauxClients;
		
		System.out.println(this.displayConstruct());
		
	}
	
	public Manager(String nom, double revenuMensuel,
			int joursVoyages, int nouveauxClients){
		
		super(nom, revenuMensuel);
		this.joursVoyages = joursVoyages;
		this.nouveauxClients = nouveauxClients;
		
		System.out.println(this.displayConstruct());
		
		
	}


	public String displayConstruct() {
		
		return " c'est un manager.";
	}
	
	public double revenuAnnuel(){
		
		return super.salaireBase() + this.bonus();
		
	}

	public double bonus() {
		
		return  Manager.FACTEUR_GAIN_CLIENT * this.nouveauxClients + Manager.FACTEUR_GAIN_VOYAGE * this.joursVoyages ;
	}


	public String info() {
		
		String str = "\n  A voyagé " + this.joursVoyages + " jours et apporté ";
		str += this.nouveauxClients + " nouveaux clients.";
		return str;
	}
	
	public String toString(){
		
			String str = super.toString();
			str += this.info();	
					
		return str;
		
	}
	
	
	
}

class Programmeur extends Employe{
	
	private int projetAcheves;
	public final static int FACTEUR_GAIN_PROJETS = 200;

	public Programmeur(String nom, double revenuMensuel,int projetAcheves) {
		super(nom, revenuMensuel);
		this.projetAcheves = projetAcheves;
		
		System.out.println(this.displayConstruct());
		
	}
	
	public Programmeur(String nom, double revenuMensuel, int projetAcheves, int tauxOccupation) {
		super(nom, revenuMensuel,tauxOccupation);
		this.projetAcheves = projetAcheves;
		
		System.out.println(this.displayConstruct());
		
	}


	public String displayConstruct() {
		
		return " c'est un programmeur.";
	}

	public double revenuAnnuel(){
		
		return super.salaireBase() + this.bonus();
		
	}

	public double bonus() {
		
		return Programmeur.FACTEUR_GAIN_PROJETS * this.projetAcheves;
		
	}
	
	public String toString(){
		
		String str = super.toString();
		str += this.info();	
				
	return str;
	
}

	public String info() {
		
		String str = "\n  A mené à bien " + this.projetAcheves;
		if(this.projetAcheves > 1){
			
			str += " projets";
			
		} else {
			
			str += " projet";
			
		}
		
		return str;
		
	}
	
}

class Testeur extends Employe{
	
	private int erreursCorriges;
	public static final int FACTEUR_GAIN_ERREURS = 10;

	public Testeur(String nom, double revenuMensuel, int erreurs, int tauxOccupation) {
		
		super(nom, revenuMensuel, tauxOccupation);
		this.erreursCorriges = erreurs;
		
		System.out.println(this.displayConstruct());
		
	}
	
	public Testeur(String nom, double revenuMensuel, int erreurs) {
		
		super(nom, revenuMensuel);
		this.erreursCorriges = erreurs;
		
		System.out.println(this.displayConstruct());
		
	}


	public String displayConstruct() {
		
		return " c'est un testeur.";
	}
	
	public double revenuAnnuel(){
		
		return super.salaireBase() + this.bonus();
		
	}


	public double bonus() {
		
		return this.erreursCorriges * Testeur.FACTEUR_GAIN_ERREURS;
		
	}
	
	public String toString(){
		
		String str = super.toString();
		str += this.info();	
				
	return str;
	
}


	public String info() {
		
		String str = "\n  A corrigé " + this.erreursCorriges;
		str += " erreurs."; 
					
		return str;
	}
	
	
	
}


/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

