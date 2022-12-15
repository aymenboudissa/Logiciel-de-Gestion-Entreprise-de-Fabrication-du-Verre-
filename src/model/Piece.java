package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.image.ImageView;

public class Piece {
private Integer id_accessoire;
private String designation;
private Double initiale;
private Double cretique;
private ImageView status;
public ImageView getStatus() {
	return status;
}
public void setStatus(ImageView status) {
	this.status = status;
}
private Double qteEntree,qteConsommer,qteFinale;

public Double getQteFinale() {
	return qteFinale;
}
public void setQteFinale(Double qteFinale) {
	this.qteFinale = qteFinale;
}
public Double getQteEntree() {
	return qteEntree;
}
public void setQteEntree(Double qteEntree) {
	this.qteEntree = qteEntree;
}
public Double getQteConsommer() {
	return qteConsommer;
}
public void setQteConsommer(Double qteConsommer) {
	this.qteConsommer = qteConsommer;
}
private PreparedStatement statement;
private String query="";



public Integer getId_accessoire() {
	return id_accessoire;
}
public void setId_accessoire(Integer id_accessoire) {
	this.id_accessoire = id_accessoire;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public Double getInitiale() {
	return initiale;
}
public void setInitiale(Double initiale) {
	this.initiale = initiale;
}
public Double getCretique() {
	return cretique;
}
public void setCretique(Double cretique) {
	this.cretique = cretique;
}

public void inserer(Connection conn) throws SQLException {
	query="insert into `accessoires`(`designation`,`stock_cretique`,`stock_initiale`) "
			+ "values(?,?,?)";
	statement =conn.prepareStatement(query);
	statement.setString(1, this.designation);
	statement.setDouble(2, this.cretique);
	statement.setDouble(3, this.initiale);
	statement.execute();
	
}
public void update(Connection connection) throws SQLException {
	query="update accessoires set "
			+ "designation = '"+this.designation+"' , "
			 + "stock_cretique = "+this.getCretique()+", "
			  + "stock_initiale = "+this.getInitiale()+" "
			  		+ "where id_accessoire= "+this.getId_accessoire()+"";
	statement =connection.prepareStatement(query);
	statement.execute();
}
}
