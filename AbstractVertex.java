// Time-stamp: <AbstractVertex.java  25 Oct 2002 18:39:47>
import java.util.*;
/**
 * Implementation generique des sommets
 * @author O. Carton
 * @version 1.0
 */
class AbstractVertex implements Vertex {
    /** Numero du sommet */
    protected int index;
    /** Retourne le numero du sommet */
    public int index() { return index; }
    /** Valeur du sommet */
    protected Object value;
    /** Retourne la valeur du sommet */
    public Object value() { return value; }
    /** Creation d'un sommet a partir d'un numero et d'une valeur */
    AbstractVertex(int index, Object value) {
	this.index = index;
	this.value = value;
    }
    /** Conversion en chaine */
    public String toString() {
	return Integer.toString(index);
    }
}
