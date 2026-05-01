package com.pany.controller.presentation.viewmodel.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pany.controller.R;

import java.util.ArrayList;

public class NotifRecyclerViewAdapter extends RecyclerView.Adapter<NotifRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<NotificationModel> notificationModels;


    public NotifRecyclerViewAdapter(Context context, ArrayList<NotificationModel> notificationModels){
        this.context = context;
        this.notificationModels = notificationModels;
    }

    @NonNull
    @Override
    public NotifRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout(Giving look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_notification, parent, false);
        return new NotifRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifRecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the layout file
        //based on the position of the recycler view
        holder.devName.setText(notificationModels.get(position).getDeviceName());
        holder.trigName.setText(notificationModels.get(position).getTriggerName());
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want to display

        return notificationModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from layout file
        //kinda like in onCreate method

        TextView devName, trigName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            devName = itemView.findViewById(R.id.textView3);
            trigName = itemView.findViewById(R.id.textView5);
        }
    }
}
