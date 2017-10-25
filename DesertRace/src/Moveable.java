import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Moveable {
	/// What do moveable things need to know about themselves???
	int x, y, w, h, m;
	BufferedImage weed, cactus, star1,van;
	int m1, m2;
	int d;
	boolean star = false;
	boolean damn = false;
	public Moveable(int xc, int yc) {
		try {
			weed = ImageIO.read(new File("images/tumbleweed_trans.gif"));
			cactus = ImageIO.read(new File("images/cactus.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = xc;
		y = yc;
		w = 5;
		h = 5;
		Random random = new Random();
		m1 = random.nextInt(14);
		m2 = random.nextInt(5);
		if (Math.random() < .5) {
			m = 1;

		} else {
			m = 2;

		}
		if (Math.random() < .5) {
			d = 1;

		} else {
			d = 2;

		}
		if (Math.random() < .1) {
			star = true;
			try {
				star1 = ImageIO.read(new File("images/dj.png"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Math.random() < .1) {
			damn = true;
			try {
				van = ImageIO.read(new File("images/white.png"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void move() {

		x += m1 + (x / 200);
		y += 1 + (y / 50);
		w += 2 + (w / 100);
		if (d == 1 && star == false) {
			h += 5 + (h / 50);
		} else {
			h += 2 + (h / 50);
		}

	}

	public void moveother() {

		x -= m1 + (x / 200);
		y += 1 + (y / 50);
		w += 2 + (w / 100);
		if (d == 1 && star == false) {
			h += 5 + (h / 50);
		} else {
			h += 2 + (h / 50);
		}

	}

	public void draw(Graphics g, int xc, int yc) {
		// TODO Auto-generated method stub
		if (d == 1 && star == false && damn == false) {
			g.drawImage(cactus, xc + x, yc + y - 100, w + 30, h + 100, null);
		} else if (d == 2 && star == false && damn ==false) {
			g.drawImage(weed, xc + x, yc + y, w + 20, h + 20, null);
		} else if ((d == 1 || d == 2) && damn == false) {
			g.drawImage(star1, xc + x, yc + y, w + 30, h + 30, null);
		}
		else{
			g.drawImage(van, xc+x, yc+y,w+30,h+30,null);
		}

		// g.fill3DRect(xc+x, yc+y, w, h, true);
	}

}
