package gt.edu.url.compiladores.prueba1;
import static gt.edu.url.compiladores.prueba1.Token.*;
%%
%class prueba2
//%standalone
%type Token
/*----------------------------------COMENTARIOS----------------------------------------------*/
    LineTerminator = \r|\n|\r\n
    InputCharacter = [^\r\n]
    WhiteSpace     = {LineTerminator} | [ \t\f]

    /* comments */
    Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

    TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    // Comment can be the last line of the file, without line terminator.
    EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
    DocumentationComment = "/**" {CommentContent} "*"+ "/"
    CommentContent       = ( [^*] | \*+ [^/*] )*

    //Identifier = [:jletter:] [:jletterdigit:]*

    //DecIntegerLiteral = 0 | [1-9][0-9]*
/*----------------------------------Asignación de valores ----------------------------*/
    L = [a-z][a-zA-Z_]*
    //L = [a-zA-Z_]+
    D = [0-9]+
    l = [A-Z][A-Za-z_]*
    espacio=[ ,\t,\r,\n]+
/*-----------Igual-------------*/
    Sigual= "="
/*---------Salto de linea------*/
    SaltoL = "\n"
/*---------Comillas------------*/
    Comillas = "\""
/*-----------Coma--------------*/
    Coma= ","
/*----------Tipos de datos------*/
    T_dato="real"
/*------Tipo de dato Cadena-----*/
    Cadena="cadena"
/*Tipo de Dato por disponibilidad*/
    T_Disponible =( publicas | privadas | publicos | privados )
/*-----------Nulo---------------*/
    Nulo= "nulo"
/*-----------Si---------------*/
    Si = "si"
/*-----------Entonces---------------*/
    Entonces = "entonces"
/*-----------Sino---------------*/
    Sino = "sino"
/*-----------Hacer---------------*/
    Hacer= "hacer"

/*-----------Mientras---------------*/
    Mientras = "mientras"
/*-----------Para---------------*/
    Para= "para"

/*-----------Devolver---------------*/
    Devolver = "devolver"

/*-----------Suma---------------*/
    Suma= "+"
/*-----------Resta---------------*/
    Resta = "-"
/*-----------Multiplicación---------------*/
    Multiplicacion = "*"
/*-----------División---------------*/
    Division = "/"
/*-----------Mod---------------*/
    Mod = "%"
/*-----------Operadores lógicos---------------*/
    Op_logico = ( "&&" | "||" | "!" | "&" | "|" )
/*-----------Operadores de cremento y decremento---------------*/
    Op_incremento = ( "++" | "--" | "incrementar"| "decrementar" )
/*-----------Operadores relacionales---------------*/
    Op_relacional = ( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" )
/*-----------Operadores de atribución---------------*/
    Op_atribucion = ( "+=" | "-="  | "*=" | "/=" | "%=")
/*-----------Operadores boleanos---------------*/
    Op_booleano = ("verdadero" | "falso")
/*-----------Parentesis inicial---------------*/
    Parentesis_a = "("
/*-----------Parentesis Final---------------*/
    Parentesis_c = ")"
/*-----------Punto---------------*/
    Punto= "."
/*-----------Leer---------------*/
    Leer = "leer"
/*-----------llave inicial---------------*/
    Llave_a = "{"
/*----------LLave final---------------*/
    Llave_c = "}"
/*-----------Corchete inicial---------------*/
    Corchete_a = "["
/*-----------Corchete final---------------*/
    Corchete_c = "]"
/*-----------Constructor y Destructor---------------*/
    Constructor = "constructor"
    Destructor = "destructor"

/*-----------Principal---------------*/
    Principal = "Principal"
/*-----------Metodo---------------*/
    Metodo = ("Metodos" | "metodos" | "metodo" |"Metodo" )
/*-----------Propiedad---------------*/
    Propiedad = ( "propiedad" | "propiedades" ) 
/*-----------Clase---------------*/
    Clase = "clase"
/*-----------Punto y coma---------------*/
    P_coma = ";"
/*-----------Instanciar---------------*/
    Instanciar = "instanciar"

/*-----------Escribir---------------*/
    Escribir = "escribir"
/*-----------Entero---------------*/
    Entero = "entero"
/*-----------Don puntos---------------*/
    Dos_puntos = ":"
/*-----------Boleano---------------*/
    Boleano = "boleano"
/*-----------Palabra---------------*/
  //  Palabra= {L}({L}|{D})*

/*-----------Barra espaciadora -----------*/
   // BEsp= "\r"
/*-----------Nombre de una clase---------------*/
    NameClass = {l}({l}|{D})*
/*-----------Palabra reservada Desde---------------*/
Desde = "desde"
/*------------Funciones especiales--------------*/
CadenaAEntero = "cadenaAEntero"
CadenaAReal = "cadenaAReal"
CadenaABoleano = "cadenaABoleano"
Seno = "seno"
Conseno = "coseno"
Tangente = "tangente"
Logaritmo = "logaritmo"
Raiz = "raiz"
FunESPReal={CadenaAReal}|{Seno}|{Conseno}|{Tangente}|{Logaritmo}|{Raiz}
AND = "AND"
OR = "OR"
OPARBool = {AND}|{OR}
Exponente = "^"
/*-----------Palabra reservada eliminar---------------*/
Eliminar = "eliminar"
Incluir = "incluir"
/*------------------Extras--------------------*/
Recursividad = "recursividad"

Estatico = "estatico"
/*-----------Variable---------------*/
    Identificador = {L}({L}|{D})*
/*-----------Numero entero---------------*/
    Numero = ("(-"{D}+")")|{D}+
/*----------Error de números------------*/
    ErrorNum= {Numero}{Identificador}
/*--------------------------------------------Armar algoritos -------------------------------------------------*/
//Variables_enteras = {Entero}{Identificador}({Sigual}{Numero}{SaltoL}|{SaltoL})
//Variables_reales = {Real}{Identificador}
%{
    public String lexeme;
%}
%%
/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
{Comment} {/*Ignore*/} 

/* Comillas */
{ Comillas } {lexeme=yytext(); return Comillas;}

/* Tipos de datos */
{T_dato} {lexeme=yytext(); return T_dato;}

/* Tipo de dato Cadena */
{Cadena} {lexeme=yytext(); return Cadena;}

/*Tipo dato Nulo*/
{Nulo} {lexeme=yytext(); return Nulo;}

/*Entero Main*/
{Entero} {lexeme=yytext(); return Entero;}

/*Tipo Booleano*/
{Boleano} {lexeme=yytext(); return Boleano;}

/* Palabra reservada Si */
{Si} {lexeme=yytext(); return Si;}

/* Palabra reservada Entonces */
{Entonces} {lexeme=yytext(); return Entonces;}

/* Palabra reservada Sino */
{Sino} {lexeme=yytext(); return Sino;}

/* Palabra reservada Hacer */
{Hacer} {lexeme=yytext(); return Hacer;}

/* Palabra reservada Mientras */
{Mientras} {lexeme=yytext(); return Mientras;}

/* Palabra reservada Para */
{Para} {lexeme=yytext(); return Para;}

/* Palabra reservada Devolver */
{Devolver } {lexeme=yytext(); return Devolver;}

/* Palabra reservada Instanciar */
{ Instanciar} {lexeme=yytext(); return Instanciar;}

/* Palabra reservada Leer */
{ Leer } {lexeme=yytext(); return Leer;}

/* Palabra reservada Escribir */
{ Escribir } {lexeme=yytext(); return Escribir;}

/* Punto */
{Punto} {lexeme=yytext(); return Punto;}

/* Operador Igual */
{Sigual} {lexeme=yytext(); return Igual;}

/* Operador Suma */
{Suma} {lexeme=yytext(); return Suma;}

/* Operador Resta */
{Resta} {lexeme=yytext(); return Resta;}

/* Operador Multiplicacion */
{Multiplicacion} {lexeme=yytext(); return Multiplicacion;}

/* Operador Division */
{Division} {lexeme=yytext(); return Division;}

/* Operador Mod */
{Mod} {lexeme=yytext(); return Mod;}

/*Operador Dos Puntos */
{Dos_puntos} {lexeme=yytext(); return Dos_puntos;}

/* Operadores logicos */
{Op_logico} {lexeme=yytext(); return Op_logico;}

/*Operadores Relacionales */
{Op_relacional} {lexeme = yytext(); return Op_relacional;}

/* Operadores Atribucion */
{Op_atribucion} {lexeme = yytext(); return Op_atribucion;}

/* Operadores Incremento y decremento */
{Op_incremento} {lexeme = yytext(); return Op_incremento;}

/*Operadores Booleanos*/
{Op_booleano} {lexeme = yytext(); return Op_booleano;}

/* Parentesis de apertura */
{Parentesis_a} {lexeme=yytext(); return Parentesis_a;}

/* Parentesis de cierre */
{Parentesis_c} {lexeme=yytext(); return Parentesis_c;}

/* Llave de apertura */
{Llave_a} {lexeme=yytext(); return Llave_a;}

/* Llave de cierre */
{Llave_c } {lexeme=yytext(); return Llave_c;}

/* Corchete de apertura */
{Corchete_a} {lexeme = yytext(); return Corchete_a;}

/* Corchete de cierre */
{Corchete_c} {lexeme = yytext(); return Corchete_c;}

/* Marcador de inicio de algoritmo */
{Principal} {lexeme=yytext(); return Principal;}

/* Marcador de inicio de algoritmo */
{Constructor} {lexeme=yytext(); return Constructor;}

/* Marcador inicio clase */
{Clase} {lexeme=yytext(); return Clase;}

/* Marcador Metodo */
{Metodo} {lexeme=yytext(); return Metodo;}

/* Marcador de Propiedades */
{Propiedad} {lexeme=yytext(); return Propiedad;}

/* Tipo de Dato por disponibilidad*/
{T_Disponible} {lexeme=yytext(); return T_Disponible;}

/* Punto y coma */
{P_coma} {lexeme=yytext(); return P_coma;}

/* Coma */
{Coma} {lexeme=yytext(); return Coma;}

/* Numero */
{Numero} {lexeme=yytext(); return Numero;}

/*Error de Número*/
{ErrorNum} {lexeme=yytext(); return ErrorNum;}

/*Nombre de una clase*/
{NameClass} {lexeme=yytext(); return NameClass;}

/* Reservada Desde */
{Desde} {lexeme=yytext(); return Desde;}
/* Funcion especial de enteros */
{CadenaAEntero} {lexeme=yytext(); return CadenaAEntero;}
/* Funciones especiales de reales */
{FunESPReal} {lexeme=yytext(); return FunESPReal;}
/* Funciones especiales a booleano */
{CadenaABoleano} {lexeme=yytext(); return CadenaABoleano;}
/* Palabra reservada eliminar */
{Eliminar} {lexeme=yytext(); return Eliminar;}
/* Reservada destructor */
{Destructor} {lexeme=yytext(); return Destructor;}
/* Reservada incluir */
{Incluir} {lexeme=yytext(); return Incluir;}
/* Reservada estatica*/
{Estatico} {lexeme=yytext(); return Estatico;}
/* Operadores para boleanos */
{OPARBool} {lexeme=yytext(); return OPARBool;}
/* Signo de exponente */
{Exponente} {lexeme=yytext(); return Exponente;}

/* Identificador */
{Identificador} {lexeme=yytext(); return Identificador;}


/* Error de analisis */
 . {return ERROR;}