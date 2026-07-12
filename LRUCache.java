class LRUCache {

    class Node
    {
        int key;
        int val;
        Node next;
        Node prev;
        Node(int a,int b)
        {
            this.key= a;
            this.val = b;
        }
        Node(){}
    }

    void addFirst(Node node)
    {
        Node currFirst = head.next;
        node.next = currFirst;
        currFirst.prev = node;
        head.next = node;
        node.prev= head;
        hm.put(node.key,node);
    }

    void removeNode(Node node)
    {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode; 
        hm.remove(node.key);
    }
    HashMap<Integer,Node>hm;
    int cap;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        hm = new HashMap<>();
        cap = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev= head;
    }
    
    public int get(int key) 
    {
        if(!hm.containsKey(key))    
            return -1;
        else
        {
            Node node = hm.get(key);
            removeNode(node);
            addFirst(node);
            return node.val;
        }
    }
    
    public void put(int key, int value) 
    {
        if(hm.containsKey(key))
        {
            Node node = hm.get(key);
            node.val = value;
            removeNode(node);
            addFirst(node);
        }   
        else // cache doesnot have the element
        {
            if(hm.size()==cap) // can you add More??
            {
                Node lastNode = tail.prev;
                removeNode(lastNode);
                Node newEntry = new Node(key,value);
                addFirst(newEntry);
            }
            else // you have size to add more
            {
                Node newEntry = new Node(key,value);
                addFirst(newEntry);
            }
        } 
    }
}
