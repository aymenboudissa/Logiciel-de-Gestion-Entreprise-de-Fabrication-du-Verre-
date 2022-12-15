package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Achat {
private Integer id;
private String fournisseur;
private String date_achat;
private String versement;
private String montant;
private String livraison;
private String qte;
private String montant_reste;
private String Query;
private Integer Id_fournisseur;
private Integer type_achat=0;
public Integer getType_achat() {
	return type_achat;
}
public void setType_achat(Integer type_achat) {
	this.type_achat = type_achat;
}
public Integer getId_fournisseur() {
	return Id_fournisseur;
}
public void setId_fournisseur(Integer id_fournisseur) {
	Id_fournisseur = id_fournisseur;
}
private PreparedStatement statement;

public String getQte() {
	return qte;
}
public void setQte(String qte) {
	this.qte = qte;
}
public String getMontant_reste() {
	return montant_reste;
}
public void setMontant_reste(String montant_reste) {
	this.montant_reste = montant_reste;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getFournisseur() {
	return fournisseur;
}
public void setFournisseur(String fournisseur) {
	this.fournisseur = fournisseur;
}

public String getDate_achat() {
	return date_achat;
}
public void setDate_achat(String date_achat) {
	this.date_achat = date_achat;
}
public String getVersement() {
	return versement;
}
public void setVersement(String versement) {
	this.versement = versement;
}
public String getMontant() {
	return montant;
}
public void setMontant(String montant) {
	this.montant = montant;
}
public String getMontantReste() {
	return montant_reste;
}
public void setMontantReste(String montantReste) {
	this.montant_reste= montantReste;
}
public String getLivraison() {
	return livraison;
}
public void setLivraison(String livraison) {
	this.livraison = livraison;
}
public void inserer(Connection conn) throws SQLException {
	 Query="insert into achat(`id_achat`,`id_fournisseur`,`date_achat`,`livraison`,`type_achat`)"
		 		+ " values(?,?,?,?,?) ";
	 statement=conn.prepareStatement(Query);
		statement.setInt(1, this.getId());
		statement.setInt(2, this.getId_fournisseur());
		statement.setString(3, this.getDate_achat());
		statement.setDouble(4, Double.parseDouble(this.getLivraison()));
		statement.setInt(5, this.getType_achat());
		statement.execute();
}

}
