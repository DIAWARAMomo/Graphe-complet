

import java.util.Iterator;
import java.util.*;
/**
 * Interface des graphes

 */
interface Graph {
    // Retourne le nombre de sommet du graphe 
    int verticesNumber();
    // Retourne un iterateur sur les sommets 
    Iterator vertices();
    // Ajoute un nouveau sommet avec une valeur donnee 
    Vertex put(Object value);
    // Retoune la valeur un sommet 
    Object get(Vertex v);
    //Ajoute une arete entre les deux sommets donnes 
    void putEdge(Vertex s, Vertex b);
    // Retourne s'il y a une arete entre deux sommets 
    boolean getEdge(Vertex s, Vertex b);
    // Retourne un iterateur sur les sommets adjacents au sommet donne 
    Iterator nextVertices(Vertex v);
    // Parcours en largeur du graphe a partir du sommet donne
    Iterator breathFirstIterator(Vertex v);
    // Parcours en profondeur du graphe a partir du sommet donne 
    // Iterator depthFirstIterator(Vertex v);
    // Teste si le graphe a un cycle 
    boolean cyclic();
    // Tri topologique d'un graphe acyclique 
    Vertex [] topologicalSort();
    // Transposition du graphe (retournement des aretes) 
    void transpose();
    // Calcul des composante fortement connexes
    Vertex [] stronglyConnectedComponents();

}
