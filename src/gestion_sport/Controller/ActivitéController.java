/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_sport.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ActivitéController implements Initializable {

    @FXML
    private TableView<type_sport> tableadr;
    @FXML
    private TableColumn<type_sport,String> noma;
    @FXML
    private TableColumn<type_sport, String> prn;
    @FXML
    private TableColumn<salle, String> salle;
    @FXML
    private TableColumn<type_sport, Integer> nbr;
   @FXML
    private TableColumn<salle, Boolean> edit;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
