import javax.swing.JFrame;
import javax.swing.Timer;
public class GameLaunch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameLaunch.launch();
	}

	private static void launch() {
		// TODO Auto-generated method stub
		 SnakeBoard board = new SnakeBoard();
		 
		 JFrame frame = new JFrame();
			
			board.start();
	}

}
