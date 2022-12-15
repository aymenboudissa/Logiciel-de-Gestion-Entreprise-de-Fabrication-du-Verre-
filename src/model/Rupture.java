package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Rupture {
private Integer idCommande,idList;
private Double qte;
private String descritpion;
private String produit,montant;
public String getMontant() {
	return montant;
}
public void setMontant(String montant) {
	this.montant = montant;
}
public String getPrix_achat() {
	return prix_achat;
}
public void setPrix_achat(String prix_achat) {
	this.prix_achat = prix_achat;
}
private String prix_achat;
public String getProduit() {
	return produit;
}
public void setProduit(String produit) {
	this.produit = produit;
}
public String getDateRupture() {
	return dateRupture;
}
public void setDateRupture(String dateRupture) {
	this.dateRupture = dateRupture;
}
private String dateRupture;
public Integer getIdCommande() {
	return idCommande;
}
public void setIdCommande(Integer idCommande) {
	this.idCommande = idCommande;
}
public Integer getIdList() {
	return idList;
}
public void setIdList(Integer idList) {
	this.idList = idList;
}
public Double getQte() {
	return qte;
}
public void setQte(Double qte) {
	this.qte = qte;
}
public String getDescritpion() {
	return descritpion;
}
public void setDescritpion(String descritpion) {
	this.descritpion = descritpion;
}
public void inserer(Connection connection) throws SQLException {
	String query="insert into rupture(`id_commande`,`id_list`,`qte_produit`,`date_rupture`,`description`) "
			+ "values(?,?,?,?,?)";
	  PreparedStatement st= connection.prepareStatement(query);
	  st.setInt(1, this.getIdCommande());
	  st.setInt(2, this.getIdList());
	  st.setDouble(3, this.getQte());
	  st.setString(4, this.getDateRupture());
	  st.setString(5, this.getDescritpion());
	  st.execute();

}


}
