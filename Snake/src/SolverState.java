/*
 * SolverState.java -- This class represents a trial solution as it is being formed.
 * 
 * The main method is "solve()", which uses the fields path and dirs as a stack
 * for backtracking.
 * 
 * Copyright (C) 2012 Stephen M. Watt
 */


public class SolverState {
	private Snake       snake;      // The snake to put in the box.	
	private Box         box;        // The box being filled.
	private int         nextLeg;    // Index of next leg to fit.
	
	// These variables keep track of the folded snake so far.
	// path[nextLeg] is the box index of where this leg must start.
	// dirs[nextLeg] is the direction of the previous leg.
	
	private BoxIndex [] path;   // Location 0 is starting index.
	private Direction[] dirs;   // Location 0 is 0 direction.
	
	
	private Shower	shower;	// For debugging.
	
	// Rounded up cube root.
	private int iCubeRt(int n) {
		int rt = 0; 
		while (rt*rt*rt < n) rt++;
		return rt;
	}
	
	public SolverState(Snake theSnake, BoxIndex start, Shower tracer) {
		shower = tracer;
		snake = theSnake;
		box   = new Box(iCubeRt(snake.totalUnitCubes()));
		path  = new BoxIndex [snake.length() + 1];
		dirs  = new Direction[snake.length() + 1];
		
		path[nextLeg] = start;
		dirs[nextLeg] = new Direction(0);
		box.mark(start);
	}
	
	public boolean solve() {
		shower.trace(path(), box);
		if (nextLeg == snake.length()) return true;
		
		Direction prevDir = dirs[nextLeg];
		BoxIndex  prevIx  = path[nextLeg];
		int       currLen = snake.legLength(nextLeg);
		
		for (int dirno = -3; dirno <= 3; dirno++) {
			if (dirno == 0 || dirno == prevDir.dirNo()) continue;
			
			Direction newDir = new Direction(dirno);
			if (!box.isLegOK(prevIx, newDir, currLen)) continue;
			
			// OK the leg fits in the box.  Put it in.
			box.markLeg(prevIx, newDir, currLen);
			nextLeg++;
			dirs[nextLeg] = newDir;
			path[nextLeg] = prevIx.advance(newDir, currLen-1);
			
			// Try to fit rest.  If solution, great, return.
			if (solve()) return true;
			
			// Otherwise undo this direction for this leg and try again.
			nextLeg--;
			box.clearLeg(prevIx,  newDir,  currLen);
		}
		// Nothing worked. Fail.
		return false;
	}
	
	// A snapshot of the path.
	public BoxIndex[] path() {
		BoxIndex[] p = new BoxIndex[nextLeg];
		for (int i = 0; i < nextLeg; i++) p[i] = path[i];
		return p;
	}
}