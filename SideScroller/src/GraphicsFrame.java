import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFrame;


public class GraphicsFrame extends JFrame {

	public static void main(String[] args) {
		
		new GraphicsFrame();
	}
	
	public GraphicsFrame() {
		
		super("SideScrollerTemplate");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.add(new MarioPanel());
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

}
