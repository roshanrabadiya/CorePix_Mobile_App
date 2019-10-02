package com.example.corepixmobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.corepixmobileapp.DatabaseHelper.DBHelper;
import com.example.corepixmobileapp.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private long startMillis=0;

    private String FILE_NAME = "";
    private String DATA;
    private Button btn_show_all_file;

    private EditText et_filename;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        init();

        btn_show_all_file.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,ViewFilesActivity.class)));

    }

    private void init() {
        btn_show_all_file = findViewById(R.id.btn_view_File);
        et_filename = findViewById(R.id.et_file_name);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_UP) {

            //get system current milliseconds
            long time= System.currentTimeMillis();


            //if it is the first time, or if it has been more than 3 seconds since the first tap ( so it is like a new try), we reset everything
            if (startMillis==0 || (time-startMillis> 3000) ) {
                startMillis=time;
                count=1;
            }
            //it is not the first, and it has been  less than 3 seconds since the first
            else{ //  time-startMillis< 3000
                count++;
            }

            if (count==3) {
                String f = et_filename.getText().toString();
                if (f.matches("")){
                    Toast.makeText(this, "Plz Enter the File Name.", Toast.LENGTH_SHORT).show();
                }else {
                    saveFile();
                }
            }
            return true;
        }
        return false;

    }

    private void saveFile() {
        FileOutputStream fos = null;
        FILE_NAME = et_filename.getText().toString().trim() +".txt";

        //  fileDataWithTimeStamp();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
        Date now = new Date();
        DATA = formatter.format(now) + "";

        try {

                // Save File
                fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                fos.write(DATA.getBytes());

                Toast.makeText(this, "Saved To"+getFilesDir()+"/"+FILE_NAME, Toast.LENGTH_LONG).show();

                // Calling insertData Method
                insertData();

                et_filename.getText().clear();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", "File write failed: " + e.toString());
        }
        finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // This method insert data in SQLi Database
    public void insertData(){
        boolean isInserted = dbHelper.insertData(FILE_NAME,DATA);
        if(isInserted)
            Toast.makeText(MainActivity.this,"File is Created",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"File is not Created",Toast.LENGTH_LONG).show();
    }


}
