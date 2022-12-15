package controller.achat.MatierePremier;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;

import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.Achat;
import model.CaisseMatiere;
import model.Matiere;
import model.Personne;
import model.Versement;

public class InsererController implements Initializable {

    @FXML
    private ComboBox<Personne> cmbFournisseur;

    @FXML
    private ComboBox<Matiere> cmbMatiere1;

    @FXML
    private ComboBox<Matiere> cmbMatiere2;

    @FXML
    private ComboBox<Matiere> cmbMatiere3;

    @FXML
    private ComboBox<Matiere> cmbMatiere4;

    @FXML
    private ComboBox<Matiere> cmbMatiere5;

    @FXML
    private TextField inputLargeur1;

    @FXML
    private TextField inputLargeur2;

    @FXML
    private TextField inputLargeur3;

    @FXML
    private TextField inputLargeur4;

    @FXML
    private TextField inputLargeur5;

    @FXML
    private TextField inputLivraison;

    @FXML
    private TextField inputLongueur1;

    @FXML
    private TextField inputLongueur2;

    @FXML
    private TextField inputLongueur3;

    @FXML
    private TextField inputLongueur4;

    @FXML
    private TextField inputLongueur5;

    @FXML
    private TextField inputNbrCaisse1;

    @FXML
    private TextField inputNbrCaisse2;

    @FXML
    private TextField inputNbrCaisse3;

    @FXML
    private TextField inputNbrCaisse4;

    @FXML
    private TextField inputNbrCaisse5;

    @FXML
    private TextField inputPrix1;

    @FXML
    private TextField inputPrix2;

    @FXML
    private TextField inputPrix3;

    @FXML
    private TextField inputPrix4;

    @FXML
    private TextField inputPrix5;
    @FXML
    private TextField inputVersement;
     
    private String Query="";
    private ResultSet result;
    private PreparedStatement statement;
    private ObservableList<Personne> dataF= FXCollections.observableArrayList();
    private Connection connection=DbConnect.getInstance().getConnection();
	private DynamiqueView dynamique= new DynamiqueView();
    private ObservableList<Matiere> cmbData1= FXCollections.observableArrayList();
    private ObservableList<Matiere> cmbData2= FXCollections.observableArrayList();
    private ObservableList<Matiere> cmbData3= FXCollections.observableArrayList();
    private ObservableList<Matiere> cmbData4= FXCollections.observableArrayList();
    private ObservableList<Matiere> cmbData5= FXCollections.observableArrayList();
    private Hashtable<Integer,String> items=getMatieres(connection);
    private boolean valueNotDouble;
	private int id,IdAchat;
	private boolean Mor2ValuesIsEmpty,valueEmpty; 
    
    private int idFournisseur=0,idMatier1=0,idMatier2=0,idMatier3,idMatier4=0,idMatier5=0;

	private void loadMatierePremier3(CaisseMatiere m2) throws SQLException {
		ArrayList<TextField> valuesMatier3=GetValuesOfMatiere03();
		Mor2ValuesIsEmpty=dynamique.ValuesEmptyMorThen2OfArray(valuesMatier3,2);
		if(!Mor2ValuesIsEmpty) {
			
		valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesMatier3);
			if(valueNotDouble) {
				clearMatiere03();
				dynamique.alertError("Page 03", "Les valeurs ne doit pas contient un charactere ");	
			}
			
			else if(idMatier3 ==0) {
				dynamique.alertError("Page 03", "Vueiller Selectionner Matière premier 3");	
				
			}
			else {
				insererOfPage01();
				insertMatiere(m2);
				CaisseMatiere m3=new CaisseMatiere();
				m3.setId_achat(id);
				m3.setId_matiere(idMatier3);
				m3.setNbr_caisse(Double.parseDouble(valuesMatier3.get(0).getText()));
				m3.setLongueur(valuesMatier3.get(1).getText());
				m3.setLargeur(valuesMatier3.get(2).getText());
				m3.setPrix_unitaire(valuesMatier3.get(3).getText());
				insertMatiere(m3);
				clearMatiere02();
				clearMatiere03();
				
			}
		}else {
			insererOfPage01();
			insertMatiere(m2);
			clearMatiere02();
		}
	}
	private void loadMatierePremier5(CaisseMatiere m) throws SQLException {
		ArrayList<TextField> valuesMatier5=GetValuesOfMatiere05();
		Mor2ValuesIsEmpty=dynamique.ValuesEmptyMorThen2OfArray(valuesMatier5,2);
		if(!Mor2ValuesIsEmpty) {
			
			valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesMatier5);
			if(valueNotDouble) {
				clearMatiere05();
				dynamique.alertError("Page 03", "Les valeurs ne doit pas contient un charactere ");	
			}
			else if(idMatier5 ==0) {
				dynamique.alertError("Page 03", "Vueiller Selectionner Matière premier 5");	
				
			}
			else {
				insererOfPage02();
				insertMatiere(m);
				clearMatiere04();	
				CaisseMatiere m5=new CaisseMatiere();
				m5.setId_achat(id);
				m5.setId_matiere(idMatier5);
				m5.setNbr_caisse(Double.parseDouble(valuesMatier5.get(0).getText()));
				m5.setLongueur(valuesMatier5.get(1).getText());
				m5.setLargeur(valuesMatier5.get(2).getText());
				m5.setPrix_unitaire(valuesMatier5.get(3).getText());
				insertMatiere(m5);
				clearMatiere05();
			}
		}else {
			insererOfPage02();
			insertMatiere(m);
			clearMatiere04();
		}
	}
	@FXML
	void btnInserer3(MouseEvent event) {
		try {
			if(connection.isClosed()) {
				connection=DbConnect.getInstance().getConnection();
			}
		
		getId();
		insererOfPage03();
		connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
    void btnInserer1(MouseEvent event) {
		try {
		if(connection.isClosed()) {
			connection=DbConnect.getInstance().getConnection();
		}
		getId();
		insererOfPage01();
		
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	private void getId() {
		try {
			IdAchat=getLastIdOfAchat();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdAchat+2;  
	}
	@FXML
    void btnInserer2(MouseEvent event) throws SQLException {
		try {
			if(connection.isClosed()) {
				connection=DbConnect.getInstance().getConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getId();
		insererOfPage02();
		connection.close();
	}
	ArrayList<String > valuesPage02=new ArrayList<>();
	
	private void insererOfPage01() {
		
//		verfier  page 01 : 
		ArrayList<TextField> valuesPage01=GetValuesOfPage01();
		valueEmpty=dynamique.ValueEmptyOfArrayBool(valuesPage01);
		if(valueEmpty) {
			dynamique.alertError(null, "Vueiller remplire tous les champs");
	       	
		}else if(idFournisseur ==0 || idMatier1 ==0){
			dynamique.alertError("Page 01", "Vueiller Selectionner le fournisseur ou Matière premier 1");
	       	
		}
		else {
			
					valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesPage01);
					if(valueNotDouble) {
						clearPage01();
						dynamique.alertError("Page 01", "Les valeurs ne doit pas contient un charactere ");	
					}else {
						
						try {
							String date=dynamique.getDateOfThisDay();
//							Insert Achat
							Achat a = new Achat();
							a.setId(id);
							a.setId_fournisseur(idFournisseur);
							a.setDate_achat(date);
							a.setLivraison(valuesPage01.get(0).getText());
							insertAchat(a);
//							Insert Versement
							Versement v= new Versement();
							v.setId(id);
							v.setDate_versement(date);
							v.setTypeAchat(0);
							v.setVersement(valuesPage01.get(1).getText());
						   insertVersement(v,connection);
//						   Insert caisse Matiere 1
						   CaisseMatiere m=new CaisseMatiere();
						   m.setId_achat(id);
						   m.setId_matiere(idMatier1);
						   m.setNbr_caisse(Double.parseDouble(valuesPage01.get(2).getText()));
						   m.setLongueur(valuesPage01.get(3).getText());
						   m.setLargeur(valuesPage01.get(4).getText());
						   m.setPrix_unitaire(valuesPage01.get(5).getText());
 						   insertMatiere(m);
 						   
						   dynamique.alertInfo(null, "Votre achat a été bien insérer");
						   clearPage01();
						   
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
		}
	}
	private void insererOfPage02() {
			ArrayList<TextField> valuesPage02=GetValuesOfMatiere02();
			valueEmpty=dynamique.ValueEmptyOfArrayBool(valuesPage02);
		if(valueEmpty) {
			dynamique.alertError("Page 02", "Vueiller remplire tous les champs   ");
		   	
		}else if(idMatier2 ==0){
			dynamique.alertError("Page 02", "Vueiller Selectionner Matière premier 2");
		   	
		}
		else {
					valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesPage02);
					if(valueNotDouble) {
						clearMatiere02();
						dynamique.alertError("Page 02", "Les valeurs ne doit pas contient un charactere ");	
					}else {
						
						try {
		//				   Insert caisse Matiere 2
						   CaisseMatiere m=new CaisseMatiere();
						   m.setId_achat(id);

						   m.setId_matiere(idMatier2);
						   m.setNbr_caisse(Double.parseDouble(valuesPage02.get(0).getText()));
						   m.setLongueur(valuesPage02.get(1).getText());
						   m.setLargeur(valuesPage02.get(2).getText());
						   m.setPrix_unitaire(valuesPage02.get(3).getText());
							 loadMatierePremier3(m);
							   
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}}
}
	private void insererOfPage03() {
		ArrayList<TextField> valuesMatiere04=GetValuesOfMatiere04();
		valueEmpty=dynamique.ValueEmptyOfArrayBool(valuesMatiere04);
		if(valueEmpty) {
			dynamique.alertError("Page 03", "Vueiller remplire tous les champs  ");
			
		}else if(idMatier4 ==0){
			dynamique.alertError("Page 03", "Vueiller Selectionner Matière premier 4");
			
		}
		else {
		valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesMatiere04);
			if(valueNotDouble) {
				clearMatiere04();
				dynamique.alertError("Page 03", "Les valeurs ne doit pas contient un charactere ");	
			}else {

				try {
					//Insert caisse Matiere 2
					CaisseMatiere m=new CaisseMatiere();
					m.setId_achat(id);
					m.setId_matiere(idMatier4);
					m.setNbr_caisse(Double.parseDouble(valuesMatiere04.get(0).getText()));
					m.setLongueur(valuesMatiere04.get(1).getText());
					m.setLargeur(valuesMatiere04.get(2).getText());
					m.setPrix_unitaire(valuesMatiere04.get(3).getText());

					loadMatierePremier5(m);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//			   verifier attribut de matiere premier 5 :
			}}
	}

	
	
	private ArrayList<TextField> GetValuesOfMatiere02(){
		ArrayList<TextField> valuesPage02=new ArrayList<>();
		valuesPage02.add(inputNbrCaisse2);
		valuesPage02.add(inputLongueur2);
		valuesPage02.add(inputLargeur2);
		valuesPage02.add(inputPrix2);
		return valuesPage02;
		}
	private ArrayList<TextField> GetValuesOfPage01(){
		ArrayList<TextField> valuesPage01=new ArrayList<>();
		valuesPage01.add(inputLivraison);
		valuesPage01.add(inputVersement);
		valuesPage01.add(inputNbrCaisse1);
		valuesPage01.add(inputLongueur1);
		valuesPage01.add(inputLargeur1);
		valuesPage01.add(inputPrix1);
		return valuesPage01;
	}
	private ArrayList<TextField> GetValuesOfMatiere03(){

		ArrayList<TextField> valuesPage03=new ArrayList<>();
		valuesPage03.add(inputNbrCaisse3);
		valuesPage03.add(inputLongueur3);
		valuesPage03.add(inputLargeur3);
		valuesPage03.add(inputPrix3);
		return valuesPage03;
	}
	private ArrayList<TextField> GetValuesOfMatiere04(){
		
		ArrayList<TextField> valuesMatiere04=new ArrayList<>();
		valuesMatiere04.add(inputNbrCaisse4);
		valuesMatiere04.add(inputLongueur4);
		valuesMatiere04.add(inputLargeur4);
		valuesMatiere04.add(inputPrix4);
		return valuesMatiere04;
	}
	private ArrayList<TextField> GetValuesOfMatiere05(){
		
		ArrayList<TextField> valuesMatiere04=new ArrayList<>();
		valuesMatiere04.add(inputNbrCaisse5);
		valuesMatiere04.add(inputLongueur5);
		valuesMatiere04.add(inputLargeur5);
		valuesMatiere04.add(inputPrix5);
		return valuesMatiere04;
	}
	private void insertVersement(Versement v,Connection conn) throws SQLException {
		v.insererAchat(conn);
	}
	private void insertMatiere(CaisseMatiere m) throws SQLException {
		m.inserer(connection);
	}
	private void insertAchat(Achat a) throws SQLException {
		a.inserer(connection);
	}
    private void getFournisseur(Connection conn) {
    	try {
 	    	
 		    String sql = "select id_personne, CONCAT(nom,' ',prenom) as fournisseur from personnes"
 		    		+ " where id_type=1";
 		    PreparedStatement status= conn.prepareStatement(sql);
 		    ResultSet rs=status.executeQuery();
 		    while(rs.next()) {
 		    	Personne p = new Personne();
 		    	p.setId(rs.getInt("id_personne"));
 		    	p.setNom(rs.getString("fournisseur"));
 		    	
 		    	dataF.add(p);
 		    	
 		    }
 		   
 		   cmbFournisseur.setItems(dataF);
 		  cmbFournisseur.setConverter(new StringConverter<Personne>() {

 			    public String toString(Personne object) {
 			        return object.getNom();
 			    }

 			    @Override
 			    public Personne fromString(String string) {
 			        return cmbFournisseur.getItems().stream().filter(ap -> 
 			            ap.getNom().equals(string)).findFirst().orElse(null);
 			    }
 			});
 	    }catch(Exception ex) {
 	    	
 	    }
    	cmbFournisseur.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null) {
		    	idFournisseur=newval.getId();
		    }
		    	
		});
		
    }
    private Hashtable<Integer,String> getMatieres(Connection conn) {
         Hashtable<Integer,String> values =new Hashtable<>();
        		
    	try {
    	
    		String sql = "select id_matiere,designation from matiere_premiere";
    		PreparedStatement status= conn.prepareStatement(sql);
    		ResultSet rs=status.executeQuery();
    		while(rs.next()) {
    			
    			values.put(rs.getInt("id_matiere"),rs.getString("designation"));
    		}
    	}catch(Exception ex) {
    		
    	}
    	return values;
    }
    private void getcmbMatieres(ComboBox<Matiere> cmb,ObservableList<Matiere> data) {
      items.forEach((k,v)->{
    	  Matiere m = new Matiere();
    	  m.setId_matiere(k);
    	  m.setDesignation(v);
    	  data.add(m);
      });
      cmb.setItems(data);
       cmb.setConverter(new StringConverter<Matiere>() {
 
		    public String toString(Matiere object) {
		        return object.getDesignation();
		    }
		    @Override
		    public Matiere fromString(String string) {
		        return cmb.getItems().stream().filter(ap -> 
		            ap.getDesignation().equals(string)).findFirst().orElse(null);
		    }
		});
        
     
    }
    private Integer getLastIdOfAchat() throws SQLException {
    	Query="select id_achat from achat ORDER by id_achat DESC LIMIT 1";
    	statement=connection.prepareStatement(Query);
        result=statement.executeQuery();
        if(result.next()) {return result.getInt("id_achat");}else{return 1;}
    }
	private void ValuesCmb1() {
		cmbMatiere1.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null) {
		    idMatier1=newval.getId_matiere();
	        
		    }
		    	
		});
	}
	private void ValuesCmb2() {
		cmbMatiere2.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idMatier2=newval.getId_matiere();
			}
			
		});
	}
	private void ValuesCmb3() {
		cmbMatiere3.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idMatier3=newval.getId_matiere();
			}
			
		});
	}
	private void ValuesCmb4() {
		cmbMatiere4.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idMatier4=newval.getId_matiere();
			}
			
		});
	}
	private void ValuesCmb5() {
		cmbMatiere5.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idMatier5=newval.getId_matiere();
			}
			
		});
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        
		
        getFournisseur(connection);
        getcmbMatieres(cmbMatiere1, cmbData1);
        getcmbMatieres(cmbMatiere2, cmbData2);
        getcmbMatieres(cmbMatiere3, cmbData3);
        getcmbMatieres(cmbMatiere4, cmbData4);
        getcmbMatieres(cmbMatiere5, cmbData5);
        ValuesCmb1();
        ValuesCmb2();
        ValuesCmb3();
        ValuesCmb4();
        ValuesCmb5();
       
	}
	
	private void clearPage01(){
		inputLargeur1.clear();
		inputVersement.clear();
		inputLongueur1.clear();
		inputLivraison.clear();
		inputNbrCaisse1.clear();
		inputPrix1.clear();
		
	}
	private void clearMatiere02(){
		inputLargeur2.clear();
		inputLongueur2.clear();
		inputNbrCaisse2.clear();
		inputPrix2.clear();
	}
	private void clearMatiere03(){
		inputLargeur3.clear();
		inputLongueur3.clear();
		inputNbrCaisse3.clear();
		inputPrix3.clear();
	}
	private void clearMatiere04(){
		inputLargeur4.clear();
		inputLongueur4.clear();
		inputNbrCaisse4.clear();
		inputPrix4.clear();
	}
	private void clearMatiere05(){
		inputLargeur5.clear();
		inputLongueur5.clear();
		inputNbrCaisse5.clear();
		inputPrix5.clear();
	
	}
	

}
