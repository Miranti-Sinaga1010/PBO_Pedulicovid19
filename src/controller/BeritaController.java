/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import hibernate.entity.Berita;
import hibernate.entity.Perkembangancovid19;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BeritaController implements Initializable {

    int updatestate=0;
    @FXML Button tambah_btn;
    @FXML private TextField waktu_tf;
    @FXML private TextField judul_tf;
    @FXML private TextField detail_tf;
    @FXML private TextField sumber_tf;
    @FXML private TextField file_tf;
    @FXML private TableView<Berita> berita_tbl;
    @FXML private TableColumn<Berita,String> waktu_col;
    @FXML private TableColumn<Berita,String> judul_col;
    @FXML private TableColumn<Berita,String> detail_col;
    @FXML private TableColumn<Berita,String> sumber_col;
    @FXML private TableColumn<Berita,String> file_col;
    
    ObservableList<Berita> update = FXCollections.observableArrayList();
    private Object idUpdate;
    
    
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
    private void Berita(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Berita.fxml"));
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
    private void tambah(ActionEvent event) throws IOException{
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        
        Query query = ss.createQuery("from Berita");
        
        

        List list = query.list();
        int id = list.size()+1;
        if(updatestate==0){
            Berita berita = new Berita();
            berita.setIdberita(id);
            berita.setWaktuberita(waktu_tf.getText());
            berita.setJudul(judul_tf.getText());
            berita.setDetail(detail_tf.getText());
            berita.setSumber(sumber_tf.getText());
            berita.setFile(file_tf.getText());
            ss.save(berita);
            JOptionPane.showMessageDialog(null,"Data berhasil di tambahkan");
            
        }else{
           Query queryUpdate = ss.createQuery("UPDATE Berita SET waktuberita= :waktuberita, judul= :judul, detail= :detail, sumber= :sumber, file= :file where idberita = :idberita");
           
            queryUpdate.setParameter("idberita", idUpdate);
            queryUpdate.setParameter("waktuberita", waktu_tf);
            queryUpdate.setParameter("judul", judul_tf);
            queryUpdate.setParameter("detail", detail_tf);
            queryUpdate.setParameter("sumber", sumber_tf);
            queryUpdate.setParameter("file", file_tf);
            
            queryUpdate.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data berhasil diubah");
        }
        
        ss.getTransaction().commit();
        ss.close();
        Berita(event);
    }
    
    @FXML
    private void ubah(ActionEvent event) throws IOException{
        int selected = berita_tbl.getSelectionModel().getSelectedIndex();
        int selected_id = 0;
        if(selected >= 0){
            if(berita_tbl.getSelectionModel().getSelectedItem() != null){
                Berita berita = berita_tbl.getSelectionModel().getSelectedItem();
                selected_id = berita.getIdberita();
            }
            
            Session ss = util.HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            Query query = ss.createQuery("from Berita where idberita = "+selected_id);
            
            List list = query.list();
            for(Object o : list){
                Berita berita = (Berita)o;
                waktu_tf.setText(berita.getWaktuberita());
                judul_tf.setText(berita.getJudul());
                detail_tf.setText(berita.getDetail());
                sumber_tf.setText(berita.getSumber());
                file_tf.setText(berita.getFile());
            }
            
            ss.getTransaction().commit();
            ss.close();
            updatestate=1;
            idUpdate = selected_id;
            tambah_btn.setText("Update");
        }else{
            JOptionPane.showMessageDialog(null, "Pilih item yang hendak diubah");
        }
    }
    
    @FXML
    private void hapus (ActionEvent event) throws IOException{
        int selected = berita_tbl.getSelectionModel().getSelectedIndex();
        int selected_id = 0;
        if(selected >= 0){
            if(berita_tbl.getSelectionModel().getSelectedItem() != null){
                Berita berita = berita_tbl.getSelectionModel().getSelectedItem();
                selected_id = berita.getIdberita();
            }
            
            Session ss = util.HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            Query query = ss.createQuery("delete from Berita where idberita = :idberita");
            query.setParameter("idberita", selected_id);
            query.executeUpdate();
            
            ss.getTransaction().commit();
            ss.close();
            
            Berita(event);
        }else{
            JOptionPane.showMessageDialog(null, "Pilih item yang hendak dihapus");
        }
    }
    
    @FXML
    private void reset(ActionEvent event){
        waktu_tf.setText("");
        judul_tf.setText("");
        detail_tf.setText("");
        sumber_tf.setText("");
        file_tf.setText("");
    }
    
    @FXML
    private void lihat(ActionEvent event) throws IOException{
        int selected = berita_tbl.getSelectionModel().getSelectedIndex();
        int selected_id = 0;
        
        if(selected >= 0){
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  HH:mm");  
        Date sekarang = new Date();
        this.waktu_tf.setText(dateFormat.format(sekarang));
        
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        Query query = ss.createQuery("from Berita");
        
        List list = query.list();
        for(Object o : list){
            Berita updatelist = (Berita) o;
            update.add(new Berita(updatelist.getIdberita(), updatelist.getWaktuberita(), updatelist.getJudul(), updatelist.getDetail(),updatelist.getSumber(),updatelist.getFile()));
            }
        
        
        //Tabel
        this.berita_tbl.setItems(update);
        this.waktu_col.setCellValueFactory(new PropertyValueFactory<>("waktuberita"));
        this.judul_col.setCellValueFactory(new PropertyValueFactory<>("judul"));
        this.detail_col.setCellValueFactory(new PropertyValueFactory<>("detail"));
        this.sumber_col.setCellValueFactory(new PropertyValueFactory<>("sumber"));
        this.file_col.setCellValueFactory(new PropertyValueFactory<>("file"));
        
        ss.getTransaction().commit();
        ss.close();
        
    }    
    
}
