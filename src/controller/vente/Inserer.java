package controller.vente;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.BisouRectelign;
import model.Commande;
import model.ListeCommande;
import model.Matiere;
import model.Personne;
import model.Versement;

public class Inserer implements Initializable {
  // page 01  : 
   Double prix_sablage,prix_br,prix_incision,montant_totale,montant_article,montant_br,montant_sablage,montant_incision;
    private DynamiqueView view = new DynamiqueView();
    @FXML
    void btnValider1(MouseEvent event) throws SQLException {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
       insertPage01();
    }
  
    @FXML
    void btnValider2(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
        insertPage02();
    }

    @FXML
    void btnValider3(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
        insertPage03();
    }

    @FXML
    void btnValider4(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage04();
    }

    @FXML
    void btnValider5(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage05();
    }

    @FXML
    void btnValider6(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage06();
    }

    @FXML
    void btnValider7(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage07();
    }

    @FXML
    void btnValider8(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage08();
    }

    @FXML
    void btnValider9(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage09();
    }
    @FXML
    void btnValider10(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage10();
    }
    @FXML
    void btnValider11(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage11();
    }
    @FXML
    void btnValider12(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage12();
    }
    @FXML
    void btnValider13(MouseEvent event) throws SQLException {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage13();
    }

    @FXML
    void btnValider14(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage14();
    }

    @FXML
    void btnValider15(MouseEvent event) {
    	try {
			IdCmd=getLastIdOfCommande();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id=IdCmd+2;
    	insertPage15();
    }

    private Connection connection=DbConnect.getInstance().getConnection();
    private ObservableList<Personne> dataClient=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr1=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr2=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr3=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr4=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr5=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr6=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr7=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr8=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr9=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr10=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr11=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr12=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr13=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr14=FXCollections.observableArrayList();
    private ObservableList<BisouRectelign> dataBr15=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit1=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit2=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit3=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit4=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit5=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit6=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit7=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit8=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit9=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit10=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit11=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit12=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit13=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit14=FXCollections.observableArrayList();
    private ObservableList<Matiere> dataProduit15=FXCollections.observableArrayList();
   
	private Integer idClient=0,idProduit1=0,idProduit14=0,idProduit15=0,idProduit2=0,idProduit3=0,idProduit4=0,idProduit5=0,
    		idProduit6=0,idProduit7=0,idProduit8=0,idProduit9=0,idProduit10=0,idProduit11=0,
    		idProduit12=0,idProduit13=0,IdCmd,id;
	private Integer idBr1=0,idBr2=0,idBr3=0,idBr4=0,idBr5=0,idBr6=0,idBr7=0,idBr8=0,idBr9=0,idBr10=0,
    		idBr11=0,idBr12=0,idBr13=0,idBr14=0,idBr15=0;
    private Integer valueEmpty=0,valueNotDouble=0;
    
    private Hashtable<Integer, String> items=getMatieres();
    private ArrayList<BisouRectelign> itemsBr=getBrs();
    
    private DynamiqueView dynamique=new DynamiqueView();
    private PreparedStatement statement;
    private ResultSet result;
    @FXML
    void fermer(MouseEvent event) throws SQLException {
    	view.closeStage(event);
    	connection.close();
    }
    private void insertPage01() {
    	ArrayList<TextField> valuesPage01=GetValuesOfPage01();
        Double qte=Double.parseDouble(valuesPage01.get(1).getText());
    	valuesPage01.forEach((value)->{
			
			if(value.getText()==null || value.getText().isEmpty()) {
				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
			    valueEmpty++;
			}
		});
		if(valueEmpty !=0) {
			dynamique.alertError("Page 01", "Vueiller Remplire tous les champs qui sont par couleur rouge");
			valueEmpty=0;
		}else {
			valuesPage01.forEach((value)->{
				if(!dynamique.ValidateDouble(value.getText())) {
					valueNotDouble++;
				}
			});
		    if(valueNotDouble !=0) {
    			dynamique.alertError("Page 01", "Les valeurs ne doit pas contient un charactere ");	
		    	valueNotDouble=0;
		    	
		    }else if(idClient ==0 || idProduit1 ==0 || idBr1==0){
				dynamique.alertError("Page 01", "Vueiller Selectionner le fournisseur,Matière premier 1 Ou Bis-Rect");
		       	valueEmpty++;
			}else {
				int comptError=0;
				String sablage=  inputSablage1.getText()==null||inputSablage1.getText().isEmpty() ? "0.0" : inputSablage1.getText();
				String  incision=  inputIncision1.getText()==null||inputIncision1.getText().isEmpty() ? "0.0" : inputIncision1.getText();
				String dateLiv =inputDateLivraison.getValue()==null ? "" :inputDateLivraison.getValue().toString(); 
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ de incision ne doit pas contient un charactere");

				}else {
					try {
//						Insert commande
						Commande c = new Commande();
						c.setId(id);
						c.setId_client(idClient);
						c.setDate_livraison(dateLiv);
						String newDate=dynamique.getDateOfThisDay();
						c.setDateCommande(newDate); 
						insertCmd(c,connection);
//						Insert Versement
						Versement v= new Versement();
						v.setId(id);
						v.setDate_versement(newDate);
						v.setVersement(valuesPage01.get(0).getText());
					   insertVersement(v,connection);
//					   Insert Liste cmd  1
					   ArrayList<Double> values=getPriceOfFonctions(connection,idBr1);
					   prix_br=values.get(0);
					   prix_incision=values.get(1);
					   prix_sablage=values.get(2);
					   ListeCommande l=new ListeCommande();
					   l.setId_commande(id);
					   l.setId_produit(idProduit1);
					   l.setId_br(idBr1);
					   l.setQte(qte);
					   l.setLongueur(Double.parseDouble(valuesPage01.get(2).getText()));
					   l.setLargeur(Double.parseDouble(valuesPage01.get(3).getText()));
					   l.setSablage(Double.parseDouble(sablage));
					   l.setIncision(Double.parseDouble(incision));
					   l.setPrix(Double.parseDouble(valuesPage01.get(4).getText()));
					   l.setBr_bottom(dynamique.RdIschecked(rdBottom1));
					   l.setBr_top(dynamique.RdIschecked(rdTop1));
					   l.setBr_right(dynamique.RdIschecked(rdRight1));
					   l.setBr_left(dynamique.RdIschecked(rdLeft1));
					   montant_article=getMontantArticle(l);
					   montant_br=getMontantBisou(l, prix_br,qte);
					   montant_incision=prix_incision*l.getIncision()*qte;
					   montant_sablage=prix_sablage*l.getSablage()*qte;
					   l.setMontant_article(String.valueOf(montant_article));
					   l.setMontant_br(String.valueOf(montant_br));
					   l.setMontant_incision(String.valueOf(montant_incision));
					   l.setMontant_sablage(String.valueOf(montant_sablage));
					   insertListeCmd(l,connection);                                       
					   valuesPage01.forEach((value)->{
		    				value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
		    		});
					   dynamique.alertInfo(null, "Votre Commande a été bien insérer");
					   clearPage01();
					   
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				
				
			}
		    
		}
		
    }
    private boolean ValidateSablageAndIncision(String entre) {
    	boolean value=true;
    	if(!dynamique.ValidateDouble(entre)  ) {
    		  value=false;
		}
    	return value;
    }
     private Double getMontantArticle(ListeCommande l) {
    	return l.getLargeur()*l.getLongueur()*l.getPrix()*l.getQte();
    }
    private Double getMontantBisou(ListeCommande l,Double prix,Double qte) {
    	Double metrage=0.0;
    	if(l.getBr_bottom()==1) {
    		metrage+=l.getLargeur();
    	}
    	if(l.getBr_top()==1) {
    		metrage+=l.getLargeur();
    	}
    	if(l.getBr_right()==1) {
    		metrage+=l.getLongueur();
    	}
    	if(l.getBr_left()==1) {
    		metrage+=l.getLongueur();
    	}
    	return metrage*prix*qte;
    }

    
    private void insertPage02() {
    	ArrayList<TextField> valuesProduit02=GetValuesOfProduit02();
         Double qte=Double.parseDouble(valuesProduit02.get(0).getText());
    	valuesProduit02.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    			
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 02", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit02.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 02", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit2 ==0 || idBr2==0){
    			dynamique.alertError("Page 02", "Vueiller Selectionner le fournisseur ou Matière premier 1");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage2.getText()==null || inputSablage2.getText().isEmpty()   ? "0.0" : inputSablage2.getText();
				String  incision= inputInscision2.getText()==null||inputInscision2.getText().isEmpty() ? "0.0" : inputInscision2.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {

    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit2);
    				l.setId_br(idBr2);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit02.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit02.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit02.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom2));
    				l.setBr_top(dynamique.RdIschecked(rdTop2));
    				l.setBr_right(dynamique.RdIschecked(rdRight2));
    				l.setBr_left(dynamique.RdIschecked(rdLeft2));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr2);
 				   prix_br=values.get(0);
 				   prix_incision=values.get(1);
 				   prix_sablage=values.get(2);
    				montant_article=getMontantArticle(l);
    				   montant_br=getMontantBisou(l, prix_br,qte);
    				   montant_incision=prix_incision*l.getIncision()*qte;
    				   montant_sablage=prix_sablage*l.getSablage()*qte;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));
    				   insertPage01();
    				insertListeCmd(l,connection);
    				valuesProduit02.forEach((value)->{
    	    				value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    	    		});
    				clearProduit2();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
    private void insertPage03() {
    	ArrayList<TextField> valuesProduit03=GetValuesOfProduit03();
         Double qte=Double.parseDouble(valuesProduit03.get(0).getText());
    	valuesProduit03.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 03", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit03.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 03", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit3 ==0 || idBr3==0){
    			dynamique.alertError("Page 03", "Vueiller Selectionner le fournisseur ou Matière premier 3");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage3.getText()==null|| inputSablage3.getText().isEmpty() ? "0.0" : inputSablage3.getText();
				String  incision=inputInscision3.getText()==null|| inputInscision3.getText().isEmpty() ? "0.0" : inputInscision3.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {

    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit3);
    				l.setId_br(idBr3);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit03.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit03.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit03.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom3));
    				l.setBr_top(dynamique.RdIschecked(rdTop3));
    				l.setBr_right(dynamique.RdIschecked(rdRight3));
    				l.setBr_left(dynamique.RdIschecked(rdLeft3));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr3);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
     				   montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
     				  l.setMontant_article(String.valueOf(montant_article));
   				   l.setMontant_br(String.valueOf(montant_br));
   				   l.setMontant_incision(String.valueOf(montant_incision));
   				   l.setMontant_sablage(String.valueOf(montant_sablage));
    				insertPage02();
    				insertListeCmd(l,connection);
    				valuesProduit03.forEach((value)->{
	    				value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
	    		});
    				clearProduit3();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}    			
    		}
    		
    	}
    	
    }
    private void insertPage04() {
    	ArrayList<TextField> valuesProduit04=GetValuesOfProduit04();
         Double qte=Double.parseDouble(valuesProduit04.get(0).getText());
    	valuesProduit04.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 04", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit04.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 04", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit4 ==0 || idBr4==0 ){
    			dynamique.alertError("Page 04", "Vueiller Selectionner le fournisseur ou Matière premier 4");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage4.getText()==null || inputSablage4.getText().isEmpty() ? "0.0" : inputSablage4.getText();
				String  incision= inputInscision4.getText()==null || inputInscision4.getText().isEmpty() ? "0.0" : inputInscision4.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {


    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit4);
    				l.setId_br(idBr4);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit04.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit04.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit04.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom4));
    				l.setBr_top(dynamique.RdIschecked(rdTop4));
    				l.setBr_right(dynamique.RdIschecked(rdRight4));
    				l.setBr_left(dynamique.RdIschecked(rdLeft4));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr4);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));
     				   insertPage03();
    				insertListeCmd(l,connection);
    				valuesProduit04.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit4();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
    private void insertPage05() {
    	ArrayList<TextField> valuesProduit05=GetValuesOfProduit05();
         Double qte=Double.parseDouble(valuesProduit05.get(0).getText());
    	valuesProduit05.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 05", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit05.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 05", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit5 ==0 || idBr5==0){
    			dynamique.alertError("Page 05", "Vueiller Selectionner le fournisseur ou Matière premier 5");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage5.getText()==null ||inputSablage5.getText().isEmpty() ? "0.0" : inputSablage5.getText();
				String  incision= inputInscision5.getText()==null || inputInscision5.getText().isEmpty() ? "0.0" : inputInscision5.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit5);
    				l.setId_br(idBr5);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit05.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit05.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit05.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom5));
    				l.setBr_top(dynamique.RdIschecked(rdTop5));
    				l.setBr_right(dynamique.RdIschecked(rdRight5));
    				l.setBr_left(dynamique.RdIschecked(rdLeft5));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr5);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));
     		
    				insertPage04();
    				insertListeCmd(l,connection);
    				valuesProduit05.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit5();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
    private void insertPage06() {
    	ArrayList<TextField> valuesProduit06=GetValuesOfProduit06();
         Double qte=Double.parseDouble(valuesProduit06.get(0).getText());
    	valuesProduit06.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 06", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit06.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 06", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit6 ==0 || idBr6==0){
    			dynamique.alertError("Page 06", "Vueiller Selectionner Matière premier 6 ou Bis-Rect");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablag6.getText()==null || inputSablag6.getText().isEmpty() ? "0.0" : inputSablag6.getText();
				String  incision= inputInscision6.getText()==null || inputInscision6.getText().isEmpty() ? "0.0" : inputInscision6.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
					
    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit6);
    				l.setId_br(idBr6);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit06.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit06.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit06.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom6));
    				l.setBr_top(dynamique.RdIschecked(rdTop6));
    				l.setBr_right(dynamique.RdIschecked(rdRight6));
    				l.setBr_left(dynamique.RdIschecked(rdLeft6));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr6);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));

     				   insertPage05();
    				insertListeCmd(l,connection);
    				valuesProduit06.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit6();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
				}
				
    		
    	}
    	
    }
    private void insertPage07() {
    	ArrayList<TextField> valuesProduit07=GetValuesOfProduit07();
         Double qte=Double.parseDouble(valuesProduit07.get(0).getText());
    	valuesProduit07.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 07", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit07.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 07", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit7 ==0 || idBr7==0){
    			dynamique.alertError("Page 07", "Vueiller Selectionner Matière premier 6 ou Bis-Rect");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage7.getText()==null || inputSablage7.getText().isEmpty() ? "0.0" : inputSablage7.getText();
				String  incision= inputInscision7.getText()==null ||  inputInscision7.getText().isEmpty() ? "0.0" : inputInscision7.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit7);
    				l.setId_br(idBr7);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit07.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit07.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit07.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom7));
    				l.setBr_top(dynamique.RdIschecked(rdTop7));
    				l.setBr_right(dynamique.RdIschecked(rdRight7));
    				l.setBr_left(dynamique.RdIschecked(rdLeft7));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr7);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
     				   montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));

     				   insertPage06();
    				insertListeCmd(l,connection);
    				valuesProduit07.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit7();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
    private void insertPage08() {
    	ArrayList<TextField> valuesProduit08=GetValuesOfProduit08();
         Double qte=Double.parseDouble(valuesProduit08.get(0).getText());
    	valuesProduit08.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 08", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit08.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 08", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit8 ==0 || idBr8==0){
    			dynamique.alertError("Page 08", "Vueiller Selectionner Matière premier 6 ou Bis-Rect");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage8.getText()==null || inputSablage8.getText().isEmpty() ? "0.0" : inputSablage8.getText();
				String  incision= inputInscision8.getText()==null || inputInscision8.getText().isEmpty() ? "0.0" : inputInscision8.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit8);
    				l.setId_br(idBr8);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit08.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit08.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit08.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom8));
    				l.setBr_top(dynamique.RdIschecked(rdTop8));
    				l.setBr_right(dynamique.RdIschecked(rdRight8));
    				l.setBr_left(dynamique.RdIschecked(rdLeft8));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr8);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));
    				insertPage07();
    				insertListeCmd(l,connection);
    				valuesProduit08.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit8();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    			}
    			
    		}
    		
    	}
    	
    }
    private void insertPage09() {
    	ArrayList<TextField> valuesProduit09=GetValuesOfProduit09();
         Double qte=Double.parseDouble(valuesProduit09.get(0).getText());
    	valuesProduit09.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 09", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit09.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 09", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit9 ==0 || idBr9==0){
    			dynamique.alertError("Page 09", "Vueiller Selectionner Matière premier 6 ou Bis-Rect");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage9.getText()==null || inputSablage9.getText().isEmpty() ? "0.0" : inputSablage9.getText();
				String  incision= inputInscision9.getText()==null || inputInscision9.getText().isEmpty() ? "0.0" : inputInscision9.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit9);
    				l.setId_br(idBr9);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit09.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit09.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit09.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom9));
    				l.setBr_top(dynamique.RdIschecked(rdTop9));
    				l.setBr_right(dynamique.RdIschecked(rdRight9));
    				l.setBr_left(dynamique.RdIschecked(rdLeft9));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr9);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
     				   montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));
     				   insertPage08();
    				insertListeCmd(l,connection);
    				valuesProduit09.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit9();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
    private void insertPage10() {
    	ArrayList<TextField> valuesProduit10=GetValuesOfProduit10();
         Double qte=Double.parseDouble(valuesProduit10.get(0).getText());
    	valuesProduit10.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 10", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit10.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 10", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit10 ==0 || idBr10==0){
    			dynamique.alertError("Page 10", "Vueiller Selectionner Matière premier 6 ou Bis-Rect");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage10.getText()==null || inputSablage10.getText().isEmpty() ? "0.0" : inputSablage10.getText();
				String  incision= inputInscision10.getText()==null|| inputInscision10.getText().isEmpty() ? "0.0" : inputInscision10.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit10);
    				l.setId_br(idBr10);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit10.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit10.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit10.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom10));
    				l.setBr_top(dynamique.RdIschecked(rdTop10));
    				l.setBr_right(dynamique.RdIschecked(rdRight10));
    				l.setBr_left(dynamique.RdIschecked(rdLeft10));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr10);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
     				   montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));
     				   insertPage09();
    				insertListeCmd(l,connection);
    				valuesProduit10.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit10();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
    private void insertPage11() {
    	ArrayList<TextField> valuesProduit11=GetValuesOfProduit11();
         Double qte=Double.parseDouble(valuesProduit11.get(0).getText());
    	valuesProduit11.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 11", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit11.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 11", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit11 ==0 || idBr11==0){
    			dynamique.alertError("Page 11", "Vueiller Selectionner Matière premier 6 ou Bis-Rect");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage11.getText()==null||inputSablage11.getText().isEmpty() ? "0.0" : inputSablage11.getText();
				String  incision=inputInscision11.getText()==null|| inputInscision11.getText().isEmpty() ? "0.0" : inputInscision11.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo(null, "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit11);
    				l.setId_br(idBr11);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit11.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit11.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit11.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom11));
    				l.setBr_top(dynamique.RdIschecked(rdTop11));
    				l.setBr_right(dynamique.RdIschecked(rdRight11));
    				l.setBr_left(dynamique.RdIschecked(rdLeft11));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr11);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
     				   montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));
     				   insertPage10();
    				insertListeCmd(l,connection);
    				valuesProduit11.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit11();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
	private void insertPage12() {
    	ArrayList<TextField> valuesProduit12=GetValuesOfProduit12();
         Double qte=Double.parseDouble(valuesProduit12.get(0).getText());
    	valuesProduit12.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 12", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		valuesProduit12.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 12", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit12 ==0 || idBr12==0){
    			dynamique.alertError("Page 12", "Vueiller Selectionner Matière premier 6 ou Bis-Rect");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage12.getText()==null || inputSablage12.getText().isEmpty() ? "0.0" : inputSablage12.getText();
				String  incision= inputInscision12.getText()==null ||inputInscision12.getText().isEmpty() ? "0.0" : inputInscision12.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo("Page 12", "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
    			try {
//					Insert commande
//    				Insert Liste cmd  2
    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit12);
    				l.setId_br(idBr12);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(valuesProduit12.get(1).getText()));
    				l.setLargeur(Double.parseDouble(valuesProduit12.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(valuesProduit12.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom12));
    				l.setBr_top(dynamique.RdIschecked(rdTop12));
    				l.setBr_right(dynamique.RdIschecked(rdRight12));
    				l.setBr_left(dynamique.RdIschecked(rdLeft12));
    				ArrayList<Double> values=getPriceOfFonctions(connection,idBr12);
  				   prix_br=values.get(0);
  				   prix_incision=values.get(1);
  				   prix_sablage=values.get(2);
     				montant_article=getMontantArticle(l);
     				   montant_br=getMontantBisou(l, prix_br,qte);
     				   montant_incision=prix_incision*l.getIncision()*qte;
     				   montant_sablage=prix_sablage*l.getSablage()*qte;
     				   montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
    				   l.setMontant_article(String.valueOf(montant_article));
    				   l.setMontant_br(String.valueOf(montant_br));
    				   l.setMontant_incision(String.valueOf(montant_incision));
    				   l.setMontant_sablage(String.valueOf(montant_sablage));
     				   insertPage11();
    				insertListeCmd(l,connection);
    				valuesProduit12.forEach((value)->{
    					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
    				});
    				clearProduit12();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
	private void insertPage13() {
    	ArrayList<TextField> values=GetValuesOfProduit13();
         Double qte=Double.parseDouble(values.get(0).getText());
    	values.forEach((value)->{
    		
    		if(value.getText()==null || value.getText().isEmpty()) {
    			value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			valueEmpty++;
    		}
    	});
    	if(valueEmpty !=0) {
    		dynamique.alertError("Page 13", "Vueiller Remplire tous les champs qui sont par couleur rouge");
    		valueEmpty=0;
    	}else {
    		values.forEach((value)->{
    			if(!dynamique.ValidateDouble(value.getText())) {
    				valueNotDouble++;
    				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
    			}
    		});
    		if(valueNotDouble !=0) {
    			dynamique.alertError("Page 13", "Les valeurs ne doit pas contient un charactere  ");	
    			valueNotDouble=0;
    			
    		}else if(idProduit13 ==0 || idBr13==0){
    			dynamique.alertError("Page 13", "Vueiller Selectionner Matière premier 13 ou Bis-Rect");
    			
    		}else {
    			int comptError=0;
				String sablage= inputSablage13.getText()==null || inputSablage13.getText().isEmpty() ? "0.0" : inputSablage13.getText();
				String  incision= inputInscision13.getText()==null||inputInscision13.getText().isEmpty() ? "0.0" : inputInscision13.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
											}
				}
				if(comptError !=0) {
					dynamique.alertInfo("Page 13", "Le champ incision ou sablage ne doit pas contient un charactere");

				}else {
    			try {
//					Insert commande
//    				Insert Liste cmd  2

    				ListeCommande l=new ListeCommande();
    				l.setId_commande(id);
    				l.setId_produit(idProduit13);
    				l.setId_br(idBr13);
    				l.setQte(qte);
    				l.setLongueur(Double.parseDouble(values.get(1).getText()));
    				l.setLargeur(Double.parseDouble(values.get(2).getText()));
    				l.setSablage(Double.parseDouble(sablage));
    				l.setIncision(Double.parseDouble(incision));
    				l.setPrix(Double.parseDouble(values.get(3).getText()));
    				l.setBr_bottom(dynamique.RdIschecked(rdBottom13));
    				l.setBr_top(dynamique.RdIschecked(rdTop13));
    				l.setBr_right(dynamique.RdIschecked(rdRight13));
    				l.setBr_left(dynamique.RdIschecked(rdLeft13));
    				ArrayList<Double> prices=getPriceOfFonctions(connection,idBr13);
   				   prix_br=prices.get(0);
   				   prix_incision=prices.get(1);
   				   prix_sablage=prices.get(2);
      				montant_article=getMontantArticle(l);
      				   montant_br=getMontantBisou(l, prix_br,qte);
      				   montant_incision=prix_incision*l.getIncision()*qte;
      				   montant_sablage=prix_sablage*l.getSablage()*qte;
      				   montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
     				   l.setMontant_article(String.valueOf(montant_article));
     				   l.setMontant_br(String.valueOf(montant_br));
     				   l.setMontant_incision(String.valueOf(montant_incision));
     				   l.setMontant_sablage(String.valueOf(montant_sablage));
      				   insertPage12();
     				insertListeCmd(l,connection);
     				values.forEach((value)->{
     					value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
     				});
     				clearProduit13();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
				}
    		}
    		
    	}
    	
    }
	private void insertPage14() {
		ArrayList<TextField> values=GetValuesOfProduit14();
                 Double qte=Double.parseDouble(values.get(0).getText());
		values.forEach((value)->{
			
			if(value.getText()==null || value.getText().isEmpty()) {
				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
				valueEmpty++;
			}
		});
		if(valueEmpty !=0) {
			dynamique.alertError("Page 14", "Vueiller Remplire tous les champs qui sont par couleur rouge");
			valueEmpty=0;
		}else {
			values.forEach((value)->{
				if(!dynamique.ValidateDouble(value.getText())) {
					valueNotDouble++;
					value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
				}
			});
			if(valueNotDouble !=0) {
				dynamique.alertError("Page 14", "Les valeurs ne doit pas contient un charactere  ");	
				valueNotDouble=0;
				
			}else if(idProduit14 ==0 || idBr14==0){
				dynamique.alertError("Page 14", "Vueiller Selectionner Matière premier 14 ou Bis-Rect");
				
			}else {
				int comptError=0;
				String sablage= inputSablage14.getText()==null ||inputSablage14.getText().isEmpty() ? "0.0" : inputSablage14.getText();
				String  incision= inputInscision14.getText()==null ||inputInscision14.getText().isEmpty() ? "0.0" : inputInscision14.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
					}
				}
				if(comptError !=0) {
					dynamique.alertInfo("Page 14", "Le champ incision ou sablage ne doit pas contient un charactere");
					
				}else {
					try {
//					Insert commande
//    				Insert Liste cmd  2
						
						ListeCommande l=new ListeCommande();
						l.setId_commande(id);
						l.setId_produit(idProduit14);
						l.setId_br(idBr14);
						l.setQte(qte);
						l.setLongueur(Double.parseDouble(values.get(1).getText()));
						l.setLargeur(Double.parseDouble(values.get(2).getText()));
						l.setSablage(Double.parseDouble(sablage));
						l.setIncision(Double.parseDouble(incision));
						l.setPrix(Double.parseDouble(values.get(3).getText()));
						l.setBr_bottom(dynamique.RdIschecked(rdBottom14));
						l.setBr_top(dynamique.RdIschecked(rdTop14));
						l.setBr_right(dynamique.RdIschecked(rdRight14));
						l.setBr_left(dynamique.RdIschecked(rdLeft14));
						ArrayList<Double> prices=getPriceOfFonctions(connection,idBr14);
						prix_br=prices.get(0);
						prix_incision=prices.get(1);
						prix_sablage=prices.get(2);
						montant_article=getMontantArticle(l);
						montant_br=getMontantBisou(l, prix_br,qte);
						montant_incision=prix_incision*l.getIncision()*qte;
						montant_sablage=prix_sablage*l.getSablage()*qte;
						montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
						l.setMontant_article(String.valueOf(montant_article));
						l.setMontant_br(String.valueOf(montant_br));
						l.setMontant_incision(String.valueOf(montant_incision));
						l.setMontant_sablage(String.valueOf(montant_sablage));
						insertPage13();
						insertListeCmd(l,connection);
						values.forEach((value)->{
							value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
						});
						clearProduit14();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	private void insertPage15() {
		ArrayList<TextField> values=GetValuesOfProduit15();
                
                 Double qte=Double.parseDouble(values.get(0).getText());
		values.forEach((value)->{
			
			if(value.getText()==null || value.getText().isEmpty()) {
				value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
				valueEmpty++;
			}
		});
		if(valueEmpty !=0) {
			dynamique.alertError("Page 15", "Vueiller Remplire tous les champs qui sont par couleur rouge");
			valueEmpty=0;
		}else {
			values.forEach((value)->{
				if(!dynamique.ValidateDouble(value.getText())) {
					valueNotDouble++;
					value.setStyle("-fx-border-color:red;-fx-border-radius: 23px; -fx-background-radius: 23px;");
				}
			});
			if(valueNotDouble !=0) {
				dynamique.alertError("Page 15", "Les valeurs ne doit pas contient un charactere  ");	
				valueNotDouble=0;
				
			}else if(idProduit15 ==0 || idBr15==0){
				dynamique.alertError("Page 15", "Vueiller Selectionner Matière premier 15 ou Bis-Rect");
				
			}else {
				int comptError=0;
				String sablage= inputSablage15.getText()==null ||inputSablage15.getText().isEmpty() ? "0.0" : inputSablage15.getText();
				String  incision= inputInscision15.getText()==null || inputInscision15.getText().isEmpty() ? "0.0" : inputInscision15.getText();
				if(!sablage.equals("0.0")) {
					if(!ValidateSablageAndIncision(sablage)) {
						comptError++;
					}
				}
				if(!incision.equals("0.0")) {
					if(!ValidateSablageAndIncision(incision)) {
						comptError++;
					}
				}
				if(comptError !=0) {
					dynamique.alertInfo("Page 15", "Le champ incision ou sablage ne doit pas contient un charactere");
					
				}else {
					try {
//					Insert commande
//    				Insert Liste cmd  2
						
						ListeCommande l=new ListeCommande();
						l.setId_commande(id);
						l.setId_produit(idProduit15);
						l.setId_br(idBr15);
						l.setQte(qte);
						l.setLongueur(Double.parseDouble(values.get(1).getText()));
						l.setLargeur(Double.parseDouble(values.get(2).getText()));
						l.setSablage(Double.parseDouble(sablage));
						l.setIncision(Double.parseDouble(incision));
						l.setPrix(Double.parseDouble(values.get(3).getText()));
						l.setBr_bottom(dynamique.RdIschecked(rdBottom15));
						l.setBr_top(dynamique.RdIschecked(rdTop15));
						l.setBr_right(dynamique.RdIschecked(rdRight15));
						l.setBr_left(dynamique.RdIschecked(rdLeft15));
						ArrayList<Double> prices=getPriceOfFonctions(connection,idBr15);
						prix_br=prices.get(0);
						prix_incision=prices.get(1);
						prix_sablage=prices.get(2);
						montant_article=getMontantArticle(l);
						montant_br=getMontantBisou(l, prix_br,qte);
						montant_incision=prix_incision*l.getIncision()*qte;
						montant_sablage=prix_sablage*l.getSablage()*qte;
						montant_totale=montant_article+montant_br+montant_incision+montant_sablage;
						l.setMontant_article(String.valueOf(montant_article));
						l.setMontant_br(String.valueOf(montant_br));
						l.setMontant_incision(String.valueOf(montant_incision));
						l.setMontant_sablage(String.valueOf(montant_sablage));
						insertPage14();
						insertListeCmd(l,connection);
						values.forEach((value)->{
							value.setStyle("-fx-border-radius: 23px; -fx-background-radius: 23px;");
						});
						clearProduit15();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
    private ArrayList<Double> getPriceOfFonctions(Connection connection,Integer id_br)  {
    	ArrayList<Double> values =new ArrayList<>();
    	String query="select f1.prix_unitaire as prix_br,f2.prix_unitaire as prix_incision,f3.prix_unitaire as prix_sablage \r\n"
    			+ "from fonctionnalite f1,fonctionnalite f2,fonctionnalite f3 \r\n"
    			+ "where f1.id_type="+id_br+" and f2.id_type=6 and f3.id_type=5 ";
    	try {
    		statement =connection.prepareStatement(query);
    		result= statement.executeQuery();
    		if(result.next()) {
    			values.add(result.getDouble("prix_br"));
    			values.add(result.getDouble("prix_incision"));
    			values.add(result.getDouble("prix_sablage"));
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return values;
    	
    }
    private void insertCmd(Commande c,Connection conn) throws SQLException {
    	c.Inserer(conn);
    }
    private void insertListeCmd(ListeCommande c,Connection conn) throws SQLException {
    	c.inserer(conn);
    }
    private ArrayList<TextField> GetValuesOfPage01(){
		ArrayList<TextField> valuesPage01=new ArrayList<>();
		valuesPage01.add(inputVersement);
		valuesPage01.add(inputQte1);
		valuesPage01.add(inputLongueur1);
		valuesPage01.add(inputLargeur1);
		valuesPage01.add(inputPrix1);
		return valuesPage01;
	}
    private ArrayList<TextField> GetValuesOfProduit02(){
    	ArrayList<TextField> valuesProduit02=new ArrayList<>();
    	valuesProduit02.add(inputQte2);
    	valuesProduit02.add(inputLongueur2);
    	valuesProduit02.add(inputLargeur2);
    	valuesProduit02.add(inputPrix2);
    	return valuesProduit02;
    }
    private ArrayList<TextField> GetValuesOfProduit03(){
    	ArrayList<TextField> valuesProduit03=new ArrayList<>();
    	valuesProduit03.add(inputQte3);
    	valuesProduit03.add(inputLongueur3);
    	valuesProduit03.add(inputLargeur3);
    	valuesProduit03.add(inputPrix3);
    	return valuesProduit03;
    }
    private ArrayList<TextField> GetValuesOfProduit04(){
    	ArrayList<TextField> valuesProduit04=new ArrayList<>();
    	valuesProduit04.add(inputQte4);
    	valuesProduit04.add(inputLongueur4);
    	valuesProduit04.add(inputLargeur4);
    	valuesProduit04.add(inputPrix4);
    	return valuesProduit04;
    }
    private ArrayList<TextField> GetValuesOfProduit05(){
    	ArrayList<TextField> valuesProduit05=new ArrayList<>();
    	valuesProduit05.add(inputQte5);
    	valuesProduit05.add(inputLongueur5);
    	valuesProduit05.add(inputLargeur5);
    	valuesProduit05.add(inputPrix5);
    	return valuesProduit05;
    }
    private ArrayList<TextField> GetValuesOfProduit06(){
    	ArrayList<TextField> valuesProduit05=new ArrayList<>();
    	valuesProduit05.add(inputQte6);
    	valuesProduit05.add(inputLongueur6);
    	valuesProduit05.add(inputLargeur6);
    	valuesProduit05.add(inputSablag6);
    	valuesProduit05.add(inputInscision6);
    	valuesProduit05.add(inputPrix6);
    	return valuesProduit05;
    }
    private ArrayList<TextField> GetValuesOfProduit07(){
    	ArrayList<TextField> valuesProduit07=new ArrayList<>();
    	valuesProduit07.add(inputQte7);
    	valuesProduit07.add(inputLongueur7);
    	valuesProduit07.add(inputLargeur7);
    	valuesProduit07.add(inputPrix7);
    	return valuesProduit07;
    }
    private ArrayList<TextField> GetValuesOfProduit08(){
    	ArrayList<TextField> valuesProduit08=new ArrayList<>();
    	valuesProduit08.add(inputQte8);
    	valuesProduit08.add(inputLongueur8);
    	valuesProduit08.add(inputLargeur8);
    	valuesProduit08.add(inputPrix8);
    	return valuesProduit08;
    }
    private ArrayList<TextField> GetValuesOfProduit09(){
    	ArrayList<TextField> valuesProduit09=new ArrayList<>();
    	valuesProduit09.add(inputQte9);
    	valuesProduit09.add(inputLongueur9);
    	valuesProduit09.add(inputLargeur9);
    	valuesProduit09.add(inputPrix9);
    	return valuesProduit09;
    }
    private ArrayList<TextField> GetValuesOfProduit10(){
    	ArrayList<TextField> valuesProduit10=new ArrayList<>();
    	valuesProduit10.add(inputQte10);
    	valuesProduit10.add(inputLongueur10);
    	valuesProduit10.add(inputLargeur10);
    	valuesProduit10.add(inputPrix10);
    	return valuesProduit10;
    }
    private ArrayList<TextField> GetValuesOfProduit11(){
    	ArrayList<TextField> valuesProduit11=new ArrayList<>();
    	valuesProduit11.add(inputQte11);
    	valuesProduit11.add(inputLongueur11);
    	valuesProduit11.add(inputLargeur11);
    	valuesProduit11.add(inputPrix11);
    	return valuesProduit11;
    }
    private ArrayList<TextField> GetValuesOfProduit12(){
    	ArrayList<TextField> valuesProduit12=new ArrayList<>();
    	valuesProduit12.add(inputQte12);
    	valuesProduit12.add(inputLongueur12);
    	valuesProduit12.add(inputLargeur12);
    	valuesProduit12.add(inputPrix12);
    	return valuesProduit12;
    }
    private ArrayList<TextField> GetValuesOfProduit13(){
    	ArrayList<TextField> valuesProduit13=new ArrayList<>();
    	valuesProduit13.add(inputQte13);
    	valuesProduit13.add(inputLongueur13);
    	valuesProduit13.add(inputLargeur13);
    	valuesProduit13.add(inputPrix13);
    	return valuesProduit13;
    }
    private ArrayList<TextField> GetValuesOfProduit14(){
    	ArrayList<TextField> valuesProduit14=new ArrayList<>();
    	valuesProduit14.add(inputQte14);
    	valuesProduit14.add(inputLongueur14);
    	valuesProduit14.add(inputLargeur14);
    	valuesProduit14.add(inputPrix14);
    	return valuesProduit14;
    }
    private ArrayList<TextField> GetValuesOfProduit15(){
    	ArrayList<TextField> valuesProduit15=new ArrayList<>();
    	valuesProduit15.add(inputQte15);
    	valuesProduit15.add(inputLongueur15);
    	valuesProduit15.add(inputLargeur15);
    	valuesProduit15.add(inputPrix15);
    	return valuesProduit15;
    }
    private void clearPage01() {
    	inputDateLivraison.setValue(null);
    	inputIncision1.setText(null);
    	inputLargeur1.setText(null);
    	inputLongueur1.setText(null);
        inputPrix1.setText(null);
        inputQte1.setText(null);
        inputSablage1.setText(null);
        inputVersement.setText(null);
        rdBottom1.setSelected(false);
        rdTop1.setSelected(false);
        rdRight1.setSelected(false);
        rdLeft1.setSelected(true);
        
    }
    private void clearProduit2() {
    	inputInscision2.setText(null);
    	inputLargeur2.setText(null);
    	inputLongueur2.setText(null);
    	inputPrix2.setText(null);
    	inputQte2.setText(null);
    	inputSablage2.setText(null);
    	rdBottom2.setSelected(false);
    	rdTop2.setSelected(false);
    	rdRight2.setSelected(false);
    	rdLeft2.setSelected(false);
    }
    private void clearProduit3() {
    	inputInscision3.setText(null);
    	inputLargeur3.setText(null);
    	inputLongueur3.setText(null);
    	inputPrix3.setText(null);
    	inputQte3.setText(null);
    	inputSablage3.setText(null);
    	rdBottom3.setSelected(false);
    	rdTop3.setSelected(false);
    	rdRight3.setSelected(false);
    	rdLeft3.setSelected(false);
    }
    
    private void clearProduit4() {
    	inputInscision4.setText(null);
    	inputLargeur4.setText(null);
    	inputLongueur4.setText(null);
    	inputPrix4.setText(null);
    	inputQte4.setText(null);
    	inputSablage4.setText(null);
    	rdBottom4.setSelected(false);
    	rdTop4.setSelected(false);
    	rdRight4.setSelected(false);
    	rdLeft4.setSelected(false);
    }
    private void clearProduit5() {
    	inputInscision5.setText(null);
    	inputLargeur5.setText(null);
    	inputLongueur5.setText(null);
    	inputPrix5.setText(null);
    	inputQte5.setText(null);
    	inputSablage5.setText(null);
    	rdBottom5.setSelected(false);
    	rdTop5.setSelected(false);
    	rdRight5.setSelected(false);
    	rdLeft5.setSelected(false);
    }
    private void clearProduit6() {
    	inputInscision6.setText(null);
    	inputLargeur6.setText(null);
    	inputLongueur6.setText(null);
    	inputPrix6.setText(null);
    	inputQte6.setText(null);
    	inputSablag6.setText(null);
    	rdBottom6.setSelected(false);
    	rdTop6.setSelected(false);
    	rdRight6.setSelected(false);
    	rdLeft6.setSelected(false);
    }
    private void clearProduit7() {
    	inputInscision7.setText(null);
    	inputLargeur7.setText(null);
    	inputLongueur7.setText(null);
    	inputPrix7.setText(null);
    	inputQte7.setText(null);
    	inputSablage7.setText(null);
    	rdBottom7.setSelected(false);
    	rdTop7.setSelected(false);
    	rdRight7.setSelected(false);
    	rdLeft7.setSelected(false);
    }
    
    private void clearProduit8() {
    	inputInscision8.setText(null);
    	inputLargeur8.setText(null);
    	inputLongueur8.setText(null);
    	inputPrix8.setText(null);
    	inputQte8.setText(null);
    	inputSablage8.setText(null);
    	rdBottom8.setSelected(false);
    	rdTop8.setSelected(false);
    	rdRight8.setSelected(false);
    	rdLeft8.setSelected(false);
    }
    
    private void clearProduit9() {
    	inputInscision9.setText(null);
    	inputLargeur9.setText(null);
    	inputLongueur9.setText(null);
    	inputPrix9.setText(null);
    	inputQte9.setText(null);
    	inputSablage9.setText(null);
    	rdBottom9.setSelected(false);
    	rdTop9.setSelected(false);
    	rdRight9.setSelected(false);
    	rdLeft9.setSelected(false);
    }
    
    private void clearProduit10() {
    	inputInscision10.setText(null);
    	inputLargeur10.setText(null);
    	inputLongueur10.setText(null);
    	inputPrix10.setText(null);
    	inputQte10.setText(null);
    	inputSablage10.setText(null);
    	rdBottom10.setSelected(false);
    	rdTop10.setSelected(false);
    	rdRight10.setSelected(false);
    	rdLeft10.setSelected(false);
    }
    
    private void clearProduit11() {
    	inputInscision11.setText(null);
    	inputLargeur11.setText(null);
    	inputLongueur11.setText(null);
    	inputPrix11.setText(null);
    	inputQte11.setText(null);
    	inputSablage11.setText(null);
    	rdBottom11.setSelected(false);
    	rdTop11.setSelected(false);
    	rdRight11.setSelected(false);
    	rdLeft11.setSelected(false);
    }
    
    
    private void clearProduit12() {
    	inputInscision12.setText(null);
    	inputLargeur12.setText(null);
    	inputLongueur12.setText(null);
    	inputPrix12.setText(null);
    	inputQte12.setText(null);
    	inputSablage12.setText(null);
    	rdBottom12.setSelected(false);
    	rdTop12.setSelected(false);
    	rdRight12.setSelected(false);
    	rdLeft12.setSelected(false);
    }
    
    private void clearProduit13() {
    	inputInscision13.setText(null);
    	inputLargeur13.setText(null);
    	inputLongueur13.setText(null);
    	inputPrix13.setText(null);
    	inputQte13.setText(null);
    	inputSablage13.setText(null);
    	rdBottom13.setSelected(false);
    	rdTop13.setSelected(false);
    	rdRight13.setSelected(false);
    	rdLeft13.setSelected(false);
    }
    private void clearProduit14() {
    	inputInscision14.setText(null);
    	inputLargeur14.setText(null);
    	inputLongueur14.setText(null);
    	inputPrix14.setText(null);
    	inputQte14.setText(null);
    	inputSablage14.setText(null);
    	rdBottom14.setSelected(false);
    	rdTop14.setSelected(false);
    	rdRight14.setSelected(false);
    	rdLeft14.setSelected(false);
    }
    private void clearProduit15() {
    	inputInscision15.setText(null);
    	inputLargeur15.setText(null);
    	inputLongueur15.setText(null);
    	inputPrix15.setText(null);
    	inputQte15.setText(null);
    	inputSablage15.setText(null);
    	rdBottom15.setSelected(false);
    	rdTop15.setSelected(false);
    	rdRight15.setSelected(false);
    	rdLeft15.setSelected(false);
    }
    
    private void getClients() {
    	try {
 	    	
 		    String sql = "select id_personne, concat(nom,' ',prenom) as client from personnes"
 		    		+ " where id_type=0";
 		    PreparedStatement status= connection.prepareStatement(sql);
 		    ResultSet rs=status.executeQuery();
 		    while(rs.next()) {
 		    	Personne p = new Personne();
 		    	p.setId(rs.getInt("id_personne"));
 		    	p.setNom(rs.getString("client"));
 		    	
 		    	dataClient.add(p);
 		    	
 		    }
 		   
 		   cmbClient.setItems(dataClient);
 		  cmbClient.setConverter(new StringConverter<Personne>() {

 			    public String toString(Personne object) {
 			        return object.getNom();
 			    }

 			    @Override
 			    public Personne fromString(String string) {
 			        return cmbClient.getItems().stream().filter(ap -> 
 			            ap.getNom().equals(string)).findFirst().orElse(null);
 			    }
 			});
 	    }catch(Exception ex) {
 	    	
 	    }
    	cmbClient.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null) {
		    	idClient=newval.getId();
		    }
		    	
		});
		
    }
    private Hashtable<Integer,String> getMatieres( ) {
         Hashtable<Integer,String> values =new Hashtable<>();
        		
    	try {
    	
    		String sql = "select id_matiere,designation from matiere_premiere";
    		 statement= connection.prepareStatement(sql);
    		result=statement.executeQuery();
    		while(result.next()) {
    			
    			values.put(result.getInt("id_matiere"),result.getString("designation"));
    		}
    	}catch(Exception ex) {
    		
    	}
    	return values;
    }
    private ArrayList<BisouRectelign> getBrs( ) {
    	ArrayList<BisouRectelign> values =new ArrayList<>();
    	
    	try {
    		
    		String sql = "select * from fonctionnalite where type=0";
    	   statement= connection.prepareStatement(sql);
    		result=statement.executeQuery();
    		while(result.next()) {
    			BisouRectelign p = new BisouRectelign();
    			p.setId(result.getInt("id_type"));
    			p.setDesignation(result.getString("activitie"));
    			values.add(p);
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
    private void getcmbBrs(ComboBox<BisouRectelign> cmb,ObservableList<BisouRectelign> data) {
    	itemsBr.forEach((v)->{
    		data.add(v);
    	});
    	cmb.setItems(data);
    	cmb.setConverter(new StringConverter<BisouRectelign>() {
    		
    		public String toString(BisouRectelign object) {
    			return object.getDesignation();
    		}
    		@Override
    		public BisouRectelign fromString(String string) {
    			return cmb.getItems().stream().filter(ap -> 
    			ap.getDesignation().equals(string)).findFirst().orElse(null);
    		}
    	});
    	
    	
    }
    private Integer getLastIdOfCommande() throws SQLException  {
     String	Query="select id_commande from commande ORDER by id_commande DESC LIMIT 1";
    	statement=connection.prepareStatement(Query);
        result=statement.executeQuery();
        if(result.next()) {return result.getInt("id_commande");}else{return 1;}
    }
    private void ValuesCmbBr1() {
    	cmbBisRect1.valueProperty().addListener((obs, oldval, newval) -> {
    		if(newval != null) {
    			idBr1=newval.getId();
    			
    		}
    		
    	});
    }
    private void ValuesCmbBr2() {
    	cmbBisRect2.valueProperty().addListener((obs, oldval, newval) -> {
    		if(newval != null) {
    			idBr2=newval.getId();
    			
    		}
    		
    	});
    }
    private void ValuesCmbBr3() {
    	cmbBisRect3.valueProperty().addListener((obs, oldval, newval) -> {
    		if(newval != null) {
    			idBr3=newval.getId();
    			
    		}
    		
    	});
    }
    private void ValuesCmbBr4() {
    	cmbBisRect4.valueProperty().addListener((obs, oldval, newval) -> {
    		if(newval != null) {
    			idBr4=newval.getId();
    			
    		}
    		
    	});
    }
    private void ValuesCmbBr5() {
    	cmbBisRect5.valueProperty().addListener((obs, oldval, newval) -> {
    		if(newval != null) {
    			idBr5=newval.getId();
    			
    		}
    		
    	});
    }
	private void ValuesCmb1() {
		cmbProduit1.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null) {
		    idProduit1=newval.getId_matiere();
	        
		    }
		    	
		});
    }
	private void ValuesCmb2() {
		cmbProduit2.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit2=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb3() {
		cmbProduit3.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit3=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb4() {
		cmbProduit4.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit4=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb5() {
		cmbProduit5.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit5=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb6() {
		cmbProduit6.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit6=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb7() {
		cmbProduit7.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit7=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb8() {
		cmbProduit8.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit8=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb9() {
		cmbProduit9.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit9=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb10() {
		cmbProduit10.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit10=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb11() {
		cmbProduit11.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit11=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb12() {
		cmbProduit12.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit12=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb13() {
		cmbProduit13.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit13=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb14() {
		cmbProduit14.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit14=newval.getId_matiere();
				
			}
			
		});
	}
	private void ValuesCmb15() {
		cmbProduit15.valueProperty().addListener((obs, oldval, newval) -> {
			if(newval != null) {
				idProduit15=newval.getId_matiere();
				
			}
			
		});
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	   getClients();
       getcmbMatieres(cmbProduit1, dataProduit1);		
       getcmbMatieres(cmbProduit2, dataProduit2);		
       getcmbMatieres(cmbProduit3, dataProduit3);		
       getcmbMatieres(cmbProduit4, dataProduit4);		
       getcmbMatieres(cmbProduit5, dataProduit5);	
       getcmbMatieres(cmbProduit6, dataProduit6);	
       getcmbMatieres(cmbProduit7, dataProduit7);	
       getcmbMatieres(cmbProduit8, dataProduit8);	
       getcmbMatieres(cmbProduit9, dataProduit9);	
       getcmbMatieres(cmbProduit10, dataProduit10);	
       getcmbMatieres(cmbProduit11, dataProduit11);	
       getcmbMatieres(cmbProduit12, dataProduit12);	
       getcmbMatieres(cmbProduit13, dataProduit13);	
       getcmbMatieres(cmbProduit14, dataProduit14);	
       getcmbMatieres(cmbProduit15, dataProduit15);	
       getcmbBrs(cmbBisRect1,dataBr1);
       getcmbBrs(cmbBisRect2,dataBr2);
       getcmbBrs(cmbBisRect3,dataBr3);
       getcmbBrs(cmbBisRect4,dataBr4);
       getcmbBrs(cmbBisRect5,dataBr5);
       getcmbBrs(cmbBisRect6,dataBr6);
       getcmbBrs(cmbBisRect7,dataBr7);
       getcmbBrs(cmbBisRect8,dataBr8);
       getcmbBrs(cmbBisRect9,dataBr9);
       getcmbBrs(cmbBisRect10,dataBr10);
       getcmbBrs(cmbBisRect11,dataBr11);
       getcmbBrs(cmbBisRect12,dataBr12);
       getcmbBrs(cmbBisRect13,dataBr13);
       getcmbBrs(cmbBisRect14,dataBr14);
       getcmbBrs(cmbBisRect15,dataBr15);
       ValuesCmb1();
       ValuesCmb2();
       ValuesCmb3();
       ValuesCmb4();
       ValuesCmb5();
       ValuesCmb6();
       ValuesCmb7();
       ValuesCmb8();
       ValuesCmb9();
       ValuesCmb10();
       ValuesCmb11();
       ValuesCmb12();
       ValuesCmb13();
       ValuesCmb14();
       ValuesCmb15();
       ValuesCmbBr1();
       ValuesCmbBr2();
       ValuesCmbBr3();
       ValuesCmbBr4();
       ValuesCmbBr5();
	}
	private void insertVersement(Versement v,Connection conn) throws SQLException {
		v.insererCmd(conn);
	}
    @FXML
    private ComboBox<BisouRectelign> cmbBisRect1;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect10;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect11;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect13;
    @FXML
    private ComboBox<BisouRectelign> cmbBisRect12;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect14;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect15;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect2;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect3;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect4;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect5;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect6;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect7;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect8;

    @FXML
    private ComboBox<BisouRectelign> cmbBisRect9;

    @FXML
    private ComboBox<Personne> cmbClient;

    @FXML
    private ComboBox<Matiere> cmbProduit1;

    @FXML
    private ComboBox<Matiere> cmbProduit10;

    @FXML
    private ComboBox<Matiere> cmbProduit11;

    @FXML
    private ComboBox<Matiere> cmbProduit12;
    @FXML
    private ComboBox<Matiere> cmbProduit13;

    @FXML
    private ComboBox<Matiere> cmbProduit14;

    @FXML
    private ComboBox<Matiere> cmbProduit15;

    @FXML
    private ComboBox<Matiere> cmbProduit2;

    @FXML
    private ComboBox<Matiere> cmbProduit3;

    @FXML
    private ComboBox<Matiere> cmbProduit4;

    @FXML
    private ComboBox<Matiere> cmbProduit5;

    @FXML
    private ComboBox<Matiere> cmbProduit6;

    @FXML
    private ComboBox<Matiere> cmbProduit7;

    @FXML
    private ComboBox<Matiere> cmbProduit8;

    @FXML
    private ComboBox<Matiere> cmbProduit9;

    @FXML
    private TextField inputIncision1;

    @FXML
    private TextField inputInscision10;

    @FXML
    private TextField inputInscision11;

    @FXML
    private TextField inputInscision12;

    @FXML
    private TextField inputInscision14;

    @FXML
    private TextField inputInscision13;
    @FXML
    private TextField inputInscision15;

    @FXML
    private TextField inputInscision2;

    @FXML
    private TextField inputInscision3;

    @FXML
    private TextField inputInscision4;

    @FXML
    private TextField inputInscision5;

    @FXML
    private TextField inputInscision6;

    @FXML
    private TextField inputInscision7;

    @FXML
    private TextField inputInscision8;

    @FXML
    private TextField inputInscision9;

    @FXML
    private TextField inputLargeur1;

    @FXML
    private TextField inputLargeur10;

    @FXML
    private TextField inputLargeur11;

    @FXML
    private TextField inputLargeur13;
    @FXML
    private TextField inputLargeur12;

    @FXML
    private TextField inputLargeur14;

    @FXML
    private TextField inputLargeur15;

    @FXML
    private TextField inputLargeur2;

    @FXML
    private TextField inputLargeur3;

    @FXML
    private TextField inputLargeur4;

    @FXML
    private TextField inputLargeur5;

    @FXML
    private TextField inputLargeur6;

    @FXML
    private TextField inputLargeur7;

    @FXML
    private TextField inputLargeur8;

    @FXML
    private TextField inputLargeur9;

    @FXML
    private TextField inputLongueur1;

    @FXML
    private TextField inputLongueur10;

    @FXML
    private TextField inputLongueur11;

    @FXML
    private TextField inputLongueur13;
    @FXML
    private TextField inputLongueur12;

    @FXML
    private TextField inputLongueur14;

    @FXML
    private TextField inputLongueur15;

    @FXML
    private TextField inputLongueur2;

    @FXML
    private TextField inputLongueur3;

    @FXML
    private TextField inputLongueur4;

    @FXML
    private TextField inputLongueur5;

    @FXML
    private TextField inputLongueur6;

    @FXML
    private TextField inputLongueur7;

    @FXML
    private TextField inputLongueur8;

    @FXML
    private TextField inputLongueur9;

    @FXML
    private TextField inputPrix1;

    @FXML
    private TextField inputPrix10;

    @FXML
    private TextField inputPrix11;

    @FXML
    private TextField inputPrix12;
    @FXML
    private TextField inputPrix13;

    @FXML
    private TextField inputPrix14;

    @FXML
    private TextField inputPrix15;

    @FXML
    private TextField inputPrix2;

    @FXML
    private TextField inputPrix3;

    @FXML
    private TextField inputPrix4;

    @FXML
    private TextField inputPrix5;

    @FXML
    private TextField inputPrix6;

    @FXML
    private TextField inputPrix7;

    @FXML
    private TextField inputPrix8;

    @FXML
    private TextField inputPrix9;

    @FXML
    private TextField inputQte1;

    @FXML
    private TextField inputQte10;

    @FXML
    private TextField inputQte11;

    @FXML
    private TextField inputQte12;
    @FXML
    private TextField inputQte13;

    @FXML
    private TextField inputQte14;

    @FXML
    private TextField inputQte15;

    @FXML
    private TextField inputQte2;

    @FXML
    private TextField inputQte3;

    @FXML
    private TextField inputQte4;

    @FXML
    private TextField inputQte5;

    @FXML
    private TextField inputQte6;

    @FXML
    private TextField inputQte7;

    @FXML
    private TextField inputQte8;

    @FXML
    private TextField inputQte9;

    @FXML
    private TextField inputSablag6;

    @FXML
    private TextField inputSablage1;

    @FXML
    private TextField inputSablage10;

    @FXML
    private TextField inputSablage11;

    @FXML
    private TextField inputSablage12;
    @FXML
    private TextField inputSablage13;

    @FXML
    private TextField inputSablage14;

    @FXML
    private TextField inputSablage15;

    @FXML
    private TextField inputSablage2;

    @FXML
    private TextField inputSablage3;

    @FXML
    private TextField inputSablage4;

    @FXML
    private TextField inputSablage5;

    @FXML
    private TextField inputSablage7;
    @FXML
    private DatePicker inputDateLivraison;
    @FXML
    private TextField inputSablage8;

    @FXML
    private TextField inputSablage9;

    @FXML
    private TextField inputVersement;

    @FXML
    private CheckBox rdBottom1;

    @FXML
    private CheckBox rdBottom10;

    @FXML
    private CheckBox rdBottom11;

    @FXML
    private CheckBox rdBottom12;
    @FXML
    private CheckBox rdBottom13;

    @FXML
    private CheckBox rdBottom14;

    @FXML
    private CheckBox rdBottom15;

    @FXML
    private CheckBox rdBottom2;

    @FXML
    private CheckBox rdBottom3;

    @FXML
    private CheckBox rdBottom4;

    @FXML
    private CheckBox rdBottom5;

    @FXML
    private CheckBox rdBottom6;

    @FXML
    private CheckBox rdBottom7;

    @FXML
    private CheckBox rdBottom8;

    @FXML
    private CheckBox rdBottom9;

    @FXML
    private CheckBox rdLeft1;

    @FXML
    private CheckBox rdLeft10;

    @FXML
    private CheckBox rdLeft11;

    @FXML
    private CheckBox rdLeft12;
    @FXML
    private CheckBox rdLeft13;

    @FXML
    private CheckBox rdLeft14;

    @FXML
    private CheckBox rdLeft15;

    @FXML
    private CheckBox rdLeft2;

    @FXML
    private CheckBox rdLeft3;

    @FXML
    private CheckBox rdLeft4;

    @FXML
    private CheckBox rdLeft5;

    @FXML
    private CheckBox rdLeft6;

    @FXML
    private CheckBox rdLeft7;

    @FXML
    private CheckBox rdLeft8;

    @FXML
    private CheckBox rdLeft9;

    @FXML
    private CheckBox rdRight1;

    @FXML
    private CheckBox rdRight10;

    @FXML
    private CheckBox rdRight11;

    @FXML
    private CheckBox rdRight12;
    @FXML
    private CheckBox rdRight13;

    @FXML
    private CheckBox rdRight14;

    @FXML
    private CheckBox rdRight15;

    @FXML
    private CheckBox rdRight2;

    @FXML
    private CheckBox rdRight3;

    @FXML
    private CheckBox rdRight4;

    @FXML
    private CheckBox rdRight5;

    @FXML
    private CheckBox rdRight6;

    @FXML
    private CheckBox rdRight7;

    @FXML
    private CheckBox rdRight8;

    @FXML
    private CheckBox rdRight9;

    @FXML
    private CheckBox rdTop1;

    @FXML
    private CheckBox rdTop10;

    @FXML
    private CheckBox rdTop11;

    @FXML
    private CheckBox rdTop12;
    @FXML
    private CheckBox rdTop13;

    @FXML
    private CheckBox rdTop14;

    @FXML
    private CheckBox rdTop15;

    @FXML
    private CheckBox rdTop2;

    @FXML
    private CheckBox rdTop3;

    @FXML
    private CheckBox rdTop4;

    @FXML
    private CheckBox rdTop5;

    @FXML
    private CheckBox rdTop6;

    @FXML
    private CheckBox rdTop7;

    @FXML
    private CheckBox rdTop8;

    @FXML
    private CheckBox rdTop9;
}
