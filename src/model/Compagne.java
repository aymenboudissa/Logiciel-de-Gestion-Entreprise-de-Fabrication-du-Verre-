package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.helpers.DbConnect;

public class Compagne {
private String etablisement,adresse,numero,logo;

public String getEtablisement() {
	return etablisement;
}

public void setEtablisement(String etablisement) {
	this.etablisement = etablisement;
}

public String getAdresse() {
	return adresse;
}

public void setAdresse(String adresse) {
	this.adresse = adresse;
}

public String getNumero() {
	return numero;
}

public void setNumero(String numero) {
	this.numero = numero;
}

public String getLogo() {
	return logo;
}

public void setLogo(String logo) {
	this.logo = logo;
}
public void update(Connection conn) throws SQLException {
	
       String query="insert into entreprise(`logo`,`etablissement`,`adresse`,`num_telephone`) "
               + "values(?,?,?,?)";

	PreparedStatement st=conn.prepareStatement(query);
	st.setString(1, this.getLogo());
	st.setString(2, this.getEtablisement());
	st.setString(3, this.getAdresse());
	st.setString(4, this.getNumero());
	st.execute();
	
	
}
}
