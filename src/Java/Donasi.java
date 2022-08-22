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
public class Donasi {
    int id;
    String nama;
    int total;

    public Donasi(int id, String nama, int total) {
        this.id = id;
        this.nama = nama;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getTotal() {
        return total;
    }
    
    
    
}
