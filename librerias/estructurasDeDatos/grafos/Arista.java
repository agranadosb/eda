package librerias.estructurasDeDatos.grafos;

/** Clase Arista: representa una arista de un grafo.<br> 
 *  
 *  @version Mayo 2018
 */
 
public class Arista implements Comparable<Arista> {
    
    // UNA Arista TIENE UN vertice origen y UN vertice destino:
    int v_src;
    int v_dst;
    // UNA Arista TIENE UN peso:
    double weigth;
    
    /** Crea una arista (v, w) con peso p.
      *
      * @param v  Vertice origen
      * @param w  Vertice destino
      * @param p  Peso
     */
    public Arista(int v, int w, double p) {
        v_src = v;
        v_dst = w;
        weigth = p;
    }

    /** Devuelve el vertice origen de una arista.
      *
      * @return int vertice origen
     */
    public int getOrigen() {    
        return v_src;
    }
    
    /** Devuelve el vertice destino de una arista.
      *
      * @return int vertice destino
     */
    public int getDestino() {  
        return v_dst;
    }
    
    /** Devuelve el peso de una arista.
      *
      * @return double Peso de la arista
     */
    public double getPeso() {
        return weigth;  
    }
    
    /** Devuelve un String que representa una arista
      * en formato (origen, destino, peso)
      *
      * @return  String que representa la arista
     */
    public String toString() {
        return "(" + v_src + ", " + v_dst + ", " + weigth + ")"; 
    }
    
    public int compareTo(Arista other) {
        if (this.weigth == other.weigth) { return 0; }
        else if (this.weigth < other.weigth) { return -1; }
        else { return 1; }
    }
}