package controller.achat;

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
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.text.Text;

public class RapportController implements Initializable {

    @FXML
    private BarChart<String, Double> barChart1;

    @FXML
    private BarChart<String, Double> barChart2;

    @FXML
    private PieChart pieChart;

    @FXML
    private Text txtMontanTotale;
    @FXML
    private Text txtMontant1;
    @FXML
    private Text txtReste1;
    @FXML
    private Text txtQte1;
    @FXML
    private Text txtQteTotale;
    @FXML
    private PieChart pieChart1;
    @FXML
    private Text txtVersement1;
    @FXML
    private Text txtResteTotale;
    @FXML
    private BarChart<String,Double> barChartPiece1;

    @FXML
    private BarChart<String,Double> barChartPiece2;
    @FXML
    private Text txtVersementTotale;
    private String query;
    private PreparedStatement st;
    private ResultSet rs;
    private Connection conn=DbConnect.getInstance().getConnection();
    int year=ConsulterController.getInstance().getThisYear();
    private ObservableList<PieChart.Data> piechartData=FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> piechartData1=FXCollections.observableArrayList();
    private ArrayList<Double> array=new ArrayList<>();
    private ArrayList<Double> array1=new ArrayList<>();
    private Double qteGlobale=0.0,montantGlobale=0.0;
    private Double qteGlobale1=0.0,montantGlobale1=0.0;
    private DynamiqueView view=new DynamiqueView();
    
    private Hashtable<String, Double> getMontantByMonth() throws SQLException {
    	Hashtable<String, Double> values =new Hashtable<>();
    	query="select date_format(a.date_achat,'%Y-%m') as month , ROUND(sum(cm.nbr_caisse*cm.largeur*cm.longueur),2) as qteTotale,ROUND(sum(cm.nbr_caisse*cm.largeur*cm.longueur*cm.prix_unitaire),2) as montanTotale\r\n"
    			+ "from achat a,caisse_matiere cm \r\n"
    			+ "where cm.id_achat=a.id_achat and date_format(a.date_achat,'%Y')='"+year+"' \r\n"
    			+ "group by month \r\n";
    	st=conn.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		double montant=rs.getDouble("montanTotale");
    		values.put(rs.getString("month"), montant);
    		qteGlobale+=rs.getDouble("qteTotale");
    		montantGlobale+=montant;
    		
    	}
    	return values;
    	}
    
    private Hashtable<String, Double> getMontantByMonthOfPiece() throws SQLException {
    	Hashtable<String, Double> values =new Hashtable<>();
    	query="select date_format(a.date_achat,'%Y-%m') as month , ROUND(sum(cm.qte),2) as qteTotale,ROUND(sum(cm.qte*cm.prix_unitaire),2) as montanTotale\r\n"
    			+ "from achat a \r\n"
    			+ "INNER JOIN caisse_accessoire cm ON cm.id_achat=a.id_achat "
    			+ "where date_format(a.date_achat,'%Y')='"+year+"' \r\n"
    			+ "group by month ";
    	st=conn.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		double montant=rs.getDouble("montanTotale");
    		values.put(rs.getString("month"), montant);
    		qteGlobale1+=rs.getDouble("qteTotale");
    		montantGlobale1+=montant;
    		
    	}
    	return values;
    }
    
    private Double SumOfArray() {
		Double sum=0.0;
		for (Double integer : array) {
			sum +=integer;
		}
		  return sum;
	}
    private Double SumOfArray1() {
    	Double sum=0.0;
    	for (Double integer : array1) {
    		sum +=integer;
    	}
    	return sum;
    }

    private void getProductsByQteAndMontant() throws SQLException {
    	 Hashtable<Double,String> values= new Hashtable<Double,String>();
	   	 double sum=0.0,x=0.0,pourcentage=0.0;
		XYChart.Series<String, Double> series1=new XYChart.Series<>();
    		series1.setName("Montant d'achat");
    		
    	query="select m.designation, ROUND(sum(cm.nbr_caisse*cm.largeur*cm.longueur),2) as qteTotale,ROUND(sum(cm.nbr_caisse*cm.largeur*cm.longueur*cm.prix_unitaire),2) as montanTotale\r\n"
    			+ "from matiere_premiere m  \r\n"
    			+ "LEFT Join \r\n"
    			+ " caisse_matiere cm ON cm.id_matiere=m.id_matiere \r\n"
    			+ "group by m.designation\r\n";
    	st=conn.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		series1.getData().add(new XYChart.Data<String, Double>(rs.getString("designation"),rs.getDouble("montanTotale")));
    		array.add(rs.getDouble("qteTotale"));
			values.put(rs.getDouble("qteTotale"), rs.getString("designation"));
    	}
    	Enumeration<Double> absences=values.keys();
		
		sum=SumOfArray();   
        while (absences.hasMoreElements()) {
       	  x=absences.nextElement();
             pourcentage=((x*100)/sum);
             piechartData.add(
  				new PieChart.Data(values.get(x), pourcentage)
  				);	
		 }
        
		pieChart.setLegendSide(Side.LEFT);		
		pieChart.setData(piechartData);
		pieChart.setTitle("La Quantite d'achat par mois dans l'année "+year );

    	barChart2.getData().add(series1);
    }
    private void getPiecesByQteAndMontant() throws SQLException {
    	Hashtable<Double,String> values= new Hashtable<Double,String>();
    	double sum=0.0,x=0.0,pourcentage=0.0;
    	XYChart.Series<String,Double> series1=new XYChart.Series<>();
    	series1.setName("Montant d'achat");
    	
    	query="select ac.designation, ROUND(sum(cm.qte),2) as qteTotale,ROUND(sum(cm.qte*cm.prix_unitaire),2) as montanTotale\r\n"
    			+ "from accessoires ac  \r\n"
    			+ "LEFT Join \r\n"
    			+ " caisse_accessoire cm ON cm.id_accessoire=ac.id_accessoire\r\n"
    			+ "group by ac.designation\r\n";
    	st=conn.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		series1.getData().add(new XYChart.Data<String, Double>(rs.getString("designation"),rs.getDouble("montanTotale")));
    		array1.add(rs.getDouble("qteTotale"));
    		values.put(rs.getDouble("qteTotale"), rs.getString("designation"));
    	}
    	Enumeration<Double> absences=values.keys();
    	
    	sum=SumOfArray1();   
    	while (absences.hasMoreElements()) {
    		x=absences.nextElement();
    		pourcentage=((x*100)/sum);
    		piechartData1.add(
    				new PieChart.Data(values.get(x), pourcentage)
    				);	
    	}
    	
    	pieChart1.setLegendSide(Side.LEFT);		
    	pieChart1.setData(piechartData1);
    	pieChart1.setTitle("La Quantite d'achat par mois dans l'année "+year );
    	
    	barChartPiece2.getData().add(series1);
    }
    private Series<String, Double>  barchart(Connection connection,Hashtable<String, Double> values,String title) throws SQLException {   
		   LinkedHashMap<String, String> valuesMois=controller.stock.RapportController.getInstance().getValuesOfMois();
		   XYChart.Series<String,Double> series1=new XYChart.Series<>();
			series1.setName(title);
			Set<Entry<String, String>> set=valuesMois.entrySet();
			Iterator<Entry<String, String>> i =set.iterator();
			while(i.hasNext()) {
				Entry<String, String> me=i.next();
				if(values.containsKey(me.getKey())) {
					series1.getData().add(new XYChart.Data<String, Double>(me.getValue(),values.get(me.getKey())));
				}else {
					series1.getData().add(new XYChart.Data<String, Double>(me.getValue(),0.0));
				}
			}
			return series1;
		
    }
    private void barChart1() throws SQLException {
    	  XYChart.Series<String,Double> series1=barchart(conn, getMontantByMonth(), "Montant d'achat");
    	  barChart1.setTitle("Le montant d'achat par mois dans l'année "+year);
    	  barChart1.getData().add(series1);
    }
    private void barChartPiece1() throws SQLException {
    	XYChart.Series<String,Double> series1=barchart(conn, getMontantByMonthOfPiece(), "Montant d'achat");
    	barChartPiece1.setTitle("Le montant d'achat par mois dans l'année "+year);
    	barChartPiece1.getData().add(series1);
    }
    private Hashtable<Integer, Double> getVersement() throws SQLException {
    	Hashtable<Integer, Double> values=new Hashtable<>();
    	query="select type,sum(c.versement) as cout \r\n"
    			+ "from versement_achat c  "
    			+ "group by type";
    	st=conn.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		
    		values.put(rs.getInt("type"), rs.getDouble("cout"));
    	}
    	return values;
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Double vPiece=0.0,vMatiere =0.0;
		try {
			barChart1();
			barChartPiece1();
			getProductsByQteAndMontant();
			getPiecesByQteAndMontant();
			Hashtable<Integer, Double> v=getVersement();
			vMatiere=v.get(0);
			vPiece=v.get(1);
			txtVersementTotale.setText(vMatiere+"DA");
			txtVersement1.setText(vPiece+"DA");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtQteTotale.setText(qteGlobale+"m2");
		txtQte1.setText(qteGlobale1+"m2");
		txtMontanTotale.setText(montantGlobale+"DA");
		txtMontant1.setText(montantGlobale1+"DA");
		Double t1 =vMatiere==null ? 0.0 : vMatiere; 
		Double t2 =vPiece==null ? 0.0 : vPiece; 
		txtResteTotale.setText(view.formatDouble(montantGlobale-t1)+"DA");
		txtReste1.setText(view.formatDouble(montantGlobale1-t2)+"DA");
		
		
	}
    
    

}
