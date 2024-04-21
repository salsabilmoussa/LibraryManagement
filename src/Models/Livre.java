package Models;

public class Livre {
	 private long code ;
	 private String titre , auteur, type ;
	 private long ISBN;
	public Livre() {
		super();
	}
	public Livre(long code, String titre, String auteur, String type, long iSBN) {
		super();
		this.code = code;
		this.titre = titre;
		this.auteur = auteur;
		this.type = type;
		ISBN = iSBN;
	}
	public Livre(String titre) {
		super();
		this.titre = titre;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}
	 
	 

}
