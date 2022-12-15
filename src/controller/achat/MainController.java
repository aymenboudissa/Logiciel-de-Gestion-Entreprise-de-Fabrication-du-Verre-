package controller.achat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.helpers.DynamiqueView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController implements Initializable  {
    @FXML
    private Button btnTable;
	@FXML
	private AnchorPane anchor;
    private DynamiqueView view=new DynamiqueView();
	private String src="";
	    @FXML
	    private Text txtUser;
    @FXML
    void logOut(MouseEvent event) throws IOException {
      src="login/login";
    view.getPageOfButton(event, src);
    }

    @FXML
    void tableauBord(MouseEvent event) throws IOException {
    	src="achat/rapports";
    	view.Load(anchor, src);
    }
    
    @FXML
    void home(MouseEvent event)  {
    	src="index";
   	 view.getPageOfButton(event, src);
    }
    
    @FXML
    void consulter_matiere(ActionEvent event) {
    	src="achat/matierePremier/consulter";
    	try {
			view.Load(anchor, src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void consulter_piece(ActionEvent event) {
    	src="achat/pieceDeRechange/consulter";
    	try {
			view.Load(anchor, src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    

    @FXML
    void inserer_matiere(ActionEvent event) {
    	src="achat/matierePremier/inserer";
    	try {
			view.Load(anchor, src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void inserer_piece(ActionEvent event) {
    	src="achat/pieceDeRechange/inserer";
    	try {
			view.Load(anchor, src);
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
