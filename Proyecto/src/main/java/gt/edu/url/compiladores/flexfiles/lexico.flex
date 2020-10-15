package gt.edu.url.compiladores.prueba1;
import static gt.edu.url.compiladores.prueba1.Token.*;
%%
%class Lexico
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
/*-------------------------------------Asignación de valores ---------------------------------*/
    L = [a-z][a-zA-Z_]*
    //L = [a-zA-Z_]+
    D = [0-9]+
    l = [A-Z][A-Za-z_]*
    espacio=[ ,\t,\r,\n]+
    SaltoL = "\n"

/*------------------------------------------Simbolos-------------------------------------------*/
    Sigual= "="
    Comillas = "\""
    Coma= ","
    Op_incremento = ( "++" | "--" )
    Op_relacional = ( ">" | "<" | "==" | "!=" | ">=" | "<=" )
    Op_atribucion = ( "+=" | "-="  | "*=" | "/=" | "%=")
    Parentesis_a = "("
    Parentesis_c = ")"
    Punto= "."
    Llave_a = "{"
    Llave_c = "}"
    Corchete_a = "["
    Corchete_c = "]"
    P_coma = ";"
    Dos_puntos = ":"
    Suma= "+"
    Resta = "-"
    Multiplicacion = "*"
    Division = "/"
    Mod = "%"
    AND = ("AND"|"&&")
    OR = ("OR"|"||")
    OPARBool = {AND}|{OR}
    Exponente = "^"
    Op_booleano = ("verdadero" | "falso")

/*----------------------------------------Estructura IF-----------------------------------------*/

    Si = "si"
    Entonces = "entonces"
    Sino = "sino"

/*---------------------------------------Palabras reservadas------------------------------------*/

    T_dato="real"
    Cadena="cadena"
    Nulo= "nulo"
    Entero = "entero"
    Boleano = "boleano"
/*---------------------------------------Métodos y Propiedades----------------------------------*/

    Metodo = "metodos"
    Propiedad = "propiedades"
    T_DisponibleM =("publicos"|"privados"|"protegidos")
    T_DisponibleP =("publicas"|"privadas"|"protegidas")

/*---------------------------------------Estructuras Iterativas---------------------------------*/
    Hacer= "hacer"
    Mientras = "mientras"
    Para= "para"
    Devolver = "devolver"
    Desde = "desde"
    
/*----------------------------------------Métodos de E/S-----------------------------------------*/

    Leer = "leer"
    Escribir = "escribir"

/*----------------------------------Constructores y Destructores---------------------------------*/

    Constructor = "constructor"
    Destructor = "destructor"

/*------------------------------------------------Main-------------------------------------------*/

    Principal = "Principal"

/*----------------------------------------------Clases-------------------------------------------*/

    Clase = "clase"
    NameClass = {l}({l}|{D})*

/*--------------------------------------------Instancias------------------------------------------*/

    Instanciar = "instanciar"
    Eliminar = "eliminar"


   // BEsp= "\r"
/*-----------------------------------Funciones Especiales-----------------------------------------*/
    CadenaAEntero = "cadenaAEntero"
    CadenaAReal = "cadenaAReal"
    CadenaABoleano = "cadenaABoleano"
    Seno = "seno"
    Conseno = "coseno"
    Tangente = "tangente"
    Logaritmo = "logaritmo"
    Raiz = "raiz"
    FunESPReal={CadenaAReal}|{Seno}|{Conseno}|{Tangente}|{Logaritmo}|{Raiz}

/*-----------------------------------------Carga de Bibliotecas-----------------------------------*/

    Incluir = "incluir"

/*--------------------------------------------Extras----------------------------------------------*/
    Estatico = "estatico"

/*-------------------------------------------Variables y Números----------------------------------*/
    Identificador = {L}({L}|{D})*
    Numero = ("(-"{D}+")")|{D}+

/*----------------------------------------------Errores-------------------------------------------*/

    ErrorNum= {Numero}{Identificador}

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

{ Comillas } {lexeme=yytext(); return Comillas;}
{T_dato} {lexeme=yytext(); return T_dato;}
{Cadena} {lexeme=yytext(); return Cadena;}
{Nulo} {lexeme=yytext(); return Nulo;}
{Entero} {lexeme=yytext(); return Entero;}
{Boleano} {lexeme=yytext(); return Boleano;}
{Si} {lexeme=yytext(); return Si;}
{Entonces} {lexeme=yytext(); return Entonces;}
{Sino} {lexeme=yytext(); return Sino;}
{Hacer} {lexeme=yytext(); return Hacer;}
{Mientras} {lexeme=yytext(); return Mientras;}
{Para} {lexeme=yytext(); return Para;}
{Devolver } {lexeme=yytext(); return Devolver;}
{ Instanciar} {lexeme=yytext(); return Instanciar;}
{ Leer } {lexeme=yytext(); return Leer;}
{ Escribir } {lexeme=yytext(); return Escribir;}
{Punto} {lexeme=yytext(); return Punto;}
{Sigual} {lexeme=yytext(); return Igual;}
{Suma} {lexeme=yytext(); return Suma;}
{Resta} {lexeme=yytext(); return Resta;}
{Multiplicacion} {lexeme=yytext(); return Multiplicacion;}
{Division} {lexeme=yytext(); return Division;}
{Mod} {lexeme=yytext(); return Mod;}
{Dos_puntos} {lexeme=yytext(); return Dos_puntos;}
//{Op_logico} {lexeme=yytext(); return Op_logico;}
{Op_relacional} {lexeme = yytext(); return Op_relacional;}
{Op_atribucion} {lexeme = yytext(); return Op_atribucion;}
{Op_incremento} {lexeme = yytext(); return Op_incremento;}
{Op_booleano} {lexeme = yytext(); return Op_booleano;}
{Parentesis_a} {lexeme=yytext(); return Parentesis_a;}
{Parentesis_c} {lexeme=yytext(); return Parentesis_c;}
{Llave_a} {lexeme=yytext(); return Llave_a;}
{Llave_c } {lexeme=yytext(); return Llave_c;}
{Corchete_a} {lexeme = yytext(); return Corchete_a;}
{Corchete_c} {lexeme = yytext(); return Corchete_c;}
{Principal} {lexeme=yytext(); return Principal;}
{Constructor} {lexeme=yytext(); return Constructor;}
{Clase} {lexeme=yytext(); return Clase;}
{Metodo} {lexeme=yytext(); return Metodo;}
{Propiedad} {lexeme=yytext(); return Propiedad;}
{T_DisponibleM} {lexeme=yytext(); return T_DisponibleM;}
{T_DisponibleP} {lexeme=yytext(); return T_DisponibleP;}
{P_coma} {lexeme=yytext(); return P_coma;}
{Coma} {lexeme=yytext(); return Coma;}
{Numero} {lexeme=yytext(); return Numero;}
{ErrorNum} {lexeme=yytext(); return ErrorNum;}
{NameClass} {lexeme=yytext(); return NameClass;}
{Desde} {lexeme=yytext(); return Desde;}
{CadenaAEntero} {lexeme=yytext(); return CadenaAEntero;}
{FunESPReal} {lexeme=yytext(); return FunESPReal;}
{CadenaABoleano} {lexeme=yytext(); return CadenaABoleano;}
{Eliminar} {lexeme=yytext(); return Eliminar;}
{Destructor} {lexeme=yytext(); return Destructor;}
{Incluir} {lexeme=yytext(); return Incluir;}
{Estatico} {lexeme=yytext(); return Estatico;}
{OPARBool} {lexeme=yytext(); return OPARBool;}
{Exponente} {lexeme=yytext(); return Exponente;}
{Identificador} {lexeme=yytext(); return Identificador;}


/* Error de analisis */
 . {return ERROR;}