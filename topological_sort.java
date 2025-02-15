import java.util.*;

public class TopologicalSort{
    private int V;
    private LinkedList<Integer> adj[];

    @SuppressWarnings( "unchecked" )
    TopologicalSort( int v ){
        V = v;
        adj = new LinkedList[v];
        for( int i = 0 ; i < v ; ++i )
            adj[i] = new LinkedList();
    }

    void addEdge( int v, int w ){ adj[v].add( w ); }

    void topologicalSortUtil( int v, boolean visited[], Stack<Integer> stack ){
        visited[v] = true;
        Integer i;

        for( Integer node : adj[v] ){
            if( !visited[node] )
                topologicalSortUtil( node, visited, stack );
        }
        stack.push( v );
    }

    void topologicalSort(){
        Stack<Integer> stack = new Stack<>();

        boolean visited[] = new boolean[V];
        for( int i = 0 ; i < V ; i++ )
            if( !visited[i] )
                topologicalSortUtil( i, visited, stack );

        while( !stack.empty() )
            System.out.print( stack.pop() + " " );
    }

    public static void main( String args[] ){
        TopologicalSort g = new TopologicalSort( 6 );
        g.addEdge( 5, 2 );
        g.addEdge( 5, 0 );
        g.addEdge( 4, 0 );
        g.addEdge( 4, 1 );
        g.addEdge( 2, 3 );
        g.addEdge( 3, 1 );

        System.out.println( "Topological Sort of the graph:" );
        g.topologicalSort();
    }
}
