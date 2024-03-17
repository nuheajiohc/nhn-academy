package course2.chapter9;

public class Node<T>{
    private final T value;
    public Node<T> leftChild;
    public Node<T> rightChild;

    public Node(T value){
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public T getValue(){
        return this.value;
    }
}
