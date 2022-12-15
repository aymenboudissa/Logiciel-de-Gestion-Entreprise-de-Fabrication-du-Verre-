package controller.humaine.utilisateurs;

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
import model.Utilisateur;

public class ConsulterController implements Initializable {
    
	private DynamiqueView view = new DynamiqueView();
    @FXML
    private TableColumn<Utilisateur, String> columnAdmin;

    @FXML
    private TableColumn<Utilisateur,String> columnButtons;

    @FXML
    private TableColumn<Utilisateur, String> columnPassword;

    @FXML
    private TableColumn<Utilisateur, String> columnUtilisateur;

    @FXML
    private TextField inputRecherche;

    @FXML
    private TableView<Utilisateur> table;

    @FXML
    void btnActualiser(MouseEvent event) {
    	try {
			actualiser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    private String query=null;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    private ObservableList<Utilisateur> data=FXCollections.observableArrayList();
     private void actualiser() throws SQLException {
    	 data.clear();
    	 connection=DbConnect.getInstance().getConnection();
         query="select * from utilisateurs";
         statement=connection.prepareStatement(query);
         result=statement.executeQuery();
         while(result.next()) {
        	 Utilisateur u =new Utilisateur();
           u.setId(result.getInt("id_utilisateur"));
           u.setPassword(result.getString("password"));
           u.setUser(result.getString("user"));
           u.setId_role(result.getInt("role"));
           if(result.getInt("role")==1) {
        	   u.setRole("Oui");
           }else {
        	   u.setRole("Non");
           }
           data.add(u);
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
	    	 columnPassword.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("password"));
	    	 columnUtilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("user"));
	    	 columnAdmin.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("role"));
	    	 Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory = (TableColumn<Utilisateur, String> param) -> {
		            // make cell containing buttons
		            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
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
		    	 columnButtons.setCellFactory(cellFoctory);
		    	  table.setItems(data);
		    	  FilterByDesignation();
		   	    
    }
   private void FilterByDesignation() {
		 FilteredList<Utilisateur> filteredData= new FilteredList<>(data,  b ->true);
		 inputRecherche.textProperty().addListener((observable,oldValue,newValue)->{
	    		 filteredData.setPredicate(productSearchModel ->{
	    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
	    				return true;
	    			}
	    			String searchKeyword = newValue.toLowerCase();
	    			if (productSearchModel.getPassword().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getUser().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getRole().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			

	    			else {
	    				return false;
	    			}
	    			
	    		 });
	    	 });
	    	 SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
	    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
	    	 table.setItems(sortedData);

   }
    private void Delete(Button delete) {
    	delete.setOnMouseClicked((MouseEvent event) -> {
            
            try {
            	Utilisateur p = table.getSelectionModel().getSelectedItem();
                if(p == null) {
             	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
                }else {
                
                query = "DELETE FROM `utilisateurs ` WHERE id_utilisateur="+p.getId();
                Connection connection=DbConnect.getInstance().getConnection();
                statement = connection.prepareStatement(query);
                statement.execute();
                actualiser();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    private void Edit(Button edite) {
    	edite.setOnMouseClicked((MouseEvent event) -> {
            
    		Utilisateur  p = table.getSelectionModel().getSelectedItem();
            
            if(p== null) {
             DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");	
         	  
            }else {
         	   
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/humaine/utilisateurs/modifier.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
            }
           ModifierController add = loader.getController();
            add.setTextField(p); 
            Parent parent = loader.getRoot();
           DynamiqueView.getInstance().loadPageFromtable(parent);
        }});
    }
    private void getStylishOfColumn() {
    	String style="-fx-font-size:14px;-fx-alignment:center";
    	columnUtilisateur.setStyle(style);
    	columnPassword.setStyle(style);
    	columnButtons.setStyle(style);
    	columnAdmin.setStyle(style);
    }
    @FXML
    void btnInserer(MouseEvent event) {
          try {
			view.LoadPageFree(event, "humaine/utilisateurs/inserer");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();
		getStylishOfColumn();
	}

}
