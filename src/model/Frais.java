package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Frais {
private Integer id_employe;
private String date_frais;
private Double cout_frais;
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
public String getDate_frais() {
	return date_frais;
}
public void setDate_frais(String date_frais) {
	this.date_frais = date_frais;
}
public Double getCout_frais() {
	return cout_frais;
}
public void setCout_frais(Double cout_frais) {
	this.cout_frais = cout_frais;
}
public void insert(Connection conn) throws SQLException {
	String query="insert into frais(`id_employe`,`date_frais`,`cout_frais`,`description`,`semaine`) values(?,?,?,?,?)";
	PreparedStatement statement=conn.prepareStatement(query);
	statement.setInt(1, this.getId_employe());
	statement.setString(2, this.getDate_frais());
	statement.setDouble(3, this.getCout_frais());
	statement.setString(4, "L'employé "+this.getId_employe()+" a prendre " +this.getCout_frais()+"DA" );
	statement.setInt(5, this.getSemaine());
	statement.execute();
}

}
