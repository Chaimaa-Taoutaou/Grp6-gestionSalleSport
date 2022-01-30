package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdhrentActivityController implements Initializable {

    @FXML
    private ComboBox<Activity> activity;
    @FXML
    private ComboBox<type_Abonnement> abon;

    @FXML
    private ComboBox<Formateur> formateur;

    @FXML
    private TextField nc;
    static final Integer[] sabon = new Integer[1];
    static final Integer[] sform= new Integer[1];
    static final Integer[] sacti= new Integer[1];

    connecter c = new connecter();


    @FXML
    void vider(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nc.setText(AdhérentController.nc);
        try {

            //Abonnement
            ObservableList<type_Abonnement> listeabon = FXCollections.observableArrayList();
            ResultSet rs = c.getabon();

            while (rs.next()) {
                listeabon.add(new type_Abonnement(rs.getInt(1), rs.getString(2)));

            }
            abon.setItems(listeabon);
            abon.valueProperty().addListener((obs, oldval, newval) -> {
                if(newval != null) {

                    sabon[0] = newval.getId_talon();
                    System.out.println(" ID: " + sabon[0]);
                }
            });
            //Formateur
            ObservableList<Formateur> listformateur= FXCollections.observableArrayList();
            ResultSet rf=c.getFormateur();
            while (rf.next()){
                listformateur.add(new Formateur(rf.getInt(1),rf.getString(2)));
            }
            formateur.setItems(listformateur);

            formateur.valueProperty().addListener((obs, oldval, newval) -> {
                if(newval != null) {

                    sform[0] = newval.getId_f();
                    System.out.println("Selected airport: " + newval.getNom_p()
                            + ". ID: " + sform[0]);

                }
            });
            //Activity
            ObservableList<Activity> listactivity= FXCollections.observableArrayList();
            ResultSet ra=c.getActivity();
            while (ra.next()){
                listactivity.add(new Activity(ra.getInt(1),ra.getString(2)));
            }
            activity.setItems(listactivity);

            activity.valueProperty().addListener((obs, oldval, newval) -> {
                if(newval != null) {

                    sacti[0] = newval.getId_activity();


                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(AddadhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void ajouterActivity(MouseEvent event) {

        String req = "UPDATE `adhrent` SET `id_abon`="+ sabon[0] +",id_ts="+sacti[0]+",id_f="+sform[0]+" WHERE id_a="+AdhérentController.idad+" ";
        if (c.addadhr(req)) {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Un adhrent a ete ajoutee");
            alert.showAndWait();
        }
    }
}
