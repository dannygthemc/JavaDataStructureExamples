/*
 * Snake.java -- A class to represent the bendy snake that has to fit into the cube.
 * 
 * For a detailed descrption, see the comment in SnakeCubeMain.java.
 * 
 * Copyright (C) 2012 Stephen M. Watt
 */


public class Snake {
	private int[] legLengths;
	
	public Snake(int[] legLengths) {
		this.legLengths = legLengths;
	}
	public int legLength(int i) {
		return legLengths[i];
	}
	public int length() {
		return legLengths.length;
	}
	public int totalUnitCubes() {
		int ncubes = legLengths[0];
		for (int i = 1; i < legLengths.length; i++)
			ncubes += legLengths[i] - 1;
		return ncubes;
	}
}
