package gt.edu.url.compiladores.prueba1;
import static gt.edu.url.compiladores.prueba1.Token.*;
%%
%class Lexico
//%standalone
%type Token
/*----------------------------------COMENTARIOS----------------------------------------------*/
    LineTerminator = \r|\n|\r\n
    InputCharacter = [^\r\n]
    //WhiteSpace     = {LineTerminator} | [ \t\f]

    /* comments */
    Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

    TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    // Comment can be the last line of the file, without line terminator.
    EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
    DocumentationComment = "/**" {CommentContent} "*"+ "/"
    CommentContent       = ( [^*] | \*+ [^/*] )*

    //Identifier = [:jletter:] [:jletterdigit:]*

    //DecIntegerLiteral = 0 | [1-9][0-9]*

    Texto = {Comillas} [^*] ~{Comillas} | {Comillas}{Comillas}

/*-------------------------------------Asignación de valores ---------------------------------*/
    L = [a-z][a-zA-Z_]*
    //L = [a-zA-Z_]+
    D = [0-9]+
    l = [A-Z][A-Za-z_]*
    espacio=[ \t\r\n]+
    //SaltoL = "\n"
    //signos = [33-47]+

/*------------------------------------------Simbolos-------------------------------------------*/
    Sigual= "="
    Comillas = "\""
    //veamos = "veamos"
    Op_incremento = ( "++" | "--" )
    Op_relacional = ( ">" | "<" | "==" | "!=" | ">=" | "<=" )
    Op_atribucion = ( "+=" | "-="  | "*=" | "/=" | "%=")
    Parentesis_a = "("
    Parentesis_c = ")"
    Punto = "."
    Llave_a = "{"
    Llave_c = "}"
    Corchete_a = "["
    Corchete_c = "]"
    P_coma = ";"
    Dos_puntos = ":"
    Suma = "+"
    Resta = "-"
    Multiplicacion = "*"
    Division = "/"
    Mod = "%"
    AND = ("AND"|"&&")
    OR = ("OR"|"||")
    OPARBool = {AND}|{OR}
    Exponente = "^"
    Op_booleano = ("verdadero" | "falso")
    Coma = ","

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
    Numero = {Resta}{D}+|{D}+

/*----------------------------------------------Errores-------------------------------------------*/

    ErrorNum = {Numero}{Identificador}
    ErrorCom = {Comillas}{Comillas}+
    ErrorComa = {Coma}{Coma}+
    ErrorOp_IN = {Op_incremento}({Op_incremento}|{Suma}|{Resta})+
    ErrorOp_Rel = {Op_relacional}{Op_relacional}+
    ErrorOp_Atr = {Op_atribucion}{Op_atribucion}+
    ErrorPar_a = {Parentesis_a}{Parentesis_a}+
    ErrorPar_c = {Parentesis_c}{Parentesis_c}+
    ErrorPun = {Punto}{Punto}+
    ErrorLL_a = {Llave_a}{Llave_a}+
    ErrorLL_c = {Llave_c}{Llave_c}+
    ErrorCo_a = {Corchete_a}{Corchete_a}+
    ErrorCo_c = {Corchete_c}{Corchete_c}+
    ErrorP_C = {P_coma}{P_coma}+
    ErrorD_pu = {Dos_puntos}{Dos_puntos}+
    ErrorMul = {Multiplicacion}{Multiplicacion}+
    ErrorMod = {Mod}{Mod}+
    Errorand = "&"
    Erroror = "|"
    ErrorOPARBo = {OPARBool}({OPARBool}|{Errorand}|{Erroror})+
    ErrorExp = {Exponente}{Exponente}+


//Variables_enteras = {Entero}{Identificador}({Sigual}{Numero}{SaltoL}|{SaltoL})
//Variables_reales = {Real}{Identificador}
%{
    public String lexeme;
%}
%%
/*--------------------------------------------Espacios en blanco----------------------------------*/
    {espacio} {/*Ignore*/}

/*-----------------------------------------------Comentarios---------------------------------------*/
    {Comment} {/*Ignore*/} 
/*----------------------------------------------Simbolos-------------------------------------------*/
    {Comillas} {lexeme=yytext(); return Comillas;}
    {Punto} {lexeme=yytext(); return Punto;}
    {Sigual} {lexeme=yytext(); return Igual;}
    {Suma} {lexeme=yytext(); return Suma;}
    {Resta} {lexeme=yytext(); return Resta;}
    {Multiplicacion} {lexeme=yytext(); return Multiplicacion;}
    {Division} {lexeme=yytext(); return Division;}
    {Mod} {lexeme=yytext(); return Mod;}
    {Dos_puntos} {lexeme=yytext(); return Dos_puntos;}
    {Op_relacional} {lexeme = yytext(); return Op_relacional;}
    {Op_atribucion} {lexeme = yytext(); return Op_atribucion;}
    {Op_incremento} {lexeme = yytext(); return Op_incremento;}
    {Parentesis_a} {lexeme=yytext(); return Parentesis_a;}
    {Parentesis_c} {lexeme=yytext(); return Parentesis_c;}
    {Llave_a} {lexeme=yytext(); return Llave_a;}
    {Llave_c } {lexeme=yytext(); return Llave_c;}
    {Corchete_a} {lexeme = yytext(); return Corchete_a;}
    {Corchete_c} {lexeme = yytext(); return Corchete_c;}
    {Op_booleano} {lexeme = yytext(); return Op_booleano;}
    {P_coma} {lexeme=yytext(); return P_coma;}
    {Coma} {lexeme=yytext(); return Coma;}
    {Exponente} {lexeme=yytext(); return Exponente;}
    {OPARBool} {lexeme=yytext(); return OPARBool;}
//{Op_logico} {lexeme=yytext(); return Op_logico;}

/*---------------------------------------Palabras reservadas------------------------------------*/
    {T_dato} {lexeme=yytext(); return T_dato;}
    {Cadena} {lexeme=yytext(); return Cadena;}
    {Nulo} {lexeme=yytext(); return Nulo;}
    {Entero} {lexeme=yytext(); return Entero;}
    {Boleano} {lexeme=yytext(); return Boleano;}

/*----------------------------------------Estructura IF-----------------------------------------*/
    {Si} {lexeme=yytext(); return Si;}
    {Entonces} {lexeme=yytext(); return Entonces;}
    {Sino} {lexeme=yytext(); return Sino;}

/*---------------------------------------Estructuras Iterativas---------------------------------*/
    {Hacer} {lexeme=yytext(); return Hacer;}
    {Mientras} {lexeme=yytext(); return Mientras;}
    {Para} {lexeme=yytext(); return Para;}
    {Devolver } {lexeme=yytext(); return Devolver;}
    {Desde} {lexeme=yytext(); return Desde;}


/*----------------------------------------Métodos de E/S-----------------------------------------*/
    {Leer} {lexeme=yytext(); return Leer;}
    {Escribir} {lexeme=yytext(); return Escribir;}

/*------------------------------------------------Main-------------------------------------------*/
    {Principal} {lexeme=yytext(); return Principal;}

/*----------------------------------Constructores y Destructores---------------------------------*/
    {Constructor} {lexeme=yytext(); return Constructor;}
    {Destructor} {lexeme=yytext(); return Destructor;}

/*---------------------------------------Métodos y Propiedades----------------------------------*/
    {Metodo} {lexeme=yytext(); return Metodo;}
    {Propiedad} {lexeme=yytext(); return Propiedad;}
    {T_DisponibleM} {lexeme=yytext(); return T_DisponibleM;}
    {T_DisponibleP} {lexeme=yytext(); return T_DisponibleP;}

/*----------------------------------------------Clases-------------------------------------------*/
    {Clase} {lexeme=yytext(); return Clase;}
    {NameClass} {lexeme=yytext(); return NameClass;}

/*--------------------------------------------Instancias------------------------------------------*/
    {Instanciar} {lexeme=yytext(); return Instanciar;}
    {Eliminar} {lexeme=yytext(); return Eliminar;}

/*-----------------------------------Funciones Especiales-----------------------------------------*/
    {CadenaAEntero} {lexeme=yytext(); return CadenaAEntero;}
    {FunESPReal} {lexeme=yytext(); return FunESPReal;}
    {CadenaABoleano} {lexeme=yytext(); return CadenaABoleano;}

/*-----------------------------------------Carga de Bibliotecas-----------------------------------*/
    {Incluir} {lexeme=yytext(); return Incluir;}

/*--------------------------------------------Extras----------------------------------------------*/
    {Estatico} {lexeme=yytext(); return Estatico;}

/*-------------------------------------------Variables y Números----------------------------------*/
    {Numero} {lexeme=yytext(); return Numero;}
    {Identificador} {lexeme=yytext(); return Identificador;}

/*-------------------------------------------Cadenas de Texto----------------------------------*/

    {Texto} {lexeme=yytext(); return Texto;}

/*----------------------------------------------Errores-------------------------------------------*/    
    {ErrorNum} {lexeme=yytext(); return ErrorNum;}
    {ErrorCom} {lexeme=yytext(); return ErrorCom;}
    {ErrorComa} {lexeme=yytext(); return ErrorComa;}
    {ErrorOp_IN} {lexeme=yytext(); return ErrorOp_IN;}
    {ErrorOp_Rel} {lexeme=yytext(); return ErrorOp_Rel;}
    {ErrorOp_Atr} {lexeme=yytext(); return ErrorOp_Atr;}
    {ErrorPar_a} {lexeme=yytext(); return ErrorPar_a;}
    {ErrorPar_c} {lexeme=yytext(); return ErrorPar_c;}
    {ErrorPun} {lexeme=yytext(); return ErrorPun;}
    {ErrorLL_a} {lexeme=yytext(); return ErrorLL_a;}
    {ErrorLL_c} {lexeme=yytext(); return ErrorLL_c;}
    {ErrorCo_a} {lexeme=yytext(); return ErrorCo_a;}
    {ErrorCo_c} {lexeme=yytext(); return ErrorCo_c;}
    {ErrorP_C} {lexeme=yytext(); return ErrorP_C;}
    {ErrorD_pu} {lexeme=yytext(); return ErrorD_pu;}
    {ErrorMul} {lexeme=yytext(); return ErrorMul;}
    {ErrorMod} {lexeme=yytext(); return ErrorMod;}
    {ErrorOPARBo} {lexeme=yytext(); return ErrorOPARBo;}
    {ErrorExp} {lexeme=yytext(); return ErrorExp;}

/* Error de analisis */
 . {return ERROR;}