package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddadhController implements Initializable {

    @FXML
    private ComboBox<salle> salle1;

    @FXML
    private TextField adr;

    @FXML
    private TextField cin;

    @FXML
    private DatePicker date;

    private String radioButtonLabel;

    @FXML
    private RadioButton f;

    @FXML
    private RadioButton m;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField tel;

    static final Integer[] asalle = new Integer[1];
    static String se;
    connecter c = new connecter();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            ObservableList<salle> listsalle = FXCollections.observableArrayList();
            ResultSet rs = c.getsalle();

            while (rs.next()) {
                listsalle.add(new salle(rs.getInt(1), rs.getString(2)));

            }
            salle1.setItems(listsalle);
            salle1.valueProperty().addListener((obs, oldval, newval) -> {
                if(newval != null) {

                    asalle[0] = newval.getId_s();

                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(AddadhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }






    public void addadr(MouseEvent mouseEvent) {
        ToggleGroup gender=new ToggleGroup();
        m.setToggleGroup(gender);
        f.setToggleGroup(gender);
        m.setOnAction(e->{
            radioButtonLabel= m.getText();
        });
        f.setOnAction(e->{
            radioButtonLabel=f.getText();
        });
        gender.selectedToggleProperty().addListener(
                (observable, oldToggle, newToggle) -> {
                    if (newToggle == m) {
                        se = "m";
                    } else if (newToggle == f) {
                        se = "f";
                    } else {
                        se = "?";
                    }
                }
        );

        String cN, n, p, t, ar;
        cN = cin.getText();
        n = nom.getText();
        p = prenom.getText();
        t = tel.getText();
        ar = adr.getText();
        String req = "INSERT INTO `adhrent` (`cin`, `nom`, `prenom`, `tel`, `adresse`, `sexe`, `date_register`, id_ts) " +
                "VALUES ('" + cN + "','" + n + "','" + p + "','" + t + "','" + ar + "','" + radioButtonLabel + "','" + date.getValue() + "'," + asalle[0] + ") ";

        if (c.addadhr(req) == true) {
            final Node source = (Node) mouseEvent.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Un adhrent a ete ajoutee");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);

            alert.setContentText("wssayez de remplire tous les champs");

            alert.showAndWait();

        }}

    public void leave(MouseEvent mouseEvent) {
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}


/*



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









    }

    public void addadr(MouseEvent mouseEvent) {
    }

    public void leave(MouseEvent mouseEvent) {
    }
*/