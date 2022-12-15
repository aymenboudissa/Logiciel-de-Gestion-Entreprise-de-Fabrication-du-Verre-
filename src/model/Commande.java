package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Commande {
private Integer id; 
private String client ;
private Integer id_client ;
private String dateCommande;
private String date_livraison;
private Double qte_totale;

public Double getQte_totale() {
	return qte_totale;
}
public void setQte_totale(Double qte_totale) {
	this.qte_totale = qte_totale;
}
public Integer getId_client() {
	return id_client;
}
public void setId_client(Integer id_client) {
	this.id_client = id_client;
}
private String Versement;
private String montant;
private String montantReste;
private String query;
private PreparedStatement statement;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getClient() {
	return client;
}
public void setClient(String client) {
	this.client = client;
}
public String getDateCommande() {
	return dateCommande;
}
public void setDateCommande(String dateCommande) {
	this.dateCommande = dateCommande;
}
public String getVersement() {
	return Versement;
}
public void setVersement(String versement) {
	Versement = versement;
}
public String getMontant() {
	return montant;
}
public void setMontant(String montant) {
	this.montant = montant;
}
public String getMontantReste() {
	return montantReste;
}
public void setMontantReste(String montantReste) {
	this.montantReste = montantReste;
}

public void Inserer(Connection connection) throws SQLException {
	query="insert into commande(`id_commande`,`id_personne`,`date_commande`,`date_livraison`) "
			+ "values(?,?,?,?)";
	statement=connection.prepareStatement(query);
	statement.setInt(1,this.getId());
	statement.setInt(2,this.getId_client());
	statement.setString(3, this.getDateCommande());	
	statement.setString(4, this.getDate_livraison());	
	statement.execute();
}
public String getDate_livraison() {
	return date_livraison;
}
public void setDate_livraison(String date_livraison) {
	this.date_livraison = date_livraison;
}
}
