
package gestion_sport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class AdduserController implements Initializable {

    private TextField nom;
    private TextField prenom;
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField nomr;
    @FXML
    private TextField prenomr;
    @FXML
    private TextField emailr;
    @FXML
    private Button s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      private void ajouter() {
      String n,pn,e,p;
      pn=prenom.getText();
     e=email.getText();
     n=nom.getText();
       p=pass.getText();
       
      
        if (n.isEmpty() || e.isEmpty() || p.isEmpty() || pn.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("essayez de remplir tous les champs");
            alert.showAndWait();

        }else {
         connecter c =new connecter();
         
          c.adduser(pn, n, e, p);
        }

}}
