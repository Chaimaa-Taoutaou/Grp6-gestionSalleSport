
package gestion_sport;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.TableCell;
import javafx.util.Callback;


import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class userController implements Initializable {
    
    
    @FXML
    private TableView<user> tableusers;
    @FXML
    private TableColumn<user, String> n;
    @FXML
    private TableColumn<user, String> p;
    @FXML
    private TableColumn<user, String> m;
    @FXML
    private TableColumn<user, Boolean> act;
    

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         n.setStyle("-fx-alignment : CENTER;");
        p.setStyle("-fx-alignment : CENTER;");
        m.setStyle("-fx-alignment : CENTER;");
        act.setStyle("-fx-alignment : CENTER;");
        try {
            charger();
        } catch (SQLException ex) {
            
        }
        
    }  
    
  
  
            
    ObservableList  ls = FXCollections.observableArrayList();
    private void afficher() throws SQLException{
        ls.clear();
        connecter c=new connecter();
        ResultSet rs=c.utilsateurinfs();
        while(rs.next()){
            ls.add(new user(rs.getString(2),rs.getString(3),rs.getString(4)));  
            tableusers.setItems(ls);
        }
    }
    
    private void charger() throws SQLException{
        connecter c=new connecter();
        //c.adduser("n", "p", "e", "pass");
        ResultSet rs=c.utilsateurinfs();
        afficher();
        
        n.setCellValueFactory(new PropertyValueFactory<>("nom"));
        p.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        m.setCellValueFactory(new PropertyValueFactory<>("email"));
        act.setSortable(false);
        
        act.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<user, Boolean>, ObservableValue<Boolean>>(){
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<user, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
               
        });
        
        act.setCellFactory(new Callback<TableColumn<user, Boolean>, TableCell<user, Boolean>>(){
            @Override
            public TableCell<user, Boolean> call(TableColumn<user, Boolean> p) {
                return new ButtonCell(tableusers);
            }
        });
        //tableusers.getColumns().add(act);
        
    }
    
    //Define the button cell
    private class ButtonCell extends TableCell<user, Boolean> {
        final Button cellButton1 = new Button("Modifier");
        final Button deleteButton = new Button("Supprimer");
        
        ButtonCell(final TableView tblView){
            cellButton1.setStyle("-fx-background-color: #3cb371; -fx-border-radius: #00CC00;");
            cellButton1.setTextFill(Color.WHITE);
            cellButton1.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                      int x = getIndex();
                    String nom = tableusers.getItems().get(x).getNom();
                    String  pr = tableusers.getItems().get(x).getPrenom();
                    String  emal = tableusers.getItems().get(x).getEmail();
                   
                    try {
                        modif();
                    } catch (IOException ex) {
                       
                    }
                    System.out.println(n);
                     
                }
                
            });
            
            deleteButton.setStyle("-fx-background-color: #f5053d; -fx-border-radius: #cc0000;");
            deleteButton.setTextFill(Color.WHITE);
            deleteButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String n = tableusers.getItems().get(x).getNom();
                    try {
                        modif();
                    } catch (IOException ex) {
                       System.out.println("erreur");
                    }
                    System.out.println(n);
                }
            });
        }

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
    
    
    @FXML
    private void adduser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/adduser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        dashboard.show();
    }
        
    private void modif() throws IOException{
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/edituser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        dashboard.show();
    }
          
         
}
