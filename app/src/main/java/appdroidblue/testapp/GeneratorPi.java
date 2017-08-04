package appdroidblue.testapp;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeneratorPi {
    static BigDecimal pi;
    static BigDecimal el1;
    static BigDecimal el2;
    static BigDecimal el3;
    static BigDecimal el4;
    static BigDecimal el5;
    static BigDecimal el6;
    static BigDecimal el7;
    static BigDecimal el8;
    static BigDecimal kDec;

    static double k = 0;
    static double n = 0;
    static int round = 0;
    static boolean ENDprocess = false;

    public void genInputData(int k, int n, int round){

    }

    public BigDecimal genPiUI(int k, int n, int round) {

        for (;k < n; k++) {
            kDec = BigDecimal.valueOf(k);
            el1 = pow(new BigDecimal("-1"), kDec).divide(pow(new BigDecimal("1024"), kDec), round, RoundingMode.HALF_UP);
            el2 = new BigDecimal("256").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("1")), round, RoundingMode.HALF_UP);
            el3 = new BigDecimal("1").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("9")), round, RoundingMode.HALF_UP);
            el4 = new BigDecimal("64").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("3")), round, RoundingMode.HALF_UP);
            el5 = new BigDecimal("32").divide(new BigDecimal("4").multiply(kDec).add(new BigDecimal("1")), round, RoundingMode.HALF_UP);
            el6 = new BigDecimal("4").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("5")), round, RoundingMode.HALF_UP);
            el7 = new BigDecimal("4").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("7")), round, RoundingMode.HALF_UP);
            el8 = new BigDecimal("1").divide(new BigDecimal("4").multiply(kDec).add(new BigDecimal("3")), round, RoundingMode.HALF_UP);
            pi = pi.add(el1.multiply(el2.add(el3).subtract(el4).subtract(el5).subtract(el6).subtract(el7).subtract(el8)));
            System.out.println("PI--------" + k);
        }

        pi = pi.multiply(new BigDecimal("1")).divide(pow(new BigDecimal("2"), new BigDecimal("6")), round, RoundingMode.HALF_UP);

        System.out.print(pi);
        return pi;
    }

    public BigDecimal genPiNewThread() {

        ENDprocess = false;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;k < n; k++) {
                    kDec = BigDecimal.valueOf(k);
                    el1 = pow(new BigDecimal("-1"), kDec).divide(pow(new BigDecimal("1024"), kDec), round, RoundingMode.HALF_UP);
                    el2 = new BigDecimal("256").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("1")), round, RoundingMode.HALF_UP);
                    el3 = new BigDecimal("1").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("9")), round, RoundingMode.HALF_UP);
                    el4 = new BigDecimal("64").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("3")), round, RoundingMode.HALF_UP);
                    el5 = new BigDecimal("32").divide(new BigDecimal("4").multiply(kDec).add(new BigDecimal("1")), round, RoundingMode.HALF_UP);
                    el6 = new BigDecimal("4").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("5")), round, RoundingMode.HALF_UP);
                    el7 = new BigDecimal("4").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("7")), round, RoundingMode.HALF_UP);
                    el8 = new BigDecimal("1").divide(new BigDecimal("4").multiply(kDec).add(new BigDecimal("3")), round, RoundingMode.HALF_UP);
                    pi = pi.add(el1.multiply(el2.add(el3).subtract(el4).subtract(el5).subtract(el6).subtract(el7).subtract(el8)));
                    System.out.println("PI--------" + k);
                }

                pi = pi.multiply(new BigDecimal("1")).divide(pow(new BigDecimal("2"), new BigDecimal("6")), round, RoundingMode.HALF_UP);
                System.out.print(pi);
                ENDprocess = true;
            }
        }).start();
        if (ENDprocess == true) return pi;
        return null;
    }

    public void genPiQuadThread(int k, int n, int round) {

    }

    public static BigDecimal pow(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(StrictMath.pow(x.doubleValue(), y.doubleValue()));
    }
}
