import javax.swing.*;

import java.awt.*;
import java.util.*;

public class Fenetre extends JFrame {
	private DessinPanel dessinPanel;
	private JPanel boutonsPanel;
	private JPanel composantesPanel;
	private JButton boutonSommet;
	private JButton boutonArete;
	private JButton boutonColoriage;
	private JList<String> liste;
	private String[] tab = new String[10];
	private DefaultListModel<HashSet<Sommet>> composantes = new DefaultListModel<HashSet<Sommet>>();
	
	private Graphe graphe;
	
	public Fenetre() {
		super();
		
		build();
	}
	
	private void build(){ 
		setTitle("Connexat");
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
	}

	private JPanel buildContentPane() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		dessinPanel = new DessinPanel(this);
		dessinPanel.setBackground(Color.white);
		dessinPanel.setVisible(true);
		panel.add(dessinPanel, BorderLayout.CENTER);
		
		boutonsPanel = buildBoutonsPanel();
		panel.add(boutonsPanel, BorderLayout.PAGE_START);
		
		
		HashSet<Sommet> sommetsSet = new HashSet<Sommet>();
		HashSet<Arete> aretesSet = new HashSet<Arete>();
		
		/*Sommet n1 = new Sommet(100,100);
		Sommet n2 = new Sommet(200,100);
		Sommet n3 = new Sommet(300,100);
		Sommet n4 = new Sommet(400,100);
		Sommet n5 = new Sommet(100,300);
		Sommet n6 = new Sommet(200,300);
		Sommet n7 = new Sommet(300,300);
		Sommet n8 = new Sommet(400,300);
		sommetsSet.add(n1);
		sommetsSet.add(n2);
		sommetsSet.add(n3);
		sommetsSet.add(n4);
		sommetsSet.add(n5);
		sommetsSet.add(n6);
		sommetsSet.add(n7);
		sommetsSet.add(n8);
		
		aretesSet.add(new Arete(n1,n2));
		aretesSet.add(new Arete(n2,n3));
		aretesSet.add(new Arete(n3,n4));
		aretesSet.add(new Arete(n4,n3));
		aretesSet.add(new Arete(n5,n1));
		aretesSet.add(new Arete(n5,n6));
		aretesSet.add(new Arete(n2,n5));
		aretesSet.add(new Arete(n2,n6));
		aretesSet.add(new Arete(n6,n7));
		aretesSet.add(new Arete(n7,n6));
		aretesSet.add(new Arete(n3,n7));
		aretesSet.add(new Arete(n8,n7));
		aretesSet.add(new Arete(n4,n8));
		aretesSet.add(new Arete(n8,n4));*/
		
		graphe = new Graphe(sommetsSet,aretesSet);
		composantes = collectionToListModel(graphe.kosaraju().values());
		return panel;
	}

	private JPanel buildBoutonsPanel() {
		JPanel result = new JPanel();
		result.setLayout(new BorderLayout());
		
		JPanel boutons = new JPanel();
		boutons.setLayout(new FlowLayout());
		
		boutonSommet = new JButton(new creerSommetAction(this.dessinPanel,"Créer Sommet"));
		boutons.add(boutonSommet);
		boutonArete = new JButton(new creerAreteAction(this.dessinPanel,"Créer Arête"));
		boutons.add(boutonArete);
		boutonColoriage = new JButton(new calculComposantesAction(this, "Infos sur les composantes connexes"));
		boutons.add(boutonColoriage);
		result.add(boutons, BorderLayout.LINE_START);
		
		return result;
	}
	
	
	public Graphe getGraphe() {
		return graphe;
	}
	
	private DefaultListModel<HashSet<Sommet>> collectionToListModel(Collection<HashSet<Sommet>> collec) {
		Iterator<HashSet<Sommet>> iter = collec.iterator();
		DefaultListModel<HashSet<Sommet>> res = new DefaultListModel<HashSet<Sommet>>();
		
		while(iter.hasNext()) {
			HashSet<Sommet> e = iter.next();
			if(!res.contains(e)) {
				res.addElement(e);
			}
		}
		return res;
	}

	public JList<String> getListe() {
		return liste;
	}
	
	public String[] getTab() {
		return tab;
	}
}
