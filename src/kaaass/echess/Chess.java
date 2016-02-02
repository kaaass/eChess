package kaaass.echess;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kaaass.echess.util.ArrayUtils;

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
	private int[] action;
	private int[] attack;
	private boolean limit;
	
	private int[] posision = {0, 0};
	private boolean isBlack = true;
	private boolean isPawn = false;
	private boolean isKnight = false;
	private boolean hasMove = false;
	
	private Chess(String name, boolean limit, int... action) {
		this.name = name;
		int[][] i = this.generatePossibleStep(action);
		this.action = i[0];
		this.attack = i[1];
		this.limit = limit;
	}
	
	public static void init() {
		king = new Chess("king" ,true, CROSS, BISHOP);
		queen = new Chess("queen" ,false, CROSS, BISHOP);
		rook = new Chess("rook" ,false, CROSS);
		knight = new Chess("knight" ,true, KNIGHT);
		bishop = new Chess("bishop" ,false, BISHOP);
		pawn = new Chess("pawn" ,false, PAWN);
	}
	
	private int[][] generatePossibleStep(int[] action) {
		/*
		 * direction:
		 * 0   1   2
		 * 3  you  4
		 * 5   6   7
		 */
		if (action == null)
			return new int[0][0];
		Set<Integer> d = new HashSet<Integer>(); // store direction
		Set<Integer> a = new HashSet<Integer>(); // store attack
		for (int type :action) {
			switch(type) {
			case PAWN:
				isPawn = true;
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
				if (type == KNIGHT)
					this.isKnight = true;
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
	
	public List<int[]> getLegalPosision() {
		// To be finished...
		return null;
	}
	
	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	
	public boolean isBlack() {
		return this.isBlack;
	}
	
	public boolean isPawn() {
		return this.isPawn;
	}
	
	public String getXPosition() {
		return String.valueOf(this.posision[0] + 1);
	}
	
	public String getYPosition() {
		return String.valueOf((char) (65 + this.posision[0]));
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
