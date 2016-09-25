import java.awt.*;


public class Sommet {
	//attributs
	private static int nombre = 0;
	private int numero; // à chaque noeud est attribué un numéro, c'est ce qui permettra de les différencier.
	private int x;
	private int y;	// x et y sont les coordonnées du sommet
	private static int rayon = 20; //rayon d'un sommet, en pixels, lorsqu'on le dessine.
	private Color couleur = Color.white;
	
	//constructeurs
	public Sommet() {
		numero = nombre++; //à la création de chaque sommet, on incrémente nombre pour que le prochain sommet créé ait un numero différent.
	}
	
	public Sommet(int xs, int ys) {
		numero = nombre++;
		x=xs;
		y=ys;
	}
	
	//méthodes
	public int num() {
		return numero;
	}
	
	public boolean equals (Object o) {
		if (o instanceof Sommet) {
			return (((Sommet) o).num() == num());  //deux sommets sont égaux ssi ils ont le même numero.
		}
		else {
			return false;
		}
	}
	
	//méthodes pour obtenir les coordonnées
	public int getAbs() {
		return x;
	}
	
	public int getOrd() {
		return y;
	}
	
	public Point getLocation() {
		return new Point(x,y);
	}
	
	//méthodes pour changer les coordonnées
	public void setPosition(Point p) {
		x = (int) p.getX();
		y = (int) p.getY();
	}
	
	//méthode pour dessiner un noeud
	public void draw(Graphics g) {
		g.setColor(couleur);
		g.fillOval(x-rayon,y-rayon,2*rayon,2*rayon); //on remplit d'abord le cercle de couleur
		g.setColor(Color.white);
		g.fillOval(x-rayon+4, y-rayon+4, 2*(rayon-4), 2*(rayon-4)); //puis on remplit un cercle blanc à l'intérieur du cercle de couleur pour obtenir une couronne de couleur
		g.setColor(Color.black);
		g.drawOval(x-rayon,y-rayon,2*rayon,2*rayon); //enfin on entoure d'un cerle noir
		g.drawString(""+numero, x-4, y+4); //et on affiche le numéro du noeud
	}
	
	public int getRayon() {
		return rayon;
	}
	
	public void setColor(Color c) {
		couleur = c;
	}
	
	public Color getCouleur() {
		return couleur;
	}
}
