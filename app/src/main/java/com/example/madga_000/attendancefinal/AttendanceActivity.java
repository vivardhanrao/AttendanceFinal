package com.example.madga_000.attendancefinal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by madga_000 on 1/30/2016.
 */
public class AttendanceActivity extends AppCompatActivity
{
    String sec_name="",class2="",absenteesString;
    TextView date_tv,absenteeslist;
    String time_half ="";
    RadioButton first_rd, second_rd;
    Button sub_btn;
    SQLiteDatabase attendancedb;
    ListView htno_lv;
    List<String> adapter_htno,absentees_htno ;
    List<Integer> count;
    List<Selection> selectionList;
    int no_stud=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendace);
        date_tv=(TextView)findViewById(R.id.tv_date);
        first_rd=(RadioButton)findViewById(R.id.rd_first);
        second_rd =(RadioButton)findViewById(R.id.rd_second);
        sub_btn=(Button)findViewById(R.id.bt_sub);
        htno_lv=(ListView)findViewById(R.id.lv_attendance);



        Intent intent = getIntent();
        sec_name = intent.getStringExtra("class1");
        class2 = intent.getStringExtra("class2");

        attendancedb = openOrCreateDatabase("AttendanceDB", Context.MODE_PRIVATE, null);

        setTitle(sec_name);

        adapter_htno = new ArrayList<String>();
        absentees_htno = new ArrayList<String>();

        count = new ArrayList<Integer>();
        for(int i=0;i<100;i++){
            count.add(0);
        }

        selectionList = new ArrayList<Selection>();

        Cursor c = attendancedb.rawQuery("select * from "+class2+";", null);

        if (c.moveToFirst()) {
            do {
                String ht_no = c.getString(0);
                adapter_htno.add(ht_no);
                selectionList.add(new Selection(0));
            } while (c.moveToNext());

        }

        ArrayAdapter<String> adapter = new MyListAdapter();
        ListView list= (ListView) findViewById(R.id.lv_attendance);
        list.setAdapter(adapter);
        c.close();


        String date_str=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        date_tv.setText(date_str);

    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rd_first:
                if (checked)
                    time_half = first_rd.getText().toString();
                break;
            case R.id.rd_second:
                if (checked)
                    time_half = second_rd.getText().toString();
                break;
        }
    }



    public class MyListAdapter extends ArrayAdapter<String>{
        public MyListAdapter(){
            super(AttendanceActivity.this, R.layout.singletextactivity, adapter_htno);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            //final MyHolder holder;

            if(itemView==null){

                itemView=getLayoutInflater().inflate(R.layout.singletextactivity, parent, false);

                }




            final String currentStudent =adapter_htno.get(position);

            final Selection currentSelection = selectionList.get(position);

            TextView currentht= (TextView) itemView.findViewById(R.id.textView4);
            currentht.setText(currentStudent);


            ListView list= (ListView) findViewById(R.id.lv_attendance);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView text_ht = (TextView) view.findViewById(R.id.textView4);
                    absenteeslist = (TextView) findViewById(R.id.textAbsenteesList);



                    if (count.get(position) == 0) {
                        count.set(position, 1);
                        //text_ht.setTextColor(Color.parseColor("#F44336"));//change color on select
                        absentees_htno.add(adapter_htno.get(position));
                        no_stud++;


                    } else {
                        //text_ht.setTextColor(Color.GRAY);//change color on deselect
                        count.set(position, 0);
                        absentees_htno.remove(adapter_htno.get(position));
                        no_stud--;
                    }

                    sub_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(AttendanceActivity.this, AbsenteesActivity.class);
                            intent1.putStringArrayListExtra("absentees_htno", (ArrayList<String>) absentees_htno);
                            intent1.putExtra("class", sec_name);
                            intent1.putExtra("half", time_half);
                            intent1.putExtra("num_stud", no_stud);

                            attendancedb.close();
                            startActivity(intent1);
                        }
                    });

                    Collections.sort(absentees_htno, String.CASE_INSENSITIVE_ORDER);

                    String actualhtno;
                    actualhtno = absentees_htno.get(0);
                    StringBuilder fivedigits = new StringBuilder(actualhtno);
                    fivedigits.setCharAt(0, actualhtno.charAt(0));
                    fivedigits.setCharAt(1, actualhtno.charAt(1));
                    fivedigits.setCharAt(2, '-');
                    fivedigits.setCharAt(3, actualhtno.charAt(7));
                    fivedigits.setCharAt(4, actualhtno.charAt(8));
                    fivedigits.setCharAt(5, actualhtno.charAt(9));
                    fivedigits.setCharAt(6, '\0');
                    fivedigits.setCharAt(7, '\0');
                    fivedigits.setCharAt(8, '\0');
                    fivedigits.setCharAt(9, '\0');

                    absenteesString = fivedigits.toString();

                    for(int y=1; y<no_stud; y++){

                        actualhtno = absentees_htno.get(y);

                        StringBuilder fivedigit = new StringBuilder(actualhtno);
                        fivedigit.setCharAt(0, actualhtno.charAt(0));
                        fivedigit.setCharAt(1, actualhtno.charAt(1));
                        fivedigit.setCharAt(2, '-');
                        fivedigit.setCharAt(3, actualhtno.charAt(7));
                        fivedigit.setCharAt(4, actualhtno.charAt(8));
                        fivedigit.setCharAt(5, actualhtno.charAt(9));
                        fivedigit.setCharAt(6, '\0');
                        fivedigit.setCharAt(7, '\0');
                        fivedigit.setCharAt(8, '\0');
                        fivedigit.setCharAt(9, '\0');

                        absenteesString = absenteesString+"   "+fivedigit;
                    }

                    absenteeslist.setText(absenteesString);

                }
            });



            return itemView;
        }
    }


}
