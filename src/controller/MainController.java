package controller;
import java.net.URL;
import java.util.ResourceBundle;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class MainController implements Initializable {
private String ressource="";

private DynamiqueView dynamique=new DynamiqueView();
	    @FXML
	    void achat(MouseEvent event) {
	    	ressource="achat/achat";

	    	dynamique.getPageOfButton(event,ressource);
	    }

	    @FXML
	    void fonction(MouseEvent event) {
	    	ressource="fonction/BisRect/consulter";
	    	
	    	dynamique.getPageOfButton(event,ressource);
	    }

	    @FXML
	    void fourClient(MouseEvent event) {
	    	ressource="fourClient/fourClient";
	    	dynamique.getPageOfButton(event,ressource);	    
	    	}
	    
	    @FXML
	    void logOut(MouseEvent event) {
	    	ressource="login/login";
	    	dynamique.getPageOfButton(event,ressource);
	    	
	    }

	    @FXML
	    void parametre(MouseEvent event) {
	    	ressource="parametre/entreprise";
	    	dynamique.getPageOfButton(event,ressource);	    	
	    }

	    @FXML
	    void roussHumaine(MouseEvent event) {
	    	ressource="humaine/humaine";
	    	dynamique.getPageOfButton(event,ressource);	  
	    }

	    @FXML
	    void stock(MouseEvent event) {
	    	ressource="stock/stock";
	    	dynamique.getPageOfButton(event,ressource);	  
	    
	    }

	    @FXML
	    void vente(MouseEvent event) {
	    	ressource="vente/vente";
	    	dynamique.getPageOfButton(event,ressource);	  
	    }

	    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
