
import java.util.LinkedList;
import java.util.*;
/*
 * Implementation d'un sommet pour les graphes par listes d'adjacence

 */
class ListVertex extends AbstractVertex {
    /* Liste des sommets adjacents */
    LinkedList next;
    /* Creation d'un sommet a partir d'un numero et d'une valeur */
    ListVertex(int index, Object value) {
	super(index, value);
	next = new LinkedList();
    }
}
