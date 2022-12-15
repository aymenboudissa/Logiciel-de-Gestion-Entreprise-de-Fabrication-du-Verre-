package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class BisouRectelign {
private Integer id;
private String designation;
private String prix;
private String query;
private PreparedStatement statement;

public String getPrix() {
	return prix;
}
public void setPrix(String prix) {
	this.prix = prix;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}

public void update(Connection connection) throws SQLException {
	query="update fonctionnalite set activitie=?,prix_unitaire=? "
			+ "where id_type=?";
	  statement=connection.prepareStatement(query);
	  statement.setString(1, this.getDesignation());
	  statement.setDouble(2, Double.parseDouble(this.getPrix()));
	  statement.setInt(3, this.getId());
	  statement.execute();
	  
}
public void insert(Connection connection) throws SQLException {
	query="insert into fonctionnalite(`activitie`,`prix_unitaire`,`type`) "
			+ "values(?,?,?)";
	statement=connection.prepareStatement(query);
	statement.setString(1, this.getDesignation());
	statement.setDouble(2, Double.parseDouble(this.getPrix()));
	statement.setInt(3,0);
	statement.execute();
	
}
}
