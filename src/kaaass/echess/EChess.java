package kaaass.echess;

import kaaass.echess.util.PositionUtils;

public class EChess {

	public static void main(String[] args) {
		Chess.init();
		int[] org = { 2, 2 };
		for (int[] i : PositionUtils.directionToLine(org, 0))
			System.out.println(i[0] + ", " + i[1]);
	}

}
