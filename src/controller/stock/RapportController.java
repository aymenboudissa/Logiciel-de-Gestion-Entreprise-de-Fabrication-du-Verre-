
package controller.stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Matiere;
import model.Piece;

public class RapportController implements Initializable {

    @FXML
    private TableView<Matiere> TableMatiere;
    @FXML
    private TableView<Piece> tablePiece;
    @FXML
    private BarChart<Object, Double> barChart1;

    @FXML
    private BarChart<Object, Double> barChart2;

    @FXML
    private TableColumn<Matiere, String> columnMatiere;

    @FXML
    private TableColumn<Piece, String> columnPiece;

    @FXML
    private TableColumn<Piece, Double> qteConsommation2;

    @FXML
    private TableColumn<Matiere, String> qteCretique1;

    @FXML
    private TableColumn<Piece,Double> qteCretique2;

    @FXML
    private TableColumn<Matiere,String> qteEntree1;

    @FXML
    private TableColumn<Piece,Double> qteEntree2;

    @FXML
    private TableColumn<Matiere,String> qteFinale1;

    @FXML
    private TableColumn<Piece,Double> qteFinale2;

    @FXML
    private TableColumn<Matiere,String> qteInitiale1;

    @FXML
    private TableColumn<Piece,Double> qteInitiale2;

    @FXML
    private TableColumn<Matiere,String> qteSortir1;

    @FXML
    private TableColumn<Matiere,ImageView> status1;
    @FXML
    private TableColumn<Piece, ImageView> status2;
    private Connection connection=DbConnect.getInstance().getConnection();
    private String query;
    private ResultSet rs;
    private final String srcPhoto="/images/stock/";
    private PreparedStatement st;
    private ObservableList<Matiere> dataMatiere=FXCollections.observableArrayList();
    private ObservableList<Piece> dataPiece=FXCollections.observableArrayList();
    private DynamiqueView dynamique =new DynamiqueView();
    private int year=ConsulterController.getInstance().getThisYear();
    
    public static RapportController getInstance() {
    	return new RapportController();
    }
    private void getMatieres() throws SQLException {
    	String photo=null;
    	Hashtable<Integer, Double> values=getQteSortie();
    	query="select m.id_matiere,m.designation,m.stock_initiale,m.stock_cretique,ROUND(sum(cm.nbr_caisse*cm.largeur*cm.longueur),2) as qte_entree \r\n"
     	+ "from matiere_premiere m   "
     	+ " LEFT JOIN \r\n"
     	+ "caisse_matiere cm ON m.id_matiere=cm.id_matiere \r\n"
     	+ "group by m.id_matiere,m.designation,m.stock_initiale,m.stock_cretique\r\n"
     	+ "ORDER by m.id_matiere ASC";
     	st= connection.prepareStatement(query);
      rs=st.executeQuery();
      while(rs.next()) {
    	  Double qteSortir=values.get(rs.getInt("id_matiere"));
    	  Double qteEntree=rs.getDouble("qte_entree");
    	  Double stockInitiale=rs.getDouble("stock_initiale");
    	  Double qteFinale=stockInitiale+qteEntree-qteSortir;
    	  Double stockCretique=rs.getDouble("stock_cretique");
    	  Matiere m=new Matiere(); 
    	  m.setDesignation(rs.getString("designation"));
    	  m.setCretique(stockCretique+"m2");
    	  m.setInitiale(stockInitiale+"m2");
    	  m.setQteEntree(qteEntree+"m2");
    	  m.setQteSortir(qteSortir+"m2");
    	
    	  m.setQteFinale(dynamique.formatDouble(qteFinale)+"m2");
    	  if(qteFinale <stockCretique ) {
          	photo=srcPhoto+"urgent.png";	
          	}
            else {
            	photo=srcPhoto+"normale.png";	
            }
      		m.setStatus(getPhoto(photo));
    	dataMatiere.add(m)  ;
      }
      TableMatiere.setItems(dataMatiere);
      
    }
    private Hashtable<String, Double> getQteSortirMatiere() throws SQLException {
    	Hashtable<String, Double> values=new Hashtable<>();
    	query="select ROUND(sum(l.quantite*l.largeur*l.longueur),2)  as qteSortir,date_format(c.date_commande,'%Y-%m') as month\r\n"
    			+ " from list_commande l,commande c\r\n"
    			+ " where l.id_commande=c.id_commande and date_format(c.date_commande,'%Y')='"+year+"' \r\n"
    			+ " group by month";
    	st=connection.prepareStatement(query);
		 rs=st.executeQuery();  
		while(rs.next()) {
			values.put(rs.getString("month"),rs.getDouble("qteSortir"));
		}
		return values;
    	
    }
  
    private Hashtable<String, Double> getQteEntreeMatiere() throws SQLException {
    	Hashtable<String, Double> values=new Hashtable<>();
    	query="select ROUND(sum(cm.nbr_caisse*cm.largeur*cm.longueur),2)  as qteEntree,date_format(a.date_achat,'%Y-%m') as month\r\n"
    			+ " from caisse_matiere cm,achat a\r\n"
    			+ " where cm.id_achat=a.id_achat\r\n and date_format(a.date_achat,'%Y')='"+year+"'"
    			+ " group by month"; 
    	st=connection.prepareStatement(query);
		 rs=st.executeQuery();  
		while(rs.next()) {
			values.put(rs.getString("month"),rs.getDouble("qteEntree"));
		}
		return values;
    }
    
    private Hashtable<String, Double> getQteEntreePiece() throws SQLException {
    	Hashtable<String, Double> values=new Hashtable<>();
    	query="select ROUND(sum(cm.qte),2) as qteEntree,date_format(a.date_achat,'%Y-%m') as month\r\n"
    			+ " from caisse_accessoire cm,achat a\r\n"
    			+ " where cm.id_achat=a.id_achat  and date_format(a.date_achat,'%Y')='"+year+"'"
    			+ " group by month"; 
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();  
    	while(rs.next()) {
    		values.put(rs.getString("month"),rs.getDouble("qteEntree"));
    	}
    	return values;
    }
    
    private Hashtable<String, Double> getQteSortirPiece() throws SQLException {
    	Hashtable<String, Double> values=new Hashtable<>();
    	query="select ROUND(sum(cm.qte_consommation),2) as qteSortir,date_format(cm.date_consommation,'%Y-%m') as month\r\n"
    			+ " from consommation cm\r\n"
    			+ " where date_format(cm.date_consommation,'%Y')='"+year+"'"
    			+ " group by month"; 
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();  
    	while(rs.next()) {
    		values.put(rs.getString("month"),rs.getDouble("qteSortir"));
    	}
    	return values;
    }
    
    public LinkedHashMap<String, String> getValuesOfMois(){
    	LinkedHashMap<String, String> values=new LinkedHashMap<>();
    	values.put(""+year+"-01","Jan");
    	values.put(""+year+"-02","Fev");
    	values.put(""+year+"-03","Mars");
    	values.put(""+year+"-04","Avr");
    	values.put(""+year+"-05","Mai");
    	values.put(""+year+"-06","Juin");
    	values.put(""+year+"-07","Juil");
    	values.put(""+year+"-08","Out");
    	values.put(""+year+"-09","Sept");
    	values.put(""+year+"-10","Oct");
    	values.put(""+year+"-11","Nov");
    	values.put(""+year+"-12","Dec");
    	return values;
    }
	   @SuppressWarnings("unchecked")
	private void barChartMatiere() throws SQLException {   
		   
		   ArrayList<Series<Object,Double>> table =barchart(connection, getQteEntreeMatiere(), getQteSortirMatiere());
			barChart1.setTitle("Les Comparaison entre QteEntree et QteSortir ");
			barChart1.getData().addAll(table.get(0),table.get(1));
}
	   private ArrayList<Series<Object,Double>>  barchart(Connection connection,Hashtable<String, Double> valuesEntree,Hashtable<String, Double> valuesSortir ) throws SQLException {   
		   ArrayList<XYChart.Series<Object,Double>> tables=new ArrayList<>();
		   LinkedHashMap<String, String> valuesMois=getValuesOfMois();
		   XYChart.Series<Object,Double> series1=new XYChart.Series<>();
		 	XYChart.Series<Object,Double> series2=new XYChart.Series<>();
		 	series2.setName("Qte Sortir" );
			series1.setName("Qte Entree");
			Set<Entry<String, String>> set=valuesMois.entrySet();
			Iterator<Entry<String, String>> i =set.iterator();
			while(i.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry me=(Map.Entry)i.next();
				if(valuesEntree.containsKey(me.getKey())) {
					series1.getData().add(new XYChart.Data<Object,Double>(me.getValue(),valuesEntree.get(me.getKey())));
				    
				}else {

					series1.getData().add(new XYChart.Data<Object,Double>(me.getValue(),0.0));
				    
				}
				if(valuesSortir.containsKey(me.getKey())) {

					series2.getData().add(new XYChart.Data<Object,Double>(me.getValue(),valuesSortir.get(me.getKey())));
					
				}else {

					series2.getData().add(new XYChart.Data<Object,Double>(me.getValue(),0.0));
					
				}

				
			}
			tables.add(series1);
			tables.add(series2);
			return tables;
			
			
		
}
	   @SuppressWarnings("unchecked")
	private void barChartPiece() throws SQLException {
		   
		ArrayList<Series<Object, Double>> table  = barchart(connection, getQteEntreePiece(), getQteSortirPiece());

			 barChart2.setTitle("Les Comparaison entre QteEntree et QteSortir ");
			barChart2.getData().addAll(table.get(0),table.get(1));
	   }
    private void getPieces() throws SQLException {
    	
    	Hashtable<Integer, Double> values=getQteConsommer();
    	query="select a.id_accessoire,a.designation,a.stock_initiale,a.stock_cretique,ROUND(sum(cm.qte),2) as qte_entree\r\n"
    			+ "from accessoires a  \r\n"
    			+ "LEFT JOIN \r\n"
    			+ "caisse_accessoire cm ON a.id_accessoire=cm.id_accessoire\r\n"
    			+ "\r\n"
    			+ "group by a.id_accessoire,a.designation,a.stock_initiale,a.stock_cretique\r\n"
    			+ "ORDER by a.id_accessoire ASC";
    	st= connection.prepareStatement(query);
        rs=st.executeQuery();
        String photo = null;
        while(rs.next()) {
        	
        	Double qteConsommer=values.get(rs.getInt("id_accessoire"));
        	Double qteEntree=rs.getDouble("qte_entree");
        	Double stockInitiale=rs.getDouble("stock_initiale");
        	Double qteFinale=stockInitiale+qteEntree-qteConsommer;
        	Double stockCretique=rs.getDouble("stock_cretique");
        	Piece p =new Piece();
        	p.setDesignation(rs.getString("designation"));
        	p.setCretique(stockCretique);
        	p.setInitiale(stockInitiale);
        	p.setQteEntree(qteEntree);
        	p.setQteConsommer(qteConsommer);
        	p.setQteFinale(qteFinale);
        	if(qteFinale <stockCretique ) {
            	photo=srcPhoto+"urgent.png";	
            	}
              else {
              	photo=srcPhoto+"normale.png";	
              }
        		p.setStatus(getPhoto(photo));
        	dataPiece.add(p);
        } 
    	tablePiece.setItems(dataPiece);
    }
    private ImageView getPhoto(String photo) {

		Image set=new Image(getClass().getResourceAsStream(photo));
		ImageView img=new ImageView(set);
		img.setFitHeight(30);
		img.setFitWidth(55);
		return img;
		
    }
    private void loadLignToColumnsOfMatiere() {
    	columnMatiere.setCellValueFactory(new PropertyValueFactory<Matiere,String>("designation")); 
    	qteCretique1.setCellValueFactory(new PropertyValueFactory<Matiere,String>("cretique")); 
    	qteInitiale1.setCellValueFactory(new PropertyValueFactory<Matiere,String>("initiale")); 
    	qteEntree1.setCellValueFactory(new PropertyValueFactory<Matiere,String>("qteEntree")); 
    	qteSortir1.setCellValueFactory(new PropertyValueFactory<Matiere,String>("qteSortir")); 
    	qteFinale1.setCellValueFactory(new PropertyValueFactory<Matiere,String>("qteFinale")); 
    	status1.setCellValueFactory(new PropertyValueFactory<Matiere,ImageView>("status")); 
    }
    private void loadLignToColumnsOfPiece() {
    	columnPiece.setCellValueFactory(new PropertyValueFactory<Piece,String>("designation")); 
    	qteCretique2.setCellValueFactory(new PropertyValueFactory<Piece,Double>("cretique")); 
    	qteInitiale2.setCellValueFactory(new PropertyValueFactory<Piece,Double>("initiale")); 
    	qteEntree2.setCellValueFactory(new PropertyValueFactory<Piece,Double>("qteEntree")); 
    	qteConsommation2.setCellValueFactory(new PropertyValueFactory<Piece,Double>("qteConsommer")); 
    	qteFinale2.setCellValueFactory(new PropertyValueFactory<Piece,Double>("qteFinale")); 
    	status2.setCellValueFactory(new PropertyValueFactory<Piece,ImageView>("status")); 

    }
    private Hashtable<Integer, Double> getQteSortie() throws SQLException {
    	Hashtable<Integer, Double> values=new Hashtable<>();
    	query="select m.id_matiere,ROUND(sum(l.quantite*l.largeur*l.longueur),2) as qte_sortir\r\n"
    			+ "from matiere_premiere m  \r\n"
    			+ "LEFT JOIN list_commande l ON m.id_matiere=l.id_produit\r\n"
    			+ "group by m.id_matiere \r\n"
    			+ "ORDER by m.id_matiere ASC";
    	 
    	st= connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		values.put(rs.getInt("id_matiere"), rs.getDouble("qte_sortir")) ;
    	}
    	return values;
    }
    private Hashtable<Integer, Double> getQteConsommer() throws SQLException {
    	Hashtable<Integer, Double> values=new Hashtable<>();
    	query="select m.id_accessoire,ROUND(sum(l.qte_consommation),2) as qte_sortir\r\n"
    			+ "from accessoires m  \r\n"
    			+ "LEFT JOIN consommation l ON m.id_accessoire=l.id_accessoire\r\n"
    			+ "group by m.id_accessoire \r\n"
    			+ "ORDER by m.id_accessoire ASC";
    	
    	st= connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		values.put(rs.getInt("id_accessoire"), rs.getDouble("qte_sortir")) ;
    	}
    	return values;
    }
    
    private void getStylishOfColumn() {
		String style = "-fx-font-size:12px;-fx-alignment:center";
		columnMatiere.setStyle(style);
		qteCretique1.setStyle(style);
		qteInitiale1.setStyle(style);
		qteEntree1.setStyle(style);
		qteSortir1.setStyle(style);
		qteFinale1.setStyle(style);
		status1.setStyle(style);
//		 table 02
		columnPiece.setStyle(style);
		qteInitiale2.setStyle(style);
		qteCretique2.setStyle(style);
		qteEntree2.setStyle(style);
		qteConsommation2.setStyle(style);
		qteFinale2.setStyle(style);
		status2.setStyle(style);
	}
	
    @Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		try {
			barChartMatiere();
			barChartPiece();
			getMatieres();
			getPieces();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		loadLignToColumnsOfMatiere();		
		loadLignToColumnsOfPiece();		
		getStylishOfColumn();
	}
}
	