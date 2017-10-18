
public class Location {

	private int row, col;
	
	public Location(int r, int c) {
		row = r;
		col = c;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Location))// o is something other than Location
			return false;
		Location other = (Location)o;
		
		return other.getRow()==this.getRow()&&other.getCol()==this.getCol();
	}
}
