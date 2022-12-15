package controller.vente;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.helpers.DbConnect;
import controller.helpers.DynamiqueView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Commande;

public class ConsulterController implements Initializable {
	private DynamiqueView view = new DynamiqueView();
	@FXML
	private TableColumn<Commande, String> columnButtons;

	@FXML
	private TableColumn<Commande, String> columnClient;

	@FXML
	private TableColumn<Commande, String> columnDateCmd;
	@FXML
	private TableColumn<Commande, String> columnLivraison;

	@FXML
	private TableColumn<Commande, String> columnMontant;

	@FXML
	private TableColumn<Commande, String> columnMontantReste;

	@FXML
	private TableColumn<Commande, Integer> columnNumCmd;

	@FXML
	private TableColumn<Commande, String> columnVersement;

	@FXML
	private TextField inputRecherche;

	@FXML
	private TableView<Commande> table;
	private String query;
	private PreparedStatement statement;
	private ResultSet set;
	private Connection connection=getConnection();
	private ObservableList<Commande> dataCmd = FXCollections.observableArrayList();

	@FXML
	void btnActualiser(MouseEvent event) throws SQLException {
		actualiser();
	}
	private Connection getConnection() {
		return DbConnect.getInstance().getConnection();	
	}
	private void actualiser() throws SQLException {
		if(connection.isClosed()) {
			connection=getConnection();
		}
		dataCmd.clear();
		query = "select c.id_commande,c.date_commande,c.id_personne,c.date_livraison,CONCAT(p.nom,' ',p.prenom) as client, \r\n"
				+ "ROUND(sum(l.montant_article+ l.montant_br+ l.montant_incision+l.montant_sablage),2) as totale,ROUND(sum(l.quantite),2) as qte_totale  \r\n"
				+ "from list_commande l,commande c ,personnes p \r\n"
				+ "where c.id_personne=p.id_personne and c.id_commande=l.id_commande \r\n"
				+ "GROUP by c.id_commande,c.date_commande,c.id_personne,client \r\n";
		PreparedStatement statement = connection.prepareStatement(query);
		 ResultSet set = statement.executeQuery();
		while (set.next()) {
			Commande c = new Commande();
			c.setQte_totale(set.getDouble("qte_totale"));
			Integer idCmd = set.getInt("id_commande");
			c.setId(idCmd);
			c.setId_client(set.getInt("id_personne"));
			c.setClient(set.getString("client"));
			c.setDateCommande(set.getString("date_commande"));
			c.setDate_livraison(set.getString("date_livraison"));
			Double totale = set.getDouble("totale");
			c.setMontant(totale + "DA");
			Double verser = getVersement(idCmd, connection);
			Double reste = totale - verser;
			c.setMontantReste(view.formatDouble(reste)+"DA");
			c.setVersement(verser + "DA");
			dataCmd.add(c);
		}
	
        }

	private Double getVersement(Integer id, Connection conn) throws SQLException {
		Double v = 0.0;
		query = "select v.id_etranger,sum(v.versement) as verser from versement_cmd v\r\n" + "where v.id_etranger=" + id
				+ " \r\n" + "group by v.id_etranger \r\n";
		statement = conn.prepareStatement(query);
		set = statement.executeQuery();
		if (set.next()) {
			v = set.getDouble("verser");
		}
		return v;
	}

	private void getStylishOfColumn() {
		String style = "-fx-font-size:12px;-fx-alignment:center";
		columnClient.setStyle(style);
		columnDateCmd.setStyle(style);
		columnNumCmd.setStyle(style);
		columnLivraison.setStyle(style);
		style += ";-fx-font-weight:bold";
		columnMontant.setStyle(style);
		columnMontantReste.setStyle(style);
		columnVersement.setStyle(style);
	}

	private void load() throws SQLException {
		actualiser();
		loadLignToColumn();
	}

	private void loadLignToColumn() {
		columnClient.setCellValueFactory(new PropertyValueFactory<Commande, String>("client"));
		columnMontantReste.setCellValueFactory(new PropertyValueFactory<Commande, String>("montantReste"));
		columnMontant.setCellValueFactory(new PropertyValueFactory<Commande, String>("montant"));
		columnDateCmd.setCellValueFactory(new PropertyValueFactory<Commande, String>("dateCommande"));
		columnLivraison.setCellValueFactory(new PropertyValueFactory<Commande, String>("Date_livraison"));
		columnNumCmd.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
		columnVersement.setCellValueFactory(new PropertyValueFactory<Commande, String>("versement"));
		Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFoctory = (
				TableColumn<Commande, String> param) -> {
			// make cell containing buttons
			final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
						setText(null);

					}
					Button deleteIcon = new Button();
					Button OrdreIcon = new Button();
					Button VersementIcon = new Button();
					Button Rupture = new Button();
					Hashtable<Button, String> values = new Hashtable<Button, String>();
					values.put(OrdreIcon, "consulter");
					values.put(deleteIcon, "delete");
					values.put(VersementIcon, "versement");
					values.put(Rupture, "rupture");
					view.SetGraphiqueIntoButton(values);

					Delete(deleteIcon);
					Versement(VersementIcon);
					Ordre(OrdreIcon);
					retirer(Rupture);

					HBox managebtn = new HBox();
					managebtn.getChildren().addAll(OrdreIcon, deleteIcon, VersementIcon, Rupture);
					view.viewOfHboxForButton(managebtn, values);
					setGraphic(managebtn);
					setText(null);
				}
			};

			return cell;
		};

		columnButtons.setCellFactory(cellFoctory);
		table.setItems(dataCmd);

		FilterByDesignation();

	}

	public void FilterByDesignation() {
		FilteredList<Commande> filteredData = new FilteredList<>(dataCmd, b -> true);
		inputRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productSearchModel -> {
				if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if (productSearchModel.getClient().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				} else if (productSearchModel.getDateCommande().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				} else if (productSearchModel.getDate_livraison().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				} else {
					return false;
				}

			});
		});
		SortedList<Commande> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);

	}

	private void Delete(Button delete) {
		delete.setOnMouseClicked((MouseEvent event) -> {

			try {
				Commande m = table.getSelectionModel().getSelectedItem();
				if (m == null) {
					DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
				} else {
					if(connection.isClosed()) {
						connection=getConnection();
					}
					query = "DELETE FROM `commande` WHERE id_commande  =" + m.getId();
					statement = connection.prepareStatement(query);
					statement.execute();
					actualiser();
				}

			} catch (SQLException ex) {
				Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

	}

	private void Ordre(Button click) {
		click.setOnMouseClicked((MouseEvent event) -> {

			Commande a = table.getSelectionModel().getSelectedItem();
			if (a == null) {
				DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
			} else {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/vente/ordre.fxml"));
				try {
					loader.load();
				} catch (IOException ex) { 
					Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
				}
				Ordre add = loader.getController();
				try {
					if(connection.isClosed()) {
						connection=getConnection();
					}
					add.setTextFieled(a, connection);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Parent parent = loader.getRoot();
				DynamiqueView.getInstance().loadPageFromtable(parent);
			}

		});

	}

	private void Versement(Button click) {
		click.setOnMouseClicked((MouseEvent event) -> {

			Commande a = table.getSelectionModel().getSelectedItem();
			if (a == null) {
				DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
			} else {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/vente/versement.fxml"));
				try {
					if(connection.isClosed()) {
						connection=getConnection();
					}
					loader.load();
				} catch (IOException ex) {
					Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Verser add = loader.getController();
				
				add.setTextFieled(a.getId(), connection);
				Parent parent = loader.getRoot();
				DynamiqueView.getInstance().loadPageFromtable(parent);
			}

		});

	}

	private void retirer(Button click) {
		click.setOnMouseClicked((MouseEvent event) -> {

			Commande a = table.getSelectionModel().getSelectedItem();
			if (a == null) {
				DynamiqueView.getInstance().alertError(null, "Veuiller selecter une ligne");
			} else {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/vente/rupture.fxml"));
				try {
					if(connection.isClosed()) {
						connection=getConnection();
					}
					loader.load();
				} catch (IOException ex) {
					Logger.getLogger(ConsulterController.class.getName()).log(Level.SEVERE, null, ex);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RuptureController add = loader.getController();
				
				add.setTextFieled(a.getId(), connection);
				Parent parent = loader.getRoot();
				DynamiqueView.getInstance().loadPageFromtable(parent);
			}

		});

	}

	@FXML
	void btnInserer(MouseEvent event) throws IOException, SQLException {
		view.LoadPageFree(event, "vente/inserer");
		connection.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getStylishOfColumn();

	}

}
