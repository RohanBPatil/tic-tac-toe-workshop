package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
	public static String[] board;
	
	public TicTacToe() {
		board = new String[10];
		for(int i = 0; i < board.length; i++)
			board[i] = "";
	}
	
	public String chooseChar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select one x or o");
		String playerChar = scanner.nextLine();
		return playerChar;
	}
	
	public void showBoard() {
		for(int i = 1; i < board.length; i++) {
			System.out.print(board[i] + "	");
			if(i % 3 == 0)
				System.out.println();
		}
	}
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		String playerChar;
		String compChar;
		
		while(true) {
			playerChar = game.chooseChar();
			compChar = (playerChar.equals("x"))? "o" : "x";
			if(playerChar.equals("o") || playerChar.equals("x"))
				break;
			System.out.println("Entered invalid character!!");
		}
		System.out.println("Your symbol is : "+playerChar);
		System.out.println("Computer's symbol is : " + compChar);
		
		game.showBoard();
	}
}
