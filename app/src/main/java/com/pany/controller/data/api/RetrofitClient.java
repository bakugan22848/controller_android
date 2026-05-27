package com.pany.controller.data.api;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.pany.controller.presentation.ui.LoginActivity;
import com.pany.controller.utils.TokenManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:8000/"; // Змінюй на свою адресу
    private static Retrofit retrofit = null;
    private static TokenManager tokenManager;

    public static Retrofit getClient(Context context) {
        if (tokenManager == null) {
            tokenManager = new TokenManager(context);
        }

        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder();

                        // Автоматично додаємо токен якщо він є
                        String token = tokenManager.getToken();
                        if (token != null) {
                            requestBuilder.header("Authorization", "Bearer " + token);
                        }

                        Request request = requestBuilder.build();
                        Response response = chain.proceed(request);

                        if (response.code() == 403) {
                            tokenManager.clearToken();

                            android.os.Handler mainHandler = new android.os.Handler(context.getMainLooper());
                            mainHandler.post(() ->
                                    Toast.makeText(context, "Session expired. Please login again.", Toast.LENGTH_LONG).show()
                            );

                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                        }

                        return response;


                    })
                    .addInterceptor(logging)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService(Context context) {
        return getClient(context).create(ApiService.class);
    }
}