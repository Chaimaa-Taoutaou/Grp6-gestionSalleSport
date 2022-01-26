/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierSalController implements Initializable {

   
    @FXML
    private Button modifiersalle;
    @FXML
    private TextField ns;
    @FXML
    private TextField vs;
    @FXML
    private TextField as;
    @FXML
    private TextField tels;
    @FXML
    private TextField ems;
  

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unit();
    }    
    private void unit(){
        String m=SalleController.m;
      ns.setText(SalleController.n);
      ems.setText(m);
     as.setText(SalleController.ad);
     vs.setText(SalleController.vi);
    tels.setText(SalleController.telephone);
    }
   /* public void updatesal(){
        // String m=SalleController.m;
    connecter c= new connecter();
    int id=c.recup(ns.getText());
    String req="UPDATE salle SET nom_s='"+ ns.getText() +"',adresse='"+ as.getText() +"',ville='"+ vs.getText() +"',tel='"+ tels.getText() +"',email='"+  ems.getText() +"' WHERE  id_s="+ id +"";
    if(c.updatesale(req)){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("");
                alert.setHeaderText(null);
              alert.setContentText("cette salle a ete modifiee");
                   alert.showAndWait();
    }
    
    }*/

    @FXML
    void updatesal() {
        connecter c= new connecter();
        int id=c.recup(ns.getText());
        String req="UPDATE salle SET nom_s='"+ ns.getText() +"',adresse='"+ as.getText() +"',ville='"+ vs.getText() +"',tel='"+ tels.getText() +"',email='"+  ems.getText() +"' WHERE  id_s="+ id +"";
        if(c.updatesale(req)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("cette salle a ete modifiee");
            alert.showAndWait();
        }
    }

}
