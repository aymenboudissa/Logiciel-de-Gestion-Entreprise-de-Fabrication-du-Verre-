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
import model.Travaille;

public class TravailleController implements Initializable {
    private DynamiqueView view = new DynamiqueView();
    private Connection connection=DbConnect.getInstance().getConnection();
    private ObservableList<Employe> data = FXCollections.observableArrayList();
    private String style;
    @FXML
    private ComboBox<Employe> cmbEmployes;
    private Integer idEmploye=-1,idWeek=-1;
    @FXML
    private ComboBox<model.Date> cmbSemaine;

    private ObservableList<model.Date> dataSemaine=FXCollections.observableArrayList();

    @FXML
    private TextField inputHeure;

    @FXML
    void btnValider(MouseEvent event) {
       String h =inputHeure.getText();
       style="-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;";
    	if(idEmploye==-1) {
    	   view.alertError(null, "Vueillez Selectionner un Employe");
       }else if(h.isEmpty()) {
    	   view.alertError(null, "Vueillez Remplire le champ qui avec la couleur rouge");
    	   inputHeure.setStyle(style);
       }else if(!view.ValidateDouble(h)) {
    	   view.alertError(null, "Le Nbr heure ne doit pas contient un charactere");
    	   inputHeure.setStyle(style);
       }else {
    	   String date=view.getDateOfThisDay();
    	   Travaille t = new Travaille();
    	   t.setId_employe(idEmploye);
    	   t.setDate(date);
    	   t.setNbr_heure(Double.parseDouble(h));
    	   t.setSemaine(idWeek);
    	   try {
			t.insert(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	   inputHeure.setText(null);
    	   view.alertInfo(null, "L'heure du travaille d'employé a été bien inserer");
       	
       }
    }
private void getIdOfSemaine(){
		 cmbSemaine.valueProperty().addListener((obs, oldval, newval) -> {
			 if(newval != null) {
				 idWeek=newval.getId_semaine();
			 }
		 });
	 }
    @FXML
    void fermer(MouseEvent event) {
      view.closeStage(event);
    }
    private void getIdOfEmploye(){
    	cmbEmployes.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null) {
		    	idEmploye=newval.getId();
		    }
		    	
		});
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		view.getEmployes(cmbEmployes, data, connection);
		view.getSemaine(dataSemaine, cmbSemaine);
                getIdOfEmploye();
                getIdOfSemaine();
                
	}

}
