package rmuti.calcaloriesstestdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    //database
    private SQLiteDatabase db;
    private Cursor c;
    int id;
    private ArrayAdapter adapter;
    String sql;

    // data
    String calbmi,sum,BMI;
    String date_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);

        // Database
        db = this.openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        sql = ""
                + " CREATE TABLE IF NOT EXISTS db_Test1("
                + "   id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "   date_time VARCHAR," + " calbmi VARCHAR," + " sum VARCHAR,"
                + "   BMI VARCHAR" +" )";
        db.execSQL(sql);

        //เรียกมาจาก MainActivity
        calbmi  = getIntent().getExtras().getString("calbmi");
        sum  = getIntent().getExtras().getString("sum");
        BMI  = getIntent().getExtras().getString("BMI");
        date_time = getIntent().getExtras().getString("date_time");


        // เพิ่มข้อมูลลง database
        sql = "";
        sql = "INSERT INTO db_Test1 VALUES(null, ':date_time', ':calbmi', ':sum', ':BMI')";
        sql = sql.replace(":date_time",date_time);
        sql = sql.replace(":calbmi", calbmi);
        sql = sql.replace(":sum", sum);
        sql = sql.replace(":BMI",BMI);
        db.execSQL(sql);

        //refresh listView
        bindData();

    }

    //-------------------------------------------------------------------------

    //refresh listView
    private void bindData(){
        sql = "";
        sql = "SELECT * FROM db_Test1";
        c = db.rawQuery(sql, null);

        int item = android.R.layout.simple_list_item_1;
        ArrayList data = new ArrayList();

        while(c.moveToNext()){
            int index = c.getColumnIndex("date_time"); //ต้องการส่วน date_time ใน dababase
            data.add(c.getString(index));
        }

        adapter = new ArrayAdapter(this, item, data);

        ListView myList = (ListView) findViewById(R.id.myList); //เรียก listView
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                //เมื่อมีการคลิกรายชื่อใน listView
                itemClick(i);
            }
        });
    }
    //-------------------------------------------------------------------------

    //เมื่อมีการคลิกรายชื่อใน listView
    public void itemClick(int index){
        c.moveToPosition(index);
        id = c.getInt(c.getColumnIndex("id"));
        setContentView(R.layout.edit_list);   //ไปที่หน้า edit_list

        TextView date_time = (TextView)findViewById(R.id.date_time);
        TextView calbmi = (TextView)findViewById(R.id.calbmi);
        TextView sum = (TextView)findViewById(R.id.sum);
        TextView bmi = (TextView)findViewById(R.id.BMI);

        date_time.setText(c.getString(c.getColumnIndex("date_time")));
        calbmi.setText(c.getString(c.getColumnIndex("calbmi")));
        sum.setText(c.getString(c.getColumnIndex("sum")));
        bmi.setText(c.getString(c.getColumnIndex("BMI")));
    }
    //-------------------------------------------------------------------------

    // edit_list
    //กดปุ่ม delete
    public void doDelete(View v){
        db.delete("db_Test1", "id = " + id, null);
        setContentView(R.layout.show_list);
        bindData();
    }
    //กดปุ่ม back
    public void doBack(View v){
        setContentView(R.layout.show_list);
        bindData();
    }
    //-------------------------------------------------------------------------


    public void doHome(View v){
        //กดปุ่ม home
    }

}
