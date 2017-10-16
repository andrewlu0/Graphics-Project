import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.OverlayLayout;

import com.sun.glass.events.KeyEvent;

public class MarioPanel extends JPanel {

	final int WIDTH = 1400, HEIGHT = 820;// starting values for width and height
											// in pixels

	private Random die = new Random();// I wanted randomness (specifically for
										// colors)
	private MarioWorld world;
	private BufferedImage bkgrnd, bkgrnd1, play, particles;
	private int BCKGRD_W;
	Clip clip;
	AudioInputStream audiostream;
	JLabel label;
	private boolean ingame;

	public MarioPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));// Otherwise, the frame
														// starts off small
		ImageIcon icon = new ImageIcon("Images/background.jpg");

		label = new JLabel(icon);

		world = new MarioWorld(this,1);
		setUpKeyBindings();
		setUpBackground();
		
	}
	
	private void setUpBackground() {

		try {

			audiostream = AudioSystem.getAudioInputStream(new File("Images/Game_of_Thrones_Extended_Theme.wav"));
			clip = AudioSystem.getClip();
			clip.open(audiostream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			play = ImageIO.read(new File("Images/play.gif"));
			bkgrnd = ImageIO.read(new File("Images/background.jpg"));
			bkgrnd1 = ImageIO.read(new File("Images/back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.BCKGRD_W = bkgrnd.getWidth(null);

	}

	private void setUpKeyBindings() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!ingame)
					ingame = true;
				world.ingame=true;
				world.mario.attack = true;
				int x = e.getX();
				int y = e.getY();
				if (x>168&&x<260&&y>439&&y<515&&world.worldtype==2&&world.score>=10){
					world.hover=true;
					if (!world.mario.weapon)
						world.score-=10;
					world.mario.weapon=true;
					
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed D"), "RightMove");
		this.getInputMap().put(KeyStroke.getKeyStroke("released D"), "RightStop");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed A"), "LeftMove");
		this.getInputMap().put(KeyStroke.getKeyStroke("released A"), "LeftStop");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed W"), "ENTER");
		this.getInputMap().put(KeyStroke.getKeyStroke("released W"), "NOENTER");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "JUMP");

		this.getActionMap().put("RightMove", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.mario.right();
			}

		});
		this.getActionMap().put("ENTER", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				world.mario.enter=true;
			}
			
		});
		this.getActionMap().put("NOENTER", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				world.mario.enter=false;
			}
			
		});		
		this.getActionMap().put("JUMP", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.mario.jump();
				if (world.mario.jumping == false) {

					world.mario.jumping = true;
					world.mario.jump1 = true;
				}
			}

		});
		this.getActionMap().put("LeftStop", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.mario.stop();
			}

		});
		this.getActionMap().put("RightStop", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.mario.stop();
			}

		});
		this.getActionMap().put("LeftMove", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.mario.left();
			}

		});
		this.requestFocusInWindow();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawBackground(g);
		
		if (ingame) {

			this.remove(label);
			world.draw(g);
		}

	}

	private void drawBackground(Graphics g) {
		// x+=step*this.compassHeading;
		// g.drawImage(this.bkgrnd, -100,0,1600, 820,null);
		
		this.add(label);
		label.setOpaque(true);

		label.validate();
	

		label.setVisible(true);

		
		this.validate();
		
		// BufferedImage rightView = bkgrnd.getSubimage(x , 0 , this.getWidth(),
		// this.bkgrnd.getHeight());

	}

}
