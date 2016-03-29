package com.sqisland.android.sliding_pane_layout;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TwoPaneActivity extends Activity implements View.OnClickListener{

    MySlidingLayout layout;
    Button btn_toggle, btn_add, btn_sub;
    static int count = 0;
    int dimen ;
    LinearLayout layout_pane2;

    GridView gridView;

    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_pane);

        dimen = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.side_pane_width);

        btn_toggle = (Button) findViewById(R.id.button_toggle);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        layout = (MySlidingLayout) findViewById(R.id.sliding_pane_layout);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_toggle.setOnClickListener(this);

        layout_pane2 = (LinearLayout)findViewById(R.id.layout_pane2);

        gridView = (GridView) findViewById(R.id.gridView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout.isOpen()) {
                    layout.closePane();
                } else {
                    layout.openPane();
                }
            }
        });



        layout.setSliderFadeColor(Color.TRANSPARENT);

        layout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.wtf("TwoPaneActivity", "onPanelSlide");
            }

            @Override
            public void onPanelOpened(View panel) {
                Log.wtf("TwoPaneActivity", "onPanelOpened");
                layout_pane2.setPadding(0, 0, dimen, 0);
            }

            @Override
            public void onPanelClosed(View panel) {
                Log.wtf("TwoPaneActivity", "onPanelClosed");
                layout_pane2.setPadding(0, 0, 0, 0);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                count++;
                Toast.makeText(this, "count == " + count, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sub:
                count--;
                Toast.makeText(this, "count == " + count, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        Toast.makeText(this, " "+ newConfig.orientation, Toast.LENGTH_SHORT).show();

    }
}