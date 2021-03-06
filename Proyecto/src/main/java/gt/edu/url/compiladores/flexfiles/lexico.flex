package gt.edu.url.compiladores.prueba1;
import static gt.edu.url.compiladores.prueba1.Token.*;
%%
%class Lexico
//%standalone
%type Token
/*----------------------------------COMENTARIOS----------------------------------------------*/
    LineTerminator = \r|\n|\r\n
    InputCharacter = [^\r\n]
    Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
    TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
    DocumentationComment = "/**" {CommentContent} "*"+ "/"
    CommentContent       = ( [^*] | \*+ [^/*] )*
/*-------------------------------------Asignación de valores ---------------------------------*/
    L = [a-z][a-zA-Z_]*
    D = [0-9]+
    D1 = [1-9]+
    D0 = "0"
    l = [A-Z][A-Za-z_]*
    espacio=[ \t\r\n]+
/*------------------------------------------Simbolos-------------------------------------------*/
    Sigual = "="
    Neg = "!"
    MayMen = (">" | "<" )
    Comillas = "\""
    Op_incremento = ( "++" | "--" )
    Op_relacional = ({MayMen}| "==" | "!=" | ">=" | "<=" )
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
    Op_booleano = ("verdadero" | "falso"|"1.0"|"0.0")
    Coma = ","
    GuionMe = "_"    

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
    BibLectura = "lectura.loop"
    BibPrint = "impresion.loop"
    Bibliotecas = {BibLectura}|{BibPrint}
    ruta = "../"
    Incluir = "incluir"
    BiExt = {Comillas}{ruta}*{Bibliotecas}{Comillas}

/*--------------------------------------------Extras----------------------------------------------*/
    Estatico = "estatico"

/*-------------------------------------------Variables y Números----------------------------------*/
    Identificador = {L}({L}|{D})*
    Numero = {Resta}{D1}{D}*|{D1}{D}*|{D0}//|{D0}{D1}
    Decimal = ({D1}{1}{D}*|{D0}){Punto}({D}*{D1}{D0}{0,1}|{D0}{2}|{D}*{D1}{D0}{2})
/*-------------------------------------------Cadenas de Texto-------------------------------------*/
    Texto = {Comillas} [^*] ~{Comillas} | {Comillas}{Comillas}
/*----------------------------------------------Errores-------------------------------------------*/
    ErrorCer = {D0}{D0}+|{D0}{D0}+{D1}|{D0}{D1}
    ErrorNum = ({Numero}|{ErrorCer}){Identificador}+
    ErrorDec = ({D0}|{ErrorCer}|{Numero})({Coma}|{ErrorPun}|{Punto})({Numero}|{D0}{3}{D0}*|{D0})
    ErrorCom = {Comillas}{2}{Comillas}+
    SimbGlob = ({ErrorSignos1}|{ErrorSig}|{Errorand}|{Erroror}|{ErrorSigBas}|{Division}|{GuionMe}|{Sigual}|{Neg}|{MayMen})
    ErrorOp_IN = {Op_incremento}({Op_incremento}/*|{Suma}|{Resta}*/|{AgrupSimGlob})+
    ErrorOp_Rel = {Op_relacional}({Op_relacional}|{AgrupSimGlob})+
    ErrorOp_Atr = {Op_atribucion}({Op_atribucion}|{AgrupSimGlob})+
    ErrorPun = {Punto}{Punto}+
    ErrorSigBas = ({Exponente}|{Mod}|{Suma}|{Resta}|{Multiplicacion})+
    ErrorSignos = ({Coma}|{Punto}|{Llave_a}|{Llave_c}|{Corchete_a}|{Corchete_c}|{P_coma}|{Dos_puntos}|{Parentesis_a}|{Parentesis_c})+
    ErrorSignos1 = ({Coma}|{Llave_a}|{Llave_c}|{Corchete_a}|{Corchete_c}|{P_coma}|{Dos_puntos}/*|{Parentesis_a}|{Parentesis_c}*/)+
    ErrorSignos2 = ({Coma}|{Punto}|{Llave_a}|{Llave_c}|{P_coma}|{Dos_puntos})+
    AgrupSimGlob = ({SimbGlob}|{GuionMe}|{Punto}|{Parentesis_a}|{Parentesis_c})
    ErrorSIGNOS = ({ErrorSignos2}|{Corchete_c}|{Parentesis_c})+{ErrorSignos}+/*{1}*/{AgrupSimGlob}*
    ErrorSIGNOS1 = {Parentesis_a}({ErrorSignos2}|{Corchete_a}|{Parentesis_a})+{AgrupSimGlob}*//{ErrorSignos}
    ErrorSIGNOS2 = {Corchete_a}({Corchete_a}|{ErrorSignos2}|{Parentesis_a}|{Parentesis_c})+{AgrupSimGlob}*//{ErrorSignos}* 
    Errorand = "&"
    Erroror = "|"
    ErrorOPARBo = {Errorand}{2}({Errorand}|{Erroror})+{AgrupSimGlob}*|{Erroror}{2}({Errorand}|{Erroror})+{AgrupSimGlob}*|{Erroror}{AgrupSimGlob}*|{Errorand}{AgrupSimGlob}*
    ErrorArr = "@"
    ErrorHash = "#"
    ErrorDoll = "$"
    ErrorSig = ({ErrorArr}|{ErrorHash}|{ErrorDoll})+([^*])*
    ErrorSigP = (({Division}{1}{ErrorSigBas}+)+|(({ErrorSigBas}+{Division}{1})+|{ErrorSigBas}+)+){AgrupSimGlob}*   
    //ErrorID = ({Identificador}{SimbGlob}+|({SimbGlob}|{GuionMe})+{Identificador}+){AgrupSimGlob}*
    //ErrorNameClss = ({NameClass}{SimbGlob}+|({SimbGlob}|{GuionMe})+{NameClass}+){AgrupSimGlob}*
    
    ErrorID = ({Identificador}({ErrorSig}|{Llave_a}|{Llave_c})+|({ErrorSig}|{Llave_a}|{Llave_c}|{GuionMe})+{Identificador}+){AgrupSimGlob}*
    ErrorNameClss = ({NameClass}({SimbGlob}|{Punto}|{ErrorSig})+|({SimbGlob}|{Punto}|{ErrorSig}|{GuionMe})+{NameClass}+){ErrorSig}*
    
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
    {BiExt} {lexeme=yytext(); return BiExt;}

/*--------------------------------------------Extras----------------------------------------------*/
    {Estatico} {lexeme=yytext(); return Estatico;}

/*-------------------------------------------Variables y Números----------------------------------*/
    {Numero} {lexeme=yytext(); return Numero;}
    {Decimal} {lexeme=yytext(); return Decimal;}
    {Identificador} {lexeme=yytext(); return Identificador;}

/*-------------------------------------------Cadenas de Texto-------------------------------------*/

    {Texto} {lexeme=yytext(); return Texto;}

/*----------------------------------------------Errores-------------------------------------------*/    
    {ErrorCer} {lexeme=yytext(); return ErrorCer;}
    {ErrorNum} {lexeme=yytext(); return ErrorNum;}
    {ErrorDec} {lexeme=yytext(); return ErrorDec;}
    {ErrorCom} {lexeme=yytext(); return ErrorCom;}
    {ErrorOp_IN} {lexeme=yytext(); return ErrorOp_IN;}
    {ErrorOp_Rel} {lexeme=yytext(); return ErrorOp_Rel;}
    {ErrorOp_Atr} {lexeme=yytext(); return ErrorOp_Atr;}
    {ErrorSigP} {lexeme=yytext(); return ErrorSigP;}
    {ErrorOPARBo} {lexeme=yytext(); return ErrorOPARBo;}
    {ErrorSig} {lexeme=yytext(); return ErrorSig;}
    {ErrorID} {lexeme=yytext(); return ErrorID;}
    {ErrorNameClss} {lexeme=yytext(); return ErrorNameClss;}
    {ErrorSIGNOS} {lexeme=yytext(); return ErrorSIGNOS;}
    {ErrorSIGNOS1} {lexeme=yytext(); return ErrorSIGNOS1;}
    {ErrorSIGNOS2} {lexeme=yytext(); return ErrorSIGNOS2;}
/* Error de analisis */
 . {return ERROR;}