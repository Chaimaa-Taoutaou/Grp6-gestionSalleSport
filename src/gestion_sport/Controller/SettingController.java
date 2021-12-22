/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SettingController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField passc;
    @FXML
    private PasswordField passn;
    @FXML
    private PasswordField pass;
connecter c = new connecter();
  String m;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       unit();
      passc.setStyle("-fx-alignment : CENTER;");
    }    
    void unit(){
       m=LoginController.em;
       username.setText(m);
      pass.setText(LoginController.p);
    
    }
   public void updatuser(){
      int id=c.recupid(m);
        String req="UPDATE admin set usernam='"+ username.getText() +"' ,password='"+ pass.getText() +"' wher id="+ id +"";
        if(passc.getText().equals(passn.getText())){
        if(c.updatuser(req)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("");
                alert.setHeaderText(null);
              alert.setContentText("vos informations sont modifiees");

                   alert.showAndWait();}
       
        }
    }
}
