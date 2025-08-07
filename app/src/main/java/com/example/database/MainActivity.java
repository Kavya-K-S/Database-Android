package com.example.database;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button register;
    private EditText mail,name,pass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        register=findViewById(R.id.button);
        mail=findViewById(R.id.email);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.password);
        db=openOrCreateDatabase("student_DB",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS students (SID INTEGER PRIMARY KEY AUTOINCREMENT, Email TEXT, Name TEXT, Password TEXT)");
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String n=name.getText().toString();
                String m=mail.getText().toString();
                String p=pass.getText().toString();
                if (m.isEmpty() || n.isEmpty() || p.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String query = "insert into students(Email,Name,Password) values('" + m + "','" + n+ "','" + p + "')";
                db.execSQL(query);
                Toast toast=Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

}