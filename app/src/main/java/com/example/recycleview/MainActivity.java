package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the recyclerview object from xml file id
        recyclerView = findViewById(R.id.recyclerView);
        //Every item of the recycler view has same size
        recyclerView.setHasFixedSize(true);
        //In contrast to other adapter-backed views such as {@link android.widget.ListView}
        //or {@link android.widget.GridView}, RecyclerView allows client code to provide custom
        //layout arrangements for child views. These arrangements are controlled by the
        //{@link LayoutManager}. A LayoutManager must be provided for RecyclerView to function.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Initializing arraylist
        listItems = new ArrayList<>();
        for (int i=0 ; i<95; i++) {
            ListItem listItem = new ListItem("Heading" + i+1, "Description" +i+3);
            listItems.add(listItem);
        }

        adapter = new ListAdapter(listItems, this);
        recyclerView.setAdapter(adapter);

    }
}
