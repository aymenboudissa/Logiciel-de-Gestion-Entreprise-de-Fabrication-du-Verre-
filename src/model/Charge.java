package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.helpers.DbConnect;

public class Charge {
private Integer id_charge;
private String charge,cout_charge,date_charge;
public Integer getId_charge() {
	return id_charge;
}
public void setId_charge(Integer id_charge) {
	this.id_charge = id_charge;
}
public String getCharge() {
	return charge;
}
public void setCharge(String charge) {
	this.charge = charge;
}
public String getCout_charge() {
	return cout_charge;
}
public void setCout_charge(String cout_charge) {
	this.cout_charge = cout_charge;
}
public String getDate_charge() {
	return date_charge;
}
public void setDate_charge(String date_charge) {
	this.date_charge = date_charge;
}
public void inserer() throws SQLException {
	Connection connection=DbConnect.getInstance().getConnection();
	String query="insert into charges(`charge`,`cout_charge`,`date_charge`) "
			+ "values(?,?,?)";
   PreparedStatement st=connection.prepareStatement(query);
   st.setString(1, this.getCharge());
   st.setDouble(2, Double.parseDouble(this.getCout_charge()));
   st.setString(3, this.getDate_charge());
   st.execute();

}  

}
