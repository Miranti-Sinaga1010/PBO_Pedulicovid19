/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Java.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BiodataController implements Initializable {

    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<user> tvBiodata;
    @FXML
    private TableColumn<user, String> colUsername;
    @FXML
    private TableColumn<user, String> colPassword;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menuDashboard(ActionEvent event)  throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/LayarDashboard.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }

    @FXML
    private void menuBiodata(ActionEvent event) throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/Biodata.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }

    @FXML
    private void menuAbout(ActionEvent event)throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/About.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }

    @FXML
    private void exit(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("“Apakah Anda hendak keluar dari aplikasi?”");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk keluar tekan Cencel Untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Alert keluar = new Alert(Alert.AlertType.INFORMATION); ;            
            keluar.setContentText("Anda memilih keluar!!");
            keluar.showAndWait();
            
            System.exit(0);
        } else {
            alert.close();
        }
    
    }

    @FXML
    private void logout(ActionEvent event)throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("“Apakah Anda hendak logout?”");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk logout");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Alert keluar = new Alert(Alert.AlertType.INFORMATION);             
            keluar.setContentText("Anda memilih logout!");
            keluar.showAndWait();
            
            FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/SplashscreenLogin.fxml"));
            Parent parent = tujuan.load();            
            Scene x = new Scene(parent);            
            Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            myStage.setScene(x);
            myStage.show();
            
        } else {
            alert.close();
        }
        
    }

    

    @FXML
    private void handleButtonAction(ActionEvent event) {
      if(event.getSource() == btnInsert){
            insertRecord();
        }else if(event.getSource() == btnUpdate){
            updateRecord();
    }   else if(event.getSource() == btnDelete){
            deleteRecord();
    }
    }
       private Connection getConnection() {
      Connection conn;
      try{
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pedulicovid_pbo", "root", "");
        return conn;
      }catch(Exception ex){
          System.out.println("Error :" +ex.getMessage());
          return null;
      }
               
    }
       public ObservableList<user> getuserList(){
           ObservableList<user> userList = FXCollections.observableArrayList();
           Connection conn = getConnection();
           String query = "SELECT * FROM user";
           Statement st;
           ResultSet rs;
           try{
           st = conn.createStatement();
           rs = st.executeQuery(query);
           user user;
           while(rs.next()){
           user = new user(rs.getString("username"), rs.getString("password"));
           userList.add(user);
           }
           
       } catch(Exception ex){
         ex.printStackTrace();
               }
           return userList;
       }
       public void showuser(){
           ObservableList<user> list = getuserList();
           colUsername.setCellValueFactory(new PropertyValueFactory<user,String>("username"));
           colPassword.setCellValueFactory(new PropertyValueFactory<user, String>("password"));
           tvBiodata.setItems(list);
       }
        private void insertRecord(){
            String query = "INSERT INTO user VALUES('"+txtUsername.getText()+",'"+txtPassword.getText()+"')";
            executeQuery(query);
            showuser();
    }
    
    
       private void updateRecord(){
        String query ="UPDATE user SET password='"+txtPassword.getText()+"' WHERE username='"+txtUsername.getText()+"'";   
        executeQuery(query);
        showuser();
    }
         
       private void deleteRecord(){
        String query = "DELETE FROM user  WHERE password ="+txtPassword.getText()+"";        
        executeQuery(query);
        showuser();
    }
       
    private void executeQuery(String query){
        Connection conn = getConnection();
        Statement st;        
        try{

            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

   
    
}
