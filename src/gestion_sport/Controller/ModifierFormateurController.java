/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_sport.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import gestion_sport.Model.connecter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierFormateurController implements Initializable {

    @FXML
    private TextField nomf;
    @FXML
    private TextField prenomf;
    @FXML
    private TextField emailf;
    @FXML
    private TextField adressef;
    @FXML
    private Button modifierformateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unit();
    }    
    
    
    
    private void unit(){
        String m=FormateurController.m;
      nomf.setText(FormateurController.n);
      prenomf.setText(FormateurController.pr);
      emailf.setText(m);
     adressef.setText(FormateurController.ad);
    }
    public void updateformateur(){
        // String m=FormateurController.m;
    connecter c= new connecter();
    int id=c.recup_formateur(nomf.getText());
    String req="UPDATE formateur SET nom_f='"+ nomf.getText() +"',prenom_f='"+ prenomf.getText() + "',email_f='"+  emailf.getText() +"',adresse_f='"+ adressef.getText() + "' WHERE  id_f="+ id +"";
    if(c.updateformateur(req)){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("");
                alert.setHeaderText(null);
              alert.setContentText("ce formateur a ete modifiee");
                   alert.showAndWait();
    }
    
    }
    
    
}
