package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import controller.helpers.DynamiqueView;

public class ListeCommande {
private Integer id_commande,id_produit,id_br,br_left,br_right,br_top,br_bottom,id_list;
private Double qte,longueur,largeur,bis_rect,incision,sablage,prix;
private String incision_m,sablage_m;
private String montant_article,montant_br,montant_incision,montant_sablage,matiere_premier,type_br;
private String prixIncision,prixSablage,titleIncision="",titleSablage,prix_br,longueur_m,largeur_m,prixArticle,longueurBr_gauche,longueurBr_droit,largeurBr_bottom,largeurBr_top;


public Integer getId_list() {
	return id_list;
}
public void setId_list(Integer id_list) {
	this.id_list = id_list;
}
public String getPrixArticle() {
	return prixArticle;
}
public void setPrixArticle(String prixArticle) {
	this.prixArticle = prixArticle;
}
public String getTitleIncision() {
	return titleIncision;
}
public String getIncision_m() {
	return incision_m;
}
public void setIncision_m(String incision_m) {
	this.incision_m = incision_m;
}
public String getSablage_m() {
	return sablage_m;
}
public void setSablage_m(String sablage_m) {
	this.sablage_m = sablage_m;
}
public String getPrixIncision() {
	return prixIncision;
}
public void setPrixIncision(String prixIncision) {
	this.prixIncision = prixIncision;
}
public String getPrixSablage() {
	return prixSablage;
}
public void setPrixSablage(String prixSablage) {
	this.prixSablage = prixSablage;
}
public void setTitleIncision(String titleIncision) {
	this.titleIncision = titleIncision;
}
public String getTitleSablage() {
	return titleSablage;
}
public void setTitleSablage(String titleSablage) {
	this.titleSablage = titleSablage;
}
public String getPrix_br() {
	return prix_br;
}
public void setPrix_br(String prix_br) {
	this.prix_br = prix_br;
}
public String getLongueur_m() {
	return longueur_m;
}
public String getLongueurBr_gauche() {
	return longueurBr_gauche;
}
public void setLongueurBr_gauche(String longueurBr_gauche) {
	this.longueurBr_gauche = longueurBr_gauche;
}
public String getLongueurBr_droit() {
	return longueurBr_droit;
}
public void setLongueurBr_droit(String longueurBr_droit) {
	this.longueurBr_droit = longueurBr_droit;
}
public String getLargeurBr_bottom() {
	return largeurBr_bottom;
}
public void setLargeurBr_bottom(String largeurBr_bottom) {
	this.largeurBr_bottom = largeurBr_bottom;
}
public String getLargeurBr_top() {
	return largeurBr_top;
}
public void setLargeurBr_top(String largeurBr_top) {
	this.largeurBr_top = largeurBr_top;
}
public void setLongueur_m(String longueur_m) {
	this.longueur_m = longueur_m;
}
public String getLargeur_m() {
	return largeur_m;
}
public void setLargeur_m(String largeur_m) {
	this.largeur_m = largeur_m;
}

public String getMontant_article() {
	return montant_article;
}
public String getMatiere_premier() {
	return matiere_premier;
}
public void setMatiere_premier(String matiere_premier) {
	this.matiere_premier = matiere_premier;
}
public String getType_br() {
	return type_br;
}
public void setType_br(String type_br) {
	this.type_br = type_br;
}
public void setMontant_article(String montant_article) {
	this.montant_article = montant_article;
}
public String getMontant_br() {
	return montant_br;
}
public void setMontant_br(String montant_br) {
	this.montant_br = montant_br;
}
public String getMontant_incision() {
	return montant_incision;
}
public void setMontant_incision(String montant_incision) {
	this.montant_incision = montant_incision;
}
public String getMontant_sablage() {
	return montant_sablage;
}
public void setMontant_sablage(String montant_sablage) {
	this.montant_sablage = montant_sablage;
}
private String query="";
private PreparedStatement statement;
public Integer getId_commande() {
	return id_commande;
}
public void setId_commande(Integer id_commande) {
	this.id_commande = id_commande;
}
public Integer getId_produit() {
	return id_produit;
}
public void setId_produit(Integer id_produit) {
	this.id_produit = id_produit;
}
public Integer getId_br() {
	return id_br;
}
public void setId_br(Integer id_br) {
	this.id_br = id_br;
}
public Integer getBr_left() {
	return br_left;
}
public void setBr_left(Integer br_left) {
	this.br_left = br_left;
}
public Integer getBr_right() {
	return br_right;
}
public void setBr_right(Integer br_right) {
	this.br_right = br_right;
}
public Integer getBr_top() {
	return br_top;
}
public void setBr_top(Integer br_top) {
	this.br_top = br_top;
}
public Integer getBr_bottom() {
	return br_bottom;
}
public void setBr_bottom(Integer br_bottom) {
	this.br_bottom = br_bottom;
}
public Double getQte() {
	return qte;
}
public void setQte(Double qte) {
	this.qte = qte;
}
public Double getLongueur() {
	return longueur;
}
public void setLongueur(Double longueur) {
	this.longueur = longueur;
}
public Double getLargeur() {
	return largeur;
}
public void setLargeur(Double largeur) {
	this.largeur = largeur;
}
public Double getBis_rect() {
	return bis_rect;
}
public void setBis_rect(Double bis_rect) {
	this.bis_rect = bis_rect;
}
public Double getIncision() {
	return incision;
}
public void setIncision(Double incision) {
	this.incision = incision;
}
public Double getSablage() {
	return sablage;
}
public void setSablage(Double sablage) {
	this.sablage = sablage;
}
public Double getPrix() {
	return prix;
}
public void setPrix(Double prix) {
	this.prix = prix;
}
public void inserer(Connection connection) throws SQLException {
	DynamiqueView view =new DynamiqueView();
	query="insert into list_commande("
			+ "`id_commande`,`id_produit`,`id_br`,`quantite`,"
			+ "`longueur`,`largeur`,`incision`,`sablage`,"
			+ "`prix_unitaire`,`br_left`,`br_right`,`br_top`,`br_bottom`,"
			+ "`montant_article`,`montant_br`,`montant_incision`,`montant_sablage`"
			+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    statement=connection.prepareStatement(query);
    statement.setInt(1, this.getId_commande());
    statement.setInt(2, this.getId_produit());
    statement.setInt(3, this.getId_br());
    statement.setDouble(4, this.getQte());
    statement.setDouble(5, this.getLongueur());
    statement.setDouble(6, this.getLargeur());
    statement.setDouble(7, this.getIncision());
    statement.setDouble(8, this.getSablage());
    statement.setDouble(9, this.getPrix());
    statement.setInt(10, this.getBr_left());
    statement.setInt(11, this.getBr_right());
    statement.setInt(12, this.getBr_top());
    statement.setInt(13, this.getBr_bottom());
    statement.setDouble(14, view.changeFormatOfDouble(Double.parseDouble(this.getMontant_article())));
    statement.setDouble(15, view.changeFormatOfDouble(Double.parseDouble(this.getMontant_br())));
    statement.setDouble(16, view.changeFormatOfDouble(Double.parseDouble(this.getMontant_incision())));
    statement.setDouble(17, view.changeFormatOfDouble(Double.parseDouble(this.getMontant_sablage())));
    statement.execute();
}


}
