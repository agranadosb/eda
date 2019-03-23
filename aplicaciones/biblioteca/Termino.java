package aplicaciones.biblioteca;

/**
 *  Un Termino tiene un dato que es un String y su valor de hashing asociado que viene dado por la
 *  función de dispersión que se seleccione al construir el objeto. La implementacion permite utilizar
 *  diferentes funciones de dispersion (generan un valor numerico a partir del String. 
 *  Se consideraran cuatro funciones de dispersion:
 *  (a) Simple: solo suma los códigos de los caracteres del String, sin pesos 
 *  (b) Weiss: propuesta por Weiss, utiliza como base 37
 *  (c) Mckenzie: la propuesta McKenzie usa 4
 *  (d) String: la estandar de Java que usa 31
 *
 * @author (EDA)
 * @version (Curso 2017-2018)
 */
public class Termino {
    public static final int SIMPLE =   0;  
    public static final int WEISS =    1;    
    public static final int MCKENZIE = 2;  
    public static final int STRING =   3;  
    public static final String[] NOMFDIS = {"Simple", "Weiss", "McKenzie", "String"};
    
    protected String termino;
    protected int valorHash;
    protected int fHash;
    
    /** Constructor por defecto: utiliza el hashCode del estandar de java */
    public Termino(String s) { this(s, STRING); }
    
    /** Construye un termino a partir del String s y usando la funcion de hashing fhash */
    public Termino(String s, int fhash) {
        termino = s;
        fHash = fhash;
        valorHash = obtieneValorHash(fhash);
    }
    
    public void setString(String nuevo) { 
        termino = nuevo;
        valorHash = obtieneValorHash(fHash);
    }
    // Hashing Simple: solo suma los codigos de termino(
    private int hashSimple() {
        if (termino == null || termino.length() == 0) return 0;

        int res = 0;
        for (int i = 0; i < termino.length() - 1; i++) {
            res += (int) termino.charAt(i);
        }
		
        return res + (int) termino.charAt(termino.length() - 1);
    }
    //((((x0*37 + x1)*37 + x2)*37 + x3)*37 + x4)*37 + x5
    // Hashing Weiss: propuesta en capítulo 19 apartado 2
    // Usa como base la constante 37
    private int hashWeiss() {
        if (termino == null || termino.length() == 0) return 0;

        int res = 0;
        for (int i = 0; i < termino.length() - 1; i++) {
            res = (res + (int) termino.charAt(i)) * 37;
        }
		
        return res + (int) termino.charAt(termino.length() - 1);
    }
    
    // Hashing McKenzie: substituir 37 por 4 en el método Weiss
    private int hashMcKenzie() { 
        if (termino == null || termino.length() == 0) return 0;

        int res = 0;
        for (int i = 0; i < termino.length() - 1; i++) {
            res = (res + (int) termino.charAt(i)) * 4;
        }
		
        return res + (int) termino.charAt(termino.length() - 1);
    }
    
    // Hashing String: usa el hashCode de la clase estandar String
    private int hashString() { return termino.hashCode(); }
    
    // Calcula el valor de hash de acuerdo a la funcion de dispersion fHash
    protected int obtieneValorHash(int fHash) {
        int res = 0;
        switch (fHash) {
            case SIMPLE:
                res = hashSimple();   break;                   
            case WEISS:
                res = hashWeiss();    break;                         
            case MCKENZIE: 
                res = hashMcKenzie(); break;                        
            case STRING:
                res = hashString();   break;
            default:
                res = hashString();   break;
        }
        return res;   
    }
    
    /** Comprueba la igualdad de this Termino con o 
     *  @param Object, objeto de tipo Termino a comparar con this
     *  @return boolean, true si son iguales, false en caso contrario
     */
    public boolean equals(Object o) {
        return o instanceof Termino && ((Termino) o).termino.equals(termino) && ((Termino) o).valorHash == valorHash && ((Termino) o).fHash == fHash;
		
    }
    
    /** Obtiene el hashCode asociado al termino 
     *  @param Object, objeto de tipo Termino a comparar con this
     *  @return int, entero asociado al termino
     */
    public int hashCode() {
        int res;
	    switch (fHash) {
            case SIMPLE: res = super.hashCode(); break;
            case WEISS: res = hashWeiss(); break;
            case MCKENZIE: res = hashMcKenzie(); break;
            default: res = hashString(); break;
        }
        return res;
	}
    
    /** Devuelve un String que contiene informacion sobre el termino
     *  @return String, termino (valorHash)
     */
    public String toString() { return termino + " (" + valorHash + ")\n"; }
}
