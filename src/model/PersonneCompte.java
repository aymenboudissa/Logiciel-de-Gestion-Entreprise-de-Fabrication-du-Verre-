package model;

import javafx.scene.image.ImageView;

public class PersonneCompte {
	private String client,montant,versement,reste,prenom,solde;
	public String getSolde() {
		return solde;
	}
	public void setSolde(String solde) {
		this.solde = solde;
	}
	public ImageView getStatus() {
		return status;
	}
	public void setStatus(ImageView status) {
		this.status = status;
	}
	private ImageView status;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getClient() {
		return client;
	}

	public String getVersement() {
		return versement;
	}

	public void setVersement(String versement) {
		this.versement = versement;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public String getReste() {
		return reste;
	}

	public void setReste(String reste) {
		this.reste = reste;
	}


}
