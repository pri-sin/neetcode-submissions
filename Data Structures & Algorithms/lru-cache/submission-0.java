class LRUCache {

    class Node{
        int key, value;
        Node next, prev;

        public Node(int key, int val){
            this.key=key;
            this.value=val;
        }
    }

    int capacity;
    Map<Integer, Node> map;
    Node head, tail;


    public LRUCache(int capacity) {
        this.capacity=capacity;
        map=new HashMap<>();
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node n=map.get(key);
        moveToHead(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)){
            if(map.size()>=capacity){
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            Node newNode=new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);
        }else{
            Node n=map.get(key);
            n.value=value;
            map.put(key, n);
            moveToHead(n);
        }
    }

    public void addNode(Node node){
        node.next=head.next;
        head.next.prev=node;
        node.prev=head;
        head.next=node;
    }

    public void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }
}
