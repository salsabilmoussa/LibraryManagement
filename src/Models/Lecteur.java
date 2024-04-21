package Models;

public class Lecteur {
	 private long cin ;
	 private String nom ;
	 private String prenom ;
	 private long credit ;
	  
	 public Lecteur() {
		super();
	 }

	public Lecteur(long cin, String nom, String prenom, long credit) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.credit = credit;
	}

	public long getCin() {
		return cin;
	}

	public void setCin(long cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}
	 

}
