package course2.chapter9.ex9_5;

import course2.chapter9.BinarySortTree;
import course2.chapter9.Node;
import course2.chapter9.SingleLinkedList;

public class Main {
    private static BinarySortTree<Double> binarySortTree = new BinarySortTree<>();
    private static boolean[] visited;

    public static void main(String[] args) {
//        for (int i = 0; i < 1023; i++) {
//            binarySortTree.insert(Math.random() * 1000);
//        }
        binarySortTree.insert(13.0);
        binarySortTree.insert(8.0);
        binarySortTree.insert(15.0);
        binarySortTree.insert(7.0);
        binarySortTree.insert(9.0);
        binarySortTree.insert(14.0);
        binarySortTree.insert(16.0);
        binarySortTree.insert(19.0);

        System.out.println("Average depth of leaves:  " + (findSumOfDepth(binarySortTree.getRootNode(),0))/countLeafNode(binarySortTree.getRootNode()));
        System.out.println("Maximum depth of leaves:  " + findMaximumDepth(binarySortTree.getRootNode(),0));
    }

    public static <T> int countLeafNode(Node<T> node) {
        if(node==null){
            return 0;
        }
        if(node.rightChild == null && node.leftChild == null){
            return 1;
        }
        return countLeafNode(node.leftChild) + countLeafNode(node.rightChild);
    }

    public static <T> int findSumOfDepth(Node<T> node, int depth) {
        if(node==null){
            return 0;
        }

        if(node.rightChild ==null && node.leftChild==null){
            return depth;
        }
        return findSumOfDepth(node.leftChild,depth+1)+findSumOfDepth(node.rightChild,depth+1);
    }

    public static <T> int findMaximumDepth(Node<T> node, int depth) {
       if(node == null){
           return 0;
       }
        if(node.rightChild ==null && node.leftChild==null){
            return depth;
        }
        return Math.max(findMaximumDepth(node.rightChild,depth+1),findMaximumDepth((node.leftChild),depth+1));
    }
}
