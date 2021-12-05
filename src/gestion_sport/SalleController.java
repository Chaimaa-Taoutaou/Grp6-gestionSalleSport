
package gestion_sport;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        final Button cellButton1 = new Button("Modifier");
        final Button deleteButton = new Button("Supprimer");
        
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
                    try {
                        editsalle() ;
                    } catch (IOException ex) {
                        Logger.getLogger(SalleController.class.getName()).log(Level.SEVERE, null, ex);
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
                    String n = tablesalle.getItems().get(x).getNom();
                 
                    System.out.println(n);
                }
            });}
             //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            System.out.println(empty);
            if(!empty){
                HBox pane = new HBox(cellButton1, deleteButton);
                pane.setSpacing(10);
                setGraphic(pane);
                
            }
            
        }
    }
    //
     public  void addsalle() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/addsalle.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        //dashboard.setResizable(false);
        dashboard.show();
    }
       
       public  void editsalle() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/ModifierSal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
      //  dashboard.setResizable(false);
        dashboard.show();
    }
        }
   