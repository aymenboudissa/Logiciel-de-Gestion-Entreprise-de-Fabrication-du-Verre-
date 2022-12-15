package controller.vente;

import java.sql.Connection;
import java.sql.SQLException;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Versement;

public class Verser {

	 @FXML
	 private TextField numCmd;

    private DynamiqueView view = new DynamiqueView(); 
    private Connection connection=DbConnect.getInstance().getConnection();
    @FXML
    private TextField inputVersement;
    public void setTextFieled(Integer id,Connection conn) {
    	numCmd.setText(String.valueOf(id));
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void clear() {
    	inputVersement.setText("");
    }
    @FXML
    void btnValider(MouseEvent event) throws SQLException {
       Integer id=Integer.parseInt(numCmd.getText()) ; 
       String versement=inputVersement.getText();
       if(versement.isEmpty()) {
    	   view.alertError(null, "Vueillez remplire tous les champs");
       }else if(!view.ValidateDouble(versement)) {
    	   view.alertError(null, "Le versement ne doit pas contient un charactère ");
    	   clear();
       }else {
    	   
    	   Versement v= new Versement();
    	   v.setId(id);
    	   v.setDate_versement(view.getDateOfThisDay());
    	   v.setVersement(versement);
    	   v.insererCmd(connection);
    	   view.alertInfo(null, "Le versement a été bien insérer");
    	   view.closeStage(event);
       }
    }
    @FXML
    void btnFermer(MouseEvent event) {
      view.closeStage(event);
    }

}
