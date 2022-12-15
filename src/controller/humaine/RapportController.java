package controller.humaine;

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
import java.util.ResourceBundle;
import java.util.Set;

import controller.helpers.DbConnect;
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
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Employe;

public class RapportController implements Initializable {
	public static RapportController getInstance() {
		return new RapportController();
	}
	  @FXML
	    private Text FraisEmployes;

	    @FXML
	    private TableColumn<Employe,String> absences;

	    @FXML
	    private BarChart<Object, Double> barChart1;

	    @FXML
	    private BarChart<Object, Double> barChart2;
	    
	    @FXML
	    private StackedBarChart<Object, Double> stackedChart;
	    
	    @FXML
	    private BarChart<Object, Double> barChart3;

	    @FXML
	    private TextField inputClient;

	    @FXML
	    private Text nbrAbsences;

	    @FXML
	    private Text nbrEmployes;

	    @FXML
	    private Text nbrJustifications;

	    @FXML
	    private TableColumn<Employe,String> nom;

	    @FXML
	    private PieChart pieChart;

	    @FXML
	    private TableColumn<Employe,String> prenom;

	    @FXML
	    private TableView<Employe> table;
	    private ArrayList<Integer> array=new ArrayList<>();
	private PreparedStatement st;
	private ResultSet rs;
	private String query;
	private Double fraisTotale=0.0,nbrabsence=0.0;
	
	private int year=ConsulterController.getInstance().getThisYear(),nbrEmploye=0,nbrJusti;
   private Connection connection=DbConnect.getInstance().getConnection();
   private ObservableList<PieChart.Data> piechartData=FXCollections.observableArrayList();
   private ObservableList<Employe> employeData=FXCollections.observableArrayList();
   private Hashtable<String, Double> absenceNonJustifier=new Hashtable<>();
   private Hashtable<String, Double> absenceJustifier=new Hashtable<>();


   public Series<Object,Double>  barchart(Connection connection,Hashtable<String, Double> values,String title) throws SQLException {   
		   LinkedHashMap<String, String> valuesMois=controller.stock.RapportController.getInstance().getValuesOfMois();
		   XYChart.Series<Object,Double> series1=new XYChart.Series<>();
			series1.setName(title);
			Set<?> set=valuesMois.entrySet();
			Iterator<?> i =set.iterator();
			while(i.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry me=(Map.Entry)i.next();
				if(values.containsKey(me.getKey())) {
					series1.getData().add(new XYChart.Data<Object, Double>(me.getValue(),values.get(me.getKey())));
				}else {
					series1.getData().add(new XYChart.Data<Object,Double>(me.getValue(),0.0));
				}
			}
			return series1;
		
 }
	private Hashtable<String, Double> getFraisByMonth() throws SQLException {
    	Hashtable<String, Double> values =new Hashtable<>();
    	query="select sum(f.cout_frais) as montanTotale,date_format(f.date_frais,'%Y-%m') as month \r\n"
    			+ "from frais f "
    			+ "where date_format(f.date_frais,'%Y')='"+year+"' \r\n"
    			+ "group by month";
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		double montant=rs.getDouble("montanTotale");
    		values.put(rs.getString("month"), montant);
    		fraisTotale+=montant;
    		
    	}
    	return values;
    	}
	private Hashtable<String, Double> getHeureByMonth() throws SQLException {
		Hashtable<String, Double> values =new Hashtable<>();
		query="select sum(h.nbr_heure) as nbrHeure,date_format(h.date_supplementaire,'%Y-%m') as month  \r\n"
				+ "from heure_supplementaire h  "
				+ " where date_format(h.date_supplementaire,'%Y')='"+year+"' \r\n"
				+ "group by month ";
		st=connection.prepareStatement(query);
		rs=st.executeQuery();
		while(rs.next()) {
			values.put(rs.getString("month"), rs.getDouble("nbrHeure"));
		}
		return values;
	}
	private Integer SumOfArray() {
		Integer sum=0;
		for (Integer integer : array) {
			sum +=integer;
		}
		  return sum;
	}
	private Hashtable<String, Double> getAbsenceByMonth() throws SQLException {
		Hashtable<String, Double> values =new Hashtable<>();
		query="select  count(a.id_absence) as id,date_format(a.date_debut,'%Y-%m') as month \r\n"
				+ "from absences a "
				+ "where date_format(a.date_debut,'%Y')='"+year+"' \r\n"
				+ "group by month ";
		st=connection.prepareStatement(query);
		rs=st.executeQuery();
		while(rs.next()) {
			Double id=rs.getDouble("id");
			values.put(rs.getString("month"),id);
			nbrabsence+=id;
		}
		return values;
	}
	private void barChart1() throws SQLException {
  	  XYChart.Series<Object,Double> series1=barchart(connection, getFraisByMonth(), "Frais");
  	  barChart1.setTitle("Les frais d'employes par mois dans l'année "+year);
  	  barChart1.getData().add(series1);
  }
	private void barChart2() throws SQLException {
		XYChart.Series<Object,Double> series1=barchart(connection, getHeureByMonth(), "Heures supplemntaires");
		barChart2.setTitle("Les Heures supplemntaires d'employes par mois dans l'année "+year);
		barChart2.getData().add(series1);
	}
	private void barChart3() throws SQLException {
		XYChart.Series<Object,Double> series1=barchart(connection, getAbsenceByMonth(), "Nbr Absences");
		barChart3.setTitle("Les Absences d'employes par mois dans l'année "+year);
		barChart3.getData().add(series1);
	}
	private void getAbsenceByType() throws SQLException {
    	Hashtable<Integer,String> values= new Hashtable<Integer,String>();
    	Integer sum=0,x=0;
    	Double pourcentage=0.0;
    	query="select count(a.id_absence) as nbrAbsence,t.titre\r\n"
    			+ "from absences a,type_absence t\r\n"
    			+ "where a.id_type=t.id_type \r\n"
    			+ "group by t.titre ";
    	PreparedStatement st=connection.prepareStatement(query);
    	ResultSet rs=st.executeQuery();
    	while(rs.next()) {
    		array.add(rs.getInt("nbrAbsence"));
    		values.put(rs.getInt("nbrAbsence"), rs.getString("titre"));
    	}
    	Enumeration<Integer> absences=values.keys();
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
    	pieChart.setTitle("Les absences d'employes par Types");
    }
	private void getAbsenceByjustification() throws SQLException {
    	query="select count(a.id_absence) as nbrAbsence,date_format(a.date_debut,'%Y-%m') as month,a.justifier\r\n"
    			+ "from absences a "
    			+ "where date_format(a.date_debut,'%Y')='"+year+"' \r\n"
    			+ "group BY month,a.justifier";
    	st=connection.prepareStatement(query);
    	rs=st.executeQuery();
    	while(rs.next()) {
    		Double nbr=rs.getDouble("nbrAbsence");
    		String month=rs.getString("month");
    		String bool=rs.getString("justifier");
    		if(bool.compareTo("Non")==0) {
    			absenceNonJustifier.put(month, nbr);
    		}else {
    			nbrJusti+=nbr;
    			absenceJustifier.put(month, nbr);
    		}
    	}
    	
	}
	@SuppressWarnings("unchecked")
	private void stackedChart() throws SQLException {
		getAbsenceByjustification();
		  LinkedHashMap<String, String> valuesMois=controller.stock.RapportController.getInstance().getValuesOfMois();
		  XYChart.Series<Object,Double> series1=new XYChart.Series<>();
	    	 XYChart.Series<Object,Double> series2=new XYChart.Series<>();
	    	 series2.setName("Non-Justifier");
	    	 series1.setName("Justifier");
	    		Set<?> set=valuesMois.entrySet();
				Iterator<?> i =set.iterator();
				while(i.hasNext()) {
					@SuppressWarnings("rawtypes")
					Map.Entry me=(Map.Entry)i.next();
					if(absenceJustifier.containsKey(me.getKey())) {
						series1.getData().add(new XYChart.Data<Object, Double>(me.getValue(),absenceJustifier.get(me.getKey())));
					}else {
						series1.getData().add(new XYChart.Data<Object, Double>(me.getValue(),0.0));
					}
					if(absenceNonJustifier.containsKey(me.getKey())) {
						series2.getData().add(new XYChart.Data<Object, Double>(me.getValue(),absenceNonJustifier.get(me.getKey())));
					}else {
						series2.getData().add(new XYChart.Data<Object, Double>(me.getValue(),0.0));
					}
				}
				
				stackedChart.setTitle("Les absences justifier et non-justifier en chaque dans l'année "+year);
				stackedChart.getData().addAll(series1,series2);
		
	}
	private void getEmployeByAbsences() throws SQLException {
		query="select e.nom_employe,e.prenom_employe,sum(a.jour) as nbrJour\r\n"
				+ "from employes e,absences a\r\n"
				+ "where e.id_employe=a.id_employe\r\n"
				+ "GROUP BY e.nom_employe,e.prenom_employe ";
		st=connection.prepareStatement(query);
		rs=st.executeQuery();
		Employe e=null;
		while(rs.next()) {
			nbrEmploye++;
			e=new Employe();
			e.setNom(rs.getString("nom_employe"));
			e.setPrenom(rs.getString("prenom_employe"));
			e.setAbsence(rs.getDouble("nbrJour")+" Jour");
			employeData.add(e);
		}
		table.setItems(employeData);
	}
	private void loadEmploye() throws SQLException {
		getEmployeByAbsences();
		nom.setCellValueFactory(new PropertyValueFactory<Employe,String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Employe,String>("prenom"));
		absences.setCellValueFactory(new PropertyValueFactory<Employe,String>("absence"));
		FilterByEmploye();
	}
	private void getStylishOfColumn() {
		String style = "-fx-font-size:12px;-fx-alignment:center";
		nom.setStyle(style);
		prenom.setStyle(style);
		style += ";-fx-font-weight:bold";
		absences.setStyle(style);
	}
    
	public void FilterByEmploye() {
		FilteredList<Employe> filteredData = new FilteredList<>(employeData, b -> true);
		inputClient.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productSearchModel -> {
				if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if (productSearchModel.getNom().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}else if(productSearchModel.getPrenom().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				}
				else {
					return false;
				}

			});
		});
		SortedList<Employe> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			barChart1();
			barChart2();
			barChart3();
			getAbsenceByType();
			stackedChart();
			loadEmploye();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getStylishOfColumn();
		FraisEmployes.setText(String.valueOf(fraisTotale));
		nbrAbsences.setText(String.valueOf(nbrabsence));
		nbrEmployes.setText(String.valueOf(nbrEmploye));
		nbrJustifications.setText(String.valueOf(nbrJusti));
	}
}
