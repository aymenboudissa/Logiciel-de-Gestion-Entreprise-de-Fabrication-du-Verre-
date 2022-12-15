package controller.vente;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import controller.helpers.DynamiqueView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Rupture;

public class RuptureController {

    @FXML
    private TextField idList;

    @FXML
    private TextField inputDescription;

    @FXML
    private TextField inputIdCmd;
   private Connection connection;
    private ResultSet result;
    @FXML
    private TextField inputQte;
    private DynamiqueView view=new DynamiqueView();
    public void setTextFieled(Integer id,Connection conn) {
    	inputIdCmd.setText(String.valueOf(id));
    	connection=conn;
    }
    @FXML
    void btnFermer(MouseEvent event) {
    	view.closeStage(event);
    }

    @FXML
    void btnValider(MouseEvent event) throws SQLException {
    	String qte=inputQte.getText();
    	if(inputQte.getText().isEmpty()) {
    		view.alertError(null, "Vueiller remplire la Qte de rupture");
    	}else if(!view.ValidateDouble(qte)) {
    		view.alertError(null, "la Qte de rupture ne doit pas contient un character");
    		
    	}else {
              Double quantite=Double.parseDouble(qte);
    		Rupture r=new Rupture ();
                Integer idcmd=Integer.parseInt(inputIdCmd.getText());
                Integer idlist=Integer.parseInt(idList.getText());
    		r.setIdCommande(idcmd);
    		r.setIdList(idlist);
    		r.setDateRupture(view.getDateOfThisDay());
    		r.setDescritpion(inputDescription.getText());
    		r.setQte(getDim(idcmd, idlist)*quantite);
    		r.inserer(connection);
    		view.alertInfo(null, "Votre Rupture a été bien inserer");
    		view.closeStage(event);
    	}
    }
    private Double getDim(Integer idCmd,Integer idList) throws SQLException{
        Double value=0.0;
        String query="select ROUND(longueur*largeur,2) as dimension  from list_commande "
                + "where id_commande="+idCmd+" and id_list="+idList+" ";
        
    PreparedStatement st=connection.prepareStatement(query);
    ResultSet set= st.executeQuery();
    while(set.next()){
           value=set.getDouble("dimension");
           
     
     }; 
     return value;
    }
}


