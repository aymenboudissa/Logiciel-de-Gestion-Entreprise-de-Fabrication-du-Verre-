package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CaisseProduit {
private Integer id_produit;
private Double quantite;
private String prix;
private String designation;
private Integer id_achat;
private String totale;

public String getTotale() {
	return totale;
}
public void setTotale(String totale) {
	this.totale = totale;
}
private String Query;
private PreparedStatement statement;

public Integer getId_produit() {
	return id_produit;
}
public void setId_produit(Integer id_produit) {
	this.id_produit = id_produit;
}
public Double getQuantite() {
	return quantite;
}
public void setQuantite(Double quantite) {
	this.quantite = quantite;
}
public String getPrix() {
	return prix;
}
public void setPrix(String prix) {
	this.prix = prix;
}
public Integer getId_achat() {
	return id_achat;
}
public void setId_achat(Integer id_achat) {
	this.id_achat = id_achat;
}
public void inserer(Connection conn) throws SQLException {
	Query="insert into caisse_accessoire(`id_achat`,id_accessoire,"
			+ "`qte`,`prix_unitaire`) "
			+ "values(?,?,?,?)";
	statement=conn.prepareStatement(Query);
	statement.setInt(1, this.getId_achat());
	statement.setInt(2, this.getId_produit());
	statement.setDouble(3, this.getQuantite());
	statement.setDouble(4, Double.parseDouble(this.getPrix()));
	statement.execute();
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}


}
