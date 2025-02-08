import java.util.Arrays;

public class BitonicSort{
    
    // Function to compare and swap elements
    private static void compareAndSwap( int[] arr, int i, int j, boolean ascending ){
        if( ( ascending && arr[i] > arr[j] ) || ( !ascending && arr[i] < arr[j] ) ){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Function to merge the bitonic sequence
    private static void bitonicMerge( int[] arr, int low, int count, boolean ascending ){
        if( count >  1 ){
            int mid = count / 2;
            for( int i = low ; i < low + mid ; i++ ){
                compareAndSwap( arr, i, i + mid, ascending );
            }

            bitonicMerge( arr, low, mid, ascending );
            bitonicMerge( arr, low + mid, mid, ascending );
        }
    }

    // Recursive function for Bitonic Sort
    private static void bitonicSort( int[] arr, int low, int count, boolean ascending ){
        if( count > 1 ){
            int mid = count / 2;

            bitonicSort( arr, low, mid, true );  // Sort first half in ascending order
            bitonicSort( arr, low + mid, mid, false );  // Sort second half in descending order

            bitonicMerge( arr, low, count, ascending );  // Merge the sequence
        }
    }

    // Wrapper function for Bitonic Sort
    public static void bitonicSort( int[] arr ){
        bitonicSort( arr, 0, arr.length, true );  // Sort entire array in ascending order
    }

    // Driver Code
    public static void main( String[] args ){
        int[] arr = { 3, 7, 4, 8, 6, 2, 1, 5 };  // Example array

        System.out.println( "Original Array: " + Arrays.toString( arr ) );
        
        bitonicSort( arr );  // Perform bitonic sort

        System.out.println( "Sorted Array: " + Arrays.toString( arr ) );
    }
}
