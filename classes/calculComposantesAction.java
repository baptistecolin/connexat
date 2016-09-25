import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;

public class calculComposantesAction extends AbstractAction {
	private Fenetre fenetre;
	
	public calculComposantesAction(Fenetre fen, String nom) {
		super(nom);
		fenetre = fen;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			Map<Sommet,HashSet<Sommet>> partition = fenetre.getGraphe().kosaraju();
			HashSet<HashSet<Sommet>> composantes = collectionToSet(partition.values());
			
			TableFenetre tableFenetre = new TableFenetre(composantes,fenetre);
			tableFenetre.setVisible(true);
	
		fenetre.repaint();
	}

	private HashSet<HashSet<Sommet>> collectionToSet(Collection<HashSet<Sommet>> c) {
		HashSet<HashSet<Sommet>> res = new HashSet<HashSet<Sommet>>();
		
		Iterator<HashSet<Sommet>> it = c.iterator();
		while(it.hasNext()) {
			res.add(it.next());
		}
		
		return res ;
	}

}
