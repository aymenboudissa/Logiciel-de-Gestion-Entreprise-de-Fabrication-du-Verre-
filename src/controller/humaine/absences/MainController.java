package controller.humaine.absences;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Absence;

public class MainController implements Initializable {
    private DynamiqueView view =new DynamiqueView();
    @FXML
    private TableColumn<Absence, String> columnButtons;

    @FXML
    private TableColumn<Absence, String> columnDateDebut;
    @FXML
    private TableColumn<Absence, String> columnEmploye;

    @FXML
    private TableColumn<Absence, String> columnHeure;

    @FXML
    private TableColumn<Absence,Integer> columnId;

    @FXML
    private TableColumn<Absence, String> columnJustification;
    @FXML
    private TableColumn<Absence, String> columnJour;
    @FXML
    private TableColumn<Absence, String> columnSemaine;
    @FXML
    private TableColumn<Absence, ImageView> columnTypeAbsence;
  
    @FXML
    private TextField inputRecherche;

    @FXML
    private TableView<Absence> table;
    private String query;
    private PreparedStatement statement;
    private ResultSet rs;
    private Connection connection=DbConnect.getInstance().getConnection();
    private ObservableList<Absence> data = FXCollections.observableArrayList();
    @FXML
    void btnAbsence(MouseEvent event) throws IOException {
      view.LoadPageFree(event, "humaine/absences/inserer");
    }

    @FXML
    void btnActualiser(MouseEvent event) {
    	try {
			actualiser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    private void actualiser() throws SQLException {
    	data.clear();
    	query="select a.*,t.titre,t.image,e.id_employe,CONCAT(e.nom_employe,' ',e.prenom_employe) as employe  from absences a,employes e ,type_absence t "
    			+ "where a.id_type=t.id_type and a.id_employe=e.id_employe";
    	statement=connection.prepareStatement(query);
    	rs=statement.executeQuery();
    	while(rs.next()) {
    		Absence a =new Absence();
    		a.setId(rs.getInt("id_absence"));
    		a.setId_type(rs.getInt("id_type"));
    		a.setEmploye(rs.getString("employe"));
    		a.setJustification(rs.getString("justifier"));
    		a.setDateDebut(rs.getString("date_debut"));
    		a.setHeure(rs.getDouble("heure")+"h");
    		a.setSemaine("0"+rs.getInt("semaine")+" semaine");
    		a.setJours(rs.getInt("jour")+" Jours");
    		String photo=rs.getString("image");
    		Image set=new Image(getClass().getResourceAsStream(photo));
    		ImageView img=new ImageView(set);
    		img.setFitHeight(30);
    		img.setFitWidth(25);
        	a.setTypeAbsence(img);
        	data.add(a);
    	}
    }
    private void load() throws SQLException {
    	actualiser();
    	LoadLignIntoColumns();
    	InsertButtons();
    	
    }
    private void LoadLignIntoColumns() {
    	columnId.setCellValueFactory(new PropertyValueFactory<Absence,Integer>("id"));
    	columnEmploye.setCellValueFactory(new PropertyValueFactory<Absence,String>("employe"));
    	columnTypeAbsence.setCellValueFactory(new PropertyValueFactory<Absence,ImageView>("typeAbsence"));
    	columnJustification.setCellValueFactory(new PropertyValueFactory<Absence,String>("justification"));
    	columnHeure.setCellValueFactory(new PropertyValueFactory<Absence,String>("heure"));
    	columnDateDebut.setCellValueFactory(new PropertyValueFactory<Absence,String>("dateDebut"));
    	columnJour.setCellValueFactory(new PropertyValueFactory<Absence,String>("jours"));
    	columnSemaine.setCellValueFactory(new PropertyValueFactory<Absence,String>("semaine"));
    }
	private void InsertButtons() {
		 Callback<TableColumn<Absence, String>, TableCell<Absence, String>> cellFoctory = (TableColumn<Absence, String> param) -> {
	            // make cell containing buttons
	            final TableCell<Absence, String> cell = new TableCell<Absence, String>() {
               @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   //that cell created only on non-empty rows
                   if (empty) {
                       setGraphic(null);
                       setText(null);
                   } 
                       Button deleteIcon = new Button();
                       Button justificationIcon = new Button();
                      Hashtable<Button, String> values= new Hashtable<>();
                      values.put(deleteIcon, "delete");
                      values.put(justificationIcon, "modifier");
                      view.SetGraphiqueIntoButton(values);
                          Delete(deleteIcon);
                          Justifier(justificationIcon);
                                                
                       HBox managebtn = new HBox(justificationIcon,deleteIcon);
                       view.viewOfHboxForButton(managebtn, values);
                       setGraphic(managebtn);
                       setText(null);    
               }
           };
          
           return cell;
       };
	    	 columnButtons.setCellFactory(cellFoctory);
	    	  table.setItems(data);
	    	  FilterByDesignation();
	}
	private void FilterByDesignation() {
		 FilteredList<Absence> filteredData= new FilteredList<>(data,  b ->true);
		 inputRecherche.textProperty().addListener((observable,oldValue,newValue)->{
	    		 filteredData.setPredicate(productSearchModel ->{
	    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
	    				return true;
	    			}
	    			String searchKeyword = newValue.toLowerCase();
	    			if (productSearchModel.getEmploye().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getJustification().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getHeure().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getDateDebut().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getJours().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getSemaine().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			
	    			

	    			else {
	    				return false;
	    			}
	    			
	    		 });
	    	 });
	    	 SortedList<Absence> sortedData = new SortedList<>(filteredData);
	    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
	    	 table.setItems(sortedData);

  }
   private void Delete(Button delete) {
   	delete.setOnMouseClicked((MouseEvent event) -> {
           
           try {
        	   Absence p = table.getSelectionModel().getSelectedItem();
               if(p == null) {
            	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
               }else {
            	   
               
               query = "DELETE FROM `absences` WHERE id_absence  ="+p.getId();
               Connection connection=DbConnect.getInstance().getConnection();
               statement = connection.prepareStatement(query);
               statement.execute();
               actualiser();
               }
               
           } catch (SQLException ex) {
               Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
   }
   private void getStylishOfColumn() {
   	String style="-fx-font-size:14px;-fx-alignment:center";
   	columnId.setStyle(style);
   	columnEmploye.setStyle(style);
   	columnTypeAbsence.setStyle(style);
   	columnJustification.setStyle(style);
   	columnHeure.setStyle(style);
   	columnDateDebut.setStyle(style);
   	columnJour.setStyle(style);
   	columnSemaine.setStyle(style);
   	columnButtons.setStyle(style);
   }
   private void Justifier(Button edite) {
   	edite.setOnMouseClicked((MouseEvent event) -> {
           
       	Absence p = table.getSelectionModel().getSelectedItem();
           
           if(p== null) {
            DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");	
        	  
           }else {
        	   
           FXMLLoader loader = new FXMLLoader ();
           loader.setLocation(getClass().getResource("/view/humaine/absences/justification.fxml"));
           try {
               loader.load();
           } catch (IOException ex) {
               Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
           }
          InsererJustification add = loader.getController();
				       add.setTextField(p.getId()); 
					  Parent parent = loader.getRoot();
				      DynamiqueView.getInstance().loadPageFromtable(parent);
       }});
   }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getStylishOfColumn();
	}

}
