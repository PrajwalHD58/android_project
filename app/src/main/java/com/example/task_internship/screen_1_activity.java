package com.example.task_internship;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class screen_1_activity extends AppCompatActivity {
    private ImageView img_circle,img_square,img_undo;
    private FrameLayout fl;
    int it = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_1);

        setTitle("Screen-1");
        img_circle=(ImageView)findViewById(R.id.circle);
        img_square=(ImageView)findViewById(R.id.square_img);
        img_undo=(ImageView)findViewById(R.id.undo);
        fl=(FrameLayout)findViewById(R.id.frame_1);
        View[] arr=new View[100000];
        img_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawcircle c = new Drawcircle(getApplicationContext());
                arr[it]=c;
                fl.addView(c);
                it++;
                Toast.makeText(getApplicationContext(),"Circle Added!",Toast.LENGTH_SHORT).show();
            }
        });

        img_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawsqure c=new Drawsqure(getApplication());
                arr[it]=c;
                fl.addView(c);
                it++;
                Toast.makeText(getApplicationContext(),"Square Added!",Toast.LENGTH_SHORT).show();
            }
        });

        img_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(it>0) {
                    it--;
                    fl.removeView(arr[it]);
                    Toast.makeText(getApplicationContext(),"View Removed!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not have any View!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
