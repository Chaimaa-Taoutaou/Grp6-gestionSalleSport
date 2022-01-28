package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VoirAdherentController implements Initializable {

    @FXML
    private TableColumn<Adherent, Boolean> abon;

    @FXML
    private TableColumn<Adherent, Boolean> action;

    @FXML
    private TableColumn<Seance, Boolean> action2;

    @FXML
    private TableColumn<Adherent, Boolean> formateur;

    @FXML
    private TableColumn<Seance, Boolean> hdebut;

    @FXML
    private TableColumn<Seance, Boolean> hfin;

    @FXML
    private TableColumn<Seance, Boolean> jour;

    @FXML
    private TableColumn<Adherent, Boolean> nom;

    @FXML
    private TableView<Adherent> tableactivity;

    @FXML
    private TableView<Seance> tableseance;
    @FXML
    private Button btn;
    static String noma;
    @FXML
    void addSeance(MouseEvent event) {

    }


    @FXML
    void addactivity() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/adhrentActivity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        // dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nom.setStyle("-fx-alignment : CENTER;");
        formateur.setStyle("-fx-alignment : CENTER;");
        jour.setStyle("-fx-alignment : CENTER;");
        hfin.setStyle("-fx-alignment : CENTER;");
        hdebut.setStyle("-fx-alignment : CENTER;");


        try {
            afficherActivity();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    ObservableList liste = FXCollections.observableArrayList();
    private void chargerActivities() throws SQLException{
        liste.clear();
        connecter c=new connecter();
        ResultSet rs=c.detadhinfs();
        while(rs.next()){
            liste.add(new Adherent(rs.getString(3),rs.getString(1),rs.getString(2)));
            tableactivity.setItems(liste);
            btn.setVisible(false);
        }
        tableactivity.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Adherent>() {
            @Override
            public void changed(ObservableValue<? extends Adherent> observableValue, Adherent oldValue, Adherent newValue) {
                if(newValue==null){
                    btn.setVisible(false);
                }
                if(newValue!=null){
                    btn.setVisible(true);
                    noma=newValue.getNomacti();
                    try {
                        chargerSeances();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }
            }
        });
    }


    private void afficherActivity() throws SQLException {
            connecter c = new connecter();

            ResultSet rs = c.salleinfs();
            chargerActivities();

            nom.setCellValueFactory(new PropertyValueFactory<>("nomacti"));
            formateur.setCellValueFactory(new PropertyValueFactory<>("Formateur"));
            abon.setCellValueFactory(new PropertyValueFactory<>("typeabon"));
            action.setSortable(false);

    }

    public void ajouterSeance(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/addSeance.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        dashboard.show();
    }
    ObservableList  listeSeance = FXCollections.observableArrayList();
    private void afficherSeance() throws SQLException{
        listeSeance.clear();
        connecter c=new connecter();
        ResultSet rs=c.seanceinfs();


        while(rs.next()){

            listeSeance.add(new Seance(rs.getString(1),rs.getString(2),rs.getString(3)));
            tableseance.setItems(listeSeance);
        }
    }
    private void chargerSeances() throws SQLException {
        connecter c = new connecter();
        afficherSeance();

        jour.setCellValueFactory(new PropertyValueFactory<>("date_s"));
        hdebut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
        hfin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));



    }

}