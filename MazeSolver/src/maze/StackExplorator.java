package maze;
//@author Daniel Gilbert
//Completed 3/18/2013
//Explorator class for a text file based maze solver
//utilizes a Stack, implements Explorator interface
public class StackExplorator implements Explorator {
	private StackADT<Square> stack;
	private int size = 0;
	
	public StackExplorator(){
		stack = new LinkedStack<Square>();
	}
	public void clear(){
		
	}
	public void add(Square _square){
		stack.push(_square);
		size++;
	}
	@Override
	public boolean isEmpty() {
		boolean check = false;
		if(size == 0){
			check = true;
		}
		return check;
	}
	@Override
	public Square getNext() {
		Square temp = stack.pop();
		size--;
		return temp;
	}

}
