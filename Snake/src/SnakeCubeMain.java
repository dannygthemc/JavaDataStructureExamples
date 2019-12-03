/*
 * Snake Cube J
 *
 * You have an N*N*N box and a snake made up of N*N*N unit cubes
 * joined at faces.   The joints can rotate and can either be
 * at a right angle, or continue straight on.
 * All snakes can therefore be flattened to form a zig zag,
 * something like this:
 *
 * xxx
 *   xxx
 *     x
 *     xxx
 *       xxx
 *         xxx
 *           x
 *           xxx
 *             xxx
 *               xxx
 *                 x
 *
 * The objective is to fold the snake to fit in the box.
 *
 * The input is a sequence of k integers, each greater than or
 * equal to 2, giving the lengths of the arms of the zig zag.
 * For example, the zig zag above would be given as input
 *
 *    3 2 3 3 3 2 3 2 3 3 3 2 3 2 3 2
 *
 * Note that the sum of the k integers should be equal to
 * N*N*N + (k-1).
 *
 * The output has two parts.
 *
 * The first is the coordinates of a starting cube, in the
 * form (a,b,c) with a,b,c each in the range 0 to N-1.
 * Note that only starting cubes (0,0,0), (0,0,1) and (1,1,1)
 * need be considered, the rest can be obtained by symmetry.
 *
 * The second part of the output is a sequence of directions,
 * each given as +d or -d with d in the range 1 to 3, and with
 * one direction for each unit cube of the zig zag.  The direction
 * can change only where it corresponds to a joint in the zig zag.
 * The direction +d (-d) means to increment (decrement) the
 * d-th dimension holding the others fixed. For example,
 * if the current position in the box were (1,1,1) and the
 * new direction were +2, then the next position would be (1,2,1).
 * If the new direction were instead -1, then the next position
 * would be (0,1,1).
 *
 * The output should give a starting position for the beginning
 * of the snake the positions of the subsequent corners.
 * If there is more than one solution, pick the
 * one that has the lowest starting cube in the order
 * 
 *    (0,0,0) < (0,0,1) < (1,1,1)
 *     
 * and always picks the lowest choice of direction in the order
 *  
 *    (0,0,-1) < (0,-1,0) < (-1,0,0) < (1,0,0) < (0,1,0) < (0,0,1).
 *
 * This solution uses the following classes:
 * 
 * Snake -- represents a whole, unfolded snake.  
 *   The zig zags are called legs, numbered starting at 0.
 *   
 * Box -- represents the box into which the snake is being folded.
 *  This acts like a 3d array with the entries being the leg number
 *  of the part of the snake folded into the box so far.
 *  
 * BoxIndex -- an index for a Box objext.
 *   This is logically a triple (0,0,0)..(N-1,N-1,N-1), but
 *   supports moving in a Direction.
 *
 * Direction --  an increment/decrement for any of the 3 axes, or 0.
 * 
 * Created on: Oct 2, 2012
 * 
 * Copyright (C) 2012 Stephen M. Watt
 */




public class SnakeCubeMain {
	public static void main(String[] args) {
		Snake theSnake = 
				new Snake(new int[] {
					3, 3, 3, 3, 2, 2, 2, 3, 3, 
					2, 2, 3, 2, 3, 2, 2, 3
				});
		BoxIndex[] starts = 
				new BoxIndex[] { 
					new BoxIndex(0,0,0), 
					new BoxIndex(0,0,1),
					new BoxIndex(0,1,1), 
					new BoxIndex(1,1,1)
				};
		
		Shower tracer = new Shower(System.out);
		
		for (int i = 0; i < starts.length; i++) 
			tryToSolve(theSnake, starts[i], tracer);
		
		tracer.done();
	}
	
	static void tryToSolve(Snake theSnake, BoxIndex start, Shower tracer) {
		System.out.println("***************************************");
		System.out.print("Solving from starting point ");
		tracer.show(start);
		System.out.println();

		SolverState s = new SolverState(theSnake, start, tracer);

		if (!s.solve()) {
			System.out.println("No solution from here.");
		}
		else {
			System.out.println("Solution found.");
			tracer.show(s.path());
			System.out.println();
		}
	}
}
