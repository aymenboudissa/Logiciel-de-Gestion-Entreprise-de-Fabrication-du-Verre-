package controller.fourClient;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Personne;

public class ConsulterController implements Initializable {

    @FXML
    private TableColumn<Personne, String> ColumnButtons;

    @FXML
    private TableColumn<Personne, String> ColumnNom;

    @FXML
    private TableColumn<Personne, Integer> ColumnNumTel;

    @FXML
    private TableColumn<Personne, String> ColumnPrenom;
    @FXML
    private TableColumn<Personne, String> ColumnSolde;
    @FXML
    private TableColumn<Personne, String> ColumnType;

    @FXML
    private TableColumn<Personne, String> Columnadresse;

    @FXML
    private TableColumn<Personne, Integer> id;

    @FXML
    private TextField inpuRecherche;

    @FXML
    private TableView<Personne> table;
    private DynamiqueView view= new DynamiqueView();
   
    @FXML
    void btnActualiser(MouseEvent event) throws SQLException {
    	actualiser();
    }

    @FXML
    void btnInserer(MouseEvent event) {
    	String src="fourClient/inserer";
        try {
 		view.LoadPageFree(event, src);
 	} catch (IOException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	}
    }
    private String query=null;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    private ObservableList<Personne> data=FXCollections.observableArrayList();
     private void actualiser() throws SQLException {
    	 data.clear();
    	 connection=DbConnect.getInstance().getConnection();
         query="select * from personnes p";
         statement=connection.prepareStatement(query);
         result=statement.executeQuery();
         while(result.next()) {
        	 Integer idType=result.getInt("id_type");
           Personne p = new Personne();
           p.setId(result.getInt("id_personne"));
           p.setNom(result.getString("nom"));
           p.setSolde(result.getDouble("solde")+"DA");
           p.setPrenom(result.getString("prenom"));
           p.setNum_telephone(result.getInt("num_telephone"));
           p.setAdresse(result.getString("adresse"));
           p.setId_type(idType);
           String value=getTypeOfPersonne(idType);
           p.setType(value);
           data.add(p);
         }
     }
     private Hashtable<Integer,String > getTypePersonne(){
    	 Hashtable<Integer,String > values =new Hashtable<>();
     	 values.put(0, "Client");
     	 values.put(1, "Fournisseur");
     return values;
     }
     private String getTypeOfPersonne(Integer id) {
    	 Hashtable<Integer,String > values =getTypePersonne();
    	 return  values.get(id);
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
	    	 id.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("id"));
	    	 ColumnNom.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
	    	 ColumnPrenom.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
	    	 ColumnNumTel.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("num_telephone"));
	    	 ColumnType.setCellValueFactory(new PropertyValueFactory<Personne,String>("type"));
	    	 Columnadresse.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
	    	 ColumnSolde.setCellValueFactory(new PropertyValueFactory<Personne,String>("solde"));
	    	 
	    	 Callback<TableColumn<Personne, String>, TableCell<Personne, String>> cellFoctory = (TableColumn<Personne, String> param) -> {
		            // make cell containing buttons
		            final TableCell<Personne, String> cell = new TableCell<Personne, String>() {
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
	                       values.put(editIcon, "modifier");
	                       values.put(deleteIcon, "delete");
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
		    	 ColumnButtons.setCellFactory(cellFoctory);
		    	  table.setItems(data);
		    	  FilterByDesignation();
		   	    
    }
   private void FilterByDesignation() {
		 FilteredList<Personne> filteredData= new FilteredList<>(data,  b ->true);
		 inpuRecherche.textProperty().addListener((observable,oldValue,newValue)->{
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
	    			else if (productSearchModel.getType().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			

	    			else {
	    				return false;
	    			}
	    			
	    		 });
	    	 });
	    	 SortedList<Personne> sortedData = new SortedList<>(filteredData);
	    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
	    	 table.setItems(sortedData);

   }
    private void Delete(Button delete) {
    	delete.setOnMouseClicked((MouseEvent event) -> {
            
            try {
            	Personne p = table.getSelectionModel().getSelectedItem();
                if(p == null) {
             	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
                }else {
             	   
                
                query = "DELETE FROM `personnes` WHERE id_personne  ="+p.getId();
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
            
        	Personne p = table.getSelectionModel().getSelectedItem();
            
            if(p== null) {
             DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");	
         	  
            }else {
         	   
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/fourClient/modifier.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            controller.fourClient.ModifierController add = loader.getController();
           
            add.setTextField(p); 
            
            Parent parent = loader.getRoot();
           DynamiqueView.getInstance().loadPageFromtable(parent);
        }});

    }
   
    private void getStylishOfColumn() {
    	String style="-fx-font-size:14px;-fx-alignment:center";
    	id.setStyle(style);
    	ColumnNom.setStyle(style);
    	ColumnPrenom.setStyle(style);
    	ColumnNumTel.setStyle(style);
    	ColumnButtons.setStyle(style);
    	ColumnType.setStyle(style);
    	Columnadresse.setStyle(style);
    	style+=";-fx-font-weight:bold;";
    	ColumnSolde.setStyle(style);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        loadData();		
        getStylishOfColumn();
       
	}


}
