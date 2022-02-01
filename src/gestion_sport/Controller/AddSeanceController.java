package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public static  Integer id_activity;
    @FXML
    void ajouterSeance(ActionEvent event) {
            String n,v,e,a,t;
            n=HeureFin.getText();
            v=heureDebut.getText();
            LocalDate value=datePicker1.getValue();

           // id_activity=ActivityController.id_s;

                connecter c =new connecter();
                id_activity=c.recupActivity(Detailadh.noma);
               if(c.addseance(value,n, v,id_activity)==true){
                   final Node source = (Node) event.getSource();
                   final Stage stage = (Stage) source.getScene().getWindow();
                   stage.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.initStyle(StageStyle.UNDECORATED);
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

    public void leave(MouseEvent mouseEvent) {
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
