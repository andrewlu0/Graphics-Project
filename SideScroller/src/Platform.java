import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Platform {
	
	int x,y,w,h;
	int type;
	BufferedImage plat1,plat2;
	protected boolean draw1=true;
	String typeofportal;
	BufferedImage plat3;
	public Platform(int xc, int yc, int wc, int hc,int type){
		this.type=type;
		
		try {
			plat1= ImageIO.read(new File("Images/plat1.png"));
			plat2 = ImageIO.read(new File("Images/portal.png"));
			plat3 = ImageIO.read(new File("Images/plat3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x=xc;
		y=yc;
		w=wc;
		h=hc;
		
	}
	
	public void draw(Graphics g) {
		if (type==1){
			
				g.drawImage(plat1, x*20,y,w,20,null);
			
		}
		else if (type==2){
			//g.fill3DRect(x,y,w,h,true);
		}
		else if (type==3){
			
			g.drawImage(plat3, x*20,y,w,20,null);
			
		}
			
	}
	
}
