package controller.fonction;

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
import model.BisouRectelign;
import model.Charge;
public class MainController implements Initializable  {
    private String src="";
	private DynamiqueView view = new DynamiqueView();
    @FXML
    private TableColumn<BisouRectelign, String> columnButtons;
    @FXML
    private TableColumn<BisouRectelign, String> columnFonction;
    @FXML
    private TableColumn<BisouRectelign, String> columnPrixUnitaire;
    @FXML
    private TableColumn<BisouRectelign, String> columnFonctionComplement;

    @FXML
    private TableColumn<BisouRectelign, Integer> columnID;

    @FXML
    private TableColumn<BisouRectelign, Integer> columnIdComplement;

    @FXML
    private TableColumn<BisouRectelign, String> columnPrixComplement;

    @FXML
    private TextField inputRecherche;
//    charge
    @FXML
    private TableColumn<Charge, String> columnButtonsCharge;

    @FXML
    private TableColumn<Charge, String> columnCharge;
    @FXML
    private TableView<Charge> tableCharge;
    @FXML  
    private TableColumn<Charge,String> columnDateCharge;
    @FXML
    private TableColumn<Charge,String> columnMontantCharge;
    @FXML
    private TextField inputRechercheCharge;
    @FXML
    private TextField inputRechercheComplement;
    @FXML
    private TableColumn<BisouRectelign, String> columnButtonComplement;
    @FXML
    private TableView<BisouRectelign> table;

    @FXML
    private TableView<BisouRectelign> tableComplement;
    private Connection connection=DbConnect.getInstance().getConnection();
    private ObservableList<BisouRectelign> data = FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataC = FXCollections.observableArrayList();
    private ObservableList<Charge> dataCharge = FXCollections.observableArrayList();
    private PreparedStatement statement;
    private String query;
    private ResultSet set;
    @FXML
    void btnActualiser(MouseEvent event) throws SQLException {
    	actualiser();
    }
    
    @FXML
    void btnActualiser1(MouseEvent event) throws SQLException {
    	actualiser1();
    }
    @FXML
    void ActualiserCharges(MouseEvent event) throws SQLException {
    	actualiserCharges();
    }

    @FXML
    void InsererCharge(MouseEvent event) {
    	src="fonction/BisRect/insererCharge";
        try {
  		view.LoadPageFree(event, src);
  	} catch (IOException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
    }
    
    private void actualiser() throws SQLException {
    	data.clear();
    	query="select * from fonctionnalite where type=0";
    	 statement=connection.prepareStatement(query);
    	set =statement.executeQuery();
    	while(set.next()) {
    		BisouRectelign b=new BisouRectelign();
    		b.setId(set.getInt("id_type"));
    		b.setDesignation(set.getString("activitie"));
    		b.setPrix(set.getString("prix_unitaire")+"DA");
    			data.add(b);
    	}
    	
    }
    private void actualiserCharges() throws SQLException {
    	dataCharge.clear();
    	query="select * from charges";
    	statement=connection.prepareStatement(query);
    	set =statement.executeQuery();
    	while(set.next()) {
    		Charge c =new Charge();
    		c.setId_charge(set.getInt("id_charge"));
    		c.setCharge(set.getString("charge"));
    		c.setCout_charge(set.getDouble("cout_charge")+"DA");
    		c.setDate_charge(set.getString("date_charge"));
    		dataCharge.add(c);
    	}
    	
    }
    private void actualiser1() throws SQLException {
    	dataC.clear();
    	query="select * from fonctionnalite where type=1";
    	statement=connection.prepareStatement(query);
    	set =statement.executeQuery();
    	while(set.next()) {
    		BisouRectelign b=new BisouRectelign();
    		b.setId(set.getInt("id_type"));
    		b.setDesignation(set.getString("activitie"));
    		b.setPrix(set.getString("prix_unitaire")+"DA");
    			dataC.add(b);
    	}
    	
    }
    private void LoadLigntoColumn() {
    	columnID.setCellValueFactory(new PropertyValueFactory<BisouRectelign,Integer>("id"));
    	columnFonction.setCellValueFactory(new PropertyValueFactory<BisouRectelign,String>("designation"));
    	columnPrixUnitaire.setCellValueFactory(new PropertyValueFactory<BisouRectelign,String>("prix"));
    }
    private void LoadLigntoColumn1() {
    	columnIdComplement.setCellValueFactory(new PropertyValueFactory<BisouRectelign,Integer>("id"));
    	columnFonctionComplement.setCellValueFactory(new PropertyValueFactory<BisouRectelign,String>("designation"));
    	columnPrixComplement.setCellValueFactory(new PropertyValueFactory<BisouRectelign,String>("prix"));
    
    }
    private void LoadLigntoColumnCharge() {
    	columnCharge.setCellValueFactory(new PropertyValueFactory<Charge,String>("charge"));
    	columnDateCharge.setCellValueFactory(new PropertyValueFactory<Charge,String>("date_charge"));
    	columnMontantCharge.setCellValueFactory(new PropertyValueFactory<Charge,String>("cout_charge"));
    	
    }
    private void load() throws SQLException {
    	actualiser();
    	LoadLigntoColumn();
    	InsertButtons();
    }
    private void load1() throws SQLException {
    	actualiser1();
    	LoadLigntoColumn1();
    	InsertButtons1();
    }
    private void loadCharge() throws SQLException {
    	actualiserCharges();
    	LoadLigntoColumnCharge();
    	InsertButtonsCharge();
    }
    private void InsertButtons() {
    	 Callback<TableColumn<BisouRectelign, String>, TableCell<BisouRectelign, String>> cellFoctory = (TableColumn<BisouRectelign, String> param) -> {
	            // make cell containing buttons
	            final TableCell<BisouRectelign, String> cell = new TableCell<BisouRectelign, String>() {
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
                        Edit(editIcon,table);
                                              
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
    private void InsertButtons1() {
    	Callback<TableColumn<BisouRectelign, String>, TableCell<BisouRectelign, String>> cellFoctory = (TableColumn<BisouRectelign, String> param) -> {
    		// make cell containing buttons
    		final TableCell<BisouRectelign, String> cell = new TableCell<BisouRectelign, String>() {
    			@Override
    			public void updateItem(String item, boolean empty) {
    				super.updateItem(item, empty);
    				//that cell created only on non-empty rows
    				if (empty) {
    					setGraphic(null);
    					setText(null);
    					
    				} 
    				Button editIcon = new Button();
    				Hashtable<Button, String> values= new Hashtable<>();
    				values.put(editIcon, "modifier");
    				view.SetGraphiqueIntoButton(values);
    				Edit(editIcon,tableComplement);
    				
    				HBox managebtn = new HBox(editIcon);
    				view.viewOfHboxForButton(managebtn, values);
    				setGraphic(managebtn);
    				setText(null);    
    			}
    		};
    		
    		return cell;
    	};
    	columnButtonComplement.setCellFactory(cellFoctory);
    	tableComplement.setItems(dataC);
    	FilterByDesignation1();
    	
    	
    }
    private void InsertButtonsCharge() {
    	Callback<TableColumn<Charge, String>, TableCell<Charge, String>> cellFoctory = (TableColumn<Charge, String> param) -> {
    		// make cell containing buttons
    		final TableCell<Charge, String> cell = new TableCell<Charge, String>() {
    			@Override
    			public void updateItem(String item, boolean empty) {
    				super.updateItem(item, empty);
    				//that cell created only on non-empty rows
    				if (empty) {
    					setGraphic(null);
    					setText(null);
    					
    				} 
    				Button editIcon = new Button();
    				Hashtable<Button, String> values= new Hashtable<>();
    				values.put(editIcon, "delete");
    				view.SetGraphiqueIntoButton(values);
    				DeleteCharge(editIcon);
    				HBox managebtn = new HBox(editIcon);
    				view.viewOfHboxForButton(managebtn, values);
    				setGraphic(managebtn);
    				setText(null);    
    			}
    		};
    		
    		return cell;
    	};
    	columnButtonsCharge.setCellFactory(cellFoctory);
    	tableCharge.setItems(dataCharge);
    	FilterByDesignationCharge();
    	
    	
    }
    private void FilterByDesignation() {
		 FilteredList<BisouRectelign> filteredData= new FilteredList<>(data,  b ->true);
		 inputRecherche.textProperty().addListener((observable,oldValue,newValue)->{
	    		 filteredData.setPredicate(productSearchModel ->{
	    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
	    				return true;
	    			}
	    			String searchKeyword = newValue.toLowerCase();
	    			if (productSearchModel.getDesignation().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getPrix().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			
	    			else {
	    				return false;
	    			}
	    			
	    		 });
	    	 });
	    	 SortedList<BisouRectelign> sortedData = new SortedList<>(filteredData);
	    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
	    	 table.setItems(sortedData);

  }
    private void FilterByDesignation1() {
    	FilteredList<BisouRectelign> filteredData= new FilteredList<>(dataC,  b ->true);
    	inputRechercheComplement.textProperty().addListener((observable,oldValue,newValue)->{
    		filteredData.setPredicate(productSearchModel ->{
    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
    				return true;
    			}
    			String searchKeyword = newValue.toLowerCase();
    			if (productSearchModel.getDesignation().toLowerCase().indexOf(searchKeyword) > -1) {
    				return true;
    			}
    			else if (productSearchModel.getPrix().toLowerCase().indexOf(searchKeyword) > -1) {
    				return true;
    			}
    			
    			else {
    				return false;
    			}
    			
    		});
    	});
    	SortedList<BisouRectelign> sortedData = new SortedList<>(filteredData);
    	sortedData.comparatorProperty().bind(tableComplement.comparatorProperty());
    	tableComplement.setItems(sortedData);
    	
    }
    private void FilterByDesignationCharge() {
    	FilteredList<Charge> filteredData= new FilteredList<>(dataCharge,  b ->true);
    	inputRechercheCharge.textProperty().addListener((observable,oldValue,newValue)->{
    		filteredData.setPredicate(productSearchModel ->{
    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
    				return true;
    			}
    			String searchKeyword = newValue.toLowerCase();
    			if (productSearchModel.getCharge().toLowerCase().indexOf(searchKeyword) > -1) {
    				return true;
    			}
    			else if (productSearchModel.getDate_charge().toLowerCase().indexOf(searchKeyword) > -1) {
    				return true;
    			}
    			
    			else {
    				return false;
    			}
    			
    		});
    	});
    	SortedList<Charge> sortedData = new SortedList<>(filteredData);
    	sortedData.comparatorProperty().bind(tableCharge.comparatorProperty());
    	tableCharge.setItems(sortedData);
    	
    }
   private void Delete(Button delete) {
   	delete.setOnMouseClicked((MouseEvent event) -> {
           
           try {
           	BisouRectelign p = table.getSelectionModel().getSelectedItem();
               if(p == null) {
            	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
               }else {
            	   
               
               query = "DELETE FROM `fonctionnalite` WHERE id_type  ="+p.getId();
               statement = connection.prepareStatement(query);
               statement.execute();
               actualiser();
               }
               
           } catch (SQLException ex) {
               Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
          

         

       });

   }
   private void DeleteCharge(Button delete) {
	   delete.setOnMouseClicked((MouseEvent event) -> {
		   
		   try {
			   Charge p = tableCharge.getSelectionModel().getSelectedItem();
			   if(p == null) {
				   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
			   }else {
				   query = "DELETE FROM `charges` WHERE id_charge  ="+p.getId_charge();
				   statement = connection.prepareStatement(query);
				   statement.execute();
				   actualiserCharges();
			   }
			   
		   } catch (SQLException ex) {
			   Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
		   }
	   });
	   
   }
   private void Edit(Button edite,TableView<BisouRectelign> b) {
   	edite.setOnMouseClicked((MouseEvent event) -> {
           
       	BisouRectelign p = b.getSelectionModel().getSelectedItem();
           
           if(p== null) {
            DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");	
        	  
           }else {
        	   
           FXMLLoader loader = new FXMLLoader ();
           loader.setLocation(getClass().getResource("/view/fonction/BisRect/modifier.fxml"));
           try {
               loader.load();
           } catch (IOException ex) {
               Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           ModifierController add = loader.getController();
          
           add.setTextField(p); 
           
           Parent parent = loader.getRoot();
          DynamiqueView.getInstance().loadPageFromtable(parent);
       }});

   }
  
   private void getStylishOfColumn() {
   	String style="-fx-font-size:14px;-fx-alignment:center";
   	columnButtons.setStyle(style);
   	columnFonction.setStyle(style);
   	columnID.setStyle(style);
   	columnIdComplement.setStyle(style);
   	columnFonctionComplement.setStyle(style);
   	columnButtonComplement.setStyle(style);
   	columnCharge.setStyle(style);
   	columnDateCharge.setStyle(style);
	style +=";-fx-font-weight:bold";
   	columnPrixComplement.setStyle(style);
   	columnPrixUnitaire.setStyle(style);
   	columnMontantCharge.setStyle(style);
   }


    @FXML
    void btnType(MouseEvent event) {
    	src="fonction/BisRect/inserer";
      try {
		view.LoadPageFree(event, src);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @FXML
    void home(MouseEvent event) {
    	src="index";
    
  		view.getPageOfButton(event, src);
  	
    }

    @FXML
    void logOut(MouseEvent event) {
    	src="login/login";
        
  		view.getPageOfButton(event, src);
  	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getStylishOfColumn();
		try {
			load();
			load1();
			loadCharge();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
