package com.example.adao1.project1;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    ListObject itemObject;//ListObject of each item of first list; holds Title and secondList
    ArrayList<String> firstList; //List of lists on the first page
    ArrayList<String> testList; //List of lists on the first page
    //ArrayList<String> secondList;//The different lists on the second page
    ArrayList<ListObject> objectList; //List that holds the object of ListObject of each item
    ArrayAdapter<String> arrayAdapter;
    static boolean editCheck = false;
    static int editCheckIndex;
    static View currentEditView;
    static Drawable currentBackground;
    FloatingActionButton addButton;
    FloatingActionButton removeButton;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization
        listView = (ListView)findViewById(R.id.listViewid);
        editText = (EditText)findViewById(R.id.editTextid);
        addButton = (FloatingActionButton) findViewById(R.id.fab);
        removeButton = (FloatingActionButton) findViewById(R.id.fabrm);
        firstList = new ArrayList<>();
        //secondList = new ArrayList<>();
        testList = new ArrayList<>();//testing
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, firstList);
        intent = new Intent(this, NextActivity.class);
        //itemObject = new ListObject("",secondList);
        objectList = new ArrayList<>();


        //Array to adapter to listView
        fillArray();//testing
        testList.add("yay");//testing
        testList.add("it");//testing
        testList.add("works"); //testing


        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);

        //OnItemClickLister
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> sendArray = new ArrayList<String>();
                //objectList.get(0).setListArray(testList); //testing
                sendArray = objectList.get(position).getListArray();

                //intent.putExtra("KEY",objectList.get(position).getListTitle());
                intent.putExtra("KEY",sendArray);
                startActivity(intent);
            }
        });

        //Edit list items
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeButton.setVisibility(View.VISIBLE);
                editText.setText(firstList.get(position));
                currentBackground = view.getBackground();
                view.setBackgroundResource(android.R.color.holo_blue_light);
                currentEditView = view;
                editCheck = true;
                editCheckIndex = position;

                return false;
            }
        });

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = editText.getText().toString();
                if (editCheck) {
                    firstList.set(editCheckIndex, currentText);
                    editCheck = false;
                    arrayAdapter.notifyDataSetChanged();
                    editText.setText("");
                    currentEditView.setBackground(currentBackground);
                    removeButton.setVisibility(View.INVISIBLE);
                    itemObject = new ListObject(currentText);
                    objectList.set(editCheckIndex, itemObject);
                } else {
                    firstList.add(currentText);
                    arrayAdapter.notifyDataSetChanged();
                    editText.setText("");
                    itemObject = new ListObject(currentText);
                    objectList.add(itemObject);
                }



            }
        });

        //Floating Remove button
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstList.remove(editCheckIndex);
                arrayAdapter.notifyDataSetChanged();
                removeButton.setVisibility(View.INVISIBLE);
                editCheck = false;
                editText.setText("");
                currentEditView.setBackground(currentBackground);
            }
        });
    }

    //Temp fill array
    public void fillArray(){
       // firstList.add("List element 1"); //testing
        //objectList.add(0,new ListObject("List element 1"));//testing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
