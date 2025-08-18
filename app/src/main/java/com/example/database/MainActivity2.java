package com.example.database;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    SQLiteDatabase db;
    Button btn,del,update,nxtpg;
    TextView txtv;
    EditText namee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        btn = findViewById(R.id.button2);
        txtv = findViewById(R.id.textView);
        del=findViewById(R.id.button3);
        namee=findViewById(R.id.delname);
        nxtpg=findViewById(R.id.button4);
        db = openOrCreateDatabase("student_DB",MODE_PRIVATE,null);
        update=findViewById(R.id.button9);
        db.execSQL("create table if not exists students (SID INTEGER PRIMARY KEY AUTOINCREMENT, Email TEXT, Name TEXT, Password TEXT)");
        refresh();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               refresh();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=namee.getText().toString();
                String s="delete from students where SID="+id;
                db.execSQL(s);
            }
        });
        nxtpg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

    }
    public void refresh(){
        StringBuilder result = new StringBuilder();
        Cursor cursor  = db.rawQuery("select * from students", null);
        if(cursor.getCount()==0){
            txtv.setText("No values present");
            return;
        }
        while(cursor.moveToNext()){
            int id= cursor.getInt(0);
            String e=cursor.getString(1);
            String n=cursor.getString(2);
            String p=cursor.getString(3);
            result.append("id:").append(id);
            result.append("name:").append(n).append('\t');
            result.append("email:").append(e).append('\t');
            result.append("password:").append(p).append('\t');
            result.append('\n');
            txtv.setText(result.toString());
        }
    }
}