package com.example.madga_000.attendancefinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by madga_000 on 1/31/2016.
 */
public class AbsenteesActivity extends AppCompatActivity implements View.OnClickListener {
    String date_str="",sec_name="",half="",ht_nums="",name_fac="";
    public static final String PREFS_NAME = "MyPrefsFile";
    TextView fac_tv,half_tv;
    int count=0;
    Button send_mail;
    List<String> absentees_htno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absentees);
        fac_tv=(TextView)findViewById(R.id.tv_facname);
        half_tv=(TextView)findViewById(R.id.tv_half);
        send_mail=(Button)findViewById(R.id.btn_send);

        Intent intent=getIntent();
        sec_name=intent.getStringExtra("class");
        half=intent.getStringExtra("half");
        count=intent.getExtras().getInt("num_stud");

        absentees_htno = getIntent().getStringArrayListExtra("absentees_htno");


        setTitle(sec_name);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        name_fac=sharedPreferences.getString("fac_name", "");

        fac_tv.setText(name_fac);
        half_tv.setText(half);

        for(int j=0;j<count;j++){
            ht_nums += absentees_htno.get(j).toString()+"\n";
        }

        ArrayAdapter<String> absenteesHtnoAdapter = new ArrayAdapter<String>(this, R.layout.absenteessingletextlayout,absentees_htno);
        //ArrayAdapter<String> absenteesAdapter2 = new ArrayAdapter<String>();
        ListView lv2 = (ListView) findViewById(R.id.listView2);
        lv2.setAdapter(absenteesHtnoAdapter);

    }

    @Override
    public void onClick(View v) {
        if(v==send_mail){
            date_str=new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            AlertDialog.Builder builder = new AlertDialog.Builder(AbsenteesActivity.this);
            builder.setTitle("CONFIRMATION");
            builder.setMessage("NUMBER OF ABSENTEES: "+count)
                    .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("message/rfc822");
                            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"13h61a0553@gmail.com"});
                            i.putExtra(Intent.EXTRA_SUBJECT, sec_name+" "+half+" Attendance Date: " + date_str);
                            i.putExtra(Intent.EXTRA_TEXT, "List of absentees from "+sec_name+" Faculty: "+name_fac+"\n"+ ht_nums);
                            try {
                                startActivity(Intent.createChooser(i, "Send mail..."));
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            }
                            absentees_htno.clear();

                        }
                    }).show();
        }
    }
}
