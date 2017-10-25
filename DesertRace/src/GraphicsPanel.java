import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class GraphicsPanel extends JPanel {

	final int WIDTH = 800, HEIGHT = 600;// starting values for width and height
										// in pixels
	JButton coolButton = new JButton("Cool!");// I could have just declared
												// here...
	JTextField textField = new JTextField(20);// put in different numbers and
												// see what happens
	JLabel label = new JLabel();// labels are dynamic and great ways to display
								// stuff
	JTextArea textArea = new JTextArea(40, 40);// these are great for displaying
												// lots of text
	Image image; // reference to an Image to be displayed. Opened later, but
					// could be opened here
	private int numPaints;// just a counter that I used to show what events
							// cause a call to paintComponent
	private int x = 50, y = 200;// these are the coords where I display the ima
	private Random die = new Random();// I wanted randomness (specifically for
										// colors)
	RaceTrack track;

	public GraphicsPanel() throws MalformedURLException {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));// Otherwise, the frame
														// starts off small
		// setUpGridLayout(2,2);// you can arrange the components in a grid
		// setUpBorderLayout();// This places things around the border or center
		this.setUpWithNullLayout();// This gives you more freedom, but you lose
									// the dynamic nature
									// that a layout manager gives.
		// setUpButtons();// or just one button. The code written in this method
		// is
		// generally accepted as a nice approach to linking the button with
		// event launch
		track = new RaceTrack(this);
		setUpKeyBindings();

	}

	private void setUpKeyBindings() {
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed RIGHT"), "RightMove");
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "RightStop");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed LEFT"), "LeftMove");
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "LeftStop");

		this.getActionMap().put("RightMove", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (track.x > 50)
					track.left();
			}

		});
		this.getActionMap().put("RightStop", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				track.stop();
			}

		});
		this.getActionMap().put("LeftMove", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(track.x);
				if (track.x < 490)
					track.right();
			}

		});
		this.getActionMap().put("LeftStop", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				track.stop();
			}

		});
		this.requestFocusInWindow();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		System.out.println("repainting");
		track.draw(g);

	}

	private void setUpWithNullLayout() {

		this.setLayout(null);

	}

}
