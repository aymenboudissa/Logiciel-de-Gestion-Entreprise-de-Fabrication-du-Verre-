package controller.parametre;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import model.Parametrage;
import model.SimpleCacheManager;

public class MainController implements Initializable {
     
	private String src="";
	private DynamiqueView view=new DynamiqueView();
	    @FXML
	    private TextField inputAbsenceSupplemetaire;
	    @FXML
	    private HBox hbox;

	    @FXML
	    private TextField inputLimiteAbsence;
//		compagne
	    @FXML
	    private TextField inputAdresse;

	    
	    @FXML 
	    private AnchorPane anchorpane;
	    private Connection connection=DbConnect.getInstance().getConnection();
	    @FXML
	    private TextField inputRemiseClient;

	    int empty=0,empty1=0;
	    @FXML
	    void btnValider1(MouseEvent event) throws SQLException {
	    	if(connection.isClosed()) {
	    		connection=DbConnect.getInstance().getConnection();
	    	}
	    	ArrayList<TextField> values=getParametre();
	    	values.forEach((v)->{
	    		if(v.getText().isEmpty() ||v.getText()==null ) {
	    			empty1++;
	    		}
	    	});
	    	if(empty1!=0) {
	    		view.ErrorOfEmpty();
	    	}else {
	    		Parametrage p =new Parametrage();
	    		p.setHeure_supplementaire(Double.parseDouble(values.get(0).getText()));
	    		p.setRetard_commande(Double.parseDouble(values.get(1).getText()));
	    		p.setRemise_client(Double.parseDouble(values.get(2).getText()));
	    		p.update(connection);
	    		view.alertInfo(null, "Votre Parametre a été bien enregistrer");
	    	}
	    	connection.close();
	    }
	    private ArrayList<TextField> getParametre() {
	    	ArrayList<TextField> values=new ArrayList<>();
	    	values.add(inputAbsenceSupplemetaire);
	    	values.add(inputLimiteAbsence);
	    	values.add(inputRemiseClient);
	    	return values;
	    }

    @FXML
    void home(MouseEvent event) {
      src="index";
      view.getPageOfButton(event, src);
      
    }

    @FXML
    void logOut(MouseEvent event) {
    	src="login/login";
        view.getPageOfButton(event, src);
    }

	private void loadParametre() throws SQLException {
		String query="select * from parametres ";
		PreparedStatement st=connection.prepareStatement(query);
		ResultSet rs=st.executeQuery();
		if(rs.next()) {
			String heureSupp=String.valueOf(rs.getDouble("heure_supplementaire"));
			String retard=String.valueOf(rs.getDouble("retard_commande"));
			String remise=String.valueOf(rs.getDouble("remise_client"));
			inputAbsenceSupplemetaire.setText(heureSupp);
			inputLimiteAbsence.setText(retard);
			inputRemiseClient.setText(remise);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			
			loadParametre();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
