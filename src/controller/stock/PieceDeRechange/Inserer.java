package controller.stock.PieceDeRechange;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Piece;

public class Inserer implements Initializable{
   private DynamiqueView view = new DynamiqueView();
    @FXML
    private TextField inputDesignation;

    @FXML
    private TextField inputStockCretique;

    @FXML
    private TextField inputStockInitiale;
    private Connection conn=DbConnect.getInstance().getConnection();
    @FXML
    void fermer(MouseEvent event) {
    view.closeStage(event); 
	
    }

    @FXML
    void btnValider(MouseEvent event) {
     Piece premier = new Piece(); 
   	 String designation = inputDesignation.getText();
     String cretique= inputStockCretique.getText();
     String initiale= inputStockInitiale.getText();
     if(designation.isEmpty() || cretique.isEmpty() || initiale.isEmpty()) {
    	view.alertError("Impossible Inserer", "Les champs ne doit pas être vide");
     }
     else if(view.ValidateDouble(initiale) &&  view.ValidateDouble(cretique)) {
    	 premier.setDesignation(designation);
    	 premier.setCretique(Double.parseDouble(cretique));
    	 premier.setInitiale(Double.parseDouble(initiale));
    	  insererPiece(premier);
    	  view.closeStage(event);
     }else {
    	 view.alertError("Impossible Inserer", "Stock initiale et cretique doit être nombre sans virgule ou avec charactère");
     }
    }
    private void insererPiece(Piece p) {
    	try {
			p.inserer(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	view.alertInfo(null, "Votre Accessoires a été bien d'insérer");
    	
    }
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
      		
	}

}
