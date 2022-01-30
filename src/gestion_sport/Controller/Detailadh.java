package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Detailadh implements Initializable  {
    @FXML
    private TableColumn<Seance, Boolean> action2;


    @FXML
    private TableColumn<Seance, Boolean> hdebut;

    @FXML
    private TableColumn<Seance, Boolean> hfin;

    @FXML
    private TableColumn<Seance, Boolean> jour;

    @FXML
    private TableView<Seance> tableseance;
    @FXML
    private TableColumn<Adherent, Boolean> abon;

    @FXML
    private TableColumn<Adherent, Boolean> formateur;

    @FXML
    private TableColumn<Adherent, Boolean> nom;

    @FXML
    private TableView<Adherent> activadr;
    @FXML
    private Button btn;
    static String noma;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nom.setStyle("-fx-alignment : CENTER;");
        formateur.setStyle("-fx-alignment : CENTER;");
     //   jour.setStyle("-fx-alignment : CENTER;");
       // hfin.setStyle("-fx-alignment : CENTER;");
        //hdebut.setStyle("-fx-alignment : CENTER;");


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
            activadr.setItems(liste);
            btn.setVisible(false);
        }
        activadr.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Adherent>() {
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

        nom.setCellValueFactory(new PropertyValueFactory<>("typeabon"));
        formateur.setCellValueFactory(new PropertyValueFactory<>("Formateur"));
        abon.setCellValueFactory(new PropertyValueFactory<>("nomacti"));
       // action.setSortable(false);

    }







    public void addadh(ActionEvent actionEvent)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/adhrentActivity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        // dashboard.setResizable(false);
         dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();
    }


    public void addseance(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/addSeance.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        dashboard.initStyle(StageStyle.UNDECORATED);

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

    private void afficherAdh(){
        
    }

    public void btnimprimer(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/print.fxml"));
        Parent root = fxmlLoader.load();

        connecter c = new connecter();

        AdhérentController adhérentController = new AdhérentController();
        String cin = adhérentController.b;
        String nom = adhérentController.a;
        String prenom = adhérentController.c;
        String tel = adhérentController.d;
        String date = adhérentController.e;
        String sexe = adhérentController.f;
        String adresse = adhérentController.g;

        String typeab = c.getAdherentAbonById(cin);
        Float prix = c.getPrixActivityByCin(cin);


        Adherent adherent = new Adherent(cin,nom,prenom,tel,adresse,sexe,date,typeab,prix);

        PrintController printController = fxmlLoader.getController();
        printController.displayAdh(adherent);
        Scene scene = new Scene(root);
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();
    }
}
