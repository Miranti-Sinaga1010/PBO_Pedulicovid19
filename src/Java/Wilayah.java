/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

/**
 *
 * @author Valentina Sinaga
 */
public class Wilayah {
    int id;
    String provinsi;
    String daerah;
    String status;

    public Wilayah(int id, String provinsi, String daerah, String status) {
        this.id = id;
        this.provinsi = provinsi;
        this.daerah = daerah;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public String getDaerah() {
        return daerah;
    }

    public String getStatus() {
        return status;
    }
    
    
    
    
}
