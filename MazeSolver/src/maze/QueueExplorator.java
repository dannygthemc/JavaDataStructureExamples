package maze;
//@author Daniel Gilbert
//Completed 3/18/2013
//Explorator class for a text file based maze solver
//utilizes a Queue, implements Explorator interface
public class QueueExplorator implements Explorator {
	private QueueADT<Square> queue;
	private int size;
	
	public QueueExplorator(){
		queue = new LinkedQueue<Square>();
	}
	public void clear(){
		
	}
	public void add(Square _square){
		queue.enqueue(_square);
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
		Square temp = queue.dequeue();
		size--;
		return temp;
	}

}

