package test;

public class CircularArrayTest {

	public static void main(String[]args){
		testCAQ();
		testLinkedStack();
		
	}
	public static void testCAQ(){
		CircularArrayQueue<Integer> test = new CircularArrayQueue<Integer>(100);
		int[] numbers =new int[100];
		
		for(int i = 0; i<100; i++){
			numbers[i] = i;
			test.enqueue(numbers[i]);
		}
		for(int i = 0; i<100; i++){
			int temp =test.dequeue();
			System.out.println("Dequeued: " + temp);
		}
	}
	public static void testLinkedStack(){
		
		LinearNode<Integer> head = null;
		LinearNode<Integer> intNode;
		
		for(int i = 100; i>=1; i--){
			intNode = new LinearNode<Integer>(i);
			intNode.setNext(head);
			head = intNode;
		}
		LinearNode<Integer> current = head;
		for(int i = 100; i>=1; i--){
			System.out.println("List item: " + current.getElement());
			current = current.getNext();
		}
	}
}
