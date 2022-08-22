/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.entity.Perkembangancovid19;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LayarDashboardController implements Initializable {
    
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnBiodata;
    @FXML
    private Button btnAbout;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnCekresiko;
    @FXML
    private Button btnBerita;
    @FXML
    private Button btnGejala;
    @FXML
    private Button btnStatistik;
    @FXML
    private Button btnInformasi;
    @FXML
    private Button btnTips;
    @FXML
    private Button btnWilayah;
    @FXML
    private Button btnDonasi;
    @FXML
    private Button btnChathbox;
    
    int updatestate=0;
    @FXML Label terkonfirmasi_lbl;
    @FXML Label dirawat_lbl;
    @FXML Label sembuh_lbl;
    @FXML Label meninggal_lbl;
    @FXML Label plus_terkonfirmasi;
    @FXML Label plus_dirawat;
    @FXML Label plus_sembuh;
    @FXML Label plus_meninggal;
    @FXML private TextField tanggal_tf;
    
    @FXML
    private Pane linechart_pane;
    
    ObservableList<Perkembangancovid19> update = FXCollections.observableArrayList();
    private Object idUpdate;
    /**
     * Initializes the controller class.
     */
   
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
        int dialogButton = JOptionPane.showConfirmDialog(null, "Apakah Anda hendak logout?", "WARNING", JOptionPane.YES_NO_OPTION);

        if (dialogButton == JOptionPane.YES_OPTION) {
            FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/SplashscreenLogin.fxml"));
            Parent parent = tujuan.load();
            Scene x = new Scene(parent);
            Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            myStage.setScene(x);
            myStage.show();
        } else {

        }
    }
    @FXML
    private void exit(){
        int dialogButton = JOptionPane.showConfirmDialog(null, "Apakah Anda hendak keluar dari aplikasi?", "WARNING", JOptionPane.YES_NO_OPTION);

        if (dialogButton == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {

        }
    }
    
    
    @FXML
    private void statistik(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Statistik.fxml"));
        Parent parent = tujuan.load();
        Scene x = new Scene(parent);
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        myStage.setScene(x);
        myStage.show();
    }
    @FXML
    private void wilayah(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Wilayah.fxml"));
        Parent parent = tujuan.load();
        Scene x = new Scene(parent);
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        myStage.setScene(x);
        myStage.show();
    }
    
    @FXML
    private void berita(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Berita.fxml"));
        Parent parent = tujuan.load();
        Scene x = new Scene(parent);
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        myStage.setScene(x);
        myStage.show();
    }
   
    @FXML
    private void chatbox(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Chatbox.fxml"));
        Parent parent = tujuan.load();
        Scene x = new Scene(parent);
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        myStage.setScene(x);
        myStage.show();
    }
    
    @FXML
    private void actionCek(ActionEvent event) throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/CekResiko.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }
    
    @FXML
    private void actionGejala(ActionEvent event) throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/Gejala.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }
    
    @FXML
    private void actionInformasi(ActionEvent event) throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/Informasi.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }
    
    @FXML
    private void actionTips(ActionEvent event) throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/Tips.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }

    @FXML
    private void actionWilayah(ActionEvent event)throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/Wilayah.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }

    @FXML
    private void actionDonasi(ActionEvent event) throws IOException {
          FXMLLoader move = new FXMLLoader(getClass().getResource("/view/Donasi.fxml"));
                Parent parent = move.load();            
                Scene x = new Scene(parent);            
                Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                myStage.setScene(x);
                myStage.show();
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        Query query = ss.createQuery("from Perkembangancovid19");
        
        List list = query.list();
        Integer terkonfirmasi = 1441;
        Integer dirawat = 1217;
        Integer meninggal = 122;
        Integer sembuh = 75;
        for(Object o : list) {
            Perkembangancovid19 updatelist = (Perkembangancovid19) o;
            
            
            update.add(new Perkembangancovid19(updatelist.getIdperubahan(),updatelist.getWaktuperubahan(),updatelist.getTerkonfirmasi(), updatelist.getDirawat(), updatelist.getSembuh(), updatelist.getMeninggal()));
            meninggal += updatelist.getMeninggal();
            dirawat += updatelist.getDirawat();
            sembuh += updatelist.getSembuh();
            terkonfirmasi += updatelist.getTerkonfirmasi();
            
            

            
            if(updatelist.getIdperubahan()==list.size()){
                String plus1 = Integer.toString(updatelist.getTerkonfirmasi());
                this.plus_terkonfirmasi.setText("+ "+plus1);    
                String plus2 = Integer.toString(updatelist.getDirawat());
                this.plus_dirawat.setText("+ "+plus2);    
                String plus3 = Integer.toString(updatelist.getSembuh());
                this.plus_sembuh.setText("+ "+plus3);    
                String plus4 = Integer.toString(updatelist.getMeninggal());
                this.plus_meninggal.setText("+ "+plus4);    
            }
            
        }
        
       
        //Live Data
        String strDirawat = Integer.toString(dirawat);
        this.dirawat_lbl.setText(strDirawat);    
        String strTerkonfirmasi = Integer.toString(terkonfirmasi);
        this.terkonfirmasi_lbl.setText(strTerkonfirmasi);    
        String strSembuh = Integer.toString(sembuh);
        this.sembuh_lbl.setText(strSembuh);    
        String strMeninggal = Integer.toString(meninggal);
        this.meninggal_lbl.setText(strMeninggal);}
    
}
