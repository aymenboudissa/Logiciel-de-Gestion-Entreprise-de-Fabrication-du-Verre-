package controller.stock.PieceDeRechange;

import java.sql.Connection;
import java.sql.SQLException;

import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Consommation;
import model.Piece;
public class InsererConsommation {
	private DynamiqueView view= new DynamiqueView();

    @FXML
    private TextField inputQte;
    
    @FXML
    private TextField idPiece;
    
	private Connection connection;
    public void setTextField(Piece p,Connection conn) {
    	idPiece.setText(String.valueOf(p.getId_accessoire()));
    	connection=conn;
    }


    @FXML
    void btnFermer(MouseEvent event) {
    	view.closeStage(event);
    }

    @FXML
    void btnValider(MouseEvent event) {
    	Integer id=Integer.parseInt(idPiece.getText()) ; 
        String versement=inputQte.getText();
        if(versement.isEmpty()) {
     	   view.alertError(null, "Vueillez remplire tous les champs");
        }else if(!view.ValidateDouble(versement)) {
     	   view.alertError(null, "La Qte Consommation ne doit pas contient un charactère ");
     	   inputQte.clear();
        }else {
        	Consommation c =new Consommation();
        	c.setIdPiece(id);
        	c.setDateConsommation(view.getDateOfThisDay());
        	c.setQteConsommation(Double.parseDouble(versement));
        	try {
				c.inserer(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	   view.alertInfo(null, "La Qte Consommation a été bien insérer");
     	   view.closeStage(event);
        }
    }

}
