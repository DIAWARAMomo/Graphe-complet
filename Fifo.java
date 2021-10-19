import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.*;
public class Fifo {
	Vertex f[] = new Vertex[100];
	int debut = 0;
	int fin = 0;
	boolean fileVide() {
		if (debut == fin) {
			return true;
		} else {
			return false;
		}
	}
	void enfiler(Vertex x) {
		f[fin++] = x;
		if (fin > 100) {
			fin = 0;
		}
	}
	Vertex defiler() {
		Vertex t = f[debut++];
		if (debut > 100) {
			debut = 0;
		}
		return t;
	}
}
