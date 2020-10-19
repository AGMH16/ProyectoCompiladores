/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.compiladores.clases;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Toshiba
 */
public class TheTokens {

    public String separarExtencion(String ruta) {
        int tamaño = ruta.length();
        String nuevaruta="";
        for (int i = 0;i<tamaño+1;i++){
            if (i>4){
            nuevaruta = ruta.substring(tamaño-i, tamaño-4);}
        }
        return nuevaruta;
    }

    public void Guardar_Archivo(String ruta, String texto) {
        try {
            FileOutputStream x = new FileOutputStream(ruta);
            OutputStreamWriter y = new OutputStreamWriter(x, "ISO-8859-1");
            y.append(texto);
            y.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRRO" + e);
        }
    }
}
