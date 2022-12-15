package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.image.ImageView;

public class Absence {
private Integer Id;
private String employe;
private Integer id_employe;
private Integer id_type;
private String query;
private String jours;
private String semaine;
public String getJours() {
	return jours;
}
public void setJours(String jours) {
	this.jours = jours;
}
public String getSemaine() {
	return semaine;
}
public void setSemaine(String semaine) {
	this.semaine = semaine;
}
private PreparedStatement statement;

public Integer getId_type() {
	return id_type;
}
public void setId_type(Integer id_type) {
	this.id_type = id_type;
}
public Integer getId_employe() {
	return id_employe;
}
public void setId_employe(Integer id_employe) {
	this.id_employe = id_employe;
}
public String getTitre_absence() {
	return titre_absence;
}
public void setTitre_absence(String titre_absence) {
	this.titre_absence = titre_absence;
}
private String titre_absence;
private ImageView typeAbsence;
private String justification;
private String heure;
private String dateDebut;
public Integer getId() {
	return Id;
}
public void setId(Integer id) {
	Id = id;
}
public String getEmploye() {
	return employe;
}
public void setEmploye(String employe) {
	this.employe = employe;
}
public ImageView getTypeAbsence() {
	return typeAbsence;
}
public void setTypeAbsence(ImageView typeAbsence) {
	this.typeAbsence = typeAbsence;
}
public String getJustification() {
	return justification;
}
public void setJustification(String justification) {
	this.justification = justification;
}
public String getHeure() {
	return heure;
}
public void setHeure(String heure) {
	this.heure = heure;
}
public String getDateDebut() {
	return dateDebut;
}
public void setDateDebut(String dateDebut) {
	this.dateDebut = dateDebut;
}
	public void inserer(Connection connection) throws SQLException {
		String h= this.getHeure().isEmpty() ? "0.0" : this.getHeure();
                String st=this.getJours().isEmpty() ? "0.0" :this.getJours(); 
		query="insert into absences(`id_type`,`id_employe`,`date_debut`,`jour`,`heure`,`semaine`) "
				+ "values(?,?,?,?,?,?)";
		statement=connection.prepareStatement(query);
		statement.setInt(1, this.getId_type());
		statement.setInt(2, this.getId_employe());
		statement.setString(3, this.getDateDebut());
		statement.setDouble(4,  Double.parseDouble(st));
		statement.setDouble(5, Double.parseDouble(h));
		statement.setInt(6, Integer.parseInt(this.getSemaine()) );
		
		statement.execute();
	
	}
}
