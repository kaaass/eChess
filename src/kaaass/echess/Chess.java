package kaaass.echess;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kaaass.echess.util.ArrayUtils;
import kaaass.echess.util.PositionUtils;

public class Chess implements Cloneable {

	public static Chess king;
	public static Chess queen;
	public static Chess rook;
	public static Chess knight;
	public static Chess bishop;
	public static Chess pawn;

	private static final int PAWN = 0;
	private static final int CROSS = 1;
	private static final int BISHOP = 2;
	private static final int KNIGHT = 3;

	private String name;
	private int score = 1;
	private int[] action;
	private int[] attack;
	private boolean limit;
	private boolean isBlack;

	private int x = 0;
	private int y = 0;
	private boolean hasMove = false;
	private int endanger = -1; // 0 for black, 1 for white.

	private Chess(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private Chess(String name, int score, boolean limit, int... action) {
		this.name = name;
		int[][] i = this.generatePossibleStep(action);
		this.action = i[0];
		this.attack = i[1];
		this.score = score;
		this.limit = limit;
	}

	public static void init() {
		king = new Chess("king", 6, true, CROSS, BISHOP);
		queen = new Chess("queen", 5, false, CROSS, BISHOP);
		rook = new Chess("rook", 4, false, CROSS);
		bishop = new Chess("bishop", 3, false, BISHOP);
		knight = new Chess("knight", 2, true, KNIGHT);
		pawn = new Chess("pawn", 1, false, PAWN);
		Chessboard.gameBoard = Chessboard.getDefault();
	}

	private int[][] generatePossibleStep(int[] action) {
		/*
		 * direction: 0 1 2 3 you 4 5 6 7
		 */
		if (action == null)
			return new int[0][0];
		Set<Integer> d = new HashSet<Integer>(); // store direction
		Set<Integer> a = new HashSet<Integer>(); // store attack
		for (int type : action) {
			switch (type) {
			case PAWN:
				d.add(1);
				a.add(0);
				a.add(2);
				break;
			case CROSS:
				d.add(1);
				d.add(3);
				d.add(4);
				d.add(6);
				a.add(1);
				a.add(3);
				a.add(4);
				a.add(6);
				break;
			case BISHOP:
			case KNIGHT:
				d.add(0);
				d.add(2);
				d.add(5);
				d.add(7);
				a.add(0);
				a.add(2);
				a.add(5);
				a.add(7);
				break;
			}
		}
		int[][] result = new int[2][0];
		result[0] = ArrayUtils.intSet2Array(d);
		result[1] = ArrayUtils.intSet2Array(a);
		return result;
	}

	public File getIcon() {
		String path = "chess\\";
		if (this.isBlack) {
			path += "black\\";
		} else {
			path += "white\\";
		}
		path += this.name + this;
		return new File(path);
	}

	public List<int[]> getLegalPosition() {
		List<int[]> result = new ArrayList<int[]>();
		int[] temp = { this.x, this.y };
		switch (this.score) {
		case 1: // Pawn
			if (!this.hasMove) { // At the beginning, the pawn could able to move 2
				temp[1] += this.isBlack ? -2 : 2;
				result.add(temp.clone());
			}
			temp[1] = this.y;
			result.addAll(PositionUtils.getPos(temp, this.action));
			temp[0] = this.x;
			temp[1] = this.y;
			for (int[] a : PositionUtils.getPos(temp, this.attack)) {
				if (PositionUtils.inside(a)
						&& Chessboard.getChessByPosition(a[0], a[1]) != null)
					result.add(a);
			}
			// In passing pawn
			if (this.isBlack ? this.y <= 3 : this.y >= 4) {
				Chess c = Chessboard.getChessByPosition(this.x - 1, this.y);
				if (c != null) {
					if (c.isPawn() && (this.isBlack ^ c.isBlack)) {
						temp[0] = this.x - 1;
						temp[1] = this.y + (this.isBlack ? -1 : 1);
						result.add(temp.clone());
					}
				}
				c = Chessboard.getChessByPosition(this.x + 1, this.y);
				if (c != null) {
					if (c.isPawn() && (this.isBlack ^ c.isBlack)) {
						temp[0] = this.x + 1;
						temp[1] = this.y + (this.isBlack ? -1 : 1);
						result.add(temp.clone());
					}
				}
			}
			break;
		}
		return result;
	}

	public void moveTo(int x, int y) {
		// To be finished...
		this.hasMove = true;
		this.x = x;
		this.y = y;
	}

	public boolean isPawn() {
		return this.score == 1;
	}

	public String getXPosition() {
		return String.valueOf(this.x + 1);
	}

	public String getYPosition() {
		return String.valueOf((char) (65 + this.y));
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Chess) {
			if (this.x == ((Chess) obj).x && this.y == ((Chess) obj).y)
				return true;
		}
		return false;
	}

	public static Chess point(int x, int y) {
		return new Chess(x, y);
	}

	public static Chess point(Chess c, int x, int y) {
		Chess a;
		try {
			a = (Chess) c.clone();
		} catch (CloneNotSupportedException e) {
			return c;
		}
		a.x = x;
		a.y = y;
		a.isBlack = y > 3;
		if (a.isBlack && a.isPawn()) {
			a.action[0] = 6;
			a.attack[0] = 5;
			a.attack[1] = 7;
		}
		return a;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Chess c = point(x, y);
		c.action = this.action.clone();
		c.attack = this.attack.clone();
		return c;
	}
}
