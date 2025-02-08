import java.util.*;

class Dijkstra {
    static class Node implements Comparable<Node>{
        int vertex, distance;
        
        public Node( int vertex, int distance ){
            this.vertex = vertex;
            this.distance = distance;
        }
        
        @Override
        public int compareTo( Node other ){
            return Integer.compare( this.distance, other.distance );
        }
    }

    public static void dijkstra( List<List<Node>> graph, int source ){
        int V = graph.size();
        int[] dist = new int[V];
        Arrays.fill( dist, Integer.MAX_VALUE );
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add( new Node( source, 0 ) );

        while( !pq.isEmpty() ){
            Node node = pq.poll();
            int u = node.vertex;

            for( Node neighbor : graph.get( u ) ){
                int v = neighbor.vertex;
                int weight = neighbor.distance;

                if( dist[u] + weight < dist[v] ){
                    dist[v] = dist[u] + weight;
                    pq.add( new Node( v, dist[v] ) );
                }
            }
        }

        System.out.println( "Shortest distances from source " + source + ": " + Arrays.toString( dist ) );
    }

    public static void main( String[] args ){
        int V = 5;
        List<List<Node>> graph = new ArrayList<>();
        
        for( int i = 0 ; i < V ; i++ )
            graph.add( new ArrayList<>() );

        graph.get( 0 ).add( new Node( 1, 10 ) );
        graph.get( 0 ).add( new Node( 2, 3 ) );
        graph.get( 1 ).add( new Node( 2, 1 ) );
        graph.get( 1 ).add( new Node( 3, 2 ) );
        graph.get( 2 ).add( new Node( 1, 4 ) );
        graph.get( 2 ).add( new Node( 3, 8 ) );
        graph.get( 2 ).add( new Node( 4, 2 ) );
        graph.get( 3 ).add( new Node( 4, 7 ) );
        graph.get( 4 ).add( new Node( 3, 9 ) );

        dijkstra( graph, 0 );
    }
}
