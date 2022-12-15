package controller.vente;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import controller.humaine.paiement.ConsulterController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.PersonneCompte;

public class RapportController implements Initializable{


    @FXML
    private BarChart<Object,Double> barChart2;

    @FXML
    private LineChart<Object,Double> lineChart;

    @FXML
    private BarChart<Object,Double> barChart4;

    @FXML
    private Text txtBenifice;

    @FXML
    private Text txtChiffreAffaire;

    @FXML
    private Text txtQteVente;

    @FXML
    private Text txtVersementTotale;
    @FXML
    private TableColumn<PersonneCompte, String> columnAchat;

    @FXML
    private TableColumn<PersonneCompte, String> columnNom;

    @FXML
    private TableColumn<PersonneCompte, String> columnPrenom;

    @FXML
    private TableColumn<PersonneCompte, String> columnStatus;

    @FXML
    private PieChart pieChart;
    private DynamiqueView view =new DynamiqueView();
    @FXML
    private TableView<PersonneCompte> table;
    private String query=""; 
    private Connection connection=DbConnect.getInstance().getConnection();
    private Double revenuEmploye=0.0,RevenuTotaleEmploye=0.0,ChiffreAffaire=0.0,benificeTotale=0.0,qteVente=0.0;
    private ResultSet rs;
    private PreparedStatement st;
    private int year=ConsulterController.getInstance().getThisYear();
    private ArrayList<Double> array=new ArrayList<>();
    private ObservableList<PieChart.Data> piechartData=FXCollections.observableArrayList();
    private ObservableList<PersonneCompte> clientData=FXCollections.observableArrayList();
    private controller.humaine.RapportController instance=controller.humaine.RapportController.getInstance();
    private Hashtable<String, Double> getchiffreDaffaireByMois() throws SQLException {
    	Hashtable<String, Double> values =new Hashtable<>();
    	query="select ROUND(sum(l.montant_article+l.montant_br+l.montant_incision+l.montant_sablage),2) as chiffreDaffaire ,date_format(c.date_commande,'%Y-%m') as month \r\n"
    			+ "from list_commande l,commande c \r\n"
    			+ "where l.id_commande=c.id_commande and date_format(c.date_commande,'%Y')='"+year+"'\r\n"
    			+ "GROUP BY month";
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		Double montant=rs.getDouble("chiffreDaffaire");
    		values.put(rs.getString("month"),montant);
    		
    	}
    	return values;
    }
    private void getCA() throws SQLException{
       Hashtable<String, Double> values= getchiffreDaffaireByMois();
       values.forEach((k,v)->{
           ChiffreAffaire+=v;
       });
    }
    private Double SumOfArray() {
		Double sum=0.0;
		for (Double integer : array) {
			sum +=integer;
		}
		  return sum;
	}
    private void getProductsByQteVente() throws SQLException {
    	Hashtable<Double,String> values= new Hashtable<Double,String>();
    	Double sum=0.0,x=0.0;
    	Double pourcentage=0.0;
    	query="select m.designation,ROUND(sum(l.quantite*l.longueur*l.largeur),2) as qteVente\r\n"
    			+ "from list_commande l,commande c,matiere_premiere m\r\n"
    			+ "where l.id_commande=c.id_commande and l.id_produit=m.id_matiere\r\n"
    			+ "GROUP BY m.designation";
    	PreparedStatement st=connection.prepareStatement(query);
    	ResultSet rs=st.executeQuery();
    	while(rs.next()) {
    		Double qte=rs.getDouble("qteVente");
    		array.add(qte);
    		values.put(qte, rs.getString("designation"));
    		qteVente+=qte;
    	}
    	Enumeration<Double> absences=values.keys();
    	sum=SumOfArray();   
    	while (absences.hasMoreElements()) {
    		x=absences.nextElement();
    		pourcentage=(double) ((x*100)/sum);
    		piechartData.add(
    				new PieChart.Data(values.get(x), pourcentage)
    				);	
    	}
    	pieChart.setLegendSide(Side.LEFT);		
    	pieChart.setData(piechartData);
    	pieChart.setTitle("Les quantites vente par produit dans l'année "+year );
    }
    private void barChart2() throws SQLException {
    	  XYChart.Series<Object,Double> series1=instance.barchart(connection, getchiffreDaffaireByMois(), "Chiffre d'affaire");
    	  barChart2.setTitle("Chiffre d'affaire par mois dans l'année "+year);
    	  barChart2.getData().add(series1);
    }
    private void barChart4() throws SQLException {
    	XYChart.Series<Object,Double> series1=instance.barchart(connection, getQteRemiseBymonth(), "Qte Rupture");
    	barChart4.setTitle("Les Qtes Ruptures par mois dans l'année "+year);
    	barChart4.getData().add(series1);
    }
    private Hashtable<String, Double> getQteRemiseBymonth() throws SQLException {
    	Hashtable<String, Double> values =new Hashtable<>();
    	query="select sum(r.qte_produit) as qteRupture,date_format(r.date_rupture,'%Y-%m') as month \r\n"
    			+ "from rupture r "
    			+ "where date_format(r.date_rupture,'%Y')='"+year+"' \r\n"
    			+ "GROUP BY month";
      st=connection.prepareStatement(query);
      rs=st.executeQuery();
      while(rs.next()) {
    	  values.put(rs.getString("month"), rs.getDouble("qteRupture"));
      }
      return values;
    }
    Double r=0.0;
    private void getqteRupture() throws SQLException{
        Hashtable<String, Double> values=getQteRemiseBymonth();
        values.forEach((k,v)->{
            r+=v;
            
        });
        txtBenifice.setText(r+"m2");
        
    }
    private void getNbrVenteByFonctions() throws SQLException {
    	XYChart.Series<Object,Double> series1=new XYChart.Series<>();
    	query="select f.activitie,count(l.id_list) as nbrvente\r\n"
    			+ "from list_commande l,fonctionnalite f\r\n"
    			+ "where l.id_br=f.id_type\r\n"
    			+ "GROUP BY f.activitie \r\n"
    			+ "UNION \r\n"
    			+ "select 'Incision',count(l.id_list) as nbrvente\r\n"
    			+ "from list_commande l\r\n"
    			+ "where l.incision !=0 \r\n"
    			+ "UNION \r\n"
    			+ "select 'Sablage',count(l.id_list) as nbrvente\r\n"
    			+ "from list_commande l\r\n"
    			+ "where l.sablage !=0 ";
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		series1.getData().add(new XYChart.Data<Object,Double>(rs.getString("activitie"),rs.getDouble("nbrvente")));
    	}
    	
    	lineChart.setTitle("Nombres de ventes par fonctions ");
    	lineChart.getData().add(series1);
    }
    private void getStylishOfColumn() {
		String style = "-fx-font-size:12px;-fx-alignment:center";
		columnNom.setStyle(style);
		columnPrenom.setStyle(style);
		columnStatus.setStyle(style);
		style += ";-fx-font-weight:bold";
		columnAchat.setStyle(style);
	}
    private Double getVersement() throws SQLException {
    	Double value = null;
    	query="select sum(c.versement) as cout \r\n"
    			+ "from versement_cmd c";
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();
    	if(rs.next()) {
    		value=rs.getDouble("cout");
    	}
    	return value;
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			barChart2();
			barChart4();
			getProductsByQteVente();
			getNbrVenteByFonctions();
			loadTableOfClients();
                        getCA();
                        getqteRupture();

			txtVersementTotale.setText(getVersement()+"DA");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getStylishOfColumn();
        
		String value=view.changeFormatOfDouble(ChiffreAffaire)+"DA";
		
		txtChiffreAffaire.setText(value);
		txtQteVente.setText(view.changeFormatOfDouble(qteVente)+"m2");
		
	}
	private Double getLimitRemise() throws SQLException {
		Double remise =null; 
		query="select remise_client from parametres";
		st= connection.prepareStatement(query);
		rs=st.executeQuery();
		if(rs.next()) {
			remise=rs.getDouble("remise_client");	
		}
		return remise;
	}
	private void getclientByRemise() throws SQLException {
		Double remise=getLimitRemise();
		query="select p.nom,p.prenom,sum(l.montant_article+l.montant_br+l.montant_incision+l.montant_sablage) as ventes\r\n"
				+ "from list_commande l,personnes p,commande c \r\n"
				+ "where l.id_commande=c.id_commande and p.id_personne=c.id_personne\r\n"
				+ "GROUP BY p.nom,p.prenom\r\n"
				+ "HAVING ventes > "+remise+"";
		 st=connection.prepareStatement(query);
		 rs=st.executeQuery();
		 PersonneCompte p=null;
		 while(rs.next()) {
			 p=new PersonneCompte();
			 p.setClient(rs.getString("nom"));
			 p.setPrenom(rs.getString("prenom"));
			 p.setMontant(rs.getDouble("ventes")+"DA");
				Image set=new Image(getClass().getResourceAsStream("/images/vente/fin.png"));
	    		ImageView img=new ImageView(set);
	    		img.setFitHeight(30);
	    		img.setFitWidth(50);
	    		p.setStatus(img);
			 clientData.add(p);
		 }
		 table.setItems(clientData);
	}
//	private void getBenificeByMonth() throws SQLException {
//		Double benefice	;
//		RevenuTotaleEmploye=getRevenuOfEmploye();
//		Hashtable<String, Double> values=getChargeAndAchatbyMonth();
//		Hashtable<String, Double> valuesCA=getchiffreDaffaireByMois();
//		 LinkedHashMap<String, String> valuesMois=controller.stock.RapportController.getInstance().getValuesOfMois();
//		   XYChart.Series<Object,Double> series1=new XYChart.Series<>();
//			series1.setName("Bénifice");
//                        
//			Set<Entry<String, String>> set=valuesMois.entrySet();
//			Iterator<Entry<String, String>> i =set.iterator();
//			while(i.hasNext()) {
//				@SuppressWarnings("rawtypes")
//				Map.Entry me=(Map.Entry)i.next();
//				if(values.containsKey(me.getKey())) {
//					if(valuesCA.containsKey(me.getKey())) {
//					benefice=valuesCA.get(me.getKey())-values.get(me.getKey())-RevenuTotaleEmploye;
//					series1.getData().add(new XYChart.Data<Object,Double>(me.getValue(),benefice));
//					
//					}else {
//						benefice=-1*(values.get(me.getKey())-RevenuTotaleEmploye);
//						series1.getData().add(new XYChart.Data<Object,Double>(me.getValue(),benefice));
//						
//					}
//					
//				}else {
//					if(valuesCA.containsKey(me.getKey())) {
//						benefice=valuesCA.get(me.getKey())-RevenuTotaleEmploye;
//						series1.getData().add(new XYChart.Data<Object,Double>(me.getValue(),benefice));
//						
//						}else {
//							benefice=-RevenuTotaleEmploye;
//							series1.getData().add(new XYChart.Data<Object,Double>(me.getValue(),benefice));
//							
//						}
//					
//				}
//			}
//			barChart1.getData().add(series1);
//                         	barChart1.setTitle("Les Bénifices par mois dans l'année "+year );
//		
//	}
	private Double getRevenuOfEmploye() throws SQLException {
		query="select sum(e.revenu*4) as totale from  employes e";
		st=connection.prepareStatement(query);
		rs=st.executeQuery();
		while(rs.next()) {
			revenuEmploye+=rs.getDouble("totale");
		}
		return revenuEmploye;
	}
	private Hashtable<String, Double> getChargeAndAchatbyMonth() throws SQLException {
		Hashtable<String, Double> values =new Hashtable<>();
		query=" select sum(ch.cout_charge) as cout,date_format(ch.date_charge,'%Y-%m') as month \r\n"
				+ "from charges ch   "
				+ "where date_format(ch.date_charge,'%Y')='"+year+"'\r\n"
				+ "GROUP BY month\r\n"
				+ "UNION \r\n"
				+ "select sum(cm.nbr_caisse*cm.longueur*cm.largeur*cm.prix_unitaire) as cout,date_format(a.date_achat,'%Y-%m') as month   \r\n"
				+ "FROM caisse_matiere cm,achat a \r\n"
				+ "WHERE cm.id_achat=a.id_achat and date_format(a.date_achat,'%Y')='"+year+"' \r\n"
				+ "GROUP BY month "
				+ "UNION \r\n"
				+ "select sum(a.livraison) as cout,date_format(a.date_achat,'%Y-%m') as month     \r\n"
				+ "from achat a "
				+ "where date_format(a.date_achat,'%Y')='"+year+"'"
				+ "GROUP BY month \r\n"
				+ "UNION \r\n"
				+ "select sum(ca.qte*ca.prix_unitaire) as cout,date_format(a.date_achat,'%Y-%m') as month   \r\n"
				+ "FROM caisse_accessoire ca,achat a \r\n"
				+ "WHERE ca.id_achat=a.id_achat and date_format(a.date_achat,'%Y')='"+year+"' \r\n"
				+ "GROUP BY month ";
		st=connection.prepareStatement(query);
		rs=st.executeQuery();
		while(rs.next()) {
			values.put(rs.getString("month"), rs.getDouble("cout"));
		}
		return values;
	}
	private void loadTableOfClients() throws SQLException {
		getclientByRemise();
		columnNom.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("client")); 
		columnPrenom.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("prenom")); 
		columnAchat.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("montant")); 
		columnStatus.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("status")); 
		
	}
	
}
