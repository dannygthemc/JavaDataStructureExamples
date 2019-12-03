/*
 * Box.java -- A class representing the box into which the snake must be fit.
 * 
 * Copyright (C) 2012 Stephen M. Watt
 */


public class Box {
	private int size;
	private int[][][] contents;
	
	private int MARK  = 1;
	private int CLEAR = 0;
	
	public Box(int size) {
		this.size = size;
		contents = new int[size][size][size];
		
		for (int i = 0; i < size; i++) 
			for (int j = 0; j < size; j++) 
				for (int k = 0; k < size; k++) 
					contents[i][j][k] = CLEAR;
	}
	
	public int size()   { return size; }
	
	public int get(BoxIndex bix) {
		return get(bix.coord(0), bix.coord(1), bix.coord(2));
	}
	
	public int get(int i, int j, int k) {
		return contents[i][j][k];
	}
	public void mark(BoxIndex bix) {
		contents[bix.coord(0)][bix.coord(1)][bix.coord(2)] = MARK;
	}
	public void clear(BoxIndex bix) {
		contents[bix.coord(0)][bix.coord(1)][bix.coord(2)] = CLEAR;
	}
	// Assume starting point of leg is already filled.
	public void markLeg(BoxIndex bix, Direction d, int length) {
		for (int i = 1; i < length; i++) mark(bix.advance(d, i));
		MARK++;
	}
	public void clearLeg(BoxIndex bix, Direction d, int length) {
		for (int i = 1; i < length; i++) clear(bix.advance(d, i));
		MARK--;
	}
	
	// In box and not intersecting path.
	public boolean isLegOK(BoxIndex bix, Direction d, int length) {
		
		for (int i = 1; i < length; i++) {
			BoxIndex nix = bix.advance(d, i);
			for (int h = 0; h < 3; h++)
				if (nix.coord(h) < 0 || nix.coord(h) >= size()) return false;
			if (get(nix) != CLEAR) return false;
		}
		return true;
	}
}
