package com.example.adrien.madrental;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SearchesAdapter extends RecyclerView.Adapter<SearchesAdapter.SearchViewHolder>
{
    //Define list of items
    private List<Search> searchList;

    //Constructor
    public SearchesAdapter(List<Search> searchList)
    {
        this.searchList = searchList;
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder
    {
        //Create future reference of searchLabel
        public TextView searchLabel;

        //Constructor
        public SearchViewHolder(final View itemView)
        {
            super(itemView);

            //Catch ID
            searchLabel = itemView.findViewById(R.id.searchLabel);
        }
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewSearch = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_view, parent, false);
        return new SearchViewHolder(viewSearch);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position)
    {
        holder.searchLabel.setText(searchList.get(position).label);
    }

    @Override
    public int getItemCount()
    {
        return searchList.size();
    }
}