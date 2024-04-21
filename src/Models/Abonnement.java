package Models;

import java.time.LocalDate;

public class Abonnement {
	
	private LocalDate date_creation ;
	private double frais_Abonnement;
	private int idabonn;
	public Abonnement() {
		super();
	}
	public Abonnement(LocalDate date_creation, double frais_Abonnement, int idabonn) {
		super();
		this.date_creation = date_creation;
		this.frais_Abonnement = frais_Abonnement;
		this.idabonn = idabonn;
	}
	public LocalDate getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(LocalDate date_creation) {
		this.date_creation = date_creation;
	}
	public double getFrais_Abonnement() {
		return frais_Abonnement;
	}
	public void setFrais_Abonnement(double frais_Abonnement) {
		this.frais_Abonnement = frais_Abonnement;
	}
	public int getIdabonn() {
		return idabonn;
	}
	public void setIdabonn(int idabonn) {
		this.idabonn = idabonn;
	}
	

}
