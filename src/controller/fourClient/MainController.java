package controller.fourClient;

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
    	src="fourClient/rapports";
    	view.Load(anchor, src);
    }
    
    @FXML
    void home(MouseEvent event)  {
    	src="index";
   	 view.getPageOfButton(event, src);
    }
    @FXML
    void clientFourni(MouseEvent event) throws IOException {
    	src="fourClient/consulter";
    	view.Load(anchor, src);
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 controller.login.MainController m =new controller.login.MainController();
			txtUser.setText((String) m.cache.get(1));
	}

}
