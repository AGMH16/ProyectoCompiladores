package gt.edu.url.compiladores.prueba1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gt.edu.url.compiladores.clases.Informacion;
import gt.edu.url.compiladores.clases.TheTokens;
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
import javax.swing.JPopupMenu;
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
        System.out.println("RUTA COMPLETA" + archivo.getAbsolutePath());
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
                        proyectos.get(proyectos.size() - 1).setRutaCarpeta(archivo.getAbsolutePath());
                        proyectos.get(proyectos.size() - 1).setNombrecarpeta(nombrecarpeta);
                        proyectos.get(proyectos.size() - 1).setRuta(lista_de_archivos[i].getAbsolutePath());
                        proyectos.get(proyectos.size() - 1).setNombre(lista_de_archivos[i].getName());
                        jList1.setModel(modeloLista);

                    }
                }
            }
        }
        rutaactiva = archivo.getAbsolutePath();

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

    private void compilar(String nombreArchivo, String nuevaruta) {
        int cont = 1;
        boolean error = false;

        File archivo;
        FileReader fr;
        BufferedReader br;
        jTextArea2.setText(null);
        try {
            archivo = new File(nombreArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            //Lexico compilador = new Lexico(br);
            LexicoCup compilador = new LexicoCup(br);
            
            Sintactico sintactico = new Sintactico(compilador);
            sintactico.parse();
            
            String resultado = "LINEA " + cont + "\t\t\tSIMBOLO\n";

          /*  while (true) {
                Token token = compilador.yylex();
                if (token == null) {
                    resultado += "FIN";
                    //jTextArea2.append(resultado);
                    //jTextArea2.set(Color.ORANGE);

                    if (error == false) {
                        System.out.println("Sin errores");
                        TheTokens thetokens = new TheTokens();
                        thetokens.Guardar_Archivo(nuevaruta + ".loop", resultado);
                        jTextArea2.append(resultado);
                        jTextArea2.setForeground(Color.blue);

                        Sintactico sintactico = new Sintactico(compilador);
                        sintactico.parse();
                    } else {
                        jTextArea2.append(resultado);
                        jTextArea2.setForeground(Color.red);
                    }
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
                        break;
                    case Devolver:
                        resultado += "  <Reservada Devolver>\t\t" + compilador.lexeme + "\n";
                        break;
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
                        error = true;
                        break;
                    case ErrorNum:
                        resultado += "  <Número mal escrito>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorDec:
                        resultado += "  <Número mal escrito>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorCom:
                        resultado += "  <Símbolo no valido com>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorOp_IN:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorOp_Rel:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorOp_Atr:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorSigP:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorOPARBo:
                        resultado += "  <Símbolo no valido>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorSig:
                        resultado += "  <Signos inválidos>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorSIGNOS:
                        resultado += "  <Signos inválidos S>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorSIGNOS1:
                        resultado += "  <Signos inválidos S1>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorSIGNOS2:
                        resultado += "  <Signos inválidos S2>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorID:
                        resultado += "  <Variable mal escrita>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ErrorNameClss:
                        resultado += "  <Clase mal escrita>\t\t" + compilador.lexeme + "\n";
                        error = true;
                        break;
                    case ERROR:
                        resultado += "  <Simbolo no definido>\n";
                        error = true;
                        break;
                    default:
                        resultado += "  < " + compilador.lexeme + " >\n";
                        error = true;
                        break;
                }
            }*/
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

        popupMenu2 = new java.awt.PopupMenu();
        menuItem1 = new java.awt.MenuItem();
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
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        popupMenu2.setLabel("popupMenu2");

        menuItem1.setLabel("Nuevo Archivo");
        menuItem1.setName("");
        popupMenu2.add(menuItem1);

        popupMenu2.getAccessibleContext().setAccessibleParent(jPanel2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setLocation(new java.awt.Point(0, 0));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

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

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));

        jTabbedPane1.setBackground(new java.awt.Color(0, 51, 51));
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

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setText("Nueva Carpeta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setText(">");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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
                        .addGap(16, 16, 16)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
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

    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jList1KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        // modeloLista.
        //jList1.setModel(modeloLista);
        if (evt.isMetaDown()) {
            String busqueda = jList1.getSelectedValue();
            System.out.println("Busqueda-- " + busqueda);

            System.out.println("derecho");
            String nuevoarchivo = JOptionPane.showInputDialog("Nuevo archivo. Agregue nombre.", JOptionPane.QUESTION_MESSAGE);
            System.out.println(nuevoarchivo);
            if (nuevoarchivo.equals("") || nuevoarchivo.equals(" ")) {
                nuevoarchivo = JOptionPane.showInputDialog("Nuevo archivo. Agregué nombre.", JOptionPane.QUESTION_MESSAGE);
            } else {
                try {
                    /* proyectos.get(proyectos.size() - 1).setRuta(rutaCarpeta + nuevoarchivo + ".txt");
                    proyectos.get(proyectos.size() - 1).setNombre(nuevoarchivo + ".txt");
                    proyectos.get(proyectos.size() - 1).Guardar_Archivo(rutaCarpeta + nuevoarchivo + ".txt", "entero Principal ()");
                    modeloLista.addElement("    " + nuevoarchivo + ".txt");
                    jList1.setModel(modeloLista);*/
                    //String busqueda = jList1.getSelectedValue().trim();
                    System.out.println("busqueda" + busqueda);
                    System.out.println("nuevoarchivo---" + nuevoarchivo);
                    System.out.println("Ruta activa--" + rutaactiva);
                    modeloLista.addElement("    " + nuevoarchivo + ".txt");
                    proyectos.add(new Informacion());
                    proyectos.get(proyectos.size() - 1).setRutaCarpeta(rutaactiva);
                    proyectos.get(proyectos.size() - 1).setNombrecarpeta(busqueda);
                    String rut = rutaactiva + "/" + nuevoarchivo + ".txt";
                    proyectos.get(proyectos.size() - 1).setRuta(rut);
                    proyectos.get(proyectos.size() - 1).setNombre(nuevoarchivo + ".txt");
                    proyectos.get(proyectos.size() - 1).setContenido("entero Principal ()");
                    proyectos.get(proyectos.size() - 1).Crear(rut, "entero Principal ()");
                    //pn = proyectos.get(proyectos.size() - 1).Lectura_de_proyectos(rut,nuevoarchivo + ".txt", jTabbedPane1);
                    //t = info.getT();
                    jList1.setModel(modeloLista);

                    /* for (Informacion info : proyectos) {
                    System.out.println("*CARPETAACTIVA------: " + carpetaactiva);
                    System.out.println("*RUTAACTIVA---------: " + rutaactiva);
                    System.out.println("*ARCHIVO------: " + info.getNombre());
                    System.out.println("*CARPETANOMBRE "+ info.getNombrecarpeta());
                    System.out.println("*Ruta"+ info.getRuta());
                    /* if (busqueda.equals(info.getNombrecarpeta())) {
                    System.out.println("entraaaa");
                    carpetaactiva = info.getNombrecarpeta();
                    rutaactiva = info.getRutaCarpeta();
                    info.setNombre(nuevoarchivo + ".txt");
                    info.setRuta(rutaactiva + nuevoarchivo + ".txt");
                    info.Guardar_Archivo(rutaactiva + nuevoarchivo + ".txt", "entero Principal ()");
                    //proyectos.get(proyectos.size() - 1).setRuta(rutaactiva + nuevoarchivo + ".txt");
                    //proyectos.get(proyectos.size() - 1).setNombre(nuevoarchivo + ".txt");
                    //proyectos.get(proyectos.size() - 1).Guardar_Archivo(rutaactiva + nuevoarchivo + ".txt", "entero Principal ()");
                    modeloLista.addElement("    " + nuevoarchivo + ".txt");
                    jList1.setModel(modeloLista);
                    pn = info.Lectura_de_proyectos(nuevoarchivo + ".txt", rutaactiva + nuevoarchivo + ".txt", jTabbedPane1);
                    t = info.getT();
                    //rutaactiva = info.getRuta();
                    }
                    
                    }*/
                } catch (IOException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (evt.isAltDown()) {
            System.out.println("alt o scroll");
        } else if (evt.isShiftDown()) {
            System.out.println("click+shift");
        } else //indica si se hizo click izquierdo con el mouse
        //lbl_salida.setText(“Click Izquierdo del mouse”);
        {
            String busqueda = jList1.getSelectedValue().trim();
            System.out.println("busqueda->" + busqueda);
            for (Informacion info : proyectos) {
                System.out.println("CARPETA------: " + carpetaactiva);
                System.out.println("RUTA---------: " + rutaactiva);
                System.out.println("ARCHIVO------: " + info.getNombre());
                if (info.getNombre().equals(busqueda)) {
                    if (info.getNombrecarpeta().equals(carpetaactiva)) {
                        System.out.println("Si selecciono todo de forma correcta ");
                        System.out.println("CARPETA: " + carpetaactiva);
                        String ruta = info.getRuta();
                        System.out.println("RUTA: " + rutaactiva);
                        System.out.println("ARCHIVO: " + info.getNombre());
                        pn = info.Lectura_de_proyectos(busqueda, ruta, jTabbedPane1);
                        t = info.getT();
                        //jTabbedPane1.add("prueba1", txt);
                    }
                } else if (busqueda.equals(info.getNombrecarpeta())) {
                    carpetaactiva = info.getNombrecarpeta();
                    rutaactiva = info.getRutaCarpeta();
                    System.out.println("LA RUTA ACTIVA " + rutaactiva);
                }
            }
            System.out.println("izquierzo con mouse ");
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
                    String ruta = info.getRuta();
                    System.out.println("RUTA: " + rutaactiva);
                    System.out.println("ARCHIVO: " + info.getNombre());
                    info.Guardar_Archivo(ruta, texto);
                    //t = info.getT();
                    //jTabbedPane1.add("prueba1", txt);
                }
            } else if (busqueda.equals(info.getNombrecarpeta())) {
                carpetaactiva = info.getNombrecarpeta();
                rutaactiva = info.getRutaCarpeta();
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
        File archivo = new File(ruta);
        String nombrecarpeta = archivo.getName();
        modeloLista.addElement(nombrecarpeta);
        //int tamaño = ruta.length();
        //String extencion = lista_de_archivos[i].getCanonicalPath().substring(tamaño - 3, tamaño);
        /*if (extencion.equals("txt")) {
            System.out.println(lista_de_archivos[i].getAbsolutePath());
            System.out.println(lista_de_archivos[i].getName());
            modeloLista.addElement("    " + lista_de_archivos[i].getName());*/
        proyectos.add(new Informacion());
        proyectos.get(proyectos.size() - 1).setRutaCarpeta(archivo.getAbsolutePath());
        proyectos.get(proyectos.size() - 1).setNombrecarpeta(nombrecarpeta);
        //proyectos.get(proyectos.size() - 1).setRuta(.getAbsolutePath());
        //proyectos.get(proyectos.size() - 1).setNombre(lista_de_archivos[i].getName());
        jList1.setModel(modeloLista);

        // }
        //File c= new File(ruta);
        //jFileChooser1.setF(c);
        /*obtener_todos_los_archivos(new File(ruta));
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class
                    .getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        TheTokens thetokens = new TheTokens();
        int index = jTabbedPane1.getSelectedIndex();
        String nombre = jTabbedPane1.getTitleAt(index);
        System.out.println(t.getText());

        System.out.println("//------- nombre -------//" + nombre);
        //System.out.println("//------- datos -------//" + datos);
        for (Informacion info : proyectos) {
            if (info.getNombre().equals(nombre)) {
                if (info.getNombrecarpeta().equals(carpetaactiva)) {
                    rutaactiva = info.getRuta();
                    String nuevaruta = thetokens.separarExtencion(rutaactiva);
                    compilar(rutaactiva, nuevaruta);
                    //jTabbedPane1.add("prueba1", txt);

                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private java.awt.MenuItem menuItem1;
    private java.awt.PopupMenu popupMenu2;
    // End of variables declaration//GEN-END:variables
}
