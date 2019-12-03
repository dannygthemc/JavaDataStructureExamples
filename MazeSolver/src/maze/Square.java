package maze;
//@author Daniel Gilbert
//Square class designed to represent a tile in a maze
public class Square {
	protected int i = 0;
	protected int j = 0;
	int val = 0;

	//@param int i, j
	//creates instance of object
	public Square(int _i, int _j){
		i = _i;
		j = _j;
	}
	//used to print out current value of square for testing
	public String toString(){
		String square = "";
		square += i + " " + j;
		return square;
		
	}
	//returns i value 
	public int getX(){
		return i;
	}
	//returns j value
	public int getY(){
		return j;
	}
}
