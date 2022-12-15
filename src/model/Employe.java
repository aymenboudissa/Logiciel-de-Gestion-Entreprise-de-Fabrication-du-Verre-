package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employe {
private Integer Id;
private String nom;
private String prenom;
private String DateNaissance;
private String DateRecrutement;
private String RevenuHebdomodaire;
private String adresse;
private Double frais;
private String absence;
public Double getFrais() {
	return frais;
}
public String getAbsence() {
	return absence;
}
public void setAbsence(String absence) {
	this.absence = absence;
}
public void setFrais(Double frais) {
	this.frais = frais;
}
private PreparedStatement statement;
private String query;
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public Integer getId() {
	return Id;
}
public void setId(Integer id) {
	Id = id;
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
public String getDateNaissance() {
	return DateNaissance;
}
public void setDateNaissance(String dateNaissance) {
	DateNaissance = dateNaissance;
}
public String getDateRecrutement() {
	return DateRecrutement;
}
public void setDateRecrutement(String dateRecrutement) {
	DateRecrutement = dateRecrutement;
}
public String getRevenuHebdomodaire() {
	return RevenuHebdomodaire;
}
public void setRevenuHebdomodaire(String revenuHebdomodaire) {
	RevenuHebdomodaire = revenuHebdomodaire;
}
 
public void inserer(Connection connection) throws SQLException {
	query="insert into employes(`nom_employe`,`prenom_employe`,`date_naissance`,"
			+ "`date_recrutement`,`adresse`,`revenu` ) "
			+ "values(?,?,?,?,?,?) ";
	 statement=connection.prepareStatement(query);
	 statement.setString(1, this.getNom());
	 statement.setString(2, this.getPrenom());
	 statement.setString(3, this.getDateNaissance());
	 statement.setString(4, this.getDateRecrutement());
	 statement.setString(5, this.getAdresse());
	 statement.setDouble(6, Double.parseDouble(this.getRevenuHebdomodaire()));
	 statement.execute();

}
public void update(Connection connection) throws SQLException {
	query="update employes set nom_employe=?,prenom_employe=?,date_naissance=?,date_recrutement=?,"
			+ "adresse=?,revenu=? where id_employe=? ";	
	statement=connection.prepareStatement(query);
	statement.setString(1, this.getNom());
	statement.setString(2, this.getPrenom());
	statement.setString(3, this.getDateNaissance());
	statement.setString(4, this.getDateRecrutement());
	statement.setString(5, this.getAdresse());
	statement.setDouble(6, Double.parseDouble(this.getRevenuHebdomodaire()));
	statement.setInt(7, this.getId());
	statement.execute();
	
}

}
