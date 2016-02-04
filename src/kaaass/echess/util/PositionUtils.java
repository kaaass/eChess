package kaaass.echess.util;

import java.util.ArrayList;
import java.util.List;

import kaaass.echess.Chess;
import kaaass.echess.Chessboard;

public class PositionUtils {
	public static List<int[]> directionToLine(Chess self, int[] position,
			int direction) {
		/*
		 * direction: 0 1 2 3 you 4 5 6 7
		 */
		List<int[]> result = new ArrayList<int[]>();
		int[] temp = position.clone();
		switch (direction) {
		case 1:
		case 6:
			while (true) {
				temp[1] += direction == 1 ? 1 : -1;
				if (!isPosStandable(self, temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
			}
			break;
		case 3:
		case 4:
			while (true) {
				temp[0] += direction == 4 ? 1 : -1;
				if (!isPosStandable(self, temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
			}
			break;
		case 0:
		case 7:
			while (true) {
				temp[0] += direction == 7 ? 1 : -1;
				temp[1] += direction == 0 ? 1 : -1;
				if (!isPosStandable(self, temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
			}
			break;
		case 2:
		case 5:
			while (true) {
				temp[0] += direction == 2 ? 1 : -1;
				temp[1] += direction == 2 ? 1 : -1;
				if (!isPosStandable(self, temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
			}
			break;
		}
		return result;
	}

	public static List<int[]> getPos(Chess self, int[] p, int[] d) {
		List<int[]> result = new ArrayList<int[]>();
		int[] temp;
		for (int i : d) {
			temp = p.clone();
			switch (i) {
			case 1:
			case 6:
				temp[1] += i == 1 ? 1 : -1;
				if (isPosStandable(self, temp))
					result.add(temp.clone());
				break;
			case 3:
			case 4:
				temp[0] += i == 4 ? 1 : -1;
				if (isPosStandable(self, temp))
					result.add(temp.clone());
				break;
			case 0:
			case 7:
				temp[0] += i == 7 ? 1 : -1;
				temp[1] += i == 0 ? 1 : -1;
				if (isPosStandable(self, temp))
					result.add(temp.clone());
				break;
			case 2:
			case 5:
				temp[0] += i == 2 ? 1 : -1;
				temp[1] += i == 2 ? 1 : -1;
				if (isPosStandable(self, temp))
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

	public static boolean isPosStandable(Chess self, int[] p) {
		if (inside(p) && self != null) {
			Chess c = Chessboard.getChessByPosition(p[0], p[1]);
			if (c != null) {
				return c.isBlack() != self.isBlack();
			}
			return true;
		}
		return false;
	}

	public static List<int[]> getDangerous() {
		// no matter there is a chess
		return null;
	}

	public static List<int[]> attackKings(Chess shooter) {
		// to be finished.
		// should contain shooter's position
		return null;
	}
}
