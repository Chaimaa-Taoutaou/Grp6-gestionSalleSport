
package gestion_sport.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    
}
