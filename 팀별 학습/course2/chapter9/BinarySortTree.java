package course2.chapter9;

import java.util.ArrayList;
import java.util.List;

public class BinarySortTree <T extends Comparable<T>>{
    private Node<T> rootNode=null;

    public void insert(T element) {
        if (this.rootNode == null) {
            this.rootNode = new Node<>(element);
        } else {
            this.rootNode = findLeafNode(this.rootNode, new Node<>(element));
        }
    }

    private Node<T> findLeafNode(Node<T> node, Node<T> newNode) {
        if (node == null) {
            return newNode;
        } else if (node.getValue().compareTo(newNode.getValue())>0) {
            node.leftChild = findLeafNode(node.leftChild, newNode);
        } else {
            node.rightChild = findLeafNode(node.rightChild, newNode);
        }
        return node;
    }

    public String inorderTree() {
        StringBuilder sb = new StringBuilder();
        recursionForInorder(sb, this.rootNode);
        return sb.toString();
    }

    private void recursionForInorder(StringBuilder sb, Node node) {
        if (node != null) {
            recursionForInorder(sb, node.leftChild);
            sb.append(node.getValue()).append("\r\n");
            recursionForInorder(sb, node.rightChild);
        }
    }

    public Node<T> getRootNode(){
        return this.rootNode;
    }
}
