package controller.humaine.paiement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import controller.stock.MatierePremier.MatiereController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.Employe;

public class ConsulterController implements Initializable  {
    private DynamiqueView view= new DynamiqueView();
  
    @FXML
    private ComboBox<Employe> cmbEmploye;

    @FXML
    private ComboBox<model.Date> cmbMois;
    private Connection connection =DbConnect.getInstance().getConnection();
    private ObservableList<Employe> dataEmployes=FXCollections.observableArrayList();
    private ObservableList<model.Date> dataSemaine=FXCollections.observableArrayList();
    private ObservableList<model.Date> dataMois=FXCollections.observableArrayList();
    
    @FXML
    private ComboBox<model.Date> cmbSemaine;
	private Integer idEmploye=-1,id_month=-1,id_week=-1;
      
    public static ConsulterController getInstance() {
    	return new ConsulterController();
    }
    @FXML
    void btnConsulter(MouseEvent event) throws SQLException {
    	if(idEmploye==-1 || id_month==-1 ){   
			view.alertError(null, "Veuiller Remplire Toutes Les champes");
	}else {
		 FXMLLoader loader = new FXMLLoader ();
         loader.setLocation(getClass().getResource("/view/humaine/paiement/consulter.fxml"));
         try {
             loader.load();
         } catch (IOException ex) {
             Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
         }

         			  OrdreController add = loader.getController();
				      add.setText(idEmploye,getThisYear(),id_month,id_week); 
					  Parent parent = loader.getRoot();
				      DynamiqueView.getInstance().loadPageFromtable(parent);
	}   
    
    }
    @SuppressWarnings("unused")
	private int getYear() {
    	return getThisYear();
    }
    private Hashtable<Integer, String> getValuesOfMois(){
    	Hashtable<Integer, String> values=new Hashtable<>();
    	values.put(1,"Jan");
    	values.put(2,"Fev");
    	values.put(3,"Mars");
    	values.put(4,"Avr");
    	values.put(5,"Mai");
    	values.put(6,"Juin");
    	values.put(7,"Juil");
    	values.put(8,"Out");
    	values.put(9,"Sept");
    	values.put(10,"Oct");
    	values.put(11,"Nov");
    	values.put(12,"Dec");
    	
    	return values;
    }
    public Hashtable<Integer, String> getValuesOfSemaine(){
    	Hashtable<Integer, String> values=new Hashtable<>();
    	values.put(1, "Semaine 01");
    	values.put(2, "Semaine 02");
    	values.put(3, "Semaine 03");
    	values.put(4, "Semaine 04");
    	return values;
    }
    @SuppressWarnings("deprecation")
	public int getThisYear() {
    	java.util.Date date=new java.util.Date();
    	return date.getYear()+1900;
   }

    public void getMois() {
    	Hashtable<Integer, String> values=getValuesOfMois();
		    values.forEach((k,v)->{
		    	model.Date d = new model.Date();
		    	d.setId_mois(k);
		    	d.setMois(v);
		    	dataMois.add(d);
		    });
    		cmbMois.setItems(dataMois);
    		cmbMois.setConverter(new StringConverter<model.Date>() {
			    public String toString(model.Date object) {
			        return object.getMois();
			    }
			    @Override
			    public model.Date fromString(String string) {
			        return cmbMois.getItems().stream().filter(ap -> 
			            ap.getMois().equals(string)).findFirst().orElse(null);
			    }
			});
    }
    private void getSemaine() {
    	Hashtable<Integer, String> values=getValuesOfSemaine();
	    values.forEach((k,v)->{
	    	model.Date d = new model.Date();
	    	d.setId_semaine(k);
	    	d.setSemaine(v);
	    	dataSemaine.add(d);
	    });
    		cmbSemaine.setItems(dataSemaine);
    		cmbSemaine.setConverter(new StringConverter<model.Date>() {
			    public String toString(model.Date object) {
			        return object.getSemaine();
			    }
			    @Override
			    public model.Date fromString(String string) {
			        return cmbSemaine.getItems().stream().filter(ap -> 
			            ap.getSemaine().equals(string)).findFirst().orElse(null);
			    }
			});
    		
        }
	 
	 private void getIdOfEmploye(){
	    	cmbEmploye.valueProperty().addListener((obs, oldval, newval) -> {
			    if(newval != null) {
			    	idEmploye=newval.getId();
			    }
			    	
			});
	    }
	 
	 private void getIdOfMois(){
		 cmbMois.valueProperty().addListener((obs, oldval, newval) -> {
			 if(newval != null) {
				 id_month=newval.getId_mois();
			 }
		 });
	 }
	 private void getIdOfSemaine(){
		 cmbSemaine.valueProperty().addListener((obs, oldval, newval) -> {
			 if(newval != null) {
				 id_week=newval.getId_semaine();
			 }
		 });
	 }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getIdOfEmploye();
		getIdOfMois();
		getIdOfSemaine();
		view.getEmployes(cmbEmploye, dataEmployes, connection);
		getSemaine();
		getMois();
	}

}
