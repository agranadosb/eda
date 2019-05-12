package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.ArrayCola;

/**
 * Prueba basica del algoritmo arbolRecubrimientoBFS
 * de GrafoNoDirigido y de la clase Arista
 * 
 * @version Diciembre 2018
 */

public class TestGrafo {
    
    // Numero de vertices de los grafos ejemplo
    private static final int NUMV = 7;  
    
    // Test de la sesion 1
    public static void main(String[] args) {
        if (!validar("Validando la clase Arista", 
                     testArista())) { return; }
        if (!validar("Validando Spanning Tree para un Grafo NO Conexo", 
                     testSTNoConexo())) { return; }
        if (!validar("Validando Spanning Tree para un Grafo Conexo", 
                     testSTConexo())) { return; }
    }
    
    public static boolean validar(String test, String res) {
        System.out.print(test + "... ");
        if (res == null) {
            System.out.println("OK!\n");
            return true;
        } else {
            System.out.println("ERROR");
            System.out.println("* " + res);
            return false;
        }
    }
    
    // Test de la clase Arista
    public static String testArista() {
        
        // Comprobacion del metodo constructor, pues los atributos son protected
        Arista prueba = new Arista(0, 2, 12.50);
        if (prueba.getOrigen()!= 0 || prueba.getDestino() != 2 || prueba.getPeso() != 12.50) {
            return "PORQUE el metodo constructor de tu clase Arista NO es "
                   + "correcto...\n  Comprueba que la declaracion de los "
                   + "atributos de la clase y\n  su incializacion en el "
                   + "constructor sean las especificadas!!";
        }
        
        // Comprobacion del metodo toString
        String sPrueba = prueba.toString();
        String sPruebaOK = "(0, 2, 12.5)";
        if (!sPrueba.equals(sPruebaOK)) {
            return "PORQUE el metodo toString() de tu clase Arista NO es "
                   + "correcto...\n  Comprueba primero que sobrescribe al "
                   + "de Object y, luego, que el\n  "  
                   + "formato del String resultante es el especificado!!";
        }    
    
        // Se comprueban ahora el resto de metodos de la clase Arista
        double sumaPesos = 0.0;
        Cola<Arista> c = new ArrayCola<Arista>();
        Arista[] datos = {new Arista(0, 2, 12.50), new Arista(1, 3, 6.50), 
                          new Arista(2, 3, 4.30), new Arista(0, 3, 14.30), 
                          new Arista(0, 1, 6.20)};     
        for (int i = 0; i < datos.length; i++) { c.encolar(datos[i]); }
        //System.out.println(c);
        int[] orden = {0, 1, 2, 3, 4};
        for (int i = 0; i < datos.length; i++) {
            Arista a = c.desencolar();
            //System.out.println("Arista " + i + ": " + a);
            sumaPesos += a.getPeso();
            Arista s = datos[orden[i]];
            //System.out.println(" VS Arista solucion " + i + ": " + s);
            if (a.getOrigen() == a.getDestino() 
                || s.getOrigen() == s.getDestino()) {
                return "PORQUE los vertices origen y destino de una arista "
                       + "NO pueden coincidir...\n  "
                       + "Comprueba los metodos getOrigen y getDestino de " 
                       + "tu clase Arista!!";
            }
            if (a.getOrigen() != s.getOrigen() 
                || a.getDestino() != s.getDestino()
                || a.getPeso() != s.getPeso()) {
                return "PORQUE las componentes de tus aristas no "
                       + "son correctas...\n  Comprueba los metodos "  
                       + "consultores de tu clase Arista!!";
            }
        }
        if (Math.abs(sumaPesos - 43.80) >= 1e-8) {
            //System.out.println("Suma de los pesos: " + sumaPesos);
            return "PORQUE los pesos de las aristas no son correctos...\n  "
                   + "Comprueba los metodos consultores de tu clase Arista!!";
        }
        return null;
    }
    
    // Test 1 del metodo arbolRecubrimientoBFS de la clase Grafo
    public static String testSTNoConexo() {
        GrafoNoDirigido g = new GrafoNoDirigido(NUMV);
        g.insertarArista(2, 3, 4);
        g.insertarArista(4, 5, 4); 
        g.insertarArista(0, 1, 6);
        g.insertarArista(1, 3, 6);
        g.insertarArista(0, 2, 12);
        g.insertarArista(0, 3, 14);
        g.insertarArista(5, 6, 15);
        g.insertarArista(4, 6, 20);
        if (g.arbolRecubrimientoBFS() == null) { return null; }
        return "Tu metodo NO devuelve null para un Grafo como, por ejemplo,"
               + "\n" + g.toString() + "\n";
    }
    
    // Test 2 del metodo arbolRecubrimientoBFS de la clase Grafo 
    private static String testSTConexo() {
        
        // construimos el grafo ejemplo del boletin
        GrafoNoDirigido g = new GrafoNoDirigido(NUMV);
        g.insertarArista(2, 3, 4);
        g.insertarArista(4, 5, 4); 
        g.insertarArista(0, 1, 6);
        g.insertarArista(1, 3, 6);
        
        g.insertarArista(3, 4, 9); // Arista para que sea Conexo
        
        g.insertarArista(0, 2, 12);
        
        g.insertarArista(2, 4, 12); // Arista para que sea Conexo
        g.insertarArista(3, 5, 12); // Arista para que sea Conexo
        
        g.insertarArista(0, 3, 14);
        g.insertarArista(5, 6, 15);
        
        g.insertarArista(1, 5, 20); // Arista para que sea Conexo
        
        g.insertarArista(4, 6, 20);
        
        String traza = "Tu metodo NO obtiene el ST correcto para un Grafo " 
                       + "como, por ejemplo,\n" + g.toString() + "\n";
        
        // Definimos el conjunto de aristas del Spannig Tree solucion
        
        Arista[] sol = {new Arista(0, 1, 6), new Arista(0, 2, 12),
                        new Arista(0, 3, 14), new Arista(1, 5, 20),
                        new Arista(2, 4, 12), new Arista(5, 6, 15)};

        // Ejecutamos el metodo arbolRecubrimientoBFS() sobre g
        Arista[] sT = g.arbolRecubrimientoBFS();
        
        // Comprobamos la solucion del alumno
        boolean okNumA = (sT != null && sT.length == NUMV - 1);
        if (okNumA) {
            String comparativa = " Arista\t   de tu ST\t   del ST correcto\n";
            // Comprobamos que las aristas de los ST del alumno y el correcto
            // son iguales una a una y en el mismo orden
            int numAristasOK = 0;
            for (int i = 0; i < sT.length; i++) {
                Arista a = sT[i], s = sol[i];
                if (a.getOrigen() == s.getOrigen() 
                    && a.getDestino() == s.getDestino() 
                    && a.getPeso() == s.getPeso()) {
                    numAristasOK++;
                }  
                comparativa += "   " + i + "\t   " + a.toString() 
                               + "\t   " + s.toString() + "\n";
            }
            if (numAristasOK == NUMV - 1) {
                //traza += "\tEl coste del MST es: " + coste 
                //+ "\n\tEl MST es:\n" + mst.toString();
                return null;
            } 
            else {
                return traza 
                       + "PORQUE algunas de sus Aristas NO son correctas:\n"
                       + comparativa + "\n";
            }
        } 
        else { 
            if (sT == null) {
                return "PORQUE devuelves un conjunto de aristas NULL!!"; 
            }
            else {
                return "PORQUE devuelves un conjunto de " + sT.length 
                       + " aristas(!!), " 
                       + "y debiera tener |V| - 1 = " + (NUMV - 1); 
            }
        }
    }
}