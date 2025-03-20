package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] aArray;
    private int size;
    private int capacity = 8;
    private int front = 0;
    private int rear = 0;
    private double spaceAvaliable = 0.25;//0.25;

    /*      front 恒定向左移
            rear 恒定向右移
     */
    public ArrayDeque() {
        aArray = (T[]) new Object[capacity];                                                    //aArray = new Rice[capacity];
        size = 0;
    }


    public void addFirst(T value) {
        if (size == capacity) {
            resize(true);
        }
        front = (front - 1 + capacity) % capacity;  //左移
        aArray[front] = value;                      //存入数值
        size++;
    }

    public void addLast(T value) {
        if (size == capacity) {
            resize(true);
        }
        aArray[rear] = value;                   //存入数值
        rear = (rear + 1) % capacity;           //右移
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = aArray[front];
        aArray[front] = null;
        front = (front + 1) % capacity;
        size--;
        checkSize();
        return result;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = aArray[(rear - 1 + capacity) % capacity];
        aArray[(rear - 1 + capacity) % capacity] = null;
        rear = (rear - 1 + capacity) % capacity;
        size--;
        checkSize();
        return result;
    }

    public T get(int index) {
        index = (front + index) % capacity;
        if (aArray[index] == null) {
            return null;
        }
        return aArray[index];
    }



    /*
        这里应该用arraycopy更好一些 但是没有用 不改了 LLD也是一样的
        Q 19年作业有这个 21年没有 21年的可以提交
        所以需要注释掉
     */

//    public ArrayDeque(ArrayDeque other) {//aArray = new Rice[capacity];
//        size = 0;
//        aArray = (T[]) new Object[other.capacity];
//        this.capacity = other.capacity;
//        for (int i = other.front; i != other.rear; i = (i + 1) % capacity) {
//            this.addLast((T) other.aArray[i]);
//        }
//
//    }

    /*      --关于resize的修改问题
           最好不要修改类，直接修改 Rice 数组比较好
     */
    private void resize(boolean flag) {
        // ArrayDeque newArray = new ArrayDeque();
        int tCapacity = capacity;
        T[] newArray = (T[]) new Object[resetCapacity(flag)];
        //int index = front;
        // newArray只是rice类型的数组而这个数组并没有addlast方法；
        /*
            扩大数组与缩小数组时capaticy已经发生了变化需要有值保证它的不变
                true 是扩大
                false 是缩小
         */

        for (int i = 0; i < size(); i++) {                                //newArray.resetCapacity();
            newArray[i] = aArray[front];
            front = (front + 1) % tCapacity;
        }
        aArray = newArray;
        front = 0;
        rear = size();
    }

    private int resetCapacity(boolean flag) {
        if (flag) {
            return capacity *= 2;
        } else return capacity /= 2;
    }

//    public boolean isEmpty(){
//        return front == rear;
//    }

    //Iterator
    private class inIterator implements Iterator<T> {
        public int iFront;
        public int iRear;
        public int pos;

        public inIterator() {
            iFront = front;
            iRear = rear;
            pos = front;
        }

        @Override
        public boolean hasNext() {
            return pos != iRear;
        }

        @Override
        public T next() {
            T tData = (T) aArray[pos];
            pos = (pos + 1) % capacity;
            return tData;
        }
    }

    public Iterator<T> iterator() {
        return new inIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque) {
            ArrayDeque other = (ArrayDeque) o;
            if (other.size != size) {
                return false;
            }
            Iterator<T> origIter = this.iterator();
            Iterator<T> compIter = other.iterator();
            while (origIter.hasNext() && compIter.hasNext()) {
                if (origIter.next() != compIter.next()) {
                    return false;
                }  //不太清楚到底应该用 != 还是 equals
            }
        }
        return true;
    }


    public void printDeque() {
        for (int i = front; i != rear; i = (i + 1) % capacity) {
            System.out.print(aArray[i] + " ");
        }
        System.out.println();
    }

    private void checkSize() {
        if (capacity < 16 || size() / capacity > spaceAvaliable) {
            return;
        }
        if (size() < spaceAvaliable * capacity) {
            //resize(capacity / size());
            resize(false);
        }

    }

    public int size() {
        return size;
    }

}
