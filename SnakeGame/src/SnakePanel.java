import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class SnakePanel extends JPanel {

	
	private final int WIDTH, HEIGHT, DIM;
	private SnakeBoard sb;
	
	public SnakePanel(SnakeBoard snakeBoard) {
		WIDTH = 800;// better to get the screens dimension
		HEIGHT = 600;//  see above
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		sb = snakeBoard;
		DIM = Math.min(WIDTH/sb.getCols(), HEIGHT/sb.getRows());
		//sb.setBorder(BUFFER);
	}

	@Override
	public void paintComponent(Graphics g) {
		sb.draw(g, DIM);
	}
}
