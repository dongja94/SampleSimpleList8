package com.example.dongja94.samplesimplelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdapter;
    EditText inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputView = (EditText)findViewById(R.id.edit_input);
        Button btn = (Button)findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputView.getText().toString();
                mAdapter.add(text);
                listView.smoothScrollToPosition(mAdapter.getCount() - 1);
            }
        });
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String)listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "text : " + text , Toast.LENGTH_SHORT).show();
            }
        });
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice);
        listView.setAdapter(mAdapter);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        initData();

        btn = (Button)findViewById(R.id.btn_choice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {
                    int position = listView.getCheckedItemPosition();
                    String text = (String)listView.getItemAtPosition(position);
                    Toast.makeText(MainActivity.this, "choice : " + text, Toast.LENGTH_SHORT).show();
                } else if (listView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
                    SparseBooleanArray selection = listView.getCheckedItemPositions();
                    StringBuilder sb = new StringBuilder();
                    for (int index = 0 ; index < selection.size(); index++) {
                        int position = selection.keyAt(index);
                        if (selection.get(position)) {
                            String text = (String)listView.getItemAtPosition(position);
                            sb.append(text).append(",");
                        }
                    }
                    Toast.makeText(MainActivity.this, "choice : " + sb.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        String[] items = getResources().getStringArray(R.array.list_item);
        for (String s : items) {
            mAdapter.add(s);
        }
    }
}
