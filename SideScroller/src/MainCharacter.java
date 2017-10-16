import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class MainCharacter {
	int x, y, t;
	long starttime;
	int o = y;
	int dx = 0;
	int direction = 0;
	boolean enter;
	boolean jumping, jump1, falling, fall,weapon;
	double dy = 0;

	final int velocity = 1;
	BufferedImage img, img2, attack1, attack2, enemy1, right1, left1,img1,img21,attack11,attack21,right11,left11;
	int height, width;

	boolean explode = false;
	protected boolean gold = false;;
	String s;
	boolean attack;
	int atk = 0;
	private boolean moving;

	public MainCharacter(int xc, int yc, String n) {
		s = n;
		x = xc;
		y = yc;
		height = 20;
		width = 20;

		try {
			right11 = ImageIO.read(new File("Images/right11.png"));
			right1 = ImageIO.read(new File("Images/right1.png"));
			left11 = ImageIO.read(new File("Images/left11.png"));
			img1 = ImageIO.read(new File("Images/default_000001.png"));
			left1 = ImageIO.read(new File("Images/left1.png"));
			attack21 = ImageIO.read(new File("Images/attackleft_000001.png"));
			attack2 = ImageIO.read(new File("Images/attackleft_00000.png"));
			img = ImageIO.read(new File("Images/default_00000.png"));
			img21 = ImageIO.read(new File("Images/defaultleft_002561.png"));
			img2 = ImageIO.read(new File("Images/defaultleft_00256.png"));
			attack1 = ImageIO.read(new File("Images/attack_00000.png"));
			attack11 = ImageIO.read(new File("Images/attack_000001.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (attack) {

			if (direction == 0)
				if (!weapon)
				g.drawImage(attack1, x, y - 20, 50, 50, null);
				else
					g.drawImage(attack11, x, y - 47, 100, 90, null);
			if (direction == 1)
				if (!weapon)
				g.drawImage(attack2, x, y - 20, 50, 50, null);
				else
					g.drawImage(attack21, x-40, y - 47, 100, 90, null);
			atk++;
			if (atk == 4) {
				attack = false;
				atk = 0;
			}
		} else if (!attack && direction == 0 && !moving )
			if (!weapon)
			g.drawImage(img, x, y - 27, 60, 60, null);
			else
			g.drawImage(img1, x,y-52,105,95,null);
		else if (!attack && direction == 1 && !moving)
			if (!weapon)
			g.drawImage(img2, x, y - 27, 60, 60, null);
			else
			g.drawImage(img21, x-40, y - 52, 105, 95, null);
		else if (!attack && direction == 0 && moving) {
			if (!weapon)
			g.drawImage(right1, x, y - 27, 60, 60, null);
			else
			g.drawImage(right11, x, y - 56, 110, 100, null);
		} else if (!attack && direction == 1 && moving) {
			if (!weapon)
			g.drawImage(left1, x, y - 27, 60, 60, null);
			else
				g.drawImage(left11, x-40, y - 56, 110, 100, null);	
		}

	}

	public void right() {
		// TODO Auto-generated method stub
		dx = 10;
		moving = true;
		direction = 0;
	}

	public void stop() {
		// TODO Auto-generated method stub
		dx = 0;
		moving = false;
	}

	public void left() {
		// TODO Auto-generated method stub
		dx = -10;
		moving = true;
		direction = 1;
	}

	public void move() {
		// TODO Auto-generated method stub

		if (x + dx > -10 && x + dx < 1350)
			x += dx;
		if (jump1) {
			t = 0;
			o = y;
			jump1 = false;
		}
		if (jumping && (y <= o || t == 0 || falling)) {
			int current = y;
			if ((int) (y - 40 + 7 * t) <= o || falling) {
				y = (int) (y - 40 + 7 * t);
				if (y > current) {
					falling = true;
				}
			}
			t++;
		}
	}

	public void jump() {

	}

	public boolean within(Platform d) {
		// TODO Auto-generated method stub
		return ((this.x + this.width > d.x && this.x + this.width < d.w + d.x) || (this.x < d.x + d.w && this.x > d.x));
	}

	public boolean isOnPlatform(List<Platform> platforms) {
		// TODO Auto-generated method stub
		for (Platform d : platforms) {
			if (((this.x + this.width > d.x && this.x + this.width < d.w + d.x) || (this.x < d.x + d.w && this.x > d.x))
					&& (y == d.y - d.h)) {
				return true;
			}
		}
		return false;
	}

	public void explode() {
		// TODO Auto-generated method stub
		explode = true;
	}

}
