
package gestion_sport;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hp
 */
public class Gestion_Sport extends Application {
    
   @Override
    public void start(Stage primaryStage) {
       try{
        Parent p;
            p = FXMLLoader.load(getClass().getResource("/gestion_sport/menu.fxml"));
         Scene s = new Scene(p);
           s.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
          primaryStage.setScene(s);
         
         primaryStage.initStyle(StageStyle.UTILITY);
         primaryStage.setResizable(false);
         primaryStage.show();
            primaryStage.setTitle("     Connection");
        }catch(Exception ex){
            System.out.println("bhsjbcsjhd");
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
