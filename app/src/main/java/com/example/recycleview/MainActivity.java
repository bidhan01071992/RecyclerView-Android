package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String url = "https://simplifiedcoding.net/demos/marvel/";

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
        //Initializing ArrayList
        listItems = new ArrayList<>();
        loadRecyclerViewData();

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRecyclerViewData();
                //For an ArrayAdapter, notifyDataSetChanged only works if you use the add(), insert(), remove(), and clear() on the Adapter.
                adapter.notifyDataSetChanged();
                //Pause the refresh indicator
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadRecyclerViewData() {
        //Start a progress indicator till the time wifi is working
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Data loading");
        progressDialog.show();

        //Create a string request with the call type GET, url and response and error
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    //Data enclosed in curly brackets ({) is a single JSONObject. Nested JSONObjects are possible and are very commonly used . I see it as dictionary in iOS
                    JSONObject object = new JSONObject(response);
                    // A JSONArray is enclosed in square brackets ([). It contains a set of objects
                    JSONArray jsonArray = object.getJSONArray("heroes");

                    for (int i= 0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ListItem listItem = new ListItem(jsonObject.getString("image"),jsonObject.getString("name"),jsonObject.getString("about"));
                        listItems.add(listItem);
                    }
                    //create adapter from list-items
                    adapter = new ListAdapter(listItems,getApplicationContext());
                    //set the adapter to recyclerview
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
