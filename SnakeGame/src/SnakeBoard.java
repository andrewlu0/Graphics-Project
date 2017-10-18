import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SnakeBoard {
	private int cols;
	private int rows;
	private GameObject[][] grid;
	private Timer t;
	private SnakePanel sp;
	JFrame frame;
	private Snake one;

	public SnakeBoard() {
		// TODO Auto-generated constructor stub
		GameObject[][] a = new GameObject[30][30];
		grid = a;
		cols = 800;
		rows = 600;
		Snake snake = new Snake();
		one = snake;
		JFrame k = new JFrame();
		frame = k;
		
	}
	public void display(){
		
		frame.setSize(1280, 720);
		SnakeBoard b = new SnakeBoard();
		SnakePanel panel = new SnakePanel(b);
		sp = panel;
		
		frame.add(sp);
		frame.pack();
		frame.validate();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	public void setUpObjects() {
		// TODO Auto-generated method stub
		
	}
	public void setUpTimer() {
		// TODO Auto-generated method stub
		t = new Timer(100 , new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveStuff();
				addStuff();
				reDraw();

			}

			private void addStuff() {
				// TODO Auto-generated method stub
				
			}

			private void moveStuff() {
				// TODO Auto-generated method stub
				one.move();
			}

			protected void reDraw() {
				// TODO Auto-generated method stub
				frame.repaint();
			}

		});
	}

	public void start() {
		// TODO Auto-generated method stub
		display();
		setUpObjects();
		setUpTimer();
		setUpKeyBind();
		t.start();
	}
	private void setUpKeyBind() {
		// TODO Auto-generated method stub
		sp.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "turnSnakeOneRight");
		sp.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "turnSnakeOneLeft");
		sp.getInputMap().put(KeyStroke.getKeyStroke("UP"), "turnSnakeOneUp");
		sp.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "turnSnakeOneDown");
		sp.getActionMap().put("turnSnakeOneRight",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				one.turnRight();
			}
		});
		sp.getActionMap().put("turnSnakeOneLeft",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				one.turnLeft();
			}
		});
		sp.getActionMap().put("turnSnakeOneUp",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				one.turnUp();
			}
		});
		sp.getActionMap().put("turnSnakeOneDown",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				one.turnDown();
			}
		});
		sp.requestFocusInWindow();
	}
	
	public int getCols() {
		// TODO Auto-generated method stub
		return cols;
	}
	public int getRows() {
		// TODO Auto-generated method stub
		return rows;
	}
	public void draw(Graphics g, int dIM) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		
	}
}
