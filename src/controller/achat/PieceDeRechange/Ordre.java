	package controller.achat.PieceDeRechange;

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
import model.CaisseProduit;
import model.Report;
import model.Versement;

public class Ordre implements Initializable {
	    @FXML
	    private TableColumn<Versement, String> columnDateVersement;

	    @FXML
	    private TableColumn<CaisseProduit, String> columnPiece;

	    @FXML
	    private TableColumn<CaisseProduit, String> columnPrix;
	    
	    @FXML
	    private TableColumn<CaisseProduit, Double> columnQte;

	    @FXML
	    private TableColumn<CaisseProduit, String> columnTotale;

	    @FXML
	    private TableColumn<Versement, String> columnVersement;

	    @FXML
	    private TableView<CaisseProduit> tablePiece;

	    @FXML
	    private TableView<Versement> tableVersement;

	    private ObservableList<CaisseProduit> dataPiece=FXCollections.observableArrayList();
	    private ObservableList<Versement> dataVersement=FXCollections.observableArrayList();
	    
	    @FXML
	    private Text txtAdresse;

	    @FXML
	    private Text txtDateaAchat;

	    @FXML
	    private Text txtFournisseur;

	    @FXML
	    private Text txtIdAchat;

	    @FXML
	    private Text txtLivraison;

	    @FXML
	    private Text txtMontantTotale;

	    @FXML
	    private Text txtNumTelephone;

	    @FXML
	    private Text txtQteTotale;

	    @FXML
	    private Text txtVersementTotale;
         
	    private DynamiqueView view=new DynamiqueView();
	    @FXML
	    void btnFermer(MouseEvent event) {
	    	view.closeStage(event);
	    }

	    @FXML
	    void btnImprimer(MouseEvent event) {
	    	String value =txtIdAchat.getText().replace("#", "");
	    	String value1 =txtVersementTotale.getText().replace("DA", "");
	    	Integer id=Integer.parseInt(value);
	    	Double versement=Double.parseDouble(value1);
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
	        	currentPath+="/src/reporting/reports/Invoice2.jrxml";  
	    	Report.createReport(conn,currentPath,id,versement);
	    
	    }

	    public void setTextFieled(Achat a) throws SQLException {
	    	txtFournisseur.setText(a.getFournisseur());
	    	txtDateaAchat.setText(a.getDate_achat());
	    	txtLivraison.setText(a.getLivraison());
	    	txtIdAchat.setText("#"+a.getId());
	    	txtMontantTotale.setText(a.getMontant());
	    	txtVersementTotale.setText(a.getVersement());
	    	txtQteTotale.setText(a.getQte());
	    	LaodPiece(a.getId());
	    	LaodVersement(a.getId());
	    	
	    }
	   
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
			getStylishOfColumn();
		}
		public void LaodPiece(int id) throws SQLException {
			Connection conn=DbConnect.getInstance().getConnection();
			String query="select c.*,m.designation,prix_unitaire*c.qte as totale \r\n"
					+ "from caisse_accessoire c,accessoires m \r\n"
					+ "where c.id_achat="+id+" and m.id_accessoire=c.id_accessoire";
			PreparedStatement statement=conn.prepareStatement(query);
			ResultSet set=statement.executeQuery();
			while(set.next()) {
				CaisseProduit c = new CaisseProduit();
				c.setDesignation(set.getString("designation"));
				c.setQuantite(set.getDouble("qte"));
				String p=view.formatDouble(set.getDouble("prix_unitaire"));
				c.setPrix(p+" DA");
				String t=view.formatDouble(set.getDouble("totale"));
				c.setTotale(t+" DA");
				dataPiece.add(c);
			}
			ColumnChargermProduit();
			
			
		}
		public void LaodVersement(int id) throws SQLException {
			Connection conn=DbConnect.getInstance().getConnection();
			String query="select *\r\n"
					+ "from versement_achat v  \r\n"
					+ "where v.id_etranger="+id+" and type=1 ";
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
	private void ColumnChargermProduit() {
			columnPiece.setCellValueFactory(new PropertyValueFactory<CaisseProduit,String>("designation"));
			columnQte.setCellValueFactory(new PropertyValueFactory<CaisseProduit,Double>("quantite"));
			columnPrix.setCellValueFactory(new PropertyValueFactory<CaisseProduit,String>("prix"));
			columnTotale.setCellValueFactory(new PropertyValueFactory<CaisseProduit,String>("totale"));
		   tablePiece.setItems(dataPiece);
		}
		private void getStylishOfColumn() {
	   	 String style="-fx-font-size:12px;-fx-alignment:center;";
	   	columnPiece.setStyle(style);
	   	columnQte.setStyle(style);
	   	columnVersement.setStyle(style);
	   	columnDateVersement.setStyle(style);
	   	style+=";-fx-font-weight:bold";
	   	columnPrix.setStyle(style);
	   	columnTotale.setStyle(style);
	   	columnVersement.setStyle(style);
	   	
	   	
	   }

}
 