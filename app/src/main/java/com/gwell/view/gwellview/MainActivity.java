package com.gwell.view.gwellview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gwell.view.gwellviewlibrary.BoundSelectView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BoundSelectView myBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBtn = (BoundSelectView)findViewById(R.id.mybtn);
        BoundSelectView.ItemOnClickListener itemOnClickListener =new BoundSelectView.ItemOnClickListener() {
            @Override
            public void onItemClick(int i, View v) {
                Toast.makeText(MainActivity.this, "itemOnClickListeners "+i, Toast.LENGTH_SHORT).show();
            }
        };

        ArrayList<String> names = new ArrayList<String>();
        names.add("select1");
        names.add("select2");
        names.add("select3");

        myBtn.setBoundButton(itemOnClickListener,names,"select2");
    }
}
