package controller.vente;

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
import model.Commande;
import model.ListeCommande;
import model.Personne;
import model.Report;
import model.Rupture;
import model.Versement;

public class Ordre implements Initializable {
	@FXML
    private TableColumn<ListeCommande, Integer> columnIdListe;
	 @FXML
	    private TableColumn<Versement, String> columnDAteVersement;

	    @FXML
	    private TableColumn<ListeCommande, String> columnDessous;

	    @FXML
	    private TableColumn<ListeCommande, String> columnDroite;

	    @FXML
	    private TableColumn<ListeCommande, String> columnGauche;

	    @FXML
	    private TableColumn<ListeCommande, String> columnHaut;

	    @FXML
	    private TableColumn<ListeCommande, String> columnLargeur;

	    @FXML
	    private TableColumn<ListeCommande, String> columnLongueur;

	    @FXML
	    private TableColumn<ListeCommande, String> columnMatiere1;

	    @FXML
	    private TableColumn<ListeCommande, String> columnMatiere2;

	    @FXML
	    private TableColumn<ListeCommande, String> columnMatiere3;

	    @FXML
	    private TableColumn<Rupture, String> columnMatiere4;
	    @FXML
	    private TableColumn<ListeCommande, String> columnMatiere5;

	    @FXML
	    private TableColumn<ListeCommande, String> columnMetrage;
	    @FXML
	    private TableColumn<ListeCommande, String> columnMetrage1;

	    @FXML
	    private TableColumn<ListeCommande, String> columnMontantArticle;

	    @FXML
	    private TableColumn<ListeCommande, String> columnMontantBr;

	    @FXML
	    private TableColumn<ListeCommande, String> columnMontantComplement;
	    @FXML
	    private TableColumn<ListeCommande, String> columnMontantComplement1;

	    @FXML
	    private Text columnMontantTotaleBr;

	    @FXML
	    private Text columnMontantTotaleComplement;
	    @FXML
	    private Text columnMontantTotaleComplement1;
	    @FXML
	    private Text txtDateLivraison;
	    @FXML
	    private TableColumn<Rupture,String> columnMontantretour;

	    @FXML
	    private Text columnMontantretourTotale;


	    @FXML
	    private TableColumn<ListeCommande, String> columnPrixArticle;

	    @FXML
	    private TableColumn<ListeCommande, String> columnPrixBr;

	    @FXML
	    private TableColumn<ListeCommande, String> columnPrixIncision;

	    @FXML
	    private TableColumn<ListeCommande, String> columnPrixSablage;

	    @FXML
	    private TableColumn<ListeCommande, Double> columnQte;

	    @FXML
	    private TableColumn<Rupture, Double> columnQteRetour;

	    @FXML
	    private TableColumn<ListeCommande, String> columnTypeBr;

	    @FXML
	    private TableColumn<ListeCommande, String> columnTypeComplement;
	    @FXML
	    private TableColumn<ListeCommande, String> columnTypeComplement1;

	    @FXML
	    private TableColumn<Versement,String> columnVersement;

	    @FXML
	    private TableView<ListeCommande> tableArticle;

	    @FXML
	    private TableView<ListeCommande> tableBr;

	    @FXML
	    private TableView<ListeCommande> tableComplement;
	    @FXML
	    private TableView<ListeCommande> tableComplement1;

	    @FXML
	    private TableView<Rupture> tableRupture;

	    @FXML
	    private TableView<Versement> tableVersement;

	    @FXML
	    private Text txtAdresse;

	    @FXML
	    private Text txtDate;

	    @FXML
	    private Text txtMontantTotale;

	    @FXML
	    private Text txtNom;

	    @FXML
	    private Text txtNum;

	    @FXML
	    private Text txtOrdreCmd;

	    @FXML
	    private Text txtPrenom;

	    @FXML
	    private Text txtQteTotale;
	    @FXML
	    private TableColumn<Rupture,String> columnPrixRetour;
	    @FXML
	    private Text txtTotaleArticle;
	    private String query;
	    private PreparedStatement statement;
	    private ResultSet rs;
	    @FXML
	    private Text txtVersement;
	    private Connection connection=DbConnect.getInstance().getConnection();
	    private ObservableList<ListeCommande> dataArticle=FXCollections.observableArrayList();
	    private ObservableList<Versement> dataVersement=FXCollections.observableArrayList();
	    private ObservableList<Rupture> dataRupturer=FXCollections.observableArrayList();
	    private DynamiqueView view=new DynamiqueView();		
	    @FXML
	    void btnImprimer(MouseEvent event) {
	    	String value=txtOrdreCmd.getText().replace("#", "");
	    	String value1=txtVersement.getText().replace("DA", "");
	    	Double versement=Double.parseDouble(value1);
	    	
	    	Integer id=Integer.parseInt(value);
	    	printReport(id,versement,"Cherry_Land.jrxml");
	   }
            @FXML
	    void btnImprimer1(MouseEvent event) {
	    	String value=txtOrdreCmd.getText().replace("#", "");
	    	Integer id=Integer.parseInt(value);
	    	printReport(id,0.0,"Cherry.jrxml");
	   }
	    private void printReport(Integer id,Double versement,String file) {
	    	Connection conn=DbConnect.getInstance().getConnection();
	    	String currentPath = null;
			try {
				currentPath = new java.io.File(".").getCanonicalPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       	currentPath+="/src/reporting/commande/"+file;   
	    	Report.createReport(conn,currentPath,id,versement);
	    	
	    }
	    @FXML
	    void btnFermer(MouseEvent event) throws SQLException {
	    	connection.close();
	    	DynamiqueView.getInstance().closeStage(event);
	    }
	public void setTextFieled(Commande c,Connection conn) throws SQLException {
		conn.close();
		Integer idCmd=c.getId();
		txtDateLivraison.setText(c.getDate_livraison());
		txtQteTotale.setText(String.valueOf(c.getQte_totale()));
		txtVersement.setText(c.getVersement());
		txtMontantTotale.setText(c.getMontant());
		txtOrdreCmd.setText("#"+idCmd);	
		txtVersement.setText(c.getVersement());
		txtDate.setText(c.getDateCommande());
		Personne p =getClient(c.getId_client());
		txtNom.setText(p.getNom());
		txtPrenom.setText(p.getPrenom());
		txtAdresse.setText(p.getAdresse());
		txtNum.setText("+213 "+p.getNum_telephone());
		getItemsOfCommande(idCmd);
		LoadLignsToColumnOfTable1();
		LoadLignsToColumnOfTable2();
		LoadLignsToColumnOfTable3();
		LoadLignsToColumnOfTable4();
		LoadLignsToColumnOfTable5();
		LoadLignsToColumnOfTable6();
		LoadLignsToColumnOfTable67();
		getrupture(idCmd);
		getStylishOfColumn();
		connection.close();
		
	}
	private void getrupture(Integer id) throws SQLException {
		Double totale=0.0;
		query="select r.description,m.designation,ROUND(avg(cm1.prix_unitaire ),2) as prix_last,sum(r.qte_produit) as qte\r\n"
				+ "from list_commande l,rupture r,caisse_matiere cm1,matiere_premiere m  \r\n"
				+ "    where r.id_list=l.id_list and r.id_commande=l.id_commande   \r\n"
				+ "    and r.id_commande="+id+" and cm1.id_matiere=l.id_produit and l.id_produit=m.id_matiere \r\n"
				+ "    group by r.description,m.designation";
		statement=connection.prepareStatement(query);
		rs=statement.executeQuery();
		while(rs.next()) {
			Double prix=rs.getDouble("prix_last");
			Double qte=rs.getDouble("qte");
			Double montant=qte*prix;
			totale +=montant;
			Rupture r=new Rupture();
			r.setQte(qte);
			r.setProduit(rs.getString("designation"));
			r.setDescritpion(rs.getString("description"));
			r.setPrix_achat(prix+"DA");
			r.setMontant(montant+"DA");
			dataRupturer.add(r);
			
		}
		columnMontantretourTotale.setText(String.valueOf(totale+"DA") ); 
		tableRupture.setItems(dataRupturer);
	}
	private void getItemsOfCommande(Integer id) throws SQLException {
		getListOfCommande(id);
		getVersement(id);
	}
	private void getListOfCommande(Integer id) throws SQLException  {
		
		Double montant_article=0.0,montant_br=0.0,montant_incision=0.0,montant_sablage=0.0;		
		query="select l.*,m.designation,f1.activitie,f2.activitie as txtIncision,f3.activitie as txtSablage,f1.prix_unitaire as prix_br,f2.prix_unitaire as prix_incision,f3.prix_unitaire as prix_Sablage "
				+ "from list_commande l,matiere_premiere m,fonctionnalite f1,fonctionnalite f2,fonctionnalite f3 "
				+ " where m.id_matiere=l.id_produit and l.id_br=f1.id_type and f2.id_type=6 and f3.id_type=5 and id_commande="+id+" ";
		statement=connection.prepareStatement(query);
		rs=statement.executeQuery();
		while(rs.next()) {
			
			Double cout_article=view.changeFormatOfDouble(rs.getDouble("montant_article"));
			Double cout_br =view.changeFormatOfDouble(rs.getDouble("montant_br"));
			Double cout_incision=view.changeFormatOfDouble(rs.getDouble("montant_incision"));
			Double cout_sablage=view.changeFormatOfDouble(rs.getDouble("montant_sablage"));
			montant_incision +=cout_incision;
			montant_sablage +=cout_sablage;
			montant_br+=cout_br;
			montant_article+=cout_article;
			Double largeur=rs.getDouble("largeur");
			Double longueur=rs.getDouble("longueur");
			ListeCommande ls = new ListeCommande();
			ls.setId_list(rs.getInt("id_list"));
			ls.setMatiere_premier(rs.getString("designation"));
			ls.setType_br(rs.getString("activitie"));
			ls.setQte(rs.getDouble("quantite"));
			ls.setLargeur_m(largeur+"m");
			ls.setLongueur_m(longueur+"m");
			ls.setPrixArticle(rs.getDouble("prix_unitaire")+"DA");
			ls.setMontant_article(cout_article+"DA");
			ls.setType_br(rs.getString("activitie"));
			if(rs.getInt("br_left")==1) {
				ls.setLongueurBr_gauche(longueur+"m");
			}
			if(rs.getInt("br_right")==1) {
				ls.setLongueurBr_droit(longueur+"m");
			}
			
			if(rs.getInt("br_top")==1) {
				ls.setLargeurBr_top(largeur+"m");
			}
			if(rs.getInt("br_bottom")==1) {
				ls.setLargeurBr_bottom(largeur+"m");
			}
			ls.setPrix_br(rs.getDouble("prix_br")+"DA");
			ls.setMontant_br(cout_br+"DA");
			ls.setMontant_incision(cout_incision+"DA");
			ls.setMontant_sablage(cout_sablage+"DA");
			if(rs.getDouble("incision")!=0.0) {
				ls.setTitleIncision(rs.getString("txtIncision"));
				ls.setPrixIncision(rs.getDouble("prix_incision")+"DA");
				ls.setIncision_m(rs.getDouble("incision")+"m2");
			}
			if(rs.getDouble("sablage")!=0.0) {
				ls.setTitleSablage(rs.getString("txtSablage"));
				ls.setPrixSablage(rs.getString("prix_sablage"));
				ls.setSablage_m(rs.getString("sablage")+"m2");
			}
			
			dataArticle.add(ls);
			
		}
		txtTotaleArticle.setText(view.formatDouble(montant_article)+"DA");
		columnMontantTotaleBr.setText(view.formatDouble(montant_br)+"DA");
		columnMontantTotaleComplement.setText(view.formatDouble(montant_incision)+"DA");
		columnMontantTotaleComplement1.setText(view.formatDouble(montant_sablage)+"DA");
		
		tableArticle.setItems(dataArticle);
		tableBr.setItems(dataArticle);
		tableComplement.setItems(dataArticle);
		tableComplement1.setItems(dataArticle);
		
	}
	private Personne getClient(Integer id) throws SQLException {
		Personne p=new Personne() ;
		 query="select * from personnes where id_personne="+id+"";
		statement=connection.prepareStatement(query);
		rs=statement.executeQuery(); 
		if(rs.next()) {
			p.setAdresse(rs.getString("adresse"));
			p.setNom(rs.getString("nom"));
			p.setPrenom(rs.getString("prenom"));
			p.setNum_telephone(rs.getInt("num_telephone"));
		}
		return p;
	}
	private void getVersement(Integer id) throws SQLException {
		query="select * from versement_cmd where id_etranger="+id+"";
		statement=connection.prepareStatement(query);
		rs=statement.executeQuery();
		while(rs.next()) {
			Versement v=new Versement ();
			v.setVersement(rs.getDouble("versement")+"DA");
			v.setDate_versement(rs.getString("date_versement"));
			dataVersement.add(v);
		}
		tableVersement.setItems(dataVersement);
	}
	private void LoadLignsToColumnOfTable1() {
		columnIdListe.setCellValueFactory(new PropertyValueFactory<ListeCommande,Integer>("id_list"));
		columnMatiere1.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("matiere_premier"));
		columnQte.setCellValueFactory(new PropertyValueFactory<ListeCommande,Double>("qte"));
		columnLargeur.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("largeur_m"));
		columnLongueur.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("longueur_m"));
		columnPrixArticle.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("prixArticle"));
		columnMontantArticle.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("montant_article"));
	}
	private void LoadLignsToColumnOfTable2() {
		columnMatiere2.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("matiere_premier"));
		columnTypeBr.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("Type_br"));
		columnGauche.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("longueurBr_gauche"));
		columnDroite.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("longueurBr_droit"));
		columnHaut.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("largeurBr_top"));
		columnDessous.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("largeurBr_bottom"));
		columnPrixBr.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("prix_br"));
		columnMontantBr.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("montant_br"));
	}
	private void LoadLignsToColumnOfTable3() {
		columnMatiere3.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("matiere_premier"));
		columnTypeBr.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("Type_br"));
		columnGauche.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("longueurBr_gauche"));
		columnDroite.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("longueurBr_droit"));
		columnHaut.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("largeurBr_top"));
		columnDessous.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("largeurBr_bottom"));
		columnPrixBr.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("prix_br"));
		columnMontantBr.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("montant_br"));
	}
	private void LoadLignsToColumnOfTable4() {
		columnMatiere3.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("matiere_premier"));
		columnTypeComplement.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("titleIncision"));
		columnMetrage.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("incision_m"));
		columnPrixIncision.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("prixIncision"));
		columnMontantComplement.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("montant_incision"));
	}
	private void LoadLignsToColumnOfTable5() {
		columnMatiere5.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("matiere_premier"));
		columnTypeComplement1.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("titleSablage"));
		columnMetrage1.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("sablage_m"));
		columnPrixSablage.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("prixSablage"));
		columnMontantComplement1.setCellValueFactory(new PropertyValueFactory<ListeCommande,String>("montant_sablage"));
	}
	private void LoadLignsToColumnOfTable6() {
		columnVersement.setCellValueFactory(new PropertyValueFactory<Versement,String>("versement"));
		columnDAteVersement.setCellValueFactory(new PropertyValueFactory<Versement,String>("date_versement"));
	}
	private void LoadLignsToColumnOfTable67() {
		columnQteRetour.setCellValueFactory(new PropertyValueFactory<Rupture,Double>("qte"));
		columnMontantretour.setCellValueFactory(new PropertyValueFactory<Rupture,String>("montant"));
		columnPrixRetour.setCellValueFactory(new PropertyValueFactory<Rupture,String>("prix_achat"));
		columnMatiere4.setCellValueFactory(new PropertyValueFactory<Rupture,String>("produit"));
		
	}
	private void getStylishOfColumn() {
	   	 String style="-fx-font-size:12px;-fx-alignment:center;";
	   	columnMatiere1.setStyle(style);
	   	columnMatiere2.setStyle(style);
	   	columnMatiere3.setStyle(style);
	   	columnMatiere4.setStyle(style);
	   	columnMatiere5.setStyle(style);
	   	columnQte.setStyle(style);
	   	columnQteRetour.setStyle(style);
	   	columnLargeur.setStyle(style);
	   	columnLongueur.setStyle(style);
	   	columnTypeBr.setStyle(style);
	   	columnHaut.setStyle(style);
	   	columnGauche.setStyle(style);
	   	columnDroite.setStyle(style);
	   	columnDessous.setStyle(style);
	   	columnTypeComplement.setStyle(style);
	   	columnMetrage.setStyle(style);
	   	columnMetrage1.setStyle(style);
	   	columnTypeComplement1.setStyle(style);
	   	columnDAteVersement.setStyle(style);
	   	style +=";-fx-font-weight:bold";
	   	columnPrixArticle.setStyle(style);
	   	columnPrixBr.setStyle(style);
	   	columnPrixIncision.setStyle(style);
	   	columnPrixSablage.setStyle(style);
	   	columnPrixRetour.setStyle(style);
	   	columnMontantArticle.setStyle(style);
	   	columnMontantBr.setStyle(style);
	   	columnMontantComplement.setStyle(style);
	   	columnMontantComplement1.setStyle(style);
	   	columnMontantretour.setStyle(style);
	   	columnVersement.setStyle(style);
	   	
	   	
	   }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
