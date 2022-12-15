package controller.humaine.paiement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Employe;
import model.Paiement;

public class OrdreController {
   private DynamiqueView view = new DynamiqueView();
    @FXML
    private TableColumn<Paiement,String> columnDate;

    @FXML
    private TableColumn<Paiement,String> columnElement;

    @FXML
    private TableColumn<Paiement,String> columnMontant;

    @FXML
    private TableColumn<Paiement,String> columnVersement;

    @FXML
    private TableView<Paiement> table;

    @FXML
    private Text txtAdresse;

    @FXML
    private Text txtDateCreation;

    @FXML
    private Text txtNom;

    @FXML
    private Text txtNumTelephone;

    @FXML
    private Text txtPeriode;

    @FXML
    private Text txtPrenom;
    @FXML
    private Text txtConge;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Text txtRevenuHebdomodaire;
    private int comptConge=0;
    private final String  conge="Congé";
    private final String retard="En retard";
    @FXML
    private Text txtRevenuMensuelle;
    @FXML
    private Text txtSalaire;
    private ObservableList<Paiement> data = FXCollections.observableArrayList();
    
    private String query;
    private Connection connection=DbConnect.getInstance().getConnection();
    private PreparedStatement statement;
    private ResultSet rs;

    private double taxJour,taxHeure,salaireTemp,taxTravaille;

    @FXML
    void btnFermer(MouseEvent event) {
         view.closeStage(event);
    }

   
    private String getMonth(int month) {
    	String mois = null;
    			if(month<10) {
    				mois=0+""+month;
    			}
    		return mois;
    }
private Double changeFormatOfDouble(Double value) {
	String set=String.format("%.2f", value);
	String val=set.replace(",", ".");
   return Double.parseDouble(val);
}
private void getStylishOfColumn() {
	 String style="-fx-font-size:12px;-fx-alignment:center";
	columnDate.setStyle(style);
	columnElement.setStyle(style);
	style +=";-fx-font-weight:bold";
	columnMontant.setStyle(style);
	columnVersement.setStyle(style);
}
	private void getFrais(int id,int annee,int month,int week,Double revenuMen,Double revenuHeb) throws SQLException {
		
		String tempMonth=getMonth(month);
		if(week==-1) {
			salaireTemp=revenuMen;
          
			query="SELECT description as element,date_frais as date, cout_frais as montant from frais \r\n"
					+ "WHERE id_employe="+id+" AND date_format(date_frais,'%Y')='"+annee+"' AND date_format(date_frais,'%m')='"+tempMonth+"'";	
			statement=connection.prepareStatement(query);
			rs=statement.executeQuery();
			while(rs.next()) {
				salaireTemp -=rs.getDouble("montant");
				Paiement p = new Paiement();
				p.setElement(rs.getString("element"));
				p.setDate(rs.getString("date"));
				p.setMontant(rs.getDouble("montant")+"DA");
				p.setVersement(salaireTemp+"DA");
				data.add(p);
			}

			table.setItems(data);
			loadData();
		}else {

			salaireTemp=revenuHeb;
			
			
			
			query="SELECT description as element,date_frais as date, cout_frais as montant from frais \r\n"
					+ " WHERE id_employe="+id+" AND date_format(date_frais,'%Y')='"+annee+"' AND date_format(date_frais,'%m')='"+tempMonth+"'  "
							+ " and semaine="+week+"";
	
			statement=connection.prepareStatement(query);
			rs=statement.executeQuery();
			while(rs.next()) {
				salaireTemp -=rs.getDouble("montant");
				Paiement p = new Paiement();
				p.setElement(rs.getString("element"));
				p.setDate(rs.getString("date"));
				p.setMontant(rs.getDouble("montant")+"DA");
				p.setVersement(salaireTemp+"DA");
				data.add(p);
			}
			table.setItems(data);
			loadData();
		}
		
	}
	   private Double getSupp() throws SQLException {
			Double remise =null; 
			query="select heure_supplementaire from parametres";
			statement= connection.prepareStatement(query);
			rs=statement.executeQuery();
			if(rs.next()) {
				remise=rs.getDouble("heure_supplementaire");	
			}
			return remise;
		}
	private void getHeureTravaille(int id,int annee,int month,int week) throws SQLException {
		taxTravaille=getSupp();
		String tempMonth=getMonth(month);
		if(week==-1) {
			
			
			query="SELECT description as element,date_supplementaire as date, nbr_heure as heure from heure_supplementaire \r\n"
					+ "WHERE id_employe="+id+" AND date_format(date_supplementaire,'%Y')='"+annee+"' AND date_format(date_supplementaire,'%m')='"+tempMonth+"'";	
			statement=connection.prepareStatement(query);
			rs=statement.executeQuery();
			while(rs.next()) {
				 Double tempValue=taxTravaille*rs.getDouble("heure");
				salaireTemp +=tempValue;
				Paiement p = new Paiement();
				p.setElement(rs.getString("element"));
				p.setDate(rs.getString("date"));
				p.setMontant(tempValue+"DA");
				p.setVersement(salaireTemp+"DA");
				data.add(p);
			}
			
			table.setItems(data);
			loadData();
		}else {
			
			query="SELECT description as element,date_supplementaire as date, nbr_heure as heure from heure_supplementaire \r\n"
					+ " WHERE id_employe="+id+" AND date_format(date_supplementaire,'%Y')='"+annee+"' AND date_format(date_supplementaire,'%m')='"+tempMonth+"'  "
					+ " and semaine="+week+"";
			
			statement=connection.prepareStatement(query);
			rs=statement.executeQuery();
			while(rs.next()) {
				 Double tempValue=taxTravaille*rs.getDouble("heure");
				salaireTemp +=tempValue;
				Paiement p = new Paiement();
				p.setElement(rs.getString("element"));
				p.setDate(rs.getString("date"));
				p.setMontant(tempValue+"DA");
				p.setVersement(salaireTemp+"DA");
				data.add(p);
			}
			table.setItems(data);
			loadData();
		}
		
	}
	private void getAbsence(int id,int annee,int month,int week,Double revenuMen) throws SQLException {
		String monthTemp=getMonth(month);
		taxJour=(double)revenuMen/22;
       taxJour=changeFormatOfDouble(taxJour);
		if(week==-1) {
			query="SELECT men.titre as element,abs.date_debut as date ,abs.jour,abs.heure"
					+ "  FROM absences abs ,type_absence men \r\n"
					+ "  WHERE date_format(abs.date_debut,'%Y')='"+annee+"' and date_format(abs.date_debut,'%m')='"+monthTemp+"'"
							+ "  and men.id_type=abs.id_type and abs.id_employe="+id+" and abs.justifier='Non'";
			statement=connection.prepareStatement(query);
			rs=statement.executeQuery();
			while(rs.next()) {
				if(!rs.getString("element").equals(retard) && !rs.getString("element").equals(conge)){
					Double temp=taxJour*rs.getInt("jour");
					salaireTemp -=temp;
					Paiement p=new Paiement();
					p.setElement(rs.getString("element"));
					p.setDate(rs.getString("date"));
					p.setMontant(temp+"DA");
					p.setVersement(changeFormatOfDouble(salaireTemp)+"DA");
					data.add(p);
                }
                else if(rs.getString("element").equals(retard) ) {
                	taxHeure=taxJour/8;
                	Double temp =taxHeure*rs.getDouble("heure") ;
                     salaireTemp-=temp;
 					Paiement p=new Paiement();
 					p.setElement(rs.getString("element"));
 					p.setDate(rs.getString("date"));
 					p.setMontant(temp+"DA");
 					p.setVersement(changeFormatOfDouble(salaireTemp)+"DA");
 					data.add(p);
                }
                else if(rs.getString("element").equals(conge)) {
                	comptConge +=rs.getInt("jour");
                }
			}
			table.setItems(data);
			loadData();
		}else {
			query="SELECT men.titre as element,abs.date_debut as date,abs.jour,abs.heure "
					+ "FROM absences abs,type_absence men \r\n "
					+ "WHERE date_format(abs.date_debut,'%Y')='"+annee+"' and date_format(abs.date_debut,'%m')='"+monthTemp+"' AND abs.semaine="+week+""
							+ " and men.id_type=abs.id_type and abs.id_employe="+id+" and abs.justifier='Non'";
			statement=connection.prepareStatement(query);
			rs=statement.executeQuery();
			while(rs.next()) {
				if(!rs.getString("element").equals(retard) && !rs.getString("element").equals(conge)  ) {
                	Double temp=taxJour*rs.getInt("jour");
                	salaireTemp -=temp;
                	Paiement p=new Paiement();
 					p.setElement(rs.getString("element"));
 					p.setDate(rs.getString("date"));
 					p.setMontant(temp+"DA");
 					p.setVersement(changeFormatOfDouble(salaireTemp)+"DA");
 					data.add(p);
                	
                }
                else if(rs.getString("element").equals(retard) ) {
                	taxHeure=taxJour/8; 
                	Double temp = taxHeure*rs.getDouble("heure");
                	salaireTemp -= temp;
                 	Paiement p=new Paiement();
 					p.setElement(rs.getString("element"));
 					p.setDate(rs.getString("date"));
 					p.setMontant(changeFormatOfDouble(temp)+"DA");
 					p.setVersement(changeFormatOfDouble(salaireTemp)+"DA");
 					data.add(p);
                }
                else if(rs.getString("element").equals(conge)) {
                	comptConge +=rs.getInt("jour");                	
                }
			}
			table.setItems(data);
			loadData();
		}
	}
	private void loadData() {

   	 columnElement.setCellValueFactory(new PropertyValueFactory<Paiement,String>("element"));	 
   	 columnDate.setCellValueFactory(new PropertyValueFactory<Paiement,String>("date"));	 
   	 columnMontant.setCellValueFactory(new PropertyValueFactory<Paiement,String>("montant"));	 
   	 columnVersement.setCellValueFactory(new PropertyValueFactory<Paiement,String>("versement"));	 
   	 txtSalaire.setText(String.valueOf(changeFormatOfDouble(salaireTemp)+" DA"));
   	 txtConge.setText(comptConge+" Jours");
	}
    public void setText(Integer idEmploye,int annee,int mois ,int week) throws SQLException {
    	if(week==-1) {txtPeriode.setText(mois+"/"+annee);}
    	else {txtPeriode.setText("Semaine 0"+week+"/"+mois+"/"+annee);}
    	Employe e=getEmploye(idEmploye);
    	txtAdresse.setText(e.getAdresse());
    	txtDateCreation.setText(view.getDateOfThisDay());
    	txtNom.setText(e.getNom());
    	txtPrenom.setText(e.getPrenom());
    	Double heb= Double.parseDouble(e.getRevenuHebdomodaire());
    	Double men=heb*4;
    	txtRevenuMensuelle.setText(heb*4+"DA");
    	txtRevenuHebdomodaire.setText(heb+"DA");
 	   getFrais(idEmploye,annee, mois, week,men,heb);
 	   getHeureTravaille(idEmploye, annee, mois, week);
  	   getAbsence(idEmploye,annee, mois, week,men);
  	   getStylishOfColumn();
  	   view.getLogo(rectangle);
    }
    private Employe getEmploye(Integer id) throws SQLException {
    	Employe e = new Employe();
    	query="select * from employes where id_employe="+id+"";
    	statement= connection.prepareStatement(query);
        rs =statement.executeQuery();
    	if(rs.next()) {
    		e.setNom(rs.getString("nom_employe"));
    		e.setPrenom(rs.getString("prenom_employe"));
    		e.setAdresse(rs.getString("adresse"));
    		e.setDateNaissance(rs.getString("date_naissance"));
    		e.setDateRecrutement(rs.getString("date_recrutement"));
    		e.setRevenuHebdomodaire(String.valueOf(rs.getDouble("revenu")));
    		
    	}
    	return e;
    }

}
