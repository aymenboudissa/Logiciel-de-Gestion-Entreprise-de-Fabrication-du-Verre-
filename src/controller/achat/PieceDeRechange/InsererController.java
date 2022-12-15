package controller.achat.PieceDeRechange;

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
import model.CaisseProduit;
import model.Personne;
import model.Piece;
import model.Versement;

public class InsererController implements Initializable {

    @FXML
    private ComboBox<Piece> cmbAccessoire1;
    @FXML
    private ComboBox<Piece> cmbAccessoire2;

    @FXML
    private ComboBox<Piece> cmbAccessoire3;

    @FXML
    private ComboBox<Piece> cmbAccessoire4;

    @FXML
    private ComboBox<Piece> cmbAccessoire5;

    @FXML
    private ComboBox<Personne> cmbFournisseur;


    @FXML
    private TextField inputLivraison;

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
    private TextField inputQte1;

    @FXML
    private TextField inputQte2;

    @FXML
    private TextField inputQte3;

    @FXML
    private TextField inputQte4;

    @FXML
    private TextField inputQte5;

    @FXML
    private TextField inputVersement;

    private String Query="";
    private ResultSet result;
    private PreparedStatement statement;
    private ObservableList<Personne> dataF= FXCollections.observableArrayList();
    private Connection connection=DbConnect.getInstance().getConnection();
	private DynamiqueView dynamique= new DynamiqueView();
    private ObservableList<Piece> cmbData1= FXCollections.observableArrayList();
    private ObservableList<Piece> cmbData2= FXCollections.observableArrayList();
    private ObservableList<Piece> cmbData3= FXCollections.observableArrayList();
    private ObservableList<Piece> cmbData4= FXCollections.observableArrayList();
    private ObservableList<Piece> cmbData5= FXCollections.observableArrayList();
    private Hashtable<Integer,String> items=getProduits(connection);
	
	private int id,IdAchat;
	private boolean valueEmpty=false,valueNotDouble=false,Mor2ValuesIsEmpty=false; 
    
    private int idFournisseur=0,idProduit1=0,idProduit2=0,idProduit3,idProduit4=0,idProduit5=0;
 	private void loadProduit3(CaisseProduit p) throws SQLException {
		ArrayList<TextField> valuesMatier3=GetValuesOfProduit03();
		Mor2ValuesIsEmpty=dynamique.ValuesEmptyMorThen2OfArray(valuesMatier3,1);
		if(!Mor2ValuesIsEmpty) {
			valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesMatier3);
			if(valueNotDouble) {
				clearProduit03();
				dynamique.alertError("Page 02", "Les valeurs ne doit pas contient un charactere ");	
			}
			
			else if(idProduit3 ==0) {
				dynamique.alertError("Page 02", "Vueiller Selectionner Produit 3");	
				
			}
			else {
				insererOfPage01();
				try {
//				   Insert caisse Produit 2
					insertProduit(p);
				    clearProduit02();
					   
				} catch (SQLException e) {
					e.printStackTrace();
				}
                CaisseProduit p3=new CaisseProduit();				
				p3.setId_achat(id);
				p3.setId_produit(idProduit3);
				p3.setQuantite(Double.parseDouble(valuesMatier3.get(0).getText()));
				p3.setPrix(valuesMatier3.get(1).getText());
				insertProduit(p3);
				clearProduit03();
				
			}
		}else {
			insererOfPage01();
			try {
//			   Insert caisse Produit 2
				insertProduit(p);
			    clearProduit02();
				   
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private void loadProduit5(CaisseProduit p4) throws SQLException {
		ArrayList<TextField> valuesMatier5=GetValuesOfProduit05();
		Mor2ValuesIsEmpty=dynamique.ValuesEmptyMorThen2OfArray(valuesMatier5,1);
		if(!Mor2ValuesIsEmpty) {
			
			valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesMatier5);
			if(valueNotDouble) {
				clearProduit05();
				dynamique.alertError("Page 03", "Les valeurs ne doit pas contient un charactere ");	
			}
			else if(idProduit5 ==0) {
				dynamique.alertError("Page 03", "Vueiller Selectionner Produit 5");	
				
			}
			else {
				insererOfPage02();
				insertProduit(p4);
				clearProduit04();
				CaisseProduit p5=new CaisseProduit();				
				p5.setId_achat(id);
				p5.setId_produit(idProduit5);
				p5.setQuantite(Double.parseDouble(valuesMatier5.get(0).getText()));
				p5.setPrix(valuesMatier5.get(1).getText());
				insertProduit(p5);
				clearProduit05();
				
				
			}
		}else {
			       insererOfPage02();
				   insertProduit(p4);
				   clearProduit04();
		}
	}
	@FXML
	void btnInserer3(MouseEvent event) throws SQLException {	
		if(connection.isClosed()) {
		connection=DbConnect.getInstance().getConnection();
	}

		getId();   
		insererOfPage03();
		connection.close();
	}
	@FXML
    void btnInserer1(MouseEvent event) throws SQLException {
		if(connection.isClosed()) {
			connection=DbConnect.getInstance().getConnection();
		}
	
		getId();
		insererOfPage01();
		connection.close();
		
    }
	private void getId() {
		try {
			IdAchat=getLastIdOfAchat();
		} catch (SQLException e) {
			e.printStackTrace();       
		}
      	id=IdAchat+2;
	}
	@FXML
    void btnInserer2(MouseEvent event) throws SQLException {
		if(connection.isClosed()) {
			connection=DbConnect.getInstance().getConnection();
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
	       	
		}else if(idFournisseur ==0 || idProduit1 ==0){
			dynamique.alertError("Page 01", "Vueiller Selectionner le fournisseur ou Produit 1");
	       	
		}
		else {
					String date =dynamique.getDateOfThisDay();
					valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesPage01);
					if(valueNotDouble) {
						valuesPage01.clear();
						dynamique.alertError("Page 01", "Les valeurs ne doit pas contient un charactere ");	
					}else {
						
						try {
//							Insert Achat
							Achat a = new Achat();
							a.setId(id);
							a.setId_fournisseur(idFournisseur);
							a.setDate_achat(date);
							a.setLivraison(valuesPage01.get(0).getText());
							a.setType_achat(1);
							insertAchat(a);
//							Insert Versement
							Versement v= new Versement();
							v.setId(id);
							v.setDate_versement(date);
							v.setTypeAchat(1);
							v.setVersement(valuesPage01.get(1).getText());
						   insertVersement(v);
//						   Insert caisse Matiere 1
						   CaisseProduit p = new CaisseProduit();
						   p.setId_achat(id);
						   p.setId_produit(idProduit1);
						   p.setQuantite(Double.parseDouble(valuesPage01.get(2).getText()));
						   p.setPrix(valuesPage01.get(3).getText());
 						   insertProduit(p);
 						   
						   dynamique.alertInfo(null, "Votre achat a été bien insérer");
						   clearPage01();
						   
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
		}
	}
	private void insererOfPage02() {
			ArrayList<TextField> valuesPage02=GetValuesOfProduit02();
			valueEmpty=dynamique.ValueEmptyOfArrayBool(valuesPage02);
		if(valueEmpty) {
			dynamique.alertError("Page 02", "Vueiller remplire tous les champs   ");
		   	
		}else if(idProduit2 ==0){
			dynamique.alertError("Page 02", "Vueiller Selectionner Produit 2");
		   	
		}
		else {
					valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesPage02);
					if(valueNotDouble) {
						clearProduit02();
						dynamique.alertError("Page 02", "Les valeurs ne doit pas contient un charactere ");	
					}else {
//						   verifier attribut de matiere premier 3 :
									try {
//										   Insert caisse Produit 2
										   CaisseProduit p = new CaisseProduit();
										   p.setId_achat(id);
										   p.setId_produit(idProduit2);
										   p.setQuantite(Double.parseDouble(valuesPage02.get(0).getText()));
										   p.setPrix(valuesPage02.get(1).getText());
										   loadProduit3(p);

									} catch (SQLException e) {
										e.printStackTrace();
									}
						
		
 									}}
}
	private void insererOfPage03() {
		ArrayList<TextField> valuesPage03=GetValuesOfProduit04();
		valueEmpty=dynamique.ValueEmptyOfArrayBool(valuesPage03);
		if(valueEmpty) {
			dynamique.alertError("Page 03", "Vueiller remplire tous les champs  ");
			
		}else if(idProduit4 ==0){
			dynamique.alertError("Page 03", "Vueiller Selectionner Produit 4");
			
		}
		else {
			valueNotDouble=dynamique.ValueNotDoubleOfArray(valuesPage03);
			if(valueNotDouble) {
				clearProduit04();
				dynamique.alertError("Page 03", "Les valeurs ne doit pas contient un charactere ");	
			}else {
				try {
					//				   Insert caisse Produit 4
					   CaisseProduit p4 = new CaisseProduit();
					   p4.setId_achat(id);
					   p4.setId_produit(idProduit4);
					   p4.setQuantite(Double.parseDouble(valuesPage03.get(0).getText()));
					   p4.setPrix(valuesPage03.get(1).getText());
					   loadProduit5(p4);	
				} catch (SQLException e) {
					e.printStackTrace();
				
				}
			}
		}
	}

	private void insertVersement(Versement v) throws SQLException {
		v.insererAchat(connection);
	}
	
	private ArrayList<TextField> GetValuesOfProduit02(){
		ArrayList<TextField> valuesPage02=new ArrayList<>();
		valuesPage02.add(inputQte2);
		valuesPage02.add(inputPrix2);
		return valuesPage02;
		}
	private ArrayList<TextField> GetValuesOfPage01(){
		ArrayList<TextField> valuesPage01=new ArrayList<>();
		valuesPage01.add(inputLivraison);
		valuesPage01.add(inputVersement);
		valuesPage01.add(inputQte1);
		valuesPage01.add(inputPrix1);
		return valuesPage01;
	}
	private ArrayList<TextField> GetValuesOfProduit03(){

		ArrayList<TextField> valuesPage03=new ArrayList<>();
		valuesPage03.add(inputQte3);
		valuesPage03.add(inputPrix3);
		return valuesPage03;
	}
	private ArrayList<TextField> GetValuesOfProduit04(){
		
		ArrayList<TextField> valuesMatiere04=new ArrayList<>();
		valuesMatiere04.add(inputQte4);
		valuesMatiere04.add(inputPrix4);
		return valuesMatiere04;
	}
	private ArrayList<TextField> GetValuesOfProduit05(){
		
		ArrayList<TextField> valuesMatiere04=new ArrayList<>();
		valuesMatiere04.add(inputQte5);
		valuesMatiere04.add(inputPrix5);
		return valuesMatiere04;
	}
	private void insertProduit(CaisseProduit p) throws SQLException {
		p.inserer(connection);
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
	    private Hashtable<Integer,String> getProduits(Connection conn) {
	         Hashtable<Integer,String> values =new Hashtable<>();
	        		
	    	try {
	    	
	    		String sql = "select id_accessoire,designation from accessoires";
	    		PreparedStatement status= conn.prepareStatement(sql);
	    		ResultSet rs=status.executeQuery();
	    		while(rs.next()) {
	    			
	    			values.put(rs.getInt("id_accessoire"),rs.getString("designation"));
	    		}
	    	}catch(Exception ex) {
	    		
	    	}
	    	return values;
	    }
	    private void getcmbProduits(ComboBox<Piece> cmb,ObservableList<Piece> data) {
	      items.forEach((k,v)->{
	    	  Piece p =new Piece();
	    	  p.setId_accessoire(k);
	    	  p.setDesignation(v);
	    	  data.add(p);
	      });
	      cmb.setItems(data);
	       cmb.setConverter(new StringConverter<Piece>() {
	 
			    public String toString(Piece object) {
			        return object.getDesignation();
			    }
			    @Override
			    public Piece fromString(String string) {
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
			cmbAccessoire1.valueProperty().addListener((obs, oldval, newval) -> {
			    if(newval != null) {
			    idProduit1=newval.getId_accessoire();
		        
			    }
			    	
			});
		}
		private void ValuesCmb2() {
			cmbAccessoire2.valueProperty().addListener((obs, oldval, newval) -> {
				if(newval != null) {
					idProduit2=newval.getId_accessoire();
				}
				
			});
		}
		private void ValuesCmb3() {
			cmbAccessoire3.valueProperty().addListener((obs, oldval, newval) -> {
				if(newval != null) {
					idProduit3=newval.getId_accessoire();
				}
				
			});
		}
		private void ValuesCmb4() {
			cmbAccessoire4.valueProperty().addListener((obs, oldval, newval) -> {
				if(newval != null) {
					idProduit4=newval.getId_accessoire();
				}
				
			});
		}
		private void ValuesCmb5() {
			
			cmbAccessoire5.valueProperty().addListener((obs, oldval, newval) -> {
				if(newval != null) {
					idProduit5=newval.getId_accessoire();
				}
				
			});
		}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		
			getFournisseur(connection);
	        getcmbProduits(cmbAccessoire1, cmbData1);
	        getcmbProduits(cmbAccessoire2, cmbData2);
	        getcmbProduits(cmbAccessoire3, cmbData3);
	        getcmbProduits(cmbAccessoire4, cmbData4);
	        getcmbProduits(cmbAccessoire5, cmbData5);
	        ValuesCmb1();
	        ValuesCmb2();
	        ValuesCmb3();
	        ValuesCmb4();
	        ValuesCmb5();
		}
		
		private void clearPage01(){
			inputVersement.clear();
			inputLivraison.clear();
			inputQte1.clear();
			inputPrix1.clear();
			
		}
		private void clearProduit02(){
			inputQte2.clear();
			inputPrix2.clear();
		}
		private void clearProduit03(){
			inputQte3.clear();
			inputPrix3.clear();
		}
		private void clearProduit04(){
			inputQte4.clear();
			inputPrix4.clear();
		}
		private void clearProduit05(){
			inputQte5.clear();
			inputPrix5.clear();
		
		}




}
