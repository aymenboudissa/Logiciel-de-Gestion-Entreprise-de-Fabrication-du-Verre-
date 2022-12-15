package controller.achat.PieceDeRechange;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Achat;

public class ConsulterController implements Initializable {

    @FXML
    private TableColumn<Achat, String> columnButtons;

    @FXML
    private TableColumn<Achat, String> columnDateAchat;

    @FXML
    private TableColumn<Achat, String> columnFournisseur;

    @FXML
    private TableColumn<Achat, String> columnLivraison;

    @FXML
    private TableColumn<Achat,String> columnMontant;

    @FXML
    private TableColumn<Achat, String> columnMontantReste;

    @FXML
    private TableColumn<Achat, Integer> columnNumAchat;

    @FXML
    private TableColumn<Achat, String> columnVersement;

    @FXML
    private TextField inputRecherche;
  
    @FXML
    private TableView<Achat> table;
    private DynamiqueView view = new DynamiqueView();
    private ObservableList<Achat> data=FXCollections.observableArrayList();
    private Connection connection=DbConnect.getInstance().getConnection();
   private String query;
   private PreparedStatement statement;
   @FXML
   void btnActualiser(MouseEvent event) throws SQLException {
	   actualiser();
   }
   private void actualiser() {
	  
	   String query="select a.id_achat,a.date_achat,a.livraison,CONCAT(p.nom,' ',p.prenom) as fournisseur,ROUND(sum(c.prix_unitaire*c.qte),2) as montant,ROUND(sum(c.qte),2) as qte  "
          		+ "from achat a,personnes p ,caisse_accessoire c \r\n"
          		+ "where a.id_fournisseur=p.id_personne and p.id_type=1 and a.id_achat = c.id_achat and a.type_achat=1   \r\n"
          		+ "group by a.id_achat,a.date_achat,a.livraison,fournisseur";
       
	   try {
		view.actualiser(data,1,query,connection);
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
    private void load() throws SQLException {
	   actualiser();
	   
	   columnNumAchat.setCellValueFactory(new PropertyValueFactory<Achat,Integer>("id"));
	   columnFournisseur.setCellValueFactory(new PropertyValueFactory<Achat,String>("fournisseur"));
	   columnDateAchat.setCellValueFactory(new PropertyValueFactory<Achat,String>("date_achat"));
	   columnLivraison.setCellValueFactory(new PropertyValueFactory<Achat,String>("livraison"));
	   columnMontant.setCellValueFactory(new PropertyValueFactory<Achat,String>("montant"));
	   columnMontantReste.setCellValueFactory(new PropertyValueFactory<Achat,String>("montant_reste"));
	   columnVersement.setCellValueFactory(new PropertyValueFactory<Achat,String>("versement"));
	  
	   Callback<TableColumn<Achat, String>, TableCell<Achat, String>> cellFoctory = (TableColumn<Achat, String> param) -> {
           // make cell containing buttons
           final TableCell<Achat, String> cell = new TableCell<Achat, String>() {
           @Override
           public void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);
               if (empty) {
                   setGraphic(null);
                   setText(null);

               } 
                   Button deleteIcon = new Button();
                   Button OrdreIcon = new Button();
                   Button VersementIcon = new Button();
                   Hashtable<Button,String> values= new Hashtable<Button,String>();
                   values.put(OrdreIcon,"consulter");
               	     values.put(deleteIcon,"delete");
               	     values.put(VersementIcon,"versement");
               	  view.SetGraphiqueIntoButton(values);
                    
                      Delete(deleteIcon);
                     Versement(VersementIcon);
                     Ordre(OrdreIcon);
                                            
	                   HBox managebtn = new HBox();
	                   managebtn.getChildren().addAll(OrdreIcon,deleteIcon,VersementIcon);
	                   view.viewOfHboxForButton(managebtn, values);
	                   setGraphic(managebtn);
	                   setText(null);    
           }
       };
      
       return cell;
   };

	   columnButtons.setCellFactory(cellFoctory);
   	  table.setItems(data);
   	  view.FilterByDesignation(inputRecherche,data,table);

   }
   

   
    private void Delete(Button delete) {
    	delete.setOnMouseClicked((MouseEvent event) -> {
            
            try {
            	Achat m = table.getSelectionModel().getSelectedItem();
                if(m == null) {
             	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
                }else {
             	   
                
                query = "DELETE FROM `achat` WHERE id_Achat  ="+m.getId();
                statement = connection.prepareStatement(query);
                statement.execute();
                actualiser();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           

          

        });

    }
      private void Ordre(Button click) {
    	click.setOnMouseClicked((MouseEvent event) -> {
    	
     		Achat a = table.getSelectionModel().getSelectedItem();
    		if(a == null) {
    			DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
    		}else {
    			
    			FXMLLoader loader = new FXMLLoader ();
    			loader.setLocation(getClass().getResource("/view/achat/PieceDeRechange/ordre.fxml"));
    			try {
    				loader.load();
    			} catch (IOException ex) {
    				Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
    			}
    			Ordre add=loader.getController();
    			try {
					add.setTextFieled(a);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			Parent parent = loader.getRoot();
    			DynamiqueView.getInstance().loadPageFromtable(parent);
    		}
    		
    		
    	});
    	
    }
    private void Versement(Button click) {
    	click.setOnMouseClicked((MouseEvent event) -> {
    		
    		Achat a = table.getSelectionModel().getSelectedItem();
    		if(a == null) {
    			DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
    		}else {
    			
    			FXMLLoader loader = new FXMLLoader ();
    			loader.setLocation(getClass().getResource("/view/achat/PieceDeRechange/versement.fxml"));
    			try {
    				loader.load();
    			} catch (IOException ex) {
    				Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
    			}
    			Verser add=loader.getController();
    			add.setTextFieled(a.getId());
    			Parent parent = loader.getRoot();
    			DynamiqueView.getInstance().loadPageFromtable(parent);
    		}
    		
    		
    	});
    	
    }
    private void getStylishOfColumn() {
    	 String style="-fx-font-size:12px;-fx-alignment:center";
    	columnFournisseur.setStyle(style);
    	columnDateAchat.setStyle(style);
    	columnNumAchat.setStyle(style);
    	style +=";-fx-font-weight:bold";
    	columnMontant.setStyle(style);
    	columnMontantReste.setStyle(style);
    	columnVersement.setStyle(style);
    	columnLivraison.setStyle(style);
    }
	public void initialize(URL arg0, ResourceBundle arg1) {
      getStylishOfColumn();
      try {
		load();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

}
