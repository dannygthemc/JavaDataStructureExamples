package maze;

import graphical.*;

public class TestMaze {
	public static void main(String[] args){
	String fileName = "mazeTwo.txt";
	//String fileName = "mazeOne.txt";
	Maze example = new Maze(fileName);
	MazeFrame frame = new MazeFrame(example);
	Explorator e = new StackExplorator();
	//Explorator e = new QueueExplorator();
	example.explore(e, frame);
		}
}
