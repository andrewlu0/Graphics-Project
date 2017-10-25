import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicsFrame extends JFrame {

	public static void main(String[] args) throws MalformedURLException {
		new GraphicsFrame();
	}

	public GraphicsFrame() throws MalformedURLException {
		super("PRIUS CACTUS GRAN TURISMO feat. DJ KHALED");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		JPanel p = new GraphicsPanel();
		
	
		
		this.add(p);
		
		this.pack();
		this.setVisible(true);
	}
	
}
