package gestion_sport.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PrintController implements Initializable {

    @FXML
    private AnchorPane printpage;
    @FXML
    private Label cin;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label telephone;
    @FXML
    private Label date;
    @FXML
    private Label adresse;
    @FXML
    private Label sexe;
    @FXML
    private Label abonnement;
    @FXML
    private Label montant;
    @FXML
    private Button printBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void printBtnAction() {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        Printer printer = printerJob.getPrinter();
        if (printerJob != null) {
            PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 0,0,0,0);

            //anchorPane is printpage where recu adherent should be
            boolean success = printerJob.printPage(pageLayout, printpage);
            if (success) {
                printerJob.endJob();
            }
        }

    }

    public void displayAdh(Adherent adherent){
        cin.setText(adherent.getCin());
        nom.setText(adherent.getNom());
        prenom.setText(adherent.getPrenom());
        telephone.setText(adherent.getTel());
        date.setText(adherent.getDateString());
        adresse.setText(adherent.getAdresse());
        sexe.setText(adherent.getS());
        abonnement.setText(adherent.getTypeabon());
        montant.setText(String.valueOf(adherent.getPrix()));

    }


    public void quitter(MouseEvent mouseEvent) {
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();


    }

}
