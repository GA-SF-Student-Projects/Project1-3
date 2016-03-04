package com.example.adao1.project1;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NextActivity extends AppCompatActivity {
    public static final int RESULT_CODE = RESULT_OK;
    private static ListView listView;
    private static EditText editText;
    private static View currentEditView;
    private static Drawable currentBackground;
    private static FloatingActionButton addButton;
    private static FloatingActionButton removeButton;
    private static Toolbar toolbar;
    private static ArrayList<String> secondList;
    private static ArrayAdapter<String> arrayAdapter;
    private static ArrayList<ListObject> objectList;
    private static ListObject listObject;
    private static boolean editCheck = false;
    private static int editCheckIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = getIntent().getStringExtra(MainActivity.TITLE_KEY);
        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        //Initialization
        listView = (ListView)findViewById(R.id.listViewid);
        editText = (EditText)findViewById(R.id.editTextid);
        addButton = (FloatingActionButton) findViewById(R.id.fab);
        removeButton = (FloatingActionButton) findViewById(R.id.fabrm);
        secondList = new ArrayList<>();
        objectList = new ArrayList<>();
        secondList = getIntent().getStringArrayListExtra(MainActivity.ARRAY_KEY);
        arrayAdapter = new ArrayAdapter<>(NextActivity.this,android.R.layout.simple_list_item_1, secondList);
        if(arrayAdapter !=null) {
            listView.setAdapter(arrayAdapter);
        }
        arrayAdapter.notifyDataSetChanged();

        strikeThroughListener();
        editListListener();
        addButtonListener();
        removeButtonListener();
    }

    private void strikeThroughListener(){
        //OnItemClickLister
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //StrikeThrough
                TextView row = (TextView) view;
                if (!objectList.get(position).isStrikeThrough()) {
                    row.setPaintFlags(row.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    objectList.get(position).changeStrikeThrough(true);
                } else {
                    row.setPaintFlags(0);
                    objectList.get(position).changeStrikeThrough(false);
                }
            }
        });
    }
    private void editListListener(){
        //Edit list items
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeButton.setVisibility(View.VISIBLE);
                editText.setText(secondList.get(position));
                currentBackground = view.getBackground();
                currentEditView = view;
                changeEditcolor();
                editCheck = true;
                editCheckIndex = position;
                return true;
            }
        });
    }
    private void addButtonListener(){
        //Floating Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = editText.getText().toString();
                if (editCheck) {
                    secondList.set(editCheckIndex, editText.getText().toString());
                    changeEditcolor();
                    editCheck = false;
                    arrayAdapter.notifyDataSetChanged();
                    editText.setText("");
                    listObject = new ListObject(currentText, false);
                    objectList.set(editCheckIndex, listObject);
                } else {
                    secondList.add(editText.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
                    editText.setText("");
                    listObject = new ListObject(currentText, false);
                    objectList.add(listObject);
                }
            }
        });
    }
    private void removeButtonListener(){
        //Floating Remove button
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView row = (TextView) currentEditView;
                row.setPaintFlags(0);
                secondList.remove(editCheckIndex);
                objectList.remove(editCheckIndex);
                arrayAdapter.notifyDataSetChanged();
                removeButton.setVisibility(View.INVISIBLE);
                changeEditcolor();
                editCheck = false;
                editText.setText("");
            }
        });
    }

    public static void changeEditcolor(){
        if(editCheck) {
            removeButton.setColorFilter(Color.WHITE);
            addButton.setColorFilter(Color.WHITE);
            toolbar.setBackgroundResource(android.R.color.darker_gray);
            currentEditView.setBackground(currentBackground);
        }else{
            removeButton.setColorFilter(Color.argb(255, 51, 181, 229));
            addButton.setColorFilter(Color.argb(255, 51, 181, 229));
            toolbar.setBackgroundResource(android.R.color.holo_blue_light);
            currentEditView.setBackgroundResource(android.R.color.holo_blue_light);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent2 = getIntent();
        if (intent2==null)return;
        if(secondList==null)return;
        intent2.putExtra("KEY2", secondList);
        setResult(RESULT_CODE, intent2);
        finish();
    }

}
