package course2.chapter10.ex10_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Predicates<T> {

    public static <T> void remove(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iter = coll.iterator();
        while (iter.hasNext()) {
            if (pred.test(iter.next())) {
                iter.remove();
            }
        }
    }

    public static <T> void retain(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iter = coll.iterator();
        while (iter.hasNext()) {
            if (!pred.test(iter.next())) {
                iter.remove();
            }
        }
    }

    public static <T> List<T> collect(Collection<T> coll, Predicate<T> pred) {
        remove(coll, pred);
        return (List<T>) coll;
    }

    public static <T> int find(ArrayList<T> list, Predicate<T> pred) {
        Iterator<T> iter = list.iterator();
        int index = -1;
        while (iter.hasNext()) {
            index++;
            if (pred.test(iter.next())) {
                return index;
            }
        }
        return -1;
    }
}
