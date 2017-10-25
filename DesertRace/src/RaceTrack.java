import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RaceTrack {
	int x, y, z, carx;
	int cardx = 0;
	int dx = 0;
	int track = 0;
	private int h, w;
	private List<Moveable> obstacles = new ArrayList<Moveable>();
	private Timer timer;
	private GraphicsPanel gp;
	int score = 0;
	boolean playing = true;

	Clip anothaone, youloyal, appreciate, song, damn;
	BufferedImage img, img1, img2 = null;

	private int delay = 30;// delay starts off as 100 milliseconds

	public RaceTrack(GraphicsPanel graphicsPanel) throws MalformedURLException {
		z = 10;

		gp = graphicsPanel;

		setUpTrack();

		x = gp.WIDTH / 2;
		carx = x;
		y = gp.HEIGHT * 9 / 10;// 90% of height
		h = y / 9;// 10% of height
		w = x / 5;// 10% of width
		File file = new File("images/u_loyal.wav");
		File a = new File("images/i_appreciate_that.wav");
		File b = new File("images/Another_One.wav");
		File c = new File("images/song.wav");
		File d = new File("images/damn.wav");
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			AudioInputStream audio = AudioSystem.getAudioInputStream(b);
			AudioInputStream audio1 = AudioSystem.getAudioInputStream(c);
			AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(a);
			AudioInputStream dam = AudioSystem.getAudioInputStream(d);
			youloyal = AudioSystem.getClip();
			damn = AudioSystem.getClip();
			damn.open(dam);
			song = AudioSystem.getClip();
			song.open(audio1);
			appreciate = AudioSystem.getClip();
			anothaone = AudioSystem.getClip();
			anothaone.open(audio);

			youloyal.open(audioInputStream);
			appreciate.open(audioInputStream1);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		song.start();
		startTimer();
	}

	private void startTimer() {
		timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// what should happen each time the timer goes off?
				moveStuff();

				try {
					checkCollisions();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				checkSpawnStuff();
				gp.repaint();

			}

		});
		timer.start();

	}

	/*
	 * This method checks to see if it is time to spawn new things on the track
	 * Maybe it is time for new obstacles or power-ups to appear. Maybe a new
	 * section of track should be added to the top of the screen...
	 */
	protected void checkSpawnStuff() {
		if (z == 0) {
			Moveable m = new Moveable(400, y - 180);
			obstacles.add(m);
			z = 20;
		} else {
			z--;
		}

	}

	/*
	 * This method checks to see if any collisions have occurred. If the "car"
	 * has collided with an obstacle, then react. This could mean the round is
	 * over, or the car has crashed with another. So, maybe it slows down or
	 * spins out, or... If it collides with a power-up, maybe points are earned,
	 * etc...
	 */
	protected void checkCollisions() throws MalformedURLException {
		// TODO Auto-generated method stub
		for (int a = 0; a < obstacles.size(); a++) {
			if (obstacles.get(a).y > 730) {
				obstacles.remove(obstacles.get(a));
				score++;

			}
			boolean xc = false;
			boolean yc = false;
			if ((obstacles.get(a).x + obstacles.get(a).w > carx - 150
					&& obstacles.get(a).x + obstacles.get(a).w < carx - 45)
					|| (obstacles.get(a).x > carx - 150 && obstacles.get(a).x < carx - 45)) {
				xc = true;
			}
			if (obstacles.get(a).y > y) {
				yc = true;
			}
			if (xc && yc && obstacles.get(a).star == false && obstacles.get(a).damn == false) {
				if (Math.random() < .5) {
					song.stop();
					youloyal.start();
				} else {
					song.stop();
					appreciate.start();
				}
				timer.stop();
				JPanel panel = new JPanel();
				if (JOptionPane.showConfirmDialog(panel, "Score: " + score + " Play again?", "Game Over!",
						JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION)
					;

				new GraphicsFrame();

				{

				}

			} else if (xc && yc && (obstacles.get(a).star == true || obstacles.get(a).damn == true)) {
				score += 10;

				if (obstacles.get(a).star == true) {
					anothaone.setFramePosition(0);
					anothaone.start();

				}
				if (obstacles.get(a).damn) {
					anothaone.setFramePosition(0);
					damn.start();

				}
				obstacles.remove(a);

			}

		}

		if (x > 490 || x < 50) {
			dx = 0;
			cardx = 0;
		}

	}

	/*
	 * Objects in the game should be moved. Maybe some objects move more quickly
	 * or in different ways, so this is just generalized. Also, maybe some
	 * objects only move every other call, or every third or... Each moveable
	 * object determines what to do when asked to move
	 */
	protected void moveStuff() {
		// TODO Auto-generated method stub
		x += dx;// DON'T DO THIS FOR THE FINAL PRODUCT!!!!!
		carx += cardx;
		for (Moveable m : obstacles) {
			if (m.m == 1) {
				m.move();
			} else {
				m.moveother();
			}
		}
	}

	/*
	 * This method is called when the track is first set up. It may load up
	 * objects from a file or images for different cars, or objects, etc. This
	 * will be done only once.
	 */
	private void setUpTrack() {

		try {
			img = ImageIO.read(new File("images/road1.jpg"));
			img1 = ImageIO.read(new File("images/road12.jpg"));
			img2 = ImageIO.read(new File("images/prius.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void right() {
		// THIS WILL CHANGE!!!!! THE CODE TO MOVE THINGS RIGHT OR LEFT
		// DO NOT BELONG HERE!!!!! Perhaps the vehicle controlled by the
		// user is told to move right... MUCH BETTER SOLUTION
		System.out.println("Go Right!");
		System.out.println(x);

		dx = 10;
		cardx = -10;

		gp.repaint();
	}

	public void left() {
		// TODO Auto-generated method stub
		System.out.println("Go Left!");
		cardx = 10;
		dx = -10;

		gp.repaint();
	}

	public void draw(Graphics g) {

		System.out.println(track);
		if (track % 2 == 0) {
			g.drawImage(img, x - 500, y - 550, 1280, 700, null);
			track++;
		} else {
			g.drawImage(img1, x - 500, y - 550, 1280, 700, null);
			track++;
		}

		for (Moveable m : obstacles) {
			m.draw(g, x - 250, y - 620);
		}

		// Really, like we did in the Card game, when the RaceTrack is told
		// to draw itself, it should tell the object on the track to draw
		// themselves. This is way easier if those objects know where they are
		g.fill3DRect(400, 550, w, h, true);
		g.drawImage(img2, 350, 500, 220, 150, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dolce Vita", Font.PLAIN, 36));
		g.drawString("Score: " + score, 20, 40);
	}

	public void stop() {
		// TODO Auto-generated method stub
		cardx = 0;
		dx = 0;
	}

	public void mute() {
		// TODO Auto-generated method stub
		if (playing)
		{
			song.stop();
			playing = false;
		}
		else{
			song.start();
			playing = true;
		}
	}

}
