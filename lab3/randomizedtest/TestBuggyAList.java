package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        System.out.println("-------Test------- 1");
        AListNoResizing<Integer> aListR = new AListNoResizing<Integer>();
        BuggyAList<Integer> byAList = new BuggyAList<Integer>();
        for(int i = 0; i < 5; i++){
            aListR.addLast(i);
            byAList.addLast(i);
        }
        for(int i = 0; i < 5; i++){
            assertEquals(aListR.size(), byAList.size());   //也许也要检查 这个没有检查 看答案了
            assertEquals(aListR.removeLast(), byAList.removeLast());
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> aList = new AListNoResizing<>();
        BuggyAList<Integer> byAList = new BuggyAList<Integer>();

        int N = 900;
        for (int i = 0; i < N; i += 1) {
            System.out.println("-------Test------- " + i + " ---------Test-------");
            int operationNumber = StdRandom.uniform(0, 3);  //StdRandom.uniform(0, 2)函数 返回一个范围在 [0, 2) 内的随机整数
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                System.out.println("addLast(" + randVal + ")");
                aList.addLast(randVal);
                byAList.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else  {
                // size
                int sizeAlist = aList.size();
                int sizeBlist = byAList.size();

                System.out.println("sizeA: " + sizeAlist + "  sizeB: " + sizeBlist);
                //(sizeAlist & sizeBlist) > 0 && operationNumber == 1
                if (sizeAlist > 0 && sizeBlist > 0 && operationNumber == 1 ){  //getLastTest
                    //assertEquals(aList.size(), byAList.size());
                    System.out.println("getLastA (" + aList.getLast() + " )"+
                            "  getLastB ( " + byAList.getLast() + " )" );
                    assertEquals(aList.getLast(),byAList.getLast());


                }
                else if(sizeAlist > 0 && sizeBlist > 0  && operationNumber == 2 ){
                    System.out.println("removeA (" + aList.getLast() + " )"+
                            "  removeB ( " + byAList.getLast() + " )" );
                    int A = aList.removeLast();
                    int B = byAList.removeLast();
                    assertEquals(A,B);
                    assertEquals(aList.size(), byAList.size());
//                    System.out.println("removeLastA ( " + A + " )"+
//                            "removeLastB ( " + B + " )" );
                }


            }
        }
    }


}
