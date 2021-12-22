
package gestion_sport.Controller;


import gestion_sport.Model.connecter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    

    @FXML
    private PasswordField pass;

    @FXML
    private   TextField usernamuser;
     static String em,p;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
      
    }    
    @FXML
     public void connexion() throws IOException{
     connecter c=new connecter();
     boolean testConnexion =c.existe(usernamuser.getText(),pass.getText());
     if(testConnexion == true){
         em=usernamuser.getText();
         p=pass.getText();
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage dashboard = new Stage();
            dashboard.setScene(scene);
            //dashboard.setResizable(false);
            dashboard.setTitle("   Accueil");
            dashboard.show();}
     else{
     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("mot de passe ou email invalide");
            alert.showAndWait();
     
     }
    
     
}}
