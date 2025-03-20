package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        Iterator<T> iter = iterator();
        T maxItem = iter.next();
        while (iter.hasNext()) {
            T nextI = iter.next();
            maxItem = comparator.compare(maxItem, nextI) > 0 ? maxItem : nextI;
            /*
               maxItem = comparator.compare(maxItem,iter.next()) > 0  ? maxItem : iter.next();
                有问题 每次item.next() 他会进行到下一个而不是 所以不能这样写

             */
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        Iterator<T> iter = iterator();
        T maxItem = iter.next();
        while (iter.hasNext()) {
            T nextI = iter.next();
            maxItem = c.compare(maxItem, nextI) > 0 ? maxItem : nextI;
        }
        return maxItem;

    }


}
