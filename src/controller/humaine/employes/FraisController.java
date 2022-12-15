package controller.humaine.employes;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import controller.humaine.absences.InsererController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Employe;
import model.Frais;

public class FraisController implements Initializable {
  private DynamiqueView view = new DynamiqueView();
    @FXML
    private ComboBox<Employe> cmbEmployes;
@FXML
    private ComboBox<model.Date> cmbSemaine;

    private ObservableList<model.Date> dataSemaine=FXCollections.observableArrayList();
    @FXML
    private TextField inputFrais;
    private Integer idEmploye=-1,idWeek=-1;
    private String style;
    private Connection connection=DbConnect.getInstance().getConnection();
    private ObservableList<Employe> data = FXCollections.observableArrayList();
    
    @FXML
    void btnValider(MouseEvent event) throws SQLException {
    	style="-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;";
    	String frais=inputFrais.getText();
    	if(frais.isEmpty() ) {
    		inputFrais.setStyle(style);
    		view.alertError(null, "Vueiller Remplire le champ qui sont par couleur rouge");
    		
    	}else if(!view.ValidateDouble(frais)) {
    		inputFrais.setStyle(style);
    		view.alertError(null, "Frais ne doit pas contient un charactere");
    		
    	}
    	else if(idEmploye==-1 || idWeek==-1) {
    		view.alertError(null, "Vueillez Selectionner un Employe ou une semaine");
    	}
    	else {
    		String date=view.getDateOfThisDay();
    		
    		Frais f = new Frais();
    		f.setId_employe(idEmploye);
    		f.setDate_frais(date);
    		f.setCout_frais(Double.parseDouble(frais));
    		f.setSemaine(idWeek);
    		f.insert(connection);
    		style="-fx-border-radius: 23px; -fx-background-radius: 23px;";
    		inputFrais.setStyle(style);
        	inputFrais.setText(null);
    		view.alertInfo(null, "Le frais d'employé a été bien inserer");
    	}
    }
private void getIdOfSemaine(){
		 cmbSemaine.valueProperty().addListener((obs, oldval, newval) -> {
			 if(newval != null) {
				 idWeek=newval.getId_semaine();
			 }
		 });
	 }
    private void getIdOfEmploye(){
    	cmbEmployes.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null) {
		    	idEmploye=newval.getId();
		    }
		    	
		});
    }
    @FXML
    void fermer(MouseEvent event) {
     view.closeStage(event);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        view.getEmployes(cmbEmployes,data,connection);
        view.getSemaine(dataSemaine, cmbSemaine);
        getIdOfEmploye();
        getIdOfSemaine();
	}

}
