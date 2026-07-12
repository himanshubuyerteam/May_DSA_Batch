import org.w3c.dom.Node;

class ll2{
    public ListNode oddEvenList(ListNode head) {

        if(head == null || head.next==null)
            return head;
        ListNode oddHead = head;
        ListNode oddTail = head;


        ListNode evenHead = head.next;
        ListNode evenTail = head.next;

        ListNode temp = head.next.next;
        int c = 2;

        while(temp!=null)
        {
            c++;

            if(c%2==0) //C is ->Even 
            {
                evenTail.next = temp;
                evenTail = evenTail.next;
            }
            else
            {
                oddTail.next = temp;
                oddTail = oddTail.next;
            }

            temp = temp.next;
        } 

        oddTail.next = evenHead;
        evenTail.next = null;
        return oddHead;
    }

    Node divide(Node head) {
        // code here
        Node evenHead = new Node(-1);
        Node oddHead = new Node(-1);
        
        
        Node oddTail = oddHead;
        Node evenTail = evenHead;
        
        Node temp = head;
        
        while(temp!=null)
        {
            int val = temp.data;
            
            if(val % 2 == 0)
            {
                evenTail.next = temp;
                evenTail = evenTail.next;
            }
            else
            {
                oddTail.next = temp;
                oddTail = oddTail.next;
            }
            temp= temp.next;
        }
        evenTail.next = oddHead.next;
        oddTail.next = null;
        return evenHead.next;
    }

    public void addNewCloneNode(Node head)
    {
        Node temp = head;

        while(temp!=null)
        {
            Node dashNode = new Node(temp.val);
            Node future = temp.next;

            temp.next = dashNode;
            dashNode.next = future;

            temp = future;
        }
    }

    public void setUpRandom(Node head)
    {
        while(head!=null)
        {
            if(head.random!=null) // original Nodes
            {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    public Node withDrawCloneLinkedList(Node head)
    {
        Node dummyHead = new Node(-1);
        Node dummyTail = dummyHead;

        while(head!=null)
        {
            dummyTail.next = head.next;
            head.next = head.next.next;
            dummyTail = dummyTail.next;
            head = head.next;
        }

        return dummyHead.next;
    }
    public Node copyRandomList(Node head) {
        //Step1
        addNewCloneNode(head);

        //Step2
        setUpRandom(head);

        //Step3
        return withDrawCloneLinkedList(head);


    }

    
}