import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public abstract class GameObject {
	
	private Location loc;
	private Color color;
	private Image image;
	
	public GameObject(Location l) {
		this(l,Color.black,null);
	}
	public GameObject(Location l, Color c) {
		this(l,c,null);
	}
	public GameObject(Location l, Color c, Image i) {
		loc = l;
		color = c;
		image = i;
	}
	public abstract void display(Graphics g, int x, int y, int w, int h);
	
	public void move() {
		// GameObjects do nothing by default
	}
	
}
