/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SudokuClasses;

import SudokuClasses.Slice;

/**
 *
 * @author Daniel Gilbert
 */
public class RowSlice extends Slice { 
    private Tableau  _t; 
    private int            _rowno;
    
    public RowSlice(Tableau t, int rowno) { _t = t; _rowno = rowno; }
        final public int size() { return _t.size(); }
        final public int getValue(int i) { return _t.getValue( _rowno, i ); }
} 
