package kaaass.echess.util;

import java.util.ArrayList;
import java.util.List;

import kaaass.echess.Chessboard;

public class PositionUtils {
	public static List<int[]> directionToLine(int[] position, int direction) {
		/*
		 * direction: 
		 * 0 1 2 
		 * 3 you 4 
		 * 5 6 7
		 */
		List<int[]> result = new ArrayList<int[]>();
		int[] temp = position.clone();
		switch (direction) {
		case 1:
		case 6:
			while (true) {
				temp[1] += direction == 1 ? 1 : -1;
				if (!inside(temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
				if (!(Chessboard.getChessByPosition(temp[0], temp[1]) == null))
					break;
			}
			break;
		case 3:
		case 4:
			while (true) {
				temp[0] += direction == 4 ? 1 : -1;
				if (!inside(temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
				if (!(Chessboard.getChessByPosition(temp[0], temp[1]) == null))
					break;
			}
			break;
		case 0:
		case 7:
			while (true) {
				temp[0] += direction == 7 ? 1 : -1;
				temp[1] += direction == 0 ? 1 : -1;
				if (!inside(temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
				if (!(Chessboard.getChessByPosition(temp[0], temp[1]) == null))
					break;
			}
			break;
		case 2:
		case 5:
			while (true) {
				temp[0] += direction == 2 ? 1 : -1;
				temp[1] += direction == 2 ? 1 : -1;
				if (!inside(temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
				if (!(Chessboard.getChessByPosition(temp[0], temp[1]) == null))
					break;
			}
			break;
		}
		return result;
	}

	public static List<int[]> getPos(int[] p, int[] d) {
		List<int[]> result = new ArrayList<int[]>();
		int[] temp;
		for (int i : d) {
			temp = p.clone();
			switch (i) {
			case 1:
			case 6:
				temp[1] += i == 1 ? 1 : -1;
				if (inside(temp))
					result.add(temp.clone());
				break;
			case 3:
			case 4:
				temp[0] += i == 4 ? 1 : -1;
				if (inside(temp))
					result.add(temp.clone());
				break;
			case 0:
			case 7:
				temp[0] += i == 7 ? 1 : -1;
				temp[1] += i == 0 ? 1 : -1;
				if (inside(temp))
					result.add(temp.clone());
				break;
			case 2:
			case 5:
				temp[0] += i == 2 ? 1 : -1;
				temp[1] += i == 2 ? 1 : -1;
				if (inside(temp))
					result.add(temp.clone());
				break;
			}
		}
		return result;
	}

	public static boolean inside(int[] p) {
		if (p == null)
			return false;
		return p[0] >= 0 && p[0] <= 7 && p[1] >= 0 && p[1] <= 7;
	}
}
