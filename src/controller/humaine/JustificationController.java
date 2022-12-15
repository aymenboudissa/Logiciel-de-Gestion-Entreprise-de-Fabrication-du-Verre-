package controller.humaine;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Justification;

public class JustificationController implements Initializable {
    @FXML
    private TableColumn<Justification, String> columnButtons;

    @FXML
    private TableColumn<Justification, String> columnDateAbsence;

    @FXML
    private TableColumn<Justification, String> columnDateJustification;

    @FXML
    private TableColumn<Justification, String> columnDescription;

    @FXML
    private TableColumn<Justification, String> columnEmploye;

    @FXML
    private TableColumn<Justification, Integer> columnID;

    @FXML
    private TableColumn<Justification, ImageView> columntypeAbsence;

    @FXML
    private TextField inputRecherche;

    @FXML
    private TableView<Justification> table;
    
    private Connection connection =DbConnect.getInstance().getConnection();
    private ObservableList<Justification> data = FXCollections.observableArrayList();
    private DynamiqueView view =new DynamiqueView();
    private String query;
    private PreparedStatement statement;
    private ResultSet rs;
    private void loadToObservable() throws SQLException {
    	data.clear();
    	 query="select j.*,a.*,t.image,e.nom_employe||' '||e.prenom_employe as employe from justifications j,absences a,type_absence t,employes e  \r\n"
    	 		+ "where a.id_type=t.id_type and j.id_absence=a.id_absence and a.id_employe=e.id_employe";
    	statement=connection.prepareStatement(query);
    	rs= statement.executeQuery();
    	while(rs.next()) {
    		Justification j = new Justification();
    		j.setId(rs.getInt("id_justification"));
    		j.setId_absence(rs.getInt("id_absence"));
    		j.setDateJustification(rs.getString("date_justification"));
    		j.setDateAbsence(rs.getString("date_debut"));
    		j.setDescription(rs.getString("description"));
    		String photo=rs.getString("image");
    		Image set=new Image(getClass().getResourceAsStream(photo));
    		ImageView img=new ImageView(set);
    		img.setFitHeight(30);
    		img.setFitWidth(25);
    		j.setTypeAbsence(img);
            j.setEmploye(rs.getString("employe"));
            data.add(j);
    }
    	
    }
    private void loadLignIntoColumns() {
		columnDateAbsence.setCellValueFactory(new PropertyValueFactory<Justification,String>("dateAbsence"));
		columnDateJustification.setCellValueFactory(new PropertyValueFactory<Justification,String>("dateJustification"));
		columnEmploye.setCellValueFactory(new PropertyValueFactory<Justification,String>("employe"));
		columnID.setCellValueFactory(new PropertyValueFactory<Justification,Integer>("id"));
		columntypeAbsence.setCellValueFactory(new PropertyValueFactory<Justification,ImageView>("typeAbsence"));
		columnDescription.setCellValueFactory(new PropertyValueFactory<Justification,String>("description"));
		
	
    }
    private void loadData() throws SQLException {
    	loadToObservable();
    	loadLignIntoColumns();
    	InsertButtons();
    }
    private void InsertButtons() {
		 Callback<TableColumn<Justification, String>, TableCell<Justification, String>> cellFoctory = (TableColumn<Justification, String> param) -> {
	            // make cell containing buttons
	            final TableCell<Justification, String> cell = new TableCell<Justification, String>() {
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
		 FilteredList<Justification> filteredData= new FilteredList<>(data,  b ->true);
		 inputRecherche.textProperty().addListener((observable,oldValue,newValue)->{
	    		 filteredData.setPredicate(productSearchModel ->{
	    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
	    				return true;
	    			}
	    			String searchKeyword = newValue.toLowerCase();
	    			if (productSearchModel.getDescription().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getDateAbsence().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getDateJustification().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			else if (productSearchModel.getEmploye().toLowerCase().indexOf(searchKeyword) > -1) {
	    				return true;
	    			}
	    			    			else {
	    				return false;
	    			}
	    			
	    		 });
	    	 });
	    	 SortedList<Justification> sortedData = new SortedList<>(filteredData);
	    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
	    	 table.setItems(sortedData);

  }
   private void Delete(Button delete) {
   	delete.setOnMouseClicked((MouseEvent event) -> {
           
           try {
           	Justification p = table.getSelectionModel().getSelectedItem();
               if(p == null) {
            	   DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
               }else {
               query = "DELETE FROM `justifications` WHERE id_justification  ="+p.getId();
               Connection connection=DbConnect.getInstance().getConnection();
               statement = connection.prepareStatement(query);
               statement.execute();
               String query2="update absences set justifier='Non' where id_absence="+p.getId_absence()+"";
               statement =connection.prepareStatement(query2);
               statement.execute();
               loadToObservable();
               }
               
           } catch (SQLException ex) {
               Logger.getLogger(MatiereController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
          

         

       });

   }
  
   private void getStylishOfColumn() {
   	String style="-fx-font-size:14px;-fx-alignment:center";
   	columnDateAbsence.setStyle(style);
   	columnDateJustification.setStyle(style);
   	columnEmploye.setStyle(style);
   	columntypeAbsence.setStyle(style);
   	columnID.setStyle(style);
   	columnButtons.setStyle(style);
   
   }
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
  try {
	loadData();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  getStylishOfColumn();
}


    

}
