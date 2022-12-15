package controller.humaine.absences;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Justification;

public class InsererJustification implements Initializable {
	private DynamiqueView view = new DynamiqueView();
    @FXML
    private TextArea inpuDescription;
    private Connection connection=DbConnect.getInstance().getConnection();
    @FXML
    private TextField inputID;
    @FXML
    void btnFermer(MouseEvent event) {
    view.closeStage(event);
    }
    @FXML
	void btnValider(MouseEvent event) throws SQLException {
        Integer id =Integer.parseInt(inputID.getText());
        	String description=inpuDescription.getText().isEmpty() ? "" :  inpuDescription.getText();
        	if(AbsenceExist(id, connection)) {
        		view.alertInfo(null, "L'absence déja justifier");
        	}else {
        		
        		Justification j =new Justification();
        		j.setId_absence(id);
        		j.setDescription(description);
        		j.setDateJustification(view.getDateOfThisDay());
        		j.inserer(connection);
        		view.alertInfo(null, "Justification d'employé a été bien inserer");
        		inputID.clear();
        		inpuDescription.clear();
        	}
			
        connection.close();
        view.closeStage(event);
         
    }
    private boolean AbsenceExist(Integer id,Connection conn) throws SQLException {
    	String query="select count(*) as nb from justifications where id_absence="+id+" ";
    	PreparedStatement statement =conn.prepareStatement(query);
     ResultSet rs=statement.executeQuery();
     if(rs.next()){
     if(rs.getInt("nb") !=0) {
    	 return true;
     }else {
    	 return false;
     }    
     }else{
         return false;
     }
     
     
    }
    public void setTextField(Integer id) {
    	inputID.setText(String.valueOf(id));
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
