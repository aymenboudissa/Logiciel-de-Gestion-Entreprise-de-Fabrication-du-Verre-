package controller.stock.PieceDeRechange;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Piece;

public class ModifierController implements Initializable {
    private DynamiqueView view= new DynamiqueView();
    @FXML
    private TextField inputDesignation;
    @FXML
    private TextField inputID;
    @FXML
    private TextField inputStockCretique;

    @FXML
    private TextField inputStockInitiale;
    private Connection connection;
    @FXML
    void btnFermer(MouseEvent event) {
     view.closeStage(event);
    }

    @FXML
    void btnValider(MouseEvent event) {
    	 Piece premier = new Piece(); 
       	 String designation = inputDesignation.getText();
         String cretique= inputStockCretique.getText();
         String initiale= inputStockInitiale.getText();
         if(designation.isEmpty() || cretique.isEmpty() || initiale.isEmpty()) {
        	view.alertError("Impossible Modifier", "Les champs ne doit pas être vide");
         }
         else if(view.ValidateDouble(initiale) &&  view.ValidateDouble(cretique)) {
        	 premier.setId_accessoire(Integer.parseInt(inputID.getText()));
        	 premier.setDesignation(designation);
        	 premier.setCretique(Double.parseDouble(cretique));
        	 premier.setInitiale(Double.parseDouble(initiale));
        	  updatePiece(premier);
        	  view.closeStage(event);
         }else {
        	 view.alertError("Impossible Inserer", "Stock initiale et cretique doit être nombre sans virgule ou avec charactère");
         }
        }
        private void updatePiece(Piece p) {
        	try {
    			p.update(connection);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        	view.alertInfo(null, "Votre Accessoires a été bien modifier");
        	
        }
       
     public void setTextField(Piece p,Connection conn) {
    	 connection=conn; 
       inputID.setText(String.valueOf(p.getId_accessoire()) ); 
       inputDesignation.setText(p.getDesignation());
       inputStockCretique.setText(String.valueOf(p.getCretique()));
       inputStockInitiale.setText(String.valueOf(p.getInitiale()));
     }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
