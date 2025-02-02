import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Node{
    int data;
    Node next;

    Node( int data ){
        this.data = data;
        this.next = null;
    }
}

public class ParallelLinkedList{
    
    // Append a node to the linked list
    public static Node append( Node head, int data ){
        Node newNode = new Node(data);
        if( head == null ){
            return newNode;
        }
        Node temp = head;
        while( temp.next != null ){
            temp = temp.next;
        }
        temp.next = newNode;
        return head;
    }

    // Convert linked list to an array for parallel processing
    public static List<Integer> listToArray( Node head ){
        List<Integer> list = new ArrayList<>();
        Node temp = head;
        while( temp != null ){
            list.add( temp.data );
            temp = temp.next;
        }
        return list;
    }

    // Update linked list from an array
    public static void arrayToList( Node head, List<Integer> dataList ){
        Node temp = head;
        for( int data : dataList ){
            if( temp != null ){
                temp.data = data;
                temp = temp.next;
            }
        }
    }

    // Print the linked list
    public static void printList( Node head ){
        Node temp = head;
        while( temp != null ){
            System.out.print( temp.data + " -> " );
            temp = temp.next;
        }
        System.out.println( "NULL" );
    }

    public static void main( String[] args ) throws InterruptedException, ExecutionException {
        int N = 10;
        Node head = null;

        // Create a linked list with values from 1 to N
        for( int i = 1 ; i <= N ; i++ ){
            head = append( head, i );
        }

        System.out.println( "Original List:" );
        printList( head );

        // Convert linked list to an array
        List<Integer> dataArray = listToArray( head );

        // Use ForkJoinPool for parallel processing
        ForkJoinPool pool = new ForkJoinPool();
        List<Integer> processedArray = pool.submit(() ->
            dataArray.parallelStream().map( num -> num * 2 ).toList()
        ).get();

        // Update the linked list with processed data
        arrayToList( head, processedArray );

        System.out.println( "Processed List (Each value multiplied by 2):" );
        printList( head );
    }
}
