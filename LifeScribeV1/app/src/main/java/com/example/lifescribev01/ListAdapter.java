package com.example.lifescribev01;

import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;

import java.util.ArrayList;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ListAdapter extends
        RecyclerView.Adapter<ListAdapter.ViewHolder> {
    AppDatabase appDb = MainActivity.GetDatabase();

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public EditText nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameTextView = (EditText) itemView.findViewById(R.id.nameTextView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    listener.onItemClick(itemView, position);
                }
            }
        }
    }

    // Store a member variable for the contacts
    private List<Integer> pids;

    // Pass in the contact array into the constructor
    public ListAdapter(List<Integer> tests) {
        pids = tests;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View listView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder viewHolder, int position) {
        List<Person> dbPeople = appDb.personDao().getAll();
        List<String> names = new ArrayList<String>();
        for (Person p: dbPeople) {
            names.add(p.name);
        }
        String name = names.get(position);
        // Set item views based on your views and data model
        EditText textView = viewHolder.nameTextView;
        textView.setText(name);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        }) ;
    }

    @Override
    public int getItemCount() {
        return pids.size();
    }
}