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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityController implements Initializable {


    @FXML
    private TableView<Activity> tableactivity;

    @FXML
    private TableView<Seance> tableseance;

    @FXML
    private TableColumn<Activity, Boolean> action;

    @FXML
    private TableColumn<Activity, Boolean> action2;

    @FXML
    private TableColumn<Seance, String> hdebut;

    @FXML
    private TableColumn<Seance, String> hfin;

    @FXML
    private TableColumn<Seance, Date> jour;

    @FXML
    private TableColumn<Activity, String> formateur;

    @FXML
    private TableColumn<Activity, String> nom;

    @FXML
    private TableColumn<Activity,String> prix;

    @FXML
    private TextField search;

    @FXML
    private Button btn;



    static String m,n,telephone,ad,vi,id;
  public  static Integer id_s;
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        nom.setStyle("-fx-alignment : CENTER;");
        prix.setStyle("-fx-alignment : CENTER;");
        formateur.setStyle("-fx-alignment : CENTER;");


        try {
            chargerActivities();
            chargerSeances();
        } catch (SQLException ex) {

        }

    }


    ObservableList liste = FXCollections.observableArrayList();
    private void afficherActivities() throws SQLException{
        liste.clear();
        connecter c=new connecter();
        ResultSet rs=c.activityinfs();
        while(rs.next()){
            liste.add(new Activity(rs.getString(1),rs.getString(2),rs.getString(3)));
            tableactivity.setItems(liste);

            btn.setVisible(false);
        }
        tableactivity.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Activity>() {
            @Override
            public void changed(ObservableValue<? extends Activity> observableValue, Activity oldValue, Activity newValue) {
                if(newValue==null){
                    btn.setVisible(false);
                }
                if(newValue!=null){
                    btn.setVisible(true);

                    if(c.recupActivity(newValue.getNom())>=0){
                        id_s=c.recupActivity(newValue.getNom());
                        try {
                            chargerSeances();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        System.out.println(c.recupActivity(newValue.getNom()));
                    }else {System.out.println("ddgdd");}
                    System.out.println(newValue.getNom());
                }
            }
        });
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

        jour.setCellValueFactory(new PropertyValueFactory<>("jour"));
        hdebut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
        hfin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));

    }
    @FXML
    void addSeance(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/addSeance.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        dashboard.show();
    }
    private void chargerActivities() throws SQLException {
        connecter c = new connecter();

        ResultSet rs = c.salleinfs();
        afficherActivities();

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        formateur.setCellValueFactory(new PropertyValueFactory<>("formateur"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        action.setSortable(false);
        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activity, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Activity, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }

        });

        action.setCellFactory(new Callback<TableColumn<Activity, Boolean>, TableCell<Activity, Boolean>>() {
            @Override
            public TableCell<Activity, Boolean> call(TableColumn<Activity, Boolean> p) {
                return new ButtonCell(tableactivity);
            }
        });

    }
        //Define the button cell
        private class ButtonCell extends TableCell<Activity, Boolean> {
            Image imgEdit = new Image(getClass().getResourceAsStream("/image/edit.png"), 25, 25, false, false);
            final Button cellButton1 = new Button();
            Image imgDeete = new Image(getClass().getResourceAsStream("/image/remove.png"), 25, 25, false, false);
            final Button deleteButton = new Button();



            ButtonCell(final TableView tblView){
                cellButton1.setStyle("-fx-background-color: #3cb371; -fx-border-radius: #00CC00;");
                cellButton1.setTextFill(Color.WHITE);
                cellButton1.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                        int x = getIndex();
                        String nom = tableactivity.getItems().get(x).getNom();
                        String  prix = tableactivity.getItems().get(x).getPrix();
                        String  formateur = tableactivity.getItems().get(x).getFormateur();

                        m=prix;n=nom;vi=formateur;


                        try {
                            editactivity() ;
                        } catch (IOException ex) {
                            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        System.out.println(nom);

                    }

                });

                //  deleteButton.setStyle("-fx-background-color: #f5053d; -fx-border-radius: #cc0000;");
                //deleteButton.setTextFill(Color.WHITE);

                deleteButton.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                        int x = getIndex();
                        String n = tableactivity.getItems().get(x).getNom();
                        connecter c=new connecter();

                        int id = c.recupActivity(n);
                        if(c.recup(n)>=0){id=c.recup(n);}else {System.out.println("ddgdd");}

                        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);

                        dialogC.setHeaderText(null);
                        dialogC.setContentText("Voulez vous vraiment supprimer cette activité");
                        Optional<ButtonType> answer = dialogC.showAndWait();
                        if (answer.get() == ButtonType.OK) {
                            if( c.delsalle("Delete FROM type_sport where id_ts="+ id +"")){  try {
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
                    //cellButton1
                    cellButton1.setStyle("-fx-background-color: transparent;-fx-background-size: 5px 5px;");
                    ImageView v = new ImageView();
                    v.setImage(imgEdit);
                    v.setPreserveRatio(true);
                    v.setSmooth(true);
                    v.setCache(true);
                    cellButton1.setGraphic(v);
                    setGraphic(cellButton1);
                    setAlignment(Pos.CENTER);
                    setText(null);

                                /*HBox managebtn = new HBox(cellButton1, deleteButton);
                                managebtn.setStyle("-fx-alignment:center");
                                HBox.setMargin(cellButton1, new Insets(1, -13, 0, 0));
                                HBox.setMargin(deleteButton, new Insets(1, -13, 0, 0));
                              //  HBox.setMargin(btnVoir, new Insets(1, 0, 0, 0));
                                setGraphic(managebtn);

                                setText(null);*/
                    HBox pane = new HBox(cellButton1, deleteButton);
                    pane.setStyle("-fx-alignment:center");
                    pane.setSpacing(10);
                    setGraphic(pane);
                }

            }
        }

    @FXML
    public  void addactivity() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/addactivity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        dashboard.show();
    }
    public  void editactivity() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/editactivity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //  dashboard.setResizable(false);
        dashboard.show();
    }
}
