package com.pany.controller.presentation.viewmodel.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        holder.state.setOnCheckedChangeListener(null);
        holder.state.setChecked(model.isState() != null ? model.isState() : false);

        holder.state.setOnCheckedChangeListener((buttonView, isChecked) ->
                controllerRepository.updateState(model.getContrId(), isChecked,
                        data -> model.setState(isChecked),
                        error -> {
                            // Повертаємо Switch назад
                            holder.state.setOnCheckedChangeListener(null);
                            holder.state.setChecked(!isChecked);
                            model.setState(!isChecked);
                            holder.state.setOnCheckedChangeListener((b, newChecked) ->
                                    controllerRepository.updateState(model.getContrId(), newChecked,
                                            d -> model.setState(newChecked),
                                            e -> Toast.makeText(context, e, Toast.LENGTH_SHORT).show()
                                    )
                            );
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                )
        );

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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contrName = itemView.findViewById(R.id.contr_name);
            contrId = itemView.findViewById(R.id.aim_t_tag);
            trigVal = itemView.findViewById(R.id.aim_trig);
            state = itemView.findViewById(R.id.switch1);

        }
    }
}
