package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Integer> aList1 = new AList<>();
        AList<Double> times = new AList<>();
        int[] testNum = new int[8];
        for(int i = 0; i < testNum.length; i++ ){

            testNum[i] = (int) (Math.pow(2,i) * 1000);
            Stopwatch sw =new Stopwatch();
            for(int j = 0; j < testNum[i]; j++){
                aList1.addLast(j);
            }
            double time = sw.elapsedTime();
            Ns.addLast(testNum[i]);
            times.addLast(time);

        }
        printTimingTable(Ns, times , Ns);




    }
}
