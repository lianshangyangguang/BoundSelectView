package com.gwell.view.gwellview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gwell.view.gwellviewlibrary.ArcAngleView;
import com.gwell.view.gwellviewlibrary.BoundSelectView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BoundSelectView myBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myBtn = (BoundSelectView)findViewById(R.id.mybtn);
        ArcAngleView view = (ArcAngleView)findViewById(R.id.view);
        view.setAngle(1f);//参数为0-1 float类型小数，即可根据百分比转到相应角度
        BoundSelectView.ItemOnClickListener itemOnClickListener =new BoundSelectView.ItemOnClickListener() {
            @Override
            public void onItemClick(int i, View v) {
                Toast.makeText(MainActivity.this, "itemOnClickListeners "+i, Toast.LENGTH_SHORT).show();
            }
        };


        ArrayList<String> names = new ArrayList();
        names.add("超清");
        names.add("高清");
        names.add("标清");

        myBtn.setBoundButton(itemOnClickListener,names,"标清");
    }

    public void change(View view) {
        myBtn.hide();
    }
}
