package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CaisseMatiere {
private String designation;
private Integer id_achat;
private Integer id_matiere;
private String Query;
private PreparedStatement statement;
public Integer getId_achat() {
	return id_achat;
}
public void setId_achat(Integer id_achat) {
	this.id_achat = id_achat;
}
public Integer getId_matiere() {
	return id_matiere;
}
public void setId_matiere(Integer id_matiere) {
	this.id_matiere = id_matiere;
}
private Double nbr_caisse;
private String longueur;
private String largeur;
private String prix_unitaire;
private String totale;
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public Double getNbr_caisse() {
	return nbr_caisse;
}
public void setNbr_caisse(Double nbr_caisse) {
	this.nbr_caisse = nbr_caisse;
}
public String getLongueur() {
	return longueur;
}
public void setLongueur(String longueur) {
	this.longueur = longueur;
}
public String getLargeur() {
	return largeur;
}
public void setLargeur(String largeur) {
	this.largeur = largeur;
}
public String getPrix_unitaire() {
	return prix_unitaire;
}
public void setPrix_unitaire(String prix_unitaire) {
	this.prix_unitaire = prix_unitaire;
}
public String getTotale() {
	
	return totale;
}
public void setTotale(String totale) {
	this.totale = totale;
}
public void inserer(Connection conn) throws SQLException {
	Query="insert into caisse_matiere(`id_achat`,`id_matiere`,"
			+ "`nbr_caisse`,`longueur`,`largeur`,`prix_unitaire`) "
			+ "values(?,?,?,?,?,?)";
	statement=conn.prepareStatement(Query);
	statement.setInt(1, this.getId_achat());
	statement.setInt(2, this.getId_matiere());
	statement.setDouble(3, this.getNbr_caisse());
	statement.setDouble(4, Double.parseDouble(this.getLongueur()));
	statement.setDouble(5, Double.parseDouble(this.getLargeur()));
	statement.setDouble(6, Double.parseDouble(this.getPrix_unitaire()));
	statement.execute();
}

}
