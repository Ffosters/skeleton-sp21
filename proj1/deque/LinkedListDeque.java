package deque;
import java.util.Iterator;
public class LinkedListDeque<Apple> implements Iterable<Apple> {
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
        public Apple data;

        public IntNode(Apple d,IntNode p,IntNode n){
            data = d;
            prev = p;
            next = n;
        }

    }

    public LinkedListDeque(){
        size = 0;
        sentinelF = new IntNode(null,null,null);              //sentinelF = new IntNode(null,null,sentinelL); //因为sentinelL还未创建所以这样是无效的
        sentinelL = new IntNode(null,null,null);
        sentinelF.next = sentinelL;
        sentinelL.prev = sentinelF;//sentinelL = new IntNode(null,sentinelF,null);
    }

    public LinkedListDeque(LinkedListDeque other){
        //LinkedListDeque newLLD = new LinkedListDeque();
        size = 0;
        sentinelF = new IntNode(null,null,null);
        sentinelL = new IntNode(null,null,null);
        sentinelF.next = sentinelL;
        sentinelL.prev = sentinelF;
        IntNode s = other.sentinelF.next;
        while(s != other.sentinelL ){
            this.addLast(s.data);
            s = s.next;
        }

    }

    //添加
    public void addFirst(Apple data){
        if(data == null){throw new IllegalArgumentException("can't add null");}
        IntNode t = sentinelF.next;
        sentinelF.next = new IntNode(data,sentinelF,sentinelF.next);
        t.prev = sentinelF.next;
        size++;
    }

    public void addLast(Apple data){
        if(data == null){throw new IllegalArgumentException("can't add null");}
        IntNode t = sentinelL.prev;
        sentinelL.prev = new IntNode(data,sentinelL.prev,sentinelL);
        t.next = sentinelL.prev;
        size++;
    }

    //删除
    public Apple removeLast(){
        //是否要查空？
        //if(sentinelL.prev == null){return;}
        if(isEmpty()){return null;}
        Apple item = (Apple)sentinelL.prev.data;
        IntNode t = sentinelL.prev.prev;
        t.next = sentinelL;
        sentinelL.prev = t;    //空指针异常？   不会 如果不是空，只会指向头 / 尾
        size--;
        return item;
    }
    public Apple removeFirst(){
        //if(sentinelF.next == null){return;}  //有问题 错误的 修正为下面的
        if(isEmpty()){return null;}
        Apple item = (Apple)sentinelF.next.data;
        IntNode t = sentinelF.next.next;
        t.prev = sentinelF;
        sentinelF.next = t;
        size--;
        return item;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return sentinelF.next == sentinelL;
    }

    private class inIterator implements Iterator<Apple>{
        IntNode curr;
        public inIterator(){
                curr = sentinelF.next;
        }

        @Override
        public boolean hasNext(){
            return curr!= sentinelL;
        }

        @Override
        public Apple next(){
            Apple tApple = (Apple)curr.data;
            curr = curr.next;
            return tApple;
        }
    }

    public Iterator<Apple> iterator(){
        return new inIterator();
    }

    @Override
    public boolean equals(Object o){
        if(o == this){return true;} //如果是想比较两个不一样的 这时候应该是false吧
        if(o instanceof LinkedListDeque){  //if(o instanceof LinkedListDeque oLLD) 这个版本不能使用
            LinkedListDeque oLLD = (LinkedListDeque) o;
            if(oLLD.size != this.size){return false;}
            Iterator<Apple> origItr = this.iterator();
            Iterator<Apple> compItr = oLLD.iterator();
            while(origItr.hasNext() && compItr.hasNext()){
                if(!origItr.next().equals(compItr.next())){return false;}
            }
        }
        return true;
    }





    public Apple get(int index){
        if(isEmpty()){return null;}
        IntNode t = sentinelF.next;
        if(index > size){return null;}
        while(index != 0){
            t = t.next;
            index--;
        }
        return t.data;
    }

    public void printDeque(){
        if(isEmpty()){return;}
        IntNode t = sentinelF.next;
        while(t != sentinelL){
            System.out.print(t.data + " ");
            t = t.next;
        }
        System.out.println();
    }

    public Apple getRecursive(int index,IntNode curr) {
        if(curr == null){return null;}
        if(index == 0){return curr.data;}
        return getRecursive(index--,curr.next);
    }




}
