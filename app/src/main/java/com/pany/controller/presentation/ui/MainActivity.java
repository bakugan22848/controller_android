package com.pany.controller.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pany.controller.R;
import com.pany.controller.presentation.viewmodel.notifications.NotifRecyclerViewAdapter;
import com.pany.controller.presentation.viewmodel.notifications.NotificationModel;
import com.pany.controller.utils.TokenManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<NotificationModel> notificationModels = new ArrayList<>();

    private Button devicesButton;
    private Button settingsButton;
    private Button profileButton;
    private Button loginButton;
    private Button signUpButton;

    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenManager = new TokenManager(this);

        devicesButton = findViewById(R.id.devices_button);
        settingsButton = findViewById(R.id.settings_button);
        profileButton = findViewById(R.id.profile_button);
        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.signup_button);

        RecyclerView recyclerView = findViewById(R.id.notif_list);
        setUpNotificationModels();
        NotifRecyclerViewAdapter adapter = new NotifRecyclerViewAdapter(this, notificationModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUIBasedOnAuth();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUIBasedOnAuth(); // Оновлюємо UI кожен раз коли активіті стає видимою
    }

    private void updateUIBasedOnAuth() {
        boolean isLoggedIn = tokenManager.isLoggedIn();

        devicesButton.setEnabled(isLoggedIn);
        settingsButton.setEnabled(isLoggedIn);
        profileButton.setEnabled(isLoggedIn);

        loginButton.setVisibility(isLoggedIn ? View.GONE : View.VISIBLE);
        signUpButton.setVisibility(isLoggedIn ? View.GONE : View.VISIBLE);
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

    public void startLoginAct(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void startSignUpAct(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
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