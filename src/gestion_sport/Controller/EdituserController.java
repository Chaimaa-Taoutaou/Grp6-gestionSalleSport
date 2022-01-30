package gestion_sport.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;

import gestion_sport.Model.connecter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EdituserController implements Initializable {
    @FXML
    private TextField nm;
    @FXML
    private TextField pnm;
    @FXML
    private TextField em;
    @FXML
    private Button deconecte;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unit();
    }
    private void unit(){
        String m=userController.emailuser;
        pnm.setText(userController.pu);
        em.setText(m);
        nm.setText(userController.nu);

    }

    @FXML
    private void quitter(MouseEvent event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    //UPDATE `utilisateur` SET `nom_u` = 'a', `prenom_u` = 'a', `email_u` = 'a', `password_u` = 'a' WHERE `utilisateur`.`id_u` = 143;

   @FXML
   private void updateuser(MouseEvent mouseEvent){



       connecter c= new connecter();

       int id=c.iduser(userController.emailuser);
       String req="UPDATE `utilisateur` SET `nom_u` ='"+ nm.getText() +"', `prenom_u` = '"+ pnm.getText() +"', `email_u` = '"+ em.getText() +"' WHERE  `id_u` = "+ id +"";

       //String req="UPDATE salle SET nom_s='"+ ns.getText() +"',adresse='"+ as.getText() +"',ville='"+ vs.getText() +"',tel='"+ tels.getText() +"',email='"+  ems.getText() +"' WHERE  id_s="+ id +"";
       if(c.updateuser(req)){
           final Node source = (Node) mouseEvent.getSource();
           final Stage stage = (Stage) source.getScene().getWindow();
           stage.close();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("");
           alert.setHeaderText(null);
           //initStyle
           alert.initStyle(StageStyle.UNDECORATED);
           alert.setContentText("cet utilisateur a ete modifiee");
           alert.showAndWait();
       }else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("");
           alert.setHeaderText(null);
           alert.setContentText(String.valueOf(id));
           alert.showAndWait();

       }

   }


}





