import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.util.*;

public class TableFenetre extends JFrame {
	//atributs
	HashSet<HashSet<Sommet>> composantes;
	Fenetre fenetre;
	HashSet<Sommet>[] tableauUtile;
	
	//constructeurs
	public TableFenetre(HashSet<HashSet<Sommet>> c, Fenetre f) {
		super();
		composantes = c;
		fenetre = f;
		build();
	}
	
	//méthodes
	private void build() {
		setTitle("Infos sur les composantes connexes");
		setSize(600,200);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane() {
		JPanel panel = new JPanel();
		JTable table;
		JButton coloriageBouton;
		
		panel.setLayout(new BorderLayout());
		
		table = new JTable(new MyTableModel(composantes));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.setDefaultRenderer(Color.class, new ColorRenderer(true));
		table.setDefaultEditor(Color.class, new ColorEditor());
		table.setPreferredScrollableViewportSize(new Dimension(600, 100));
		panel.add(scrollPane, BorderLayout.PAGE_START);
		
		coloriageBouton = new JButton(new colorierAction(this, fenetre, "Recolorier", table.getModel(), tableauUtile, fenetre.getGraphe()));
		panel.add(coloriageBouton, BorderLayout.PAGE_END);
		
		return panel;
	}
	
	class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Nom",
                                        "Couleur",
                                        "Sommets de la composante"};
        
        private Object[][] resultat  = new Object[composantes.size()][3];
        
        public MyTableModel(HashSet<HashSet<Sommet>> composantes) {
        	Iterator<HashSet<Sommet>> itSet = composantes.iterator();
    		
        	tableauUtile = new HashSet[composantes.size()];
        	
    		int i = 1;
    		for(Object[] array : resultat) {
    			HashSet<Sommet> set = itSet.next();
    			
    			tableauUtile[i-1] = set;
    			
    			array[0] = "Composante n°"+i++;
    			array[1] = couleur(set);
    			array[2] = "";
    			
    			Iterator<Sommet> itSom = set.iterator();
    			while(itSom.hasNext()) {
    				array[2] = array[2]+ ""+ itSom.next().num();
    				
    				if(itSom.hasNext()) {
    					array[2] = array[2]+", ";
    				}
    				
    			}
    		}
        }
        
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return resultat.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return resultat[row][col];
        }
        
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
        	
            resultat[row][col] = value;
            fireTableCellUpdated(row, col);

        }

    }
	 
	private Color couleur(HashSet<Sommet> set) {
		 Iterator<Sommet> it = set.iterator();
		 
		 return it.next().getCouleur();
	 }
	
}
