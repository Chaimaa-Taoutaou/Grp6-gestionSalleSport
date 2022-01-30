
package gestion_sport.Controller;


import gestion_sport.Model.connecter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AdduserController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    private AnchorPane an;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //an.setStyle("-fx-border-radius: 30;");
    }

    @FXML
    private void ajouter(MouseEvent event) {
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


            if( c.adduser(pn, n, e, p)==true){
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);

            alert.setContentText("Un utilisateur a été ajouté");

            alert.showAndWait();


            }
        }

    }

    @FXML
    private void quitter(MouseEvent event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}




