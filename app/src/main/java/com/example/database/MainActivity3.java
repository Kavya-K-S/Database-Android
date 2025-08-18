package com.example.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    EditText sid,name,pswd;
    SQLiteDatabase db;
    Button button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        sid=findViewById(R.id.t1);
        name=findViewById(R.id.t2);
        pswd=findViewById(R.id.t3);
        button5=findViewById(R.id.button5);
        db=openOrCreateDatabase("student_DB",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS students (SID INTEGER PRIMARY KEY AUTOINCREMENT, Email TEXT, Name TEXT, Password TEXT)");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i=sid.getText().toString();
                String n=name.getText().toString();
                String p=pswd.getText().toString();
                Cursor cursor  = db.rawQuery("select * from students where SID="+i, null);
                if(cursor.getCount()==0){
                    Toast.makeText(getApplicationContext(), "Student with this id is not present", Toast.LENGTH_SHORT).show();
                    return;
                }
                String q="update students set Name='"+n+"'"+",Password='"+p+"' where SID="+i;
                db.execSQL(q);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}