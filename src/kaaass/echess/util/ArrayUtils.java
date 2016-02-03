package kaaass.echess.util;

import java.util.Set;

public class ArrayUtils {
	public static int[] intSet2Array(Set<Integer> set) {
		Integer[] t = new Integer[0];
		int[] r;
		t = set.toArray(t);
		r = new int[t.length];
		for (int i = 0; i < t.length; i++)
			r[i] = t[i];
		return r;
	}
}
