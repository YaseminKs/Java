import java.util.*;

class Graph{
    private int vertices;
    private LinkedList<Integer>[] adjList;

    public Graph( int vertices ){
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for( int i = 0 ; i < vertices ; i++ ){
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge( int src, int dest ){
        adjList[src].add( dest );
        adjList[dest].add( src ); // For an undirected graph
    }

    public void bfs( int start ){
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add( start );

        while( !queue.isEmpty() ){
            int node = queue.poll();
            System.out.print( node + " " );

            for( int neighbor : adjList[node] ){
                if( !visited[neighbor] ){
                    visited[neighbor] = true;
                    queue.add( neighbor );
                }
            }
        }
    }

    public static void main( String[] args ){
        Graph g = new Graph( 6 );
        g.addEdge( 0, 1 );
        g.addEdge( 0, 2 );
        g.addEdge( 1, 3 );
        g.addEdge( 1, 4 );
        g.addEdge( 2, 5 );

        System.out.println( "BFS traversal starting from node 0:" );
        g.bfs( 0 );
    }
}
