
public class LinkedList {
    private Node head;
    private Node tail;
    private int cnt = 0;

    public LinkedList()
    {
        this.head = null;
        this.tail = null;
    }

    public void addNode(int num)
    {

        cnt += 1;
        Node n = new Node(num, null);
        if (this.head == null)
        {
            this.head = n;
            this.tail = n;
        }
        else {
            Node temp = this.tail;
            this.tail = n;
            temp.setNext(this.tail);

        }
        /*
        cnt += 1;
        Node n = new Node(num, this.head);
        this.head = n;
        */

    }

    public Node getHead()
    {
        return this.head;
    }

    public int getSize()
    {
        return this.cnt;
    }

    public String getList()
    {
        String s = "";
        Node n = this.head;
        while (n != null)
        {
            s += n.getValue();
            if(n.getNext() != null)
            {
                s += " -> ";
            }
            n = n.getNext();
        }
        return s;
    }
}
