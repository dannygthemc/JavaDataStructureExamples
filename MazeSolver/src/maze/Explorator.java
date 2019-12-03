package maze;
//Provided explorator interface
public interface Explorator{
	  void clear();
	  boolean isEmpty();
	  Square getNext();
	  void add(Square s);
	}