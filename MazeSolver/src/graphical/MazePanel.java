package graphical;
import java.awt.*;
import javax.swing.*;

import maze.*;

public class MazePanel extends JPanel  {
  final int SQUARE_SIZE = 10;
  final Color GREY = new Color(200, 200, 200);
  private QueueADT<GraphicalObject> queue;
  private int size;


  void add(GraphicalObject obj){
    queue.enqueue(obj);
    size++;
  }

  //constructor for the panel
  //@param int height/width
  MazePanel(int h, int w){
    Dimension g = new Dimension(SQUARE_SIZE*w, SQUARE_SIZE*h);
    setPreferredSize(g);
    setBackground(GREY);

    queue = new LinkedQueue<GraphicalObject>();//holds graphical objects
  }

  //draws the graphical objects in the queue
  //@param Graphics object
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(int i = 0; i < size; i++){
    	GraphicalObject obj = queue.dequeue();
    	obj.draw(g, SQUARE_SIZE);
    	queue.enqueue(obj);
    }
  }
}