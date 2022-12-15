package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Travaille {
private Integer id_employe;
private Double nbr_heure;
private String date;
private int semaine;
public int getSemaine() {
	return semaine;
}
public void setSemaine(int semaine) {
	this.semaine = semaine;
}
public Integer getId_employe() {
	return id_employe;
}
public void setId_employe(Integer id_employe) {
	this.id_employe = id_employe;
}
public Double getNbr_heure() {
	return nbr_heure;
}
public void setNbr_heure(Double nbr_heure) {
	this.nbr_heure = nbr_heure;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
	public void insert(Connection connection) throws SQLException {
		String query="insert into heure_supplementaire(`id_employe`,`nbr_heure`,`date_supplementaire`,`semaine`,`description`) "
				+ "values(?,?,?,?,?)";
		PreparedStatement statement= connection.prepareStatement(query);
		statement.setInt(1, this.getId_employe());
		statement.setDouble(2, this.getNbr_heure());
		statement.setString(3, this.getDate());
		statement.setInt(4, this.getSemaine());
		statement.setString(5, this.nbr_heure+"h de travaille");
		statement.execute();
	} 
}
