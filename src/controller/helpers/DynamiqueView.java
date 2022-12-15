package controller.helpers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.achat.MatierePremier.ConsulterController;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import model.Achat;
import model.Employe;
import model.Utilisateur;
import model.Versement;

public class DynamiqueView implements Initializable{
	public Object p;
	private static double xoffset;
	private int valueNotDouble=0,valueEmpty=0,TwoValuesIsEmpty=0;
	private static double yoffset;
	private PreparedStatement statement;
	private ResultSet result;
	private Connection connection=DbConnect.getInstance().getConnection();
	private String query;
	public static DynamiqueView getInstance() {
		 return new DynamiqueView();
	 }
				public static void getPage(Parent root,Stage stage,Scene scene) {
	   			
	   			root.setOnMousePressed(e_fils->{
	   				xoffset=e_fils.getSceneX();
	   				yoffset=e_fils.getSceneY();
	   			});
	   			root.setOnMouseDragged(e->{
	   				stage.setX(e.getScreenX()-xoffset);
	   				stage.setY(e.getScreenY()-yoffset);
	   			});
	   			

	   			stage.setScene(scene);

	            
	   			
	   			stage.centerOnScreen();
	   		  
				}
			    public void Load(AnchorPane anchor,String path) throws IOException {
			    	 anchor.getChildren().clear();
			    	Parent root= FXMLLoader.load(getClass().getResource("/view/"+path+".fxml"));
			        
					 // List should stretch as anchorPane is resized
				    
				     AnchorPane.setTopAnchor(root,0.0);
				     AnchorPane.setLeftAnchor(root, 0.0);
				     AnchorPane.setRightAnchor(root, 0.0);
				    
				     AnchorPane.setTopAnchor(root, 0.0);
				     AnchorPane.setRightAnchor(root, 0.0);
				     anchor.getChildren().add(root);
				}
			    
			    public void LoadPage(String ressource,MouseEvent event) throws IOException {
			    	
			    	try {
						Parent root= FXMLLoader.load(getClass().getResource("/ressource/fxml/"+ressource));
					   Node node=(Node) event.getSource();
					   Stage stage=(Stage) node.getScene().getWindow();
					   stage.setScene(new Scene(root));
			    	 } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
					 // List should stretch as anchorPane is resized
				    
				    
				}
			    public void loadPageFromtable(Parent parent) {
			    	 Stage stage = new Stage();
			            Scene scene=new Scene(parent);
			            stage.setScene(scene);
			            stage.initStyle(StageStyle.TRANSPARENT);
			            getPage(parent,stage,scene);
			            stage.show();

			    }
				 
				   public void LoadPageFree(MouseEvent event,String ressource) throws IOException {
				    	  try {
				              Parent parent = FXMLLoader.load(getClass().getResource("/view/"+ressource+".fxml"));
				              Scene scene = new Scene(parent);
				              Stage stage = new Stage();
				              stage.setScene(scene);
				              stage.initStyle(StageStyle.TRANSPARENT);
				              DynamiqueView.getPage(parent, stage, scene);
				              stage.show();
				          } catch (IOException ex) {
				              Logger.getLogger(DynamiqueView.class.getName()).log(Level.SEVERE, null, ex);
				          }
				          
						  
						  
						
					}
				    public void getPageOfButton(MouseEvent event,String src) {

			    		try {
							Parent root= FXMLLoader.load(getClass().getResource("/view/"+src+".fxml"));				    
							Node node=(Node) event.getSource();
							Stage stage=(Stage) node.getScene().getWindow();
							Scene scene = new Scene(root);
							controller.helpers.DynamiqueView.getPage(root, stage, scene);
							
				    	} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	
			    	
			    	
			    }
				    public void closeStage(MouseEvent event) {
				      	 final Node source = (Node) event.getSource();
				     	  final Stage stage = (Stage) source.getScene().getWindow();
				   	    stage.close();
				    }
				    public void alertError(String header,String message) {
				    	 Alert alert = new Alert(Alert.AlertType.ERROR);
			              alert.setHeaderText(header);
			             alert.setContentText(message);
			             alert.show();
				    }
				    public void ErrorOfEmpty() {
				    	alertError(null, "Vueiller Remplire tous les champs qui sont par couleur rouge");
				    }
				    public void ErrorOfDouble(String header) {
				    	alertError(header, "Les valeurs ne doit pas contient un charactere ");
				    }
				    public void alertInfo(String header,String message) {
				    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
				    	alert.setHeaderText(header);
				    	alert.setContentText(message);
				    	alert.show();
				    }
				    public boolean ValidateInteger(String value) {
					    return value.matches("[0-9]+");
				    }
				    
				    public boolean ValidateDouble(String value) {
				    	return value.matches("([0-9]*[.])?[0-9]+");
				    }
				    
				    public Double changeFormatOfDouble(Double value) {
				    	String set=String.format("%.2f", value);
				    	String val=set.replace(",", ".");
				       return Double.parseDouble(val);
				    }
				    public boolean VerifyUser(Utilisateur user,Connection conn) throws SQLException {
				    			final Connection connection=conn;    	
				    			String query="select * from utilisateurs where user='"+user.getUser()+"' and "
				    					+ "password='"+user.getPassword()+"'";
				    			PreparedStatement statement=connection.prepareStatement(query);	 
				    			ResultSet result=statement.executeQuery();

				    			return result.next();
				    	
				    }
				    public void LoadPageTemporelle(String src) throws IOException {
				 	   Parent parent = FXMLLoader.load(getClass().getResource("/view/"+src+".fxml"));
					   Scene scene=new Scene(parent);
					   Stage popup=new Stage();
					   popup.setScene(scene);
					   PauseTransition delay=new PauseTransition(Duration.seconds(3));
					   delay.setOnFinished(e->popup.hide());
					   popup.show();
					   delay.play();
				    }
				    
				    
				    public void viewOfHboxForButton(HBox managebtn,Hashtable<Button, String> values) {
				    	String style="-fx-background-color:white;-fx-cursor:hand;-fx-opacity:1";
				    	managebtn.setStyle("-fx-opacity:0;-fx-alignment:center");
				        managebtn.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
				        	
							@Override
							public void handle(MouseEvent arg0) {
								// TODO Auto-generated method stub
								managebtn.setStyle("-fx-opacity:1;-fx-alignment:center");
							values.forEach((k,v)->{
								k.setStyle(style);
							});
							}
				        	
						});
				        managebtn.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
				        	
				        	@Override
				        	public void handle(MouseEvent arg0) {
				        		managebtn.setStyle("-fx-opacity:0;-fx-alignment:center");
				        	}
				        	
				        });
				        values.forEach((k,v)->{
							HBox.setMargin(k, new Insets(0,1,0,1));
						});
				        


				    }

				    public void SetGraphiqueIntoButton(Hashtable<Button, String> values) {
				    	values.forEach((k,v)->{
				    		 Image image = new Image(getClass().getResourceAsStream("/images/index/"+v+".png"));
						        
						        ImageView view = new ImageView(image);
						        view.setFitHeight(30);
						        view.setFitWidth(25);
						        k.setGraphic(view);
						        k.setStyle("-fx-background-color:white;-fx-cursor:hand;-fx-opacity:0");
							 
				    	});
				    	       
					     
				    }

				    public void ButtonClick(Button click,String src,Object objet,String className,TableView<?> table) {
				    	click.setOnMouseClicked((MouseEvent event) -> {
				    	
				    		Object o = table.getSelectionModel().getSelectedItem();
				    		if(o == null) {
				    			DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
				    		}else {
				    			String methodName="setTextField";

				    	    	 try {
									Method method=objet.getClass().getMethod(methodName, Object.class);
									try {
										method.invoke(o);
									} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
				    	    	 } catch (NoSuchMethodException e) {
									e.printStackTrace();
								} catch (SecurityException e) {
									e.printStackTrace();
								}
				    			FXMLLoader loader = new FXMLLoader ();
				    			loader.setLocation(getClass().getResource("/view/"+src+".fxml"));
				    			try {
				    				loader.load();
				    			} catch (IOException ex) {
				    				Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
				    			}
				    			Parent parent = loader.getRoot();
				    			loadPageFromtable(parent);
				    		}
				    		
				    		
				    	});
				    	
				    }
				    public String formatDouble(Double value) {
				    	String set=String.format("%.2f", value);
				    	return set;
				    }
				    public ArrayList<TextField> ValueEmptyOfArray(ArrayList<TextField> values) {
				    	 ArrayList<TextField> table=new ArrayList<>();
				    	values.forEach((value)->{
							
							if(value.getText()==null || value.getText().isEmpty()) {
								table.add(value);
								
							}
						});

					    return table;
				    }
				    public boolean ValueEmptyOfArrayBool(ArrayList<TextField> values) {
				    	values.forEach((value)->{
				    		
				    		if(value.getText()==null || value.getText().isEmpty()) {
				    			valueEmpty++;
				    			
				    		}
				    	});
				    	
				    	if(valueEmpty !=0) {
				    		valueEmpty=0;
					    	return true;
					    	}
					    else {
					    	return false;
					    	}
				    }
				    public boolean ValueNotDoubleOfArray(ArrayList<TextField> values) {
					    
				    values.forEach((value)->{
						if(!ValidateDouble(value.getText())) {
							valueNotDouble++;
						}
					});
				    if(valueNotDouble !=0) {
				    	valueNotDouble=0;
				    	return true;
				    	}
				    else {
				    	return false;
				    	}
				    }
				    public boolean ValuesEmptyMorThen2OfArray(ArrayList<TextField> values,Integer maxEmpty) {
				    	values.forEach((value)->{
							if(value.getText().isEmpty()) {
								TwoValuesIsEmpty++;
							}
						});

					    if(TwoValuesIsEmpty !=0 && TwoValuesIsEmpty >maxEmpty ) {
					    	TwoValuesIsEmpty=0;
					    	return true;
					    	}
					    else {return false;}
				    }
				    private Hashtable<Integer, Double> getVersement(Connection connection,Integer type_versement) throws SQLException {
				 	   
				 	   query="select id_etranger ,ROUND(sum(versement),2) as versement from versement_achat \r\n"
				        		+ "where type="+type_versement+" "
				        		+ "group by id_etranger ";
				 	   statement=connection.prepareStatement(query);  
				        result=statement.executeQuery();
				        Hashtable<Integer, Double> values=new Hashtable<>();
				        while(result.next()) {
				     	   values.put(result.getInt("id_etranger"), result.getDouble("versement"));
				        }
				        return values;
				    }

				    public void actualiser(ObservableList<Achat> data,Integer type,String query,Connection conn) throws SQLException {
				      	 data.clear();
				      	 Hashtable<Integer, Double> values=getVersement(connection,type);
				           statement=conn.prepareStatement(query);  
				           result=statement.executeQuery();
				         
				           while(result.next()) {
				           	Achat a = new Achat();
				           	Integer ID=result.getInt("id_achat");
				           	a.setId(ID);
				           	a.setQte(String.valueOf(result.getDouble("qte")) );
				             	a.setDate_achat(result.getString("date_achat"));
				             	a.setLivraison(result.getDouble("livraison")+" DA");
				             	a.setFournisseur(result.getString("fournisseur"));
				             	a.setMontant(result.getDouble("montant")+" DA");
				           	
				             	values.forEach((k,v)->{
				           		if(k==ID) {
				                     	a.setVersement(v+" DA");
				                     	Double sous;
				   					try {
				   						sous = result.getDouble("montant")-v;
				   						a.setMontant_reste(formatDouble(sous)+" DA");
				   					} catch (SQLException e) {
				   						e.printStackTrace();
				   					}
				                     	return;
				           		}
				           		
				           	});
				             	data.add(a);
				           }
				    }
				    public void FilterByDesignation(TextField inputRecherche,ObservableList<Achat> data,TableView<Achat> table) {
						 FilteredList<Achat> filteredData= new FilteredList<>(data,  b ->true);
					    	 inputRecherche.textProperty().addListener((observable,oldValue,newValue)->{
					    		 filteredData.setPredicate(productSearchModel ->{
					    			if(newValue.isEmpty() || newValue.isBlank() ||newValue ==null  ) {
					    				return true;
					    			}
					    			String searchKeyword = newValue.toLowerCase();
					    		if(productSearchModel.getFournisseur().toLowerCase().indexOf(searchKeyword) > -1 ) {
				 	    		 return true ;
					    		}else if(productSearchModel.getDate_achat().toLowerCase().indexOf(searchKeyword) > -1 ) {
					    			return true ;	
					    		}else {
					    			return false;
					    		}
					    		 
					    			
					    		 });
					    	 });
					    	 SortedList<Achat> sortedData = new SortedList<>(filteredData);
					    	 sortedData.comparatorProperty().bind(table.comparatorProperty());
					    	 table.setItems(sortedData);

}
				    public void insererVersement(Versement v) throws SQLException {
				    	Connection connection=DbConnect.getInstance().getConnection();
				    	v.insererAchat(connection);
				    }
				    public String getDateOfThisDay() {
				    	 DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
						 LocalDateTime now=LocalDateTime.now();
						 String NewDate=dtf.format(now);
						 return NewDate;
				    }
				    
				    public int RdIschecked(CheckBox btn) {
				    	
				    	if(btn.isSelected()) {
				    		return 1;
				    	}else {
				    		return 0;
				    	}
				    }
									@Override
					public void initialize(URL arg0, ResourceBundle arg1) {
						
					}
								    public void getEmployes(ComboBox<Employe> cmbEmployes,ObservableList<Employe> data,Connection conn) {
								    	try {
								 	    	
								 		    String sql = "select id_employe, CONCAT(nom_employe,' ',prenom_employe) as employe from employes";
								 		    PreparedStatement status= conn.prepareStatement(sql);
								 		    ResultSet rs=status.executeQuery();
								 		    while(rs.next()) {
								 		    	Employe e = new Employe();
								 		    	e.setId(rs.getInt("id_employe"));
								 		    	e.setNom(rs.getString("employe"));
								 		    	data.add(e);
								 		    }
								 		   
								 		   cmbEmployes.setItems(data);
								 		  cmbEmployes.setConverter(new StringConverter<Employe>() {

								 			    public String toString(Employe object) {
								 			        return object.getNom();
								 			    }

								 			    @Override
								 			    public Employe fromString(String string) {
								 			        return cmbEmployes.getItems().stream().filter(ap -> 
								 			            ap.getNom().equals(string)).findFirst().orElse(null);
								 			    }
								 			});
								 	    }catch(Exception ex) {
								 	    	
								 	    }
								    	
										
								    }
								    public void getLogo(Rectangle rectangle) {
										Connection conn=DbConnect.getInstance().getConnection();
										String query="select logo from entreprise "
                                                                                        + "order by id DESC limit 1";
										PreparedStatement statement;
										try {
											statement = conn.prepareStatement(query);
											ResultSet set=statement.executeQuery();
											if(set.next()) {
                                                                                             String path  = new java.io.File(".").getCanonicalPath();
												Image img=new Image(path+set.getString("logo"));
												ImagePattern patt=new ImagePattern(img);
												rectangle.setFill(patt);
										}
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										
										} catch (IOException ex) {
                Logger.getLogger(DynamiqueView.class.getName()).log(Level.SEVERE, null, ex);
            }
										}
  public void getSemaine(ObservableList<model.Date> dataSemaine,ComboBox<model.Date> cmbSemaine) {
    	Hashtable<Integer, String> values=controller.humaine.paiement.ConsulterController.getInstance().getValuesOfSemaine();
	    values.forEach((k,v)->{
	    	model.Date d = new model.Date();
	    	d.setId_semaine(k);
	    	d.setSemaine(v);
	    	dataSemaine.add(d);
	    });
    		cmbSemaine.setItems(dataSemaine);
    		cmbSemaine.setConverter(new StringConverter<model.Date>() {
			    public String toString(model.Date object) {
			        return object.getSemaine();
			    }
			    @Override
			    public model.Date fromString(String string) {
			        return cmbSemaine.getItems().stream().filter(ap -> 
			            ap.getSemaine().equals(string)).findFirst().orElse(null);
			    }
			});
    		
        }
	
}
