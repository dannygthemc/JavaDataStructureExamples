package graphical;

import java.awt.*;
import javax.swing.*;
import maze.*;

public class MazeFrame extends JFrame{
  final static Color BLACK = new Color(0, 0, 0);
  final static Color WHITE = new Color(255, 255, 255);
  final static Color RED = new Color(255, 0, 0);
  final static Color GREEN = new Color(0, 255, 0);
  final static Color BLUE = new Color(0, 0, 255);

  private MazePanel panel;

  public void add(GraphicalObject obj){
    panel.add(obj);
    repaint();
  }
  
  public MazeFrame(Maze maze){
    super("Maze");  
    
    int h = maze.getHeight();
    int w = maze.getWidth();

    panel = new MazePanel(h, w);  
    add(panel);  

    for (int i = 0; i < h; i++){
        for (int j = 0; j < w; j++){
        	
            if (maze.isFree(i, j))
              add(new SquareObject(WHITE, i, j));
            else
              add(new SquareObject(BLACK, i, j));
        }
     }
        add(new CircleObject(BLUE, maze.getExit().getX(), maze.getExit().getY()));
        add(new CircleObject(GREEN, maze.getEntrance().getX(), maze.getEntrance().getY()));
        
    

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    pack();
    setResizable(false);
    setVisible(true);
    repaint();
  }
}