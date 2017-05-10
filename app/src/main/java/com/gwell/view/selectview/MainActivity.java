package com.gwell.view.selectview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gwell.view.library.Attribute;
import com.gwell.view.library.BaseSelectItem;
import com.gwell.view.library.SelectView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BaseSelectItem currentT;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SelectView my = (SelectView) findViewById(R.id.selectview);
        SelectView.OnItemClickListener onItemClickListener = new SelectView.OnItemClickListener() {
            @Override
            public void onItemClick(BaseSelectItem baseSelectItem, View v, boolean isCurentItem) {
                Toast.makeText(MainActivity.this, baseSelectItem.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        ArrayList arrays = new ArrayList();
        BaseSelectItem r = new BaseSelectItem("a1b", "gghh");
        currentT = r;
        arrays.add(r);
        arrays.add(new BaseSelectItem("a2b", 22));
        arrays.add(new BaseSelectItem("a3b", 33.3));
        arrays.add(new BaseSelectItem("a4b", 44));
        arrays.add(new BaseSelectItem("a5b", 55));

        Attribute attr =new Attribute.AttributeBuilder().rootTextColor(Color.rgb(0xff,0x00,0x00)).build();
        my.setAttrs(attr);

        my.setSelectView(onItemClickListener, arrays, currentT);
    }
}
