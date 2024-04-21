package Models;
import java.time.*;


public class Emprunt {
	private Livre idLivre;
	private LocalDate dateEmprunt ;
	private LocalDate dateRetour;
	private Lecteur cinLecteur;
	public Emprunt() {
		super();
	}
	public Emprunt(Livre idLivre, Lecteur cinLecteur) {
		super();
		this.idLivre = idLivre;
		this.dateEmprunt = LocalDate.now();
		this.dateRetour = dateEmprunt.plusDays(7);
		this.cinLecteur = cinLecteur;
	}
	public Livre getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(Livre idLivre) {
		this.idLivre = idLivre;
	}
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public LocalDate getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	public Lecteur getCinLecteur() {
		return cinLecteur;
	}
	public void setCinLecteur(Lecteur cinLecteur) {
		this.cinLecteur = cinLecteur;
	}

}
