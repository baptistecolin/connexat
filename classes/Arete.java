import java.awt.Graphics;

public class Arete {
	//attributs
	private Sommet depart;
	private Sommet arrivee;
	
	//constructeur
	public Arete() {
		
	}
	public Arete (Sommet dep , Sommet arr) {
		depart = dep;
		arrivee = arr;
	}
	
	//méthodes
	public Sommet getDepart() {
		return depart;
	}
	
	public Sommet getArrivee() {
		return arrivee;
	}
	
	public Arete reverse() {
		return (new Arete (arrivee , depart));
	}
	
	public void draw(Graphics g) {
		try{
			int xb = arrivee.getAbs();
			int xa = depart.getAbs();
			int yb = arrivee.getOrd();
			int ya = depart.getOrd();
			int r = arrivee.getRayon()+2;
			double n = Math.sqrt((xb-xa)*(xb-xa) + (yb-ya)*(yb-ya));
			int xm = (int) (xa+((n-r)/n)*(xb-xa));
			int ym = (int) (ya+((n-r)/n)*(yb-ya));
			int xmm = (int) (xa+((n-arrivee.getRayon())/n)*(xb-xa));
			int ymm = (int) (ya+((n-arrivee.getRayon())/n)*(yb-ya));
			int l = 7;
			
			
			if (arrivee.num()!=0) {
				g.drawLine(xa, ya, xb, yb);
			}
			else {
				g.drawLine(xa, ya, xm, ym);
			} //distinction de cas nécessaire à un affichage propre de l'arête lorsque le sommet d'arrivée n'a pas encore été sélectionné par l'utiliateur
			
					
			double xd = (xb-xa)/n;
			double yd = (yb-ya)/n; //ce sont les coordonnées du vecteur directeur unitaire de l'arête, orienté vers le sommet d'arrivée
			double xn = -yd;
			double yn = xd; //ce sont les coordonnées d'un vecteur normal à l'arête
			
			
			int[] x = new int[3];
			int[] y = new int[3]; 
				//on dessine la pointe de la flèche à l'aide des coordonnées des 3 points qui le définissent.
			
			x[0] = (int) Math.round(xmm);
			x[1] = (int) Math.round((xb-(l+arrivee.getRayon())*xd+l*xn));
			x[2] = (int) Math.round((xb-(l+arrivee.getRayon())*xd-l*xn));
			
			y[0] = (int) Math.round(ymm);
			y[1] = (int) Math.round((yb-(l+arrivee.getRayon())*yd+l*yn));
			y[2] = (int) Math.round((yb-(l+arrivee.getRayon())*yd-l*yn)); 
			
			g.fillPolygon(x,y,3);
		}
		catch (NullPointerException e) {}
	}

	public void setDepart(Sommet som) {
		depart=som;
	}

	public void setArrivee(Sommet som) {
		arrivee=som;		
	}
}
