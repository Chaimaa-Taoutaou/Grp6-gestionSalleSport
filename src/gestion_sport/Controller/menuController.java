/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestion_sport;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class menuController implements Initializable {

    @FXML
    private Button buttona;
    @FXML
    private Button buttons;
    @FXML
    private Button buttonf;
    @FXML
    private Button buttonadh;
    @FXML
    private Button buttonac;
    @FXML
    private Button buttonds;
    @FXML
    private Button buttonstt;
    
    @FXML Pane mainPane;

    /**
     * Initializes the controller class.
     */
      
   
       public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Pane pane =  FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // TODO
    }    
    public void bta() throws IOException{
        try {
        
            Pane pane =  FXMLLoader.load(getClass().getResource("/gestion_sport/Accueil.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
   
    }
 
    public void btnclick(){
        System.out.println(".menuController.btu()");
    try {
        
            Pane pane =  FXMLLoader.load(getClass().getResource("/gestion_sport/users.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    } 
      public void bts() throws IOException{
     Pane pane =  FXMLLoader.load(getClass().getResource("/gestion_sport/Salle.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
    }  
        public void btf() throws IOException{
     Pane pane =  FXMLLoader.load(getClass().getResource("/gestion_sport/Formateur.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
    }  
         public void btad() throws IOException{
      Pane pane =  FXMLLoader.load(getClass().getResource("/gestion_sport/Adhérent.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
    }   
            public void btact() throws IOException{
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/Activite.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage dashboard = new Stage();
            dashboard.setScene(scene);
            dashboard.setTitle("Activites");
            dashboard.show();
    }  
               public void btaset() throws IOException{
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestion_sport/parametres.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage dashboard = new Stage();
            dashboard.setScene(scene);
            dashboard.setTitle("Settings");
            dashboard.show();
    }
    
    
   
   
    
}
