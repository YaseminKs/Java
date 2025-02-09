// Java: Uses ForkJoinPool for parallel counting sort per digit.

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelRadixSort{
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

    public static void radixSort( int[] arr ){
        int max = Arrays.stream( arr ).max().getAsInt();
        ForkJoinPool pool = new ForkJoinPool( MAX_THREADS );

        for( int exp = 1 ; max / exp > 0 ; exp *= 10 ){
            pool.invoke( new CountingSortTask( arr, exp ) );
        }
    }

    static class CountingSortTask extends RecursiveAction{
        private final int[] arr;
        private final int exp;

        CountingSortTask( int[] arr, int exp ){
            this.arr = arr;
            this.exp = exp;
        }

        @Override
        protected void compute(){
            int n = arr.length;
            int[] output = new int[n];
            int[] count = new int[10];

            for( int num : arr ) count[( num / exp ) % 10]++;
            for( int i = 1 ; i < 10 ; i++)  count[i] += count[i - 1];

            for( int i = n - 1 ; i >= 0 ; i--){
                output[count[( arr[i] / exp ) % 10] - 1] = arr[i];
                count[( arr[i] / exp ) % 10]--;
            }

            System.arraycopy( output, 0, arr, 0, n );
        }
    }

    public static void main( String[] args ){
        int[] arr = { 170, 45, 75, 90, 802, 24, 2, 66 };
        radixSort( arr );
        System.out.println( Arrays.toString( arr ) );
    }
}
