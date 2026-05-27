package com.pany.controller.presentation.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pany.controller.R;
import com.pany.controller.data.api.ApiService;
import com.pany.controller.data.api.RetrofitClient;
import com.pany.controller.data.models.Device;
import com.pany.controller.data.models.DeviceCreate;
import com.pany.controller.presentation.viewmodel.devices.DeviceModel;
import com.pany.controller.presentation.viewmodel.devices.DeviceRecyclerViewAdapter;
import com.pany.controller.utils.TokenManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevicesActivity extends AppCompatActivity {

    ArrayList<DeviceModel> deviceModels = new ArrayList<>();
    private ApiService apiService;
    private TokenManager tokenManager;
    private DeviceRecyclerViewAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        apiService = RetrofitClient.getApiService(this);
        tokenManager = new TokenManager(this);

        RecyclerView recyclerView = findViewById(R.id.device_list);
        progressBar = findViewById(R.id.progress_bar);

        adapter = new DeviceRecyclerViewAdapter(this, deviceModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab_add_device);
        fab.setOnClickListener(v -> showAddDeviceDialog());

        loadDevices();

    }

    private void showAddDeviceDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Device");

        EditText input = new EditText(this);
        input.setHint("Device name");
        input.setPadding(48, 24, 48, 24);
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = input.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter device name", Toast.LENGTH_SHORT).show();
                return;
            }
            createDevice(name);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void createDevice(String name) {
        String token = "Bearer " + tokenManager.getToken();


        DeviceCreate body = new DeviceCreate(name);

        apiService.createDevice(token, body).enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(DevicesActivity.this, "Device added!", Toast.LENGTH_SHORT).show();
                    deviceModels.clear();
                    loadDevices();
                } else {
                    Toast.makeText(DevicesActivity.this, "Failed to add device", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Toast.makeText(DevicesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDevices(){
        progressBar.setVisibility(View.VISIBLE);
        String token = "Bearer " + tokenManager.getToken();

        apiService.getMyDevices(token).enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<Device> devices = response.body();

                    for (Device device : devices) {
                        deviceModels.add(new DeviceModel(
                                device.getName(),
                                device.getId(),
                                device.getPeriphCount()
                        ));
                    }
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(DevicesActivity.this, "Failed to load devices", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DevicesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void goBack(View v){
        finish();
    }
}