package model;

public class Paiement {
private String element;
private String date;
private String montant;
private String versement;

public String getElement() {
	return element;
}
public String getVersement() {
	return versement;
}
public void setVersement(String versement) {
	this.versement = versement;
}
public void setElement(String element) {
	this.element = element;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getMontant() {
	return montant;
}
public void setMontant(String montant) {
	this.montant = montant;
}


}
