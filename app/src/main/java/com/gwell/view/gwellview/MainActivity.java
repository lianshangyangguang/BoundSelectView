package com.gwell.view.gwellview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gwell.view.gwellviewlibrary.BaseSelectItem;
import com.gwell.view.gwellviewlibrary.BoundSelectView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BoundSelectView myBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myBtn = (BoundSelectView)findViewById(R.id.mybtn);
        BoundSelectView.ItemOnClickListener itemOnClickListener = new BoundSelectView.ItemOnClickListener() {
            @Override
            public void onItemClick(BaseSelectItem baseSelectItem, View view, boolean b) {
                Toast.makeText(MainActivity.this,baseSelectItem.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        ArrayList<BaseSelectItem> names = new ArrayList();
        names.add(new BaseSelectItem("1",1));
        names.add(new BaseSelectItem("2",2));
        names.add(new BaseSelectItem("3",3));

        //参数分别为：选项的监听事件，选项名称，默认显示
        myBtn.setBoundButton(itemOnClickListener, names, "2");
    }

    public void change(View view) {
        myBtn.hide();
    }
}
