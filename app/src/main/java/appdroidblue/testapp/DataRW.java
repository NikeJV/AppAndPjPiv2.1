package appdroidblue.testapp;


import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;

public class DataRW extends AppCompatActivity {

    static BigDecimal Pi;
    static double dobK;
    static boolean ProcessEND;

    final String FILENAME = "tempt.out";

    public void ReadData() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            ObjectInputStream oin = new ObjectInputStream(fis);
            DataSerializable dataSerializable = (appdroidblue.testapp.DataSerializable) oin.readObject();
            Pi = dataSerializable.DataOutPi();
            dobK = dataSerializable.DataOutK();
            ProcessEND = dataSerializable.CheckOutSerializable();

            System.out.println("File read - OK");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File read - ERROR");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("oin - ERROR");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Read Object - ERROR");
        }
    }

    public BigDecimal RDoutPi() {
        return Pi;
    }

    public double RDoutK() {
        return dobK;
    }

    public boolean RDoutProcessEND() {
        return ProcessEND;
    }


    public void WriteData() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_WORLD_READABLE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            DataSerializable dataSerializable = new DataSerializable();
            oos.writeObject(dataSerializable);
            oos.flush();
            oos.close();
            System.out.println("File saved");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File save - ERROR");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("oos - ERROR");
        }
    }

}
