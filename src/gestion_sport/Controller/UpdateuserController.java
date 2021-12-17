
package Classes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdateuserController implements Initializable {

   
    @FXML
    private TextField nm;
    @FXML
    private TextField pnm;
    @FXML
    private TextField em;
    @FXML
    private TextField pm;
  
  

    
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
}