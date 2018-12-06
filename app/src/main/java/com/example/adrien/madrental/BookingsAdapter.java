package com.example.adrien.madrental;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>
{
    //Define list of items
    private List<Booking> bookingList;

    //Constructor
    public BookingsAdapter(List<Booking> bookingList)
    {
        this.bookingList = bookingList;
    }


    public class BookingViewHolder extends RecyclerView.ViewHolder
    {
        //Create future reference of memoLabel
        public TextView bookingLabel;

        //Constructor
        public BookingViewHolder(final View itemView)
        {
            super(itemView);

            //Catch ID
            bookingLabel = itemView.findViewById(R.id.bookingLabel);
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
        holder.bookingLabel.setText(bookingList.get(position).label);
    }

    @Override
    public int getItemCount()
    {
        return bookingList.size();
    }
}