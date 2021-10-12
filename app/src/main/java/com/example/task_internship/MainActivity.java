package com.example.task_internship;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_sc1,btn_sc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sc1=(Button)findViewById(R.id.screen_1);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        btn_sc1.startAnimation(myAnim);
        btn_sc2=(Button)findViewById(R.id.screen_2);
        btn_sc2.startAnimation(myAnim);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        btn_sc1.startAnimation(myAnim);
        btn_sc2.startAnimation(myAnim);

        btn_sc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),screen_1_activity.class);
                startActivity(intent);
            }
        });

        btn_sc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ImageListActivity.class);
                startActivity(intent);
            }
        });
    }
}