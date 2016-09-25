import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.table.TableModel;


public class colorierAction extends AbstractAction {
	//attributs
	TableModel table;
	Graphe graphe;
	HashSet[] tableau;
	JFrame fenetre;
	JFrame fenetreDeBase;
	
	//constructeurs
	public colorierAction(JFrame f, JFrame fbase, String nom, TableModel t, HashSet[] tab, Graphe g) {
		super(nom);
		table = t;
		tableau = tab;
		fenetre = f;
		fenetreDeBase = fbase;
		graphe = g;
	}

	//méthodes
	public void actionPerformed(ActionEvent arg0) {
		
		int i=0;
		
		while (i !=tableau.length) {
			Color couleur = (Color) table.getValueAt(i, 1);
			Iterator<Sommet> itSom = tableau[i].iterator();
			while(itSom.hasNext()){
				Sommet reference = itSom.next();
				Iterator<Sommet> itGrph = graphe.getSommets().iterator(); 
				while(itGrph.hasNext()) {
					Sommet som = itGrph.next();
					if (som.equals(reference)) {
						som.setColor(couleur);
					}
				}
			}
			
			i++;
		}
		
		fenetre.setVisible(false);
		fenetreDeBase.repaint();
	}
	

}
