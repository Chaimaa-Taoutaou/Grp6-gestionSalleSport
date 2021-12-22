
package gestion_sport;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddsalleController implements Initializable {

    @FXML
    private Button addsal;
    @FXML
    private TextField ns;
    @FXML
    private TextField vs;
    @FXML
    private TextField ad;
    @FXML
    private TextField tels;
    @FXML
    private TextField es;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void vider(){
    
        ns.setText("");
         vs.setText("");
          ad.setText("");
           tels.setText("");
            es.setText("");
    
    }
    
    
}
