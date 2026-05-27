package com.pany.controller.presentation.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pany.controller.R;
import com.pany.controller.data.api.ApiService;
import com.pany.controller.data.api.RetrofitClient;

import com.pany.controller.data.models.Controller;
import com.pany.controller.data.models.ControllerCreate;
import com.pany.controller.data.models.ControllerUpdate;
import com.pany.controller.data.models.Trigger;
import com.pany.controller.data.models.TriggerCreate;
import com.pany.controller.data.models.TriggerUpdate;

import com.pany.controller.data.repository.ControllerRepository;
import com.pany.controller.data.repository.DeviceRepository;
import com.pany.controller.data.repository.TriggerRepository;

import com.pany.controller.presentation.viewmodel.controller.ControllerModel;
import com.pany.controller.presentation.viewmodel.controller.ControllerRecyclerViewAdapter;
import com.pany.controller.presentation.viewmodel.trigger.TriggerModel;
import com.pany.controller.presentation.viewmodel.trigger.TriggerRecyclerViewAdapter;

import com.pany.controller.utils.TokenManager;

import java.util.ArrayList;



public class PeripheralActivity extends AppCompatActivity {

    ArrayList<ControllerModel> controllersModels = new ArrayList<>();
    ArrayList<TriggerModel> triggersModels = new ArrayList<>();

    private TriggerRecyclerViewAdapter tAdapter;
    private ControllerRecyclerViewAdapter cAdapter;

    private ApiService apiService;
    private TokenManager tokenManager;


    private String deviceId;
    private String deviceName;

    private TriggerRepository triggerRepository;
    private ControllerRepository controllerRepository;
    private DeviceRepository deviceRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peripheral);

        apiService = RetrofitClient.getApiService(this);
        tokenManager = new TokenManager(this);

        triggerRepository = new TriggerRepository(this);
        controllerRepository = new ControllerRepository(this);
        deviceRepository = new DeviceRepository(this);

        deviceId = getIntent().getStringExtra("device_id");
        deviceName = getIntent().getStringExtra("device_name");

        if (deviceId == null) {
            Toast.makeText(this, "Device not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView deviceNameView = findViewById(R.id.device_name);
        deviceNameView.setText(deviceName);

        findViewById(R.id.btn_device_settings).setOnClickListener(v -> showDeviceSettingsDialog());

        RecyclerView controllerList = findViewById(R.id.controller_list);
        cAdapter = new ControllerRecyclerViewAdapter(this, controllersModels, (model, position) -> {
            showUpdateControllerDialog(model, position);
        });
        controllerList.setAdapter(cAdapter);
        controllerList.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView triggerList = findViewById(R.id.trigger_list);
        tAdapter = new TriggerRecyclerViewAdapter(this, triggersModels, (model, position) -> {
            showUpdateTriggerDialog(model, position);
        });
        triggerList.setAdapter(tAdapter);
        triggerList.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab_add_peripheral);
        fab.setOnClickListener(v -> showAddPeripheralDialog());

        loadTriggers();
        loadControllers();

    }

    private void loadTriggers() {
        triggerRepository.getAll(deviceId,
                triggers -> {
                    triggersModels.clear();
                    for (Trigger t : triggers) {
                        triggersModels.add(new TriggerModel(
                                t.getName(), t.getId(),
                                t.getNotifValue(),
                                t.getLastValue()
                        ));
                    }
                    tAdapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        );
    }

    private void loadControllers() {
        controllerRepository.getAll(deviceId,
                controllers -> {
                    controllersModels.clear();
                    for (Controller c : controllers) {
                        controllersModels.add(new ControllerModel(
                                c.getName(), c.getId(), c.getTriggerId(),
                                c.getTriggerValue(), c.getLastState(),
                                c.getTriggerVector(), c.getAutomatic()
                        ));
                    }
                    cAdapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        );
    }


    private void showDeviceSettingsDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Settings");

        EditText input = new EditText(this);
        input.setHint("New device name");
        input.setText(deviceName);
        input.setPadding(48, 24, 48, 24);
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) updateDevice(newName);
        });

        builder.setNeutralButton("Delete", (dialog, which) -> showDeleteDeviceConfirmation());
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showDeleteDeviceConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Device")
                .setMessage("Are you sure you want to delete " + deviceName + "?")
                .setPositiveButton("Delete", (dialog, which) -> deleteDevice())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void updateDevice(String newName) {
        deviceRepository.update(deviceId, newName,
                device -> {
                    deviceName = newName;
                    ((TextView) findViewById(R.id.device_name)).setText(newName);
                    Toast.makeText(this, "Device updated!", Toast.LENGTH_SHORT).show();
                },
                error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        );
    }

    private void deleteDevice() {
        deviceRepository.delete(deviceId,
                device -> {
                    Toast.makeText(this, "Device deleted!", Toast.LENGTH_SHORT).show();
                    finish();
                },
                error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        );
    }

    private void showAddPeripheralDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Peripheral");

        // Вибір типу
        String[] types = {"Trigger", "Controller"};
        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        spinner.setAdapter(spinnerAdapter);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 24, 48, 24);
        layout.addView(spinner);

        builder.setView(layout);

        builder.setPositiveButton("Next", (dialog, which) -> {
            String selectedType = spinner.getSelectedItem().toString();
            if (selectedType.equals("Trigger")) {
                showAddTriggerDialog();
            } else {
                showAddControllerDialog();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showAddTriggerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Trigger");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 24, 48, 24);

        EditText nameInput = new EditText(this);
        nameInput.setHint("Trigger name");
        layout.addView(nameInput);

        EditText notifValueInput = new EditText(this);
        notifValueInput.setHint("Notification value");
        notifValueInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        layout.addView(notifValueInput);

        EditText checkClockInput = new EditText(this);
        checkClockInput.setHint("Check clock (seconds)");
        checkClockInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        layout.addView(checkClockInput);

        EditText writeClockInput = new EditText(this);
        writeClockInput.setHint("Write clock (seconds)");
        writeClockInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        layout.addView(writeClockInput);

        builder.setView(layout);
        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String notifValue = notifValueInput.getText().toString().trim();
            String checkClock = checkClockInput.getText().toString().trim();
            String writeClock = writeClockInput.getText().toString().trim();

            if (name.isEmpty() || notifValue.isEmpty() || checkClock.isEmpty() || writeClock.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            TriggerCreate body = new TriggerCreate(
                    name, deviceId, null,
                    Float.parseFloat(notifValue),
                    Integer.parseInt(checkClock),
                    Integer.parseInt(writeClock)
            );

            triggerRepository.create(body,
                    trigger -> {
                        Toast.makeText(this, "Trigger added!", Toast.LENGTH_SHORT).show();
                        loadTriggers();
                    },
                    error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            );
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showAddControllerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Controller");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 24, 48, 24);

        EditText nameInput = new EditText(this);
        nameInput.setHint("Controller name");
        layout.addView(nameInput);

        EditText triggerValueInput = new EditText(this);
        triggerValueInput.setHint("Trigger value");
        triggerValueInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        layout.addView(triggerValueInput);

        builder.setView(layout);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String triggerValue = triggerValueInput.getText().toString().trim();

            if (name.isEmpty() || triggerValue.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            ControllerCreate body = new ControllerCreate(
                    name, deviceId, Integer.parseInt(triggerValue)
            );

            controllerRepository.create(body,
                    controller -> {
                        Toast.makeText(this, "Controller added!", Toast.LENGTH_SHORT).show();
                        loadControllers();
                    },
                    error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            );
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showUpdateTriggerDialog(TriggerModel model, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Trigger: " + model.getTrigName());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 24, 48, 24);

        EditText nameInput = new EditText(this);
        nameInput.setHint("Name");
        nameInput.setText(model.getTrigName());
        layout.addView(nameInput);

        EditText notifValInput = new EditText(this);
        notifValInput.setHint("Notification value");
        notifValInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        notifValInput.setText(model.getTrigVal() != null ? String.valueOf(model.getTrigVal()) : "");
        layout.addView(notifValInput);

        EditText checkClockInput = new EditText(this);
        checkClockInput.setHint("Check clock");
        checkClockInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        layout.addView(checkClockInput);

        EditText writeClockInput = new EditText(this);
        writeClockInput.setHint("Write clock");
        writeClockInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        layout.addView(writeClockInput);

        builder.setView(layout);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String notifVal = notifValInput.getText().toString().trim();
            String checkClock = checkClockInput.getText().toString().trim();
            String writeClock = writeClockInput.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            TriggerUpdate body = new TriggerUpdate(
                    name, null,
                    checkClock.isEmpty() ? null : Integer.parseInt(checkClock),
                    notifVal.isEmpty() ? null : Float.parseFloat(notifVal),
                    writeClock.isEmpty() ? null : Integer.parseInt(writeClock)
            );

            triggerRepository.update(model.getTrigId(), body,
                    trigger -> {
                        Toast.makeText(this, "Trigger updated!", Toast.LENGTH_SHORT).show();
                        loadTriggers();
                    },
                    error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            );
        });

        builder.setNeutralButton("Delete", (dialog, which) ->
                new AlertDialog.Builder(this)
                        .setTitle("Delete Trigger")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Delete", (d, w) ->
                                triggerRepository.delete(model.getTrigId(),
                                        data -> {
                                            triggersModels.remove(position);
                                            tAdapter.notifyItemRemoved(position);
                                            Toast.makeText(this, "Trigger deleted!", Toast.LENGTH_SHORT).show();
                                        },
                                        error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                                )
                        )
                        .setNegativeButton("Cancel", (d, w) -> d.dismiss())
                        .show()
        );

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showUpdateControllerDialog(ControllerModel model, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Controller: " + model.getContrName());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 24, 48, 24);

        EditText nameInput = new EditText(this);
        nameInput.setHint("Name");
        nameInput.setText(model.getContrName());
        layout.addView(nameInput);

        EditText trigValInput = new EditText(this);
        trigValInput.setHint("Trigger value");
        trigValInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        trigValInput.setText(model.getTrigVal() != null ? String.valueOf(model.getTrigVal()) : "");
        layout.addView(trigValInput);

        builder.setView(layout);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String trigVal = trigValInput.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            ControllerUpdate body = new ControllerUpdate(
                    model.isState(),
                    trigVal.isEmpty() ? null : Integer.parseInt(trigVal),
                    name

            );

            controllerRepository.update(model.getContrId(), body,
                    controller -> {
                        Toast.makeText(this, "Controller updated!", Toast.LENGTH_SHORT).show();
                        loadControllers();
                    },
                    error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            );
        });

        builder.setNeutralButton("Delete", (dialog, which) ->
                new AlertDialog.Builder(this)
                        .setTitle("Delete Controller")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Delete", (d, w) ->
                                controllerRepository.delete(model.getContrId(),
                                        data -> {
                                            controllersModels.remove(position);
                                            cAdapter.notifyItemRemoved(position);
                                            Toast.makeText(this, "Controller deleted!", Toast.LENGTH_SHORT).show();
                                        },
                                        error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                                )
                        )
                        .setNegativeButton("Cancel", (d, w) -> d.dismiss())
                        .show()
        );

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


    public void goBack(View v){
        finish();
    }
}