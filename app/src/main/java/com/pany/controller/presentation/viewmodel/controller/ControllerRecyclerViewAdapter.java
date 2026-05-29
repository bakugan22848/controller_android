package com.pany.controller.presentation.viewmodel.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pany.controller.R;
import com.pany.controller.data.api.ApiService;
import com.pany.controller.data.api.RetrofitClient;
import com.pany.controller.data.models.Controller;
import com.pany.controller.data.models.ControllerCreate;
import com.pany.controller.data.models.ControllerUpdate;
import com.pany.controller.data.repository.ControllerRepository;
import com.pany.controller.utils.TokenManager;

import java.util.ArrayList;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class ControllerRecyclerViewAdapter extends RecyclerView.Adapter<ControllerRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ControllerModel> controllerModels;
    private OnControllerClickListener listener;
    private ControllerRepository controllerRepository;

    public interface OnControllerClickListener {
        void onControllerClick(ControllerModel model, int position);
    }

    public ControllerRecyclerViewAdapter(Context context, ArrayList<ControllerModel> controllerModels,
                                         OnControllerClickListener listener){
        this.context = context;
        this.controllerModels = controllerModels;
        this.listener = listener;
        this.controllerRepository = new ControllerRepository(context);

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
        holder.trigVal.setText(String.valueOf(model.getTrigVal()));

        if (model.getTriggerId() == null){
            holder.isAutomatic.setVisibility(View.GONE);
            holder.state.setEnabled(true);
        } else {
            holder.isAutomatic.setVisibility(View.VISIBLE);

            holder.isAutomatic.setOnCheckedChangeListener(null);
            boolean isAuto = model.getAutomatic() != null ? model.getAutomatic() : false;
            holder.isAutomatic.setChecked(isAuto);
            holder.state.setEnabled(!isAuto);

            holder.isAutomatic.setOnCheckedChangeListener((buttonView, isChecked) -> {
                controllerRepository.updateAutomatic(model.getContrId(), isChecked,
                        data -> {
                            model.setAutomatic(isChecked);
                            holder.state.setEnabled(!isChecked); // блокуємо/розблоковуємо Switch

                            // --- НОВА ЛОГІКА: Якщо увімкнули автомат, скидаємо Switch ---
                            if (isChecked) {
                                // Тимчасово знімаємо слухач зі Switch, щоб він не відправив зайвий PUT/PATCH запит на сервер
                                holder.state.setOnCheckedChangeListener(null);

                                holder.state.setChecked(false); // вимикаємо візуально
                                model.setState(false);          // оновлюємо в локальній моделі

                                // Повертаємо слухач Switch назад, щоб він працював, якщо автомат знову вимкнуть
                                restoreSwitchListener(holder, model, position);
                            }
                        },
                        error -> {
                            // Відкат назад у разі помилки
                            holder.isAutomatic.setOnCheckedChangeListener(null);
                            holder.isAutomatic.setChecked(!isChecked);
                            Toast.makeText(context, "Помилка: " + error, Toast.LENGTH_SHORT).show();

                            // Переініціалізуємо слухач після відкату
                            notifyItemChanged(position);
                        }
                );
            });
        }

        holder.state.setOnCheckedChangeListener(null);
        holder.state.setChecked(model.isState() != null ? model.isState() : false);
        restoreSwitchListener(holder, model, position);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onControllerClick(model, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return controllerModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView contrName, contrId, trigVal;
        Switch state;

        CheckBox isAutomatic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contrName = itemView.findViewById(R.id.contr_name);
            contrId = itemView.findViewById(R.id.aim_trig);
            trigVal = itemView.findViewById(R.id.trig_val);
            state = itemView.findViewById(R.id.switch1);
            isAutomatic = itemView.findViewById(R.id.is_automatic);

        }
    }
    private void restoreSwitchListener(MyViewHolder holder, ControllerModel model, int position) {
        holder.state.setOnCheckedChangeListener((buttonView, isChecked) ->
                controllerRepository.updateState(model.getContrId(), isChecked,
                        data -> model.setState(isChecked),
                        error -> {
                            holder.state.setOnCheckedChangeListener(null);
                            holder.state.setChecked(!isChecked);
                            model.setState(!isChecked);
                            notifyItemChanged(position);
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }
}
