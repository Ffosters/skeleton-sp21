package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;
public class MaxArrayTest {
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
    @Test
    public void testMaxWithDefaultComparator() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntComparator());
        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(4);
        deque.addLast(2);

        assertEquals((Integer) 4, deque.max()); // 4 是最大值
    }

    @Test
    public void testMaxWithCustomComparator() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntComparator());
        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(4);
        deque.addLast(2);

        Comparator<Integer> reverseComparator = (o1, o2) -> o2 - o1;

        assertEquals((Integer) 1, deque.max(reverseComparator)); // 1 是最小值（反向比较）
    }

    @Test
    public void testMaxOnEmptyDeque() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntComparator());
        assertNull(deque.max());
    }



}
