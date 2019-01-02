package com.example.adrien.madrental;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>
{
    //Define list of items
    private List<RentalDTO> bookingList;

    //Constructor
    public BookingsAdapter(List<RentalDTO> bookingList)
    {
        this.bookingList = bookingList;
    }


    public class BookingViewHolder extends RecyclerView.ViewHolder
    {
        //Create future reference of memoLabel
        public TextView bookingName;
        public ImageView bookingImage;
        public TextView bookingPrice;
        public TextView bookingStartDate;
        public TextView bookingEndDate;

        //Constructor
        public BookingViewHolder(final View itemView)
        {
            super(itemView);

            //Catch ID
            bookingName = itemView.findViewById(R.id.bookingName);
            bookingImage = itemView.findViewById(R.id.bookingImage);
            bookingStartDate = itemView.findViewById(R.id.bookingStartDate);
            bookingEndDate = itemView.findViewById(R.id.bookingEndDate);
        }
    }

    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewBooking = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_view, parent, false);
        return new BookingViewHolder(viewBooking);
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position)
    {
        //Format Dates
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date rStartDate;
        Date rEndDate;

        rStartDate = new Date(bookingList.get(position).startDate);
        rEndDate = new Date(bookingList.get(position).endDate);

        //Assign Values to view
        holder.bookingName.setText(bookingList.get(position).name + " - " + bookingList.get(position).price + " €");
        holder.bookingStartDate.setText("début: " + sdf.format(rStartDate));
        holder.bookingEndDate.setText("fin: " + sdf.format(rEndDate));

        Picasso.with(holder.bookingImage.getContext())
                .load("http://s519716619.onlinehome.fr/exchange/madrental/images/" + bookingList.get(position).image)
                .fit()
                .centerCrop() // ou centerInside()
                .into(holder.bookingImage);
    }

    @Override
    public int getItemCount()
    {
        return bookingList.size();
    }
}