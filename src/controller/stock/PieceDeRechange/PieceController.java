package controller.stock.PieceDeRechange;

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
import model.Piece;

public class PieceController implements Initializable{
	private String src="";
	 private String query=null;
	    private Connection connection=DbConnect.getInstance().getConnection();;
	    private PreparedStatement statement;
	    private ResultSet result;
	    private ObservableList<Piece> data=FXCollections.observableArrayList();
	   
	private DynamiqueView view = new DynamiqueView();
	@FXML
    private TableColumn<Piece, String> columnPiece;

	 @FXML
    private TableColumn<Piece, Double> columnStockCretique;

    @FXML
    private TableColumn<Piece, Double> columnStockInitiale;

    @FXML
    private TableColumn<Piece, String> columnButtons;
    @FXML
    private TextField inputRecherche;
    @FXML
    private TableView<Piece> table;

    @FXML
    void btnActualiser(MouseEvent event) {
        try {
			refresh();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnConsommation(MouseEvent event) {
        src="stock/PieceDeRechange/consommation";
    	try {
    		view.LoadPageFree(event, src);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void btnProduit(MouseEvent event) {
       src="stock/pieceDeRechange/inserer";
       try {
		view.LoadPageFree(event, src);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
    }
    private void refresh() throws SQLException {
    	data.clear();
    	query="select * from accessoires";
    	statement=connection.prepareStatement(query);
       result=statement.executeQuery();
       while(result.next()) {
    	   Piece p = new Piece();
    	   p.setId_accessoire(result.getInt("id_accessoire"));
    	   p.setDesignation(result.getString("designation"));
    	   p.setCretique(result.getDouble("stock_cretique"));
    	   p.setInitiale(result.getDouble("stock_initiale"));
    	   data.add(p);
       }
    }
    private void load() throws SQLException {
    	refresh();
    	columnPiece.setCellValueFactory(new PropertyValueFactory<Piece,String>("designation"));
    	columnStockInitiale.setCellValueFactory(new PropertyValueFactory<Piece,Double>("initiale"));
    	columnStockCretique.setCellValueFactory(new PropertyValueFactory<Piece,Double>("cretique"));
        Callback<TableColumn<Piece, String>, TableCell<Piece, String>> cellFoctory = (TableColumn<Piece, String> param) -> {
            // make cell containing buttons
            final TableCell<Piece, String> cell = new TableCell<Piece, String>() {
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
                    Button ConsommationIcon = new Button();
                   Hashtable<Button, String> values= new Hashtable<Button,String>();
                   values.put(editIcon, "modifier");
                   values.put(deleteIcon, "delete");
                   values.put(ConsommationIcon, "versement");
                   
                   view.SetGraphiqueIntoButton(values);
                       Delete(deleteIcon);
                       Edit(editIcon);
                       Consommer(ConsommationIcon);
                                             
                    HBox managebtn = new HBox(editIcon, deleteIcon,ConsommationIcon);
                    view.viewOfHboxForButton(managebtn,values);
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
		 FilteredList<Piece> filteredData= new FilteredList<>(data,  b ->true);
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
	    	 SortedList<Piece> sortedData = new SortedList<>(filteredData);
	    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
	    	 table.setItems(sortedData);

  }

    private void Delete(Button delete) {
    	delete.setOnMouseClicked((MouseEvent event) -> {
            
            try {
            	Piece p = table.getSelectionModel().getSelectedItem();
                if(p == null) {
             	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
                }else {
             	   
                
                query = "DELETE FROM `accessoires` WHERE id_accessoire  ="+p.getId_accessoire();
                statement = connection.prepareStatement(query);
                statement.execute();
                refresh();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(PieceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           

          

        });

    }
    private void Edit(Button edite) {
    	edite.setOnMouseClicked((MouseEvent event) -> {
            
        	Piece p = table.getSelectionModel().getSelectedItem();
            
            if(p== null) {
             DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");	
         	  
            }else {
         	   
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/stock/pieceDeRechange/modifier.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(PieceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                ModifierController add = loader.getController();           
             add.setTextField(p,connection); 
            
            Parent parent = loader.getRoot();
           DynamiqueView.getInstance().loadPageFromtable(parent);
        }});

    }
    private void Consommer(Button edite) {
    	edite.setOnMouseClicked((MouseEvent event) -> {
    		
    		Piece p = table.getSelectionModel().getSelectedItem();
    		
    		if(p== null) {
    			DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");	
    			
    		}else {
    			
    			FXMLLoader loader = new FXMLLoader ();
    			loader.setLocation(getClass().getResource("/view/stock/pieceDeRechange/consommation.fxml"));
    			try {
    				loader.load();
    			} catch (IOException ex) {
    				Logger.getLogger(PieceController.class.getName()).log(Level.SEVERE, null, ex);
    			}
    			
    			InsererConsommation add = loader.getController();           
    			add.setTextField(p,connection); 
    			
    			Parent parent = loader.getRoot();
    			DynamiqueView.getInstance().loadPageFromtable(parent);
    		}});
    	
    }
    private void getStylishOfColumn() {
    	columnPiece.setStyle("-fx-font-size:14px;-fx-alignment:center");
    	columnStockCretique.setStyle("-fx-font-size:14px;-fx-alignment:center");
    	columnStockInitiale.setStyle("-fx-font-size:14px;-fx-alignment:center");
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
