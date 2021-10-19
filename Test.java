
import java.util.Iterator;
import java.util.*;
/*
 * Classe de tests
 
 */
class Test {
    public static void main(String [] args)
    {
	Graph g1 = new ListGraph();
	Vertex v0 = g1.put("A");
	Vertex v1 = g1.put("B");
	Vertex v2 = g1.put("C");
	Vertex v3 = g1.put("D");
	Vertex v4 = g1.put("E");
	g1.putEdge(v0,v1);
  	g1.putEdge(v1,v2);
 	g1.putEdge(v0,v3);
	//g1.putEdge(v3,v0);
  	g1.putEdge(v2,v4);
  	g1.putEdge(v1,v4);
	g1.putEdge(v4,v1);
	System.out.println(g1);
	// Affichage des composantes fortement connexes
	Vertex [] components = g1.stronglyConnectedComponents();
	for (Iterator i = g1.vertices(); i.hasNext();) {
	    Vertex u = (Vertex) i.next();
	    System.out.println("Sommet " + u + "-->" + components[u.index()]);
	}
	//  	g1.putEdge(v4,v0);
	//	g1.putEdge(v0,v4);
 	//for (Iterator it = g1.depthFirstSearch(v0);
	//    it.hasNext(); ) {
// 	    Vertex v = (Vertex) it.next();
// 	    System.out.println("Sommet : " + v + 
// 			       ", valeur : " + v.value().toString());
// 	}
	// Creation d'un graphe identique
//  	MatrixGraph g2 = new MatrixGraph(g1);
//  	g2.transpose();
//  	System.out.println(g2.cyclic());
// 	for (Iterator it = g2.depthFirstSearch(0);
// 	     it.hasNext(); ) {
// 	    Vertex v = (Vertex) it.next();
// 	    System.out.println("Sommet : " + v + 
// 			       ", valeur : " + v.value().toString());
// 	}
//  	Graph g3 = new ListGraph(g2);
//  	g3.transpose();
//  	System.out.println(g3);
	
    }
}
