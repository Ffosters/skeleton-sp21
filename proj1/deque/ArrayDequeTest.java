package deque;

import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    public void ArrayIteratorTest(){
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        ArrayDeque<Integer> a2 = new ArrayDeque<>();
        for(int i = 0; i < 4 ; i++){
            a1.addLast(i);
            a2.addLast(i);
        }
        Iterator<Integer> i1 = a1.iterator();
        while(i1.hasNext()){
            System.out.print(i1.next() + " ");
        }

//        assertEquals("l1 VS l2",true, a1.equals(a2));
    }

    @Test
    public void fillTest(){
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        for(int i = 0; i < 8 ; i++){
            a1.addFirst(i);
        }

        //assertEquals("",true, a1);
        assertEquals("fill up : ",8, a1.size());
        for(int i = 0; i < 9 ; i++){
            a1.removeFirst();
        }
        //assertEquals("Empty",0, a1.size());

    }

    @Test
    public void test1(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
        }

        for (int i = 0; i < 5; i++) {
            deque.removeFirst();
        }

        for (int i = 10; i < 20; i++) {
            deque.addFirst(i);
        }

        // 应该输出 19 18 17 16 15 14 13 12 11 10 4 3 2 1 0
        while (deque.size() > 0) {
            System.out.print(deque.removeFirst() + " ");
        }
        System.out.println();
    }



}
