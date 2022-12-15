package controller.achat.MatierePremier;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Achat;
import model.CaisseMatiere;
import model.Report;
import model.Versement;

public class Ordre implements Initializable {

    @FXML
    private TableColumn<CaisseMatiere, String> columnLargeur;

    @FXML
    private TableColumn<CaisseMatiere, String> columnLongueur;

    @FXML
    private TableColumn<CaisseMatiere, String> columnMatiere;

    @FXML
    private TableColumn<CaisseMatiere, Integer> columnNbrCaisse;

    @FXML
    private TableColumn<CaisseMatiere, String> columnPrix;

    @FXML
    private TableColumn<CaisseMatiere, String> columnTotale;
    
    @FXML
    private TableColumn<Versement, String> columnDateVersement;

    @FXML
    private TableColumn<Versement, String> columnVersement;

    @FXML
    private TableView<CaisseMatiere> tableMatiere;

    @FXML
    private TableView<Versement> tableVersement;

    @FXML
    private Text txtAdresse;

    @FXML
    private Text txtDateCreation;

    @FXML
    private Text txtLivraison;

    @FXML
    private Text txtIdAchat;

    @FXML
    private Text txtMontantTotale;

    @FXML
    private Text txtNom;

    @FXML
    private Text txtNumTeleph;

    @FXML
    private Text txtQteTotale;
    private DynamiqueView view = new DynamiqueView(); 
    @FXML
    private Text txtVersementTotale;
     private ObservableList<CaisseMatiere> data=FXCollections.observableArrayList();
     private ObservableList<Versement> dataVersement=FXCollections.observableArrayList();
    @FXML
    void btnFermer(MouseEvent event) {
      view.closeStage(event);
    }
    
    @FXML
    void imprimer(MouseEvent event) {
    	String value =txtIdAchat.getText().replace("#", "");
    	String value1 =txtVersementTotale.getText().replace("DA","");
    	Double versement=Double.parseDouble(value1);
    	Integer id=Integer.parseInt(value);
    	printReport(id,versement);		
    }
    private void printReport(Integer id,Double versement) {
    	Connection conn=DbConnect.getInstance().getConnection();
        String currentPath = null;
		try {
			currentPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	currentPath+="/src/reporting/reports/Invoice.jrxml";    
    	Report.createReport(conn,currentPath,id,versement);
    	
    }
    public void setTextFieled(Achat a) throws SQLException {
    	txtNom.setText(a.getFournisseur());
    	txtDateCreation.setText(a.getDate_achat());
    	txtLivraison.setText(a.getLivraison());
    	txtIdAchat.setText("#"+a.getId());
    	txtMontantTotale.setText(a.getMontant());
    	txtVersementTotale.setText(a.getVersement());
    	String r= a.getQte();
    	txtQteTotale.setText(view.formatDouble(Double.parseDouble(r))+" m2");
    	LaodMatiere(a.getId());

    	LaodVersement(a.getId());
    }   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		getStylishOfColumn();
	}
	public void LaodMatiere(int id) throws SQLException {
		Connection conn=DbConnect.getInstance().getConnection();
		String query="select c.*,m.designation,prix_unitaire*c.longueur*c.largeur*c.nbr_caisse as totale \r\n"
				+ "from caisse_matiere c,matiere_premiere m \r\n"
				+ "where c.id_achat="+id+" and m.id_matiere=c.id_matiere";
		PreparedStatement statement=conn.prepareStatement(query);
		ResultSet set=statement.executeQuery();
		while(set.next()) {
			CaisseMatiere c = new CaisseMatiere();
			c.setDesignation(set.getString("designation"));
			c.setLargeur(set.getDouble("largeur")+"m");
			c.setLongueur(set.getDouble("longueur")+"m");
			c.setNbr_caisse(set.getDouble("nbr_caisse"));
			
			String p=view.formatDouble(set.getDouble("prix_unitaire"));
			c.setPrix_unitaire(p+" DA");
			String t=view.formatDouble(set.getDouble("totale"));
			c.setTotale(t+" DA");
			data.add(c);
		}
		ColumnChargermMatiere();
		
		
	}
	public void LaodVersement(int id) throws SQLException {
		Connection conn=DbConnect.getInstance().getConnection();
		String query="select *\r\n"
				+ "from versement_achat v  \r\n"
				+ "where v.id_etranger="+id+" and type=0 ";
		PreparedStatement statement=conn.prepareStatement(query);
		ResultSet set=statement.executeQuery();
		while(set.next()) {
			Versement v= new Versement();
			String p=view.formatDouble(set.getDouble("versement"));
			v.setVersement(p+" DA");
		   v.setDate_versement(set.getString("date_versement"));	
			dataVersement.add(v);
		}
		ColumnChargermVersement();
		
		
	}
	
private void ColumnChargermVersement() {
	columnVersement.setCellValueFactory(new PropertyValueFactory<Versement,String>("versement"));
	columnDateVersement.setCellValueFactory(new PropertyValueFactory<Versement,String>("date_versement"));
    tableVersement.setItems(dataVersement);
}
private void ColumnChargermMatiere() {
		columnMatiere.setCellValueFactory(new PropertyValueFactory<CaisseMatiere,String>("designation"));
		columnNbrCaisse.setCellValueFactory(new PropertyValueFactory<CaisseMatiere,Integer>("nbr_caisse"));
		columnLargeur.setCellValueFactory(new PropertyValueFactory<CaisseMatiere,String>("largeur"));
		columnLongueur.setCellValueFactory(new PropertyValueFactory<CaisseMatiere,String>("longueur"));
		columnPrix.setCellValueFactory(new PropertyValueFactory<CaisseMatiere,String>("prix_unitaire"));
		columnTotale.setCellValueFactory(new PropertyValueFactory<CaisseMatiere,String>("totale"));
	   tableMatiere.setItems(data);
	}
	private void getStylishOfColumn() {
   	 String style="-fx-font-size:12px;-fx-alignment:center;";
   	columnMatiere.setStyle(style);
   	columnNbrCaisse.setStyle(style);
   	columnLargeur.setStyle(style);
   	columnLongueur.setStyle(style);
   	style +=";-fx-font-weight:bold";
   	columnPrix.setStyle(style);
   	columnTotale.setStyle(style);
   	String v= style.replace("-fx-alignment:center", "");
   	columnVersement.setStyle(v);
   	
   	
   }

}
