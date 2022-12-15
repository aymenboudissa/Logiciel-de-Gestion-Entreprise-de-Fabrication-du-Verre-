package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.image.ImageView;

public class Matiere {
private String designation;
private String initiale,qteEntree,qteSortir,qteFinale;
private ImageView status;
public String getQteEntree() {
	return qteEntree;
}
public void setQteEntree(String qteEntree) {
	this.qteEntree = qteEntree;
}
public ImageView getStatus() {
	return status;
}
public void setStatus(ImageView status) {
	this.status = status;
}
public String getQteSortir() {
	return qteSortir;
}
public void setQteSortir(String qteSortir) {
	this.qteSortir = qteSortir;
}
public String getQteFinale() {
	return qteFinale;
}
public void setQteFinale(String qteFinale) {
	this.qteFinale = qteFinale;
}
private String cretique;
private Integer id_matiere;
private final String table="matiere_premiere";
private final String column1="id_matiere";
private final String column2="designation";
private final String column3="stock_initiale";
private final String column4="stock_cretique";
private PreparedStatement statement;
private String query="";

public Integer getId_matiere() {
	return id_matiere;
}
public void setId_matiere(Integer id_matiere) {
	this.id_matiere = id_matiere;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getInitiale() {
	return initiale;
}
public void setInitiale(String initiale) {
	this.initiale = initiale;
}
public String getCretique() {
	return cretique;
}
public void setCretique(String cretique) {
	this.cretique = cretique;
}
public void update(Connection connection) throws SQLException {
	 Double newCretique=Double.parseDouble(this.getCretique());
	 Double newInitiale=Double.parseDouble(this.getInitiale());
 query="update "+table+" set "
   		+ ""+column2+"= '"+this.getDesignation()+"' ,"
   		+ ""+column3+"= "+newInitiale+" ,"
   		+ ""+column4+"= "+newCretique+" "
   		+ " where  "
   		+ ""+column1+" = "+this.getId_matiere()+" ";
   		statement=connection.prepareStatement(query);
   		statement.execute();
   		
}
public void inserer(Connection connection) throws SQLException {
	Double newInitiale=Double.parseDouble(this.getInitiale());
	Double newCretique=Double.parseDouble(this.getCretique());
    query="insert into `matiere_premiere`( "
    		+ "`designation`,`stock_initiale`,`stock_cretique`) "
    		+ "values(?,?,?)";
    statement=connection.prepareStatement(query);
    statement.setString(1, this.getDesignation());
    statement.setDouble(2, newInitiale);
    statement.setDouble(3, newCretique);
    statement.execute();
}

}
