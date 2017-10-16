import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Moveable {
	/// What do moveable things need to know about themselves???
	int x, y, w, h, dx;
	BufferedImage img, imggold, coin,boi,boi1,health1;
	public boolean gold;
	int type;
	int health;
	int deathtimer = 0;
	boolean right;
	boolean dying;
	boolean coin1 = false;
	boolean jump1;
	boolean jumping;
	boolean falling;
	int o;
	int t;

	public Moveable(int xc, int yc) {
		if (Math.random() < .5) {
			right = true;
		}
		if (type==1){
			health=1;
		}
		else health = 250;
		dx = 4;
		x = xc;
		y = yc;
		w = 20;
		h = 20;
		try {
			health1 = ImageIO.read(new File("Images/health.jpg"));
			boi1 =  ImageIO.read(new File("Images/dat boi1.png"));
			boi= ImageIO.read(new File("Images/dat boi.png"));
			img = ImageIO.read(new File("Images/enemy1_00000.png"));
			imggold = ImageIO.read(new File("Images/enemydie_00000.png"));
			coin = ImageIO.read((new File("Images/coin_00000.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move() {
		if (!dying) {
			if (right)
				x += dx;
			else
				x -= dx;
		}
		if (dying) {
			deathtimer++;
		}
		if (coin1){

			if (jump1){
				t=0;
				o =y;
				jump1= false;
			}
			jumping = true;
			if (jumping&&(y<=o||t==0||falling)){
			
				int current = y;
				if ((int) (y-40+7*t)<=o||falling){
					
				
					y = (int) (y-40+7*t);
					if (y>current){
						
						
				
						falling=true;
					}
					
				}
				
				
				
				t++;
			}
			
		}
		
	}

	public void draw(Graphics g) {
		if (type == 1 && !dying && !coin1) {
			g.drawImage(img, x, y, w, h, null);
		} else if (type == 1 && dying && !coin1) {
			g.drawImage(imggold, x, y, w, h, null);
		} else if (coin1) {
			g.drawImage(coin, x, y, w, h, null);
		}
		else if (type==2 && !right){
			g.drawImage(boi, x,y,300,450,null);
			for (int c=0;c<health;c++){
				g.drawImage(health1, x+c,y-30,1,10,null);
			}
	
		}
		else if (type==2 &&right){
			g.drawImage(boi1, x,y,300,450,null);
			for (int c=0;c<health;c++){
				g.drawImage(health1, x+c,y-30,1,10,null);
			}
	
		}
		

	}
}
