import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.*;

public class creerSommetAction extends AbstractAction {
	private DessinPanel panel;
	
	public creerSommetAction(DessinPanel fen, String nom) {
		super(nom);
		panel = fen;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		Iterator<Sommet> it = panel.getGraphe().getSommets().iterator();
		while(it.hasNext()) {
			it.next().setColor(Color.white);
		}
		panel.repaint();
		
		Sommet s = new Sommet();
		panel.getGraphe().ajouteSommet(s);
		
		panel.setDeposeSommet(true);
		panel.setSommetDepose(s);
	}

}
