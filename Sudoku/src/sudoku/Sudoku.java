/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;


import SudokuClasses.*;

/**
 *
 * @author Daniel Gilbert
 */
public class Sudoku {
    
    public static void main(String[] args) {
        
        int n = 9;
        Tableau t = new Tableau(new String[] {
            "534 678 912",
            "672 195 348",
            "198 342 567",
            "859 761 423",
            "426 853 791",
            "713 924 856",
            "961 537 284",
            "287 419 635",
            "345 286 179"
        });
        t.print(); 
        boolean ok = checkSudoku(t); 
        if (ok) System.out.println("The Sudoku is OK."); 
        else    System.out.println("The Sudoku is not OK."); 
    } 
    public static boolean checkSudoku(Tableau t) { 
        Slice s;
        for (int i = 0; i < t.size(); i++) { 
            s = new RowSlice (t, i);      if (!checkSlice(s)) return false;
            s = new ColumnSlice(t, i);  if (!checkSlice(s)) return false; 
            s = new SquareSlice(t, i);   if (!checkSlice(s)) return false; 
        } 
        return true; 
    } 
        public static boolean checkSlice(Slice s) { 
            for (int i = 1; i <= s.size(); i++) { 
            // Check that value i is there. 
            boolean found = false;
            for (int j = 0; j < s.size() && !found; j++) {
            if (s.getValue(j) == i) found = true; 
            }
            // If not found, then the check fails.
            if (!found) return false; 
            } 
            // All were found so check succeeds. 
            return true; 
    }
}


