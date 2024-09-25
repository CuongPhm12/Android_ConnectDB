package com.example.connectwith_sql;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnConnect = (Button) findViewById(R.id.button);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView Name = findViewById(R.id.textView);
                ConnectSQL c = new ConnectSQL();
                connection = c.conClass();
                if (c != null) {
                    try {
                        String sqlStatement = "Select * from MA_Employee";
                        Statement smt = connection.createStatement();
                        ResultSet rs = smt.executeQuery(sqlStatement);
                        while (rs.next()) {
                            Name.setText(rs.getString(2));
                        }
                        connection.close();
                    } catch (Exception e) {
                        Log.e("The error is ", e.getMessage());
                    }

                }
            }
        });
    }
}