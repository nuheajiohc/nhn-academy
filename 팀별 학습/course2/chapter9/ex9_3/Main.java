package course2.chapter9.ex9_3;

import course2.chapter9.SingleLinkedList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        int[] orginalList = {1,2,3,4,5,6,7,8,9,10};
        for(int element : orginalList){
            singleLinkedList.add(element);
        }
        System.out.println(Arrays.toString(orginalList));
        System.out.println(singleLinkedList.reverse());
    }
}
