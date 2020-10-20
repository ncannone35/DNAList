public class LList {
  
    private Node head; // head of list
    private Node tail; // tail of list
    private int n;//counts size
    
    public LList() {
    	head = null;
        tail = null;
    }
  
    // Linked list Node. 
    // This inner class is made static 
    // so that main() can access it 
    static class Node { 
  
        private String data; 
        private Node next; 
  
        // Constructor 
        Node(String d) 
        { 
            data = d; 
            next = null; 
        } 
     // Various Constructors
        Node(String it, Node nextval) { data = it;  next = nextval; }

        Node(Node nextval) { next = nextval; }

        Node(){ }

        Node getNext() { return next; }  // getter for the <next> pointer

        Node setNext(Node nextval) // setter for <next>
        { return next = nextval; }

        String getItem() { return data; }  // getter for <item>

        String setItem(String it) { return data = it; } // setter for <item>
    } 
    public boolean isEmpty()    { return n==0; } // check empty list

    public int size()           { return n; } // get current # of items in list

    // add items from the head of the list
    public void addFirst(String it){
          head = new Node(it, head);
          n++;
    }

    // remove items from the head of the list
    public String removeFirst(){
      String it = head.getItem();
      head = head.next;
      n--;
      return it;
    }
    // Method to insert a new node 
    public static LList insert(LList list, String data) 
    { 
        // Create a new node with given data 
        Node new_node = new Node(data); 
        new_node.next = null; 
  
        // If the Linked List is empty, 
        // then make the new node as head 
        if (list.head == null) { 
            list.head = new_node; 
        } 
        else { 
            // Else traverse till the last node 
            // and insert the new_node there 
            Node last = list.head; 
            while (last.next != null) { 
                last = last.next; 
            } 
  
            // Insert the new_node at last node 
            last.next = new_node; 
        } 
  
        // Return the list by head 
        return list; 
    } 
 // Method to print the LinkedList. 
    public static String formatList(LList list) 
    { 
    	String l = "";
        Node currNode = list.head;
        // Traverse through the LinkedList 
        while (currNode != null) {  
            l += currNode.data; 
            // Go to next node 
            currNode = currNode.next; 
        } 
        return l;
    }
}