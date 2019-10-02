package com.example.corepixmobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.corepixmobileapp.Adapter.FileAdapter;
import com.example.corepixmobileapp.DatabaseHelper.DBHelper;
import com.example.corepixmobileapp.ModelClass.File;
import com.example.corepixmobileapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ViewFilesActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<File> files = new ArrayList<>();
    private FileAdapter fileAdapter;

    DBHelper dbHelper;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewFilesActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        dbHelper = new DBHelper(ViewFilesActivity.this);

        c = dbHelper.getData();

        if (c.getCount() > 0){
            if(c.moveToFirst()){
                do{
                    String filename = c.getString(1);
                    String data = c.getString(2);

                    File file = new File(filename,data);
                    files.add(file);
                }while (c.moveToNext());
            }
        }
        fileAdapter = new FileAdapter(ViewFilesActivity.this,files);
        recyclerView.setAdapter(fileAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.file_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1){
            sortByName();
            Toast.makeText(this, "Sort By Name", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.item2){
            sortByName();
            Toast.makeText(this, "Sort By Date", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void sortByName() {
        Log.d(TAG, "sortByName: ");
        Collections.sort(files,  (l1, l2) -> l1.getFile_name().compareTo(l2.getFile_name()));
    }

    private void sortByDate() {
        Log.d(TAG, "sortByDate: ");
        Collections.sort(files,  (l1, l2) -> l1.getFile_date().compareTo(l2.getFile_date()));
    }

}
