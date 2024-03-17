package course2.chapter10.ex10_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static Collection<Integer> collection;

    public static void main(String[] args) {
        removeTest();
        retainTest();
        collectTest();
        findTest();
    }


    public static void removeTest() {
        collection = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Predicates.remove(collection, x -> x % 2 == 0);
        System.out.println(collection);
    }

    public static void retainTest() {
        collection = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Predicates.retain(collection, x -> x % 2 == 0);
        System.out.println(collection);
    }

    public static void collectTest() {
        collection = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        System.out.println(Predicates.collect(collection, x -> x % 3 == 0));
    }

    public static void findTest() {
        collection = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        System.out.println(Predicates.find((ArrayList<Integer>) collection, x -> x % 2 == 0));

        collection = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        System.out.println(Predicates.find((ArrayList<Integer>) collection, x -> x % 7 == 0));
    }
}
