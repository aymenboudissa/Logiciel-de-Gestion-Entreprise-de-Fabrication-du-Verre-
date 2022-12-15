package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.helpers.DbConnect;

public class Utilisateur {
private Integer id;
private String user;
private String password;
private Integer id_role;
private String role;
private String query;
private PreparedStatement statement;
private Connection conn=DbConnect.getInstance().getConnection();
public Integer getId_role() {
	return id_role;
}
public void setId_role(Integer id_role) {
	this.id_role = id_role;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

private boolean changePassword=false;

public boolean isChangePassword() {
	return changePassword;
}

private String newPassword;
public String getNewPassword() {
	return newPassword;
}
public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
	this.changePassword=true;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
	
}
public void inserer() throws SQLException {
	query="insert into utilisateurs(`user`,`password`,`role`)"
			+ "values(?,?,?)";
	statement=conn.prepareStatement(query);
	statement.setString(1, this.getUser());
	statement.setString(2, this.getPassword());
	statement.setInt(3, this.getId_role());
    statement.execute();	
}
public void update() throws SQLException {
	query="update utilisateurs set user=? , password=?,role=? "
			+ "where id_utilisateur=?";
	statement=conn.prepareStatement(query);
	statement.setString(1, this.getUser());
	statement.setString(2, this.getPassword());
	statement.setInt(3, this.getId_role());
	statement.setInt(4, this.getId());
	statement.execute();	
}

}
