package controller.humaine.employes;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Employe;

public class InsererController implements Initializable {
   private DynamiqueView view=new DynamiqueView();
    @FXML
    private DatePicker DateNaissance;

    @FXML
    private DatePicker DateRecrutement;

    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputPrenom;
    @FXML
    private TextField inputAdresse;
private Connection connection =DbConnect.getInstance().getConnection();
    @FXML
    private TextField inputRevenuHebdomodaire;
    private Integer ValueEmpty=0;
    
    @FXML
    void btnValider(MouseEvent event) {
    	 
        	ArrayList<TextField> values =getValues();
        	values.forEach((field)->{
        		if(field.getText()==null ||field.getText().isEmpty()) {
        			ValueEmpty++;
        		}
        	});
        	if(ValueEmpty !=0) {
        		view.ErrorOfEmpty();
        		ValueEmpty=0;	
        	
        	}else{
        		ArrayList<DatePicker> valuesDate =getValuesOfDate();
        		valuesDate.forEach((field)->{
            		if(field.getValue()==null) {
            			ValueEmpty++;
            		}
            	});
        		if(ValueEmpty !=0) {

            		view.ErrorOfEmpty();
            		ValueEmpty=0;	
        		}
        		else if(!view.ValidateDouble(values.get(3).getText())) {
        			view.alertError("Page 01", "Les valeurs ne doit pas contient un charactere ");	
        		
        		}
        		else {
        			Employe e = new Employe();
        			e.setNom(values.get(0).getText());
        			e.setPrenom(values.get(1).getText());
        			e.setAdresse(values.get(2).getText());
        			e.setRevenuHebdomodaire(values.get(3).getText());
        			e.setDateNaissance(valuesDate.get(0).getValue().toString());
        			e.setDateRecrutement(valuesDate.get(1).getValue().toString());
        			try {
						e.inserer(connection);
						view.alertInfo(null, "L'Employé a été bien inserer");
						clear();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
        		}
        	}
    }
    private void clear() {
    	ArrayList<TextField> values =getValues();
    	ArrayList<DatePicker> valuesDate =getValuesOfDate();
    	values.forEach((field)->{
    		field.clear();
    	});
    	valuesDate.forEach((field)->{
    		field.setValue(null);
    	});
        
    }
    private ArrayList<TextField> getValues() {
    	ArrayList<TextField> values =new ArrayList<>();
            values.add(inputNom);
            values.add(inputPrenom);
            values.add(inputAdresse);
            values.add(inputRevenuHebdomodaire);
            return values;
    }
    private ArrayList<DatePicker> getValuesOfDate() {
    	ArrayList<DatePicker> values =new ArrayList<>();
    	values.add(DateNaissance);
    	values.add(DateRecrutement);
    	return values;
    }
    @FXML
    void fermer(MouseEvent event) {
       view.closeStage(event);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
