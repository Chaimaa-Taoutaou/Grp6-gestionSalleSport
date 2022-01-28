package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditSeanceController implements Initializable {

    @FXML
    private TextField HeureFin;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker datePicker1;

    @FXML
    private TextField heureDebut;

    @FXML
    void editseance(ActionEvent event) {
        connecter c = new connecter();
        int id = c.recupSeance(HeureFin.getText());
        LocalDate value=datePicker1.getValue();
        System.out.println("edit"+value);
        String req = "UPDATE seance SET jour='" + value + "',heureDebut='"+heureDebut.getText()+"',heureFin='"+HeureFin.getText()+"' WHERE id_seance=" + id + "";
        if (c.updateSeance(req)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            System.out.println(heureDebut.getText());
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("cet seance a ete modifiee");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HeureFin.setText(ActivityController.hf_s);
        heureDebut.setText(ActivityController.hd_s);
        //datePicker1.setAccessibleText(ActivityController.jr_s);
    }
}
