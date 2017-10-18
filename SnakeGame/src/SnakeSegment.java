import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public class SnakeSegment extends GameObject {
	Location a;
	private int direction;
	private int row;
	private int col;
	public SnakeSegment(Location l) {
		super(l);
		// TODO Auto-generated constructor stub
		a=l;
		row = l.getRow();
		col = l.getCol();
	}
	public int direction(int i){
		return i;
	}
	public SnakeSegment(Location l, Color c) {
		super(l, c);
		// TODO Auto-generated constructor stub
	}

	public SnakeSegment(Location l, Color c, Image i) {
		super(l, c, i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display(Graphics g, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move(){
		if (direction == 1){
			row--;
			
		}
		else if (direction ==2){
			col++;
		}
		else if (direction ==3){
			row++;
		}
		else if (direction ==4){
			col--;
		}
	}
	public int getRow(){
		return a.getRow();
	}
	public int getCol(){
		return a.getCol();
	}
}
