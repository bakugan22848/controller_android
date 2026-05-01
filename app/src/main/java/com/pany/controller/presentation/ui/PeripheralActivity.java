package com.pany.controller.presentation.ui;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.pany.controller.R;
import com.pany.controller.presentation.viewmodel.controller.ControllerModel;
import com.pany.controller.presentation.viewmodel.controller.ControllerRecyclerViewAdapter;
import com.pany.controller.presentation.viewmodel.devices.DeviceRecyclerViewAdapter;
import com.pany.controller.presentation.viewmodel.trigger.TriggerModel;
import com.pany.controller.presentation.viewmodel.trigger.TriggerRecyclerViewAdapter;

import java.util.ArrayList;

public class PeripheralActivity extends AppCompatActivity {

    ArrayList<ControllerModel> controllersModels = new ArrayList<>();
    ArrayList<TriggerModel> triggersModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peripheral);

        RecyclerView controllerList = findViewById(R.id.controller_list);
        setUpControllersModels();
        ControllerRecyclerViewAdapter cAdapter = new ControllerRecyclerViewAdapter(this, controllersModels);
        controllerList.setAdapter(cAdapter);
        controllerList.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView triggerList = findViewById(R.id.trigger_list);
        setUpTriggersModels();
        TriggerRecyclerViewAdapter tAdapter = new TriggerRecyclerViewAdapter(this, triggersModels);
        triggerList.setAdapter(tAdapter);
        triggerList.setLayoutManager(new LinearLayoutManager(this));

    }
    public void goBack(View v){
        finish();
    }

    private void setUpTriggersModels(){
        String[] triggersNames = getResources().getStringArray(R.array.trigger_names);
        String[] aimControllers = getResources().getStringArray(R.array.control_ids);
        String[] trggerIds = getResources().getStringArray(R.array.trig_ids);
        int[] curVals = {30, 45, 50, 80};
        int[] trigVals = {40, 50, 55, 90};

        for (int i = 0; i<triggersNames.length; i++){
            triggersModels.add(new TriggerModel(triggersNames[i], aimControllers[i], trggerIds[i], curVals[i], trigVals[i]));
        }
    }

    private void setUpControllersModels(){
        String[] controllersNames = getResources().getStringArray(R.array.control_names);
        String[] controllersIds = getResources().getStringArray(R.array.control_ids);
        String[] aimTriggers = getResources().getStringArray(R.array.trig_ids);
        boolean[] conStates = {true, false, false,true};

        for (int i = 0; i<controllersNames.length; i++){
            controllersModels.add(new ControllerModel(controllersNames[i], controllersIds[i], aimTriggers[i], conStates[i]));
        }
    }
}