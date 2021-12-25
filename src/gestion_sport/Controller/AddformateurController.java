
package gestion_sport.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import gestion_sport.Model.connecter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddformateurController implements Initializable {

    @FXML
    private Button addformateur;
    @FXML
    private TextField nf;
    @FXML
    private TextField pf;
    @FXML
    private TextField ef;
    @FXML
    private TextField af;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void vider(){
    
        nf.setText("");
         pf.setText("");
          ef.setText("");
           af.setText("");
    
    }
    
    
    public  void ajouterformateur() {
        String n,p,e,a;
          n=nf.getText();
         p=pf.getText();
         e=ef.getText();
         a=af.getText();
        
          if (n.isEmpty() || p.isEmpty() || e.isEmpty() || a.isEmpty()) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setHeaderText(null);
              alert.setContentText("essayez de remplir tous les champs");
              alert.showAndWait();

          }else {
           connecter c =new connecter();
             if(c.addformateur(n,p,e,a)==true){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("");
                  alert.setHeaderText(null);
                alert.setContentText("Un formateur a �t� ajouter");

                     alert.showAndWait();
                     
                     
             }else {
             System.out.println("formateur n'est pas ajouter");
             
             }
          
          }
    	}
    
    
}
