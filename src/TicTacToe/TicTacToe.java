package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
	public static String[] board;
	public static String playerChar, compChar;
	public static int countMoves = 0;

	/**
	 * UC 1 creating board with empty spaces and number of moves will be 0 initially
	 */
	public TicTacToe() {
		board = new String[10];
		for (int i = 0; i < board.length; i++)
			board[i] = " ";
		countMoves = 0;
	}

	/**
	 * UC 2 allowing player to choose character
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
	 * UC 3 showing current status of board
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
	 * UC 4 ability for user to make a desired move
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
			if (isFreeSpace(board, position)) {
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

	/**
	 * UC 5 checking if the position is empty or not
	 * 
	 * @param position
	 * @return
	 */
	public static boolean isFreeSpace(String[] checkBoard, int position) {
		if (checkBoard[position].equals(" "))
			return true;
		return false;
	}

	/**
	 * UC 6 tossing to decide who will play first
	 * 
	 * @return
	 */
	public static boolean toss() {
		final int HEADS = 0;
//		final int TAILS = 1;
		int toss = (int) Math.floor(Math.random() * 10) % 2;
		System.out.println(
				"Tossing......\nIf HEADS comes, you will play first and if TAILS comes, computer will play first.");
		if (toss == HEADS) {
			System.out.println("HEADS it is. So, you play first");
			return true;
		} else {
			System.out.println("TAILS it is. So, computer will play first");
			return false;
		}
	}

	/**
	 * UC 7 checking for win or tie or change turn
	 * 
	 * @param checkBoard
	 * @param symbol
	 * @return
	 */
	public static int checkWinner(String[] checkBoard, String symbol) {
		if ((symbol.equals(checkBoard[1]) && symbol.equals(checkBoard[2]) && symbol.equals(checkBoard[3]))
				|| (symbol.equals(checkBoard[4]) && symbol.equals(checkBoard[5]) && symbol.equals(checkBoard[6]))
				|| (symbol.equals(checkBoard[7]) && symbol.equals(checkBoard[8]) && symbol.equals(checkBoard[9]))
				|| (symbol.equals(checkBoard[1]) && symbol.equals(checkBoard[4]) && symbol.equals(checkBoard[7]))
				|| (symbol.equals(checkBoard[2]) && symbol.equals(checkBoard[5]) && symbol.equals(checkBoard[8]))
				|| (symbol.equals(checkBoard[3]) && symbol.equals(checkBoard[6]) && symbol.equals(checkBoard[9]))
				|| (symbol.equals(checkBoard[1]) && symbol.equals(checkBoard[5]) && symbol.equals(checkBoard[9]))
				|| (symbol.equals(checkBoard[3]) && symbol.equals(checkBoard[5]) && symbol.equals(checkBoard[7]))) {
			return 1;
		}
		if (countMoves == 9)
			return 2;
		return 0;
	}

	/**
	 * UC 8 checking if there is any wining position or not
	 * 
	 * @return
	 */
	public static int winPosition(String symbol) {
		String[] copyBoard = board.clone();

		for (int i = 1; i < copyBoard.length; i++) {
			if (isFreeSpace(copyBoard, i)) {
				copyBoard[i] = symbol;
				if (checkWinner(copyBoard, symbol) == 1) {
					return i;
				} else
					copyBoard[i] = " ";
			}
		}
		return 0;
	}

	/**
	 * UC 8 checking if computer can win and returning wining position of computer
	 * 
	 * @return
	 */
	public static int checkCompWin() {
		return winPosition(compChar);
	}

	/**
	 * UC 9 checking if player can win and returning wining position for player
	 * 
	 * @return
	 */
	public static int checkPlayerWin() {
		return winPosition(playerChar);
	}

	/**
	 * UC 10 check for available corner
	 * 
	 * @return
	 */
	public static int availableCorner() {
		int[] corners = { 1, 3, 7, 9 };
		int corner = 0;
		// counting number of empty corners
		int countEmptyCorners = 0;
		for (int i = 0; i < corners.length; i++) {
			if (isFreeSpace(board, corners[i])) {
				countEmptyCorners++;
			}
		}

		if (countEmptyCorners > 0) {
			while (true) {
				corner = corners[(int) Math.floor(Math.random() * 10) % 4];
				if (isFreeSpace(board, corner))
					break;
			}
		}

		return corner;
	}

	/**
	 * UC 11 selecting center if available or side
	 * 
	 * @return
	 */
	public static int centreOrSide() {
		int position = 5;
		int[] sides = { 2, 4, 6, 8 };
		if (isFreeSpace(board, position))
			return position;
		else {
			while (true) {
				position = sides[(int) Math.floor(Math.random() * 10) % 4];
				if (isFreeSpace(board, position))
					break;
			}
		}

		return position;
	}

	/**
	 * modified for UC 10, UC 11, UC 13
	 */
	public static void compMove() {
		int nextMove = 0;

		int winPosComp = checkCompWin();
		int winPosPlayer = checkPlayerWin();

		if (winPosComp != 0) {
			nextMove = winPosComp;
		} else if (winPosPlayer != 0) { // computer will choose that position only if computer is not wining
			nextMove = winPosPlayer;
		} else {
			int corner = availableCorner();
			if (corner != 0) {
				nextMove = corner;
			} else {
				nextMove = centreOrSide();
			}
		}

		board[nextMove] = compChar;
		System.out.println("Computer made its move.");
		countMoves++;
		showBoard();

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String anotherGame;

		do {
			new TicTacToe();
			chooseChar(scanner);
			boolean chance = toss();
			showBoard();

			while (true) {
				if (chance) {
					makeMove(scanner);
					chance = false;
				}

				else {
					compMove();
					chance = true;
				}

				if (checkWinner(board, playerChar) == 1) {
					System.out.println("Congratulations!! You won this game");
					break;
				} else if (checkWinner(board, compChar) == 1) {
					System.out.println("COMPUTER won this game");
					break;
				} else if (countMoves == 9) {
					System.out.println("No one won this game");
					break;
				}
			}
			scanner.nextLine();
			System.out.println("DO YOU WANT TO PLAY ANOTHER GAME?  YES/NO");
			anotherGame = scanner.nextLine();
		} while (anotherGame.equalsIgnoreCase("YES"));
	}
}
