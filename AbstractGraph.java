
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.*;
/*
 * Implementation generique des graphes
 * Cette classe abstraite gere le nombre des sommets.
 * Elle implemente aussi les parcours de graphes puisque ceux-ci
 * ne dependent pas de l'implementation du graphe.
 
 */
abstract class AbstractGraph implements Graph {
    /* Nombre de sommets du graphe */
    protected int verticesNumber;
    /* Retourne le nombre de sommets */
    public int verticesNumber() { 
	return verticesNumber; 
    }
    /* Creation d'un graphe vide */
    protected AbstractGraph() { 
	// Aucune sommet au depart
	verticesNumber = 0;	
    }
    /* Retoune la valeur un sommet */
    public Object get(Vertex v) { 
	return v.value(); 
    } 
    /* Conversion en chaine */
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("Sommets : ");
	for (Iterator i = vertices(); i.hasNext(); ) {
	    Vertex u = (Vertex) i.next();
	    // Affichage du sommet et de sa valeur entre parentheses
	    sb.append(u.toString() + "(" + u.value() + "), ");
	}
	sb.append("\n");	// Retour a la ligne
	sb.append("Aretes : ");
	for (Iterator i = vertices(); i.hasNext(); ) {
	    Vertex u = (Vertex) i.next();
	    for (Iterator j = nextVertices(u); j.hasNext(); ) 
		sb.append(u.toString() + "-->" + j.next().toString() + ", ");
	}
	return sb.toString();
    }
    /* Iterateur pour un parcours en largeur du graphe */
    class BreadthFirstIterator implements Iterator {
	// File d'attente
	// Lors qu'un sommet est decouvert pour la premiere fois,
	// il est mis dans la file d'attente jusqu'a ce qu'il soit 
	// traite ulterieurement.  Il est alors traite au moment 
	// ou il est retire de la file d'attente.
	Fifo fifo;
	// Couleurs des sommets
	// A chaque sommet est associee une couleur parmi les trois couleurs
	// { white, grey, black }.
	// Au depart, tous les sommets sont blancs.  Lorsqu'un sommet est
	// decouvert, il devient gris et il est mis dans la file d'attente.
	// Finalement, un sommet devient noir lorsqu'il est retire de la
	// file d'attente et qu'il est traite.  
	byte color[];
	// Constantes pour les couleurs 
	final byte white = 0;
	final byte grey  = 1;
	final byte black = 2;
	/* Constructeur */
	BreadthFirstIterator(Vertex v) {
	    // Creation de la pile et du tableau des couleurs
	    fifo = new Fifo();
	    color = new byte [verticesNumber()];
	    // Initialisation des couleurs des sommets
	    // Au depart, tous les sommets sont blancs.
	    for (int i = 0; i < color.length; i++)
		color[i] = white;
	    // On met le sommet v dans la file et il devient gris.
	    color[v.index()] = grey;
	    fifo.enfiler(v);
	}
	public boolean hasNext() {
	    // La file contient les sommets a traiter.
	    return !fifo.fileVide();
	}
	public Object next() {
	    if (hasNext()) {
		// Le sommet a traiter est le sommet en tete de liste.
		Vertex current = (Vertex) fifo.defiler();
		// Code de tracage
		// System.out.println("Noeud " + current + " hors de la file");
		// On parcours les sommets adjacents au sommet a traiter.
		for (Iterator it = nextVertices(current);
		     it.hasNext();) {
		    // Tout sommet qui est decouvert devient gris et il
		    // est mis dans la file d'attente.
		    Vertex v = (Vertex) it.next();
		    // Code de tracage
		    // System.out.println("Arete " + current + "-->" +  v);
		    if (color[v.index()] == white) {
			// Code de tracage
			// System.out.println("Noeud " + v + " dans la file");
			color[v.index()] = grey;
			fifo.enfiler(v);
		    }
		}
		// Le sommet traite devient noir.
		color[current.index()] = black;
		// La valeur du sommet est retournee.
		return current;
	    } else 
		// Plus d'element a traiter
		throw new NoSuchElementException();
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /* Retourne un iterateur pour un parcours en largeur du graphe */
    public Iterator breathFirstIterator(Vertex v) {
	return new BreadthFirstIterator(v);
    }
    /* Iterateur pour un parcours en profondeur du graphe */
    class DepthFirstIterator implements Iterator {
	// Sommet courant 
	// Cette variable est positionee sur le prochain sommet a rendre
	// par la methode hasNext().  La methode next() met la reference null
	// dans cette variable pour marquer que le sommet courant a ete 
	// retourne et qu'il faut chercher le suivant.
	Vertex current = null;
	// Pile pour contenir les iterateurs en cours
	Stack stack;
	// Tableau des couleurs
	// A chaque sommet est associee une couleur parmi les trois couleurs
	// { white, grey, black }.
	// Au depart, tous les sommets sont blancs.
	byte color[];
	// Constantes pour les couleurs 
	final byte white = 0;
	final byte grey  = 1;
	final byte black = 2;
	/* Constructeur */
	DepthFirstIterator() {
	    // Creation du tableau des couleurs
	    color = new byte [verticesNumber()];
	    // Initialisation des couleurs des sommets
	    // Au depart, tous les sommets sont blancs.
	    for (int i = 0; i < color.length; i++)
		color[i] = white;
	    // Creation de la pile
	    stack = new Stack();
	    // Empilement de l'iterateur sur les sommets
	    stack.push(vertices());
	}
	public boolean hasNext() {
	    // Un sommet est disponible
	    if (current != null)
		return true;
	    // Recherche 
	    while (!stack.empty()) {
		Iterator adj = (Iterator) stack.peek();
		while (adj.hasNext()) {
		    Vertex w = (Vertex) adj.next();
		    // Code de tracage
		    if (stack.size() == 1) {
			System.out.println("Sommet " + w + " de l'iterateur");
		    } else {
			System.out.println("Arete -->" + w);
		    }
		    if (color[w.index()] == white) {
			// Code de tracage
			System.out.println("Sommet " + w + " decouvert");
			// Le nouveau sommet trouve devient gris
			color[w.index()] = grey;
			// L'iterateur sur les successeurs de w est empile
			stack.push(nextVertices(w));
			// Le prochain sommet est w
			current = w;
			// On retourne true
			return true;
		    } 
		}
		// L'iterateur sur le sommet de la pile n'a pas donne
		// de successeur blanc. Il est donc depile.
		stack.pop();
	    }
	    // Il n'y a plus d'iterateur dann la pile et il n'y a 
	    // donc plus de sommet blanc.
	    return false;
	}
	public Object next() {
	    if (hasNext()) {
		// Le sommet a retourner
		Vertex result = current;
		// La variable current est positionee a null pour marquer
		// que le sommet a ete retourne.
		current = null;
		// Le resultat est retourne
		return result;
	    } else 
		// Plus d'element a traiter
		throw new NoSuchElementException();
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /* Retourne un iterateur pour un parcours en profondeur du graphe */
    public Iterator depthFirstIterator() {
	return new DepthFirstIterator();
    }
    /*
     * Parcours en profondeur.
     * Ce parcours en profondeur permet de tester si un graphe est acyclique
     * et auquel cas de calculer un tri topologique des sommets.
     */
    private class DepthFirstSearch {
	// Couleurs pour marquer les sommets lors du parcours
	byte color[];
	// Constantes pour les couleurs 
	final byte white = 0;
	final byte grey  = 1;
	final byte black = 2;
	// Liste pour le tri topologique
	LinkedList list;
	/* Constructeur */
	DepthFirstSearch () {
	    // Creation du tableau des couleurs
	    color = new byte [verticesNumber()];
	    // Creation de la liste
	    list = new LinkedList();
	}
	/* 
	 * Appel recusif pour le tri topologique.
	 * Cette methode ajoute les sommets a la liste au fur et a mesure 
         * que leur traitement se termine.
	 * Cette methode retourne en outre si le graphe possede un cycle.
	 */
	private boolean topologicalSortVisit(Vertex u) {
	    color[u.index()] = grey;
	    // Parcours des aretes issues de u
	    for (Iterator i = nextVertices(u); i.hasNext();) {
		// Sommet adjacent a u
		Vertex v = (Vertex) i.next();
		// Arc retour
		if (color[v.index()] == grey)
		    return true;
		// Arc de liaison
		if (color[v.index()] == white && topologicalSortVisit(v))
		    return true;
	    }
	    color[u.index()] = black;
	    // Ajout de u en tete de liste
	    list.addFirst(u);
	    // Pas de cycle trouve
	    return false;
	}	    
	/*
	 * Tri topologique des sommets.
	 * Si le graphe est acyclique, cette fonction retourne un tableau
	 * contenant les sommets tries dans un ordre topologique.
	 * Si le graphe possede un cycle, cette fonction retourne la 
	 * reference null.
	 */
	Vertex[] topologicalSort() {
	    // Initialisation des couleurs des sommets
	    // Au depart, tous les sommets sont blancs.
	    for (int i = 0; i < color.length; i++)
		color[i] = white;
	    // Initialisation de la liste
	    list.clear();
	    // Parcours de tous les sommets
	    for (Iterator i = vertices(); i.hasNext();) {
		// Sommet de depart
		Vertex v = (Vertex) i.next();
		// Lancement du parcours en v 
		if (color[v.index()] == white && topologicalSortVisit(v))
		    // Cycle trouve
		    return null;
	    }
	    return (Vertex []) list.toArray(new Vertex [0]);
	}
    }
    /* Test si le graphe a un cycle par un parcours en profondeur */
    public boolean cyclic() {
	DepthFirstSearch dfs = new DepthFirstSearch();
	return dfs.topologicalSort() == null;
    }
    /* Tri topologique  */
    public Vertex [] topologicalSort() {
	DepthFirstSearch dfs = new DepthFirstSearch();
	return dfs.topologicalSort();
    }
    private class StronglyConnectedComponents {
	// Tableau des listes des predecesseurs
	// Pour chaque sommet v, prev[i] va contenir les predecesseurs de v
	// c'est-a-dire les sommets u tels que (u,v) est une arete.
	List [] prev;
	// Couleurs pour marquer les sommets lors du parcours
	byte color[];
	// Constantes pour les couleurs 
	final byte white = 0;
	final byte grey  = 1;
	final byte black = 2;
	// Liste contenant les sommets par ordre inverse de fin 
	// du premier parcours
	LinkedList revlist;
	// Tableau contenant le representant de chaque sommet
	Vertex [] components;
	/* Constructeur */
	StronglyConnectedComponents () {
	    // Creation du tableau des couleurs
	    color = new byte [verticesNumber()];
	    // Creation de la liste
	    revlist = new LinkedList();
	    // Creation du tableau de listes
	    prev = new List[verticesNumber()];
	    // Creation des listes
	    for (int i = 0; i < prev.length; i++)
		prev[i] = new ArrayList();
	    // Calcul des listes
	    for (Iterator i = vertices(); i.hasNext();) {
		Vertex u = (Vertex) i.next();
		for (Iterator j = nextVertices(u); j.hasNext();) {
		    Vertex v = (Vertex) j.next();
		    prev[v.index()].add(u);
		}
	    }
	}
	/* Premier parcours en profondeur */
	private void firstVisit(Vertex u) {
	    color[u.index()] = grey;
	    // Parcours des aretes issues de u
	    for (Iterator i = nextVertices(u); i.hasNext();) {
		// Sommet adjacent a u
		Vertex v = (Vertex) i.next();
		// Arc de liaison
		if (color[v.index()] == white)
		    firstVisit(v);
	    }
	    color[u.index()] = black;
	    // Ajout de u en tete de liste
	    revlist.addFirst(u);
	}
	/* Second parcours en profondeur */
	private void secondVisit(Vertex u, Vertex w) {
	    // Le sommet w est representant de la composante de u
	    components[u.index()] = w;
	    color[u.index()] = grey;
	    // Parcours des aretes arrivant en u
	    for (Iterator i = prev[u.index()].iterator(); i.hasNext();) {
		// Sommet adjacent a u
		Vertex v = (Vertex) i.next();
		// Arc de liaison
		if (color[v.index()] == white)
		    secondVisit(v, w);
	    }				
	    color[u.index()] = black;
	}
	/*
	 * Calcul des composantes fortement connexes.
	 * Cette methode retourne un tableau t de sommets.  Pour chaque 
	 * sommet u, t[u.index()] contient la reference du sommet qui 
	 * represente la composante fortement contient de u.
	 * Deux sommets sont donc dans la meme composante ssi
	 * t[u.index()] == t[v.index()].
	 */
	Vertex [] components() {
	    // Creation du tableau
	    components = new Vertex [verticesNumber()];
	    // Premier parcours en profondeur
	    // Initialisation des couleurs des sommets
	    // Au depart, tous les sommets sont blancs.
	    for (int i = 0; i < color.length; i++)
		color[i] = white;
	    // Initialisation de la liste
	    revlist.clear();
	    // Parcours de tous les sommets
	    for (Iterator i = vertices(); i.hasNext();) {
		// Sommet de depart
		Vertex v = (Vertex) i.next();
		// Lancement du parcours en v 
		if (color[v.index()] == white)
		    firstVisit(v);
	    }
	    // Second parcours en profondeur
	    // Initialisation des couleurs des sommets
	    // Au depart, tous les sommets sont blancs.
	    for (int i = 0; i < color.length; i++)
		color[i] = white;
	    // Initialisation de la liste
	    // Parcours de tous les sommets
	    for (Iterator i = revlist.iterator(); i.hasNext();) {
		// Sommet de depart
		Vertex v = (Vertex) i.next();
		// Lancement du parcours en v avec v comme representant
		if (color[v.index()] == white)
		    secondVisit(v, v);
	    }
	    return components;
	}
    }
    /* Calcul des composantes fortement connexes */
    public Vertex [] stronglyConnectedComponents() {
	return new StronglyConnectedComponents().components();
    }
}	    
