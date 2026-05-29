package com.pany.controller.data.repository;

import android.content.Context;

import com.pany.controller.data.api.ApiService;
import com.pany.controller.data.api.RetrofitClient;
import com.pany.controller.data.models.Controller;
import com.pany.controller.data.models.ControllerCreate;
import com.pany.controller.data.models.ControllerUpdate;
import com.pany.controller.utils.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControllerRepository {
    private ApiService apiService;
    private TokenManager tokenManager;
    private Context context;

    public interface OnSuccess<T> {
        void onSuccess(T data);
    }

    public interface OnError {
        void onError(String message);
    }

    public ControllerRepository(Context context) {
        this.context = context;
        this.apiService = RetrofitClient.getApiService(context);
        this.tokenManager = new TokenManager(context);
    }

    private String token() {
        return "Bearer " + tokenManager.getToken();
    }

    public void getAll(String deviceId, OnSuccess<List<Controller>> onSuccess, OnError onError) {
        apiService.getControllersByDevice(token(), deviceId).enqueue(new Callback<List<Controller>>() {
            @Override
            public void onResponse(Call<List<Controller>> call, Response<List<Controller>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to load controllers");
                }
            }
            @Override
            public void onFailure(Call<List<Controller>> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }

    public void create(ControllerCreate body, OnSuccess<Controller> onSuccess, OnError onError) {
        apiService.createController(token(), body).enqueue(new Callback<Controller>() {
            @Override
            public void onResponse(Call<Controller> call, Response<Controller> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to create controller");
                }
            }
            @Override
            public void onFailure(Call<Controller> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }

    public void update(String id, ControllerUpdate body, OnSuccess<Controller> onSuccess, OnError onError) {
        apiService.updateController(token(), id, body).enqueue(new Callback<Controller>() {
            @Override
            public void onResponse(Call<Controller> call, Response<Controller> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to update controller");
                }
            }
            @Override
            public void onFailure(Call<Controller> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }

    public void updateState(String id, Boolean lastState, OnSuccess<Controller> onSuccess, OnError onError) {
        ControllerUpdate body = new ControllerUpdate(null, null, null,
                null, lastState, null, null);
        update(id, body, onSuccess, onError);
    }

    public void updateAutomatic(String id, Boolean isAutomatic, OnSuccess<Controller> onSuccess, OnError onError) {
        ControllerUpdate body = new ControllerUpdate(null, null, null,
                null, null, isAutomatic, null);
        update(id, body, onSuccess, onError);
    }

    public void delete(String id, OnSuccess<Controller> onSuccess, OnError onError) {
        apiService.deleteController(token(), id).enqueue(new Callback<Controller>() {
            @Override
            public void onResponse(Call<Controller> call, Response<Controller> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to delete controller");
                }
            }
            @Override
            public void onFailure(Call<Controller> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }
}
