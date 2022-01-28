package gestion_sport.Controller;


import gestion_sport.Model.connecter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


public class AdherentController implements Initializable {
    @FXML
    private TextField filterField;
    @FXML
    private TableView<Adherent> tableadr;
    @FXML
    private TableColumn<Adherent, String> cin;
    @FXML
    private TableColumn<Adherent, String> nomadh;
    @FXML
    private TableColumn<Adherent, String> prenomadh;
    @FXML
    private TableColumn<Adherent, String> teladh;
    @FXML
    private TableColumn<Adherent, String> adresadh;
    @FXML
    private TableColumn<Adherent,String > dateins;
    @FXML
    private TableColumn<Adherent, String> sexeadh;
    @FXML
    private TableColumn<Adherent, Boolean> actionadr;
    static String a,b,c,d,e,f,g;
    static  int idad;
    Image mapImage = new Image("/image/remove.png");
    ImageView mapView = new ImageView(mapImage);

    Image img = new Image("/image/remove.png");
    ImageView view = new ImageView(img);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cin.setStyle("-fx-alignment : CENTER;");
        nomadh.setStyle("-fx-alignment : CENTER;");
        prenomadh.setStyle("-fx-alignment : CENTER;");
        teladh.setStyle("-fx-alignment : CENTER;");
        adresadh.setStyle("-fx-alignment : CENTER;");
        sexeadh.setStyle("-fx-alignment : CENTER;");
        actionadr.setStyle("-fx-alignment : CENTER;");
        dateins.setStyle("-fx-alignment : CENTER;");
       try {
            chargeradh();
        } catch (SQLException ex) {
            Logger.getLogger(AdherentController.class.getName()).log(Level.SEVERE, null, ex);
        }




        // mapView.setFitHeight(20);
        // mapView.setFitWidth(20);

    }
    ObservableList  liste = FXCollections.observableArrayList();
    private void afficherAdhrent() throws SQLException{
        liste.clear();
        connecter c=new connecter();
        ResultSet rs=c.adhinfs();

        //SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        while(rs.next()){

            liste.add(new Adherent(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8)));
            tableadr.setItems(liste);
        }
        tableadr.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Adherent>() {
            @Override
            public void changed(ObservableValue<? extends Adherent> observableValue, Adherent oldValue, Adherent newValue) {
                if(newValue!=null){
                    if(c.recupadh(newValue.getNom())>=0){
                        idad=c.recupadh(newValue.getNom());
                        System.out.println(c.recupadh(newValue.getNom()));
                    }else {System.out.println("ddgdd");}
                    System.out.println("Ce nom: "+newValue.getNom());
                }
            }
        });
    }
    private  void chargeradh() throws SQLException{
        connecter c=new connecter();

        ResultSet rs=c.adhinfs();
        afficherAdhrent();

        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nomadh.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomadh.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        teladh.setCellValueFactory(new PropertyValueFactory<>("tel"));
        adresadh.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        sexeadh.setCellValueFactory(new PropertyValueFactory<>("s"));
        dateins.setCellValueFactory(new PropertyValueFactory<>("dateinscrip"));
        actionadr.setSortable(false);
        //,;
        actionadr.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Adherent, Boolean>, ObservableValue<Boolean>>(){
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Adherent, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }

        });

        actionadr.setCellFactory(new Callback<TableColumn<Adherent, Boolean>, TableCell<Adherent, Boolean>>(){
            @Override
            public TableCell<Adherent, Boolean> call(TableColumn<Adherent, Boolean> p) {
                return new ButtonCell(tableadr);
            }
        });


    }


    //Define the button cell
    private class ButtonCell extends TableCell<Adherent, Boolean> {
        Image imgEdit = new Image(getClass().getResourceAsStream("/image/edit.png"), 25, 25, false, false);
        final Button cellButton1 = new Button();
        Image imgDeete = new Image(getClass().getResourceAsStream("/image/remove.png"), 25, 25, false, false);
        final Button deleteButton = new Button();
        Image imgVoir = new Image(getClass().getResourceAsStream("/image/view.png"), 25, 25, false, false);
        final Button btnVoir = new Button();



        ButtonCell(final TableView tblView){
            cellButton1.setStyle("-fx-background-color: #00BFFF; -fx-border-radius: #00CC00;-fx-marginleft: 10;");
            cellButton1.setTextFill(Color.WHITE);

            cellButton1.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String nom = tableadr.getItems().get(x).getNom();
                    String  cin = tableadr.getItems().get(x).getCin();
                    String  prenom = tableadr.getItems().get(x).getPrenom();
                    String  tele = tableadr.getItems().get(x).getTel();
                    String  dateinscrip = tableadr.getItems().get(x).getDateinscrip().toString();
                    String  s = tableadr.getItems().get(x).getS();
                    String  adr = tableadr.getItems().get(x).getAdresse();

                    a=nom;b=cin;c=prenom;d=tele;e=dateinscrip;f=s;g=adr;
                    System.out.println("nom from here " +a);

                    try {
                        editadherent();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            });

            btnVoir.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        voiradherent();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            deleteButton.setStyle("-fx-background-color: #f5053d; -fx-border-radius: #cc0000;");
            deleteButton.setTextFill(Color.WHITE);
            deleteButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String n = tableadr.getItems().get(x).getNom();
                    String cin=tableadr.getItems().get(x).getCin();
                    connecter c=new connecter();




                    Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);

                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Voulez vous vraiment supprimer cette salle");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    if (answer.get() == ButtonType.OK) {
                        if( c.delsalle("Delete FROM adhrent where cin='"+ cin +"'")){  try {
                            chargeradh();

                        } catch (SQLException ex) {
                            // Logger.getLogger(SalleController.class.getName()).log(Level.SEVERE, null, ex);
                        }




                        }}}}

            );}
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            System.out.println(empty);
            if(!empty){
                btnVoir.setStyle("-fx-background-color: transparent;-fx-background-size: 5px 5px;");
                ImageView ia = new ImageView();
                ia.setImage(imgVoir);
                ia.setPreserveRatio(true);
                ia.setSmooth(true);
                ia.setCache(true);
                btnVoir.setGraphic(ia);
                setGraphic(btnVoir);
                setAlignment(Pos.CENTER);
                setText(null);

                deleteButton.setStyle("-fx-background-color: transparent;-fx-background-size: 5px 5px;");
                ImageView ir = new ImageView();
                ir.setImage(imgDeete);
                ir.setPreserveRatio(true);
                ir.setSmooth(true);
                ir.setCache(true);
                deleteButton.setGraphic(ir);
                setGraphic(deleteButton);
                setAlignment(Pos.CENTER);
                setText(null);

                cellButton1.setStyle("-fx-background-color: transparent;-fx-background-size: 5px 5px;");
                ImageView iv = new ImageView();
                iv.setImage(imgEdit);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                cellButton1.setGraphic(iv);

                setGraphic(cellButton1);
                setAlignment(Pos.CENTER);
                setText(null);

                HBox managebtn = new HBox(btnVoir, cellButton1, deleteButton);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(cellButton1, new Insets(1, -13, 0, 0));
                HBox.setMargin(deleteButton, new Insets(1, -13, 0, 0));
                HBox.setMargin(btnVoir, new Insets(1, 0, 0, 0));
                setGraphic(managebtn);

                setText(null);

            }

        }


    }

    private void voiradherent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/voirAdherent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        // dashboard.setResizable(false);
        //dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();

    }

    private void editadherent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/editadhrent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        // dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();
    }

    @FXML
    void addadh() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/addadherent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
       // dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();
    }

  @FXML
  void chercher(){



      FilteredList<Adherent> filteredData = new FilteredList<>(FXCollections.observableList(liste));
      tableadr.setItems(filteredData);




      // 2. Set the filter Predicate whenever the filter changes.
      filterField.textProperty().addListener((observable, oldValue, newValue) -> {
          filteredData.setPredicate(Adherent -> {
              // If filter text is empty, display all persons.

              if (newValue == null || newValue.isEmpty()) {
                  return true;
              }

              // Compare first name and last name of every person with filter text.
              String lowerCaseFilter = newValue.toLowerCase();

              if (Adherent.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                  return true; // Filter matches first name.


              }else
                  return false; // Does not match.
          });
      });

      // 3. Wrap the FilteredList in a SortedList.
      SortedList<Adherent> sortedData = new SortedList<>(filteredData);

      // 4. Bind the SortedList comparator to the TableView comparator.
      // 	  Otherwise, sorting the TableView would have no effect.
      sortedData.comparatorProperty().bind(tableadr.comparatorProperty());

      // 5. Add sorted (and filtered) data to the table.
      tableadr.setItems(sortedData);


  }

}