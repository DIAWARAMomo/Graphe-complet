
import java.util.*;
/**
 * Interface des sommets d'un graphe

 */
interface Vertex {
    /**
     * Retourne le numero du sommet.
     * Chaque sommet d'un graphe a un numero qui est un entier entre 
     * 0 et le nombre de sommets du graphe moins un.
     * Cet numero est en particulier utilise par les algorithme qui
     * associe des informations aux sommets (comme la couleur dans
     * les parcours de graphe).  Ces informations peuvent etre stockees
     * dans un tableau 
     */
    int index();
    /**
     * Retourne la valeur du sommet.
     */
    Object value();
}
