package com.pany.controller.data.repository;

import android.content.Context;
import android.widget.Toast;

import com.pany.controller.data.api.ApiService;
import com.pany.controller.data.api.RetrofitClient;
import com.pany.controller.data.models.Trigger;
import com.pany.controller.data.models.TriggerCreate;
import com.pany.controller.data.models.TriggerUpdate;
import com.pany.controller.utils.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TriggerRepository {
    private ApiService apiService;
    private TokenManager tokenManager;
    private Context context;

    public interface OnSuccess<T> {
        void onSuccess(T data);
    }

    public interface OnError {
        void onError(String message);
    }

    public TriggerRepository(Context context) {
        this.context = context;
        this.apiService = RetrofitClient.getApiService(context);
        this.tokenManager = new TokenManager(context);
    }

    private String token() {
        return "Bearer " + tokenManager.getToken();
    }

    public void getAll(String deviceId, OnSuccess<List<Trigger>> onSuccess, OnError onError) {
        apiService.getTriggersByDevice(token(), deviceId).enqueue(new Callback<List<Trigger>>() {
            @Override
            public void onResponse(Call<List<Trigger>> call, Response<List<Trigger>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to load triggers");
                }
            }
            @Override
            public void onFailure(Call<List<Trigger>> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }

    public void create(TriggerCreate body, OnSuccess<Trigger> onSuccess, OnError onError) {
        apiService.createTrigger(token(), body).enqueue(new Callback<Trigger>() {
            @Override
            public void onResponse(Call<Trigger> call, Response<Trigger> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to create trigger");
                }
            }
            @Override
            public void onFailure(Call<Trigger> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }

    public void update(String id, TriggerUpdate body, OnSuccess<Trigger> onSuccess, OnError onError) {
        apiService.updateTrigger(token(), id, body).enqueue(new Callback<Trigger>() {
            @Override
            public void onResponse(Call<Trigger> call, Response<Trigger> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to update trigger");
                }
            }
            @Override
            public void onFailure(Call<Trigger> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }

    public void delete(String id, OnSuccess<Trigger> onSuccess, OnError onError) {
        apiService.deleteTrigger(token(), id).enqueue(new Callback<Trigger>() {
            @Override
            public void onResponse(Call<Trigger> call, Response<Trigger> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to delete trigger");
                }
            }
            @Override
            public void onFailure(Call<Trigger> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }
}
