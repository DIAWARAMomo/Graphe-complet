
import java.util.*;
/*
 * Implementation d'un sommet pour les graphes par matrice d'adjacence

 */
class MatrixVertex extends AbstractVertex {
    /* Creation d'un sommet a partir d'un numero et d'une valeur */
    MatrixVertex(int index, Object value) {
	super(index, value);
    }
    /* Creation d'un sommet a partir d'un numero */
    MatrixVertex(int index) {
	this(index, new Integer(index));
    }
}
