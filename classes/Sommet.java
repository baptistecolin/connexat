import java.awt.*;


public class Sommet {
	//attributs
	private static int nombre = 0;
	private int numero; // � chaque noeud est attribu� un num�ro, c'est ce qui permettra de les diff�rencier.
	private int x;
	private int y;	// x et y sont les coordonn�es du sommet
	private static int rayon = 20; //rayon d'un sommet, en pixels, lorsqu'on le dessine.
	private Color couleur = Color.white;
	
	//constructeurs
	public Sommet() {
		numero = nombre++; //� la cr�ation de chaque sommet, on incr�mente nombre pour que le prochain sommet cr�� ait un numero diff�rent.
	}
	
	public Sommet(int xs, int ys) {
		numero = nombre++;
		x=xs;
		y=ys;
	}
	
	//m�thodes
	public int num() {
		return numero;
	}
	
	public boolean equals (Object o) {
		if (o instanceof Sommet) {
			return (((Sommet) o).num() == num());  //deux sommets sont �gaux ssi ils ont le m�me numero.
		}
		else {
			return false;
		}
	}
	
	//m�thodes pour obtenir les coordonn�es
	public int getAbs() {
		return x;
	}
	
	public int getOrd() {
		return y;
	}
	
	public Point getLocation() {
		return new Point(x,y);
	}
	
	//m�thodes pour changer les coordonn�es
	public void setPosition(Point p) {
		x = (int) p.getX();
		y = (int) p.getY();
	}
	
	//m�thode pour dessiner un noeud
	public void draw(Graphics g) {
		g.setColor(couleur);
		g.fillOval(x-rayon,y-rayon,2*rayon,2*rayon); //on remplit d'abord le cercle de couleur
		g.setColor(Color.white);
		g.fillOval(x-rayon+4, y-rayon+4, 2*(rayon-4), 2*(rayon-4)); //puis on remplit un cercle blanc � l'int�rieur du cercle de couleur pour obtenir une couronne de couleur
		g.setColor(Color.black);
		g.drawOval(x-rayon,y-rayon,2*rayon,2*rayon); //enfin on entoure d'un cerle noir
		g.drawString(""+numero, x-4, y+4); //et on affiche le num�ro du noeud
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
