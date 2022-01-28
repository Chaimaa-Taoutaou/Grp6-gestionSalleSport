package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddSeanceController implements Initializable {

    @FXML
    private TextField HeureFin;

    @FXML
    private Button btnSave;
    @FXML
    private Label lab;
    @FXML
    private DatePicker datePicker1;

    @FXML
    private TextField heureDebut;

    @FXML
    void ajouterSeance(ActionEvent event) {
            String n,v,e,a,t;
            n=HeureFin.getText();
            v=heureDebut.getText();
            LocalDate value=datePicker1.getValue();
            Integer id_activity;
            id_activity=ActivityController.id_s;
                connecter c =new connecter();
                Integer id_a=c.recupActivity(VoirAdherentController.noma);
                System.out.println(id_a);
               if(c.addseance(value,n, v,id_a)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Une salle a été ajouté");

                    alert.showAndWait();


                }else {
                    System.out.println("benbactam");

                }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("id of selected row is: "+ActivityController.id_s);
        //datePicker.getValue();


    }

    @FXML
    void getDate(MouseEvent event) {

        LocalDate value=datePicker1.getValue();
        System.out.println("date from here: "+value);
    }
}
