
public class Node {

    private int num;
    private Node next = null;
    private Node prev;

    public Node(int num, Node next)
    {
        this.num = num;
        this.next = next;
        this.prev = null;
    }

    public Node getNext()
    {
        return this.next;
    }

    public int getValue()
    {
        return this.num;
    }

    public void setNext(Node n)
    {
        this.next = n;
    }

}