package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.image.ImageView;

public class Justification {
private Integer id;
private Integer id_absence;
public Integer getId_absence() {
	return id_absence;
}
public void setId_absence(Integer id_absence) {
	this.id_absence = id_absence;
}
private String employe;
private String dateAbsence;
private String dateJustification ;
private String description;
private ImageView typeAbsence;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getEmploye() {
	return employe;
}
public void setEmploye(String employe) {
	this.employe = employe;
}
public String getDateAbsence() {
	return dateAbsence;
}
public void setDateAbsence(String dateAbsence) {
	this.dateAbsence = dateAbsence;
}
public String getDateJustification() {
	return dateJustification;
}
public void setDateJustification(String dateJustification) {
	this.dateJustification = dateJustification;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public ImageView getTypeAbsence() {
	return typeAbsence;
}
public void setTypeAbsence(ImageView typeAbsence) {
	this.typeAbsence = typeAbsence;
}
public void inserer(Connection connection) throws SQLException {
	String query="insert into justifications(`id_absence`,`date_justification`,description) "
			+ "values(?,?,?)";
PreparedStatement statement=connection.prepareStatement(query);
statement.setInt(1, this.getId_absence());
statement.setString(2, this.getDateJustification());
statement.setString(3, this.getDescription());
statement.execute();
String query2="update absences set justifier='Oui' where id_absence="+this.getId_absence()+"";
statement =connection.prepareStatement(query2);
statement.execute();
}

}

