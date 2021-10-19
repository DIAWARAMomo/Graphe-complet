
import java.util.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/*
 * Implementation des graphes par listes d'adjacence 

 */
public class ListGraph extends AbstractGraph {
    /* Liste des sommets */
    private List verticesList;
    /* Creation d'un graphe vide */
    public ListGraph() {
	// super();
	verticesList = new LinkedList();
    }
   /* Creation d'un graphe identique a un graphe donne */
    public ListGraph(Graph g) {
	// Creation du graphe vide
	this();
	// Tableau pour stocker la correspondance entre les sommets
	// du graphe g et celui de this.
	Vertex[] corres = new Vertex[g.verticesNumber()];
	// Creation des sommets
	for (Iterator i = g.vertices(); i.hasNext();) {
	    // Sommet de g
	    Vertex u = (Vertex) i.next();
	    // Sommet de this correspondant
	    corres[u.index()] = put(u.value());
	}
	// Mise en place des aretes
	for (Iterator i = g.vertices(); i.hasNext();) {
	    Vertex u = (Vertex) i.next();
	    for (Iterator j = g.nextVertices(u); j.hasNext();) {
		Vertex v = (Vertex) j.next();
		putEdge(corres[u.index()], corres[v.index()]);
	    }
	}
    }
    /*Retourne un iterateur sur les sommets */
    public Iterator vertices() { 
	return verticesList.iterator(); 
    }
    /*Ajoute un nouveau sommet avec une valeur donnee */
    public Vertex put(Object value) {
	// Creation d'un nouveau sommet
	Vertex v = new ListVertex(verticesNumber(), value);
	// Incrementation du nombre de sommets
	verticesNumber++;
	// Ajout du sommet a la liste
	verticesList.add(v);
	return v;
    }
    /* Ajoute une arete entre les deux sommets donnes */
    public void putEdge(Vertex s, Vertex b) { 
	((ListVertex) s).next.add(b); 
    }
    /* Retourne s'il y a une arete entre deux sommets */
    public boolean getEdge(Vertex s, Vertex b) { 
	return ((ListVertex) s).next.contains(b); 
    }
    /* Retourne un iterateur sur les sommets adjacents au sommet donne */
    public Iterator nextVertices(Vertex v) { 
	return ((ListVertex) v).next.iterator(); 
    }
    /* Transposition du graphe (retournement des aretes) */
    public void transpose() {
	// Tableau temporaire pour stocker les nouvelles listes d'adjacences
	LinkedList[] next = new LinkedList [verticesNumber];
	// Creation des listes
	for (int i = 0; i < verticesNumber; i++) 
	    next[i] = new LinkedList();
	for (Iterator i = vertices(); i.hasNext();) {
	    Vertex u = (Vertex) i.next();
	    for (Iterator j = nextVertices(u); j.hasNext();) {
		Vertex v = (Vertex) j.next();
		next[v.index()].add(u);
	    }
	}
	for (Iterator i = vertices(); i.hasNext();) {
	    ListVertex u = (ListVertex) i.next();
	    u.next = next[u.index()];
	}
    }
}
