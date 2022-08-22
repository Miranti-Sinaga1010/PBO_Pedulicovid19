/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Java.Cekresiko;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CekResikoController implements Initializable {

    @FXML
    private TableView<Cekresiko> tvResiko;
    @FXML
    private TableColumn<Cekresiko, Integer> colID;
    @FXML
    private TableColumn<Cekresiko, String> colJudul;
    @FXML
    private TableColumn<Cekresiko, String> colJenis;
    @FXML
    private TableColumn<Cekresiko, String> colTingkat;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtJudul;
    @FXML
    private TextField txtJenis;
    @FXML
    private TextField txtTingkat;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCekresiko();
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
    private void menuBiodata(ActionEvent event)  throws IOException {
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
    private void logout(ActionEvent event) throws IOException{
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
 private void handleButtonAction(ActionEvent event)  {
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
  public ObservableList<Cekresiko> getCekresikoList(){
           ObservableList<Cekresiko>cekresikoList = FXCollections.observableArrayList();
           Connection conn = getConnection();
           String query = "SELECT * FROM cekresiko";
           Statement st;
           ResultSet rs;
           try{
           st = conn.createStatement();
           rs = st.executeQuery(query);
         Cekresiko cekresiko;
           while(rs.next()){
            cekresiko = new Cekresiko(rs.getInt("id"),rs.getString("judul"),rs.getString("jenis"),rs.getString("tingkat"));
          cekresikoList.add(cekresiko);
           }
           
       } catch(Exception ex){
         ex.printStackTrace();
               }
           return cekresikoList;
       }
  public void showCekresiko(){
           ObservableList<Cekresiko> list = getCekresikoList();
           colID.setCellValueFactory(new PropertyValueFactory<Cekresiko, Integer>("id"));
           colJudul.setCellValueFactory(new PropertyValueFactory<Cekresiko, String>("judul"));
           colJenis.setCellValueFactory(new PropertyValueFactory<Cekresiko, String>("jenis"));
           colTingkat.setCellValueFactory(new PropertyValueFactory<Cekresiko, String>("tingkat"));  
           tvResiko.setItems(list);
       }
        private void insertRecord(){
            String query = "INSERT INTO cekresiko VALUES("+txtID.getText()+",'"+txtJudul.getText()+"','"+txtJenis.getText()+"','"+txtTingkat.getText()+"')";
            executeQuery(query);
           showCekresiko();
    }
    
    
       private void updateRecord(){
        String query ="UPDATE cekresiko SET judul = '"+txtJudul.getText()+"', jenis='"+txtJenis.getText()+"',tingkat='"+txtTingkat.getText()+"' WHERE id="+txtID.getText()+"";   
        executeQuery(query);
        showCekresiko();
    }
         
       private void deleteRecord(){
        String query = "DELETE FROM cekresiko WHERE id = "+txtID.getText()+"";        
        executeQuery(query);
        showCekresiko();
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
