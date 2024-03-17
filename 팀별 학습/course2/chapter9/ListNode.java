package course2.chapter9;

public class ListNode <T> {
    private T value;
    ListNode<T> next;

    public ListNode(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return this.value;
    }
}
