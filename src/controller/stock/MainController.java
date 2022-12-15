package controller.stock;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController implements Initializable {
	
	private controller.helpers.DynamiqueView dynamique =new controller.helpers.DynamiqueView();
	
	private String src="";
	@FXML
	private AnchorPane anchor;
	  
    @FXML
    private Button btnTable;
    @FXML
    private Button accueill;
    @FXML
    private Text txtUser;
    @FXML
    void home(MouseEvent event) {
    	src="index";
    	
	 dynamique.getPageOfButton(event, src);
		
    }

    @FXML
    void logOut(MouseEvent event) { 
    	src="login/login";
    	dynamique.getPageOfButton(event,src);

    }

    @FXML
    void matierePremier(MouseEvent event) {
    	src="stock/matierePremier/consulter";
    	try {
			dynamique.Load(anchor,src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void pieceRechange(MouseEvent event) {
    	src="stock/pieceDeRechange/consulter";
    	try {
			dynamique.Load(anchor,src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void tableauBord(MouseEvent event) {
    	src="stock/rapports";
    	try {
			dynamique.Load(anchor, src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		controller.login.MainController m =new controller.login.MainController();
		txtUser.setText((String) m.cache.get(1));
		
		
	}

}
