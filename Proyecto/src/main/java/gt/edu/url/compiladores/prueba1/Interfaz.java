package gt.edu.url.compiladores.prueba1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gt.edu.url.compiladores.clases.Informacion;
import java.awt.Color;
import java.awt.event.KeyEvent;
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
import javax.accessibility.AccessibleContext;
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
    JScrollPane pn = new JScrollPane();
    JTextArea t = new JTextArea();

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
            Lexico compilador = new Lexico(br);
            String resultado = "LINEA " + cont + "\t\t\tSIMBOLO\n";

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
                        resultado += "  <Debe cerrar las comillas>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Desde:
                        resultado += "  <Estructura iterativa For>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Igual:
                        resultado += "  <Operador igual>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Suma:
                        resultado += "  <Operador suma>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Resta:
                        resultado += "  <Operador resta>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Multiplicacion:
                        resultado += "  <Operador multiplicación>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Division:
                        resultado += "  <Operador división>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Mod:
                        resultado += "  <Operador MOD>\t\t" + compilador.lexeme + "\n";
                        break;
                    case CadenaAEntero:
                        resultado += "  <Función especial>\t\t" + compilador.lexeme + "\n";
                        break;
                    case FunESPReal:
                        resultado += "  <Función especial>\t\t" + compilador.lexeme + "\n";
                        break;
                    case CadenaABoleano:
                        resultado += "  <Función especial>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Eliminar:
                        resultado += "  <Reservada Instanciación >\t\t" + compilador.lexeme + "\n";
                        break;
                    case Destructor:
                        resultado += "  <Reservada Destructor >\t\t" + compilador.lexeme + "\n";
                        break;
                    case Incluir:
                        resultado += "  <Reservadan Carga Bibliotecas>\t" + compilador.lexeme + "\n";
                        break;
                    case BiExt:
                        resultado += "  <Bibliotecas Externas>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Estatico:
                        resultado += "  <Método >\t\t\t" + compilador.lexeme + "\n";
                        break;
                    case OPARBool:
                        resultado += "  <Operadores Boleanos >\t\t" + compilador.lexeme + "\n";
                        break;
                    case Exponente:
                        resultado += "  <Operador Exponencial >\t\t" + compilador.lexeme + "\n";
                        break;
                    case Cadena:
                        resultado += "  <Tipo de dato>\t\t" + compilador.lexeme + "\n";
                        break;
                    case T_dato:
                        resultado += "  <Tipo de dato>\t\t" + compilador.lexeme + "\n";
                        break;
                    case T_DisponibleP:
                        resultado += "  <Disponibilidad para Propiedades>\t" + compilador.lexeme + "\n";
                        break;
                    case T_DisponibleM:
                        resultado += "  <Disponibilidad para Métodos>\t" + compilador.lexeme + "\n";
                        break;
                    case Nulo:
                        resultado += "  <Tipo de dato>\t\t\t" + compilador.lexeme + "\n";
                        break;
                    case Si:
                        resultado += "  <Estructura selectiva If>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Entonces:
                        resultado += "  <Estructura selectiva If>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Sino:
                        resultado += "  <Estructura selectiva If>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Hacer:
                        resultado += "  <Estructura Iterativa Do-While>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Mientras:
                        resultado += "  <Estructura Iterativa While>\t\t" + compilador.lexeme + "\n";
                        break;
                    /* case Para:
                        resultado += "  <Estructura Iterativa For>\t" + compilador.lexeme + "\n";
                        break;*/
                    case Devolver:
                        resultado += "  <Reservada Devolver>\t\t" + compilador.lexeme + "\n";
                        break;
                    /*case Op_logico:
                        resultado += "  <Operador logico>\t" + compilador.lexeme + "\n";
                        break;*/
                    case Op_incremento:
                        resultado += "  <Operador incremento|decremento>\t" + compilador.lexeme + "\n";
                        break;
                    case Op_relacional:
                        resultado += "  <Operador relacional>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Op_atribucion:
                        resultado += "  <Operador atribucion>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Op_booleano:
                        resultado += "  <Operador booleano>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Parentesis_a:
                        resultado += "  <Paréntesis de apertura>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Parentesis_c:
                        resultado += "  <Paréntesis de cierre>\t\t" + compilador.lexeme + "\n";
                        break;    
                    case Llave_a:
                        resultado += "  <Llave de apertura>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Llave_c:
                        resultado += "  <Llave de cierre>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Corchete_a:
                        resultado += "  <Corchete de apertura>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Corchete_c:
                        resultado += "  <Corchete de cierre>\t\t" + compilador.lexeme + "\n";
                        break;   
                    case Principal:
                        resultado += "  <Reservada Main>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Metodo:
                        resultado += "  <Reservada Método>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Leer:
                        resultado += "  <Reservada Leer>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Escribir:
                        resultado += "  <Reservada Escribir>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Instanciar:
                        resultado += "  <Reservada Instanciar>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Constructor:
                        resultado += "  <Reservada Constructor>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Propiedad:
                        resultado += "  <Reservada Propiedades>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Clase:
                        resultado += "  <Clase>\t\t\t" + compilador.lexeme + "\n";
                        break;
                    case NameClass:
                        resultado += "  <Nombre de una clase>\t\t" + compilador.lexeme + "\n";
                        break;
                    /*case CallFunofClss:
                        resultado += "  <Llamado a una función>\t\t" + compilador.lexeme + "\n";
                        break;*/    
                    case Punto:
                        resultado += "  <Opunto Punto>\t\t" + compilador.lexeme + "\n";
                        break;
                    case P_coma:
                        resultado += "  <Operador Punto y coma>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Coma:
                        resultado += "  <Operador Coma>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Dos_puntos:
                        resultado += "  <Operador Dos Punto>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Identificador:
                        resultado += "  <Identificador Variable>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Texto:
                        resultado += "  <Cadena de texto>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Entero:
                        resultado += "  <Reservada int>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Boleano:
                        resultado += "  <Reservada Bolean>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Numero:
                        resultado += "  <Número Entero>\t\t" + compilador.lexeme + "\n";
                        break;
                    case Decimal:
                        resultado += "  <Número Decimal>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorCer:
                        resultado += "  <Número mal escrito>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorNum:
                        resultado += "  <Número mal escrito>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorDec:
                        resultado += "  <Número mal escrito>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorComa:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorOp_IN:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorOp_Rel:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorOp_Atr:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorPar_a:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorPar_c:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorPun:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorLL_a:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorLL_c:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorCo_a:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorCo_c:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorP_C:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorD_pu:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorMul:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorMod:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorSigP:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorExp:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;   
                    case ErrorSum:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorRes:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;     
                    case ErrorOPARBo:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorSig:
                        resultado += "  <Signos inválidos>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorSig2:
                        resultado += "  <Signos inválidos>\t\t" + compilador.lexeme + "\n";
                        break;
                    case ErrorSIGNOS:
                        resultado += "  <Signos inválidos S>\t\t" + compilador.lexeme + "\n";
                        break;                        
                    case ErrorSIGNOS1:
                        resultado += "  <Signos inválidos S1>\t\t" + compilador.lexeme + "\n";
                        break; 
                    case ErrorSIGNOS2:
                        resultado += "  <Signos inválidos S2>\t\t" + compilador.lexeme + "\n";
                        break; 
                    case ErrorID:
                        resultado += "  <Variable mal escrita>\t\t" + compilador.lexeme + "\n";
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
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

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

        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
        });

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

        button1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        button1.setLabel(">>");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(11, 11, 11)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
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
        //JTextArea t = new JTextArea();
        //JScrollPane pn = new JScrollPane();
        int index = jTabbedPane1.getSelectedIndex();
        String nombre = jTabbedPane1.getTitleAt(index);
        //JTextArea t = (JTextArea) jTabbedPane1.getComponent(index);
        System.out.println(t.getText());

//pn = (JScrollPane) jTabbedPane1.getComponent(index);
        //t= (JTextArea) pn.getComponent(i);
        //t=(JTextArea) pn.getComponent(0);
        // Falta permitir que el usuario actualice su código
        //String datos = t.getText();
        System.out.println("//------- nombre -------//" + nombre);
        //System.out.println("//------- datos -------//" + datos);
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
                    pn = info.Lectura_de_proyectos(busqueda, rutaactiva, jTabbedPane1);
                    t = info.getT();
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String texto = t.getText();
        System.out.println(texto);
        String busqueda = jList1.getSelectedValue().trim();

        for (Informacion info : proyectos) {
            if (info.getNombre().equals(busqueda)) {
                if (info.getNombrecarpeta().equals(carpetaactiva)) {
                    System.out.println("Si selecciono todo de forma correcta ");
                    System.out.println("CARPETA: " + carpetaactiva);
                    rutaactiva = info.getRuta();
                    System.out.println("RUTA: " + rutaactiva);
                    System.out.println("ARCHIVO: " + info.getNombre());
                    info.Guardar_Archivo(rutaactiva, texto);
                    //t = info.getT();
                    //jTabbedPane1.add("prueba1", txt);
                }
            } else if (busqueda.equals(info.getNombrecarpeta())) {
                carpetaactiva = info.getNombrecarpeta();
                //rutaactiva = info.getRuta();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed
        // TODO add your handling code here:
        if (evt.VK_ENTER == evt.getKeyCode()) {
            String texto = t.getText();
            System.out.println(texto);
        }

    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        //try {
        // TODO add your handling code here:
        //  Acceso_a_la_pc();
        jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser1.showOpenDialog(null);
        String ruta = (jFileChooser1.getSelectedFile().toString());
        //File c= new File(ruta);
        //jFileChooser1.setF(c);
        /*obtener_todos_los_archivos(new File(ruta));
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class
                    .getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
