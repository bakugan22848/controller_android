package com.pany.controller.data.repository;

import android.content.Context;

import com.pany.controller.data.api.ApiService;
import com.pany.controller.data.api.RetrofitClient;
import com.pany.controller.data.models.Device;
import com.pany.controller.data.models.DeviceUpdate;
import com.pany.controller.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceRepository {
    private ApiService apiService;
    private TokenManager tokenManager;

    public interface OnSuccess<T> {
        void onSuccess(T data);
    }

    public interface OnError {
        void onError(String message);
    }

    public DeviceRepository(Context context) {
        this.apiService = RetrofitClient.getApiService(context);
        this.tokenManager = new TokenManager(context);
    }

    private String token() {
        return "Bearer " + tokenManager.getToken();
    }

    public void update(String id, String newName, OnSuccess<Device> onSuccess, OnError onError) {
        DeviceUpdate body = new DeviceUpdate(newName);
        apiService.updateDevice(token(), id, body).enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to update device");
                }
            }
            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }

    /*public void firmware(String id, OnSuccess<Device> onSuccess, OnError onError){
        apiService.buildFirmware(token(), id){

        }
    }*/

    public void delete(String id, OnSuccess<Device> onSuccess, OnError onError) {
        apiService.deleteDevice(token(), id).enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onSuccess.onSuccess(response.body());
                } else {
                    onError.onError("Failed to delete device");
                }
            }
            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                onError.onError(t.getMessage());
            }
        });
    }
}
