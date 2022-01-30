
package gestion_sport.Controller;
import gestion_sport.Model.connecter;
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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;
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
   
    
     static String emailuser,nu,pu,pasu;
    @FXML
    private TextField searcusers;
    @FXML
    private Button closeButton;
    
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
    public void afficher() throws SQLException{
        ls.clear();
        connecter c=new connecter();
        ResultSet rs=c.utilsateurinfs();
        while(rs.next()){
            ls.add(new user(rs.getString(2),rs.getString(3),rs.getString(4)));  
            tableusers.setItems(ls);
        }
    }
    
     void charger() throws SQLException{
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
                     String emal = tableusers.getItems().get(x).getEmail();
                     String nom = tableusers.getItems().get(x).getNom();
                      String prenom = tableusers.getItems().get(x).getPrenom();
                     // String prenom = tableusers.getItems().get(x).get;
                   emailuser=emal;
                   nu=nom;
                   pu=prenom;
                    try {
                        modif();
                    } catch (IOException ex) {
                                     System.out.println("vv");                    }
                     
                }
                
            });
            
            deleteButton.setStyle("-fx-background-color: #f5053d; -fx-border-radius: #cc0000;");
            deleteButton.setTextFill(Color.WHITE);
            deleteButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int x = getIndex();
                    String em = tableusers.getItems().get(x).getEmail();
                
                    Alert dialogC = new Alert(AlertType.CONFIRMATION);
            
             dialogC.setHeaderText(null);
              dialogC.setContentText("Voulez vous vraiment supprimer cet utilisateur");
              Optional<ButtonType> answer = dialogC.showAndWait();
             if (answer.get() == ButtonType.OK) {
                 connecter c= new connecter();
                String req="Delete From utilisateur where email_u='"+ em +"'";
                c.delser(req);
                        try {
                            charger();
                        } catch (SQLException ex) {
                            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
                        }
               }
            
                    
           

                }
            });
            
        }

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
    private void adduser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/adduser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
        dashboard.setResizable(false);
        dashboard.initStyle(StageStyle.UNDECORATED);

        dashboard.show();
    }
        
    private void modif() throws IOException{
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/View/edituser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setResizable(false);
        dashboard.initStyle(StageStyle.UNDECORATED);
        dashboard.setScene(scene);
          dashboard.show();
 

    
     //dashboard.setResizable(false);
    }
    
      
        
        
    @FXML
         public void ref() throws SQLException{
        charger();
       
       }
      
    /*   private void exi() throws IOException{
           // System.exit(1);
           
        Stage s = (Stage) cButton.getScene().getWindow();
            s.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage dashboard = new Stage();
        dashboard.setScene(scene);
         dashboard.show();
       
        } */
          public void search(){
         FilteredList<user> filteredData = new FilteredList<>(ls, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searcusers.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<user> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        //      Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableusers.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableusers.setItems(sortedData);
    }
        
    
}
    
    
         
         


     
     
        
         
        
        
       
                
      
      
               
               
     