
package gestion_sport.Controller;

import gestion_sport.Model.connecter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AddsalleController implements Initializable {

    @FXML
    private Button addsal;
    @FXML
    private TextField ns;
    @FXML
    private TextField vs;
    @FXML
    private TextField ad;
    @FXML
    private TextField tels;
    @FXML
    private TextField es;

    @FXML
      private javafx.scene.control.Button closeButton; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void vider(){
    
        ns.setText("");
         vs.setText("");
          ad.setText("");
           tels.setText("");
            es.setText("");
    
    }
    
    
            public  void ajoutersal(MouseEvent event) {
      String n,v,e,a,t;
       n=ns.getText();
       v=vs.getText();
       a=ad.getText();
        e=es.getText();
          t=tels.getText();
      
        if (n.isEmpty() || e.isEmpty() || v.isEmpty() || n.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("essayez de remplir tous les champs");
            alert.showAndWait();

        }else {
         connecter c =new connecter();
           if(c.addsalle(n, a, v, t,e)==true){
               final Node source = (Node) event.getSource();
               final Stage stage = (Stage) source.getScene().getWindow();
               stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("");
                alert.setHeaderText(null);

              alert.setContentText("Une salle a été ajouté");

                   alert.showAndWait();
                   
                   
           }else {
           System.out.println("benbactam");
           
           }
        
        }}
    @FXML
    private void leave(MouseEvent event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
