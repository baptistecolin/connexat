import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Graphe implements Cloneable {
	//attributs
	private HashSet<Sommet> sommets;
	private HashSet<Arete> aretes;
	
	//constructeurs
	public Graphe(HashSet<Sommet> N, HashSet<Arete> A) {
		sommets = N;
		aretes = A;
	}
	
	public Graphe() {
		this(new HashSet<Sommet>(), new HashSet<Arete>());
	}
	
	//méthodes
	
	//pour dessiner un graphe, on dessine toutes les arêtes puis tous les sommets.
	//en dessinant les sommets après les arêtes, on s'assure qu'à l'écran les arêtes ne "rentrent" pas dans les sommets.
	public void draw(Graphics g) {
		Iterator<Arete> itAretes = aretes.iterator();
		while (itAretes.hasNext()) {
			itAretes.next().draw(g);
		}
		
		Iterator<Sommet> itSommets = sommets.iterator();
		while (itSommets.hasNext()) {
			itSommets.next().draw(g);
		}
	}
	
	
	public Graphe clone() {
		Graphe res = new Graphe((HashSet<Sommet>) sommets.clone(), (HashSet<Arete>) aretes.clone());
		return res;
	}
	
	public void ajouteSommet(Sommet N) {
		this.sommets.add(N);
	}	
	
	public void ajouteSommet(Sommet N, HashSet<Sommet> dep, HashSet<Sommet> arr) { //ajoute un sommet N au graphe, et créé des arêtes allant de tous les
		//ajout de N au graphe												       //sommets de dep vers N, et de N vers tous les sommets de arr
		this.sommets.add(N);													   

		//construction des arrêtes allant vers N
		Iterator<Sommet> it = dep.iterator(); 
		while (it.hasNext()) {
			ajouteArete(new Arete(it.next(), N));
		} 
		
		//construction des arrêtes partant de N
		it = arr.iterator();
		while(it.hasNext()) {
			ajouteArete(new Arete(N, it.next()));			
		}
		
	}
	
	public void ajouteArete(Arete A) {
		this.aretes.add(A);
	}
	
	
	public HashSet<Sommet> voisins(Sommet N) {//cette méthode renvoie tous les sommets qui sont la terminaison d'une arete partant de N
		HashSet<Sommet> res = new HashSet<Sommet>();
		
		Iterator<Arete> it = aretes.iterator();
		while (it.hasNext()) {
			Arete A = it.next();
			if (A.getDepart().equals(N)) {
				res.add(A.getArrivee());
			}
		}
		return res;
	}
	
	public Graphe enlever(Sommet N) {//renvoie le graphe obtenu en enlevant toutes les arrêtes arrivant sur N ainsi que le sommet N
		Graphe g = this.clone();
		Iterator<Arete> it = this.aretes.iterator();
		while (it.hasNext()) { //on examine toutes les arêtes; si elles aboutissent sur N ou partent de N on les enlève.
			Arete A = it.next();
			if (A.getArrivee().equals(N) || A.getDepart().equals(N)) {
				g.aretes.remove(A);
			}
		}
		g.sommets.remove(N); //on enlève le sommet N
		
		return g;
	}
	
	public void remove(Sommet N) { //même fonction mais par effet de bord cette fois ci
		Graphe g = this.clone();
		Iterator<Arete> it = g.aretes.iterator();
		while (it.hasNext()) { //on examine toutes les arêtes; si elles aboutissent sur N ou partent de N on les enlève.
			Arete A = it.next();
			if (A.getArrivee().equals(N) || A.getDepart().equals(N)) {
				this.aretes.remove(A);
			}
		}
		this.sommets.remove(N); //on enlève le sommet N
	}
	
	public Graphe enlever(HashSet<Sommet> E) {// même chose, mais en enlevant un ensemble de noeuds
		Graphe g = this.clone();
		Iterator<Sommet> it = E.iterator();
		while (it.hasNext()) {
			g = g.enlever(it.next());
		}
		return g;
	}
	
	public HashSet<Sommet> sommetsDescendantsAux(Sommet N, Graphe g) { //cette méthode  récursive renvoie l'ensemble des sommets du graphe qui peuvent être atteints en partant de N
		HashSet<Sommet> res = new HashSet<Sommet>();
		HashSet<Sommet> voisins = g.voisins(N);
		
		res.add(N);
		g = g.enlever(N);
		
		Iterator<Sommet> it = voisins.iterator();
		while (it.hasNext()) {
			res.addAll(this.sommetsDescendantsAux(it.next(),g));
		}
		
		return res;		
	}
	
	public HashSet<Sommet> sommetsDescendants(Sommet N) {
		Graphe g = this.clone();
		return sommetsDescendantsAux(N,g);
	}
	
	public HashSet<Sommet> sommetsAscendants(Sommet N) {
		return (this.reverse()).sommetsDescendants(N); //on utilise ici le fait que si A est descendant de B, alors B est ascendant de A
	}
	
	public Graphe reverse() { //fonction qui à un graphe g associe le graphe g' comportant les mêmes sommets et les arrêtes de g inversées (g comprend une arête de A vers B sssi g' comprend une arête de B vers A)
		HashSet<Sommet> resNoeuds = (HashSet<Sommet>) this.sommets.clone();
		HashSet<Arete> resAretes = new HashSet<Arete>();
		
		Iterator<Arete> it = this.aretes.iterator();
		while (it.hasNext()) {
			resAretes.add(it.next().reverse());
		}
		
		return (new Graphe(resNoeuds, resAretes));
	}
	
	public Map<Sommet,HashSet<Sommet>> kosaraju() { //on va renvoyer un Map qui à un sommet associe sa composante connexe.
		if (this.sommets.isEmpty()) {
			return(new HashMap<Sommet,HashSet<Sommet>>()); //si le graphe est vide, on renvoie un Map vide
		}
		else {
			Iterator<Sommet> it = this.sommets.iterator();
			Sommet N = it.next(); //on part d'un sommet quelconque

			HashSet<Sommet> composante = sommetsDescendants(N);
			composante.retainAll(sommetsAscendants(N));
			
			Map<Sommet,HashSet<Sommet>> res = new HashMap<Sommet,HashSet<Sommet>>(); //on a construit une composante connexe en prenant l'intersection des sommet ascendants et descendants d'un certain sommet
			Iterator<Sommet> iter = composante.iterator();
			
			while(iter.hasNext()) {
				res.put(iter.next(), composante);
			}
			
			res.putAll((this.enlever(composante)).kosaraju()); //on réitère l'opération en enlevant les noeuds pours lesquels on a déjà trouvé la composante connexe
			return res;
		}
	}
	
	public HashSet<Sommet> getSommets() {
		return sommets;
	}
	
	public HashSet<Arete> getAretes() {
		return aretes;
	}
}
