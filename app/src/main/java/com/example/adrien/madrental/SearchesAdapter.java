package com.example.adrien.madrental;

import android.content.Intent;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.parceler.Parcels;
import org.w3c.dom.Text;

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
        //Initialize variables
        public TextView searchName;
        public TextView searchBaseDailyPrice;
        public TextView searchCo2Category;
        public ImageView searchImage;
        public ConstraintLayout searchViewWrapper;
        public TextView searchPromotion;

        //Constructor
        public SearchViewHolder(final View itemView)
        {
            super(itemView);

            //Catch ID
            searchViewWrapper = itemView.findViewById(R.id.searchViewWrapper);
            searchName = itemView.findViewById(R.id.searchName);
            searchBaseDailyPrice = itemView.findViewById(R.id.searchBaseDailyPrice);
            searchCo2Category = itemView.findViewById(R.id.searchCo2Category);
            searchImage = itemView.findViewById(R.id.searchImage);
            searchPromotion = itemView.findViewById(R.id.searchPromotion);

            // listener :
            searchViewWrapper.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(itemView.getContext(), BookingState1.class);
                    intent.putExtra("searchId", searchList.get(getAdapterPosition()).id);
                    intent.putExtra("searchName", searchList.get(getAdapterPosition()).name);
                    intent.putExtra("searchImage", searchList.get(getAdapterPosition()).image);
                    intent.putExtra("searchAvailable", searchList.get(getAdapterPosition()).available);
                    intent.putExtra("searchBaseDailyPrice", searchList.get(getAdapterPosition()).baseDailyPrice);
                    intent.putExtra("searchSale", searchList.get(getAdapterPosition()).sale);
                    intent.putExtra("searchAgeMin", searchList.get(getAdapterPosition()).ageMin);
                    intent.putExtra("searchCo2Category", searchList.get(getAdapterPosition()).co2Category);
                    intent.putExtra("searchEquipments",  searchList.get(getAdapterPosition()).equipments.toString());
                    intent.putExtra("searchOptions", searchList.get(getAdapterPosition()).options.toString());
                    intent.putExtra("startDate", searchList.get(getAdapterPosition()).startDate.toString());
                    intent.putExtra("endDate", searchList.get(getAdapterPosition()).endDate.toString());
                    itemView.getContext().startActivity(intent);
                }
            });
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
        //Catch values
        holder.searchName.setText(searchList.get(position).name);
        holder.searchBaseDailyPrice.setText(searchList.get(position).baseDailyPrice.toString() + " € / jour");
        holder.searchCo2Category.setText("Catégorie CO2 : " + searchList.get(position).co2Category);

        Picasso.with(holder.searchImage.getContext())
                .load("http://s519716619.onlinehome.fr/exchange/madrental/images/" + searchList.get(position).image)
                .fit()
                .centerCrop() // ou centerInside()
                .into(holder.searchImage);

        if (searchList.get(position).sale != 0 && searchList.get(position).sale != null) {
            holder.searchPromotion.setBackground(ContextCompat.getDrawable(holder.searchPromotion.getContext(), R.drawable.promotion_gradiant));
            holder.searchPromotion.setText("- " + searchList.get(position).sale.toString() + " %");
        }
    }

    @Override
    public int getItemCount()
    {
        return searchList.size();
    }
}