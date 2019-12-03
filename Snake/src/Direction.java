/*
 * Direction.java -- A class to represent a direction within a Box.
 * 
 * A BoxIndex may be altered by adding a delta in a given direction.
 * The constructor with integer argument allows simple iteration over the directions.
 * We could memo-ize the constructor, rather than creating the same directions over and over.
 * 
 * Copyright (C) 2012 Stephen M. Watt
 */


public class Direction {
	
	private int		dirNo;
	private int[]	delta = new int[3];
	
	public Direction(int d) {
		dirNo = d;
		if (d != 0) delta[Math.abs(d)-1] = d/Math.abs(d);
	}
	public int dirNo()         { return this.dirNo; }
	public int delta(int axis) { return delta[axis]; }
}
