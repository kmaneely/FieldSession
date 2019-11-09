package com.example.lifescribev01;

import androidx.recyclerview.widget.RecyclerView;


import android.widget.EditText;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;
import com.example.lifescribev01.database.Story;

import java.util.ArrayList;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class StoryListAdapter extends
        RecyclerView.Adapter<StoryListAdapter.ViewHolder> implements Filterable{
    AppDatabase appDb = MainActivity.GetDatabase();

    private OnItemClickListener listener;
    List<Story> filteredResults;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                title = (List<Story>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                filteredResults = null;
                if (constraint.length() == 0) {
                    for(Story p: title){
                        filteredResults.add(p);
                    }
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    protected List<Story> getFilteredResults(String constraint) {
        List<Story> results = new ArrayList<>();

        for (Story item : title) {
            if (item.title.toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
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
            nameTextView = itemView.findViewById(R.id.nameTextView);
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
    private List<Story> title;

    // Pass in the contact array into the constructor
    public StoryListAdapter(List<Story> tests) {
        title = tests;
    }

    public List<Story> getFilterList(){
        if(filteredResults != null) {
            return filteredResults;
        }else{
            return title;
        }
    }

    @Override
    public StoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View listView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoryListAdapter.ViewHolder viewHolder, int position) {
        String pName = title.get(position).title;
        // Set item views based on your views and data model
        EditText textView = viewHolder.nameTextView;
        textView.setText(pName);
    }

    @Override
    public int getItemCount() {
        if(title == null){
            return 0;
        }
        return title.size();
    }
}