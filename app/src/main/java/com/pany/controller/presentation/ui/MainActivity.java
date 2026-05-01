package com.pany.controller.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pany.controller.R;
import com.pany.controller.presentation.viewmodel.notifications.NotifRecyclerViewAdapter;
import com.pany.controller.presentation.viewmodel.notifications.NotificationModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<NotificationModel> notificationModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.notif_list);
        setUpNotificationModels();
        NotifRecyclerViewAdapter adapter = new NotifRecyclerViewAdapter(this, notificationModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void startDeviceAct(View v){
        Intent intent = new Intent(this, DevicesActivity.class);
        startActivity(intent);
    }

    public void startSettingsAct(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void startProfileAct(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void setUpNotificationModels(){
        String[] deviceNames = getResources().getStringArray(R.array.device);
        String[] trigerNames = getResources().getStringArray(R.array.trigger_names);

        for (int i = 0; i<deviceNames.length; i++){
            notificationModels.add(new NotificationModel(deviceNames[i], trigerNames[i]));
        }
    }
}