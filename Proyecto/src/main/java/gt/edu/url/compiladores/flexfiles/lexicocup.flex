package gt.edu.url.compiladores.prueba1;
import java_cup.runtime.Symbol;
%%

%class LexicoCup
%line
%column
%cup
%eofval{
    return new Symbol(sym.EOF);
%eofval}
//%standalone
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
    DobleIgual = "=="
    Negar = "!="
    Op_incremento = ( "++" | "--" )
    Op_relacional = ({MayMen}| DobleIgual | Negar | ">=" | "<=" )
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
    BoolText = "verdadero" | "falso"
    BoolNum = "1.0"|"0.0"
    Op_booleano = (BoolText|BoolNum)
    Coma = ","
    GuionMe = "_"    

/*----------------------------------------Estructura IF-----------------------------------------*/

    Si = "si"
    Entonces = "entonces"
    Sino = "sino"
    FinSi = "finsi"

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


%%
/*--------------------------------------------Espacios en blanco----------------------------------*/
    {LineTerminator} {return new Symbol(sym.FINLINEA,yytext()); }
    {espacio} { }//{ return new Symbol(sym.FINLINEA,yytext());}
/*-----------------------------------------------Comentarios---------------------------------------*/
    {Comment} { } 
    {Comillas} { return new Symbol(sym.COMILLAS,yytext());}
    {DobleIgual} { return new Symbol(sym.DOBLEIGUAL,yytext());}
    {Negar} { return new Symbol(sym.NEGAR,yytext());}
    {Punto} { return new Symbol(sym.PUNTO,yytext());}
    {Sigual} { return new Symbol(sym.SIGUAL,yytext());}
    {Suma} { return new Symbol(sym.SUMA,yytext());}
    {Resta} { return new Symbol(sym.RESTA,yytext());}
    {Multiplicacion} { return new Symbol(sym.MULTIPLICACION,yytext());}
    {Division} { return new Symbol(sym.DIVISION,yytext());}
    {Mod} { return new Symbol(sym.MOD,yytext());}
    {Dos_puntos} { return new Symbol(sym.DOSPUNTOS,yytext());}
    {Op_relacional} { return new Symbol(sym.OPRELACIONAL,yytext());}
    {Op_atribucion} { return new Symbol(sym.OPATRIBUCION,yytext());}
    {Op_incremento} { return new Symbol(sym.OPINCREMENTO,yytext());}
    {Parentesis_a} { return new Symbol(sym.PARENTESISA,yytext());}
    {Parentesis_c} { return new Symbol(sym.PARENTESISC,yytext());}
    {Llave_a} { return new Symbol(sym.LLAVEA,yytext());}
    {Llave_c } { return new Symbol(sym.LLAVEC,yytext());}
    {Corchete_a} { return new Symbol(sym.CORCHETEA,yytext());}
    {Corchete_c} { return new Symbol(sym.CORCHETEC,yytext());}
    {BoolText} { return new Symbol(sym.BOOLTEXT,yytext());}
    {BoolNum} { return new Symbol(sym.BOOLNUM,yytext());}
    {Op_booleano} { return new Symbol(sym.OPBOLEANO,yytext());}
    {P_coma} { return new Symbol(sym.PUNTOYCOMA,yytext());}
    {Coma} { return new Symbol(sym.COMA,yytext());}
    {Exponente} { return new Symbol(sym.EXPONENTE,yytext());}
    {OPARBool} { return new Symbol(sym.OPARBOOL,yytext());}

/*---------------------------------------Palabras reservadas------------------------------------*/
    {T_dato} { return new Symbol(sym.REAL,yytext());}
    {Cadena} { return new Symbol(sym.CADENA,yytext());}
    {Nulo} { return new Symbol(sym.NULO,yytext());}
    {Entero} { return new Symbol(sym.ENTERO,yytext());}
    {Boleano} { return new Symbol(sym.BOLEANO,yytext());}
/*----------------------------------------Estructura IF-----------------------------------------*/
    {Si} { return new Symbol(sym.SI,yytext());}
    {Entonces} { return new Symbol(sym.ENTONCES,yytext());}
    {Sino} { return new Symbol(sym.SINO,yytext());}
    {FinSi} { return new Symbol(sym.FINSI,yytext());}

/*---------------------------------------Estructuras Iterativas---------------------------------*/
    {Hacer} { return new Symbol(sym.HACER,yytext());}
    {Mientras} { return new Symbol(sym.MIENTRAS,yytext());}
    {Para} { return new Symbol(sym.PARA,yytext());}
    {Devolver } { return new Symbol(sym.DEVOLVER,yytext());}
    {Desde} { return new Symbol(sym.DESDE,yytext());}
/*----------------------------------------Métodos de E/S-----------------------------------------*/
    {Leer} { return new Symbol(sym.LEER,yytext());}
    {Escribir} { return new Symbol(sym.ESCRIBIR,yytext());}
/*------------------------------------------------Main-------------------------------------------*/
    {Principal} { return new Symbol(sym.PRINCIPAL,yytext());}

/*----------------------------------Constructores y Destructores---------------------------------*/
    {Constructor} { return new Symbol(sym.CONSTRUCTOR,yytext());}
    {Destructor} { return new Symbol(sym.DESTRUCTOR,yytext());}
/*---------------------------------------Métodos y Propiedades----------------------------------*/
    {Metodo} { return new Symbol(sym.METODO,yytext());}
    {Propiedad} { return new Symbol(sym.PROPIEDAD,yytext());}
    {T_DisponibleM} { return new Symbol(sym.TDISPONIBLEM,yytext());}
    {T_DisponibleP} { return new Symbol(sym.TDISPONIBLEP,yytext());}
/*----------------------------------------------Clases-------------------------------------------*/
    {Clase} { return new Symbol(sym.CLASE,yytext());}
    {NameClass} { return new Symbol(sym.NAMECLASS,yytext());}
/*--------------------------------------------Instancias------------------------------------------*/
    {Instanciar} { return new Symbol(sym.INSTANCIAR,yytext());}
    {Eliminar} { return new Symbol(sym.ELIMINAR,yytext());}
/*-----------------------------------Funciones Especiales-----------------------------------------*/
    {CadenaAEntero} { return new Symbol(sym.CADENAaENTERO,yytext());}
    {FunESPReal} { return new Symbol(sym.FUNespREAL,yytext());}
    {CadenaABoleano} { return new Symbol(sym.CADaBOOL,yytext());}
/*-----------------------------------------Carga de Bibliotecas-----------------------------------*/
    {Incluir} { return new Symbol(sym.INCLUIR,yytext());}
    {BiExt} { return new Symbol(sym.EXTENCIONBIB,yytext());}
/*--------------------------------------------Extras----------------------------------------------*/
    {Estatico} { return new Symbol(sym.ESTATICO,yytext());}
/*-------------------------------------------Variables y Números----------------------------------*/
    {Numero} { return new Symbol(sym.NUMERO,Integer.parseInt(yytext()));}
    {Decimal} { return new Symbol(sym.DECIMAL,Float.parseFloat(yytext()));}
    {Identificador} {System.out.println("Identificador"+yytext());return new Symbol(sym.IDENTIFICADOR,yytext());}
/*-------------------------------------------Cadenas de Texto-------------------------------------*/
    {Texto} { return new Symbol(sym.CADTEXTO,yytext());}
/*----------------------------------------------Errores-------------------------------------------*/    
    {ErrorCer} { return new Symbol(sym.ERRORCERO,yytext());}
    {ErrorNum} { return new Symbol(sym.ERRORNUM,yytext());}
    {ErrorDec} { return new Symbol(sym.ERRORDECIM,yytext());}
    {ErrorCom} { return new Symbol(sym.ERRORCOM,yytext());}
    {ErrorOp_IN} { return new Symbol(sym.ERROROPINC,yytext());}
    {ErrorOp_Rel} { return new Symbol(sym.ERROREAL,yytext());}
    {ErrorOp_Atr} { return new Symbol(sym.ERRORARIT,yytext());}
    {ErrorSigP} { return new Symbol(sym.ERRORSIGP,yytext());}
    {ErrorOPARBo} { return new Symbol(sym.ERROROPARBO,yytext());}
    {ErrorSig} { return new Symbol(sym.ERRORSIG,yytext());}
    {ErrorID} { return new Symbol(sym.ERRORID,yytext());}
    {ErrorNameClss} { return new Symbol(sym.ERRORNAMECLASS,yytext());}
    {ErrorSIGNOS} { return new Symbol(sym.ERRORSIGNOS,yytext());}
    {ErrorSIGNOS1} { return new Symbol(sym.ERRORSIGNOS1,yytext());}
    {ErrorSIGNOS2} { return new Symbol(sym.ERRORSIGNOS2,yytext());}
/* Error de analisis */
.                   { System.out.println("Error"+yytext());return new Symbol(sym.error);}