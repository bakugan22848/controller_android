package com.pany.controller.presentation.viewmodel.devices;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pany.controller.R;
import com.pany.controller.presentation.ui.PeripheralActivity;

import java.util.ArrayList;

public class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<DeviceModel>  deviceModels;

    public DeviceRecyclerViewAdapter(Context context, ArrayList<DeviceModel> deviceModels){
        this.context = context;
        this.deviceModels = deviceModels;
    }

    @NonNull
    @Override
    public DeviceRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_device, parent, false);

        return new DeviceRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceRecyclerViewAdapter.MyViewHolder holder, int position) {
        DeviceModel device = deviceModels.get(position);
        holder.devName.setText(deviceModels.get(position).getDeviceName());
        holder.devId.setText(deviceModels.get(position).getDeviceId());
        holder.conPeriph.setText(String.valueOf(deviceModels.get(position).getConnectPeriph()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PeripheralActivity.class);
            intent.putExtra("device_id", device.getDeviceId());
            intent.putExtra("device_name", device.getDeviceName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return deviceModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView devName, devId, conPeriph;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            devName = itemView.findViewById(R.id.dev_name);
            devId = itemView.findViewById(R.id.val_id);
            conPeriph = itemView.findViewById(R.id.con_val);
        }
    }
}

