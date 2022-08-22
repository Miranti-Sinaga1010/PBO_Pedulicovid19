/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedulicovid19;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class CreateAkun {
    public static void main(String[] args) throws IOException{
        File file = new File("src/kelompok10.txt");
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write("Admin");
            bw.newLine();
            bw.write("Admin10");
        }
        catch(FileNotFoundException ex){
            System.out.println("File "+file.getName()+"Tidak Ada");
        }
        catch(IOException ex){
            System.out.println("File "+file.getName()+"Tidak dapat dibaca");
        }
    }
}
