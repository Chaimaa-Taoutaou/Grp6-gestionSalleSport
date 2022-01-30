package gestion_sport.Controller;


import gestion_sport.Model.connecter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddadhController implements Initializable {

    @FXML
    private TextField cin;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private ComboBox<type_Abonnement> abon;
    @FXML
    private ComboBox<salle> salles;
    @FXML
    private DatePicker date;
    @FXML
    private TextField adr;
    @FXML
    private RadioButton f;
    @FXML
    private RadioButton m;
    static final Integer[] sform = new Integer[1];

    connecter c = new connecter();
    ToggleGroup g= new ToggleGroup();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m.setToggleGroup(g);
        f.setToggleGroup(g);
        m.setSelected(true);
        try {

            ObservableList<type_Abonnement> listeabon = FXCollections.observableArrayList();
            ResultSet rs = c.getabon();

            while (rs.next()) {
                listeabon.add(new type_Abonnement(rs.getInt(1), rs.getString(2)));

            }
            abon.setItems(listeabon);
        } catch (SQLException ex) {
            Logger.getLogger(AddadhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addadr(MouseEvent mouseEvent) {
      //  final static id ;
        abon.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {

                sform[0] = newval.getId_talon();
             //  id =newval.getId_talon();

                System.out.print("hadi canim :"+sform[0]);





            }
        });
        System.out.println(sform[0]);
        String cN, n, p, t, ar, s = "M";
        if (f.isSelected()) {
            s = "f";


        }

        cN = cin.getText();
        n = nom.getText();
        p = prenom.getText();
        t = tel.getText();
        ar = adr.getText();
        String req = "INSERT INTO `adhrent` (`cin`, `nom`, `prenom`, `tel`, `adresse`, `sexe`, `date_register`, `id_abon`) " +
                "VALUES ('" + cN + "','" + n + "','" + p + "','" + t + "','" + ar + "','" + s + "','" + date.getValue() + "'," + sform[0] + ") ";
        if (n.isEmpty() || cN.isEmpty() || p.isEmpty() || ar.isEmpty() || t.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setContentText("essayez de remplir tous les champs");
            alert.showAndWait();

        }else {
        if (c.addadhr(req) == true) {
            final Node source = (Node) mouseEvent.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setContentText("Un adhrent a ete ajoutee");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);

            alert.setContentText("wssayez de remplire tous les champs");

            alert.showAndWait();

        }}
}

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






 */




