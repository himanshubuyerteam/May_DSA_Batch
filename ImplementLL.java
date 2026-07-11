class Node
{
    int val;
    Node next;
    Node(int a,Node b)
    {
        val = a;
        next = b;
    }
    Node(int a)
    {
        val = a;
    }
}
class ImplementLL
{
    
    private static Node head;

    public static void addFront(int num)
    {
        Node node = new Node(num);
        node.next = head;
        head = node;
    }

    public static void addLast(int num)
    {
        Node node = new Node(num);

        if(head == null)
        {
            head = node;
            return;
        }
        // LL empty
        Node temp = head;

        while(temp.next!=null)
        {
            temp = temp.next;
        }

        temp.next =node;
        // LL old Length
        // LL Even Length
    }
    public static void printLL(Node head)
    {
        Node temp = head;
        while(temp!=null)
        {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
    public static int Length(Node head)
    {
        Node temp = head;
        int size = 0;
        while(temp!=null)
        {
            temp = temp.next;
            size++;
        }
        return size;
    }
    public static void main(String[] args) {
        
        addLast(10);
        addLast(20);
        addLast(30);

        addFront(100);


        addLast(40);
        addLast(50);


        printLL(head);
    }
}