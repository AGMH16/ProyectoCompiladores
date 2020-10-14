/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.compiladores.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author Toshiba
 */
public class Informacion {

    private String ruta, contenido, nombre, nombrecarpeta;
    private Informacion siguiente;

    public Informacion() {
        this.nombrecarpeta = "";
        this.ruta = "";
        this.nombre = "";
        this.contenido = "";
    }

    public Informacion(String nombrecarpeta, String ruta, String nombre, String contenido) {
        this.nombrecarpeta = nombrecarpeta;
        this.ruta = ruta;
        this.nombre = nombre;
        this.contenido = contenido;
    }

    public String getNombrecarpeta() {
        return nombrecarpeta;
    }

    public void setNombrecarpeta(String nombrecarpeta) {
        this.nombrecarpeta = nombrecarpeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Informacion getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Informacion siguiente) {
        this.siguiente = siguiente;
    }

    public void Lectura_de_proyectos(String name, String nombreArchivo, JTabbedPane panel) {//jTabbedPane1.add("prueba1", txt);
        JTextArea t = new JTextArea();
        //JPanel p= new JPanel();
        t.setSize(564, 332);

        JScrollPane scrll = new JScrollPane(t);
        //p.setSize(564, 332);
        //scrll.setSize(, 0);

        /* File archivo;
        FileReader fr;
        BufferedReader br;*/
        try {
            /*archivo = new File(nombreArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                
            }
            br.close();
            fr.close();*/
            FileInputStream fis = new FileInputStream(ruta);
            InputStreamReader is = new InputStreamReader(fis, "UTF-8");
            BufferedReader bf = new BufferedReader(is);
            String linea;
            while ((linea = bf.readLine()) != null) {
                //System.out.println(linea);
                t.append(linea + "\n");

            }
            bf.close();
            is.close();
            fis.close();

            panel.add(name, scrll);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRRO LEYENDO ARCHIVO" + e);
        }

    }

}
