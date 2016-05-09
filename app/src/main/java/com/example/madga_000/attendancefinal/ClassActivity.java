package com.example.madga_000.attendancefinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by madga_000 on 1/30/2016.
 */
public class ClassActivity extends AppCompatActivity implements View.OnClickListener{
    String fac_name="",name_fac="";
    public static final String PREFS_NAME = "MyPrefsFile";
    Button sec2a_btn,sec2b_btn,sec2c_btn,sec2d_btn,sec3a_btn,sec3b_btn,sec3c_btn,sec3d_btn,addDB_btn;
    /*Button del_btn;*/
    boolean status;
    SQLiteDatabase attendancedb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        sec2a_btn=(Button)findViewById(R.id.btn_2secA);
        sec2b_btn=(Button)findViewById(R.id.btn_2secB);
        sec2c_btn=(Button)findViewById(R.id.btn_2secC);
        sec2d_btn=(Button)findViewById(R.id.btn_2secD);
        sec3a_btn=(Button)findViewById(R.id.btn_3secA);
        sec3b_btn=(Button)findViewById(R.id.btn_3secB);
        sec3c_btn=(Button)findViewById(R.id.btn_3secC);
        sec3d_btn=(Button)findViewById(R.id.btn_3secD);
        addDB_btn=(Button)findViewById(R.id.btn_addDB);


        attendancedb = openOrCreateDatabase("AttendanceDB", Context.MODE_PRIVATE, null);
        attendancedb.execSQL("create table if not exists csetwoa( htno varchar primary key);");
        attendancedb.execSQL("create table if not exists csetwob( htno varchar primary key);");
        attendancedb.execSQL("create table if not exists csetwoc( htno varchar primary key);");
        attendancedb.execSQL("create table if not exists csetwod( htno varchar primary key);");
        attendancedb.execSQL("create table if not exists csethreea( htno varchar primary key);");
        attendancedb.execSQL("create table if not exists csethreeb( htno varchar primary key);");
        attendancedb.execSQL("create table if not exists csethreec( htno varchar primary key);");
        attendancedb.execSQL("create table if not exists csethreed( htno varchar primary key);");

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        status=sharedPreferences.getBoolean("curr_status",true);

        if(status==false){
            addDB_btn.setVisibility(View.GONE);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.add(0, 0, 1, "DELETE DB");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == 0) {
            ClassActivity.this.deleteDatabase("attendancedb");
            addDB_btn.setVisibility(View.VISIBLE);
        }
        if (id == R.id.add_fac) {
            alert();
        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v==sec2a_btn){
                Navigate("II CSE A", "csetwoa");
        }
        if(v==sec2b_btn){
            Navigate("II CSE B","csetwob");
        }
        if(v==sec2c_btn){
            Navigate("II CSE C","csetwoc");
        }
        if(v==sec2d_btn){
            Navigate("II CSE D","csetwod");
        }
        if(v==sec3a_btn){
            Navigate("III CSE A","csethreea");
        }
        if(v==sec3b_btn){
            Navigate("III CSE B","csethreeb");
        }
        if(v==sec3c_btn){
            Navigate("III CSE C","csethreec");
        }
        if(v==sec3d_btn){
            Navigate("III CSE D","csethreed");
        }
        if(v==addDB_btn){

            addDB_btn.setVisibility(View.GONE);


            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("curr_status",false);
            editor.commit();


            //SECOND YEAR
            for(int i=1;i<=9;i++){
                attendancedb.execSQL("insert into csetwoa values('14H61A050" + i + "');");
            }

            for(int i=10;i<=60;i++){
                attendancedb.execSQL("insert into csetwoa values('14H61A05" + i + "');");
            }

            for(int i=61;i<=99;i++){
                attendancedb.execSQL("insert into csetwob values('14H61A050" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwob values('14H61A05A" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwob values('14H61A05B" + i + "');");
            }

            attendancedb.execSQL("insert into csetwob values('14H61A05C0');");

            for(int i=1;i<=9;i++){
                attendancedb.execSQL("insert into csetwoc values('14H61A05C" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwoc values('14H61A05D" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwoc values('14H61A05E" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwoc values('14H61A05F" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwoc values('14H61A05G" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwoc values('14H61A05H" + i + "');");
            }

            attendancedb.execSQL("insert into csetwoc values('14H61A05J0');");

            for(int i=1;i<=9;i++){
                attendancedb.execSQL("insert into csetwod values('14H61A05J" + i + "');");
            }

            for(int i=1;i<=9;i++){
                attendancedb.execSQL("insert into csetwod values('14H61A05K" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwod values('14H61A05L" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwod values('14H61A05N" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csetwod values('14H61A05P" + i + "');");
            }

            attendancedb.execSQL("insert into csetwod values('14H61A05Q0');");

            //THIRD YEAR
            for(int i=1;i<=9;i++){
                attendancedb.execSQL("insert into csethreea values('13H61A050" + i + "');");
            }

            for(int i=10;i<=60;i++){
                attendancedb.execSQL("insert into csethreea values('13H61A05" + i + "');");
            }

            for(int i=61;i<=99;i++){
                attendancedb.execSQL("insert into csethreeb values('13H61A050" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreeb values('13H61A05A" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreeb values('13H61A05B" + i + "');");
            }

            attendancedb.execSQL("insert into csethreeb values('13H61A05C0');");

            for(int i=1;i<=9;i++){
                attendancedb.execSQL("insert into csethreec values('13H61A05C" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreec values('13H61A05D" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreec values('13H61A05E" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreec values('13H61A05F" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreec values('13H61A05G" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreec values('13H61A05H" + i + "');");
            }

            attendancedb.execSQL("insert into csethreec values('13H61A05J0');");

            for(int i=1;i<=9;i++){
                attendancedb.execSQL("insert into csethreed values('13H61A05J" + i + "');");
            }

            for(int i=1;i<=9;i++){
                attendancedb.execSQL("insert into csethreed values('13H61A05K" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreed values('13H61A05L" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreed values('13H61A05N" + i + "');");
            }

            for(int i=0;i<=9;i++){
                attendancedb.execSQL("insert into csethreed values('13H61A05P" + i + "');");
            }

            attendancedb.execSQL("insert into csethreed values('13H61A05Q0');");
        }

    }

    public void Navigate(String class_1, String class_2){
        Intent intent = new Intent(ClassActivity.this,AttendanceActivity.class);
        intent.putExtra("class1",class_1);
        intent.putExtra("class2",class_2);
        attendancedb.close();
        startActivity(intent);
    }
    public void alert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ClassActivity.this);
        alertDialog.setTitle("Faculty name");
        alertDialog.setMessage("Enter name");
        final EditText input = new EditText(ClassActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        //Restoring faculty name.
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        name_fac=sharedPreferences.getString("fac_name","");
        input.setText(name_fac); //setting it to the alertdialog.

        alertDialog.setPositiveButton("DONE",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        fac_name = input.getText().toString();
                        //Saving faculty name.
                        try {
                            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("fac_name", fac_name);
                            editor.commit();
                            Toast.makeText(getApplicationContext(), "Faculty name set to: " + fac_name, Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        alertDialog.show();
    }
}
