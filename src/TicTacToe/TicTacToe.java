package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
	public static String[] board;

	/**
	 * Constructor
	 */
	public TicTacToe() {
		board = new String[10];
		for (int i = 0; i < board.length; i++)
			board[i] = " ";
	}

	/**
	 * UC 2
	 */
	public String chooseChar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select one x or o");
		String playerChar = scanner.nextLine();
		return playerChar;
	}

	/**
	 * UC 3
	 */
	public void showBoard() {
		for (int i = 1; i < board.length; i++) {
			System.out.print("|" + board[i]);
			if (i % 3 == 0) {
				System.out.print("|");
				System.out.println("\n_______\n");
			}
		}
	}
	
	/**
	 * UC 4
	 * @param position
	 * @param playerChar
	 * @return
	 */
	public boolean makeMove(int position, String playerChar) {
		if(board[position].equals(" ")) {
			board[position] = playerChar;
			return true;
		}
		else {
			System.out.println("Position is already filled.");
			return false;
		}
	}

	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		String playerChar;
		String compChar;
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			playerChar = game.chooseChar();
			compChar = (playerChar.equals("x")) ? "o" : "x";
			if (playerChar.equals("o") || playerChar.equals("x"))
				break;
			System.out.println("Entered invalid character!!");
		}
		System.out.println("Your symbol is : " + playerChar);
		System.out.println("Computer's symbol is : " + compChar);

		game.showBoard();
		
		while(true) {
			System.out.println("Enter the position to write your symbol");
			int position = scanner.nextInt();
			if(position < 1 || position > 9) {
				System.out.println("Invalid Position");
				continue;
			}
			if(game.makeMove(position, playerChar)) {
				game.showBoard();
				break;
			}
		}
		
		
		scanner.close();
	}
}
