package com.pany.controller.presentation.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pany.controller.R;
import com.pany.controller.presentation.viewmodel.devices.DeviceModel;
import com.pany.controller.presentation.viewmodel.devices.DeviceRecyclerViewAdapter;


import java.util.ArrayList;

public class DevicesActivity extends AppCompatActivity {

    ArrayList<DeviceModel> deviceModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        RecyclerView recyclerView = findViewById(R.id.device_list);
        setUpDevicesModels();
        DeviceRecyclerViewAdapter adapter = new DeviceRecyclerViewAdapter(this, deviceModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpDevicesModels(){
        String[] deviceNames = getResources().getStringArray(R.array.devices1);
        String[] deviceIds = getResources().getStringArray(R.array.devices_ids);
        int[] conPeriph = {3, 5};

        for (int i = 0; i<deviceNames.length; i++){
            deviceModels.add(new DeviceModel(deviceNames[i], deviceIds[i], conPeriph[i]));
        }
    }

    public void goBack(View v){
        finish();
    }
}