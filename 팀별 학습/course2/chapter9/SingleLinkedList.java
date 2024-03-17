package course2.chapter9;

import org.w3c.dom.Node;

public class SingleLinkedList<T> {
    private ListNode<T> head = null;
    private ListNode<T> tail = null;
    private int size=0;

    public void add(T value) {
        if (this.head == null) {
            this.head = new ListNode<>(value);
            this.tail = this.head;
        } else {
            this.tail.next = new ListNode<>(value);
            this.tail = this.tail.next;
        }
        size++;
    }

    public T remove(){
        if(this.head == null){
            return null;
        }
        T value = this.head.getValue();
        this.head = this.head.next;
        size--;
        return value;
    }

    public int size(){
        return this.size;
    }

    public String reverse(){
        StringBuilder sb = new StringBuilder();
        recursionForReverse(sb,this.head);
        return sb.toString();
    }

    private void recursionForReverse(StringBuilder sb,ListNode<T> node){
        if(node==null){
            return;
        }
        recursionForReverse(sb,node.next);
        sb.append(node.getValue());
    }
}
