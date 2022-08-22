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
public class Cekresiko {
    int id;
    String judul;
    String jenis;
    String tingkat;

    public Cekresiko(int id, String judul, String jenis, String tingkat) {
        this.id = id;
        this.judul = judul;
        this.jenis = jenis;
        this.tingkat = tingkat;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getJenis() {
        return jenis;
    }

    public String getTingkat() {
        return tingkat;
    }

    
    
}
