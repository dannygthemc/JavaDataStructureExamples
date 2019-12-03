package maze;
import java.awt.Color;
import java.io.*;
import graphical.*;
import java.util.Scanner;

//@author Daniel Gilbert
//Completed 3/18/2013
//Maze class for a text file based maze solver
public class Maze {
	String lines[] = new String[10];
	int counter = 0;
	boolean tableau[][];
	boolean visited[][];
	Square entrance;
	Square exit;
	int width =0;
	int height = 0;
	final static Color RED = new Color(255, 0, 0);
	
	//@param String fileName
	//creates an object of type maze by reading from a file
	public Maze(String fileName){
		readFile(fileName);
		height = Integer.parseInt(lines[0]);
		width = Integer.parseInt(lines[1]);
		entrance = new Square(Integer.parseInt(lines[2]),Integer.parseInt(lines[3]));
		exit = new Square(Integer.parseInt(lines[4]),Integer.parseInt(lines[5]));
		tableau = new boolean[height][width];
		visited = new boolean[height][width];
		fillTableau();
	}
	//fills the array that keeps track of the free squares
	//also sets the visited array to all false
	public void fillTableau(){
		int x=0;
		int y = 0;
		for (int i = 6; i <counter; i++){
			String current = lines[i];
			for(int p =0; p < current.length(); p++){
				System.out.print(current.charAt(p));
				//sets true or false to tableau at every x and y coordinate
				//sets which squares are free
				if(current.charAt(p) == '1'){
				tableau[y][x] = true;
				}
				else{
				tableau[y][x] = false;
		        }
				visited[y][x] = false;//sets all x and y in visited to false to start
				x++;//keeps track of x co-ords
			}
			y++;//keeps track of y co-ords
			x =0;
			System.out.println("\n");
		}
	}
	//reads from a file and fills lines[]with the lines from the file 
	public void readFile(String fileName){
		File file = new File(fileName);
		try {
			 
	        Scanner scanner = new Scanner(file);

	        while (scanner.hasNextLine()) {
	            lines[counter] = scanner.nextLine();//adds lines to array
	            counter++;//adds to counter
	            if(counter == lines.length){//expands if array is full
	            	expand();
	            }
	        }
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
		
	      }
	}
	//expands array
	public void expand(){
		String longer[] = new String[lines.length*2];//creates new array of double length
		for(int i = 0;i<lines.length;i++){
			longer[i] = lines[i];//copies over lines
		}
			lines = longer;//sets old array to the new
	}
	//used to traverse the maze
	//@param Explorator e, MazeFrame fram
	//@ return boolean 
	public boolean explore(Explorator e, MazeFrame frame){
		boolean found = false;
		
		e.add(entrance);
		visited[entrance.getX()][entrance.getY()] = true;
		while(e.isEmpty() != true){
				Square temp = e.getNext();
				int x = temp.getX();
				int y = temp.getY();
				
				
				if(isExit(x,y)){//checks if the current Square is the exit, breaks and ends if this is so
					found = true;
					System.out.println("Exit Found");
					break;
				}
				//following if statements check the availability of the squares left right up and down
				//if they are free, adds them to the explorator
				
				
				if (isFree(x-1, y)){//checks square up
					pause(100);
					e.add(new Square(x-1, y));
					TriangleUpObject tring = new TriangleUpObject(RED, x-1, y);
					frame.add(tring);//adds triangle object to graphical queue
					visited[x-1][y] = true;
				}
				if (isFree(x, y-1)){//checks square left
					pause(100);
					e.add(new Square(x, y-1));
					TriangleLeftObject tring = new TriangleLeftObject(RED, x, y-1);
					frame.add(tring);
					visited[x][y-1] = true;
				}
				
				if (isFree(x+1, y)){//checks square down
					pause(100);
					e.add(new Square(x+1, y));
					TriangleDownObject tring = new TriangleDownObject(RED, x+1, y);
					frame.add(tring);
					visited[x+1][y] = true;
				}
				if (isFree(x, y+1)){//check square right
					pause(100);
					e.add(new Square(x, y+1));
					TriangleRightObject tring = new TriangleRightObject(RED, x, y+1);
					frame.add(tring);
					visited[x][y+1] = true;
				}
				
		}
		
		return found;
	}
	//used to slow speed so as to make visible
	static void pause (int i) {
	    try {
	      Thread.sleep(i);
	    } catch (InterruptedException e) { }
	  }
	//returns width of the maze
	//@return int width
	public int getWidth(){
		return width;
	}
	//returns height of the maze
	//@return int height
	public int getHeight(){
		return height;
	}
	//checks if desired square is free
	public boolean isFree(int _x, int _y){
		
		boolean check = false;
		try
		{
			if(tableau[_x][_y] == true && visited[_x][_y] == false){//if the desired spot is a one, and had not yet been checked
				System.out.println("You're at: " + _y + "," + _x);//prints out location
				check = true;
			}
			
		}catch (ArrayIndexOutOfBoundsException e){//catches references outside of the maze
		}
		return check;
	}
	//checks if the spot in question is the exit
	//@param int x, int y
	//@return boolean
	public boolean isExit(int _x, int _y){
		boolean check = false;
		if(_x == exit.getX() && _y == exit.getY())
			check = true;
		return check;
	}
	//@ return Square exit
	public Square getExit(){
		return exit;
	}
	//@return Square entrance
	public Square getEntrance(){
		return entrance;
	}
}
