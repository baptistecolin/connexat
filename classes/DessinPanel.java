import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DessinPanel extends JPanel implements MouseListener, MouseMotionListener {
	private Fenetre fenetre;
	private Sommet sommetDeplace;
	
	//variables relatives à la création d'une arete
	private boolean creationArete = false;
	private boolean sommetDepartSelectionne = false;
	private Arete areteCreee;
	private Sommet fantome = new Sommet();
	
	//variables relatives à la création d'un sommet
	private boolean deposeSommet = false;
	private Sommet sommetDepose;
	
	public DessinPanel(Fenetre fen) {
		super();
		fenetre = fen;
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		fenetre.getGraphe().draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			if (deposeSommet) {
				deposeSommet = false;
			}
			else if (creationArete) {
				
				if (sommetDepartSelectionne) {
					Iterator<Sommet> it = fenetre.getGraphe().getSommets().iterator();
					
					while (it.hasNext()) {
						Sommet s = it.next();
						if (e.getPoint().distance(s.getLocation())<s.getRayon()) {
							areteCreee.setArrivee(s);
							creationArete = false;
							repaint();
						}
					}
				}
				else {
					Iterator<Sommet> it = fenetre.getGraphe().getSommets().iterator();
				
					while (it.hasNext()) {
						Sommet s = it.next();
						if (e.getPoint().distance(s.getLocation())<s.getRayon()) {
							areteCreee.setDepart(s);
							sommetDepartSelectionne = true;
							areteCreee.setArrivee(fantome);
						}
					}
				}
			}
			else {
				Iterator<Sommet> it = fenetre.getGraphe().getSommets().iterator();
			
				while (it.hasNext()) {
					Sommet s = it.next();
					if (e.getPoint().distance(s.getLocation())<s.getRayon()) {
						sommetDeplace = s;
					}
				}
			}
		}
		else if (SwingUtilities.isRightMouseButton(e)) {
			Iterator<Sommet> it = ((HashSet<Sommet>) fenetre.getGraphe().getSommets().clone()).iterator();
			while (it.hasNext()) {
				Sommet s = it.next();
				if (e.getPoint().distance(s.getLocation())<s.getRayon()) {
					fenetre.getGraphe().remove(s);
				}
			}
			
			Iterator<Sommet> iter = fenetre.getGraphe().getSommets().iterator();
			while(iter.hasNext()) {
				iter.next().setColor(Color.white);
			}
			
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		sommetDeplace = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (sommetDeplace != null) {
			if(sommetDeplace.getRayon()<e.getPoint().getX() && e.getPoint().getX()<getWidth()-sommetDeplace.getRayon() && sommetDeplace.getRayon()<e.getPoint().getY() && e.getPoint().getY()<getHeight()-sommetDeplace.getRayon())
			sommetDeplace.setPosition(e.getPoint());
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (deposeSommet) {
			sommetDepose.setPosition(e.getPoint());
			repaint();
		}
		if (creationArete && sommetDepartSelectionne) {
			fantome.setPosition(e.getPoint());
			repaint();
		}
	}
	
	public Graphe getGraphe() {
		return fenetre.getGraphe();
	}
	
	public void setDeposeSommet(boolean b) {
		deposeSommet = b;
	}
	
	public void setSommetDepose(Sommet s) {
		sommetDepose = s;
	}
	
	public void setAreteCreee(Arete a) {
		areteCreee = a;
	}

	public void setSommetDepartSelectionne(boolean b) {
		sommetDepartSelectionne = b;
	}

	public void setCreationArete(boolean b) {
		creationArete = b;
	}
	
	public Fenetre getFenetre() {
		return fenetre;
	}
}
