package controller.fourClient;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import controller.humaine.paiement.ConsulterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.PersonneCompte;

public class RapportController implements Initializable {

    @FXML
    private TextField InputFournisseur;
    @FXML
    private TextField inputClient;

    @FXML
    private BarChart<String, Integer> barCharFour1;

    @FXML
    private BarChart<String, Double> barCharFour2;

    @FXML
    private BarChart<String, Integer> barChartClient1;

    @FXML
    private BarChart<String, Integer> barChartClient2;

    @FXML
    private TableColumn<PersonneCompte, String> columnClient;

    @FXML
    private TableColumn<PersonneCompte, String> columnFourniseur;

    @FXML
    private TableColumn<PersonneCompte, String> columnMontant;

    @FXML
    private TableColumn<PersonneCompte, String> columnMontant1;

    @FXML
    private TableColumn<PersonneCompte, String> columnReste;

    @FXML
    private TableColumn<PersonneCompte, String> columnReste1;

    @FXML
    private TableColumn<PersonneCompte, String> columnVersement1;
    
    @FXML
    private TableColumn<PersonneCompte, String> columnVersement;
    
    @FXML
    private PieChart pieChart;

    @FXML
    private TableView<PersonneCompte> tablFournisseur;

    @FXML
    private TableView<PersonneCompte> tableClient;

    @FXML
    private Text txrCrediTotale;

    @FXML
    private Text txtCreditClient;

    @FXML
    private Text txtNbrClient;

    @FXML
    private Text txtNbrFour;
    private ArrayList<Double> array1=new ArrayList<>();
    private Connection connection=DbConnect.getInstance().getConnection();
    private ObservableList<PieChart.Data> piechartData=FXCollections.observableArrayList();
    private ObservableList<PersonneCompte> clientData=FXCollections.observableArrayList();
    private ObservableList<PersonneCompte> FourData=FXCollections.observableArrayList();
    private PreparedStatement st;
    private ResultSet rs;
    private String query;
    private Double Credit=0.0,creditFour=0.0;
    private DynamiqueView view =new DynamiqueView();
    @FXML
    private TableColumn<PersonneCompte, String> columnSolde;

    @FXML
    private TableColumn<PersonneCompte, String> columnSolde1;
    
    private Map<String, Double> map = new HashMap<String, Double>();
    private int nbrClients=0,nbrFournisseurs=0;
    private int year=ConsulterController.getInstance().getThisYear();
    private void getClientByNbrProducts() throws SQLException {
    	XYChart.Series<String,Integer> series =new XYChart.Series<>();
    	query=" select CONCAT(p.nom,' ',p.prenom) as client,count(l.id_produit) as nbrProduit   \r\n"
    			+ "from personnes p,commande c ,list_commande l   \r\n"
    			+ "where p.id_type=0 and c.id_personne=p.id_personne and c.id_commande=l.id_commande  \r\n"
    			+ "group by client \r\n"
    			+ "ORDER BY nbrProduit DESC\r\n"
    			+ "LIMIT 12";
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		series.getData().add(new XYChart.Data<String, Integer>(rs.getString("client"),rs.getInt("nbrProduit")));
    	}
    	barChartClient1.setTitle("Les 12 clients plus acheter des produits dans l'année "+year);
    	barChartClient1.getData().add(series);
    	
    }
    private void getFourByNbrProducts() throws SQLException {
    	XYChart.Series<String,Integer> series =new XYChart.Series<>();
    	query=" select sum(nbrProduit) as nbr,fournisseur  from ( select CONCAT(p.nom,' ',p.prenom) as fournisseur,count(cm.id_matiere) as nbrProduit   \r\n"
    			+ "from personnes p,achat a,caisse_matiere cm   \r\n"
    			+ "where p.id_type=1 and a.id_fournisseur=p.id_personne and a.id_achat=cm.id_achat\r\n"
    			+ "  group by fournisseur  \r\n"
    			+ "      \r\n"
    			+ "UNION ALL \r\n"
    			+ "select CONCAT(p.nom,' ',p.prenom) as fournisseur,count(cm.id_accessoire) as nbrProduit   \r\n"
    			+ "from personnes p,achat a,caisse_accessoire cm   \r\n"
    			+ "where p.id_type=1 and a.id_fournisseur=p.id_personne and a.id_achat=cm.id_achat\r\n"
    			+ "group by fournisseur   \r\n"
    			+ " ) t \r\n"
    			+ " group by fournisseur\r\n"
    			+ " ORDER BY nbr DESC\r\n"
    			+ "  LIMIT 12";
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		series.getData().add(new XYChart.Data<String, Integer>(rs.getString("fournisseur"),rs.getInt("nbr")));
    	}
    	barCharFour1.setTitle("Les 12 Fournisseurs qui on a acheter plus leurs produits" );
    	barCharFour1.getData().add(series);
    	
    }
    private Double SumOfArray1() {
    	Double sum=0.0;
    	for (Double integer : array1) {
    		sum +=integer;
    	}
    	return sum;
    }
    private void loadLignClientFromDBB() throws SQLException {
    	query="select p.id_personne,p.solde as soldes,CONCAT(p.nom,' ',p.prenom) as client,p.solde,sum(l.montant_article+l.montant_br+l.montant_incision+l.montant_sablage) as montant \r\n"
    			+ "from personnes p,commande c,list_commande l\r\n"
    			+ "where p.id_personne=c.id_personne and c.id_commande=l.id_commande \r\n"
    			+ "GROUP BY p.id_personne,client,soldes";
    	st= connection.prepareStatement(query);
    	rs=st.executeQuery();
    	
    	while(rs.next()) {
    		PersonneCompte client=new PersonneCompte(); 
    		client.setClient(rs.getString("client"));
    		Integer id=rs.getInt("id_personne");
    		Double solde=rs.getDouble("soldes");
    		Double montant=rs.getDouble("montant");
    		Credit+=montant;
    		Double verse=getVersmentOfClient(id);
    		Double reste =solde+montant-verse;
    		client.setSolde(solde+"DA");
    		client.setVersement(view.formatDouble(verse)+"DA");
    		client.setMontant(montant+"DA");
    		client.setReste(reste+"DA");
    		clientData.add(client);
    	}
    	tableClient.setItems(clientData);
    	FilterByClient();	
    }
    private void getPersonnes() throws SQLException {
    	query="select p.id_type as type ,count(*) as nbr from personnes p \r\n"
    			+ "GROUP BY p.id_type";
    	 st=connection.prepareStatement(query);
    	 rs=st.executeQuery();
    	 while(rs.next()) {
    		 if(rs.getInt("type")==0) {
    			 nbrClients+=rs.getInt("nbr"); 
    		 }else {
    			 nbrFournisseurs+=rs.getInt("nbr"); 
    		 }
    	 }
    	
    	 txtNbrClient.setText(String.valueOf(nbrClients));
    	 txtNbrFour.setText(String.valueOf(nbrFournisseurs));
    }
	public void FilterByClient() {
		FilteredList<PersonneCompte> filteredData = new FilteredList<>(clientData, b -> true);
		inputClient.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productSearchModel -> {
				if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if (productSearchModel.getClient().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				} else {
					return false;
				}

			});
		});
		SortedList<PersonneCompte> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableClient.comparatorProperty());
		tableClient.setItems(sortedData);

	}
	public void FilterByFour() {
		FilteredList<PersonneCompte> filteredData = new FilteredList<>(FourData, b -> true);
		InputFournisseur.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productSearchModel -> {
				if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if (productSearchModel.getClient().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				} else {
					return false;
				}
				
			});
		});
		SortedList<PersonneCompte> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablFournisseur.comparatorProperty());
		tablFournisseur.setItems(sortedData);
		
	}
	private void loadLignClientToColumns() throws SQLException {
    	loadLignClientFromDBB();
    	columnClient.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("client"));
    	columnMontant.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("montant"));
    	columnVersement.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("versement"));
    	columnReste.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("reste"));
    	columnSolde.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("solde"));
    	
    }
	private void loadLignFourToColumns() throws SQLException {
		getFourByCredit();
		columnFourniseur.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("client"));
		columnMontant1.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("montant"));
		columnVersement1.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("versement"));
		columnReste1.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("reste"));
		columnSolde1.setCellValueFactory(new PropertyValueFactory<PersonneCompte,String>("solde"));
		
	}
	private void getFourByCredit() throws SQLException {
		query="select sum(montant) as montanTotale,id,soldes,fournisseur  "
				+ "from ( select p.id_personne as id,p.solde as soldes,CONCAT(p.nom,' ',p.prenom) as fournisseur,ROUND(sum(cm.nbr_caisse*cm.largeur*cm.longueur*cm.prix_unitaire),2) as montant  \r\n"
				+ "from personnes p,achat a,caisse_matiere cm   \r\n"
				+ "where p.id_type=1 and a.id_fournisseur=p.id_personne and a.id_achat=cm.id_achat\r\n"
				+ "       group by id,fournisseur,soldes  \r\n"
				+ "      \r\n"
				+ "UNION ALL \r\n"
				+ "select p.id_personne as id,p.solde as soldes,CONCAT(p.nom,' ',p.prenom) as fournisseur,ROUND(sum(cm.qte*cm.prix_unitaire),2) as montant   \r\n"
				+ "from personnes p,achat a,caisse_accessoire cm   \r\n"
				+ "where p.id_type=1 and a.id_fournisseur=p.id_personne and a.id_achat=cm.id_achat\r\n"
				+ "group by id,fournisseur,soldes   \r\n"
				+ " ) t \r\n"
				+ " group by id,fournisseur,soldes\r\n"
				+ " ";
		st=connection.prepareStatement(query);
		rs=st.executeQuery();
		PersonneCompte p=null;
		while(rs.next()){
			p=new PersonneCompte();
			int id =rs.getInt("id");
			String four=rs.getString("fournisseur");
			Double montant=rs.getDouble("montanTotale");
			Double solde=rs.getDouble("soldes");
			p.setClient(four);
			p.setSolde(solde+"DA");
			p.setMontant(montant+"DA");
			Double verser=getVersmentOfFourn(id);
			p.setVersement(verser+"DA");
			Double rest=solde+montant-verser;
			creditFour+=rest;
			p.setReste(view.formatDouble(rest)+"DA");
			map.put(four,rest);
			FourData.add(p);
			
		}
		tablFournisseur.setItems(FourData);
		FilterByFour();
	}
	private void getFourByMostCredit() {
		XYChart.Series<String,Double> series =new XYChart.Series<>();
		series.setName("Fournisseur");
		List<Entry<String, Double>> greatest = findGreatest(map, 5);
        for (Entry<String, Double> entry : greatest)
        {
        	series.getData().add(new XYChart.Data<String,Double>(entry.getKey(),entry.getValue()));
        	
        }
        barCharFour2.setTitle("Les 5 Fournisseures qui ont plus credit avec Nous dans L'année 2022");
        barCharFour2.getData().add(series);
	}
	   private static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
       findGreatest(Map<K, V> map, int n)
   {
       Comparator<? super Entry<K, V>> comparator = 
           new Comparator<Entry<K, V>>()
       {
           @Override
           public int compare(Entry<K, V> e0, Entry<K, V> e1)
           {
               V v0 = e0.getValue();
               V v1 = e1.getValue();
               return v0.compareTo(v1);
           }
       };
       PriorityQueue<Entry<K, V>> highest = 
           new PriorityQueue<Entry<K,V>>(n, comparator);
       for (Entry<K, V> entry : map.entrySet())
       {
           highest.offer(entry);
           while (highest.size() > n)
           {
               highest.poll();
           }
       }

       List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
       while (highest.size() > 0)
       {
           result.add(highest.poll());
       }
       return result;
   }
	private void getStylishOfColumn() {
		String style = "-fx-font-size:12px;-fx-alignment:center";
		columnClient.setStyle(style);
		columnFourniseur.setStyle(style);
		style += ";-fx-font-weight:bold";
		columnMontant.setStyle(style);
		columnReste.setStyle(style);
		columnVersement.setStyle(style);
		columnMontant1.setStyle(style);
		columnReste1.setStyle(style);
		columnSolde.setStyle(style);
		columnSolde1.setStyle(style);
		columnVersement1.setStyle(style);
	}
    
    private Double getVersmentOfClient(Integer id) throws SQLException {
    	Double versement=0.0;
    	query="select sum(v.versement) as verse \r\n"
    			+ "from versement_cmd v,commande c\r\n"
    			+ "where c.id_personne="+id+" and c.id_commande=v.id_etranger";
    	PreparedStatement stat=connection.prepareStatement(query);
    	ResultSet set=stat.executeQuery();
    	if(set.next()) {
    		versement=set.getDouble("verse");
    	}
    	return versement;
    }
    private Double getVersmentOfFourn(Integer id) throws SQLException {
    	Double versement=0.0;
    	query="select sum(v.versement) as verse  \r\n"
    			+ "from versement_achat v,achat a \r\n"
    			+ "where a.id_fournisseur="+id+" and a.id_achat=v.id_etranger";
    	PreparedStatement stat=connection.prepareStatement(query);
    	ResultSet set=stat.executeQuery();
    	if(set.next()) {
    		versement=set.getDouble("verse");
    	}
    	return versement;
    }
    private void getClientsByRemise() throws SQLException {
   	 Hashtable<Double,String> values= new Hashtable<Double,String>();
	   	 double sum=0.0,x=0.0,pourcentage=0.0;
   	   XYChart.Series<String,Double> series1=new XYChart.Series<>();
   		series1.setName("Montant d'achat");
   		
   	query="select  p.id_personne,CONCAT(p.nom,' ',p.prenom) as client,sum(r.qte_produit) as qte\r\n"
   			+ "from commande c ,personnes p,rupture r\r\n"
   			+ "where c.id_personne=p.id_personne and c.id_commande=r.id_commande\r\n"
   			+ "GROUP BY client \r\n"
   			+ "ORDER by qte DESC\r\n"
   			+ "LIMIT 5";
   	st=connection.prepareStatement(query);
   	rs=st.executeQuery();
   	while(rs.next()) {
   		array1.add(rs.getDouble("qte"));
			values.put(rs.getDouble("qte"), rs.getString("client"));
   	}
   	Enumeration<Double> absences=values.keys();
		
		sum=SumOfArray1();   
       while (absences.hasMoreElements()) {
      	  x=absences.nextElement();
            pourcentage=((x*100)/sum);
            piechartData.add(
 				new PieChart.Data(values.get(x), pourcentage)
 				);	
		 }
       
		pieChart.setLegendSide(Side.LEFT);		
		pieChart.setData(piechartData);
		pieChart.setTitle("Les 5 clients qui ont faire plus remise dans l'année "+year );

   }
    private Double getRetard() throws SQLException {
		Double remise =null; 
		query="select retard_commande from parametres";
		st= connection.prepareStatement(query);
		rs=st.executeQuery();
		if(rs.next()) {
			remise=rs.getDouble("retard_commande");	
		}
		return remise;
	}
    private void getClientByNbrCommandeNonPaye() throws SQLException {
    	Double retard=getRetard();
    	XYChart.Series<String,Integer> series =new XYChart.Series<>();
    	query=" select CONCAT(p.nom,' ',p.prenom) as client,count(c.id_commande) as nbrCommande\r\n"
    			+ "from commande c ,versement_cmd v,personnes p \r\n"
    			+ "where c.id_commande=v.id_etranger and c.id_personne=p.id_personne and DATEDIFF(v.date_versement,c.date_livraison) >"+retard+"  and \r\n"
    			+ "v.date_versement =( \r\n"
    			+ "select max(v.date_versement) \r\n"
    			+ "from versement_cmd v \r\n"
    			+ "where v.id_etranger=c.id_commande  \r\n"
    			+ ") \r\n"
    			+ "GROUP BY client LIMIT 12";
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		series.getData().add(new XYChart.Data<String, Integer>(rs.getString("client"),rs.getInt("nbrCommande")));
    	}
    	barChartClient2.setTitle("Les 12 clients plus retard à payer les commandes dans l'année "+year);
    	barChartClient2.getData().add(series);
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getStylishOfColumn();
		try {
			getPersonnes();
			getClientByNbrProducts();
			getClientByNbrCommandeNonPaye();
			getClientsByRemise();
			loadLignClientToColumns();
			loadLignFourToColumns();
			getFourByNbrProducts();
			getFourByMostCredit();
			txtCreditClient.setText(String.valueOf(view.formatDouble(Credit)));
			txrCrediTotale.setText(String.valueOf( view.formatDouble(creditFour)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
