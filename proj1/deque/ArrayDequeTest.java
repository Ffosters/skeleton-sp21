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
    public void testGetHead(){
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        for(int i = 0; i < 8 ; i++){
            a1.addLast(i);
        }
        int i1 = a1.removeFirst();
        a1.removeFirst();

        int i2 = a1.get(0);
        int i3 = a1.get(3);
        System.out.println("i1 = " +  i1);
        System.out.println("i2 = " +  i2);
        System.out.println("i3 = " +  i3);
//        assertEquals("head0",0, i1);
//        assertEquals("head1",1, i2);
//        assertEquals("head3",2, i3);
    }
}
