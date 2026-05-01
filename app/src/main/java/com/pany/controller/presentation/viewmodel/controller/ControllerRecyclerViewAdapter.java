package com.pany.controller.presentation.viewmodel.controller;

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

public class ControllerRecyclerViewAdapter extends RecyclerView.Adapter<ControllerRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ControllerModel> controllerModels;

    public ControllerRecyclerViewAdapter(Context context, ArrayList<ControllerModel> controllerModels){
        this.context = context;
        this.controllerModels = controllerModels;
    }

    @NonNull
    @Override
    public ControllerRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_controller, parent, false);

        return new ControllerRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ControllerRecyclerViewAdapter.MyViewHolder holder, int position) {
        ControllerModel model = controllerModels.get(position);

        holder.contrName.setText(model.getContrName());
        holder.contrId.setText(model.getContrId());
        holder.aimTrig.setText(model.getAimTrig());

        holder.state.setOnCheckedChangeListener(null);
        holder.state.setChecked(model.isState());

        holder.state.setOnCheckedChangeListener((buttonView, isChecked) -> {
            model.setState(isChecked);
        });
    }

    public void updateState(int position, boolean newState){
        controllerModels.get(position).setState(newState);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return controllerModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView contrName, contrId, aimTrig;
        Switch state;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contrName = itemView.findViewById(R.id.contr_name);
            contrId = itemView.findViewById(R.id.aim_t_tag);
            aimTrig = itemView.findViewById(R.id.aim_trig);
            state = itemView.findViewById(R.id.switch1);

        }
    }
}
