package com.example.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;


    //Constructor to set listItems and context
    public ListAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    //This method is called right when the adapter is created and is used to initialize your ViewHolder(s).
    //LayoutInflater (which coverts an XML layout file into corresponding ViewGroups and Widgets)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //First we create the LayoutInflater from the ViewGroup context -> getContext() returns the context the view is currently running in.
        // inflate method create view from layout xml. The first parameter is the source xml, second the the view group which will contain the views
        //attach to root true means the view will be added to ViewGroup automatically. we don't try to do that in recycler view because
        // it decides when to add a view to view group and when not
        //After the view is created we need to create a view holder with it and return that
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new ViewHolder(view);
    }

    //This method is called for each ViewHolder to bind it to the adapter. This is where we will pass our data to our ViewHolder.
    //This method will be called for each row, we can get the row position from position parameter
    //Get the ListItem object from listItems using position
    //bind the list item heading and description value to ViewHolder heading and description TextView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHeading());
        holder.textViewDesc.setText(listItem.getDesc());

    }

    //This method returns the size of the collection that contains the items we want to display
    //Returns the size of the collection
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewHead;
        TextView textViewDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHead = (TextView) itemView.findViewById(R.id.heading);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDescription);
        }
    }
}
