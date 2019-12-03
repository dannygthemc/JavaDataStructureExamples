/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SudokuClasses;

/**
 *
 * @author Daniel Gilbert
 */
public class Tableau {
    
private int _subsize;
private int _size;
private int[][] _tableau;

public int size(){ 
    return _size; }
public int subsize(){
    return _subsize; }
public int getValue(int r, int c){
    return _tableau[r][c]; }
    
public Tableau(int n) {
_size    = n;
_subsize = isqrt(n);
_tableau = new int[n][n];
}
// Compute the square root of a small square integer.
private static int isqrt(int n) {
    int i = 0; while (i*i < n) i++; return i; }

// We put blank lines and columns between sub-squares.
public void print() {
for (int i = 0; i < _size; i++) {
if (i > 0 && i % _subsize == 0) System.out.println();
for (int j = 0; j < _size; j++) {
if (j > 0 && j % _subsize == 0) System.out.print(" ");
System.out.print(_tableau[i][j]);
}
System.out.println();
}
}
// Create a Sudoku tableau from an array of rows.
public Tableau(String[] rows) { 
    this(rows.length);   // Call the private emtpy-tableau constructor.
    for (int i = 0; i < size(); i++)
    fillRow(rows[i], 0, _tableau[i]);
}
// Create a Sudoku tableau from a single string.
public Tableau(int n, String initial) {
    this(n);  // Call the private empty-tableau constructor.
    for (int rix = 0, ix = 0; rix < n; rix++) {
    // Each iteration advances the starting position in the string.
    ix = fillRow(initial, ix, _tableau[rix]);
    }         
}
 private static int fillRow(String s, int ix, int[] row) {
        int rix = 0;
        for ( ; ix < s.length() && rix < row.length; ix++) {
            char c = s.charAt(ix);
            if (!Character.isDigit(c)) continue; 
        row[rix++] = Character.digit(c, 10);
        }
        for ( ; rix < row.length; rix++)
            row[rix] = 0;
            return ix;
}
    
}
