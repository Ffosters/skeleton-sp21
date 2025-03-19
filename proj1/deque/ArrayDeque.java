package deque;
import java.util.Iterator;

public class ArrayDeque<Rice> implements Iterable<Rice>,Deque<Rice> {
    private Rice[] aArray;
    private int size;
    public int capacity = 8;
    private int front = 0;
    private int rear = 0;
    private double spaceAvaliable = 0.25;//0.25;
/*      front 恒定向左移
        rear 恒定向右移
 */
    public ArrayDeque(){
        aArray = (Rice[]) new Object[capacity];                                                    //aArray = new Rice[capacity];
        size = 0;
    }



    public void addFirst(Rice value){
        if(size == capacity){resize(true);}
        front = (front - 1 + capacity) % capacity;  //左移
        aArray[front] = value;                      //存入数值
        size++;
    }
    public void addLast(Rice value){
        if(size == capacity){resize(true);}
        aArray[rear] = value;                   //存入数值
        rear = (rear + 1) % capacity;           //右移
        size++;
    }

    public Rice removeFirst(){
        if(size == 0){return null;}
        Rice result = aArray[front];
        aArray[front] = null;
        front = (front + 1) % capacity;
        size--;
        checkSize();
        return result;
    }

    public Rice removeLast(){
        if(size == 0){return null;}
        Rice result = aArray[(rear - 1 + capacity) % capacity];
        aArray[(rear - 1 + capacity) % capacity] = null;
        rear = (rear - 1 + capacity) % capacity;
        size--;
        checkSize();
        return result;
    }

    public Rice get(int index){
        index = (front + index) % capacity;
        if(aArray[index] == null){return null;}
        return aArray[index];
    }



    /*
        这里应该用arraycopy更好一些 但是没有用 不改了 LLD也是一样的
     */

    public ArrayDeque(ArrayDeque other){//aArray = new Rice[capacity];
        size = 0;
        aArray = (Rice[]) new Object[other.capacity];
        this.capacity = other.capacity;
        for(int i = other.front; i != other.rear ; i = (i + 1) % capacity){
            this.addLast((Rice)other.aArray[i]);
        }

    }

    /*      --关于resize的修改问题
           最好不要修改类，直接修改 Rice 数组比较好
     */
    public void resize(boolean flag){
       // ArrayDeque newArray = new ArrayDeque();
        Rice[] newArray = (Rice[]) new Object[resetCapacity(flag)];
                                                                    //newArray只是rice类型的数组而这个数组并没有addlast方法；
        for(int i = 0; i < size(); i++){                                //newArray.resetCapacity();
            newArray[i] = aArray[front];
            front =  (front + 1) % (capacity / 2);
        }
        aArray = newArray;
        front = 0;
        rear = size();
    }

    public int resetCapacity(boolean flag){
        if(flag) {return capacity *= 2;}
        else return capacity/=2;
    }

//    public boolean isEmpty(){
//        return front == rear;
//    }

    //Iterator
    private class inIterator implements Iterator<Rice>{
        public int iFront;
        public int iRear;
        public int pos;
        public inIterator(){
            iFront = front;
            iRear = rear;
            pos = front;
        }

        @Override
        public boolean hasNext(){
            return pos != iRear;
        }

        @Override
        public Rice next(){
            Rice tData = (Rice)aArray[pos];
            pos = (pos + 1) % capacity;
            return tData;
        }
    }

    public Iterator<Rice> iterator(){
        return new inIterator();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof ArrayDeque){
            ArrayDeque other = (ArrayDeque) o;
            if(other.size != size){return false;}
            Iterator<Rice> origIter = this.iterator();
            Iterator<Rice> compIter = other.iterator();
            while(origIter.hasNext() && compIter.hasNext()){
                if(origIter.next() != compIter.next()){return false;}  //不太清楚到底应该用 != 还是 equals
            }
        }
        return true;
    }





    public void printDeque(){
        for(int i = front; i != rear; i = (i + 1) % capacity){
            System.out.print(aArray[i]+ " ");
        }
        System.out.println();
    }

    private void checkSize(){
        if(capacity < 16 || size() / capacity > spaceAvaliable){return;}
        if(size() < spaceAvaliable * capacity){
            //resize(capacity / size());
            resize(false);
        }

    }

    public int size(){return size;}

}
