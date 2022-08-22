/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Java.Donasi;
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
public class DonasiController implements Initializable {

    @FXML
    private TableColumn<Donasi, Integer> colID;
    @FXML
    private TableColumn<Donasi, String> colNama;
    @FXML
    private TableColumn<Donasi, Integer> colTotal;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Donasi> tvDonasi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showDonasi();
    }    

    @FXML
    private void menuDashboard(ActionEvent event) throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/LayarDashboard.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }
    @FXML
    private void menuBiodata(ActionEvent event) 
        throws IOException {
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
      public ObservableList<Donasi> getDonasiList(){
           ObservableList<Donasi> DonasiList = FXCollections.observableArrayList();
           Connection conn = getConnection();
           String query = "SELECT * FROM donasi";
           Statement st;
           ResultSet rs;
           try{
           st = conn.createStatement();
           rs = st.executeQuery(query);
          Donasi Donasi;
           while(rs.next()){
           Donasi = new Donasi(rs.getInt("id"),rs.getString("nama"),rs.getInt("total"));
           DonasiList.add(Donasi);
           }
    
       } catch(Exception ex){
         ex.printStackTrace();
               }
           return DonasiList;
       }
       public void showDonasi(){
           ObservableList<Donasi> list = getDonasiList();
           colID.setCellValueFactory(new PropertyValueFactory<Donasi, Integer>("id"));
           colNama.setCellValueFactory(new PropertyValueFactory<Donasi, String>("nama"));
           colTotal.setCellValueFactory(new PropertyValueFactory<Donasi,Integer>("total"));          
           tvDonasi.setItems(list);
       }
        private void insertRecord(){
            String query = "INSERT INTO donasi VALUES("+txtID.getText()+",'"+txtNama.getText()+"',"+txtTotal.getText()+")";
            executeQuery(query);
            showDonasi();
    }
    
    
       private void updateRecord(){
        String query ="UPDATE donasi SET nama='"+txtNama.getText()+"', total="+txtTotal.getText()+" WHERE id="+txtID.getText()+"";   
        executeQuery(query);
        showDonasi();
    }
         
       private void deleteRecord(){
        String query = "DELETE FROM donasi WHERE id="+txtID.getText()+"";        
        executeQuery(query);
        showDonasi();
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

    private void handleMouseAction(MouseEvent event) {
        Donasi Donasi = tvDonasi.getSelectionModel().getSelectedItem();
        txtID.setText(""+Donasi.getId());
       txtNama.setText(""+Donasi.getNama());
        txtTotal.setText(""+Donasi.getTotal());
     
    }
}
