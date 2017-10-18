import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Snake {
	List<SnakeSegment> list;
	private int direction;
	public Snake() {
		// TODO Auto-generated constructor stub
		List<SnakeSegment> a = new ArrayList<SnakeSegment>();
		for (int x = 0; x<8; x++){
			Location d = new Location(x,7);
			SnakeSegment one = new SnakeSegment(d);
			a.add(one);
		}
		list = a;
	}
	public void turnRight() {
		// TODO Auto-generated method stub
		if (!(direction==4)){
			direction = 2;
		}
	}
	public void turnLeft() {
		// TODO Auto-generated method stub
		if (!(direction==2)){
			direction = 4;
		}
	}
	public void turnUp() {
		// TODO Auto-generated method stub
		if (!(direction==3)){
			direction = 1;
		}
	}
	public void turnDown() {
		// TODO Auto-generated method stub
		if (!(direction==1)){
			direction = 3;
		}
	}

	public void move() {
		// TODO Auto-generated method stub
	
			list.get(list.size()-1).move();
			list.add(0, list.remove(list.size()-1));
		
	}
	public void draw(Graphics g){
		g.setColor(Color.RED);
		for (SnakeSegment sp: list){
			int c = sp.getCol();
			int r = sp.getRow();
			g.fillRect(c, r, 1, 1);
			
		}
	}
}
