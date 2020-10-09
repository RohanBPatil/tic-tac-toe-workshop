package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
	public static String[] board;
	public static String playerChar, compChar;
	public static int countMoves = 0;

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
	public static void chooseChar(Scanner scanner) {
		while (true) {
			System.out.println("Select your symbol x or o : ");
			playerChar = scanner.nextLine();
			if (playerChar.equals("o") || playerChar.equals("x")) {
				compChar = (playerChar.equals("x")) ? "o" : "x";
				break;
			}
			System.out.println("Entered invalid character!!");
		}
		System.out.println("Your symbol is : " + playerChar);
		System.out.println("Computer's symbol is : " + compChar);
	}

	/**
	 * UC 3
	 */
	public static void showBoard() {
		System.out.println("-----");
		for (int i = 1; i < board.length; i++) {
			System.out.print(board[i]);
			if (i % 3 == 0) {
				System.out.println("\n-----");
				continue;
			}
			System.out.print("|");
		}
	}

	/**
	 * UC 4 and 5
	 * 
	 * @param position
	 * @param playerChar
	 * @return
	 */
	public static void makeMove(Scanner scanner) {
		while (true) {
			System.out.println("Enter the position to write your symbol");
			int position = scanner.nextInt();
			if (position < 1 || position > 9) {
				System.out.println("Invalid Position");
				continue;
			}
			if (board[position].equals(" ")) {
				board[position] = playerChar;
				System.out.println("Your symbol entered successfully");
				countMoves++;
				showBoard();
				break;
			} else {
				System.out.println("Position is already filled.");
				showBoard();
			}
		}
	}

	public static void compMove() {
		while (true) {
			int position = (int) Math.floor(Math.random() * 10) % 9 + 1;
			if (board[position].equals(" ")) {
				board[position] = compChar;
				System.out.println("Computer made move.");
				countMoves++;
				showBoard();
				break;
			}
		}
	}

	/**
	 * UC 6
	 * 
	 * @return
	 */
	public static int toss() {
		return (int) Math.floor(Math.random() * 10) % 2;
	}

	/**
	 * UC 7
	 * 
	 * @param symbol
	 * @return
	 */
	public static int checkWinner(String symbol) {
		if (countMoves == 9)
			return 2;
		if ((symbol.equals(board[1]) && symbol.equals(board[2]) && symbol.equals(board[3]))
				|| (symbol.equals(board[4]) && symbol.equals(board[5]) && symbol.equals(board[6]))
				|| (symbol.equals(board[7]) && symbol.equals(board[8]) && symbol.equals(board[9]))
				|| (symbol.equals(board[1]) && symbol.equals(board[4]) && symbol.equals(board[7]))
				|| (symbol.equals(board[2]) && symbol.equals(board[5]) && symbol.equals(board[8]))
				|| (symbol.equals(board[3]) && symbol.equals(board[6]) && symbol.equals(board[9]))
				|| (symbol.equals(board[1]) && symbol.equals(board[5]) && symbol.equals(board[9]))
				|| (symbol.equals(board[3]) && symbol.equals(board[5]) && symbol.equals(board[7]))) {
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) {
		final int playerNum = 0;
//		final int compNum = 1;
		Scanner scanner = new Scanner(System.in);

		new TicTacToe();
		chooseChar(scanner);
		int first = toss();
		if (first == playerNum)
			System.out.println("You play first");
		else
			System.out.println("Computer will play first");

		while (true) {
			if (first % 2 == playerNum) {
				makeMove(scanner);
				first++;
				if (checkWinner(playerChar) == 1) {
					System.out.println("Congratulations!! You won this game");
					break;
				} else if (checkWinner(playerChar) == 2) {
					System.out.println("No one won this game");
				}
			}

			else {
				compMove();
				first++;
				if (checkWinner(compChar) == 1) {
					System.out.println("COMPUTER won this game");
					break;
				} else if (checkWinner(compChar) == 2) {
					System.out.println("No one won this game");
				}
			}
		}
	}
}
