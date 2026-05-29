package com.pany.controller.data.api;

import retrofit2.http.*;
import retrofit2.Call;
import java.util.List;

import com.pany.controller.data.models.*;

public interface ApiService {

    @POST("jwt/signup")
    Call<TokenResponse> signUp(@Body SignUpRequest request);

    @POST("jwt/signin")
    Call<TokenResponse> signIn(@Body SignInRequest request);

    @GET("jwt/me")
    Call<User> getMe(@Header("Authorization") String token);

    @GET("device/")
    Call<List<Device>> getMyDevices(@Header("Authorization") String token);

    @POST("device/")
    Call<Device> createDevice(
            @Header("Authorization") String token,
            @Body DeviceCreate request
    );

    @POST("/device/{device_id}/build")
    Call<Device> buildFirmware(
            @Header("Authorization") String token,
            @Path("id") String deviceId
    );

    @PUT("device/{id}")
    Call<Device> updateDevice(
            @Header("Authorization") String token,
            @Path("id") String deviceId,
            @Body DeviceUpdate request
    );

    @DELETE("device/{id}")
    Call<Device> deleteDevice(
            @Header("Authorization") String token,
            @Path("id") String deviceId
    );

    @GET("triggers/{device_id}")
    Call<List<Trigger>> getTriggersByDevice(
            @Header("Authorization") String token,
            @Path("device_id") String deviceId
    );

    @POST("trigger/")
    Call<Trigger> createTrigger(
            @Header("Authorization") String token,
            @Body TriggerCreate request
    );

    @PUT("trigger/{id}")
    Call<Trigger> updateTrigger(
            @Header("Authorization") String token,
            @Path("id") String triggerId,
            @Body TriggerUpdate request
    );

    @DELETE("trigger/{id}")
    Call<Trigger> deleteTrigger(
            @Header("Authorization") String token,
            @Path("id") String triggerId
    );

    @GET("controller/")
    Call<List<Controller>> getControllersByDevice(
            @Header("Authorization") String token,
            @Query("device_id") String deviceId
    );

    @POST("controller/")
    Call<Controller> createController(
            @Header("Authorization") String token,
            @Body ControllerCreate request
    );

    @PUT("controller/{id}")
    Call<Controller> updateController(
            @Header("Authorization") String token,
            @Path("id") String controllerId,
            @Body ControllerUpdate request
    );

    @DELETE("controller/{id}")
    Call<Controller> deleteController(
            @Header("Authorization") String token,
            @Path("id") String controllerId
    );
}
