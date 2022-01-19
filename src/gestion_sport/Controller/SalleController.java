
package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


public class SalleController implements Initializable {

    @FXML
    private TableView<salle> tablesalle;
    @FXML
    private TableColumn<salle, String> ns;
    @FXML
    private TableColumn<salle, String> vs;
    @FXML
    private TableColumn<salle, String> adrs;
    @FXML
    private TableColumn<salle, String> tel;
    @FXML
    private TableColumn<salle, String> emai;
    @FXML
    private TableColumn<salle, Boolean> edt;
    @FXML
    private TextField search;
    static String m,n,telephone,ad,vi,id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        ns.setStyle("-fx-alignment : CENTER;");
        vs.setStyle("-fx-alignment : CENTER;");
        adrs.setStyle("-fx-alignment : CENTER;");
        tel.setStyle("-fx-alignment : CENTER;");
        emai.setStyle("-fx-alignment : CENTER;");
        try {
            chargersalles();
        } catch (SQLException ex) {

        }

    }


    ObservableList  liste = FXCollections.observableArrayList();
    private void affichersalles() throws SQLException{
        liste.clear();
        connecter c=new connecter();
        ResultSet rs=c.salleinfs();
        while(rs.next()){
            liste.add(new salle(rs.getString(2),rs.getString(4),rs.getString(3),rs.getString(6),rs.getString(5)));
            tablesalle.setItems(liste);
        }
    }
    private void chargersalles() throws SQLException{
        connecter c=new connecter();

        ResultSet rs=c.salleinfs();
        affichersalles();

        ns.setCellValueFactory(new PropertyValueFactory<>("nom"));
        vs.setCellValueFactory(new PropertyValueFactory<>("ville"));
        adrs.setCellValueFactory(new PropertyValueFactory<>("adrs"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tels"));
        emai.setCellValueFactory(new PropertyValueFactory<>("email"));
        edt.setSortable(false);
        //,;
        edt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<salle, Boolean>, ObservableValue<Boolean>>(){
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<salle, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }

        });

        edt.setCellFactory(new Callback<TableColumn<salle, Boolean>, TableCell<salle, Boolean>>(){
            @Override
            public TableCell<salle, Boolean> call(TableColumn<salle, Boolean> p) {
                return new ButtonCell(tablesalle);
            }
        });
        //tableusers.getColumns().add(act);

    }

    //Define the button cell
    private class ButtonCell extends TableCell<salle, Boolean> {
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
                    String nom = tablesalle.getItems().get(x).getNom();
                    String  v = tablesalle.getItems().get(x).getVille();
                    String  emal = tablesalle.getItems().get(x).getEmail();
                    String   a = tablesalle.getItems().get(x).getAdrs();
                    String ccc=tablesalle.getItems().get(x).getTels();
                    m=emal;n=nom;vi=v;ad=a; telephone=ccc;
                    try {
                        editsalle() ;
                    } catch (IOException ex) {
                        Logger.getLogger(SalleController.class.getName()).log(Level.SEVERE, null, ex);
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
                    String n = tablesalle.getItems().get(x).getNom();
                    connecter c=new connecter();

                    int id = c.recup(n);
                    if(c.recup(n)>=0){id=c.recup(n);}else {System.out.println("ddgdd");}

                    Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);

                    dialogC.setHeaderText(null);
                    dialogC.setContentText("Voulez vous vraiment supprimer cette salle");
                    Optional<ButtonType> answer = dialogC.showAndWait();
                    if (answer.get() == ButtonType.OK) {
                        if( c.delsalle("Delete FROM salle where id_s="+ id +"")){  try {
                            chargersalles();

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
    //
    @FXML
    public  void addsalle() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/addsalle.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        dashboard.show();
    }

    public  void editsalle() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/ModifierSal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //  dashboard.setResizable(false);
        dashboard.show();
    }
    public void serch(){

        FilteredList<salle> filteredData = new FilteredList<>(liste, p -> true);


        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(salle -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (salle.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (salle.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });


        SortedList<salle> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tablesalle.comparatorProperty());

        tablesalle.setItems(sortedData);
    }


}
   