package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static gestion_sport.Controller.AddadhController.se;

public class EditadhrentController implements Initializable {
    @FXML
    private ComboBox<type_Abonnement> abon;

    @FXML
    private TextField adr;

    @FXML
    private TextField cin;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton f=new RadioButton("F");

    @FXML
    private RadioButton m=new RadioButton("M");

    private String radioButtonLabel;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField tel;

    public void leave(MouseEvent mouseEvent) {
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    static final Integer[] sform = new Integer[1];
    connecter c = new connecter();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup gender=new ToggleGroup();
        m.setToggleGroup(gender);
        f.setToggleGroup(gender);
        m.setOnAction(e->{
            radioButtonLabel=m.getText();
        });
        f.setOnAction(e->{
            radioButtonLabel=f.getText();
        });
        try {
            cin.setText(AdherentController.b);
            nom.setText(AdherentController.a);
            prenom.setText(AdherentController.c);
            tel.setText(AdherentController.d);
            adr.setText(AdherentController.g);


            ObservableList<type_Abonnement> listeabon = FXCollections.observableArrayList();
            ResultSet rs = c.getabon();

            while (rs.next()) {
                listeabon.add(new type_Abonnement(rs.getInt(1), rs.getString(2)));

            }
            abon.setItems(listeabon);
            abon.valueProperty().addListener((obs, oldval, newval) -> {
                if (newval != null) {

                    sform[0] = newval.getId_talon();
                    System.out.println(" ID: " + sform[0]);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(AddadhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editadr(MouseEvent mouseEvent) {
        String cN, n, p, t, ar, s, d;
        cN = cin.getText();
        n = nom.getText();
        p = prenom.getText();
        t = tel.getText();
        ar = adr.getText();
        d = date.getValue().toString();
        String req = "UPDATE `adhrent` SET `cin`='" + cN + "', `nom`='" + n + "', `prenom`='" + p + "',sexe='"+radioButtonLabel+"', `tel`='" + t + "', `adresse`='" + ar + "',`date_register`='" + d + "', `id_abon`=" + sform[0] + "";
        if (n.isEmpty() || cN.isEmpty() || p.isEmpty() || ar.isEmpty() || t.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("essayez de remplir tous les champs");
            alert.showAndWait();

        } else {
            if (c.addadhr(req) == true) {
                final Node source = (Node) mouseEvent.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Un adhrent a ete ajoutee");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);

                alert.setContentText("wssayez de remplire tous les champs");

                alert.showAndWait();

            }
        }
    }

}