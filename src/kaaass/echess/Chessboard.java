package kaaass.echess;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {
	public static Chessboard gameBoard;

	private List<Chess> black = new ArrayList<Chess>();
	private List<Chess> white = new ArrayList<Chess>();

	private Chessboard() {
	}

	public static Chessboard getDefault() {
		Chessboard cb = new Chessboard();
		// White chess
		cb.white.add(Chess.point(Chess.king, 4, 0));
		cb.white.add(Chess.point(Chess.queen, 3, 0));
		cb.white.add(Chess.point(Chess.rook, 0, 0));
		cb.white.add(Chess.point(Chess.rook, 7, 0));
		cb.white.add(Chess.point(Chess.bishop, 2, 0));
		cb.white.add(Chess.point(Chess.bishop, 5, 0));
		cb.white.add(Chess.point(Chess.knight, 1, 0));
		cb.white.add(Chess.point(Chess.knight, 6, 0));
		for (int i = 0; i < 8; i++)
			cb.white.add(Chess.point(Chess.pawn, i, 1));
		// Black chess
		cb.black.add(Chess.point(Chess.king, 4, 7));
		cb.black.add(Chess.point(Chess.queen, 3, 7));
		cb.black.add(Chess.point(Chess.rook, 0, 7));
		cb.black.add(Chess.point(Chess.rook, 7, 7));
		cb.black.add(Chess.point(Chess.bishop, 2, 7));
		cb.black.add(Chess.point(Chess.bishop, 5, 7));
		cb.black.add(Chess.point(Chess.knight, 1, 7));
		cb.black.add(Chess.point(Chess.knight, 6, 7));
		for (int i = 0; i < 8; i++)
			cb.black.add(Chess.point(Chess.pawn, i, 6));
		return cb;
	}

	public static Chess getChessByPosition(int x, int y) {
		int i = gameBoard.black.indexOf(Chess.point(x, y));
		if (i < 0) {
			i = gameBoard.white.indexOf(Chess.point(x, y));
			if (i < 0)
				return null;
			return gameBoard.white.get(i);
		} else {
			return gameBoard.black.get(i);
		}
	}
}
