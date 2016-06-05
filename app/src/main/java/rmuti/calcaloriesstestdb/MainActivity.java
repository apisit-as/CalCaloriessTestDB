package rmuti.calcaloriesstestdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // เป็น String ทั้งหมด
    String calbmi ="18.61";  // bmi หน้าแรก
    String sum = "1413";   // หน้าสุดท้าย ส่วนแรก
    String BMI = "412";    // หน้าสุดท้าย ส่วนสอง

    String date_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // กด save
    public void doSave(View v){
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("calbmi",calbmi);
        intent.putExtra("sum", sum);
        intent.putExtra("BMI", BMI);

        date_time();
        intent.putExtra("date_time", date_time);
        startActivity(intent);
    }
    public void date_time(){
        // Current Date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy"); // date d-m-y
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); //  time H,m,s
        date_time = "วันที่:"+date.format(c.getTime())+"   เวลา:"+time.format(c.getTime());
    }

}
