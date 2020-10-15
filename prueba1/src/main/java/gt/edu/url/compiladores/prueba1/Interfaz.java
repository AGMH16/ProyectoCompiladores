package gt.edu.url.compiladores.prueba1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gt.edu.url.compiladores.clases.Informacion;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Toshiba
 */
public class Interfaz extends javax.swing.JFrame {

    String carpetaactiva, rutaactiva;
    JMenuBar menu = new JMenuBar();
    DefaultListModel modeloLista = new DefaultListModel();

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        proyectos = new ArrayList();
        jList1.setModel(modeloLista);

        /*menu.setVisible(true);
        menu.*/
        //JFrame interfaz2=new JFrame();
        //interfaz2.setVisible(true);
        //jPanel2.add();
    }

    private void obtener_todos_los_archivos(File archivo) throws IOException {
        String nombrecarpeta = archivo.getName();
        System.out.println("NOMBRE CARPETA " + nombrecarpeta);
        modeloLista.addElement(nombrecarpeta);
        File lista_de_archivos[] = archivo.listFiles();
        if (lista_de_archivos != null) {
            for (int i = 0; i < lista_de_archivos.length; i++) {
                if (lista_de_archivos[i].isDirectory()) {
                    obtener_todos_los_archivos(lista_de_archivos[i]);
                } else {
                    int tamaño = lista_de_archivos[i].getAbsolutePath().length();
                    String extencion = lista_de_archivos[i].getCanonicalPath().substring(tamaño - 3, tamaño);
                    if (extencion.equals("txt")) {
                        System.out.println(lista_de_archivos[i].getAbsolutePath());
                        System.out.println(lista_de_archivos[i].getName());
                        modeloLista.addElement("    " + lista_de_archivos[i].getName());
                        proyectos.add(new Informacion());
                        proyectos.get(proyectos.size() - 1).setNombrecarpeta(nombrecarpeta);
                        proyectos.get(proyectos.size() - 1).setRuta(lista_de_archivos[i].getAbsolutePath());
                        proyectos.get(proyectos.size() - 1).setNombre(lista_de_archivos[i].getName());
                        jList1.setModel(modeloLista);

                    }
                }
            }
        }

    }

    private void compilar2(String ruta) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        //FileNotFoundException, IOException {
// TODO code application logic here
        // TODO code application logic here

        FileInputStream fis = new FileInputStream(ruta);
        InputStreamReader is = new InputStreamReader(fis, "UTF-8");
        BufferedReader bf = new BufferedReader(is);
        String linea;
        while ((linea = bf.readLine()) != null) {
            System.out.println(linea);
        }
        bf.close();
        is.close();
        fis.close();
        // }
    }

    private void compilar(String nombreArchivo) {
        int cont = 1;
        File archivo;
        FileReader fr;
        BufferedReader br;
        try {
            archivo = new File(nombreArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            prueba2 compilador = new prueba2(br);
            String resultado = "LINEA " + cont + "\t\tSIMBOLO\n";

            while (true) {
                Token token = compilador.yylex();
                if (token == null) {
                    resultado += "FIN";
                    jTextArea2.append(resultado);
                    return;
                }
                switch (token) {
                    case Linea:
                        cont++;
                        resultado += "LINEA " + cont + "\n";
                        break;
                    case Comillas:
                        resultado += "  <Comillas>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Desde:
                        resultado += "  <Reservada >\t" + compilador.lexeme + "\n";
                        break;
                    case CadenaAEntero:
                        resultado += "  <Funcion especial>\t" + compilador.lexeme + "\n";
                        break;
                    case FunESPReal:
                        resultado += "  <Funcion especial>\t" + compilador.lexeme + "\n";
                        break;
                    case CadenaABoleano:
                        resultado += "  <Funcion especial>\t" + compilador.lexeme + "\n";
                        break;
                     case Eliminar:
                        resultado += "  <Reservada >\t\t" + compilador.lexeme + "\n";
                        break; 
                     case Destructor:
                        resultado += "  <Reservada >\t\t" + compilador.lexeme + "\n";
                        break; 
                     case Incluir:
                        resultado += "  <Reservada >\t\t" + compilador.lexeme + "\n";
                        break;   
                     case Estatico:
                        resultado += "  <Reservada >\t\t" + compilador.lexeme + "\n";
                        break;   
                    case OPARBool:
                        resultado += "  <Operadores Boleanos >\t" + compilador.lexeme + "\n";
                        break;    
                    case Exponente:
                        resultado += "  <Operador Exponencial >\t" + compilador.lexeme + "\n";
                        break;
                    case Cadena:
                        resultado += "  <Tipo de dato>\t" + compilador.lexeme + "\n";
                        break;
                    case T_dato:
                        resultado += "  <Tipo de dato>\t" + compilador.lexeme + "\n";
                        break;
                    case T_Disponible:
                        resultado += "  <Disponibilidad>\t" + compilador.lexeme + "\n";
                        break;
                    case Nulo:
                        resultado += "  <Reservada Nulo>\t" + compilador.lexeme + "\n";
                        break;
                    case Si:
                        resultado += "  <Reservada si>\t" + compilador.lexeme + "\n";
                        break;
                    case Entonces:
                        resultado += "  <Reservada Entonces>\t" + compilador.lexeme + "\n";
                        break;
                    case Sino:
                        resultado += "  <Reservada else>\t" + compilador.lexeme + "\n";
                        break;
                    case Hacer:
                        resultado += "  <Reservada do>\t" + compilador.lexeme + "\n";
                        break;
                    case Mientras:
                        resultado += "  <Reservada while>\t" + compilador.lexeme + "\n";
                        break;
                    case Para:
                        resultado += "  <Reservada For>\t" + compilador.lexeme + "\n";
                        break;
                    case Devolver:
                        resultado += "  <Reservada Devolver>\t" + compilador.lexeme + "\n";
                        break;
                    case Igual:
                        resultado += "  <Operador igual>\t" + compilador.lexeme + "\n";
                        break;
                    case Suma:
                        resultado += "  <Operador suma>\t" + compilador.lexeme + "\n";
                        break;
                    case Resta:
                        resultado += "  <Operador resta>\t" + compilador.lexeme + "\n";
                        break;
                    case Multiplicacion:
                        resultado += "  <Operador multiplicacion>\t" + compilador.lexeme + "\n";
                        break;
                    case Division:
                        resultado += "  <Operador division>\t" + compilador.lexeme + "\n";
                        break;
                    case Mod:
                        resultado += "  <Operador MOD>\t" + compilador.lexeme + "\n";
                        break;
                    case Op_logico:
                        resultado += "  <Operador logico>\t" + compilador.lexeme + "\n";
                        break;
                    case Op_incremento:
                        resultado += "  <Operador incremento>\t" + compilador.lexeme + "\n";
                        break;
                    case Op_relacional:
                        resultado += "  <Operador relacional>\t" + compilador.lexeme + "\n";
                        break;
                    case Op_atribucion:
                        resultado += "  <Operador atribucion>\t" + compilador.lexeme + "\n";
                        break;
                    case Op_booleano:
                        resultado += "  <Operador booleano>\t" + compilador.lexeme + "\n";
                        break;
                    case Parentesis_a:
                        resultado += "  <Parentesis de apertura>\t" + compilador.lexeme + "\n";
                        break;
                    case Parentesis_c:
                        resultado += "  <Parentesis de cierre>\t" + compilador.lexeme + "\n";
                        break;
                    case Llave_a:
                        resultado += "  <Llave de apertura>\t" + compilador.lexeme + "\n";
                        break;
                    case Llave_c:
                        resultado += "  <Llave de cierre>\t" + compilador.lexeme + "\n";
                        break;
                    case Corchete_a:
                        resultado += "  <Corchete de apertura>\t" + compilador.lexeme + "\n";
                        break;
                    case Corchete_c:
                        resultado += "  <Corchete de cierre>\t" + compilador.lexeme + "\n";
                        break;
                    case Principal:
                        resultado += "  <Reservada main>\t" + compilador.lexeme + "\n";
                        break;
                    case Metodo:
                        resultado += "  <Reservada Metodo>\t" + compilador.lexeme + "\n";
                        break;
                    case Leer:
                        resultado += "  <Reservada Leer>\t" + compilador.lexeme + "\n";
                        break;
                    case Escribir:
                        resultado += "  <Reservada Escribir>\t" + compilador.lexeme + "\n";
                        break;
                    case Instanciar:
                        resultado += "  <Reservada Instanciar>\t" + compilador.lexeme + "\n";
                        break;
                    case Constructor:
                        resultado += "  <Reservada Constructor>\t" + compilador.lexeme + "\n";
                        break;
                    case Propiedad:
                        resultado += "  <Reservada Propiedades>\t" + compilador.lexeme + "\n";
                        break;
                    case Clase:
                        resultado += "  <Clase>\t\t" + compilador.lexeme + "\n";
                        break;
                    case NameClass:
                        resultado += "  <Nombre de una clase>\t" + compilador.lexeme + "\n";
                        break;
                    case Punto:
                        resultado += "  <Op. Punto>\t\t" + compilador.lexeme + "\n";
                        break;
                    case P_coma:
                        resultado += "  <Punto y coma>\t" + compilador.lexeme + "\n";
                        break;
                    case Coma:
                        resultado += "  <coma>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Dos_puntos:
                        resultado += "  <Op. Punto>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Identificador:
                        resultado += "  <Identificador>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Entero:
                        resultado += "  <Reservada int>\t" + compilador.lexeme + "\n";
                        break;
                    case Boleano:
                        resultado += "  <Reservada Bolean>\t" + compilador.lexeme + "\n";
                        break;
                    case Numero:
                        resultado += "  <Numero>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorNum:
                        resultado += "  <Simbolo incorrecto>\t" + compilador.lexeme + "\n";
                        break;                            
                     case ERROR:
                        resultado += "  <Simbolo no definido>\n";
                        break;
                    default:
                        resultado += "  < " + compilador.lexeme + " >\n";
                        break;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRRO LEYENDO ARCHIVO" + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        button1 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton2.setText("Abrir proyecto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel3.setBackground(new java.awt.Color(102, 51, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );

        button1.setLabel("button1");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //  Acceso_a_la_pc();
            jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jFileChooser1.showOpenDialog(null);
            String ruta = (jFileChooser1.getSelectedFile().toString());

            obtener_todos_los_archivos(new File(ruta));
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        JTextArea t = new JTextArea();
        JScrollPane pn = new JScrollPane();
        int index = jTabbedPane1.getSelectedIndex();
        String nombre = jTabbedPane1.getTitleAt(index);
        pn = (JScrollPane) jTabbedPane1.getComponent(index);
        //t=(JTextArea) pn.getComponent(0);
        // Falta permitir que el usuario actualice su código
        String datos = t.getText();
        System.out.println("//------- nombre -------//" + nombre);
        System.out.println("//------- datos -------//" + datos);
        for (Informacion info : proyectos) {
            if (info.getNombre().equals(nombre)) {
                if (info.getNombrecarpeta().equals(carpetaactiva)) {
                    rutaactiva = info.getRuta();
                    compilar(rutaactiva);
                    //jTabbedPane1.add("prueba1", txt);

                }
            }
        }
        //jTabbedPane1.getComponent();
        /*JTextArea txt = new JTextArea();
        txt.setBounds(150, 150, 10, 20);
        txt.setBackground(Color.red);

        txt.append("quiza salga");
        txt.append("veamos si sale");
        txt.setVisible(true);*/

    }//GEN-LAST:event_button1ActionPerformed

    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jList1KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        // modeloLista.
        //jList1.setModel(modeloLista);
        String busqueda = jList1.getSelectedValue().trim();
        System.out.println("busqueda" + busqueda);
        for (Informacion info : proyectos) {
            System.out.println("CARPETA------: " + carpetaactiva);
            System.out.println("RUTA---------: " + rutaactiva);
            System.out.println("ARCHIVO------: " + info.getNombre());
            if (info.getNombre().equals(busqueda)) {
                if (info.getNombrecarpeta().equals(carpetaactiva)) {
                    System.out.println("Si selecciono todo de forma correcta ");
                    System.out.println("CARPETA: " + carpetaactiva);
                    rutaactiva = info.getRuta();
                    System.out.println("RUTA: " + rutaactiva);
                    System.out.println("ARCHIVO: " + info.getNombre());
                    info.Lectura_de_proyectos(busqueda, rutaactiva, jTabbedPane1);
                    //jTabbedPane1.add("prueba1", txt);
                }
            } else if (busqueda.equals(info.getNombrecarpeta())) {
                carpetaactiva = info.getNombrecarpeta();
                //rutaactiva = info.getRuta();
            }
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged


    }//GEN-LAST:event_jList1ValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    private ArrayList<Informacion> proyectos;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
