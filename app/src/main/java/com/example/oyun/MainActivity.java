package com.example.oyun;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormalOyun = findViewById(R.id.btnNormalOyun);
        Button btnSureliOyun = findViewById(R.id.btnSureliOyun);
        Button btnOyundanCikis = findViewById(R.id.btnOyundanCikis);

        btnNormalOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalOyunActivity.class);
                startActivity(intent);
            }
        });

        btnSureliOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, SureliOyunActivity.class);
                startActivity(intent2);
            }
        });

        btnOyundanCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }}
