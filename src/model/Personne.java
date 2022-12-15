package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Personne {
private Integer id;
private String nom;
private String prenom;
private Integer num_telephone;
private String type;
private String adresse;
private Integer id_type;
private String solde; 
public String getSolde() {
	return solde;
}
public void setSolde(String solde) {
	this.solde = solde;
}
public Integer getId_type() {
	return id_type;
}
public void setId_type(Integer id_type) {
	this.id_type = id_type;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public Integer getNum_telephone() {
	return num_telephone;
}
public void setNum_telephone(Integer num_telephone) {
	this.num_telephone = num_telephone;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
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
public void inserer(Connection connection) throws SQLException {
	String query="insert into personnes(`nom`,`prenom`,`num_telephone`,`adresse`,`id_type`,`solde`) "
			+ "values(?,?,?,?,?,?)";
	 PreparedStatement statement= connection.prepareStatement(query);
	 statement.setString(1, this.getNom());
	 statement.setString(2, this.getPrenom());
	 statement.setInt(3, this.getNum_telephone());
	 statement.setString(4, this.getAdresse());
	 statement.setInt(5, this.getId_type());
	 statement.setDouble(6, Double.parseDouble(this.getSolde()));
	 
	 statement.execute();	 
	 
}
public void update(Connection connection) throws SQLException {
	String query="update personnes set nom=?,prenom=?,num_telephone=?,adresse=? ,id_type=?,solde=? "
			+ "where id_personne=? ";
	PreparedStatement statement= connection.prepareStatement(query);
	statement.setString(1, this.getNom());
	statement.setString(2, this.getPrenom());
	statement.setInt(3, this.getNum_telephone());
	statement.setString(4, this.getAdresse());
	statement.setInt(5, this.getId_type());
	statement.setDouble(6,Double.parseDouble(this.getSolde()));
	statement.setInt(7, this.getId());
	statement.execute();	 
	
}

}
