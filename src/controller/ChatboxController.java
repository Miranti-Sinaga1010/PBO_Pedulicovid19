/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.entity.Chatbox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChatboxController implements Initializable {
    
    @FXML private TextField komentar;
    @FXML private TableView<Chatbox> komentar_tbl;
    @FXML private TableColumn<Chatbox,String> komentar_col;
    
    ObservableList<Chatbox> update = FXCollections.observableArrayList();

    @FXML
    private void menuDashboard(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/LayarDashboard.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
    }
    
    @FXML
    private void menuBiodata(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Biodata.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
    }
    
    @FXML
    private void Chatbox(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Chatbox.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
    }
    
    @FXML
    private void menuAbout(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/About.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
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
    private void exit(){
        
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
    void kirim(ActionEvent event) throws IOException{
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        Query query = ss.createQuery("from Chatbox");
        
        List list = query.list();
        int id = list.size()+1;
        Chatbox komentar = new Chatbox();
        komentar.setId(id);
        
        komentar.setKomentar(this.komentar.getText());
        
        ss.save(komentar);
        JOptionPane.showMessageDialog(null, "Komentar telah ditambahkan");
        ss.getTransaction().commit();
        
        ss.close();
        
        Chatbox(event);
        
    }
    
    @FXML
    void hapus(ActionEvent event) throws IOException{
        int selected = komentar_tbl.getSelectionModel().getSelectedIndex();
        int selected_id = 0;
        if(selected >= 0){
            if(komentar_tbl.getSelectionModel().getSelectedItem() != null){
                Chatbox komentar = komentar_tbl.getSelectionModel().getSelectedItem();
                selected_id = komentar.getId();
            }
            
            Session ss = util.HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            Query query = ss.createQuery("delete from Chatbox where id = :id");
            query.setParameter("id", selected_id);
            query.executeUpdate();
            
            ss.getTransaction().commit();
            ss.close();
            
            Chatbox(event);
           
        }else{
            JOptionPane.showMessageDialog(null, "Pilih komentar yang hendak di hapus");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        Query query = ss.createQuery("from Chatbox");
        
        List list = query.list();
  

        
        
        
        for(Object o : list) {
            Chatbox updatelist = (Chatbox) o;
            update.add(new Chatbox(updatelist.getId(),updatelist.getKomentar()));

        }
        
        
        // Tabel
        this.komentar_tbl.setItems(update);
        this.komentar_col.setCellValueFactory(new PropertyValueFactory<>("komentar"));
        
        ss.getTransaction().commit();
        
        ss.close();
    }    
    
}
