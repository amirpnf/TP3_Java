import java.util.Arrays;

public class Pebble { 
  private static void swap(int[] array, int index1, int index2) {
    var temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

  private static int indexOfMin(int[] array) {
    var min = 0;
    for(var i = 0; i < array.length; i++) {
        if(array[i] < array[min]) {
            min = i;
        }
    }
    return min;
  }

  private static int indexOfMinRange(int[] array, int start, int end) {
    var indexMin = start;
    for(var i = start; i < end; i++) {
      if(array[i] < array[indexMin]) {
        indexMin = i;
      }
    }
    return indexMin;
  }   

  public static void sort(int[] array) {
    var indexMin = 0;
    for(int i = 0; i < array.length; i++) {
      indexMin = indexOfMinRange(array, i, array.length); 
      swap(array, indexMin, i);
    } 
  }


  public static void main(String[] args) {
    int[] array = new int[5];
    for(var i = 5; i > 0 ; --i) {
        array[5 - i] = i;
    }
    swap(array, 2, 3);
    System.out.println(Arrays.toString(array));
    System.out.println(indexOfMinRange(array, 2, 4));
    sort(array);
    System.out.println(Arrays.toString(array));
  }
}  
