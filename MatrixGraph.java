
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.*;
/*
 * Implementation des graphes par matrice d'adjacence.
 * Dans cette implementation, le nombre de sommets est fixe a la creation
 * du graphe.  Il n'est pas possible d'ajouter de nouveaux sommets.

 */
class MatrixGraph extends AbstractGraph {
    /* Tableaux des sommets */
    private MatrixVertex [] verticesArray;
    /* Matrice d'adjacence */
    private boolean [][] matrix;
    /* Creation d'un graphe sans aretes d'un nombre de sommets donnes  */
    public MatrixGraph(int size) {
	// super();
	// Initialisation du nombre de sommets
	verticesNumber = size;
	// Creation de tous les sommets au depart
	verticesArray = new MatrixVertex[size];
	for (int i = 0; i < size; i++) 
	    verticesArray[i] = new MatrixVertex(i);
	// Creation de la matrice d'adjacence
	// Au depart il n'y a aucune arete
	matrix = new boolean [size][size] ;
    }
    /* Creation d'un graphe identique a un graphe donne */
    public MatrixGraph(Graph g) {
	// Creation du graphe vide
	this(g.verticesNumber());
	// Mise en place des aretes
	for (Iterator i = g.vertices(); i.hasNext();) {
	    Vertex u = (Vertex) i.next();
	    int uindex = u.index();
	    for (Iterator j = g.nextVertices(u); j.hasNext();) {
		Vertex v = (Vertex) j.next();
		matrix[uindex][v.index()] = true;
	    }
	}
    }
    /* Iterateur sur les sommets */
    private class Vertices implements Iterator {
	int i = 0;		// Curseur sur le sommet courant
	public boolean hasNext() {
	    return i < verticesNumber;
	}
	public Object next() {
	    if (hasNext()) 
		return verticesArray[i++];
	    else
		throw new NoSuchElementException();
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /* Retourne un iterateur sur les sommets */
    public Iterator vertices() {
	return new Vertices();
    }
    /* Ajoute un nouveau sommet avec une valeur donnee */
    public Vertex put(Object value) {
	throw new UnsupportedOperationException();
    }
    /* Ajoute une arete entre les deux sommets donnes */
    public void putEdge(Vertex s, Vertex b) { 
	matrix[s.index()][b.index()] = true;
    }
    /* Ajoute une arete entre les deux sommets donnes */
    public void putEdge(int s, int b) { 
	if (s < verticesNumber && b < verticesNumber)
	    matrix[s][b] = true;
    }
    /* Retourne s'il y a une arete entre deux sommets */
    public boolean getEdge(Vertex s, Vertex b) { 
	return matrix[s.index()][b.index()]; 
    }
    /* Retourne s'il y a une arete entre deux sommets */
    public boolean getEdge(int s, int b) { 
	if (s < verticesNumber && b < verticesNumber)
	    return matrix[s][b]; 
	else 
	    return false;
    }
    /* Iterateur sur les les sommets adjacents au sommet donne */
    private class NextVertices implements Iterator {
	private int i;
	private int j;
	NextVertices(int i) {
	    this.i = i;
	    j = 0;
	}
	public boolean hasNext() {
	    while (j < verticesNumber && ! matrix[i][j])
		j++;
	    return j < verticesNumber;
	}
	public Object next() {
	    if (hasNext()) 
		return verticesArray[j++];
	    else
		throw new NoSuchElementException();
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /* Retourne un iterateur sur les sommets adjacents au sommet donne */
    public Iterator nextVertices(Vertex v) { 
	return new NextVertices(v.index());
    }
    /* Retourne un iterateur pour un parcours en largeur du graphe */
    public Iterator breathFirstIterator(int i) {
	return new BreadthFirstIterator(verticesArray[i]);
    }
    /* Retourne un iterateur pour un parcours en profondeur du graphe */
    /*    public Iterator depthFirstIterator(int i) {
    	return new DepthFirstIterator(verticesArray[i]);
    }*/
    /* Transposition du graphe (retournement des aretes) */
    public void transpose() {
	for (int i = 0; i < verticesNumber; i++) 
	    for (int j = 0; j < i; j++) {
		// Echange de matrix[i][j] et de matrix[j][i]
		boolean tmp = matrix[i][j];
		matrix[i][j] = matrix[j][i];
		matrix[j][i] = tmp;
	    }
    }
}

