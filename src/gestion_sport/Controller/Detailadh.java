package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Detailadh implements Initializable  {
    @FXML
    private TableColumn<Adherent, Boolean> action;

    @FXML
    private TableColumn<Seance, Boolean> action2;
public  static  int id;

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
          jour.setStyle("-fx-alignment : CENTER;");
           hfin.setStyle("-fx-alignment : CENTER;");
        hdebut.setStyle("-fx-alignment : CENTER;");
        try {
            afficherActivity();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    ObservableList liste = FXCollections.observableArrayList();
    private void chargerActivities() throws SQLException{
        liste.clear();
        connecter c=new connecter();
        ResultSet rs=c.getadhacti();
        while(rs.next()){
            liste.add(new Adherent(rs.getString(1),rs.getString(2),rs.getString(3)));
            activadr.setItems(liste);
            btn.setVisible(false);
        }
        System.out.println("nom nm: "+activadr.getItems().get(0).getNomacti());
        activadr.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Adherent>() {
            @Override
            public void changed(ObservableValue<? extends Adherent> observableValue, Adherent oldValue, Adherent newValue) {
                if(newValue==null){
                    btn.setVisible(false);
                    tableseance.setVisible(false);
                }
                if(newValue!=null){
                   btn.setVisible(true);
                    noma=newValue.getNomacti();
                    System.out.println("from here"+noma);
                    tableseance.setVisible(true);
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
       formateur.setCellValueFactory(new PropertyValueFactory<>("formateur"));
        abon.setCellValueFactory(new PropertyValueFactory<>("typeabon"));
       // action.setSortable(false);
        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Adherent, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Adherent, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }

        });

        action.setCellFactory(new Callback<TableColumn<Adherent, Boolean>, TableCell<Adherent, Boolean>>() {
            @Override
            public TableCell<Adherent, Boolean> call(TableColumn<Adherent, Boolean> p) {
                return new Detailadh.ButtonCell(activadr);
            }
        });

    }

    public void levear(MouseEvent mouseEvent) {
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    //Define the button cell
    private class ButtonCell extends TableCell<Adherent, Boolean> {
        Image imgDeete = new Image(getClass().getResourceAsStream("/image/remove.png"), 25, 25, false, false);
        final Button deleteButton = new Button();



        ButtonCell(final TableView tblView){


            deleteButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String n = activadr.getItems().get(x).getNom();
                    connecter c=new connecter();

                    int id = c.recupadh(n);
                    if(c.recup(n)>=0){id=c.recup(n);}else {System.out.println("ddgdd");}

                    Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);

                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Voulez vous vraiment supprimer cette activité");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    if (answer.get() == ButtonType.OK) {
                        if( c.delsalle("Delete FROM activity_adrt where id_a="+ id +"")){  try {
                            chargerActivities();

                        } catch (SQLException ex) {
                            Logger.getLogger(SalleController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }


                    }}
            });}

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            System.out.println(empty);
            if(!empty){

                deleteButton.setStyle("-fx-background-color: transparent;-fx-background-size: 5px 5px;");
                ImageView iv = new ImageView();
                iv.setImage(imgDeete);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                deleteButton.setGraphic(iv);
                setGraphic(deleteButton);
                setAlignment(Pos.CENTER);
                setText(null);

                HBox pane = new HBox( deleteButton);
                pane.setStyle("-fx-alignment:center");
                pane.setSpacing(10);
                setGraphic(pane);
            }else{
                deleteButton.setGraphic(null);
            }

        }
    }








    public void addadh(ActionEvent actionEvent)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/Addactivadh.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        dashboard.setResizable(false);
        dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();
    }


    public void addseance(MouseEvent mouseEvent) throws IOException {
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

        int id= c.recupActivity(noma);
        System.out.println("now1: "+id);
        ResultSet rs=c.adhSeance(id);
        String req ="SELECT jour,heureDebut, heureFin FROM seance where id_ts="+id+"";


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
        action2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Seance, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Seance, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }

        });

        action2.setCellFactory(new Callback<TableColumn<Seance, Boolean>, TableCell<Seance, Boolean>>() {
            @Override
            public TableCell<Seance, Boolean> call(TableColumn<Seance, Boolean> p) {
                return new Detailadh.Buttond(tableseance);
            }
        });


    }
    //Define the button cell
    private class Buttond extends TableCell<Seance, Boolean> {
        Image imgDeete = new Image(getClass().getResourceAsStream("/image/remove.png"), 25, 25, false, false);
        final Button deleteButton2 = new Button();



        Buttond(final TableView tblView){

            //  deleteButton.setStyle("-fx-background-color: #f5053d; -fx-border-radius: #cc0000;");
            //deleteButton.setTextFill(Color.WHITE);

            deleteButton2.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String n = tableseance.getItems().get(x).getHeure_fin();
                    connecter c=new connecter();

                    int id = c.recupSeance(n);
                    //  if(c.recup(n)>=0){id=c.recup(n);}else {System.out.println("ddgdd");}

                    Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);

                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Voulez vous vraiment supprimer cette séance");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    if (answer.get() == ButtonType.OK) {
                        if( c.delsalle("Delete FROM seance where id_seance="+ id +"")){  try {
                            chargerSeances();

                        } catch (SQLException ex) {
                            Logger.getLogger(SalleController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }


                    }}
            });}
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            System.out.println(empty);
            if(!empty){

                deleteButton2.setStyle("-fx-background-color: transparent;-fx-background-size: 5px 5px;");
                ImageView iv = new ImageView();
                iv.setImage(imgDeete);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                deleteButton2.setGraphic(iv);
                setGraphic(deleteButton2);
                setAlignment(Pos.CENTER);
                setText(null);

                HBox pane = new HBox( deleteButton2);
                pane.setStyle("-fx-alignment:center");
                pane.setSpacing(10);
                setGraphic(pane);
            }

        }
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
        String salle_name = c.getSalleNameByCin(cin);


        Adherent adherent = new Adherent(cin,nom,prenom,tel,adresse,sexe,date,typeab,prix,salle_name);

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
