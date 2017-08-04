package appdroidblue.testapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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


    static int k = 0;

    static int cycle = 103;

    static int round = 100;

    static long finish;
    static double timeConsumedMillis;
    static long start;
    static String strtime;

    static boolean processEND = true;
    static String Pi;

    final String SAVE_DATA_ID_PI = "PI";
    final String SAVE_DATA_ID_K = "K";
    final String SAVE_DATA_ID_PE = "PE";

    Button btn1;
    TextView tv1;
    TextView tvTime;
    EditText etRound;
    EditText etCycle;
    ProgressBar progressBar;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(this);

        tv1 = (TextView) findViewById(R.id.textView);

        tvTime = (TextView) findViewById(R.id.tvTime);

        etRound = (EditText) findViewById(R.id.etRound);
        etCycle = (EditText) findViewById(R.id.etcycle);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {

        //введення даних
        String strRound;
        String strCycle;
        try {
            strRound = etRound.getText().toString();
            round = Integer.valueOf(strRound);
            strCycle = etCycle.getText().toString();
            cycle = Integer.valueOf(strCycle);
            progressBar.setMax(cycle);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("ERROR-INPUT");
        }

        //обнулення значень обрахунку
        pi = new BigDecimal("0");
        k = 0;

        //очищую поле з часом
        tvTime.setText("");

        try {
            DataPref();
            processEND = sPref.getBoolean(SAVE_DATA_ID_PE, true);
        } catch (Exception e) {
            e.printStackTrace();
            processEND = true;
        }

        if (processEND == false){
            DataPref();
            k = sPref.getInt(SAVE_DATA_ID_K, 0);
            Pi = sPref.getString(SAVE_DATA_ID_PI, "");
            pi = new BigDecimal(Pi);
        }

        //обнулення progressBar
        progressBar.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(0);
            }
        });


        Thread PiThread = new Thread(new Runnable() {
            @Override
            public void run() {

                processEND = false;
                DataPref();
                SharedPreferences.Editor ed = sPref.edit();
                ed.putBoolean(SAVE_DATA_ID_PE, false);
                ed.commit();
                System.out.println("TEST_OUT_" + sPref.getBoolean(SAVE_DATA_ID_PE, true));

                //замір часу
                start = System.currentTimeMillis();

                //змінюю колір тексту кнопки
                btn1.post(new Runnable() {
                    @Override
                    public void run() {
                        btn1.setTextColor(Color.RED);
                    }
                });

                for (;k < cycle; k++) {
                    kDec = BigDecimal.valueOf(k);
                    //Формула Белларда
                    //максимальна кількість циклів 103 оскільки більше число не можливо піднести в степінь 1024
                    el1 = pow(new BigDecimal("-1"), kDec).divide(pow(new BigDecimal("1024"), kDec), round, RoundingMode.HALF_UP);
                    el2 = new BigDecimal("256").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("1")), round, RoundingMode.HALF_UP);
                    el3 = new BigDecimal("1").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("9")), round, RoundingMode.HALF_UP);
                    el4 = new BigDecimal("64").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("3")), round, RoundingMode.HALF_UP);
                    el5 = new BigDecimal("32").divide(new BigDecimal("4").multiply(kDec).add(new BigDecimal("1")), round, RoundingMode.HALF_UP);
                    el6 = new BigDecimal("4").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("5")), round, RoundingMode.HALF_UP);
                    el7 = new BigDecimal("4").divide(new BigDecimal("10").multiply(kDec).add(new BigDecimal("7")), round, RoundingMode.HALF_UP);
                    el8 = new BigDecimal("1").divide(new BigDecimal("4").multiply(kDec).add(new BigDecimal("3")), round, RoundingMode.HALF_UP);
                    pi = pi.add(el1.multiply(el2.add(el3).subtract(el4).subtract(el5).subtract(el6).subtract(el7).subtract(el8)));

                    DataPref();
                    ed.putString(SAVE_DATA_ID_PI, pi.toString());
                    ed.putInt(SAVE_DATA_ID_K, k);
                    ed.commit();

                    System.out.println("PI--------" + k);
                    btn1.post(new Runnable() {
                        @Override
                        public void run() {
                            btn1.setText(String.valueOf(k));
                        }
                    });
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(k);
                        }
                    });
                }

                pi = pi.multiply(new BigDecimal("1")).divide(pow(new BigDecimal("2"), new BigDecimal("6")), round, RoundingMode.HALF_UP);

                //замір часу
                finish = System.currentTimeMillis();
                timeConsumedMillis = finish - start;
                timeConsumedMillis = timeConsumedMillis / 1000;
                //strtime = String.valueOf(timeConsumedMillis);

                //виведення даних
                //вертаю назву і колір кнопці
                btn1.post(new Runnable() {
                    @Override
                    public void run() {
                        btn1.setText("BUTTON");
                        btn1.setTextColor(Color.BLACK);
                    }
                });
                // >> число pi
                tv1.post(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(pi.toString());
                    }
                });
                // >> час виконання
                tvTime.post(new Runnable() {
                    @Override
                    public void run() {

                        tvTime.setText(Math.round(timeConsumedMillis/60/60) + " h | " + Math.round(timeConsumedMillis/60) + " m || " + timeConsumedMillis + " s");
                    }
                });

                //обрахунок завершено запис маркера в память
                processEND = true;
                DataPref();
                ed.putBoolean(SAVE_DATA_ID_PE, processEND);
                ed.commit();
                System.out.println("TEST_OUT_" + sPref.getBoolean(SAVE_DATA_ID_PE, true));
            }
        });

        //запуск потоку для обрахунку pi
        PiThread.start();
    }

    //для піднесення в степінь чисел з великою точністю
    public static BigDecimal pow(BigDecimal x, BigDecimal y) {
        return BigDecimal.valueOf(StrictMath.pow(x.doubleValue(), y.doubleValue()));
    }

    public void DataPref() {
        sPref = getSharedPreferences("myPref", MODE_PRIVATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}
