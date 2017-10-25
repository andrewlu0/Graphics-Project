import java.awt.GridLayout;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class HangMan {
	int lives = 5;
	int total = 0;
	String letter;
	String hidden;
	String guess = "";
	String overallguess;
	List<String> list2 = new ArrayList<String>();
	List<String> list = new ArrayList<String>();
	List<String> asdf = new ArrayList<String>();
	public HangMan() {
		loadDictionary();
		start();
		// set up the view (if necessary), including the body parts, Neil...
	}
	
	private void loadDictionary() {
		// TODO Auto-generated method stub
		File file = new File("wordsEn.txt");

		try {
			Scanner n = new Scanner(file);
			while (n.hasNext()){
				asdf.add(n.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		
		chooseWord();// different for two player or one player
		for (int x = 0;x<hidden.length();x++){
			guess += "_";
		}
		while(!won() && !lost()) {
			displayStatus();
			promptForGuessChoice();
			
			//guessLetter();// asks the user for a letter
			//guessWord();//asks the user if they want to guess the word
			
			//hitMiss();// checks to see if the letter matches or not
			
		}
		
		displayResults();
		boolean again = playAgain();
		if(again)
			start();
		else
			goodBye();
	}

	private void goodBye() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "LATA BITCH!");
		System.exit(0);
	}

	private boolean playAgain() {
		// TODO Auto-generated method stub
		list2.clear();
		list.clear();
		guess = "";
		JFrame frame = new JFrame();
		Object array[] = {
				"YAH"
				,"NAH"
		};
		int n =JOptionPane.showOptionDialog(frame, "Play again?", "Play again?", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE,null, array, array[0]);
		if (n == JOptionPane.YES_OPTION){
			return true;
		}
		else if (n== JOptionPane.NO_OPTION){
			return false;
		}
		else {
			return false;
		}
	}

	private void displayResults() {
		// TODO Auto-generated method stub
	}

	private void hitMiss() {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame();
		if (hidden.contains(letter)){
			JOptionPane.showMessageDialog(frame,"IT CONTAINS THE LETTER");
			for (int x = 0; x<hidden.length();x++){
				if (hidden.substring(x,x+1).equalsIgnoreCase(letter)){
					list2.set(x, letter);
					
				}
			}
//			guess = "";
//			for (int x = 0; x<hidden.length();x++){
//				if (hidden.substring(x,x+1).equalsIgnoreCase(letter)){
//					guess += letter+ " ";
//				}
//				else {
//					guess += "_";
//				}
//			}
//		
//			for (int x = 0;x<hidden.length();x++){
//				if (hidden.substring(x, x)==letter){
//					guess += letter;
//				}
//				else {
//					guess += "_";
//				}
//			}
		}
		else {
			lives --;
			JOptionPane.showMessageDialog(frame,"IT DOESN'T CONTAIN THE LETTER, You have " + lives + " lives left.");					
		}	
		if (!(list2.contains("_"))){
			
		}
	}

	private void guessWord() {
		// TODO Auto-generated method stub
		total++;
		guess = JOptionPane.showInputDialog("GUESS THE WORD");
		if (!(guess.equals(hidden))){
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "WRONG WORD");
			lives --;
			promptForGuessChoice();
		}
	}

	private void guessLetter() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		letter  = JOptionPane.showInputDialog("GUESS A LETTER");
		
		total ++;
		if (letter.length()>1){
			JOptionPane.showMessageDialog(frame, "thats not a letter");
			total --;
			letter = "";
			promptForGuessChoice();
		}
		hitMiss();
	}

	private void promptForGuessChoice() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		Object array[] = {
				"Letter"
				,"Word"
		};
		int n =JOptionPane.showOptionDialog(frame, "Guess letter or word?", "Letter or Word?", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE,null, array, array[0]);
		if (n == JOptionPane.YES_OPTION){
			guessLetter();
		}
		else if (n== JOptionPane.NO_OPTION){
			guessWord();
		}
		else {
			JOptionPane.showMessageDialog(frame,
					"wtf guess a letter or a word");
			promptForGuessChoice();
			
		}
		
	}

	private void displayStatus() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();

		JOptionPane.showMessageDialog(frame, "Your current guess  : " + list2);
	
	}

	private boolean lost() {
		// TODO Auto-generated method stub
		if (lives == 0){
			return true;
		}
		else {
			return false;
		}
	}

	private boolean won() {
		// TODO Auto-generated method stub
		if (!(list2.contains("_"))||guess.equals(hidden)){
			JFrame frame = new JFrame();
			if (total==1){
				JOptionPane.showMessageDialog(frame, "U WON WITH " + total + " GUESS!");
			}
			else{
				JOptionPane.showMessageDialog(frame, "U WON WITH " + total + " GUESSES!");
			}
			return true;
		}
		else{
			
			return false;}
	}

	private void chooseWord() {
		boolean onePlayer = true;
		// how many players?
//		JFrame n = new JFrame("Hangman 2.0");	
//		JPanel panel = new JPanel();
//		JLabel label = new JLabel("How many players?");
//		JButton button = new JButton();
//		JButton button2 = new JButton();
//		button.setText("1");
//		button2.setText("2");
//		panel.add(label);
//		panel.add(button);
//		panel.add(button2);
//		n.add(panel);
//		n.setSize(400,400);
//		n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		n.setVisible(true);
		JFrame frame = new JFrame();
		Object array[] = {
				"1"
				,"2"
		};
		int n =JOptionPane.showOptionDialog(frame, "How many players?", "Hangman", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE,null, array, array[0]);
		if (n == JOptionPane.YES_OPTION){
			onePlayer = true;
		}
		else if (n== JOptionPane.NO_OPTION){
			onePlayer = false;
		}
		else {
			JOptionPane.showMessageDialog(frame, "CHOOSE A DAMN NUMBER OF PLAYERS!");
			chooseWord();
		}
		if(onePlayer) {
			hidden = computerPick();
		}
		else{
			hidden = playerPick();
		}
		System.out.println(hidden);
		for (int x=0; x<hidden.length();x++){
			list2.add("_");
			list.add(hidden.substring(x,x+1));
		}
		System.out.println(list);
	}

	private String playerPick() {
		// TODO Auto-generated method stub
		
		String p =JOptionPane.showInputDialog("Word?");		
		System.out.println(p);
		return p;
	}

	private String computerPick() {
		// need a dictionary
		// randomly choose from the dictionary, making sure level is appropriate
		Random random = new Random();
		int index = random.nextInt(asdf.size());
		System.out.println(index);
		String w = asdf.get(index);
		return w;
	}

}
