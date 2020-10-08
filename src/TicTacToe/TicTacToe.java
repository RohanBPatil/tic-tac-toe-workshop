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
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		String playerChar = game.chooseChar();
		String compChar = (playerChar.equals("x"))? "o" : "x";
		System.out.println(compChar);
	}
}
