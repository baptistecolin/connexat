import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.*;

public class creerAreteAction extends AbstractAction {
	private DessinPanel panel;
	
	public creerAreteAction(DessinPanel fen, String nom) {
		super(nom);
		panel = fen;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Iterator<Sommet> it = panel.getGraphe().getSommets().iterator();
		while(it.hasNext()) {
			it.next().setColor(Color.white);
		}
		panel.repaint();
		
		Arete a = new Arete();
		panel.getGraphe().ajouteArete(a);
		
		panel.setCreationArete(true);
		panel.setSommetDepartSelectionne(false);
		panel.setAreteCreee(a);
	}
	
}
