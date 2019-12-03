/**
 * Class SearchDemo demonstrates linear search vs binary search
 * on arrays of integers.
 * @author CS1027a 2009
 */

public class SearchDemo {
	
  final static int NOT_FOUND = -1;
  /** 
   * Linear search method, including counter for number of comparisons.	
   * @param array		array of integers
   * @param target	number to search for
   * @return index of element in array if found, -1 otherwise
   */
  public static int linearSearch(int[] array, int target)
  {		
    int index = 0;
    int compareCount = 1;			// for algorithm analysis		
		
    while ((index < array.length - 1) && (array[index] != target))
      {
	index ++;
	compareCount ++;
      }
    System.out.println("Number of elements compared against: " + compareCount);
    if (array[index] == target)
      return index;
    else
      return NOT_FOUND;
  }

  /**
   * Binary search method, including counter for number of comparisons.
   * @param array		array of integers
   * @param target	number to search for
   * @return index of element in array if found, -1 otherwise@param array
   */
  public static int binarySearch(int[] array, int target)
  {
    int compareCount = 1;			// for algorithm analysis
    int first = 0;
    int last = array.length - 1;
    int mid;
	
    // precondition: array is sorted in ascending order		
    do {
      mid = (first + last)/2;		// integer divide
      if (target < array[mid])
	last = mid - 1;
      else
	first = mid + 1;
      compareCount ++;
    } while (array[mid] != target && first <= last);
		
    System.out.println("Number of elements compared against: " + compareCount);
		
    if (array[mid] == target)
      return mid;
    else
      return NOT_FOUND;
  }

  /**
   * Main method generates a test array of integers, and then
   * invokes the linear search and binary search methods.
   * 
   */
  public static void main(String [] args) {
    int target, index;
		
    final int SIZE = 512;						// size of test array generated
    int[] testArray = new int[SIZE];
    for (int i = 0; i < testArray.length; i++) 	// generate test array
      testArray[i] = i * 5;
		
    testSearches(testArray, 25);				// search for number in array
    testSearches(testArray, 33);				// search for number not in array
    testSearches(testArray, testArray[SIZE-1]);	// search for last element	
  }

  /** Helper method to start up the linear search and binary search, and
   * print result of searches.
   * @param array		array of integers	
   * @param target	number to search for
   */
  static void testSearches(int[] array, int target)
  {
    int index;
		
    System.out.println("\nLinear Search: size of array is " + array.length);
    index = linearSearch(array, target);
    if (index != NOT_FOUND)
      System.out.println("Found " + target +" at index "+ index );
    else
      System.out.println("Did not find " + target);
		
    System.out.println("\nBinary Search: size of array is " + array.length);
    index = binarySearch(array, target);
    if (index != NOT_FOUND)
      System.out.println("Found " + target +" at index "+ index );
    else
      System.out.println("Did not find " + target);
			
  }
}