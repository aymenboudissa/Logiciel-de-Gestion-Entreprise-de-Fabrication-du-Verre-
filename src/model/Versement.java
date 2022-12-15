package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Versement {
private Integer id ;
private String versement;
private String date_versement;
private String query="";
public Integer getTypeAchat() {
	return typeAchat;
}
public void setTypeAchat(Integer typeAchat) {
	this.typeAchat = typeAchat;
}
private Integer typeAchat;
private PreparedStatement statement;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getVersement() {
	return versement;
}
public void setVersement(String versement) {
	this.versement = versement;
}
public String getDate_versement() {
	return date_versement;
}
public void setDate_versement(String date_versement) {
	this.date_versement = date_versement;
}
public void insererCmd(Connection conn) throws SQLException {
	query="insert into versement_cmd(`date_versement`,`versement`,`id_etranger`) "
			+ "values(?,?,?)";
	statement=conn.prepareStatement(query);
	statement.setString(1,this.getDate_versement());
	Double value=Double.parseDouble(this.getVersement());
	statement.setDouble(2,value);
	statement.setInt(3, this.getId());
	statement.execute();
	
   
}
public void insererAchat(Connection conn) throws SQLException {
	query="insert into versement_achat(`date_versement`,`versement`,`id_etranger`,`type`) "
			+ "values(?,?,?,?)";
	statement=conn.prepareStatement(query);
	statement.setString(1,this.getDate_versement());
	Double value=Double.parseDouble(this.getVersement());
	statement.setDouble(2,value);
	statement.setInt(3, this.getId());
	statement.setInt(4, this.getTypeAchat());
	statement.execute();
	
	
}
}
