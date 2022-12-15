package controller.humaine.absences;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import controller.humaine.paiement.ConsulterController;
import java.util.Hashtable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.Absence;
import model.Employe;

public class InsererController implements Initializable {
     private DynamiqueView view = new DynamiqueView();
     private Connection connection=DbConnect.getInstance().getConnection();
     private ObservableList<Employe> dataEmploye=FXCollections.observableArrayList();
     private ObservableList<Absence> dataAbsence=FXCollections.observableArrayList();
     private Integer idEmploye=-1,idAbsence=-1;
    @FXML
    private ComboBox<Employe> cmbEmploye;

    @FXML
    private ComboBox<Absence> combTypeAbsence;
 @FXML
    private ComboBox<model.Date> cmbSemaine;

    @FXML
    private TextField inputJour;
    @FXML
    private TextField inputHeure;
    private String style;
    private String sql ;
    private Integer id_week=-1;
	private PreparedStatement status;
	private ResultSet rs;
       
    private ObservableList<model.Date> dataSemaine=FXCollections.observableArrayList();
    
	 
         private void getIdOfSemaine(){
		 cmbSemaine.valueProperty().addListener((obs, oldval, newval) -> {
			 if(newval != null) {
				 id_week=newval.getId_semaine();
			 }
		 });
	 }
	 
    @FXML
    void btnValider(MouseEvent event) throws SQLException, ParseException {
		 String debut=view.getDateOfThisDay();
//		 int idsemaine=getIdOfSemaine();
		 
      style="-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;";
      String heure=inputHeure.getText().isEmpty() ? "" :inputHeure.getText() ;
      String jour=inputJour.getText().isEmpty() ? "" :inputJour.getText() ;
      
      if( jour.isEmpty() && idAbsence!=4 ) {
    	  view.ErrorOfEmpty();
    	  inputJour.setStyle(style);
      	  
      }else if(idEmploye==-1|| idAbsence==-1 || id_week==-1) {
    	  view.alertError(null, "Vueiller selectionner un employer ou type d'absence ou bien une semaine ");
    	  
      }else if(!view.ValidateInteger(jour) && idAbsence!=4 ) {
    	  view.ErrorOfDouble(null);
	     inputJour.setStyle(style);
      }
      
      else {
    	  if(heure.isEmpty() && idAbsence==4) {
        	  view.alertError(null, "Vueiller remplire l'heure en type d'absence est en retard");
        	  inputHeure.setStyle(style);
    	  }else if(!heure.isEmpty() && !view.ValidateDouble(heure)) {
        	  view.ErrorOfDouble(null);
        	  inputHeure.setStyle(style);
    	  }else {
    		  if(AbsenceExist(idEmploye, debut,connection)) {
    			  view.alertInfo(null, "Vous pouvez pas inserer deux absence d'un employe dans une seule date");
    		  }else {
    			  
    			  Absence a =new Absence();
    			  a.setId_employe(idEmploye);
    			  a.setId_type(idAbsence);
    			  a.setDateDebut(debut);
    			  a.setHeure(heure);
    			  a.setSemaine(String.valueOf(id_week));
    			  a.setJours(jour);
    			  try {
    				  a.inserer(connection);
    			  } catch (SQLException e) {
    				  e.printStackTrace();
    			  }
    			  view.alertInfo(null, "L'absence a été bien inserer");
    		          view.closeStage(event);
    		  }
    	  }
    		  }
    }
    @FXML
    void fermer(MouseEvent event) {
   view.closeStage(event);
    }
    private void getIdOfEmploye(){
    	cmbEmploye.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null) {
		    	idEmploye=newval.getId();
		    }
		});
    }
    private void getIdOfType(){
    	combTypeAbsence.valueProperty().addListener((obs, oldval, newval) -> {
    		if(newval != null) {
    			idAbsence=newval.getId_type();
    			DisabledHeure();
    		}
    		
    	});
    }
    private void DisabledHeure() {
    	if(idAbsence==4) {
    		inputHeure.setDisable(false);
    		inputJour.setDisable(true);
    	}else {
    		inputJour.setDisable(false);
    		inputHeure.setDisable(true);
    	}
    }
    
    private void getTypes() {
    	try {
 	    	
 		    sql = "select id_type,titre from type_absence";
 		    status= connection.prepareStatement(sql);
 		    rs=status.executeQuery();
 		    while(rs.next()) {
 		    	Absence a = new Absence();
 		    	a.setId_type(rs.getInt("id_type"));
 		    	a.setTitre_absence(rs.getString("titre"));
 		    	dataAbsence.add(a);
 		    }
 		   
 		   combTypeAbsence.setItems(dataAbsence);
 		  combTypeAbsence.setConverter(new StringConverter<Absence>() {

 			    public String toString(Absence object) {
 			        return object.getTitre_absence();
 			    }

 			    @SuppressWarnings("unlikely-arg-type")
				@Override
 			    public Absence fromString(String value) {
 			        return combTypeAbsence.getItems().stream().filter(ap -> 
 			            ap.getTypeAbsence().equals(value)).findFirst().orElse(null);
 			    }
 			});
 	    }catch(Exception ex) {
 	    	
 	    }
    	
		
    }
	private boolean AbsenceExist(Integer id,String date,Connection conn) throws SQLException {
		String query="select count(*) as nb from absences where id_employe="+id+" and date_debut='"+date+"'";
	 PreparedStatement status =conn.prepareStatement(query);
	 ResultSet rs= status.executeQuery();
	 if(rs.next()){
         if(rs.getInt("nb") !=0) {
		 return true;
	 }else {
		 return false;
	 }    
         }else{
             return false;
         }
         
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inputHeure.setDisable(true);
		view.getEmployes(cmbEmploye, dataEmploye, connection);	
      getTypes();
      view.getSemaine(dataSemaine, cmbSemaine);
      getIdOfEmploye();
      getIdOfSemaine();
      getIdOfType();
      
      
	}

}
