package com.sqisland.android.sliding_pane_layout;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoPaneActivity extends Activity implements View.OnClickListener{

    MySlidingLayout slidingLayout;
    Button btn_toggle, btn_add, btn_sub, btn_edit;
    static int count = 0;
    int dimen ;
    LinearLayout layout_pane2;

    ArrayAdapter<String> adapterGrid, adapterList;
    GridView gridView;
    ListView listView;

    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    ArrayList<String> listKeys = new ArrayList<>(Arrays.asList(numbers));
    private boolean isEditable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_pane);

        initVars();

        gridView.setAdapter(adapterGrid);

        listView.setAdapter(adapterList);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String strItem = (String) ((TextView) v).getText();
                Toast.makeText(getApplicationContext(), strItem, Toast.LENGTH_SHORT).show();

                if (isEditable) {
                    listKeys.remove(strItem);
                    gridView.setAdapter(adapterGrid);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();

            }
        });

        slidingLayout.setSliderFadeColor(Color.TRANSPARENT);

        slidingLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
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

    private void initVars() {
        dimen = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.side_pane_width);

        slidingLayout = (MySlidingLayout) findViewById(R.id.sliding_pane_layout);
        layout_pane2 = (LinearLayout)findViewById(R.id.layout_pane2);

        btn_toggle = (Button) findViewById(R.id.btn_toggle);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_edit = (Button) findViewById(R.id.btn_edit);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_toggle.setOnClickListener(this);
        btn_edit.setOnClickListener(this);


        listView = (ListView)findViewById(R.id.listView_pane1);
        gridView = (GridView) findViewById(R.id.gridView);

        adapterList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers);
        adapterGrid = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listKeys);
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
            case R.id.btn_toggle:
                if (slidingLayout.isOpen()) {
                    slidingLayout.closePane();
                } else {
                    slidingLayout.openPane();
                }
                break;
            case R.id.btn_edit:
                if(!isEditable){
                    btn_edit.setTextColor(Color.GREEN);
                }
                else{
                    btn_edit.setTextColor(Color.BLACK);
                }
                isEditable = !isEditable;
                break;

        }
    }


}