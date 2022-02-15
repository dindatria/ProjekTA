package com.dindatria.shetpi.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dindatria.shetpi.R;

public class MainActivity extends AppCompatActivity {

 Button btnList, btnGrafik, btnDataSapi, btnPengukuran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnPengukuran=findViewById(R.id.button);
        btnList=findViewById(R.id.button1);
        btnGrafik=findViewById(R.id.button2);
        btnDataSapi=findViewById(R.id.button3);

        btnPengukuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PengukuranActivitity.class);
                startActivity(intent);
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerviewDataActivity.class);
                startActivity(intent);
            }
        });

        btnDataSapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputDataActivity.class);
                startActivity(intent);

            }
        });

        btnGrafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GrafikActivity.class);
                startActivity(intent);
            }
        });
    }
}
