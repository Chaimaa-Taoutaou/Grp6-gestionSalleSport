package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static gestion_sport.Controller.ActivityController.*;

public class EditactivityController implements Initializable {

    @FXML
    private ComboBox<Formateur> formateur;

    @FXML
    private Button modifieractivity;

    @FXML
    private TextField nom;

    @FXML
    private TextField prix;

    @FXML
    void editActivity(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            unit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    Integer[] sform1 = new Integer[1];


    private void unit() throws SQLException {
        nom.setText(n);
        prix.setText(ActivityController.m);

        try {
            connecter c = new connecter();
            ObservableList<Formateur> listformateur = FXCollections.observableArrayList();
            ResultSet rs = c.getFormateur();
            while (rs.next()) {
                listformateur.add(new Formateur(rs.getInt(1), rs.getString(2)));
            }
            formateur.setItems(listformateur);
            formateur.valueProperty().addListener((obs, oldval, newval) -> {
                if (newval != null) {
                    sform1[0] = newval.getId_f();
                    System.out.println("Selected airport: " + newval.getNom_p()
                            + ". ID: " + sform1[0]);

                }
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editactivity(MouseEvent mouseEvent) {
        connecter c = new connecter();
        int id = c.recupActivity(nom.getText());
        String req = "UPDATE type_sport SET nom_a='" + nom.getText() + "',prix=" + prix.getText() + ",id_f="+sform1[0]+" WHERE id_ts=" + id + "";
        if (c.updateActivity(req)) {
            final Node source = (Node) mouseEvent.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("cet activité a ete modifiee");
            alert.showAndWait();
        }
    }


    public void exit(MouseEvent mouseEvent) {
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
