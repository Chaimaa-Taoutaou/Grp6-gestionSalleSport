package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private final DatePicker datePicker=new DatePicker();

    @FXML
    private TextField heureDebut;

    @FXML
    void ajouterSeance(ActionEvent event) {
            String n,v,e,a,t;
            n=HeureFin.getText();
            v=heureDebut.getText();
           // e=datePicker.getValue().toString();
         //   System.out.println(e);
        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();
                System.err.println("Selected date: " + date);
                lab.setText(String.valueOf(date));
            }
        });

           // e=datePicker.toString();
            Integer id_activity;
            id_activity=ActivityController.id_s;
                connecter c =new connecter();
             /*   if(c.addseance(date,n, v,id_activity)==true){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    // Stage stage = (Stage) closeButton.getScene().getWindow();
                    // stage.close();
                    alert.setContentText("Une salle a été ajouté");

                    alert.showAndWait();


                }else {
                    System.out.println("benbactam");

                }*/

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("id of selected row is: "+ActivityController.id_s);

    }


}
