package controller.humaine.employes;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Employe;
public class ConsulterController implements Initializable {
    private DynamiqueView view = new DynamiqueView();
    private Connection conn=DbConnect.getInstance().getConnection();
    private PreparedStatement statement;
    private ResultSet rs;
    private String query;
    private final String src="humaine/employes/";
    @FXML
    private TableColumn<Employe, String> columnButtons;

    @FXML
    private TableColumn<Employe, String> columnDateNaissance;

    @FXML
    private TableColumn<Employe, String> columnDateRecrutement;

    @FXML
    private TableColumn<Employe, String> columnNom;

    @FXML
    private TableColumn<Employe, String> columnPrenom;

    @FXML
    private TableColumn<Employe, String> columnRevenuHebdomodaire;

    @FXML
    private TextField inputRecherche;

    @FXML
    private TableView<Employe> table;
    private ObservableList<Employe> data=FXCollections.observableArrayList();

    @FXML
    void btnInsererActualiser(MouseEvent event) throws SQLException {
    	actualiser();
    }

    @FXML
    void btnInsererEmploye(MouseEvent event) {
        try {
			view.LoadPageFree(event, src+"inserer");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void btnInsererFrais(MouseEvent event) {
        try {
			view.LoadPageFree(event, src+"frais");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void btnInsererTravaille(MouseEvent event) {
    	try {
			view.LoadPageFree(event, src+"travaille");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void actualiser() throws SQLException {
    	data.clear();
    	query="select * from employes";
    	statement=conn.prepareStatement(query);
    	rs=statement.executeQuery();
    	while(rs.next()) {
    		Employe e=new Employe();
    		e.setId(rs.getInt(1));
    		e.setNom(rs.getString(2));
    		e.setPrenom(rs.getString(3));
    		e.setDateNaissance(rs.getString(4));
    		e.setDateRecrutement(rs.getString(5));
    		e.setAdresse(rs.getString(6));
    		e.setRevenuHebdomodaire(rs.getDouble(7)+"DA");
    		data.add(e);
    	}
    }
		private void load() throws SQLException {
			actualiser();
			LoadLignOfColumns();		
			InsertButtons();
	
		}
		private void LoadLignOfColumns() {
			columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			columnRevenuHebdomodaire.setCellValueFactory(new PropertyValueFactory<>("RevenuHebdomodaire"));
			columnDateRecrutement.setCellValueFactory(new PropertyValueFactory<>("DateRecrutement"));
			columnDateNaissance.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));
		}
		private void InsertButtons() {
			 Callback<TableColumn<Employe, String>, TableCell<Employe, String>> cellFoctory = (TableColumn<Employe, String> param) -> {
		            // make cell containing buttons
		            final TableCell<Employe, String> cell = new TableCell<Employe, String>() {
	                @Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    //that cell created only on non-empty rows
	                    if (empty) {
	                        setGraphic(null);
	                        setText(null);

	                    } 
	                        Button deleteIcon = new Button();
	                       Hashtable<Button, String> values= new Hashtable<>();
	                       values.put(deleteIcon, "delete");
	                       view.SetGraphiqueIntoButton(values);
	                           Delete(deleteIcon);
	                                                 
	                        HBox managebtn = new HBox(deleteIcon);
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
			 FilteredList<Employe> filteredData= new FilteredList<>(data,  b ->true);
			 inputRecherche.textProperty().addListener((observable,oldValue,newValue)->{
		    		 filteredData.setPredicate(productSearchModel ->{
		    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
		    				return true;
		    			}
		    			String searchKeyword = newValue.toLowerCase();
		    			if (productSearchModel.getNom().toLowerCase().indexOf(searchKeyword) > -1) {
		    				return true;
		    			}
		    			else if (productSearchModel.getPrenom().toLowerCase().indexOf(searchKeyword) > -1) {
		    				return true;
		    			}
		    			else if (productSearchModel.getDateNaissance().toLowerCase().indexOf(searchKeyword) > -1) {
		    				return true;
		    			}
		    			else if (productSearchModel.getDateRecrutement().toLowerCase().indexOf(searchKeyword) > -1) {
		    				return true;
		    			}
		    			else if (productSearchModel.getRevenuHebdomodaire().toLowerCase().indexOf(searchKeyword) > -1) {
		    				return true;
		    			}
		    			

		    			else {
		    				return false;
		    			}
		    			
		    		 });
		    	 });
		    	 SortedList<Employe> sortedData = new SortedList<>(filteredData);
		    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
		    	 table.setItems(sortedData);

	   }
	    private void Delete(Button delete) {
	    	delete.setOnMouseClicked((MouseEvent event) -> {
	            
	            try {
	            	Employe p = table.getSelectionModel().getSelectedItem();
	                if(p == null) {
	             	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
	                }else {
	             	   
	                
	                query = "DELETE FROM `employes` WHERE id_employe  ="+p.getId();
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
	    	columnNom.setStyle(style);
	    	columnPrenom.setStyle(style);
	    	columnDateNaissance.setStyle(style);
	    	columnDateRecrutement.setStyle(style);
	    	columnButtons.setStyle(style);
	    	style +=";-fx-font-weight:bold";
	    	columnRevenuHebdomodaire.setStyle(style);
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
