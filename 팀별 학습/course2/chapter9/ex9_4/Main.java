package course2.chapter9.ex9_4;

import course2.chapter9.BinarySortTree;
import course2.chapter9.Node;
import course2.chapter9.SingleLinkedList;

public class Main {
    public static void main(String[] args) {
        int[] numList = {13,8,5,7,9,22,6};

        BinarySortTree<Integer> binarySortTree = new BinarySortTree<>();
        for (int num : numList) {
            binarySortTree.insert(num);
        }

        SingleLinkedList<Node<Integer>> queue = new SingleLinkedList<>();
        queue.add(binarySortTree.getRootNode());
        while (queue.size() != 0) {
            Node<Integer> node = queue.remove();
            System.out.println(node.getValue());
            if(node.leftChild!=null){
                queue.add(node.leftChild);
            }
            if(node.rightChild!=null){
                queue.add(node.rightChild);
            }
        }
    }
}
