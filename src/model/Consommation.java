package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Consommation {
private Integer idPiece;
private String dateConsommation;
private Double qteConsommation;
public Integer getIdPiece() {
	return idPiece;
}
public void setIdPiece(Integer idPiece) {
	this.idPiece = idPiece;
}
public String getDateConsommation() {
	return dateConsommation;
}
public void setDateConsommation(String dateConsommation) {
	this.dateConsommation = dateConsommation;
}
public Double getQteConsommation() {
	return qteConsommation;
}
public void setQteConsommation(Double qteConsommation) {
	this.qteConsommation = qteConsommation;
}
public void inserer(Connection connection) throws SQLException {
	String query="insert into consommation(`id_accessoire`,`qte_consommation`,`date_consommation`) "
			+ "values(?,?,?)";
	PreparedStatement st=connection.prepareStatement(query);
	st.setInt(1,this.getIdPiece());
	st.setDouble(2, this.getQteConsommation());
	st.setString(3, this.getDateConsommation());
	st.execute();
}

}
