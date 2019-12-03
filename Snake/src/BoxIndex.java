/*
 * BoxIndex.java -- A class to represent locations in a Box.
 * 
 * Copyright (C) 2012 Stephen M. Watt
 */

public class BoxIndex {
	private int[] coords = new int[3];
	
	public BoxIndex(int i, int j, int k) {
		coords[0] = i;
		coords[1] = j;
		coords[2] = k;
	}
	public int coord(int i) { return coords[i]; }
	
	public BoxIndex advance(Direction d) { return advance(d,1); }
	
	public BoxIndex advance(Direction d, int times) {
		return new BoxIndex(
				coords[0] + times * d.delta(0),
				coords[1] + times * d.delta(1),
				coords[2] + times * d.delta(2));
	}
}
