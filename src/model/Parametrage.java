package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Parametrage {
private Double heure_supplementaire,retard_commande,remise_client;
public Double getHeure_supplementaire() {
	return heure_supplementaire;
}

public void setHeure_supplementaire(Double heure_supplementaire) {
	this.heure_supplementaire = heure_supplementaire;
}

public Double getRetard_commande() {
	return retard_commande;
}

public void setRetard_commande(Double retard_commande) {
	this.retard_commande = retard_commande;
}

public Double getRemise_client() {
	return remise_client;
}

public void setRemise_client(Double remise_clien) {
	this.remise_client = remise_clien;
}
public void update(Connection conn) throws SQLException {
    
 String query="update parametres set heure_supplementaire=?,retard_commande=?,remise_client=? "
 		+ "where id=1";
 PreparedStatement st=conn.prepareStatement(query);
 st.setDouble(1, this.getHeure_supplementaire());
 st.setDouble(2, this.getRetard_commande());
 st.setDouble(3, this.getRemise_client());
 st.execute();
}

}
