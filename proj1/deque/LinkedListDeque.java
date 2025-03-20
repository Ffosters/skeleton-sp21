package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private IntNode sentinelF;
    private IntNode sentinelL;
    private int size;

    /*  关于sentinel的处理
有两种方式   1.本类所写的 两个哨兵一头一尾
            2.形成环    头尾相连 只要一个 sentinel sentinel指向头
     */
    public class IntNode {
        public IntNode prev;
        public IntNode next;
        public T data;

        public IntNode(T d, IntNode p, IntNode n) {
            data = d;
            prev = p;
            next = n;
        }

    }

    public LinkedListDeque() {
        size = 0;
        sentinelF = new IntNode(null, null, null);              //sentinelF = new IntNode(null,null,sentinelL); //因为sentinelL还未创建所以这样是无效的
        sentinelL = new IntNode(null, null, null);
        sentinelF.next = sentinelL;
        sentinelL.prev = sentinelF;//sentinelL = new IntNode(null,sentinelF,null);
    }


        /*
            19年的作业要求 21年不需要

         */
//    public LinkedListDeque(LinkedListDeque other) {
//        //LinkedListDeque newLLD = new LinkedListDeque();
//        size = 0;
//        sentinelF = new IntNode(null, null, null);
//        sentinelL = new IntNode(null, null, null);
//        sentinelF.next = sentinelL;
//        sentinelL.prev = sentinelF;
//        IntNode s = other.sentinelF.next;
//        while (s != other.sentinelL) {
//            this.addLast(s.data);
//            s = s.next;
//        }
//
//    }

    //添加
    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("can't add null");
        }
        IntNode t = sentinelF.next;
        sentinelF.next = new IntNode(data, sentinelF, sentinelF.next);
        t.prev = sentinelF.next;
        size++;
    }

    public void addLast(T data) {
        if (data == null) {
            throw new IllegalArgumentException("can't add null");
        }
        IntNode t = sentinelL.prev;
        sentinelL.prev = new IntNode(data, sentinelL.prev, sentinelL);
        t.next = sentinelL.prev;
        size++;
    }

    //删除
    public T removeLast() {
        //是否要查空？
        //if(sentinelL.prev == null){return;}
        if (isEmpty()) {
            return null;
        }
        T item = (T) sentinelL.prev.data;
        IntNode t = sentinelL.prev.prev;
        t.next = sentinelL;
        sentinelL.prev = t;    //空指针异常？   不会 如果不是空，只会指向头 / 尾
        size--;
        return item;
    }

    public T removeFirst() {
        //if(sentinelF.next == null){return;}  //有问题 错误的 修正为下面的
        if (isEmpty()) {
            return null;
        }
        T item = (T) sentinelF.next.data;
        IntNode t = sentinelF.next.next;
        t.prev = sentinelF;
        sentinelF.next = t;
        size--;
        return item;
    }

    public int size() {
        return size;
    }

    //---------------------------------------
    //只要size == 0 就是空  也就可以默认所有都这样执行了
//    public boolean isEmpty(){
//        return sentinelF.next == sentinelL;
//    }

    private class inIterator implements Iterator<T> {
        IntNode curr;

        public inIterator() {
            curr = sentinelF.next;
        }

        @Override
        public boolean hasNext() {
            return curr != sentinelL;
        }

        @Override
        public T next() {
            T tApple = (T) curr.data;
            curr = curr.next;
            return tApple;
        }
    }

    public Iterator<T> iterator() {
        return new inIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } //如果是想比较两个不一样的 这时候应该是false吧
        if (o instanceof LinkedListDeque) {  //if(o instanceof LinkedListDeque oLLD) 这个版本不能使用
            LinkedListDeque oLLD = (LinkedListDeque) o;
            if (oLLD.size != this.size) {
                return false;
            }
            Iterator<T> origItr = this.iterator();
            Iterator<T> compItr = oLLD.iterator();
            while (origItr.hasNext() && compItr.hasNext()) {
                if (!origItr.next().equals(compItr.next())) {
                    return false;
                }
            }
        }
        return true;
    }


    public T get(int index) {
        if (isEmpty() || index > size) {
            return null;
        }
        IntNode t = sentinelF.next;
        while (index != 0) {
            t = t.next;
            index--;
        }
        return t.data;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        IntNode t = sentinelF.next;
        while (t != sentinelL) {
            System.out.print(t.data + " ");
            t = t.next;
        }
        System.out.println();
    }

    public T getRecursive(int index) {
        if (isEmpty() || index > size) {
            return null;
        }
        IntNode curr = sentinelF;
        return getWithRecursive(index, curr);


    }

    private T getWithRecursive(int index, IntNode curr) {
        if (index == 0) {
            return curr.data;
        }
        return getWithRecursive(--index,curr.next);
    }


}
