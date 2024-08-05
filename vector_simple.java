import java.util.ArrayList;
import java.util.Random;


public class vector {
	
	public static void main( String [] args ){
		
		ArrayList< Integer > liste = new ArrayList<>();
		Random rnd = new Random();
		
		for( int i = 0 ; i < 10 ; i++ ){
			liste.add( rnd.nextInt( 100 ) );	
		} 
		
		liste.set( 0, 1000 );
		liste.add( 200 );
		liste.remove( 0 );
		System.out.println( liste.size() );
		System.out.println( liste.contains( 17 ) );
		System.out.println( liste.contains( 200 ) );
		System.out.println( liste );
		
	}
	
}
