package gestion_sport.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import gestion_sport.Model.connecter;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;



public class FormateurController implements Initializable {


    @FXML
    private TableView<Formateur> tableformateur;
    @FXML
    private TableColumn<Formateur, String> nf;
    @FXML
    private TableColumn<Formateur, String> pf;
    @FXML
    private TableColumn<Formateur, String> ef;
    @FXML
    private TableColumn<Formateur, String> af;
    @FXML
    private TableColumn<Formateur, Boolean> edt;
    @FXML
    private TextField searchFormateur;
    	static String n,pr,m,ad,id;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
         nf.setStyle("-fx-alignment : CENTER;");
        pf.setStyle("-fx-alignment : CENTER;");
       ef.setStyle("-fx-alignment : CENTER;");
        af.setStyle("-fx-alignment : CENTER;");
        try {
           chargerformateurs();
            search();
        } catch (SQLException ex) {

        }
    }


    ObservableList  liste = FXCollections.observableArrayList();
              private void afficherformateurs() throws SQLException{
        liste.clear();
        connecter c=new connecter();
        ResultSet rs=c.formateurinfs();


        while(rs.next()){
            liste.add(new Formateur(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            tableformateur.setItems(liste);
        }
    }
                 private void chargerformateurs() throws SQLException{
        connecter c=new connecter();

        ResultSet rs=c.formateurinfs();
        afficherformateurs();

        nf.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        pf.setCellValueFactory(new PropertyValueFactory<>("prenom_p"));
        ef.setCellValueFactory(new PropertyValueFactory<>("email_p"));
        af.setCellValueFactory(new PropertyValueFactory<>("adresse_f"));
        edt.setSortable(false);
        //,;
       edt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Formateur, Boolean>, ObservableValue<Boolean>>(){
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Formateur, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }

        });

        edt.setCellFactory(new Callback<TableColumn<Formateur, Boolean>, TableCell<Formateur, Boolean>>(){
            @Override
            public TableCell<Formateur, Boolean> call(TableColumn<Formateur, Boolean> p) {
                return new ButtonCell(tableformateur);
            }
        });
        //tableusers.getColumns().add(act);

    }

    //Define the button cell
    private class ButtonCell extends TableCell<Formateur, Boolean> {
        Image imgEdit = new Image(getClass().getResourceAsStream("/image/edit.png"), 20, 20, false, false);
        final Button cellButton1 = new Button();
        Image imgDeete = new Image(getClass().getResourceAsStream("/image/remove.png"), 20, 20, false, false);
        final Button deleteButton = new Button();

        ButtonCell(final TableView tblView){
            cellButton1.setStyle("-fx-background-color: #3cb371; -fx-border-radius: #00CC00;");
            cellButton1.setTextFill(Color.WHITE);
            cellButton1.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                      int x = getIndex();
                    String nom = tableformateur.getItems().get(x).getNom_p();
                    String  p = tableformateur.getItems().get(x).getPrenom_p();
                    String  emal = tableformateur.getItems().get(x).getEmail_p();
                    String   a = tableformateur.getItems().get(x).getAdresse_f();
                    n=nom;pr=p;m=emal;ad=a;

                    try {
                        editformateur();
                    } catch (IOException ex) {
                        Logger.getLogger(FormateurController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println(nom);

                }

            });

            deleteButton.setStyle("-fx-background-color: #f5053d; -fx-border-radius: #cc0000;");
            deleteButton.setTextFill(Color.WHITE);
            deleteButton.setOnAction(new EventHandler<ActionEvent>(){
            	@Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String n = tableformateur.getItems().get(x).getNom_p();
                 connecter c=new connecter();

                 int id = c.recup(n);
                 if(c.recup_formateur(n)>=0){id=c.recup_formateur(n);}else {System.out.println("ddgdd");}

                  Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);

             dialogC.setHeaderText(null);
              dialogC.setContentText("Voulez vous vraiment supprimer ce formateur");
              Optional<ButtonType> answer = dialogC.showAndWait();
             if (answer.get() == ButtonType.OK) {
                if( c.delsalle("Delete FROM formateur where id_f="+ id +"")){  try {
                    chargerformateurs();

                    } catch (SQLException ex) {
                        Logger.getLogger(FormateurController.class.getName()).log(Level.SEVERE, null, ex);
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
                HBox pane = new HBox(cellButton1, deleteButton);
                pane.setStyle("-fx-alignment:center");
                pane.setSpacing(10);
                setGraphic(pane);

            }

        }
    }
    //
     public  void addformateur() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/addformateur.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
         dashboard.setResizable(false);
         dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();
    }

       public  void editformateur() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/ModifierFormateur.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
           dashboard.setResizable(false);
           dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.show();
    }
    public void search(){

        FilteredList<Formateur> filteredData = new FilteredList<>(liste, b -> true);

        searchFormateur.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Formateur -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if(Formateur.getNom_p().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(Formateur.getPrenom_p().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else{
                    return false;
                }

            });
        });

        SortedList<Formateur> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableformateur.comparatorProperty());

        tableformateur.setItems(sortedData);

    }



}
   