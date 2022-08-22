/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Valentina Sinaga
 */
public class SplashscreenLoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnReset;
    @FXML
    private PasswordField txtPassword;

      Connection con;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionLogin(ActionEvent event) {
         if (txtUsername.getText().isEmpty()) {
            return;
        }
        if (txtPassword.getText().isEmpty()) {
            return;
        }

        int status = DBConnection.checkLogin(txtUsername.getText().trim().toLowerCase(), txtPassword.getText());

        switch (status) {
            case 0: {
                Stage stage = (Stage) txtUsername.getScene().getWindow();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/LayarDashboard.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(SplashscreenLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.setScene(new Scene(root));
            }
            break;
            case -1:
                JOptionPane.showMessageDialog(null, "Connection Failed");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Username/Password Anda tidak terdaftar");
                break;
        }
    
    }

    @FXML
    private void actionReset(ActionEvent event) throws  Exception {
            txtUsername.clear();
            txtPassword.clear();
    }
    
}
