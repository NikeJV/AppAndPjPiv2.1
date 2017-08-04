package appdroidblue.testapp;


import java.io.Serializable;
import java.math.BigDecimal;

// Клас серіалізації
public class DataSerializable implements Serializable{

    static BigDecimal Pi;
    static double dobK;
    static boolean ProcessEND;

    public void DataInSerializable(BigDecimal pi, double k) {
        Pi = pi;
        dobK = k;
    }

    public void CheckInSerializable(boolean processEND) {
        ProcessEND = processEND;
    }

    public BigDecimal DataOutPi() {
        return Pi;
    }

    public double DataOutK() {
        return dobK;
    }

    public boolean CheckOutSerializable() {
        return ProcessEND;
    }
}
