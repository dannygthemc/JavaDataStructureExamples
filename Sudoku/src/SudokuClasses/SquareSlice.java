/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SudokuClasses;

/**
 *
 * @author Daniel Gilbert
 */
public class SquareSlice extends Slice {
    private Tableau _t; 
    private int _row0, _col0; 
    
    public SquareSlice(Tableau t, int n) { 
        _t = t;
        _row0 = (n / _t.subsize()) * _t.subsize();
        _col0 = (n % _t.subsize()) * _t.subsize();
    }
    
    final public int getValue(int i) {
        int r = _row0 + i / _t.subsize(); 
        int c = _col0 + i % _t.subsize(); 
        return _t.getValue(r, c); 
    } 
    
    final public int size() { return _t.size(); } 
}
