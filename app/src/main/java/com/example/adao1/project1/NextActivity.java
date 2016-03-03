package com.example.adao1.project1;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NextActivity extends AppCompatActivity {

    ListView listView2;
    EditText editText2;
    ArrayList<String> secondList;
    ArrayAdapter<String> arrayAdapter2;
    ArrayList<ListObject> objectList2;
    ListObject itemObject2;
    static boolean editCheck2 = false;
    static int editCheckIndex2;
    static View currentEditView2;
    static Drawable currentBackground2;
    FloatingActionButton addButton2;
    FloatingActionButton removeButton2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization
        listView2 = (ListView)findViewById(R.id.listViewid);
        editText2 = (EditText)findViewById(R.id.editTextid);
        addButton2 = (FloatingActionButton) findViewById(R.id.fab);
        removeButton2 = (FloatingActionButton) findViewById(R.id.fabrm);
        secondList = new ArrayList<>();
        objectList2 = new ArrayList<>();
        secondList = getIntent().getStringArrayListExtra("KEY");
        arrayAdapter2 = new ArrayAdapter<String>(NextActivity.this,android.R.layout.simple_list_item_1, secondList);
        Log.d("NextActivity","Check if secondList got it ");

        listView2.setAdapter(arrayAdapter2);
        arrayAdapter2.notifyDataSetChanged();
        Log.d("NextActivity", "Post-adapter");


        //OnItemClickLister
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //StrikeThrough
                TextView row = (TextView)view;
                if (!objectList2.get(position).isStrikeThrough()){
                    row.setPaintFlags(row.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    objectList2.get(position).changeStrikeThrough(true);
                }
                else {
                    row.setPaintFlags(0);
                    objectList2.get(position).changeStrikeThrough(false);
                }
            }
        });

        //Edit list items
        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeButton2.setVisibility(View.VISIBLE);
                editText2.setText(secondList.get(position));
                currentBackground2 = view.getBackground();
                view.setBackgroundResource(android.R.color.holo_blue_light);
                currentEditView2 = view;
                editCheck2 = true;
                editCheckIndex2 = position;

                return false;
            }
        });


        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Add button
        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = editText2.getText().toString();

                if (editCheck2) {
                    secondList.set(editCheckIndex2, editText2.getText().toString());
                    editCheck2 = false;
                    arrayAdapter2.notifyDataSetChanged();
                    editText2.setText("");
                    currentEditView2.setBackground(currentBackground2);
                    itemObject2 = new ListObject(currentText,false);
                    objectList2.set(editCheckIndex2, itemObject2);

                } else {
                    secondList.add(editText2.getText().toString());
                    arrayAdapter2.notifyDataSetChanged();
                    editText2.setText("");
                    itemObject2 = new ListObject(currentText,false);
                    objectList2.add(itemObject2);
                }

            }
        });

        //Floating Remove button
        removeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondList.remove(editCheckIndex2);
                objectList2.remove(editCheckIndex2);
                arrayAdapter2.notifyDataSetChanged();
                removeButton2.setVisibility(View.INVISIBLE);
                editCheck2 = false;
                editText2.setText("");
                currentEditView2.setBackground(currentBackground2);

            }
        });



    }
}
