import java.util.HashSet;
import java.util.Iterator;

public class Too {
	
	public static void main( String [] args ){
		
		HashSet< Integer > a = new HashSet< Integer >();
		a.add( 1 );
		a.add( 2 );
		a.add( 100 );
		a.add( 5 );
		a.add( 5 );
		a.add( 45 );
    
		Iterator< Integer > it = a.iterator();
		
		while( it.hasNext() ){
			System.out.println( it.next() );
			it.remove();
		}
		
		a.add( 1453 );
		System.out.println( a );
		
	}

}
 
