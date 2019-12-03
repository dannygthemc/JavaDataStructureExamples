/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SudokuClasses;

/**
 *
 * @author Daniel Gilbert
 */
public class ColumnSlice extends Slice { 
    private Tableau  _t; 
    private int            _colno; 
    
    public ColumnSlice(Tableau t, int colno) { _t = t; _colno = colno; }
        final public int size() { return _t.size(); }
        final public int getValue(int i) { return _t.getValue( i, _colno); }
} 
