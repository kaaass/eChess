package kaaass.echess.util;

import java.util.ArrayList;
import java.util.List;

public class PositionUtils {
	public static List<int[]> directionToLine(int[] position, int direction) {
		/*
		 * direction: 0 1 2 3 you 4 5 6 7
		 */
		List<int[]> result = new ArrayList<int[]>();
		int[] temp = position;
		switch (direction) {
		case 1:
		case 6:
			while (true) {
				temp[1] += direction == 1 ? 1 : -1;
				if (!inside(temp))
					break;
				if (!temp.clone().equals(position))
					result.add(temp.clone());
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
			}
			break;
		}
		return result;
	}

	public static boolean inside(int[] p) {
		return p[0] >= 0 && p[0] <= 7 && p[1] >= 0 && p[1] <= 7;
	}
}
