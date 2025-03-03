/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** Buggy implementation of nextNumber! */
    public static int nextNumber(int n) {
//        if (n  == 128) {
//            return 1;
//        } else if (n == 5) {
//            return 3 * n + 1;
//        } else {
//            return n * 2;
//        }
        if(n == 1){
            System.out.print(n);
            return 1;}
        else
        {
            System.out.print(n+" ");
            return nextNumber(n % 2 == 0 ? n/2 : 1+3 * n);
           // return n % 2 == 0 ? n/2 : 1+3 * n;
        }

    //nextNumber(n % 2 == 0 ? n/2 : 1+3 * n);
    }

    public static void main(String[] args) {
        int n = 5;
//        System.out.print(n + " ");
//        while (n != 1) {
//            n = nextNumber(n);
//            System.out.print(n + " ");
//        }
        nextNumber(n);


    }
}

