import javax.swing.*;

public class LanceFenetre {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Fenetre fenetre = new Fenetre();
				fenetre.setVisible(true);
			}
		});
	
	}
	
}
