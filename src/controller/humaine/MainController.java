package controller.humaine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController implements Initializable  {
	
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
    	src="humaine/rapport";
    	view.Load(anchor, src);
    }
    
    @FXML
    void home(MouseEvent event)  {
    	src="index";
   	 view.getPageOfButton(event, src);
    }
    
    @FXML
    void users(MouseEvent event)  {
    	src="humaine/utilisateurs/consulter";
    	try {
			view.Load(anchor, src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void employes(MouseEvent event)  {
    	src="humaine/employes/consulter";
    	try {
			view.Load(anchor, src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void absences(MouseEvent event)  {
    	src="humaine/absences/consulter";
    	try {
			view.Load(anchor, src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void justifications(MouseEvent event)  {
    	src="humaine/justifications/consulter";
    	try {
			view.Load(anchor, src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void paiement(MouseEvent event)  {
    	src="humaine/paiement/paiement";
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
