/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_sport.Controller;

import gestion_sport.Model.connecter;

import java.awt.event.InputEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    @FXML private javafx.scene.control.Button closeButton;

    
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


    public void exit(MouseEvent mouseEvent) {
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void updatesal(MouseEvent mouseEvent) {



            // String m=SalleController.m;
            connecter c= new connecter();

            int id=c.recup(SalleController.n);
            String req="UPDATE salle SET nom_s='"+ ns.getText() +"',adresse='"+ as.getText() +"',ville='"+ vs.getText() +"',tel='"+ tels.getText() +"',email='"+  ems.getText() +"' WHERE  id_s="+ id +"";
            if(c.updatesale(req)){
                final Node source = (Node) mouseEvent.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("cette salle a ete modifiee");
                alert.showAndWait();
            }


        }

}
