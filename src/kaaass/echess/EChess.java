package kaaass.echess;

import kaaass.echess.util.PositionUtils;

public class EChess {

	public static int nowSide = -1; // 0 for black, 1 for white, -1 for not
									// gaming.

	public static void main(String[] args) {
		Chess.init();
		System.out.println("== Direction test ==");
		int[] org = { 2, 1 };
		for (int[] i : PositionUtils.directionToLine(
				Chessboard.getChessByPosition(org[0], org[1]), org, 2))
			System.out.println(i[0] + ", " + i[1]);
		System.out.println("== In passing test ==");
		Chessboard.getChessByPosition(0, 6).moveTo(0, 4);
		Chessboard.getChessByPosition(1, 1).moveTo(1, 4);// Just for test ^-^
		for (int[] i : Chessboard.getChessByPosition(1, 4).getLegalPosition())
			System.out.println(i[0] + ", " + i[1]);
		System.out.println("== Knight test ==");
		Chessboard.getChessByPosition(0, 1).moveTo(0, 2);
		for (int[] i : Chessboard.getChessByPosition(1, 0).getLegalPosition())
			System.out.println(i[0] + ", " + i[1]);
		System.out.println("== Bishop test ==");
		for (int[] i : Chessboard.getChessByPosition(2, 0).getLegalPosition())
			System.out.println(i[0] + ", " + i[1]);
	}

}
