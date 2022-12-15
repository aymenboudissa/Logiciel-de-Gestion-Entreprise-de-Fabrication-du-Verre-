package controller.fonction;

import java.sql.Connection;
import java.sql.SQLException;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.BisouRectelign;

public class InsererController {
    private DynamiqueView view= new DynamiqueView();
    @FXML
    private TextField inputFonction;
    private Connection connection=DbConnect.getInstance().getConnection();
    @FXML
    private TextField inputPrix;

    @FXML
    void btnFermer(MouseEvent event) {
    	view.closeStage(event);
    }

    @FXML
    void btnInserer(MouseEvent event) throws SQLException {
    	  String fonction=inputFonction.getText();
          String prix=inputPrix.getText();
          if(prix.isEmpty() || fonction.isEmpty()) {
        	  view.ErrorOfEmpty();
          }else if(!view.ValidateDouble(prix)) {
        	  view.ErrorOfDouble(null);
          }else {
        	  BisouRectelign b = new BisouRectelign();
        	  b.setDesignation(fonction);
        	  b.setPrix(prix);
        	  b.insert(connection);
        	  view.alertInfo(null, "Votre Activitie a été bien inserer");
        	  inputFonction.clear();
        	  inputPrix.clear();
          }
    }

}
