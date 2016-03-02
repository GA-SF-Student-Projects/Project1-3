package com.example.adao1.project1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    ArrayList<String> firstList;
    ArrayAdapter<String> arrayAdapter;
    static boolean editCheck = false;
    static int editCheckIndex;
    static View currentEditView;
    static Drawable currentBackground;
    FloatingActionButton addButton;
    FloatingActionButton removeButton;



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
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, firstList);

        //Array to adapter to listView
        fillArray();
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);

        //OnItemClickLister
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //Edit list items
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeButton.setVisibility(View.VISIBLE);
                editText.setText(firstList.get(position));
                currentBackground=view.getBackground();
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

                if (editCheck) {
                    firstList.set(editCheckIndex, editText.getText().toString());
                    editCheck = false;
                    arrayAdapter.notifyDataSetChanged();
                    editText.setText("");
                    currentEditView.setBackground(currentBackground);
                } else {
                    firstList.add(editText.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
                    editText.setText("");
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
        firstList.add("List element 1");
        firstList.add("List element 2");
        firstList.add("List element 3");
        firstList.add("List element 4");
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
