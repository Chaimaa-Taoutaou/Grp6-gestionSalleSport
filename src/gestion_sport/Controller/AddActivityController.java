package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class AddActivityController implements Initializable {

    @FXML
    private ComboBox<Formateur> formateur;

    @FXML
    private TextField nom;

    @FXML
    private TextField prix;
    @FXML
    private Label item;
    static  final Integer[] sform = new Integer[1];
    static Integer nomf;
    static ArrayList<Formateur> f=new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
            connecter c=new connecter();
            ObservableList<Formateur> listformateur= FXCollections.observableArrayList();
            ResultSet rs=c.getFormateur();
            while (rs.next()){
                listformateur.add(new Formateur(rs.getInt(1),rs.getString(2)));
            }
            formateur.setItems(listformateur);

            formateur.valueProperty().addListener((obs, oldval, newval) -> {
                if(newval != null) {

                    sform[0] = newval.getId_f();
                    System.out.println("Selected airport: " + newval.getNom_p()
                            + ". ID: " + sform[0]);
                    nomf= newval.getId_f();
                    f.add(new Formateur(nomf,newval.getNom_p()));
                    System.out.println(f.get(0));
            }
        });
            //formateur.getSelectionModel().select(1);
           // formateur.setValue("Select Item From ComboBox");
           // String selectedItem = formateur.getSelectionModel().getSelectedItem();
           // item.setText(selectedItem);
            ajouterActivity();
        }catch (Exception ex){
            //Logger.getLogger(AddActivityController.class.getName()).log(Level.SEVER,null,ex);
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    void ajouterActivity() {

        String snom;
        float sprix;

        snom = nom.getText();
        sprix = Float.parseFloat(prix.getText());
        System.out.println("sprix"+sprix+" snom:"+snom);

        connecter c = new connecter();
        System.out.println("Selected airport: "
                + ". ID22: " + sform[0]);
       if(c.addactivity(snom,sprix,sform[0])) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("");
           alert.setHeaderText(null);
           // Stage stage = (Stage) closeButton.getScene().getWindow();
           // stage.close();
           alert.setContentText("Une salle a été ajouté");

           alert.showAndWait();

       }

    }
    public void vider(){

        nom.setText("");
        prix.setText("");
       // formateur.setValue("");
        //clear comboBox

    }

}
