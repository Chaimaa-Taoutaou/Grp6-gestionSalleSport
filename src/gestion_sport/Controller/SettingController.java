/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_sport.Controller;

import gestion_sport.Model.connecter;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SettingController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private Button btn1;

    @FXML
    private TextField username;
    @FXML
    private TextField passc;
    @FXML
    private PasswordField passn;
    @FXML
    private PasswordField pass;
    private File mainfile;
    @FXML


    connecter c = new connecter();
    String m;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unit();
        passc.setStyle("-fx-alignment : CENTER;");
    }

    void unit() {
        m = LoginController.em;
        username.setText(m);
        pass.setText(LoginController.p);

    }

    public void updatuser() {
      /*   int id = c.recupid(m);
        String req = "UPDATE admin set usernam='" + username.getText() + "' ,password='" + pass.getText() + "' wher id=" + id + "";
       if(passc.getText().equals(passn.getText())){
        if(c.updatuser(req)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("");
                alert.setHeaderText(null);
              alert.setContentText("vos informations sont modifiees");

                   alert.showAndWait();}
       
        }*/

    }

    public void buttonclick(ActionEvent actionEvent) {

    }

    public void buttonclick(javafx.event.ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File f = fileChooser.showOpenDialog(img1.getScene().getWindow());
        System.out.println(f.getAbsolutePath());
        img1.setImage(new Image(f.getAbsolutePath().toString()));

    }

     /*@FXML
    public void searchImage() {
       /* FileChooser fileChooser = new FileChooser();
        //Extention filter remove below two comments for extension filter
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Images (.png, .jpg, .bmp)", "*.jpg", "*.png", "*.bmp");
        fileChooser.getExtensionFilters().add(extentionFilter);
        //Set to user directory or go to default if cannot access
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if (!userDirectory.canRead()) {
            userDirectory = new File("/image/");
        }
        fileChooser.setInitialDirectory(userDirectory);

        //Choose the file
        File chosenFile = fileChooser.showOpenDialog(null);
        //Make sure a file was selected, if not return default
        String path;
        if (chosenFile != null) {
            path = chosenFile.getPath();
            File file = new File(path);
            mainfile = file;

            //to set image in image view
          //  Image image = new Image(file.toURI().toString());
            //imgview.setImage(image);

        } else {
            //default return value
            path = null;
        }
         FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Open Resource File");
         File f = fileChooser.showOpenDialog(imgview.getScene().getWindow());
         System.out.println(f.getAbsolutePath());
         imgview.setImage(new Image(f.getAbsolutePath().toString()));

     }*/





}

