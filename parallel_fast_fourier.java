import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelFFT{
    public static void fft( Complex[] x ){
        int n = x.length;
        if( n == 1 ) return;

        Complex[] even = new Complex[n / 2];
        Complex[] odd = new Complex[n / 2];
        for( int i = 0 ; i < n / 2 ; i++ ){
            even[i] = x[i * 2];
            odd[i] = x[i * 2 + 1];
        }

        ForkJoinPool pool = new ForkJoinPool();
        FFTTask evenTask = new FFTTask( even );
        FFTTask oddTask = new FFTTask( odd );
        pool.invoke( evenTask );
        pool.invoke( oddTask );

        for( int k = 0 ; k < n / 2 ; k++ ){
            Complex t = Complex.exp( -2 * Math.PI * k / n ).multiply( odd[k] );
            x[k] = even[k].add( t );
            x[k + n / 2] = even[k].subtract( t );
        }
    }

    static class FFTTask extends RecursiveAction{
        private Complex[] x;

        FFTTask( Complex[] x ){
            this.x = x;
        }

        @Override
        protected void compute(){
            fft( x );
        }
    }

    public static void main( String[] args ){
        int n = 8;
        Complex[] data = new Complex[n];
        for( int i = 0 ; i < n ; i++ ){
            data[i] = new Complex( i, 0 );
        }

        fft( data );
        for( Complex c : data ){
            System.out.println( c );
        }
    }
}
