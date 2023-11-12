package fourInARow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Linea {

	public static String messageColumnOutOfBounds = "Column out of bounds";
	public static String messageGameFinished = "Game finished";
	public static String messageFullColumn = "Column is full";
	public static String messageNotRedsTurn = "It's not red's turn to play";
	public static String messageNotBluesTurn = "It's not blue's turn to play";
	public static String messageNotAValidVariant = "Not a valid variant";
	public static String messageWinnerRed = "Winner: Red";
	public static String messageWinnerBlue = "Winner: Blue";
	public static String messageDraw = "Draw";

    private int height;
    private int base;
    private boolean finished;
	private Turn turn;
    private WinVariant winVariant;
	private List<List<Character>> board;

    public Linea(int base, int height, char variant) {
        this.base = base;
		this.height = height;
        finished = false;
		turn = new RedTurn();
        winVariant = WinVariant.getVariant(variant);
   
        board = IntStream.range(0, base)
                .mapToObj(i -> new ArrayList<Character>())
                .collect(Collectors.toList());

		}

	public void playRedAt(int column) {
		checkGameState();
		checkForColumnBounds(column);
		checkIfColumnIsFull(column);
		turn = turn.playRed(this, column);
	}

	public void playBlueAt(int column) {
		checkGameState();
		checkForColumnBounds(column);
		checkIfColumnIsFull(column);
		turn = turn.playBlue(this, column);
	}

	public void play(char player, int column) {
        board.get(column).add(player);
        checkEnd();
    }

	public void checkEnd() {
		finished = winVariant.checkWin(this, 'R') ||
				   winVariant.checkWin(this, 'B') ||
				   checkForDraw();
	}

	public boolean finished() {
		return finished;
	}

    boolean checkForDraw() {
    	return board.stream()
                .map(lista -> lista.size() == height)
                .reduce(true, (a, b) -> a && b);
    }

	public boolean checkForVertical(char player) {
		return IntStream.range(0, base)
				.anyMatch(column -> IntStream.range(0, height - 3)
						.anyMatch(row -> IntStream.range(0, 4)
								.allMatch(i -> getElementInPosition(column, row + i) == player)
						)
				);
	}

	public boolean checkForHorizontal(char player) {
		return IntStream.range(0, height)
				.anyMatch(row -> IntStream.range(0, base - 3)
						.anyMatch(column -> IntStream.range(0, 4)
								.allMatch(i -> getElementInPosition(column + i, row) == player)
						)
				);
	}

	public boolean ckeckForDiagonal (char player) {
		return IntStream.range(0, height)
				.anyMatch(row ->
						IntStream.range(0, base)
								.filter(column -> board.get(column).size() > row && board.get(column).get(row) == player)
								.anyMatch(column -> checkDiagonalUp(column, row, player) || checkDiagonalDown(column, row, player))
				);
	}

	private boolean checkDiagonalDown(int column, int row, char player) {
		int[] count = {0};
		int maxIterations = Math.min(base - column, row + 1);
		return IntStream.range(0, maxIterations)
				.anyMatch(i -> {
					int c = column + i;
					int r = row - i;
					count[0] = updateCountForDiagonal(c, r, player, count[0]);
					return count[0] == 4;
				});
	}

	private boolean checkDiagonalUp(int column, int row, char player) {
		int[] count = {0};
		int maxIterations = Math.min(base - column, height - row);
		return IntStream.range(0, maxIterations)
				.anyMatch(i -> {
					int c = column + i;
					int r = row + i;
					count[0] = updateCountForDiagonal(c, r, player, count[0]);
					return count[0] == 4;
				});
	}

	private int updateCountForDiagonal(int column, int row, char player, int count) {
		return (board.get(column).size() > row && board.get(column).get(row) == player) ? count + 1 : 0;
	}

	private void checkGameState(){
		Optional.of(finished)
				.filter(finished -> finished)
				.ifPresent(unused -> {
					throw new RuntimeException(messageGameFinished);
				});
	}

	private void checkForColumnBounds(int column){
		Stream.of(column)
				.filter(col -> col >= 0 && col < base)
				.findAny()
				.orElseThrow(() -> new RuntimeException(messageColumnOutOfBounds));
	}

	private void checkIfColumnIsFull(int column){
		Optional.of(board.get(column).size() >= height)
				.filter(isFull -> isFull)
				.ifPresent(isFull -> {
					throw new RuntimeException(messageFullColumn);
				});
	}

	public String show() {
		return IntStream.range(0, height)
				.mapToObj(row -> IntStream.range(0, base)
						.mapToObj(column -> getElementInPosition(column, row) + " ")
						.reduce("", (a, b) -> a + b)
				)
				.reduce("", (a, b) -> b + "\n" + a) + getWinner () + "\n" + getPositions();
	}

	private char getElementInPosition(int column, int row) {
		return (column < board.size() && row < board.get(column).size()) ? board.get(column).get(row) : '_';
	}

	public String getWinner () {
		if (winVariant.checkWin(this, 'R')) {
			return messageWinnerRed;
		}

		if (winVariant.checkWin(this, 'B')) {
			return messageWinnerBlue;
		}

		if (checkForDraw()) {
			return messageDraw;
		}

		return "";
	}

	public String getPositions() {
		return IntStream.range(0, base)
				.mapToObj(i -> i + " ")
				.reduce("", (a, b) -> a + b) + "\n";
	}

	public int getBase() {
		return base;
	}
}
