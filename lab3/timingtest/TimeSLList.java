package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        int multi = 8;
        int[] testNum =new int[multi];
        AList<Integer> Ns = new AList<>();
       // SLList<Integer> aList1 = new SLList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opT = new AList<>();

        // TODO: YOUR CODE HERE
        for(int i = 0; i < testNum.length; i++ ){
            SLList<Integer> aList1 = new SLList<>();
            testNum[i] = (int) (Math.pow(2,i) * 1000);
            for(int j = 0; j < testNum[i]; j++){
                aList1.addLast(j);
            }
            opT.addLast(10000);
            Stopwatch sw =new Stopwatch();
            for(int j = 0; j < 10000 ; j++){
                aList1.getLast();
            }
            double time = sw.elapsedTime();
            Ns.addLast(testNum[i]);
            times.addLast(time);
            printTimingTable(Ns, times , opT);
        }
       printTimingTable(Ns, times , Ns);
    }

}
