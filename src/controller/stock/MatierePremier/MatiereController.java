package controller.stock.MatierePremier;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Matiere;

public class MatiereController implements Initializable {
    	
      private DynamiqueView view = new DynamiqueView();
      private String src="";
      @FXML
      private TableColumn<Matiere, String> columnButtons;
    @FXML
    private TableColumn<Matiere, String> columnDesignation;

    @FXML
    private TableColumn<Matiere, String> columnStockCretique;

    @FXML
    private TableColumn<Matiere, String> columnStockInitiale;

    @FXML
    private TextField inputRecherche;

    @FXML
    private TableView<Matiere> table;
    private String query=null;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    private ObservableList<Matiere> data=FXCollections.observableArrayList();
    @FXML
    void inserer(MouseEvent event) {
    	src="stock/matierePremier/inserer";
       try {
		view.LoadPageFree(event, src);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
     private void actualiser() throws SQLException {
    	 data.clear();
    	 connection=DbConnect.getInstance().getConnection();
         query="select * from matiere_premiere ";
         statement=connection.prepareStatement(query);
         result=statement.executeQuery();
         while(result.next()) {
         	Matiere m =new Matiere();
         	m.setId_matiere(result.getInt("id_matiere"));
         	m.setDesignation(result.getString("designation"));
         	m.setCretique(result.getDouble("stock_cretique")+"m2");
         	m.setInitiale(result.getDouble("stock_initiale")+"m2");
         	data.add(m);
         }
     }
    @FXML
    void refresh(MouseEvent event) throws SQLException {
        actualiser();
    }
    public void loadData() {
    	try {
    		actualiser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	 columnDesignation.setCellValueFactory(new PropertyValueFactory<Matiere,String>("designation"));
	    	 columnStockCretique.setCellValueFactory(new PropertyValueFactory<Matiere,String>("cretique"));
	    	 columnStockInitiale.setCellValueFactory(new PropertyValueFactory<Matiere,String>("initiale"));
	    	   Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>> cellFoctory = (TableColumn<Matiere, String> param) -> {
		            // make cell containing buttons
		            final TableCell<Matiere, String> cell = new TableCell<Matiere, String>() {
	                @Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    //that cell created only on non-empty rows
	                    if (empty) {
	                        setGraphic(null);
	                        setText(null);

	                    } 
	                        Button deleteIcon = new Button();
	                        Button editIcon = new Button();
	                       Hashtable<Button, String> values= new Hashtable<>();
	                       values.put(editIcon,"modifier");
	                 	     values.put(deleteIcon,"delete");
	                       view.SetGraphiqueIntoButton(values);
	                           Delete(deleteIcon);
	                           Edit(editIcon);
	                                                 
	                        HBox managebtn = new HBox(editIcon, deleteIcon);
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
		 FilteredList<Matiere> filteredData= new FilteredList<>(data,  b ->true);
	    	 inputRecherche.textProperty().addListener((observable,oldValue,newValue)->{
	    		 filteredData.setPredicate(productSearchModel ->{
	    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
	    				return true;
	    			}
	    			String searchKeyword = newValue.toLowerCase();
	    			if (productSearchModel.getDesignation().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			

	    			else {
	    				return false;
	    			}
	    			
	    		 });
	    	 });
	    	 SortedList<Matiere> sortedData = new SortedList<>(filteredData);
	    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
	    	 table.setItems(sortedData);

   }
    private void Delete(Button delete) {
    	delete.setOnMouseClicked((MouseEvent event) -> {
            
            try {
            	Matiere m = table.getSelectionModel().getSelectedItem();
                if(m == null) {
             	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
                }else {
             	   
                
                query = "DELETE FROM `matiere_premiere` WHERE id_matiere  ="+m.getId_matiere();
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
    private void Edit(Button edite) {
    	edite.setOnMouseClicked((MouseEvent event) -> {
            
        	Matiere m = table.getSelectionModel().getSelectedItem();
            
            if(m== null) {
             DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");	
         	  
            }else {
         	   
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/stock/matierePremier/modifier.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ModifierController add = loader.getController();
           
            add.setTextField(m,connection); 
            
            Parent parent = loader.getRoot();
           DynamiqueView.getInstance().loadPageFromtable(parent);
        }});

    }
   
    private void getStylishOfColumn() {
    	columnDesignation.setStyle("-fx-font-size:14px;-fx-alignment:center");
    	columnStockCretique.setStyle("-fx-font-size:14px;-fx-alignment:center");
    	columnStockInitiale.setStyle("-fx-font-size:14px;-fx-alignment:center");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        loadData();		
        getStylishOfColumn();
	}

}
