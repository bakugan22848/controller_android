package com.pany.controller.presentation.viewmodel.trigger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pany.controller.R;

import java.util.ArrayList;

public class TriggerRecyclerViewAdapter extends RecyclerView.Adapter<TriggerRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<TriggerModel> triggerModels;

    public TriggerRecyclerViewAdapter(Context context, ArrayList<TriggerModel> triggerModels){
        this.context = context;
        this.triggerModels = triggerModels;
    }

    @NonNull
    @Override
    public TriggerRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_trigger, parent, false);

        return new TriggerRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TriggerRecyclerViewAdapter.MyViewHolder holder, int position) {
        TriggerModel model = triggerModels.get(position);

        holder.trigName.setText(model.getTrigName());
        holder.aimContr.setText(model.getAimContr());
        holder.trigVal.setText(String.valueOf(model.getTrigVal()));
        holder.curVal.setText(String.valueOf(model.getCurVal()));

    }


    @Override
    public int getItemCount() {
        return triggerModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView trigName, trigId, aimContr, trigVal, curVal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trigName = itemView.findViewById(R.id.trig_name);
            aimContr = itemView.findViewById(R.id.aim_contr);
            trigVal = itemView.findViewById(R.id.trig_val);
            curVal = itemView.findViewById(R.id.cur_val);

        }
    }
}
